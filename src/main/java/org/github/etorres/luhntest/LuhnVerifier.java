package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.Tuple2;
import javaslang.collection.Seq;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static org.github.etorres.luhntest.NumberReverser.getDigitsAndReverse;

public class LuhnVerifier {

    private final static Predicate<Tuple2<Byte, Long>> isOddPosition = t -> t._2%2 == 0;

    private final static Predicate<Tuple2<Byte, Long>> isEvenPosition = t -> t._2%2 != 0;

    private final static Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> digitsWithIndex =
            Seq::zipWithIndex;

    private final static BiFunction<Byte, Byte, Byte> sumDigits = (d1, d2) -> (byte)(d1 + d2);

    private final static Function1<Number, Byte> getDigitsAndSum = NumberReverser.getDigits
            .andThen(s -> s.fold((byte)0, sumDigits));

    private final static Function1<Seq<Tuple2<Byte, Long>>, Byte> sumOddDigits = s -> s
            .filter(isOddPosition)
            .map(Tuple2::_1)
            .fold((byte)0, sumDigits);

    private final static Function1<Seq<Tuple2<Byte, Long>>, Byte> computeEventDigits = s -> s
            .filter(isEvenPosition)
            .map(t -> {
                byte twoTimes = (byte)(2*t._1);
                return twoTimes > 9 ? getDigitsAndSum.apply(twoTimes) : twoTimes;
            })
            .fold((byte)0, sumDigits);

    private final static Function1<Seq<Tuple2<Byte, Long>>, Boolean> verifyLuhn = s ->
            (sumOddDigits.apply(s) + computeEventDigits.apply(s))%10 == 0;


    public boolean verify(long number) {
        return verifyLuhn.apply(Function1.of(digitsWithIndex).memoized()
                .apply(getDigitsAndReverse.apply(number)));
    }

}

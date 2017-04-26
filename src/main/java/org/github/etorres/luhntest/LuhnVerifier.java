package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.Tuple2;
import javaslang.collection.Seq;

import java.util.function.BiFunction;
import java.util.function.Predicate;

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

    public boolean verify(long number) {
        // Reverse the digits
        Seq<Byte> reversedDigits = new LongReverser().reverseDigits(number);
        // Sum the odd digits
        Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> memDigitsWithIndex = Function1
                .of(digitsWithIndex).memoized();
        Seq<Tuple2<Byte, Long>> digitsWithIndexTuple = memDigitsWithIndex.apply(reversedDigits);
        Byte s1 = sumOddDigits.apply(digitsWithIndexTuple);
        // Compute the even digits
        Byte s2 = computeEventDigits.apply(digitsWithIndexTuple);
        return (s1 + s2)%10 == 0;
    }

}

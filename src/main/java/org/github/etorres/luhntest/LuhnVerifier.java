package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.Function3;
import javaslang.Tuple2;
import javaslang.collection.Seq;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.github.etorres.luhntest.NumberReverser.getDigitsAndReverse;

public class LuhnVerifier {

    private final static Predicate<Tuple2<Integer, Long>> isOddPosition = t -> t._2%2 == 0;

    private final static Predicate<Tuple2<Integer, Long>> isEvenPosition = t -> t._2%2 != 0;

    private final static Function1<Seq<Integer>, Seq<Tuple2<Integer, Long>>> digitsWithIndex =
            Seq::zipWithIndex;

    private final static Function1<Number, Integer> getDigitsAndSum = NumberReverser.getDigits
            .andThen(s -> s.sum().intValue());

    private final static Function<Tuple2<Integer, Long>, Integer> computeEvenDigit = t -> {
        Integer twoTimes = 2*t._1;
        return twoTimes > 9 ? getDigitsAndSum.apply(twoTimes) : twoTimes;
    };

    private final static Function3<Seq<Tuple2<Integer, Long>>,
                Predicate<Tuple2<Integer, Long>>,
                Function<Tuple2<Integer, Long>, Integer>,
                Integer> computeDigits = (s, p, fx) -> s
            .filter(p)
            .map(fx)
            .sum()
            .intValue();

    private final static Function1<Seq<Tuple2<Integer, Long>>, Boolean> verifyLuhn = s ->
            (computeDigits.apply(s, isOddPosition, Tuple2::_1)
                    + computeDigits.apply(s, isEvenPosition, computeEvenDigit))%10 == 0;

    public boolean verify(long number) {
        return verifyLuhn.apply(digitsWithIndex.memoized()
                .apply(getDigitsAndReverse.apply(number)));
    }

}

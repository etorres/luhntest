package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;
import javaslang.control.Try;

public interface NumberReverser<T extends Number> {

    Function1<Number, Seq<Integer>> getDigits = n -> Try.of(() ->
            List.ofAll(String.valueOf(Option.of(n).get()).toCharArray()))
            .getOrElse(List.empty())
            .map(d -> Integer.parseInt(d.toString()));

    Function1<Seq<Integer>, Seq<Integer>> reverse = Seq::reverse;

    Function1<Number, Seq<Integer>> getDigitsAndReverse = getDigits.andThen(reverse);

    default Seq<Integer> reverseDigits(T number) {
        return getDigitsAndReverse.apply(number);
    }

}

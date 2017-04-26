package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;
import javaslang.control.Try;

public interface NumberReverser<T extends Number> {

    Function1<Number, Seq<Character>> getDigits = n -> Try.of(() ->
            List.ofAll(String.valueOf(Option.of(n).get()).toCharArray()))
            .getOrElse(List.empty());

    Function1<Seq<Character>, Seq<Character>> reverse = Seq::reverse;

    Function1<Number, Seq<Character>> getDigitsAndReverse = getDigits.andThen(reverse);

    Seq<Character> reverseDigits(T sequence);

}

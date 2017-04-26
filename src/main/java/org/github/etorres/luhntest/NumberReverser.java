package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;
import javaslang.control.Try;

public interface NumberReverser<T extends Number> {

    Function1<Number, Seq<Byte>> getDigits = n -> Try.of(() ->
            List.ofAll(String.valueOf(Option.of(n).get()).toCharArray()))
            .getOrElse(List.empty())
            .map(d -> Byte.parseByte(d.toString()));

    Function1<Seq<Byte>, Seq<Byte>> reverse = Seq::reverse;

    Function1<Number, Seq<Byte>> getDigitsAndReverse = getDigits.andThen(reverse);

    default Seq<Byte> reverseDigits(T number) {
        return getDigitsAndReverse.apply(number);
    }

}

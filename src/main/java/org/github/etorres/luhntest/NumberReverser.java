package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.collection.Seq;

public class NumberReverser<T extends Number> implements SequenceReverser<T> {

    private static Function1<Number, Seq<Character>> reverseDigits = n -> List
            .ofAll(String.valueOf(n).toCharArray()).reverse();

    @Override
    public T reverse(Number sequence) {
        // TODO
        return null;
    }

}

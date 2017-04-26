package org.github.etorres.luhntest;

import javaslang.collection.Seq;

public class LongReverser implements NumberReverser<Long> {

    @Override
    public Seq<Character> reverseDigits(Long number) {
        return getDigitsAndReverse.apply(number);
    }

}

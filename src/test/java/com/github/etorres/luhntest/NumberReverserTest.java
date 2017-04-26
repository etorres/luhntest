package com.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.Seq;
import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;
import org.github.etorres.luhntest.LongReverser;
import org.github.etorres.luhntest.NumberReverser;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JMockit.class)
public class NumberReverserTest {

    @Test
    public void get_digits_fx_returns_digits() throws Exception {
        Function1<Number, Seq<Character>> getDigits = Deencapsulation
                .getField(NumberReverser.class,"getDigits");
        Seq<Character> digits = getDigits.apply(49927398716L);
        assertThat(digits).hasSize(11)
                .containsExactly('4', '9', '9', '2', '7', '3', '9', '8', '7', '1', '6');
    }

    @Test
    public void get_digits_fx_from_null_returns_empty_list() throws Exception {
        Function1<Number, Seq<Character>> getDigits = Deencapsulation
                .getField(NumberReverser.class,"getDigits");
        Seq<Character> digits = getDigits.apply(null);
        assertThat(digits).isEmpty();
    }

    @Test
    public void reverse_digits_returns_digits_in_reverse_order() throws Exception {
        LongReverser longReverser = new LongReverser();
        Seq<Character> reversedDigits = longReverser.reverseDigits(49927398716L);
        assertThat(reversedDigits).hasSize(11)
                .containsExactly('6', '1', '7', '8', '9', '3', '7', '2', '9', '9', '4');
    }

}

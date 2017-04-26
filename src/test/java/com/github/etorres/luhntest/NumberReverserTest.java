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
    public void whenGetDigits_thenCorrect() throws Exception {
        Function1<Number, Seq<Byte>> getDigits = Deencapsulation
                .getField(NumberReverser.class,"getDigits");
        Seq<Byte> digits = getDigits.apply(49927398716L);
        assertThat(digits).hasSize(11)
                .containsExactly(new Byte[]{ 4, 9, 9, 2, 7, 3, 9, 8, 7, 1, 6 });
    }

    @Test
    public void whenGetDigitsFromNull_thenGetEmptyList() throws Exception {
        Function1<Number, Seq<Byte>> getDigits = Deencapsulation
                .getField(NumberReverser.class,"getDigits");
        Seq<Byte> digits = getDigits.apply(null);
        assertThat(digits).isEmpty();
    }

    @Test
    public void whenReverseDigits_thenCorrect() throws Exception {
        LongReverser longReverser = new LongReverser();
        Seq<Byte> reversedDigits = longReverser.reverseDigits(49927398716L);
        assertThat(reversedDigits).hasSize(11)
                .containsExactly(new Byte[]{ 6, 1, 7, 8, 9, 3, 7, 2, 9, 9, 4 });
    }

}

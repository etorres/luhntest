package com.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.collection.Seq;
import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;
import org.github.etorres.luhntest.NumberReverser;
import org.github.etorres.luhntest.SequenceReverser;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JMockit.class)
public class SequenceReverserTest {

    @Test
    public void reversing_digits_fx_returns_digits_in_reverse_order() throws Exception {
        Function1<Number, Seq<Character>> reverseDigits = Deencapsulation
                .getField(NumberReverser.class,"reverseDigits");
        Seq<Character> reversedDigits = reverseDigits.apply(49927398716L);
        assertThat(reversedDigits).hasSize(11)
                .containsExactly('6', '1', '7', '8', '9', '3', '7', '2', '9', '9', '4');
    }

    @Test
    public void longNumberReversingTest() throws Exception {
        SequenceReverser<Long> numberReverser = new NumberReverser<>();
        Long reversed = numberReverser.reverse(49927398716L);
        assertThat(reversed).isEqualTo(61789372994L);
    }

}

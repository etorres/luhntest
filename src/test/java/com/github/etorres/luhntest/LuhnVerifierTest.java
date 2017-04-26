package com.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Seq;
import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;
import org.github.etorres.luhntest.LuhnVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JMockit.class)
public class LuhnVerifierTest {

    @Test
    public void whenSumTheOddDigits_thenCorrect() throws Exception {
        Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> digitsWithIndex = Deencapsulation
                .getField(LuhnVerifier.class, "digitsWithIndex");
        Seq<Tuple2<Byte, Long>> digitsWithIndexTuple = digitsWithIndex
                .apply(List.of(new Byte[]{ 6, 1, 7, 8, 9, 3, 7, 2, 9, 9, 4 }));
        Function1<Seq<Tuple2<Byte, Long>>, Byte> sumOddDigits = Deencapsulation
                .getField(LuhnVerifier.class, "sumOddDigits");
        Byte sum = sumOddDigits.apply(digitsWithIndexTuple);
        assertThat(sum).isEqualTo(Byte.valueOf((byte)42));
    }

    @Test
    public void whenComputeTheEvenDigits_thenCorrect() throws Exception {
        Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> digitsWithIndex = Deencapsulation
                .getField(LuhnVerifier.class, "digitsWithIndex");
        Seq<Tuple2<Byte, Long>> digitsWithIndexTuple = digitsWithIndex
                .apply(List.of(new Byte[]{ 6, 1, 7, 8, 9, 3, 7, 2, 9, 9, 4 }));
        Function1<Seq<Tuple2<Byte, Long>>, Byte> computeEventDigits = Deencapsulation
                .getField(LuhnVerifier.class, "computeEventDigits");
        Byte computed = computeEventDigits.apply(digitsWithIndexTuple);
        assertThat(computed).isEqualTo(Byte.valueOf((byte)28));
    }

    @Test
    public void whenVerifyLuhnNumber_thenCorrect() throws Exception {
        LuhnVerifier luhnVerifier = new LuhnVerifier();
        boolean result = luhnVerifier.verify(49927398716L);
        assertThat(result).isTrue();
    }

}

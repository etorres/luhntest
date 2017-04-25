package com.github.etorres.luhntest;

import org.github.etorres.luhntest.SequenceReverser;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SequenceReverserTest {

    @Test
    public void checkNumberReversing() throws Exception {
        SequenceReverser<Long> numberReverser = new SequenceReverser<>();
        Long reversed = numberReverser.reverse(49927398716L);
        assertThat("number is reversed", reversed, equalTo(61789372994L));
    }

}

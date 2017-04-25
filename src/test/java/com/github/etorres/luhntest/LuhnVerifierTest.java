package com.github.etorres.luhntest;

import org.github.etorres.luhntest.LuhnVerifier;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class LuhnVerifierTest {

    @Test
    public void checkCorrectNumber() throws Exception {
        LuhnVerifier luhnVerifier = new LuhnVerifier();
        boolean result = luhnVerifier.verify(49927398716L);
        assertThat("number is correct", result);
    }

}

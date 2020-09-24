package com.wellitonpaiva.dailyupdater;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CovidUpdaterTest {

    @Test
    void getNumberOfCases() throws URISyntaxException, MalformedURLException {
        CovidUpdater sut = new CovidUpdater();
        assertThat(sut.getNumberOfCases(), is(10));
    }
}
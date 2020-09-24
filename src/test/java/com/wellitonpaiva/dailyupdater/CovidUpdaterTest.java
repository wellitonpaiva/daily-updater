package com.wellitonpaiva.dailyupdater;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CovidUpdaterTest {

    @Test
    void getTotalNumberOfCases() {
        CovidUpdater sut = new CovidUpdater();
        assertThat(sut.getTotalNumberOfCases(), is(6634));
    }

    @Test
    void getLondonNumberOfCases() {
        CovidUpdater sut = new CovidUpdater();
        assertThat(sut.getLondonNumberOfCases(), is(620));
    }
}
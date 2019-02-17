package com.ood.weatherstation.weatherdata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class WeatherDataTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void observer_can_remove_itself_safely() {
        WeatherData wd = new WeatherData();
        SuicideObserver suicideObserver = new SuicideObserver(wd);
        NormalObserver normalObserver1 = new NormalObserver();
        NormalObserver normalObserver2 = new NormalObserver();

        wd.registerObserver(suicideObserver);
        wd.registerObserver(normalObserver1);
        wd.registerObserver(normalObserver2);
        try {
            wd.setMeasurements(1.0, 1.0, 1.0);
            String expectedOutput = "I am a suicide.\r\nI am a normal.\r\nI am a normal.\r\n";
            Assert.assertEquals(expectedOutput, output.toString());
        } catch (Exception e) {
            fail();
        }
    }

}
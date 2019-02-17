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

        wd.registerObserver(suicideObserver, 1);
        wd.registerObserver(normalObserver1, 2);
        wd.registerObserver(normalObserver2, 3);
        try {
            wd.setMeasurements(1.0, 1.0, 1.0);
            String expectedOutput = "I am a normal.\r\n" +
                    "I am a normal.\r\n" +
                    "I am a suicide.\r\n";
            Assert.assertEquals(expectedOutput, output.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void observers_have_priority() {
        WeatherData wd = new WeatherData();
        SuicideObserver suicideObserver = new SuicideObserver(wd);
        NormalObserver normalObserver1 = new NormalObserver();
        NormalObserver normalObserver2 = new NormalObserver();
        NormalObserver normalObserver3 = new NormalObserver();

        wd.registerObserver(suicideObserver, 1);
        wd.registerObserver(normalObserver1, 2);
        wd.registerObserver(normalObserver2, 3);
        wd.registerObserver(suicideObserver, 5);
        wd.registerObserver(normalObserver3, 10);

        wd.setMeasurements(1.0, 1.0, 1.0);
        String expectedOutput = "I am a normal.\r\n" +
                "I am a suicide.\r\n" +
                "I am a normal.\r\n" +
                "I am a normal.\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

}
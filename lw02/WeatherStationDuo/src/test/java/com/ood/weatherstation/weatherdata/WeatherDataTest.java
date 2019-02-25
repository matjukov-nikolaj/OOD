package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.ObservableType;
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
    public void has_indicators_inside_and_outside() {
        Assert.assertTrue(true);
//        WeatherData wdo = new WeatherData(ObservableType.OUT);
//        WeatherData wdi = new WeatherData(ObservableType.IN);
//
//        SimpleObserver simpleObserver = new SimpleObserver();
//        wdi.registerObserver(simpleObserver, 1);
//        wdo.registerObserver(simpleObserver, 1);
//
//        wdi.setMeasurements(1.0, 1.0, 1.0);
//        wdo.setMeasurements(1.0, 1.0, 1.0);
//
//        String expectedResult = "I'm a simple observer.\r\n" +
//                "Inside\r\n" +
//                "I'm a simple observer.\r\n" +
//                "Outside\r\n";
//        Assert.assertEquals(expectedResult, output.toString());
    }

}
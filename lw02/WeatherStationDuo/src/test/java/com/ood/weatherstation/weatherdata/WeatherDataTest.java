package com.ood.weatherstation.weatherdata;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        WeatherData wdo = new WeatherData();
        WeatherData wdi = new WeatherData();
        NormalObserver normalObserver1 = new NormalObserver(wdi, wdo);
        NormalObserver normalObserver2 = new NormalObserver(wdi, wdo);

        wdi.registerObserver(normalObserver1, 2);
        wdo.registerObserver(normalObserver2, 1);

        wdi.setMeasurements(1.0, 1.0, 1.0);
        wdo.setMeasurements(1.0, 1.0, 1.0);

        String expectedResult = "Normal Inside.\r\n" +
                "Normal Outside.\r\n";
        Assert.assertEquals(expectedResult, output.toString());
    }

    @Test
    public void can_print_statistic_inside_and_outside() {
        WeatherData wdo = new WeatherData();
        WeatherData wdi = new WeatherData();
        SimpleObserver simpleObserver1 = new SimpleObserver(wdi, wdo);
        SimpleObserver simpleObserver2 = new SimpleObserver(wdi, wdo);
        SimpleObserver simpleObserver3 = new SimpleObserver(wdi, wdo);

        wdi.registerObserver(simpleObserver1, 2);
        wdo.registerObserver(simpleObserver2, 1);
        wdi.registerObserver(simpleObserver3, 3);

        wdi.setMeasurements(1.0, 1.0, 1.0);
        wdo.setMeasurements(1.0, 1.0, 1.0);

        String expectedResult = "Simple Inside. I have statistic.\r\n" +
                "Simple Inside. I have statistic.\r\n" +
                "Simple Outside. I have statistic.\r\n";
        Assert.assertEquals(expectedResult, output.toString());
    }

    @Test
    public void throw_exception_if_trying_to_update_unnecessary_observable_type() {
        this.output.reset();
        WeatherData wdi = new WeatherData();
        WeatherData wdo = new WeatherData();
        WeatherData wdError = new WeatherData();
        NormalObserver normalObserver = new NormalObserver(wdError, wdo);
        wdi.registerObserver(normalObserver, 1);
        wdi.setMeasurements(1.0, 1.0, 1.0);
        String out = this.output.toString();
        Assert.assertEquals("Incorrect observable type.\r\n", out);
    }

}
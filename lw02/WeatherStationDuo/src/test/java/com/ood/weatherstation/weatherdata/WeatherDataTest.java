package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import org.junit.*;

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
        WeatherData wdo = new WeatherData(ObservableType.OUT);
        WeatherData wdi = new WeatherData(ObservableType.IN);
        try {
            NormalObserver normalObserverIn = new NormalObserver(ObservableType.IN);
            NormalObserver normalObserverOut = new NormalObserver(ObservableType.OUT);

            wdi.registerObserver(normalObserverIn, 2);
            wdo.registerObserver(normalObserverOut, 1);
        } catch (IncorrectObservableType e) {
            System.out.println(e.getMessage());
        }

        wdi.setMeasurements(1.0, 1.0, 1.0);
        wdo.setMeasurements(1.0, 1.0, 1.0);

        String expectedResult = "Normal Inside.\r\n" +
                "Normal Outside.\r\n";
        Assert.assertEquals(expectedResult, output.toString());
    }

    @Test
    public void can_print_statistic_inside_and_outside() {
        WeatherData wdo = new WeatherData(ObservableType.OUT);
        WeatherData wdi = new WeatherData(ObservableType.IN);
        try {
            SimpleObserver simpleObserverIn = new SimpleObserver(ObservableType.IN);
            SimpleObserver simpleObserverOut = new SimpleObserver(ObservableType.OUT);

            wdi.registerObserver(simpleObserverIn, 2);
            wdo.registerObserver(simpleObserverOut, 1);
        } catch (IncorrectObservableType e) {
            System.out.println(e.getMessage());
        }

        wdi.setMeasurements(1.0, 1.0, 1.0);
        wdo.setMeasurements(1.0, 1.0, 1.0);

        String expectedResult = "Simple Inside. I have statistic.\r\n" +
                "Simple Outside. I have statistic.\r\n";
        Assert.assertEquals(expectedResult, output.toString());
    }

    @Test
    public void throw_exception_if_trying_to_remove_incorrect_observable_type() {
        WeatherData wdo = new WeatherData(ObservableType.OUT);
        try {
            NormalObserver normalObserver = new NormalObserver(ObservableType.ERROR);
            wdo.removeObserver(normalObserver);
            fail();
        } catch (IncorrectObservableType e) {
            Assert.assertEquals("Incorrect observable type.", e.getMessage());
        }
    }

    @Test
    public void throw_exception_if_trying_to_register_incorrect_observable_type() {
        WeatherData wdo = new WeatherData(ObservableType.OUT);
        try {
            NormalObserver normalObserver = new NormalObserver(ObservableType.ERROR);
            wdo.registerObserver(normalObserver, 1);
            fail();
        } catch (IncorrectObservableType e) {
            Assert.assertEquals("Incorrect observable type.", e.getMessage());
        }
    }

}
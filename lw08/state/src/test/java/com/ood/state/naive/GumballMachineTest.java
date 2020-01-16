package com.ood.state.naive;

import com.ood.exception.WrongAmountException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GumballMachineTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;
    private GumballMachine baseMachine;

    @Before
    public void setUp() throws WrongAmountException {
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        baseMachine = new GumballMachine(10);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canCreateWithoutGumBalls() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        assertEquals(State.SOLD_OUT, gumballMachine.getState());
    }

    @Test
    public void canCreateWithoutGumBallsAndThenRefill() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        assertEquals(State.SOLD_OUT, gumballMachine.getState());
        gumballMachine.refill(10);
        assertEquals(State.NO_QUARTER, gumballMachine.getState());
        String expectedResult = "";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canCreateWithGumBalls() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(10);
        assertEquals(State.NO_QUARTER, gumballMachine.getState());
    }

    @Test
    public void canInsertQuarter() {
        baseMachine.insertQuarter();
        assertEquals(State.HAS_QUARTER, baseMachine.getState());
        assertEquals(1, baseMachine.getQuartersController().getQuartersCount());
        String expectedResult = "Inserted a quarter. Quarters count: 1\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canInsertQuarterAndThenGetGumball() {
        baseMachine.insertQuarter();
        baseMachine.turnCrank();
        assertEquals(State.NO_QUARTER, baseMachine.getState());
        assertEquals(0, baseMachine.getQuartersController().getQuartersCount());
        String expectedResult = "Inserted a quarter. Quarters count: 1\r\n" +
                "You turned...\r\n" +
                "You used one quarter. Quarters count: 0\r\n" +
                "A gumball comes rolling out the slot\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canInsertQuarterAndEnjectIt() {
        baseMachine.insertQuarter();
        baseMachine.ejectQuarter();
        assertEquals(State.NO_QUARTER, baseMachine.getState());
        assertEquals(0, baseMachine.getQuartersController().getQuartersCount());
        String expectedResult = "Inserted a quarter. Quarters count: 1\r\n" +
                "1 quarter returned\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canGetCurrentMachineState() {
        baseMachine.insertQuarter();
        String expectedResult = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: 10 gumballs\r\n" +
                "Machine is waiting for turn of crank\r\n";
        assertEquals(expectedResult, baseMachine.toString());
    }

    @Test
    public void canInsertTheSomeQuarters() {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void canInsertTheSomeQuartersAndThenTurnCrank() {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(4, baseMachine.getQuartersController().getQuartersCount());
        baseMachine.turnCrank();
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
        assertEquals(State.HAS_QUARTER, baseMachine.getState());
    }

    @Test
    public void userCantTurnCrankWithoutGumBalls() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        gumballMachine.turnCrank();
        String expectedResult = "You turned but there's no gumballs\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void userCantTwiceCrank() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.setState(State.SOLD);
        gumballMachine.turnCrank();
        String expectedResult = "Turning twice doesn't get you another gumball\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void userCantTurnCrankWhenInMachineNoQuarters() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.turnCrank();
        String expectedResult = "You turned but there's no gumballs";
        String[] outArray = output.toString().split("\r\n");
        assertEquals(expectedResult, outArray[outArray.length - 1]);
    }

    @Test
    public void cantCreateMachineWithNegativeNumbersOfGumBalls() {
        try {
            GumballMachine gumballMachine = new GumballMachine(-1);
            fail();
        } catch (Exception e) {
            assertEquals(WrongAmountException.class, e.getClass());
            assertEquals("Count of gumballs cant be less than zero.", e.getMessage());
        }
    }

    @Test
    public void cantInsertQuarterInSoldOutState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        gumballMachine.insertQuarter();
        String expectedResult = "You can't insert a quarter, the machine is sold out\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canInsertQuarterWhenSells() {
        baseMachine.setState(State.SOLD);
        baseMachine.insertQuarter();
        assertEquals(1, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void cantEjectQuarterInSoldOutState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        gumballMachine.ejectQuarter();
        String expectedResult = "You can't eject, you haven't inserted a quarter yet\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantEjectQuarterInSellsState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.setState(State.SOLD);
        gumballMachine.ejectQuarter();
        String expectedResult = "You can't eject, you haven't inserted a quarter yet\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cabGetQuartersInSoldOutState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        assertEquals(2, gumballMachine.getQuartersController().getQuartersCount());
        gumballMachine.ejectQuarter();
        assertEquals(0, gumballMachine.getQuartersController().getQuartersCount());
    }


    @Test
    public void cabGetQuartersInSellsState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        assertEquals(3, gumballMachine.getQuartersController().getQuartersCount());
        gumballMachine.setState(State.SOLD);
        gumballMachine.ejectQuarter();
        assertEquals(0, gumballMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void ifMachineDoesntHaveQuartersUserCantEnjectQuarter() throws WrongAmountException {
        String expectedResult = "You haven't inserted a quarter\r\n";
        baseMachine.ejectQuarter();
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void ifMachineHasQuartersUserCangetAllQuarters() {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
        baseMachine.ejectQuarter();
        assertEquals(0, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void cantTurnCrankWhenNoQuarters() {
        baseMachine.turnCrank();
        String expectedResult = "You turned but there's no quarter\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantRefillToNegativeNumbersOfGumBalls() {
        try {
            baseMachine.refill(-1);
            fail();
        } catch (Exception e) {
            assertEquals(WrongAmountException.class, e.getClass());
            assertEquals("Count of gumballs cant be less than zero.", e.getMessage());
        }
    }

    @Test
    public void ifRefillToZeroInSoldOutStateStateIsNotChanged() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        gumballMachine.refill(0);
        assertEquals(State.SOLD_OUT, gumballMachine.getState());
    }

    @Test
    public void ifRefillAndMachineHasQuartersMachineStateEqualsHasQuarter() throws WrongAmountException {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
        baseMachine.refill(4);
        assertEquals(State.HAS_QUARTER, baseMachine.getState());
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void cantRefillMachineInSoldState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.setState(State.SOLD);
        gumballMachine.refill(2);
        String expectedResult = "Cant refill machine in SOLD state\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canRefillMachineInNoQuarterStateStateIsNotChanged() throws WrongAmountException {
        assertEquals(State.NO_QUARTER, baseMachine.getState());
        baseMachine.refill(2);
        assertEquals(State.NO_QUARTER, baseMachine.getState());
    }

    @Test
    public void ifRefillMachineToZeroGumBallsInNotQuarterStateThenStateIsSoldOut() throws WrongAmountException {
        assertEquals(State.NO_QUARTER, baseMachine.getState());
        baseMachine.refill(0);
        assertEquals(State.SOLD_OUT, baseMachine.getState());
    }

    @Test
    public void ifRefillMachineToZeroGumBallsInHasQuarterStateThenStateIsSoldOut() throws WrongAmountException {
        baseMachine.insertQuarter();
        assertEquals(State.HAS_QUARTER, baseMachine.getState());
        baseMachine.refill(0);
        assertEquals(State.SOLD_OUT, baseMachine.getState());
    }

    @Test
    public void canConvertMachineToStringWithOneGumBall() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        String expectedResult = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: 1 gumball\r\n" +
                "Machine is waiting for quarter\r\n";
        assertEquals(expectedResult, gumballMachine.toString());
    }

    @Test
    public void cantDispenceInSoldOutState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(0);
        gumballMachine.dispence();
        String expectedResult = "No gumball dispensed.\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantDispenceInNoQuarterState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.dispence();
        String expectedResult = "You need to pay first\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantDispenceInHasQuarterState() throws WrongAmountException {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.insertQuarter();
        gumballMachine.dispence();
        String expectedResult = "No gumball dispensed";
        String[] outArray = output.toString().split("\r\n");
        assertEquals(expectedResult, outArray[outArray.length - 1]);
    }

    @Test
    public void canGetBallsCount() {
        assertEquals(10, baseMachine.getBallsCount());
    }

    @Test
    public void userCantInsertMoreThanFiveQuarters() {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(5, baseMachine.getQuartersController().getQuartersCount());
        baseMachine.insertQuarter();
        String expectedResult = "You can not insert more than 5 quarters";
        String[] outArray = output.toString().split("\r\n");
        assertEquals(expectedResult, outArray[outArray.length - 1]);
    }

}
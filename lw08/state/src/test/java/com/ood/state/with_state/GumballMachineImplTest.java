package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GumballMachineImplTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;
    private GumballMachineImpl baseMachine;

    @Before
    public void setUp() throws WrongAmountException {
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        baseMachine = new GumballMachineImpl(10);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canCreateWithoutGumBalls() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        assertEquals(SoldOutState.class, gumballMachine.getState().getClass());
    }

    @Test
    public void canCreateWithoutGumBallsAndThenRefill() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        assertEquals(SoldOutState.class, gumballMachine.getState().getClass());
        gumballMachine.getState().refill(10);
        assertEquals(NoQuarterState.class, gumballMachine.getState().getClass());
        String expectedResult = "";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canCreateWithGumBalls() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(10);
        assertEquals(NoQuarterState.class, gumballMachine.getState().getClass());
    }

    @Test
    public void canInsertQuarter() {
        baseMachine.insertQuarter();
        assertEquals(HasQuarterState.class, baseMachine.getState().getClass());
        assertEquals(1, baseMachine.getQuartersController().getQuartersCount());
        String expectedResult = "Inserted a quarter. Quarters count: 1\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canInsertQuarterAndThenGetGumball() {
        baseMachine.insertQuarter();
        baseMachine.turnCrank();
        assertEquals(NoQuarterState.class, baseMachine.getState().getClass());
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
        assertEquals(NoQuarterState.class, baseMachine.getState().getClass());
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
        assertEquals(HasQuarterState.class, baseMachine.getState().getClass());
    }

    @Test
    public void userCantTurnCrankWithoutGumBalls() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        gumballMachine.turnCrank();
        String expectedResult = "You turned but there's no gumballs\r\nNo gumball dispensed\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void userCantTwiceCrank() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.setState(new SoldState(gumballMachine));
        gumballMachine.turnCrank();
        String expectedResult = "Turning twice doesn't get you another gumball\r\n" +
                "A gumball comes rolling out the slot\r\n" +
                "Oops, out of gumballs\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void userCantTurnCrankWhenInMachineNoQuarters() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.turnCrank();
        String expectedResult = "You turned but there's no gumballsNo gumball dispensed";
        String[] outArray = output.toString().split("\r\n");
        assertEquals(expectedResult, outArray[outArray.length - 2] + outArray[outArray.length - 1]);
    }

    @Test
    public void cantCreateMachineWithNegativeNumbersOfGumBalls() {
        try {
            GumballMachineImpl gumballMachine = new GumballMachineImpl(-1);
            fail();
        } catch (Exception e) {
            assertEquals(WrongAmountException.class, e.getClass());
            assertEquals("Count of gumballs cant be less than zero.", e.getMessage());
        }
    }

    @Test
    public void cantInsertQuarterInSoldOutState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        gumballMachine.insertQuarter();
        String expectedResult = "You can't insert a quarter, the machine is sold out\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canInsertQuarterWhenSells() {
        baseMachine.setState(new SoldState(baseMachine));
        baseMachine.insertQuarter();
        assertEquals(1, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void cantEjectQuarterInSoldOutState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        gumballMachine.ejectQuarter();
        String expectedResult = "You can't eject, you haven't inserted a quarter yet\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantEjectQuarterInSellsState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.setState(new SoldState(gumballMachine));
        assertEquals(0, gumballMachine.getQuartersController().getQuartersCount());
        gumballMachine.ejectQuarter();
        assertEquals(0, gumballMachine.getQuartersController().getQuartersCount());
        assertEquals("", output.toString());
    }

    @Test
    public void cabGetQuartersInSoldOutState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
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
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        assertEquals(3, gumballMachine.getQuartersController().getQuartersCount());
        gumballMachine.setState(new SoldState(gumballMachine));
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
    public void ifMachineHasQuartersUserCangetAllQuarters() throws WrongAmountException {
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
        String expectedResult = "You turned but there's no quarter\r\nYou need to pay first\r\n";
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
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        gumballMachine.refill(0);
        assertEquals(SoldOutState.class, gumballMachine.getState().getClass());
    }

    @Test
    public void ifRefillAndMachineHasQuartersMachineStateEqualsHasQuarter() throws WrongAmountException {
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        baseMachine.insertQuarter();
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
        baseMachine.refill(4);
        assertEquals(HasQuarterState.class, baseMachine.getState().getClass());
        assertEquals(3, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void cantRefillMachineInSoldState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.setState(new SoldState(gumballMachine));
        gumballMachine.refill(2);
        String expectedResult = "Cant refill machine in SOLD state\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void canRefillMachineInNoQuarterStateStateIsNotChanged() throws WrongAmountException {
        assertEquals(NoQuarterState.class, baseMachine.getState().getClass());
        baseMachine.refill(2);
        assertEquals(NoQuarterState.class, baseMachine.getState().getClass());
    }

    @Test
    public void ifRefillMachineToZeroGumBallsInNotQuarterStateThenStateIsSoldOut() throws WrongAmountException {
        assertEquals(NoQuarterState.class, baseMachine.getState().getClass());
        baseMachine.refill(0);
        assertEquals(SoldOutState.class, baseMachine.getState().getClass());
    }

    @Test
    public void ifRefillMachineToZeroGumBallsInHasQuarterStateThenStateIsSoldOut() throws WrongAmountException {
        baseMachine.insertQuarter();
        assertEquals(HasQuarterState.class, baseMachine.getState().getClass());
        baseMachine.refill(0);
        assertEquals(SoldOutState.class, baseMachine.getState().getClass());
        assertEquals(1, baseMachine.getQuartersController().getQuartersCount());
    }

    @Test
    public void canConvertMachineToStringWithOneGumBall() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        String expectedResult = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: 1 gumball\r\n" +
                "Machine is waiting for quarter\r\n";
        assertEquals(expectedResult, gumballMachine.toString());
    }

    @Test
    public void cantDispenceInSoldOutState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(0);
        gumballMachine.getState().dispence();
        String expectedResult = "No gumball dispensed\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantDispenceInNoQuarterState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.getState().dispence();
        String expectedResult = "You need to pay first\r\n";
        assertEquals(expectedResult, output.toString());
    }

    @Test
    public void cantDispenceInHasQuarterState() throws WrongAmountException {
        GumballMachineImpl gumballMachine = new GumballMachineImpl(1);
        gumballMachine.insertQuarter();
        gumballMachine.getState().dispence();
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

    @Test
    public void cantRefillToNegativeBallsCount() {
        try {
            baseMachine.refill(-1);
        } catch (Exception e) {
            assertEquals(WrongAmountException.class, e.getClass());
        }
    }

}
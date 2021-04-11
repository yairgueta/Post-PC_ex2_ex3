package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsDeletedThenPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsClearedThenPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsDeletedThenIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsClearedThenMinus_then_outputShouldBe0Minus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertMinus();
    assertEquals("0-", calculatorUnderTest.output());
  }


  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(7);

    calculatorUnderTest.deleteLast();
    assertEquals("132+2", calculatorUnderTest.output());

    calculatorUnderTest.deleteLast();
    assertEquals("132+", calculatorUnderTest.output());

    calculatorUnderTest.deleteLast();
    assertEquals("132", calculatorUnderTest.output());

    calculatorUnderTest.deleteLast();
    assertEquals("13", calculatorUnderTest.output());

    calculatorUnderTest.insertMinus();
    calculatorUnderTest.deleteLast();
    assertEquals("13", calculatorUnderTest.output());

    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertPlus();
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());


    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

    firstCalculator.insertDigit(7);
    firstCalculator.insertDigit(6);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(8);
    firstCalculator.insertDigit(5);
    firstCalculator.insertDigit(8);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(8);
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(3);
    firstCalculator.insertDigit(6);

    Serializable state = firstCalculator.saveState();
    secondCalculator.loadState(state);
    assertEquals("76+858+8-36", secondCalculator.output());
  }

  @Test
  public void when_usingNumbersEndingWithZero_should_haveRightAnswer(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(0);
    assertEquals("10", calculatorUnderTest.output());

    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(0);
    assertEquals("10+490", calculatorUnderTest.output());

    calculatorUnderTest.insertEquals();
    assertEquals("500", calculatorUnderTest.output());

    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(0);
    assertEquals("500-100-50+20", calculatorUnderTest.output());

    calculatorUnderTest.insertEquals();
    assertEquals("370", calculatorUnderTest.output());
  }



  @Test
  public void when_subtractSmallerFromBigger_should_beMinus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertEquals();

    assertEquals("-287", calculatorUnderTest.output());
  }

  @Test
  public void when_addSmallToNegative_should_beNegative(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();

    assertEquals("-70", calculatorUnderTest.output());

  }

  @Test
  public void when_deleteLast_should_workWell(){
    //"5+7-13<DeleteLast>25", expected output is "5+17-125"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);

    assertEquals("5+7-125", calculatorUnderTest.output());

  }

  @Test
  public void when_useTwoClears_should_workFine(){
    //"9<Clear>12<Clear>8-7=", expected output is "1"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();

    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void when_useMultiplePluses_should_haveOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);

    assertEquals("9+72+1", calculatorUnderTest.output());
  }

  @Test
  public void when_useMultipleMinuses_should_haveOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);

    assertEquals("70-2-2", calculatorUnderTest.output());
  }

  @Test
  public void when_useMultipleOperators_should_haveOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();

    assertEquals("70+2-1+2-", calculatorUnderTest.output());
  }


  @Test
  public void when_useEqualsManyTimes_should_calculateAllOfThem(){
    // "8-7=+4=-1=", expected output is "4"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();

    assertEquals("4", calculatorUnderTest.output());
  }

  @Test
  public void when_useEqualsWithNegatives_should_workFine(){
    // "999-888-222=-333", expected output is "-111-333"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);

    assertEquals("-111-333", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondUsedCalculator(){
    // with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

    firstCalculator.insertDigit(9);
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(1);
    firstCalculator.insertDigit(2);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(8);
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(7);

    secondCalculator.insertDigit(9);
    secondCalculator.insertDigit(7);
    secondCalculator.insertDigit(3);
    secondCalculator.insertPlus();

    Serializable state = firstCalculator.saveState();
    secondCalculator.loadState(state);

    assertEquals("9-12+8-7", secondCalculator.output());
  }

  @Test
  public void when_cycleStateBetweenThreeCalculators_should_workFine(){
    //"9<Clear>12<Clear>8-7=", expected output is "1"
    Serializable state;
    SimpleCalculatorImpl firstCalc = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalc = new SimpleCalculatorImpl();
    SimpleCalculatorImpl thirdCalc = new SimpleCalculatorImpl();

    firstCalc.insertDigit(9);
    firstCalc.insertDigit(7);
    firstCalc.insertMinus();

    state = firstCalc.saveState();
    secondCalc.loadState(state);
    secondCalc.insertDigit(7);
    secondCalc.insertPlus();
    secondCalc.insertDigit(1);

    state = secondCalc.saveState();
    thirdCalc.loadState(state);
    thirdCalc.insertDigit(0);
    thirdCalc.insertMinus();
    thirdCalc.insertDigit(5);

    state = thirdCalc.saveState();
    firstCalc.loadState(state);
    firstCalc.insertDigit(0);

    assertEquals("97-7+10-50", firstCalc.output());
  }
}
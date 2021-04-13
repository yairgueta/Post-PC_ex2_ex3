package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private View button0;
  private View button1;
  private View button2;
  private View button3;
  private View button4;
  private View button5;
  private View button6;
  private View button7;
  private View button8;
  private View button9;
  private View buttonBackspace;
  private View buttonClear;
  private View buttonPlus;
  private View buttonMinus;
  private View buttonEquals;
  private TextView textViewOutput;

  /** initialize main activity with a real calculator */
  @Before
  public void setup(){
    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityController.create().start().resume();
    button0 = activityUnderTest.findViewById(R.id.button0);
    button1 = activityUnderTest.findViewById(R.id.button1);
    button2 = activityUnderTest.findViewById(R.id.button2);
    button3 = activityUnderTest.findViewById(R.id.button3);
    button4 = activityUnderTest.findViewById(R.id.button4);
    button5 = activityUnderTest.findViewById(R.id.button5);
    button6 = activityUnderTest.findViewById(R.id.button6);
    button7 = activityUnderTest.findViewById(R.id.button7);
    button8 = activityUnderTest.findViewById(R.id.button8);
    button9 = activityUnderTest.findViewById(R.id.button9);
    buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
    buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
    buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
    textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
  }

  @Test
  public void flowTest1(){
    // run clicks on "13+5"
    for (View button: Arrays.asList(
      button1, button3, buttonPlus, button5
    )) {
      button.performClick();
    }

    assertEquals("13+5", textViewOutput.getText().toString());
  }


  @Test
  public void flowTest2(){
    // run clicks on "7+5<backspace>4="
    for (View button: Arrays.asList(
      button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("11", textViewOutput.getText().toString());
  }

  @Test
  public void testAllDigitsInput(){
    // 1234567890
    for (View b : Arrays.asList(button1, button2, button3, button4, button5, button6, button7,button8,button9,button0)){
      b.performClick();
    }

    assertEquals("1234567890", textViewOutput.getText().toString());
  }

  @Test
  public void testMinus(){
    for (View b : Arrays.asList(button5, button3, buttonMinus, button9))
      b.performClick();

    assertEquals("53-9", textViewOutput.getText().toString());
  }

  @Test
  public void testMinusCalculation(){
    for (View b : Arrays.asList(button3, button8, buttonMinus, button5, buttonEquals))
      b.performClick();

    assertEquals("33", textViewOutput.getText().toString());
  }

  @Test
  public void testMinusPlus(){
    for (View b : Arrays.asList(
            button5, button3, button4, buttonPlus, button7, button0, buttonMinus ,button2, buttonPlus, button2, buttonPlus, button3
    ))
      b.performClick();

    assertEquals("534+70-2+2+3", textViewOutput.getText().toString());
  }

  @Test
  public void testMinusPlusCalculation(){
    for (View b : Arrays.asList(
            button5, button3, buttonMinus, button3, button3, buttonPlus, button2, buttonEquals,
            buttonPlus, button3, buttonMinus, button7, buttonPlus, button2,buttonEquals
    ))
      b.performClick();

    assertEquals("20", textViewOutput.getText().toString());
  }

  @Test
  public void testBackspaceZero(){
    for (View b : Arrays.asList(button2, button3, buttonBackspace, buttonBackspace))
      b.performClick();

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void testManyBackspaceZero(){
    for (View b : Arrays.asList(button3, buttonBackspace, buttonBackspace,buttonBackspace,buttonBackspace))
      b.performClick();

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void testFirstBackspaceZero(){
    buttonBackspace.performClick();

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void testManyPluses(){
    for (View b : Arrays.asList(button8, buttonPlus, buttonPlus, button7, buttonPlus, buttonPlus, buttonPlus))
      b.performClick();

    assertEquals("8+7+", textViewOutput.getText().toString());
  }

  @Test
  public void testManyMinuses(){
    for (View b : Arrays.asList(button3, buttonMinus, buttonMinus, button4, button4, buttonMinus, buttonMinus, buttonMinus))
      b.performClick();

    assertEquals("3-44-", textViewOutput.getText().toString());
  }

  @Test
  public void testManyPlusesAndMinuses(){
    for (View b : Arrays.asList(
            button6, buttonPlus, buttonMinus, button7, buttonMinus, buttonPlus, button3,
            buttonMinus, buttonMinus, buttonPlus, buttonMinus, buttonPlus, button1,
            buttonPlus, buttonMinus, buttonPlus, buttonPlus, buttonMinus, button1
            ))
      b.performClick();

    assertEquals("6+7-3-1+1", textViewOutput.getText().toString());
  }

  @Test
  public void testClear(){
    for (View b : Arrays.asList(
            button2, button5, buttonMinus, button2, buttonEquals, buttonPlus, button6, buttonClear
    ))
      b.performClick();

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void testManyClears(){
    for (View b : Arrays.asList(
            button2, button5, buttonMinus, button2, buttonEquals, buttonPlus, button6, buttonClear,
            button3, buttonMinus, button2, buttonPlus, buttonClear,
            button2, button6
    ))
      b.performClick();

    assertEquals("26", textViewOutput.getText().toString());
  }

  @Test
  public void testOverflow(){
    int overflowFactor = 70;
    StringBuilder expected = new StringBuilder();
    for (int i = 0 ; i < overflowFactor ; i++){
      button9.performClick();
      expected.append('9');
    }
    buttonPlus.performClick();
    expected.append('+');
    for (int i = 0 ; i < overflowFactor ; i++){
      button7.performClick();
      expected.append('7');
    }

    assertEquals(expected.toString(), textViewOutput.getText().toString());
  }

  @Test
  public void testOverflowCalculations(){
    int overflowFactor = 70;
    StringBuilder expected = new StringBuilder();
    for (int i = 0 ; i < overflowFactor ; i++){
      button6.performClick();
    }
    buttonPlus.performClick();
    for (int i = 0 ; i < overflowFactor ; i++){
      button3.performClick();
      expected.append('9');
    }
    buttonEquals.performClick();

    assertEquals(expected.toString(), textViewOutput.getText().toString());
  }

  @Test
  public void testSaveStateAfterActivityDestroyed(){

    for (View b : Arrays.asList(
            button4, button5, buttonMinus, button9, button2, button4, buttonPlus
    ))
      b.performClick();
    activityUnderTest.recreate();
    activityUnderTest = activityController.get();
    textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    assertEquals("45-924+", textViewOutput.getText().toString());

  }
}

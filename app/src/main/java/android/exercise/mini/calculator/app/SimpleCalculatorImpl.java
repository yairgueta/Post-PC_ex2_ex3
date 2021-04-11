package android.exercise.mini.calculator.app;

import androidx.core.app.CoreComponentFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleCalculatorImpl implements SimpleCalculator {
  private static final int PLUS = 10;
  private static final int MINUS = 11;

  private int activeNumber;
  private List<Integer> previousInputs = new ArrayList<>();

  // determines whether the last input was an operator or a digit.
  private boolean isLastInputOperator = false;

  @Override
  public String output() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0 ; i < previousInputs.size(); i++){
      if (i%2 == 0){
        sb.append(previousInputs.get(i));
      }else {
        int operator = previousInputs.get(i);
        switch (operator) {
          case PLUS:
            sb.append('+');
            break;
          case MINUS:
            sb.append('-');
            break;
        }
      }
    }
    if (!isLastInputOperator) sb.append(activeNumber);
    return sb.toString();
  }

  @Override
  public void insertDigit(int digit) {
    if (digit < 0 || digit > 9) throw new IllegalArgumentException();
    activeNumber *= 10;
    activeNumber += digit;
    isLastInputOperator = false;
  }



  @Override
  public void insertPlus() {
    if (isLastInputOperator) return;
    previousInputs.add(activeNumber);
    previousInputs.add(PLUS);
    activeNumber = 0;
    isLastInputOperator = true;
  }

  @Override
  public void insertMinus() {
    if (isLastInputOperator) return;
    previousInputs.add(activeNumber);
    previousInputs.add(MINUS);
    activeNumber = 0;
    isLastInputOperator = true;
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    previousInputs.add(activeNumber);
    activeNumber = previousInputs.get(0);
    for (int i = 1 ; i < previousInputs.size(); i+=2){
      int operator = previousInputs.get(i);
      switch (operator){
        case PLUS:
          activeNumber += previousInputs.get(i+1);
          break;
        case MINUS:
          activeNumber -= previousInputs.get(i+1);
          break;
      }
    }
    isLastInputOperator = false;
    previousInputs.clear();
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here

    if (isLastInputOperator){
      activeNumber = previousInputs.get(previousInputs.size()-2);
      previousInputs.remove(previousInputs.size()-1);
      previousInputs.remove(previousInputs.size()-1);
      isLastInputOperator = false;
    }else if (activeNumber == 0){
      if (!previousInputs.isEmpty()){
        isLastInputOperator = true;
        previousInputs.remove(previousInputs.size()-1);
      }
    }else{
      activeNumber = activeNumber / 10;
      isLastInputOperator = activeNumber == 0 && previousInputs.size() > 0;
    }

  }

  @Override
  public void clear() {
    isLastInputOperator = false;
    previousInputs.clear();
    activeNumber = 0;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.activeNumber = this.activeNumber;
    state.isLastInputOperator = this.isLastInputOperator;
    state.previousInputs = new ArrayList<>(this.previousInputs);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    this.activeNumber = casted.activeNumber;
    this.previousInputs = casted.previousInputs;
    this.isLastInputOperator = casted.isLastInputOperator;
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    int activeNumber;
    List<Integer> previousInputs;
    boolean isLastInputOperator;
  }
}

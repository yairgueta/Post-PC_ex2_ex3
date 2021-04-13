package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;

    private View[] numbersViews;
    private View plusView, minusView, equalsView, clearView, delView;
    private TextView outputView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }
        numbersViews = new TextView[]{
                findViewById(R.id.button0),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9),
        };
        plusView = findViewById(R.id.buttonPlus);
        minusView = findViewById(R.id.buttonMinus);
        equalsView = findViewById(R.id.buttonEquals);
        clearView = findViewById(R.id.buttonClear);
        delView = findViewById(R.id.buttonBackSpace);
        outputView = findViewById(R.id.textViewCalculatorOutput);

        numbersViews[0].setOnClickListener(new IntButtonOnClick(0));
        numbersViews[1].setOnClickListener(new IntButtonOnClick(1));
        numbersViews[2].setOnClickListener(new IntButtonOnClick(2));
        numbersViews[3].setOnClickListener(new IntButtonOnClick(3));
        numbersViews[4].setOnClickListener(new IntButtonOnClick(4));
        numbersViews[5].setOnClickListener(new IntButtonOnClick(5));
        numbersViews[6].setOnClickListener(new IntButtonOnClick(6));
        numbersViews[7].setOnClickListener(new IntButtonOnClick(7));
        numbersViews[8].setOnClickListener(new IntButtonOnClick(8));
        numbersViews[9].setOnClickListener(new IntButtonOnClick(9));

        plusView.setOnClickListener(v -> {
            calculator.insertPlus();
            updateToCalculatorOutput();
        });

        minusView.setOnClickListener(v -> {
            calculator.insertMinus();
            updateToCalculatorOutput();
        });

        equalsView.setOnClickListener(v -> {
            calculator.insertEquals();
            updateToCalculatorOutput();
        });

        clearView.setOnClickListener(v -> {
            calculator.clear();
            updateToCalculatorOutput();
        });

        delView.setOnClickListener(v -> {
            calculator.deleteLast();
            updateToCalculatorOutput();
        });

        updateToCalculatorOutput();
    }

    private class IntButtonOnClick implements View.OnClickListener{
        private final int _i;
        public IntButtonOnClick(int i){
            _i = i;
        }
        @Override
        public void onClick(View v) {
            calculator.insertDigit(_i);
            updateToCalculatorOutput();
        }
    }

    private void updateToCalculatorOutput(){
      outputView.setText(calculator.output());
    }

    private static final String STATE_KEY = "CALCULATOR KEY STATE";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_KEY, calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator.loadState(savedInstanceState.getSerializable(STATE_KEY));
        updateToCalculatorOutput();
    }
}
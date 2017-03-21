package sw2017.at;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        buttonEqual = ( Button ) findViewById( R.id.buttonEqual );
        buttonClear = ( Button ) findViewById( R.id.buttonClear );
        buttonAdd = ( Button ) findViewById( R.id.buttonAdd );
        buttonDiv = ( Button ) findViewById( R.id.buttonDiv );
        buttonSub = ( Button ) findViewById( R.id.buttonSub );
        buttonMul = ( Button ) findViewById( R.id.buttonMul );
        button1 = ( Button ) findViewById( R.id.button1 );
        button2 = ( Button ) findViewById( R.id.button2 );
        button3 = ( Button ) findViewById( R.id.button3 );
        button4 = ( Button ) findViewById( R.id.button4 );
        button5 = ( Button ) findViewById( R.id.button5 );
        button6 = ( Button ) findViewById( R.id.button6 );
        button7 = ( Button ) findViewById( R.id.button7 );
        button8 = ( Button ) findViewById( R.id.button8 );
        button9 = ( Button ) findViewById( R.id.button9 );
        button0 = ( Button ) findViewById( R.id.button0 );

        buttonEqual.setOnClickListener ( this );
        buttonAdd.setOnClickListener ( this );
        buttonClear.setOnClickListener ( this );
        buttonDiv.setOnClickListener ( this );
        buttonSub.setOnClickListener ( this );
        buttonMul.setOnClickListener ( this );
        setUpNumberButtonListener ();

        numberView = (TextView) findViewById ( R.id.textView);
    }

    private TextView numberView;
    private Button buttonEqual;
    private Button buttonClear;
    private Button buttonAdd;
    private Button buttonDiv;
    private Button buttonSub;
    private Button buttonMul;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private ArrayList<Button> numberButtons;
    private int firstNumber;
    private State state;

    @Override
    public void onClick ( View v ) {
        Button clickedButton = ( Button ) v;
        switch ( clickedButton . getId ()) {
            case R.id.buttonAdd:
                clearNumberView ();
                state = State.ADD;
                break;
            case R.id.buttonSub:
                clearNumberView ();
                state = State.SUB;
                break;
            case R.id.buttonMul:
                clearNumberView ();
                state = State . MUL;
                break;
            case R.id.buttonDiv:
                clearNumberView ();
                state = State.DIV;
                break;
            case R . id . buttonEqual:
                calculateResult ();
                state = State.INIT;
                break;
            case R.id.buttonClear:
                clearTextView ();
                break;
            default:
                String recentNumber = numberView . getText (). toString ();
                if ( state == State.INIT ) {
                    recentNumber = "";
                    state = State.NUM;
                }
                recentNumber += clickedButton . getText (). toString ();
                numberView . setText ( recentNumber );
        }
    }

    public void setUpNumberButtonListener () {
        numberButtons = new ArrayList<>();
        for ( int i = 0 ; i <= 9 ; i ++) {
            String buttonName = "button" + i;
            int id = getResources().getIdentifier( buttonName , "id" ,
                    R.class.getPackage().getName());
            Button button = ( Button ) findViewById( id );
            button.setOnClickListener(this);
            numberButtons.add(button);
        }
    }

    private void clearTextView () {
        numberView.setText ("0");
        firstNumber = 0;
        state = State.INIT;
    }

    private void clearNumberView () {
        String tempString = numberView.getText().toString ();
        if (! tempString.equals ( "" )){
            firstNumber = Integer.valueOf(tempString);
        }
        numberView.setText ( "" );
    }

    public enum State {
        ADD , SUB , MUL , DIV , INIT , NUM
    }

    private void calculateResult () {
        int secondNumber = 0;
        String tempString = numberView.getText ().toString ();
        if (! tempString . equals ( "" )){
            secondNumber = Integer.valueOf ( tempString );
        }
        int result;
        switch ( state ){
            case ADD:
                result = Calculations.doAddition ( firstNumber , secondNumber );
                break;
            case SUB:
                result = Calculations.doSubtraction ( firstNumber , secondNumber );
                break;
            case MUL:
                result = Calculations.doMultiplication ( firstNumber , secondNumber );
                break;
            case DIV:
                result = Calculations.doDivision ( firstNumber , secondNumber );
                break;
            default:
                result = secondNumber;
        }
        numberView.setText ( Integer.toString ( result ));
    }
}


class Calculations {
    private Calculations () {
    }
    static int doAddition ( int firstNumber , int secondNumber ) {
        return firstNumber + secondNumber;
    }
    static int doSubtraction ( int firstNumber , int secondNumber ) {
        return firstNumber - secondNumber;
    }
    static int doMultiplication ( int firstNumber , int secondNumber ) {
        return firstNumber * secondNumber;
    }
    static int doDivision ( int firstNumber , int secondNumber ) {
        if ( secondNumber == 0 ) {
            return 0;
        }
        return firstNumber / secondNumber;
    }

}
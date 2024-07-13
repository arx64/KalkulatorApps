package com.kelompok4.kalkulator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);





    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
//            solutionTv.setText(resultTv.getText());
            solutionTv.setText(dataToCalculate);
            solutionTv.setTextSize(50);
            resultTv.setTextSize(70);
            return;
        }
        if(buttonText.equals("C") && !dataToCalculate.isEmpty()){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            Log.d("[DEBUG]", String.valueOf(dataToCalculate.length()));
            Log.d("[DEBUG]", dataToCalculate);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        if (dataToCalculate.length() >= 11) {
            solutionTv.setTextSize(30);
            resultTv.setTextSize(40);
        } else if (dataToCalculate.length() >= 21) {
            solutionTv.setTextSize(20);
            resultTv.setTextSize(30);
        } else if (dataToCalculate.length() >= 31) {
            solutionTv.setTextSize(10);
            resultTv.setTextSize(20);
        } else {
            solutionTv.setTextSize(50);
            resultTv.setTextSize(40);
        }

        if (dataToCalculate.isEmpty()) {
//            solutionTv.setText("0");
            Log.d("[DEBUG]", String.valueOf(dataToCalculate.isEmpty()));
//            resultTv.setText("0");
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        } else {
            solutionTv.setText(dataToCalculate);
        }
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText("= " + finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}
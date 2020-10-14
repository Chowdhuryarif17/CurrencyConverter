package com.example.chowdhuryarif.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] currencyNames;
    private Spinner fromSpinner, toSpinner;
    private Button clearButton, swapButton, converterButton;
    private TextView resultTextView;
    EditText amountEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdS(); //all find views here

        String admin = "admin Branch";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textViewSampleId, currencyNames);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        clearButton.setOnClickListener(this);
        swapButton.setOnClickListener(this);
        converterButton.setOnClickListener(this);
    }

    void findViewByIdS(){

        currencyNames = getResources().getStringArray(R.array.currency_names);

        fromSpinner = findViewById(R.id.fromSpinnerId);
        toSpinner = findViewById(R.id.toSpinnerId);

        amountEditText = findViewById(R.id.amountEditTextId);

        clearButton = findViewById(R.id.clearId);
        swapButton = findViewById(R.id.swapId);
        converterButton = findViewById(R.id.converterId);

        resultTextView = findViewById(R.id.resultTextViewId);
    }

    @Override
    public void onClick(View view) {
        try {

            String amount = amountEditText.getText().toString();
            double num = Double.parseDouble(amount); // convert into double

            if (view.getId() == R.id.clearId) {

                resultTextView.setText(" ");
            }
            if (view.getId() == R.id.swapId) {

                int temp = fromSpinner.getSelectedItemPosition();
                fromSpinner.setSelection(toSpinner.getSelectedItemPosition());
                toSpinner.setSelection(temp);
                convert(view, num);
                //resultTextView.setText("error");

            }
            if (view.getId() == R.id.converterId) {
                convert(view, num);
            }

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "please enter any amount", Toast.LENGTH_SHORT).show();
        }
    }

    void convert(View view, double num) {
        if (view.getId() == R.id.converterId || view.getId() == R.id.swapId) {

            String fromValue = fromSpinner.getSelectedItem().toString();
            String resultValue = toSpinner.getSelectedItem().toString();

            double temp = num;
            num = convertToDoller(fromValue, num);


            if(fromValue == resultValue){
                num = temp;
                resultTextView.setText(num + " " + resultValue);
            }
            else if (resultValue.equals("BDT")) {
                num = num * 84.18;
                resultTextView.setText(num + " BDT");
            } else if (resultValue.equals("EUR")) {
                num = num * 0.69881;
                resultTextView.setText(num + " EUR");
            } else if (resultValue.equals("GBP")) {
                num = num * 0.61095;
                resultTextView.setText(num + " GBP");
            } else if (resultValue.equals("AUD")) {
                num = num * 0.93188;
                resultTextView.setText(num + " AUD");
            } else if (resultValue.equals("CAD")) {
                num = num * 0.96680;
                resultTextView.setText(num + " CAD");
            } else if (resultValue.equals("INR")) {
                num = num * 68.62;
                resultTextView.setText(num + " INR");
            } else if (resultValue.equals("JPY")) {
                num = num * 80.55;
                resultTextView.setText(num + " JPY");
            } else {
                resultTextView.setText(num + " USD");
            }

        }
    }

    double convertToDoller(String currency, double num) {

        if (currency.equals("BDT")) {
            num = num * 0.012;
            return num;
        } else if (currency.equals("EUR")) {
            num = num * 1.17;
            return num;
        } else if (currency.equals("GBP")) {
            num = num * 1.32;
            return num;
        } else if (currency.equals("AUD")) {
            num = num * 0.74;
            return num;
        } else if (currency.equals("CAD")) {
            num = num * 0.77;
            return num;
        } else if (currency.equals("INR")) {
            num = num * 0.015;
            return num;
        } else if (currency.equals("JPY")) {
            num = num * 0.0090;
            return num;
        } else {
            return num;
        }

    }
}

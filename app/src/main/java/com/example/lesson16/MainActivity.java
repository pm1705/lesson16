package com.example.lesson16;

/** main screen where you choose the settings of the sequence
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton tb1;
    EditText et1, et2;

    /** onCreate
     * connects all view elements to their java variabels
     * sets deafult textview values and sets list.
     * @param savedInstanceState - ...
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb1 = (ToggleButton) findViewById(R.id.tb1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
    }

    /** when pressed will gather information based on the given settings and sends them to the next activity
     * @param view
     */

    public void send(View view) {
        String num = et1.getText().toString();
        String differ = et2.getText().toString();
        if (num.matches("") || differ.matches("") || num.equals("-") || differ.equals("-") || num.equals(".") || differ.equals(".")){
            Toast.makeText(this, "please fill every input field with a valid entry", Toast.LENGTH_LONG).show();
        }
        else {
            int mode;
            if (tb1.isChecked()) {
                mode = 1;
            } else {
                mode = 0;
            }

            double diff = Double.parseDouble(differ);
            double first = Double.parseDouble(num);

            if (mode == 1 && diff == 0 || mode == 1 && first == 0) {
                Toast.makeText(this, "in a geometric sequence the diffrence / first item cant be 0", Toast.LENGTH_LONG).show();
            } else {
                Intent si = new Intent(this, results.class);
                si.putExtra("mode", mode);
                si.putExtra("first", num);
                si.putExtra("diff", differ);
                startActivity(si);
            }
        }
    }
}
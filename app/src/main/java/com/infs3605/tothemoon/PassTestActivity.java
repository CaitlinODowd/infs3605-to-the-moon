package com.infs3605.tothemoon;

//This will be coded to show all errors with the password at once, instead of one at a time,
//which the user might find annoying

//CRITERIA FOR PASSWORD STRENGTH
// At least 8 characters
// At least one upper and lower case character
// At least one number
// At least one special character

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassTestActivity extends AppCompatActivity {
    Button bCheck;
    EditText etPassword;
    TextView tvOutcome, tvWeak, tvStrong, tvMedium, tvStrength, tvPassTestTitle, tvPassTestQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);

        //Disclaimer pop-up alert
        AlertDialog.Builder disclaimer = new AlertDialog.Builder(PassTestActivity.this);
        disclaimer.setCancelable(true);
        disclaimer.setTitle("Password Strength Tester Disclaimer");
        disclaimer.setMessage("All data and passwords entered into the tester will not be saved or stored in the system. \n" +
                        "Please note that you shouldn't give out your password details to anyone or websites unless trusted.");
        //set OK button
        disclaimer.setPositiveButton("I understand", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        disclaimer.show();

        //initialise UI
        tvPassTestTitle = findViewById(R.id.tvPassTestTitle);
        tvPassTestQuestion = findViewById(R.id.tvPassTestQuestion);
        tvWeak = findViewById(R.id.tvWeak);
        tvMedium = findViewById(R.id.tvMedium);
        tvStrong = findViewById(R.id.tvStrong);
        tvStrength = findViewById(R.id.tvStrength);
        bCheck = findViewById(R.id.bCheck);
        etPassword = findViewById(R.id.etPassword);
        tvOutcome = findViewById(R.id.tvOutcome);

        //check password when button is clicked
        bCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWeak.setTextColor(Color.parseColor("#767676"));
                tvMedium.setTextColor(Color.parseColor("#767676"));
                tvStrong.setTextColor(Color.parseColor("#767676"));
                String password = etPassword.getText().toString();
                determineOutcome(password);
            }
        });
    }

    public void determineOutcome (String password) {
        int falseCount = 0;
        String outcome = "Your password needs:\n";

        //convert to char array
        char[] passArray = password.toCharArray();

        //check outcomes of all tests
        if (!checkLength(passArray)) {
            outcome += "- To be at least 8 characters long\n";
            falseCount += 1;
        }

        if (!checkUpperCase(passArray)) {
            outcome += "- To contain at least one capital letter\n";
            falseCount += 1;
        }

        if (!checkLowerCase(passArray)) {
            outcome += "- To contain at least one lower case letter\n";
            falseCount += 1;
        }

        if (!checkNumber(passArray)) {
            outcome += "- To contain at least one number\n";
            falseCount += 1;
        }

        if (!checkSymbol(passArray)) {
            outcome += "- To contain at least one special character\n";
            falseCount += 1;
        }

        //intialise strength bar
        if(falseCount == 4 || falseCount == 5){
            tvStrength.setText("VERY WEAK");
            tvStrength.setTextColor(Color.RED);
            tvWeak.setTextColor(Color.RED);
        }
        else if (falseCount == 1 || falseCount ==2 || falseCount ==3){
            tvStrength.setText("MEDIUM");
            tvStrength.setTextColor(Color.parseColor("#ffa500"));
            tvWeak.setTextColor(Color.RED);
            tvMedium.setTextColor(Color.parseColor("#ffa500"));
        }
        else if(falseCount == 0) {
            outcome = "No errors with password";
            tvStrength.setText("VERY STRONG");
            tvStrength.setTextColor(Color.GREEN);
            tvWeak.setTextColor(Color.RED);
            tvMedium.setTextColor(Color.parseColor("#ffa500"));
            tvStrong.setTextColor(Color.GREEN);
        }

        tvOutcome.setText(outcome);

    }
    public boolean checkLength (char[] passArray) {
        //if password is not long enough
        if (passArray.length > 7) {
            return true;
        }
        return false;
    }

    public boolean checkUpperCase (char[] passArray) {
        for (char passChar : passArray) {
            //if password does not have capital letter
            if (passChar > 64 && passChar < 91) {
                return true;
            } else {

            }
        }
        return false;
    }

    public boolean checkLowerCase (char[] passArray) {
        for (char passChar : passArray) {
            //if password does not have lower letter
            if (passChar > 96 && passChar < 123) {
                return true;
            } else {

            }
        }
        return false;
    }

    public boolean checkNumber (char[] passArray) {
        for (char passChar : passArray) {
            //if password does not have number
            if (passChar > 47 && passChar < 58) {
                return true;
            } else {

            }
        }
        return false;
    }

    public boolean checkSymbol (char[] passArray) {
        for (char passChar : passArray) {
            //if password does not have symbol
            if (passChar > 32 && passChar < 48) {
                return true;
            } else if (passChar > 57 && passChar < 65) {
                return true;
            } else if (passChar > 90 && passChar < 97) {
                return true;
            } else if (passChar > 122 && passChar < 127) {
                return true;
            }
        }
        return false;
    }

}

package com.infs3605.scrumlabs;

//This will be coded to show all errors with the password at once, instead of one at a time,
//which the user might find annoying

//CRITERIA FOR PASSWORD STRENGTH
// At least 8 characters
// At least one upper and lower case character
// At least one number
// At least one special character

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassTestActivity extends AppCompatActivity {
    Button bCheck;
    EditText etPassword;
    TextView tvOutcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);

        //initialise UI
        //bCheck = findViewById(R.id.bCheck);
        //etPassword = findViewById(R.id.etPassword);
        //tvOutcome = findViewById(R.id.tvOutcome);

        //check password when button is clicked
        bCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            outcome += "- To be at least 8 characters long";
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

        if(falseCount == 0) {
            outcome = "No errors with password";
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

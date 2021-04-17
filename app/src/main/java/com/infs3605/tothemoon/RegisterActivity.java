package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button bRegister;
    EditText etRegisterEmail, etRegisterPassword, etRegisterConfirmPassword, etdRegisterDate, etRegisterFirstName, etRegisterLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = findViewById(R.id.bRegister);
        etRegisterFirstName = findViewById(R.id.etRegisterFirstName);
        etRegisterLastName = findViewById(R.id.etRegisterLastName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        etdRegisterDate = findViewById(R.id.etdRegisterDate);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(etRegisterEmail.getText().toString(), etRegisterPassword.getText().toString(), etRegisterConfirmPassword.getText().toString(), etdRegisterDate.getText().toString(), etRegisterFirstName.getText().toString(), etRegisterLastName.getText().toString());
            }
        });
    }

    private void launchTutorialActivity() {
        Intent tutorialIntent = new Intent(this, TutorialActivity.class);
        startActivity(tutorialIntent);
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONObject response, String password);
    }

    VolleyResponseListener listener = new VolleyResponseListener() {
        @Override
        public void onError(String message) {
            // do something...
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onResponse(JSONObject response, String password) {
            // do something...
            if(response.has("token")) {
                try {
                    LoginUserDBTask loginUserDBTask = new LoginUserDBTask();
                    loginUserDBTask.setDatabase(getApplicationContext());

                    User newUser = new User();
                    newUser.setEmail(response.getString("email"));
                    newUser.setFirstName(response.getString("firstName"));
                    newUser.setLastName(response.getString("lastName"));
                    newUser.setToken(response.getString("token"));
                    newUser.setPasswordHash(password);

                    Boolean result = loginUserDBTask.execute(newUser).get();

                    if(result) {
                        Toast.makeText(getBaseContext(), "Successfully registered " + newUser.getFirstName() + "!", Toast.LENGTH_LONG).show();

                        launchTutorialActivity();
                    } else {
                        Toast.makeText(getBaseContext(), "Failed to register " + newUser.getFirstName() + "!", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else if(response.has("status")) {
                try {
                    Toast.makeText(getBaseContext(), response.getString("error"), Toast.LENGTH_LONG).show();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void registerUser(String email, String password, String confirmPassword, String DOB, String firstName, String lastName) {
        String postUrl = getString(R.string.eSeniorsRegisterUrl);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
            postData.put("confirmPassword", confirmPassword);
            postData.put("DOB", DOB);
            postData.put("firstName", firstName);
            postData.put("lastName", lastName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                listener.onResponse(response, password);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                listener.onError(error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
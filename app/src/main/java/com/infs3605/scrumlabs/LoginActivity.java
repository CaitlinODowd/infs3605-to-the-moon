package com.infs3605.scrumlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button bLogin, bLoginRegister;
    EditText etLoginEmail, etLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etRegisterEmail);
        etLoginPassword = findViewById(R.id.etRegisterPassword);
        bLogin = findViewById(R.id.bRegister);
        bLoginRegister = findViewById(R.id.bLoginRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(etLoginEmail.getText().toString(), etLoginPassword.getText().toString());
            }
        });

        bLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegisterActivity();
            }
        });
    }

    private void loginUser(String email, String password) {
        String postUrl = "https://eseniors.pythonanywhere.com/auth/device/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            // TODO
            // Change post json value from 'username' to 'email'
            postData.put("username", email);
            postData.put("password", password);

            // TODO
            // Change returned json value from 'username' to 'email'
            Log.d(TAG, "loginUser: POST email = " + postData.getString("username"));
            Log.d(TAG, "loginUser: POST password = " + postData.getString("password"));

            launchHomeActivity();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d(TAG, error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void launchHomeActivity() {
        Intent mainIntent = new Intent(this, HomeActivity.class);
        startActivity(mainIntent);
    }

    private void launchRegisterActivity() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
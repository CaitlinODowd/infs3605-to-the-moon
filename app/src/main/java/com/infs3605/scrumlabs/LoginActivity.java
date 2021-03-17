package com.infs3605.scrumlabs;

import androidx.appcompat.app.AppCompatActivity;

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
import com.infs3605.scrumlabs.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button bLogin, bRegister;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        bRegister = findViewById(R.id.bRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void loginUser(String username, String password) {
        String postUrl = "https://eseniors.pythonanywhere.com/auth/device/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("password", password);

            Log.d(TAG, "loginUser: POST username = " + postData.getString("username"));
            Log.d(TAG, "loginUser: POST password = " + postData.getString("password"));

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
}
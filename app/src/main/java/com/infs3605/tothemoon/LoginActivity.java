package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button bLogin, bLoginRegister;
    EditText etLoginEmail, etLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LogoutCurrentUserTask logoutTask = new LogoutCurrentUserTask();
        logoutTask.setDatabase(getApplicationContext());
        logoutTask.execute();

        etLoginEmail = findViewById(R.id.etRegisterEmail);
        etLoginPassword = findViewById(R.id.etRegisterPassword);
        bLogin = findViewById(R.id.bRegister);
        bLoginRegister = findViewById(R.id.bLoginRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserOnline(etLoginEmail.getText().toString(), etLoginPassword.getText().toString());
            }
        });

        bLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegisterActivity();
            }
        });
    }

    private void launchHomeActivity() {
        Intent mainIntent = new Intent(this, HomeActivity.class);
        startActivity(mainIntent);
    }

    private void launchRegisterActivity() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
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
                        Toast.makeText(getBaseContext(), "Successfully signed-in " + newUser.getFirstName() + "!", Toast.LENGTH_LONG).show();

                        launchHomeActivity();
                    } else {
                        Toast.makeText(getBaseContext(), "Failed to sign-in " + newUser.getFirstName() + "!", Toast.LENGTH_LONG).show();
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

    private void loginUserOnline(String email, String password) {
        String postUrl = getString(R.string.eSeniorsLoginUrl);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);

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

    private void loginUserOffline(String email, String password) {
        getUserByEmailTask userByEmailTask = new getUserByEmailTask();
        userByEmailTask.setDatabase(getApplicationContext());

        try {
            User user = userByEmailTask.execute(email).get();
            if(user != null) {
                if(user.checkPassword(password)) {
                    Log.d(TAG, "Correct email/password!");
                    LoginUserDBTask loginUserDBTask = new LoginUserDBTask();
                    loginUserDBTask.setDatabase(getApplicationContext());

                    Boolean result = loginUserDBTask.execute(user).get();

                    if(result) {
                        Toast.makeText(getBaseContext(), "Successfully signed-in " + user.getFirstName() + "!", Toast.LENGTH_LONG).show();
                        launchHomeActivity();
                    } else {
                        Log.d(TAG, "Failed to write to DB");
                        Toast.makeText(getBaseContext(), "Failed to write to DB", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d(TAG, "Incorrect email/password!");
                    Toast.makeText(getBaseContext(), "Incorrect email/password!", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d(TAG, "Login information for that account isn't stored, please connect to internet and try again!");
                Toast.makeText(getBaseContext(), "Login information for that account isn't stored, please connect to internet and try again!", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.d(TAG, "Failed to obtain user object for - " + email + "!");
            Toast.makeText(getBaseContext(), "Failed to obtain user object for - " + email + "!", Toast.LENGTH_LONG).show();
        }
    }
}
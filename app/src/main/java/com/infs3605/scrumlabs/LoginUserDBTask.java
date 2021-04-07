package com.infs3605.scrumlabs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;

import static com.infs3605.scrumlabs.AppDatabase.getDatabase;

public class LoginUserDBTask extends AsyncTask<User, Boolean, Boolean> {
    private static final String TAG = "LoginUserTask";

    String postUrl = "https://eseniors.pythonanywhere.com/auth/device/";
    AppDatabase mDb;

    public void setDatabase(Context context) {
        this.mDb = getDatabase(context);
    }

    @Override
    protected Boolean doInBackground(User... users) {
        try {
            for (User user: users) {
                user.setSignedIn(true);

                mDb.userDao().insertUsers(user);
            }
        } catch(Exception e) {
            Log.d(TAG, e.toString());
            return false;
        }

        return true;
    }
}

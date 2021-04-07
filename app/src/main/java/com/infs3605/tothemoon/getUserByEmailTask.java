package com.infs3605.tothemoon;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class getUserByEmailTask extends AsyncTask<String, Void, User> {
    private static final String TAG = "getCurrentUserTask";

    AppDatabase mDb;
    User currentUser = null;

    public void setDatabase(Context context) {
        this.mDb = AppDatabase.getDatabase(context);
    }

    @Override
    protected User doInBackground(String... params) {
        currentUser = mDb.userDao().getUserByEmail(params[0]);

        if(currentUser != null) {
            Log.d(TAG, "Found user - " + currentUser.getEmail());
        } else {
            Log.d(TAG, "No user with that email exists");
        }

        return currentUser;
    }
}

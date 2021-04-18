package com.infs3605.tothemoon;

/*
INFS3605 Capstone Project T1 2021
To the Moon
Caitlin O'Dowd z5183007
Sharon Cheung z5162825
Neil Matani z5162753
Aiden Mansley z5265120
Connor Williams z5189800
Timothy Baker z5162709
*/

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

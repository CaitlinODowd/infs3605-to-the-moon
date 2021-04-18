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

import android.os.AsyncTask;
import android.util.Log;

public class getCurrentUserTask extends AsyncTask<Void, Void, User> {
    private static final String TAG = "getCurrentUserTask";

    AppDatabase mDb;
    User currentUser = null;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected User doInBackground(Void... params) {
        currentUser = mDb.userDao().getCurrentUser();

        if(currentUser != null) {
            Log.d(TAG, "Current user signed in - " + currentUser.getEmail());
        } else {
            Log.d(TAG, "No user is currently signed in!");
        }

        return currentUser;
    }
}

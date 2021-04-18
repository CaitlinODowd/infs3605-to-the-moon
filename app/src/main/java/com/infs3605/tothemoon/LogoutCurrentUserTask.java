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

public class LogoutCurrentUserTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "LogoutCurrentUserTask";

    AppDatabase mDb;

    public void setDatabase(Context context) {
        this.mDb = AppDatabase.getDatabase(context);
    }

    @Override
    protected Void doInBackground(Void... params) {
        User currentUser = mDb.userDao().getCurrentUser();

        if(currentUser != null) {
            currentUser.setSignedIn(false);
            mDb.userDao().insertUsers(currentUser);
            Log.d(TAG, "Successfully logged out User - " + currentUser.getEmail());
        } else {
            Log.d(TAG, "No user currently logged in!");
        }

        return null;
    }
}

package com.infs3605.scrumlabs;

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

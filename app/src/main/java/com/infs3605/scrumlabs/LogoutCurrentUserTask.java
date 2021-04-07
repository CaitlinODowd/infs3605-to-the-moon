package com.infs3605.scrumlabs;

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

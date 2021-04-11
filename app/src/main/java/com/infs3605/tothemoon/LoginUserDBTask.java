package com.infs3605.tothemoon;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import static com.infs3605.tothemoon.AppDatabase.getDatabase;

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

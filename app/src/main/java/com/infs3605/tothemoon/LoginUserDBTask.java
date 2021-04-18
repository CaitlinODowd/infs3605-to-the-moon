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

import static com.infs3605.tothemoon.AppDatabase.getDatabase;

public class LoginUserDBTask extends AsyncTask<User, Boolean, Boolean> {
    private static final String TAG = "LoginUserTask";
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

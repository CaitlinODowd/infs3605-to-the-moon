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

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public synchronized static AppDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    public static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "eSeniors.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        dbExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                User newUser = new User();
                                newUser.setEmail("Test@eSeniors.com");
                                newUser.setFirstName("John");
                                newUser.setLastName("Doe");
                                newUser.setPasswordHash("example");
                                newUser.setSignedIn(false);
                                getDatabase(context).userDao().insertUsers(newUser);

                                newUser = new User();
                                newUser.setEmail("Example@eSeniors.com");
                                newUser.setFirstName("Jane");
                                newUser.setLastName("Doe");
                                newUser.setPasswordHash("test");
                                newUser.setSignedIn(false);
                                getDatabase(context).userDao().insertUsers(newUser);
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }
}
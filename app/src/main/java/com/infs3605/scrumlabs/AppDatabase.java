package com.infs3605.scrumlabs;

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
                                newUser.setSignedIn(true);
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
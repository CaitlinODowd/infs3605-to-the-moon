package com.infs3605.tothemoon;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user WHERE signedIn LIKE 1 LIMIT 1")
    User getCurrentUser();

    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT 1")
    User getUserByEmail(String email);

    @Update
    public void updateUsers(User... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);

    @Delete
    public void deleteUsers(User... users);
}

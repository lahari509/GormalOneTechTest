package com.andsf.sampleproject.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductsDetailsToDB.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

}

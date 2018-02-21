package com.example.kramdany.todolist;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseCreator {

    private static AppDatabase appDatabase;
    private static final Object LOCK = new Object();

    public synchronized static AppDatabase getAppDatabase(Context context){
        if(appDatabase == null) {
            synchronized (LOCK) {
                if (appDatabase == null) {
                    // Allow this to have synchronous access for now, until I learn about java async constructs
                    appDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, "person db").allowMainThreadQueries().build();
                }
            }
        }
        return appDatabase;
    }
}
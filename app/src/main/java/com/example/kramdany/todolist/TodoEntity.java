package com.example.kramdany.todolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String text;
}

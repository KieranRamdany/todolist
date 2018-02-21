package com.example.kramdany.todolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT text FROM todoEntity")
    List<String> getAllTodoText();

    @Insert
    public void insertTodo(TodoEntity todo);
}

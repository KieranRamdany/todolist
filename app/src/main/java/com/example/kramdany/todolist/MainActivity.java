package com.example.kramdany.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout todoList;
    private EditText editText;
    private Button addButton;
    private AppDatabase db;
    private LayoutInflater layoutInflater;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = DatabaseCreator.getAppDatabase(getApplicationContext());
            layoutInflater = LayoutInflater.from(this.getApplicationContext());
            todoList = this.findViewById(R.id.todoList);
            addButton = this.findViewById(R.id.addButton);
            editText = this.findViewById(R.id.editText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(editable.toString().trim().isEmpty()){
                        addButton.setEnabled(false);
                    }
                    else {
                        addButton.setEnabled(true);
                    }
                }
            });
            initiateTodoList();
        }

    private void initiateTodoList() {
        for(String text : db.todoDao().getAllTodoText()) {
            addToTodoList(text);
        }
    }

    private void addToTodoList(String text) {
        TextView todoItem = (TextView) layoutInflater.inflate(R.layout.todo_item, todoList, false);
        todoItem.setText(text);
        editText.setText("");
        todoList.addView(todoItem);

    }

    public void addTodo(View view) {
        Log.i("buttonClickedViewId", Integer.toString(view.getId()));
        String text = editText.getText().toString().trim();
        if(!text.isEmpty()) {
            TodoEntity todo = new TodoEntity();
            todo.text = text;
            db.todoDao().insertTodo(todo);
            addToTodoList(text);
        }
        else {
            editText.setText("");
        }


    }
}

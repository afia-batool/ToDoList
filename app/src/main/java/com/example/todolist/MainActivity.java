package com.example.todolist;

import static android.os.Build.VERSION_CODES.R;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener, ToDoAdapter.OnDeleteClickListener {

    private final List<ToDoModel> todoList = new ArrayList<>();
    private ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R);


        RecyclerView tasksRecyclerView = findViewById(R);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add some sample tasks
        todoList.add(new ToDoModel("Go to the market and bring vegetables immediately today.", 0));
        todoList.add(new ToDoModel("Finish homework for the Java course.", 1));
        todoList.add(new ToDoModel("Call Mom.", 0));

        // Initialize the adapter with the list of tasks
        toDoAdapter = new ToDoAdapter(todoList, this);
        tasksRecyclerView.setAdapter(toDoAdapter);

        // Add button click listener to open AddNewTask dialog
        FloatingActionButton addTaskButton = findViewById(R);
        addTaskButton.setOnClickListener(v -> {
            AddNewTask addNewTask = AddNewTask.newInstance();
            addNewTask.show(getSupportFragmentManager(), AddNewTask.TAG);
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog, String newTask) {
        if (newTask != null) {

            todoList.size();
            todoList.add(new ToDoModel(newTask, 0));
            toDoAdapter.notifyItemInserted(todoList.size() - 1);
        }
    }

    @Override
    public void onDeleteClick(int position) {
        todoList.remove(position);
        toDoAdapter.notifyItemRemoved(position);
    }
}

package com.example.todolist.Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.ToDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private final OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public ToDoAdapter(List<ToDoModel> todoList, OnDeleteClickListener onDeleteClickListener) {
        this.todoList = todoList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R, parent, false);
        return new ViewHolder(itemView, onDeleteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int status = isChecked ? 1 : 0;
            item.setStatus(status);
            // Here you can update the status in the database or take any other action
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final CheckBox task;
        final ImageButton deleteButton;

        ViewHolder(View view, OnDeleteClickListener onDeleteClickListener) {
            super(view);
            task = view.findViewById(R);
            deleteButton = view.findViewById(R);

            deleteButton.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onDeleteClickListener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}

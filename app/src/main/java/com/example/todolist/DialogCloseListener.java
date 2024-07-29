package com.example.todolist;

import android.content.DialogInterface;

public interface DialogCloseListener {
    void handleDialogClose(DialogInterface ignoredDialog, String newTask);
}

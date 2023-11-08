package com.example.organizeme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TodoItem> todoItems;

    public TodoListAdapter(Context context, ArrayList<TodoItem> todoItems) {
        this.context = context;
        this.todoItems = todoItems;
    }

    @Override
    public int getCount() {
        return todoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return todoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todoitem, parent, false);
        }

        TodoItem todoItem = (TodoItem) getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        CheckBox completedCheckBox = convertView.findViewById(R.id.completedCheckBox);
        TextView dueDateTextView = convertView.findViewById(R.id.dueDateTextView);

        titleTextView.setText(todoItem.getTitle());
        completedCheckBox.setChecked(todoItem.isCompleted());

        // Format and display the due date
        // Replace this with your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

// Get the current date or any other date
        Date dueDate = new Date(); // Replace this with your due date

// Format the date as a string
        String formattedDate = dateFormat.format(dueDate);

// Set the formatted date to your TextView
        dueDateTextView.setText(formattedDate);


        return convertView;
    }
}

package com.example.organizeme;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Homepage extends AppCompatActivity {
    private ListView todoListView;
    private Button addTodoButton;
    private TodoListAdapter todoListAdapter;
    private DatabaseReference todoItemsRef;
    private ArrayList<TodoItem> todoItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        todoListView = findViewById(R.id.listViewTodo);
        addTodoButton = findViewById(R.id.buttonAdd);
        todoListAdapter = new TodoListAdapter(this, todoItems);
        todoItemsRef = FirebaseDatabase.getInstance().getReference("todoItems");

        // Set up a listener to fetch and display to-do items
        todoItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                todoItems.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    TodoItem todoItem = itemSnapshot.getValue(TodoItem.class);
                    todoItems.add(todoItem);
                }
                todoListView.setAdapter(todoListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle any errors here
            }
        });

        // Set up a click listener for the "Add To-Do" button
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new to-do item and add it to Firebase
                Date dueDate = new Date(); // Replace this with the desired due date
                TodoItem newTodoItem = new TodoItem("New To-Do", false, dueDate);
                String itemId = todoItemsRef.push().getKey();
                todoItemsRef.child(itemId).setValue(newTodoItem);
            }
        });
    }
}
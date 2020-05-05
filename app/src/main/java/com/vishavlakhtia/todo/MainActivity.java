package com.vishavlakhtia.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton bt_add;
    ListView listView;
    DBHelper db;
    fetchTask adapter;
    ArrayList<String> lists ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        lists = db.getAllTask();
        listView = (ListView) findViewById(R.id.lv_todo);
        adapter = new fetchTask(this,lists);
        listView.setAdapter(adapter);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        bt_add = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),addToList.class);
                Toast.makeText(getApplicationContext(),"add list",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

}

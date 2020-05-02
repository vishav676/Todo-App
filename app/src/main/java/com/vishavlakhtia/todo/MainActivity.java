package com.vishavlakhtia.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton bt_add;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> lists = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv_todo);
        adapter = new ArrayAdapter<String>(this,R.layout.item_list,R.id.disTitle,lists);
        listView.setAdapter(adapter);

        bt_add = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),addToList.class);
                Toast.makeText(getApplicationContext(),"add list",Toast.LENGTH_SHORT).show();
                startActivityForResult(i,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2)
        {
            String title = data.getStringExtra("NEWTASK");
            adapter.add(title);
        }
    }
}

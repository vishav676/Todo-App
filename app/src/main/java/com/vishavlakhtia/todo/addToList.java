package com.vishavlakhtia.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addToList extends AppCompatActivity {

    EditText et_title;
    EditText et_description;
    Button addNew;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        db = new DBHelper(this);
        et_title = (EditText)findViewById(R.id.titleEt);
        addNew = (Button)findViewById(R.id.addnewTask);


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                db.insertTask(title);
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}

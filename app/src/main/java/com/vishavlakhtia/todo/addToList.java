package com.vishavlakhtia.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addToList extends AppCompatActivity {

    EditText et_title;
    EditText et_description;
    Button addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        
        et_title = (EditText)findViewById(R.id.titleEt);
        addNew = (Button)findViewById(R.id.addnewTask);


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                Intent i = new Intent();
                i.putExtra("NEWTASK", title);
                setResult(2,i);
                finish();
            }
        });
    }
}

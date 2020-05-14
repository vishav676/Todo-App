package com.vishavlakhtia.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class addToList extends AppCompatActivity {

    EditText et_title;
    EditText et_description;
    Button addNew;
    DBHelper db;
    String saveCurrentDate, saveCurrentTime;
    String id;
    DatabaseReference dataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        db = new DBHelper(this);
        et_title = (EditText)findViewById(R.id.titleEt);
        addNew = (Button)findViewById(R.id.addnewTask);
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());
                id = saveCurrentDate+saveCurrentTime;
                HashMap<String,Object> dataMap = new HashMap<>();
                dataMap.put("title",title);
                dataMap.put("id", id);
                dataRef = FirebaseDatabase.getInstance().getReference();
                dataRef.child(currentUser.getUid()).child("unComplete").child(id).updateChildren(dataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Task Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                db.insertTask(title);
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}

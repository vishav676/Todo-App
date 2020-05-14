package com.vishavlakhtia.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;

    LottieAnimationView get_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email_sign);
        password = findViewById(R.id.pass_sign);

        get_started = findViewById(R.id.bt_sign);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = username.getText().toString();
                final String pass = password.getText().toString();

                if (TextUtils.isEmpty(user))
                {
                    Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(pass))
                {
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(user,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful())
                                    {
                                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user,pass)
                                                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull final Task<AuthResult> task) {
                                                        if (!task.isSuccessful())
                                                        {
                                                            Toast.makeText(getApplicationContext(),"Invalid user details",Toast.LENGTH_SHORT).show();
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                        }
                                                    }
                                                });

                                    }else {

                                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                }
                            });
                }
            }
        });


    }
}

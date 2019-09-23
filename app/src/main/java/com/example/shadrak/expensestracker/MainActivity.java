package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailid, password;
    Button login, register;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("test","oncreate()");
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);
        register = findViewById(R.id.button3);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null) {
                    Toast.makeText(MainActivity.this,"LogIn Successfull!!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Homeactivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this,"Please Login!!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty()) {
                    emailid.setError("Please enter your EmailId");
                    emailid.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    password.setError("Please Enter your Password!!");
                    password.requestFocus();
                }
                else if(!email.isEmpty() && !pwd.isEmpty()) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this,"Login Error, Please Login again!!",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this,"Login Successfull!!",Toast.LENGTH_SHORT).show();
                                Intent inToHome = new Intent(MainActivity.this, Homeactivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registeractivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

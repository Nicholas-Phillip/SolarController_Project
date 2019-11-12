package com.example.solarcontrollerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class login extends AppCompatActivity {

    //private Menu globalmenu;
    private FirebaseAuth mAuth;
    //private TextView message;
    private Button login;
    private Button register;
    //private Button signout;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findAllViewsfromLayout();

        LoggingIn(); //change to start up
    }

    private void LoggingIn() {
        //Firebased Initialized
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() { //if user clicks register
            @Override
            public void onClick(View v) {
                createNewUser(String.valueOf(email.getText()),String.valueOf(password.getText()));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {  //if user clicks login
            @Override
            public void onClick(View v) {
                Login(String.valueOf(email.getText()),String.valueOf(password.getText()));
                //LoggingIn();
            }
        });
    }

    private void findAllViewsfromLayout() {
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        password = findViewById(R.id.password);
        login= findViewById(R.id.login);
        //signout = findViewById(R.id.signout);
    }

    private void createNewUser(String email,String password) {
        Intent register = new Intent(this,Register.class);
        startActivity(register);
    }


    private void Login(String email, String password){
        // TODO: Login with Email and Password on Firebase.
        if (email.length()==0 || password.length()==0){
            Toast.makeText(getApplicationContext(), "Email and password cannot be empty",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MapleLeaf", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //message.setText("User "+ user.getEmail() + " is now Logged In");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MapleLeaf", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    }



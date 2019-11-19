package com.example.solarcontrollerproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView message;
    private Button signin;
    private Button register;
    private Button signout;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FindAllViews();
        handleLogin();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Replace with your own action",Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });
    }

    private void handleLogin(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "register",
                       Toast.LENGTH_LONG).show();
                createNewUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Sign in was pressed ",
                      //  Toast.LENGTH_LONG).show();
                loginUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sign out was pressed ",
                       Toast.LENGTH_LONG).show();
                SignoutfromDatabase();
            }
        });
    }
    private void createNewUser(String email, String password){
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }

    private void loginUser(String email, String password) {
        // TODO: Login with Email and Password on Firebase.
        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(),"Email and password cannot be empty",
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
                            Toast.makeText(getApplicationContext(), "Sign Successful: Welcome" + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                            message.setText("User "+ user.getEmail() + " is now Logged In");

                            Goto_main_screen();

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
    private void FindAllViews() {
        message = findViewById(R.id.message1);
        signin = findViewById(R.id.signin);
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signout = findViewById(R.id.signout);
    }

    public void Goto_main_screen() {
        Intent i = new Intent (this, MainActivity.class);
        String User=(email.getText().toString());
        i.putExtra("email",User);
        startActivity(i);
    }

    private void SignoutfromDatabase(){
        // TODO: Logout from Firebase.
        mAuth.signOut();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_quit)
        {
            // TODO: Finish the APP.
            finishAndRemoveTask();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
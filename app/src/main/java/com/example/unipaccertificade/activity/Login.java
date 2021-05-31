package com.example.unipaccertificade.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unipaccertificade.R;
import com.example.unipaccertificade.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button enter;
    TextView txtcad;
    private FirebaseAuth autentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailiPROF);
        password = findViewById(R.id.passwordidPROF);
        enter = (Button) findViewById(R.id.entrarid);
        txtcad = (TextView) findViewById(R.id.cadid2);


    }

    public void enterLogin (User user){
        autentification.signInWithEmailAndPassword(user.getEmail(),user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if ( task.isSuccessful()){
                    callMenu();
                } else {
                    Toast.makeText(Login.this, "Email ou senha incorreta!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void AuthInfoLogin (View view){
        String emaillogin =  email.getText().toString(); // Get the text for convert to string
        String passwordlogin =  password.getText().toString();  // Get the text for convert to string
        //Verify if email and password is empty or not
        if( !emaillogin.isEmpty()){
            if(!passwordlogin.isEmpty()){
                //Use the object to set a information
                User user = new User();
                user.setEmail(emaillogin);
                user.setPassword(passwordlogin);
                enterLogin(user);
            }else {
                Toast.makeText(Login.this, "Senha vazia! (;", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Login.this, "Email vazio! (;", Toast.LENGTH_SHORT).show();
        }
    }

    public void callProfessorLogin (View view) {
        Intent intent = new Intent(Login.this, Menu.class);
        startActivity(intent);
    }

    public void callMenu (){
        Intent intent = new Intent(Login.this, Menu.class);
        startActivity(intent);

    }

}
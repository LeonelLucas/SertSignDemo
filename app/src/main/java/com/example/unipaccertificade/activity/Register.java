package com.example.unipaccertificade.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipaccertificade.R;
import com.example.unipaccertificade.conf.ConfFireBase;
import com.example.unipaccertificade.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Register extends AppCompatActivity {
    EditText emailtext, passwordtext; //
    FirebaseAuth autentification; //Used for login autentification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailtext = findViewById(R.id.emailiPROF);
        passwordtext = findViewById(R.id.passwordidPROF);
    }
    //Save the user, this method use the firebase configurations in confFireBase class
    public void saveUSer(User user){
        autentification = ConfFireBase.Auth(); // Call the method from confFireBase
        // Get the data of user from this class in method AuthInfo
        autentification.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this,"Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String exception = "";
                    try {
                       throw  task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        exception = "Senha fraca!";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Email inválido";
                    }catch (FirebaseAuthUserCollisionException e) {
                        exception = "Essa conta já existe!";
                    } catch (Exception e) {
                        exception = "Ocorreu um erro ao cadastrar" +e.getMessage();
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    // Email autentification
    public void AuthInfo(View view){
     String email =  emailtext.getText().toString(); // Get the text for convert to string
     String password =  passwordtext.getText().toString();  // Get the text for convert to string
        //Verify if email and password is empty or not
     if( !email.isEmpty()){
         if(!password.isEmpty()){
             //Use the object to set a information
             User user = new User();
             user.setEmail(email);
             user.setPassword(password);
             saveUSer(user);
         }else {
             Toast.makeText(Register.this, "Senha vazia! (;", Toast.LENGTH_SHORT).show();
         }
    }else{
         Toast.makeText(Register.this, "Email vazio! (;", Toast.LENGTH_SHORT).show();
     }
    }
}
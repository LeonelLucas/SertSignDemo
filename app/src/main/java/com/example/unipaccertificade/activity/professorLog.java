package com.example.unipaccertificade.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unipaccertificade.R;
import com.example.unipaccertificade.model.Professor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class professorLog extends AppCompatActivity {
    EditText emailprof, passwordprof;
    FirebaseAuth autentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_log);

        emailprof = findViewById(R.id.emailiPROF);
        passwordprof = findViewById(R.id.passwordidPROF);
    }

    public void logProf (Professor professor){
        autentification.signInWithEmailAndPassword(professor.getEmail(), professor.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
              if (task.isSuccessful())  {

              } else {
                  Toast.makeText(professorLog.this, "Falha ao carregar", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }

    public void authLogProf (View view) {
        String email = emailprof.getText().toString();
        String passoword = passwordprof.getText().toString();
        if(!email.isEmpty()){
            if(!passoword.isEmpty()) {
                Professor professor = new Professor();
                professor.getEmail();
                professor.getPassword();
                logProf(professor);
            } else{
                Toast.makeText(professorLog.this, "Senha vazia ou inválida! (;", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(professorLog.this, "Email vazio ou inválido! (;", Toast.LENGTH_SHORT).show();
        }
    }
}


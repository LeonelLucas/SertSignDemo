package com.example.unipaccertificade.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.unipaccertificade.R;
import com.example.unipaccertificade.activity.Camera;
import com.example.unipaccertificade.activity.Certificate;

public class Menu extends AppCompatActivity {
    Button photo, SendAttachment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        photo = (Button)findViewById(R.id.photoid);
        SendAttachment  =  (Button) findViewById(R.id.sendAttenchment);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        SendAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCertificade();
            }
        });
    }

    public  void openCamera(){
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    public  void openCertificade(){
        Intent intent = new Intent(this, Certificate.class);
        startActivity(intent);
    }


}
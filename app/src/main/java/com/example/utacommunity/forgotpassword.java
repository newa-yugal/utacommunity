package com.example.utacommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    EditText fEmail;
    Button Send;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        fEmail = findViewById(R.id.sendEmail);
        Send = findViewById(R.id.Send_btn);
        fAuth = FirebaseAuth.getInstance();

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.sendPasswordResetEmail(fEmail.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgotpassword.this,"Password send to your email",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(forgotpassword.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
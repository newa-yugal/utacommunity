package com.example.utacommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mNetId, mMajor, mEmail, mPhone, mAddress, mUser, mPassword, mRepass, mSummary;
    Button mSubmitBtn, mBackBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fname);
        mNetId = findViewById(R.id.id);
        mMajor = findViewById(R.id.major);
        mEmail = findViewById(R.id.email);
        mPhone = findViewById(R.id.phone);
        mAddress = findViewById(R.id.address);
        mUser = findViewById(R.id.username);
        mPassword = findViewById(R.id.pass);
        mRepass = findViewById(R.id.repass);
        mSummary = findViewById(R.id.summary);
        mSubmitBtn = findViewById(R.id.submit);
        mBackBtn = findViewById(R.id.back);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = mFullName.getText().toString().trim();
                String netid = mNetId.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String user = mUser.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRepass.getText().toString().trim();


                if(TextUtils.isEmpty(Name)){
                    mFullName.setError("Full name is required");
                    return;
                }
                if(TextUtils.isEmpty(netid)){
                    mNetId.setError("NetId is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(user)){
                    mUser.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length() < 5){
                    mPassword.setError("Password must be greater or equal to 6 characters");
                    return;
                }
                if(!password.equals(repassword)){
                    mRepass.setError("Passwords must be same");
                    return;
                }



            }
        });
    }
}
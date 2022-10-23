package com.example.loginactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mName,mUsername,mEmail,mPassword,mConfirmPassword;
    Button mRegister;
    TextView mAlreadyuser;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mName=findViewById(R.id.inputName2);
        mUsername=findViewById(R.id.inputUsername2);
        mEmail=findViewById(R.id.inputemail2);
        mPassword=findViewById(R.id.inputpassword2);
        mConfirmPassword=findViewById(R.id.inputconfirmpassword2);
        mRegister=findViewById(R.id.signupforanewaccount);
        mAlreadyuser=findViewById(R.id.alreadyhaveanaccount);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=mUsername.getText().toString();
                String email =mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String confirmpassword=mConfirmPassword.getText().toString().trim();
                String name=mName.getText().toString();

                if(TextUtils.isEmpty(username)){
                    mUsername.setError("Username is Required");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(TextUtils.isEmpty(confirmpassword)){
                    mConfirmPassword.setError("Re-enter the password");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be >= 6 characters to become strong");
                    return;
                }

                if(!confirmpassword.equals(password)){
                    mConfirmPassword.setError("Password not matching");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user to the firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration.this, "Registration Successful,Please do Login to continue further.", Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("users").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("Name",name);
                            user.put("Username",username);
                            user.put("Email",email);
                            user.put("Password",password);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccesss: user Profile is created for "+ userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else{
                            Toast.makeText(Registration.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mAlreadyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

         }
}
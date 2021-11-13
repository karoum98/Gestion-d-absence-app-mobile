package com.hassoun.absenceehtp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassoun.absenceehtp.Model.CurrentTeacher;
import com.hassoun.absenceehtp.Model.Teacher;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    EditText mail,password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRef = database.getReference("Teachers");
        mail=findViewById(R.id.id_email);
        password=findViewById(R.id.id_password);
        login=findViewById(R.id.id_login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_mail=mail.getText().toString();
                String s_password=password.getText().toString();


                if(TextUtils.isEmpty(s_mail) || TextUtils.isEmpty(s_password)){
                    Toast.makeText(LoginActivity.this,"all fields are required !", Toast.LENGTH_SHORT).show();
                }
                else if (s_password.length()<6){
                    Toast.makeText(LoginActivity.this,"password must be at least 6 caracters",Toast.LENGTH_SHORT).show();

                }
                else {
                    login(s_mail, s_password);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void login(String name, String password) {
        myRef = database.getReference("Teachers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean etat=false;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Teacher teacher=dataSnapshot.getValue(Teacher.class);
                    if(teacher.getName().equals(name) && teacher.getPassword().equals(password)){
                        etat=true;
                         
                    }
                }
                if (etat)
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                //Toast.makeText(LoginActivity.this,"successful",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this,"failed",Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
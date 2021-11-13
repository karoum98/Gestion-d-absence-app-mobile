package com.hassoun.absenceehtp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassoun.absenceehtp.Adapter.classAdapter;
import com.hassoun.absenceehtp.Model.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ModuleActivity extends AppCompatActivity implements classAdapter.ListItemClickListener  {

    com.hassoun.absenceehtp.Adapter.classAdapter classAdapter;
    ArrayList<String> elements;
    ArrayList<String> modules;
    RecyclerView recyclerView;
    String element;
    String year;
    DatabaseReference mbase;
    Intent intent;
    TextView datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        datepicker=findViewById(R.id.datepicker);
        recyclerView=findViewById(R.id.recycler_view);
        elements =new ArrayList<>();
        element=getIntent().getStringExtra("element");
        year=getIntent().getStringExtra("year");
        mbase= FirebaseDatabase.getInstance().getReference("Elements").child(year).child(element);

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                elements.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String element=dataSnapshot.child("element").getValue(String.class);
                     elements.add(element);

                }
                classAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        classAdapter =new classAdapter(elements, this,ModuleActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ModuleActivity.this));
        recyclerView.setAdapter(classAdapter);

        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        datepicker.setText(String.valueOf(dateFormat.format(new Date())));

    }

    @Override
    public void onListItemClick(int position) {
        intent=new Intent(ModuleActivity.this, StudentActivity.class);
        modules=new ArrayList<>();
        element=getIntent().getStringExtra("element");
        year=getIntent().getStringExtra("year");
        intent.putExtra("element",element);
        intent.putExtra("year",year);
        mbase = FirebaseDatabase.getInstance().getReference("Elements").child(year).child(element);
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String s=dataSnapshot.child("element").getValue(String.class);
                    modules.add(s);
                }
                String module =modules.get(position);
                intent.putExtra("module",module);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
package com.hassoun.absenceehtp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassoun.absenceehtp.Adapter.classAdapter;
import com.hassoun.absenceehtp.ModuleActivity;
import com.hassoun.absenceehtp.R;

import java.util.ArrayList;

public class SecondyearFragment extends Fragment implements classAdapter.ListItemClickListener {

    DatabaseReference mbase;
    com.hassoun.absenceehtp.Adapter.classAdapter classAdapter;
    ArrayList<String> classes;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_firstyear, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        mbase = FirebaseDatabase.getInstance().getReference("Elements").child("Second year");

        classes =new ArrayList<>();
        classAdapter =new classAdapter(classes, this, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(classAdapter);

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classes.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    classes.add(dataSnapshot.getKey().toString());
                }
                classAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent=new Intent(getActivity(), ModuleActivity.class);
        ArrayList<String> elements=new ArrayList<>();
        mbase = FirebaseDatabase.getInstance().getReference("Elements").child("Second year");

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String s=dataSnapshot.getKey();
                    elements.add(s);
                }
                String element =elements.get(position);
                intent.putExtra("element",element);
                intent.putExtra("year","Second year");
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
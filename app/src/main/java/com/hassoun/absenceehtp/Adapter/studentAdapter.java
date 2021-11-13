package com.hassoun.absenceehtp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hassoun.absenceehtp.Model.Student;
import com.hassoun.absenceehtp.R;

import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.ViewHolder> {
    ArrayList<Student> students;
    Context context;
    DatabaseReference mbase;

    public studentAdapter(ArrayList<Student> students,Context context,DatabaseReference mbase) {
        this.students = students;
        this.context=context;
        this.mbase=mbase;
    }

    @NonNull
    @Override
    public studentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemlayoutview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item,null);

        studentAdapter.ViewHolder viewHolder=new studentAdapter.ViewHolder(itemlayoutview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull studentAdapter.ViewHolder holder, int position) {
        Student student=students.get(position);
        holder.studentname.setText(student.getName());
        holder.cardView.setCardBackgroundColor(Color.GREEN);
        mbase.child(student.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    holder.cardView.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"present",Toast.LENGTH_SHORT).show();
                mbase.child(student.getId()).removeValue();
                holder.cardView.setCardBackgroundColor(Color.GREEN);
            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"absent",Toast.LENGTH_SHORT).show();
                holder.cardView.setCardBackgroundColor(Color.RED);
                mbase.child(student.getId()).setValue(student.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button present,absent;
        TextView studentname;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            present=itemView.findViewById(R.id.present);
            absent=itemView.findViewById(R.id.absent);
            studentname=itemView.findViewById(R.id.studentname);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }
}

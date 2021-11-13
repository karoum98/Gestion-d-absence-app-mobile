package com.hassoun.absenceehtp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassoun.absenceehtp.R;

import java.util.ArrayList;

public class classAdapter extends RecyclerView.Adapter<classAdapter.ViewHolder> {
    private ArrayList<String> classes;
    final private ListItemClickListener OnClickListener;
    Context context;

    public classAdapter(ArrayList<String> classes, ListItemClickListener onClickListener, Context context) {
        this.classes = classes;
        OnClickListener = onClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemlayoutview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view,null);

        ViewHolder viewHolder=new ViewHolder(itemlayoutview);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String classe =classes.get(position);
        holder.textView.setText(classe);

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_item);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            OnClickListener.onListItemClick(position);
        }
    }
    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
}

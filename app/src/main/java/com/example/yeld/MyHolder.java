package com.example.yeld;

import android.view.View;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    //public TextView id;
    public TextView distance;
    public TextView name;
    MyAdapter.OnNoteListener onNoteListener;


    public MyHolder(@NonNull View itemView, MyAdapter.OnNoteListener onNoteListener) {
        super(itemView);
        //save the item of one row
        //this.id = itemView.findViewById(R.id.tv_id);

        this.distance = itemView.findViewById(R.id.tv_distance);

        this.name = itemView.findViewById(R.id.tv_name);
        this.onNoteListener = onNoteListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onNoteListener.onNoteClick(getAdapterPosition());

    }
}
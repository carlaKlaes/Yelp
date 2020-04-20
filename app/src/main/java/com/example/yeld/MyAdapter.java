package com.example.yeld;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Model> arrayModel; //list with parameters of Model Class
    private OnNoteListener onNoteListener;

    public MyAdapter(Context c, ArrayList<Model> models,OnNoteListener onNoteListener) {
        this.c = c;
        this.arrayModel = models;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate the row
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yelp, null);
        //return the view to holder class
        return new MyHolder(view,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        //String businessId = arrayModel.get(i).getBusinessId();
        String businessName = arrayModel.get(i).getBusinessName();
        Double businessDistance = arrayModel.get(i).getBusinessDistance();

        //myHolder.id.setText(businessId);

        String temp = String.format("%.2f",businessDistance);

        myHolder.distance.setText(temp);
        myHolder.name.setText(businessName);
    }

    @Override
    public int getItemCount() {
        return arrayModel.size();
    }

    //Get clicked item
    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}

package com.example.smarttripride__login;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter extends FirebaseRecyclerAdapter<ModuleClass, Adapter.myViewHolder> {





    public Adapter(@NonNull FirebaseRecyclerOptions<ModuleClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModuleClass model) {
        holder.Name_Text.setText(model.getUserName());
        holder.Phone_num.setText(model.getPhoneNumber());
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item_view,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView Name_Text, Phone_num;
        Button Send_Request;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Name_Text = (TextView) itemView.findViewById(R.id.Scan_name);
            Phone_num = (TextView) itemView.findViewById(R.id.Scan_phone);
            Send_Request = (Button) itemView.findViewById(R.id.Scan_btn_request);


        }
    }




}

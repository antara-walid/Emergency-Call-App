package com.example.emergencycallapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyVIewHolder> {

    Context context;
    ArrayList contact_name, contact_num, contact_email,contact_id;
    Activity activity;

    CustomAdapter(Activity activity, Context context , ArrayList contact_id,ArrayList contact_name, ArrayList contact_num, ArrayList contact_email){
        this.activity = activity;
        this.context=context;
        this.contact_id=contact_id;
        this.contact_name=contact_name;
        this.contact_num=contact_num;
        this.contact_email=contact_email;
    }
    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyVIewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int position) {
        holder.contact_name_txt.setText(String.valueOf(contact_id.get(position)));
        holder.contact_name_txt.setText(String.valueOf(contact_name.get(position)));
        holder.contact_num_txt.setText(String.valueOf(contact_num.get(position)));
        holder.contact_email_txt.setText(String.valueOf(contact_email.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(contact_id.get(position)));
                intent.putExtra("name", String.valueOf(contact_name.get(position)));
                intent.putExtra("num", String.valueOf(contact_num.get(position)));
                intent.putExtra("email", String.valueOf(contact_email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  contact_name.size();
    }

    public class MyVIewHolder extends RecyclerView.ViewHolder {

        TextView contact_name_txt,contact_num_txt,contact_email_txt,contact_id_txt;
        LinearLayout mainLayout;
        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name_txt=itemView.findViewById(R.id.contact_name_txt);
            contact_num_txt=itemView.findViewById(R.id.contact_num_txt);
            contact_email_txt=itemView.findViewById(R.id.contact_email_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);

            Animation translate_anim= AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}

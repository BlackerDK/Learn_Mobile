package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdaper extends RecyclerView.Adapter<UserAdaper.ViewHolder> {

    private ArrayList<User> userList;

    public UserAdaper(ArrayList<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User u = userList.get(position);
        holder.tvusername.setText(u.getUsername());
        holder.tvFullName.setText(u.getFullName());
        holder.tvEmail.setText(u.getEmail());
        if(position%2!=0)
            holder.linearLayout.setBackgroundResource(R.color.old_color);
    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvusername;
        TextView tvFullName;
        TextView tvEmail;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvusername = itemView.findViewById(R.id.tvUsername);
            linearLayout = itemView.findViewById(R.id.llWrapper);
        }
    }
}

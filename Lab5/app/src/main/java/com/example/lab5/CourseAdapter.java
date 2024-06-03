package com.example.lab5;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> courses;
    private OnItemKhanhClickListener onLongClickListener;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.course_row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course c = courses.get(position);
        holder.tvTitle.setText(c.getTitle());
        holder.tvDes.setText(c.getDes());
        holder.itemView.setOnLongClickListener(v -> {
            onLongClickListener.onItemLongClickListener(c, position);
            return false;
        });

        if(c.getIcon() instanceof Uri ){
            holder.icon.setImageURI((Uri) c.getIcon());
        }else if(c.getIcon() instanceof Integer){
            holder.icon.setImageResource((Integer) c.getIcon());
        }

        if(c.getImg() instanceof Uri ){
            holder.ivBanner.setImageURI((Uri) c.getImg());
        }else if(c.getImg() instanceof Integer){
            holder.ivBanner.setImageResource((Integer) c.getImg());
        }
        holder.linearLayout.setBackgroundResource(position%2==0?R.color.white:R.color.blue_bg_color);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivBanner;
        ImageView icon;
        TextView tvTitle;
        TextView tvDes;

        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner =  itemView.findViewById(R.id.ivBanner);
            icon = itemView.findViewById(R.id.ivIcon);
            tvDes = itemView.findViewById(R.id.tvDes);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            linearLayout = itemView.findViewById(R.id.llWrapper2);
        }
    }

    public void setOnLongClickListener(OnItemKhanhClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}

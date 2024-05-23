package com.example.demo_custom_listview_lab3;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FootBallAdapter extends BaseAdapter {
    private List<Foodball> dataSource;
    private int rowLayout;
    private Context context;


    public FootBallAdapter(List<Foodball> dataSource, int rowLayout, Context context) {
        this.dataSource = dataSource;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(rowLayout,null);

        TextView tvTitle = view.findViewById(R.id.tvFTitle);
        TextView tvDes = view.findViewById(R.id.tvFDes);
        ImageView iv = view.findViewById(R.id.ivFImg);
        ImageView ivF = view.findViewById(R.id.ivFlag);

        Foodball f = dataSource.get(position);
        tvTitle.setText(f.getName());
        tvDes.setText(f.getDes());
        if(f.getAvatart() instanceof Uri){
            iv.setImageURI((Uri) f.getAvatart());
        }else{
            iv.setImageResource((Integer)f.getAvatart());
        }
        if(f.getFlag() instanceof Uri){
            ivF.setImageURI((Uri) f.getFlag());
        }else{
            ivF.setImageResource((Integer)f.getFlag());
        }

        return view;
    }
}

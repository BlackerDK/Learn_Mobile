package com.example.demo_custom_listview_lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private List<Fruit> dataSource;
    private int rowLayout;
    private Context context;


    public FruitAdapter(List<Fruit> dataSource, int rowLayout, Context context) {
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

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvDes = view.findViewById(R.id.tvDes);
        ImageView iv = view.findViewById(R.id.ivImg);

        Fruit fruit = dataSource.get(position);
        tvTitle.setText(fruit.getName());
        tvDes.setText(fruit.getDes());
        if(fruit.getImageType() ==ImageType.RESOURCE){
            iv.setImageResource(fruit.getImg());
        }else{
            iv.setImageURI(fruit.getUri());
        }

        return view;
    }
}

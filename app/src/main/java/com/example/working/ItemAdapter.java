package com.example.working;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Activity activity ;
    private LayoutInflater inflater ;
    private List<item> items ;
    public ItemAdapter(Activity activity, List<item> items){
        this.activity = activity ;
        this.items = items ;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       if (inflater == null){
           inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       }
       if (convertView == null){
           convertView = inflater.inflate(R.layout.custom_layout,null);

       }



        TextView name, id, description, punishment, reward;
        name = convertView.findViewById(R.id.name);
        id = convertView.findViewById(R.id.id);
        description = convertView.findViewById(R.id.description);
        punishment = convertView.findViewById(R.id.punishment);
        reward = convertView.findViewById(R.id.reward);

        //getting data from row
        item  item = items.get(position);


        name.setText(item.getName());
        description.setText(item.getDescription());
        punishment.setText(item.getPunishment());
        reward.setText(item.getPunishment());
        id.setText(String.valueOf(item.getId()));

        return convertView;

    }
}

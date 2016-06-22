package com.example.phuc.footballmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.phuc.footballmanagement.Model.ItemSlidingMenu;
import com.example.phuc.footballmanagement.R;


import java.util.ArrayList;


public class SlidingMenuAdapter extends ArrayAdapter<ItemSlidingMenu>{
    Activity mContext = null;
    int idLayout;
    ArrayList<ItemSlidingMenu> itemSlidingMenuArrayList = null;

    public SlidingMenuAdapter(Activity mContext, int idLayout, ArrayList<ItemSlidingMenu> itemSlidingMenuArrayList) {
        super(mContext, idLayout, itemSlidingMenuArrayList);
        this.mContext = mContext;
        this.idLayout = idLayout;
        this.itemSlidingMenuArrayList = itemSlidingMenuArrayList;
    }

    public  SlidingMenuAdapter(Context context, int resource){
        super(context,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = mContext.getLayoutInflater();
            convertView = layoutInflater.inflate(idLayout, null);

            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        }

        ItemSlidingMenu itemSlidingMenu = itemSlidingMenuArrayList.get(position);
        viewHolder.itemImage.setImageResource(itemSlidingMenu.getIdImage());
        viewHolder.itemName.setText(itemSlidingMenu.getItemName());
        notifyDataSetChanged();
        return  convertView;
    }

    class ViewHolder{
        ImageView itemImage;
        TextView itemName;
    }
}
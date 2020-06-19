package com.example.thecomicslibrary.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thecomicslibrary.R;

import com.squareup.picasso.Picasso;

import com.example.thecomicslibrary.utility.RoundBorderTransformation;


import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView name;
        ImageView image;
    }

    private ArrayList<Comic> arrayL;
    private List<Comic> temp;
    private Context context;

    public ComicAdapter(Context context, ArrayList comics) {
        this.temp = comics;
        this.context = context;
        arrayL = new ArrayList<>();
        arrayL.addAll(temp);
    }

    @Override
    public int getCount() {
        return temp.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder = null;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.grid_element, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.IconText);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.IconView);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // here setting up names and images
        viewHolder.name.setText(temp.get(position).getIssue_title() + "");

        final int radius = 10;
        final int margin = 10;

        //todo modify the link
        Picasso.get().load(temp.get(position).getIssue_link_image()).transform(new RoundBorderTransformation(radius,margin)).resize(400, 550).into(viewHolder.image);

        rowView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, GridView.AUTO_FIT));


        return rowView;
    }
}

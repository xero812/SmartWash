package com.smartwash.activity;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smartwash.R;
import com.smartwash.model.Bucket;
import com.smartwash.model.Fabric;


public class ListViewFabricAdapter extends ArrayAdapter<Fabric> {

    public ListViewFabricAdapter(Context context, ArrayList<Fabric> fabrics) {
        super(context, 0, fabrics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Fabric fabric = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        }
        // Lookup view for data population
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView material = (TextView) convertView.findViewById(R.id.material);
        TextView color = (TextView) convertView.findViewById(R.id.color);
        // Populate the data into the template view using the data object
        type.setText(fabric.getType());
        material.setText(fabric.getMaterial());
        color.setText(fabric.getColor());
        // Return the completed view to render on screen
        return convertView;
    }
}
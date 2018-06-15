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

public class BucketViewAdapter extends ArrayAdapter<Bucket>
{
    Context mContext;
    int resourceId;
    ArrayList<Bucket> data = new ArrayList<Bucket>();

    public BucketViewAdapter(Context context, int layoutResourceId, ArrayList<Bucket> data)
    {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null)
        {
            final LayoutInflater layoutInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.imgItem = (ImageView) itemView.findViewById(R.id.imgItem);
            holder.txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            itemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) itemView.getTag();
        }

        Bucket item = getItem(position);
        holder.txtItem.setText("Bucket "+ String.valueOf(item.getId()+" ["+item.getFabrics().size()+"]"));

        return itemView;
    }

    static class ViewHolder
    {
        ImageView imgItem;
        TextView txtItem;
    }

}
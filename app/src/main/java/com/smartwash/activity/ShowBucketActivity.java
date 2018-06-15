package com.smartwash.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.smartwash.R;

import com.smartwash.model.Bucket;
import com.smartwash.model.Fabric;
import com.smartwash.model.FabricClassifier;

public class ShowBucketActivity extends Activity implements OnItemClickListener
{
    GridView gridview;
    BucketViewAdapter gridviewAdapter;
    ArrayList<Bucket> data = new ArrayList<Bucket>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbucket);

        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapter(); // Set the Data Adapter
    }

    // Initialize the GUI Components
    private void initView()
    {
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(this);
    }

    // Insert The Data
    private void fillData() {
        FabricClassifier fabricClassifier = new FabricClassifier();
        data.addAll(fabricClassifier.getBuckets());
    }

    // Set the Data Adapter
    private void setDataAdapter()
    {
        gridviewAdapter = new BucketViewAdapter(getApplicationContext(), R.layout.gridview_layout, data);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = "Clicked : " + data.get(position).getId();
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
    }

}


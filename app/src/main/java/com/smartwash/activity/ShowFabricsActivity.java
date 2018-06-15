package com.smartwash.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.smartwash.R;
import com.smartwash.model.Bucket;
import com.smartwash.model.Fabric;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowFabricsActivity extends AppCompatActivity {

    ListView listView;
    ListViewFabricAdapter listViewFabricAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fabrics);
        listView = (ListView)findViewById(R.id.simpleListView);
        Bundle extras = getIntent().getExtras();
        ArrayList<Fabric> data = null;
        if (extras != null) {
            data = (ArrayList<Fabric>) extras.getSerializable("fabricsC");
        }
        listViewFabricAdapter = new ListViewFabricAdapter(getApplicationContext(),  data);
        listView.setAdapter(listViewFabricAdapter);


    }
    // Set the Data Adapter
    private void setDataAdapter(ArrayList<String> values)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
    }

}

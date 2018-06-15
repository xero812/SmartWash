package com.smartwash.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.smartwash.R;
import com.smartwash.model.Bucket;

import java.util.ArrayList;

public class ShowFabricsActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fabrics);
        textView = (TextView)findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<Bucket> data = (ArrayList<Bucket>) extras.getSerializable("fabricsC");
            textView.setText(data.size());
        }


    }
}

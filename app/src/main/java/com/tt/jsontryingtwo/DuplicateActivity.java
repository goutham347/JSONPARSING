package com.tt.jsontryingtwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by vijay on 1/13/2017.
 */

public class DuplicateActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    String[] id;
    String[] name;
    String[] duration;
//    int position;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duplicate_view);

//        String name = getIntent().getStringExtra("id");

         tv1 = (TextView)findViewById(R.id.duplicate_tv1);
        tv2 = (TextView)findViewById(R.id.duplicate_tv2);
        tv3 = (TextView)findViewById(R.id.duplicate_tv3);


        /*Intent i = getIntent();
//        position = i.getExtras().getInt("position");
        id = i.getStringArrayExtra("id");
        name = i.getStringArrayExtra("name");
        duration = i.getStringArrayExtra("duration");

        tv1.setText("id");
        tv2.setText("name");
        tv3.setText("duration");*/

//        tv1.setText(intent.getStringExtra("id"));
//        System.out.println("Getaway");
//        tv2.setText(intent.getStringExtra("name"));
//        tv3.setText(intent.getStringExtra("duration"));

        tv1.setText(""+getIntent().getExtras().getString("id"));
//        System.out.println("getout");
       tv2.setText(""+getIntent().getExtras().getString("name"));
       tv3.setText(""+getIntent().getExtras().getString("duration"));
    }
}

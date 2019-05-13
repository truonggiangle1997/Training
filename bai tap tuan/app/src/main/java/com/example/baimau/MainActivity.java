package com.example.baimau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView1;
    ArrayList<Model> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }
    public void setControl(){
        listView1 = findViewById(R.id.listView1);
    }
    public void setEvent(){
        KhoiTao();
        ModelAdapter adapter = new ModelAdapter(this,R.layout.listview_item_row,data);
        listView1.setAdapter(adapter);
    }

    void KhoiTao(){

        data.add(new Model(R.drawable.facebook,"Facebook"));
        data.add(new Model(R.drawable.linkedin,"Linkedin"));
        data.add(new Model(R.drawable.msn,"MSN"));
        data.add(new Model(R.drawable.skype,"Skype"));
        data.add(new Model(R.drawable.yahoo,"Yahoo"));
    }
}

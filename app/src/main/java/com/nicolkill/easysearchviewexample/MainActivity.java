package com.nicolkill.easysearchviewexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nicolkill.easysearchview.EasySearchView;
import com.nicolkill.easysearchview.SearchElement;
import com.nicolkill.superrecyclerview.SuperRecyclerAdapter;
import com.nicolkill.superrecyclerview.listeners.ClickListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EasySearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Random random = new Random();
        ArrayList<Option> options = new ArrayList<>();
        for (int i = 0;i < random.nextInt(20) + 10;i++) {
            options.add(new Option(random.nextInt(100)));
        }

        SuperRecyclerAdapter<Option> adapter = new SuperRecyclerAdapter<>((RecyclerView) findViewById(R.id.recycler), options);

        adapter.setOnClickListener(new ClickListener<Option>() {
            @Override
            public void onItemSelected(View view, int position, Option element) {
                Snackbar.make(view, "Click option selected: " + element.getOptionName(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        mSearchView = (EasySearchView) findViewById(R.id.search_container);
        mSearchView.setOnItemClickListener(new EasySearchView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, SearchElement object, int position) {
                Snackbar.make(view, "Click in search: " + object.toString(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
        mSearchView.setData(options);
    }

}

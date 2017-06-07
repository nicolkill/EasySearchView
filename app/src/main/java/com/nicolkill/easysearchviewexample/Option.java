package com.nicolkill.easysearchviewexample;

import android.view.View;
import android.widget.TextView;

import com.nicolkill.easysearchview.SearchElement;
import com.nicolkill.superrecyclerview.annotations.BindField;
import com.nicolkill.superrecyclerview.annotations.LayoutResource;

/**
 * Created by nicolkill on 6/7/17.
 */

@LayoutResource(R.layout.row)
public class Option implements SearchElement {

    private static final String TAG = Option.class.getSimpleName();

    private int mOptionNumber;

    public Option(int number) {
        mOptionNumber = number;
    }

    @BindField(id = R.id.option_name)
    public String getOptionName() {
        return "Option " + (mOptionNumber + 1);
    }

    @Override
    public String toString() {
        return getOptionName();
    }

    @Override
    public boolean isSearchPosibillity(String search) {
        return getOptionName().toLowerCase().contains(search.toLowerCase());
    }

    @Override
    public Object getObject() {
        return this;
    }

    @Override
    public int getViewRes() {
        return R.layout.row_search;
    }

    @Override
    public void render(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(getOptionName());
    }
}

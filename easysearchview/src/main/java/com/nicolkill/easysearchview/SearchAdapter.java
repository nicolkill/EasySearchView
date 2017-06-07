package com.nicolkill.easysearchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by nicolkill on 6/7/17.
 */

public class SearchAdapter extends ArrayAdapter<String> {

    private ArrayList<SearchElement> mShowedData;
    private ArrayList<SearchElement> mData;
    private LayoutInflater mInflater;

    public SearchAdapter(final Context context, ArrayList<? extends SearchElement> data) {
        super(context, android.R.layout.activity_list_item);
        mData = new ArrayList<>(data);
        mShowedData = mData;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mShowedData.size();
    }

    @Override
    public String getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        SearchElement element = mShowedData.get(position);
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(element.getViewRes(), null);
        }
        element.render(view);
        return view;
    }

    public ArrayList<SearchElement> getData() {
        return mShowedData;
    }

    public SearchElement getObjectOfIndex(int index) {
        return mShowedData.get(index);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return resultValue.toString();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null) {
                ArrayList<SearchElement> suggestions = new ArrayList<SearchElement>();
                for (SearchElement object : mData) {
                    if (object.isSearchPosibillity(constraint.toString())) {
                        suggestions.add(object);
                    }
                }
                results.values = suggestions;
                results.count = suggestions.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if (results != null && results.count > 0) {
                mShowedData = (ArrayList<SearchElement>) results.values;
            } else {
                mShowedData = mData;
            }
            notifyDataSetChanged();
        }
    };
}

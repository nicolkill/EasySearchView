package com.nicolkill.easysearchview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.nicolkill.easysearchview.R;

import java.util.ArrayList;

/**
 * Created by nicolkill on 4/17/17.
 */

public class EasySearchView extends LinearLayout {

    public static final String TAG = EasySearchView.class.getSimpleName();

    private AutoCompleteTextView mAutoComplete;
    private SearchAdapter mAdapter;
    private OnItemClickListener mListener;

    public EasySearchView(Context context) {
        this(context, null);
    }

    public EasySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int dp8 = paddingDpTransform(context, 8);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setPadding(dp8, dp8, dp8, dp8);
        setBackgroundResource(R.color.gray);
        final View view = LayoutInflater.from(context).inflate(R.layout.view_search, null);
        mAutoComplete = (AutoCompleteTextView) view.findViewById(R.id.search_autocomplete);
        mAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAutoComplete.setText("");
                if (mListener != null) {
                    mListener.onItemClick(view, mAdapter.getObjectOfIndex(position), position);
                }
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoComplete.requestFocus();
            }
        });
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setData(ArrayList<? extends SearchElement> values) {
        mAdapter = new SearchAdapter(getContext(), values);
        mAutoComplete.setAdapter(mAdapter);
        mAutoComplete.clearFocus();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, SearchElement object, int position);
    }

    public static int paddingDpTransform(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dp * density + 0.5f);
    }

}

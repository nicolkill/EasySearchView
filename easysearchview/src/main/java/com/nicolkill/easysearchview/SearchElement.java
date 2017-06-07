package com.nicolkill.easysearchview;

import android.view.View;

/**
 * Created by nicolkill on 6/7/17.
 */

public interface SearchElement {

    boolean isSearchPosibillity(String search);

    Object getObject();

    int getViewRes();

    void render(View view);

}

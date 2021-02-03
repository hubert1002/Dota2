package com.deadgame.dota2.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by levent_j on 16-3-25.
 */
public class SGDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SGDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}

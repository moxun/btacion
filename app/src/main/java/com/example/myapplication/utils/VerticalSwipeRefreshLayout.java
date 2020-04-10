package com.example.myapplication.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by admin on 2019/5/20.
 */

public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    //实际需要滑动的child view
    private View mScrollUpChild;

    public VerticalSwipeRefreshLayout(Context context) {
        super(context);
    }

    public VerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollUpChild(View view) {
        mScrollUpChild = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpChild != null) {
            if (android.os.Build.VERSION.SDK_INT < 14) {
                if (mScrollUpChild instanceof AbsListView) {
                    final AbsListView absListView = (AbsListView) mScrollUpChild;
                    return absListView.getChildCount() > 0
                            && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                            .getTop() < absListView.getPaddingTop());
                } else {
                    return ViewCompat.canScrollVertically(mScrollUpChild, -1) || mScrollUpChild.getScrollY() > 0;
                }
            } else {
                return ViewCompat.canScrollVertically(mScrollUpChild, -1);
            }
        }
        return super.canChildScrollUp();
    }
}
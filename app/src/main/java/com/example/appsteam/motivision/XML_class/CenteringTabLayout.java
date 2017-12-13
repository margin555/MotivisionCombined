package com.example.appsteam.motivision.XML_class;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by appsteam on 06-12-2017.
 */

public class CenteringTabLayout extends TabLayout {


    public CenteringTabLayout(Context context) {
        super(context);
    }

    public CenteringTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CenteringTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View firstTab = ((ViewGroup) getChildAt(0)).getChildAt(0);
        View lastTab = ((ViewGroup) getChildAt(0)).getChildAt(((ViewGroup) getChildAt(0)).getChildCount() - 1);
        if ((firstTab==null)|| (lastTab==null)) {

        }
        else {
            ViewCompat.setPaddingRelative(getChildAt(0), (getWidth() / 2) - (firstTab.getWidth() / 2), 0, (getWidth() / 2) - (lastTab.getWidth() / 2), 0);
        }}



    private int mLastScrollTo;

    public void scrollToTab(int tabIndex, int positionOffset) {
        ViewGroup mTabStrip = null;
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null && selectedChild.getMeasuredWidth() != 0) {

            int targetScrollX = ((positionOffset + selectedChild.getLeft()) - getWidth() / 2) + selectedChild.getWidth() / 2;

            if (targetScrollX != mLastScrollTo) {
                scrollTo(targetScrollX, 0);
                mLastScrollTo = targetScrollX;
            }
        }
    }
}



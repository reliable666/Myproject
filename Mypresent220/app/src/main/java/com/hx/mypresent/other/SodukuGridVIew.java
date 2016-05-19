package com.hx.mypresent.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by dllo on 16/5/18.
 */
public class SodukuGridVIew extends GridView {
    public SodukuGridVIew(Context context) {
        super(context);
    }

    public SodukuGridVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SodukuGridVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}

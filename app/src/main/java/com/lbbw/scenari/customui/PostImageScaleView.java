package com.lbbw.scenari.customui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.parse.ParseImageView;

/**
 * Created by neegbeahreeves on 5/23/17.
 */

public class PostImageScaleView extends ParseImageView {

    public PostImageScaleView(final Context context, final AttributeSet attrs){
        super(context,attrs);

    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final Drawable d = getDrawable();

        if (d != null) {
            int width;
            int height;
            if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
                height = MeasureSpec.getSize(heightMeasureSpec);
                width = (int) Math.ceil(height * (float) d.getIntrinsicWidth() / d.getIntrinsicHeight());
            } else {
                width = MeasureSpec.getSize(widthMeasureSpec);
                height = (int) Math.ceil(width * (float) d.getIntrinsicHeight() / d.getIntrinsicWidth());
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

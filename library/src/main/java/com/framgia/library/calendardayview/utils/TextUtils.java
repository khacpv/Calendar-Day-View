package com.framgia.library.calendardayview.utils;

import android.text.Layout;
import android.text.StaticLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public class TextUtils {

    public static float getTextHeight(TextView textView) {
        return (new StaticLayout(textView.getText(), textView.getPaint(), (int)getTextWidth(textView),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true)).getHeight();
    }

    public static float getTextWidth(TextView textView) {
        RelativeLayout.LayoutParams param =
                (RelativeLayout.LayoutParams) textView.getLayoutParams();
        float measureTextWidth = textView.getPaint().measureText(textView.getText().toString());
        return Math.max(measureTextWidth, param.width)
                + param.getMarginEnd()
                + param.getMarginStart();
    }
}

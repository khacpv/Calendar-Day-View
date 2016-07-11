package com.framgia.library.calendardayview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
public class DayView extends FrameLayout {

    private TextView mTextHour;

    public DayView(Context context) {
        super(context);
        init(null);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_day,this,true);

        mTextHour = (TextView)findViewById(R.id.text_hour);
    }

    public void setText(String text){
        mTextHour.setText(text);
    }

    public float getHourTextWidth(){
        return mTextHour.getPaint().measureText(mTextHour.getText().toString());
    }
}

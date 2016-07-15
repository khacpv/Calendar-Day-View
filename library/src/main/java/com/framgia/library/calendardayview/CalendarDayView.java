package com.framgia.library.calendardayview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.framgia.library.calendardayview.data.IEvent;
import java.util.Calendar;
import java.util.List;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
public class CalendarDayView extends FrameLayout {

    private int mDayHeight = 300;

    private int mEventPaddingLeft = 0;

    private int mHourWidth = 120;

    private int mTimeHeight = 120;

    private int mSeparateHourHeight = 0;

    private LinearLayout mLayoutContainer;

    private FrameLayout mLayoutEvent;

    private FrameLayout mLayoutPopup;

    private EventView.OnEventClickListener mEventClickListener;

    private EventPopup.OnEventPopupClickListener mPopupClickListener;

    public CalendarDayView(Context context) {
        super(context);
        init(null);
    }

    public CalendarDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CalendarDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_day_calendar, this, true);

        mLayoutContainer = (LinearLayout) findViewById(R.id.container);
        mLayoutEvent = (FrameLayout) findViewById(R.id.event_container);
        mLayoutPopup = (FrameLayout) findViewById(R.id.popup_container);

        if(attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarDayView);
            try {
                mEventPaddingLeft = a.getDimensionPixelSize(R.styleable.CalendarDayView_eventPaddingLeft,
                        mEventPaddingLeft);
                mDayHeight = a.getDimensionPixelSize(R.styleable.CalendarDayView_dayHeight,
                        mDayHeight);
            }finally {
                a.recycle();
            }
        }

        addDayViews();
    }

    private void addDayViews() {
        mLayoutContainer.removeAllViews();
        DayView dayView = null;
        for (int i = 0; i < 25; i++) {
            dayView = new DayView(getContext());
            dayView.setText(String.format("%1$2s:00", i));
            mLayoutContainer.addView(dayView);
        }
        mHourWidth = (int) dayView.getHourTextWidth();
        mTimeHeight = (int) dayView.getHourTextHeight();
        mSeparateHourHeight = (int) dayView.getSeparateHeight();
    }

    public void setEvents(List<IEvent> events) {
        mLayoutEvent.removeAllViews();
        mLayoutPopup.removeAllViews();

        for (IEvent event : events) {
            // add event views
            EventView eventView = new EventView(getContext());
            eventView.setEvent(event);
            Rect rect = getEventBound(event);
            eventView.setPosition(rect, -mTimeHeight, mTimeHeight - mSeparateHourHeight * 2);
            eventView.setOnEventClickListener(mEventClickListener);
            mLayoutEvent.addView(eventView, eventView.getLayoutParams());

            // add popup views
            if(event.getPopup() != null) {
                EventPopup popup = new EventPopup(getContext());
                popup.setEvent(event);
                popup.setPosition(rect);
                popup.setOnPopupClickListener(mPopupClickListener);
                popup.hide();
                eventView.addPopupView(popup);
                mLayoutPopup.addView(popup, popup.getLayoutParams());
            }
        }
    }

    private Rect getEventBound(IEvent event) {
        Rect rect = new Rect();
        rect.top = getPositionOfTime(event.getStartTime()) + mTimeHeight / 2 + mSeparateHourHeight;
        rect.bottom = getPositionOfTime(event.getEndTime()) + mTimeHeight / 2 + mSeparateHourHeight;
        rect.left = mHourWidth + mEventPaddingLeft;
        rect.right = getWidth();
        return rect;
    }

    private int getPositionOfTime(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * mDayHeight + minute * mDayHeight / 60;
    }

    public void setOnEventClickListener(EventView.OnEventClickListener listener){
        this.mEventClickListener = listener;
    }

    public void setOnPopupClickListener(EventPopup.OnEventPopupClickListener listener){
        this.mPopupClickListener = listener;
    }
}

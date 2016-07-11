package com.framgia.library.calendardayview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.framgia.library.calendardayview.data.IEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
public class CalendarDayView extends FrameLayout implements EventView.OnEventClickListener {

    private int mDayHeight = 300;

    private int mHourWidth = 120;

    private LinearLayout mLayoutContainer;

    private FrameLayout mLayoutEvent;

    private FrameLayout mLayoutPopup;

    private List<IEvent> mEvents = new ArrayList<>();

    private List<EventView.OnEventClickListener> mEventClickListener = new ArrayList<>();

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

        mDayHeight = getContext().getResources().getDimensionPixelSize(R.dimen.dayHeight);

        addDayViews();
    }

    public void setEvents(List<IEvent> mEvents) {
        this.mEvents = mEvents;
        addEvents(mEvents);
    }

    private void addDayViews() {
        for (int i = 0; i < 25; i++) {
            DayView dayView = new DayView(getContext());
            dayView.setText(String.format("%1$2s:00", i));
            mLayoutContainer.addView(dayView);
        }
        invalidate();
    }

    private void addEvents(List<IEvent> events) {
        mLayoutPopup.removeAllViews();

        for (IEvent event : events) {
            EventView eventView = new EventView(getContext());
            eventView.setEvent(event);
            Rect rect = getEventBound(event);
            eventView.setPosition(rect);
            eventView.setBackgroundColor(event.getColor());
            eventView.setOnEventClickListener(this);
            mLayoutEvent.addView(eventView, eventView.getLayoutParams());

            EventPopup popup = new EventPopup(getContext());
            popup.setEvent(event);
            popup.setPosition(rect);
            popup.hide();
            eventView.addPopupView(popup);
            mLayoutPopup.addView(popup, popup.getLayoutParams());
        }
    }

    private Rect getEventBound(IEvent event) {
        Rect rect = new Rect();
        rect.top = getPositionOfTime(event.getStartTime());
        rect.bottom = getPositionOfTime(event.getEndTime());
        rect.left = mHourWidth;
        rect.right = getWidth();
        return rect;
    }

    private int getPositionOfTime(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * mDayHeight + minute * mDayHeight / 60;
    }

    public void addOnEventClickListener(EventView.OnEventClickListener listener){
        if(listener != null && !mEventClickListener.contains(listener)) {
            this.mEventClickListener.add(listener);
        }
    }

    @Override
    public void onEventClick(EventView view, IEvent data) {
        for(EventView.OnEventClickListener listener : mEventClickListener) {
            listener.onEventClick(view, data);
        }
    }
}

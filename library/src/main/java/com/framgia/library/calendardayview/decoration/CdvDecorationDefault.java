package com.framgia.library.calendardayview.decoration;

import android.content.Context;
import android.graphics.Rect;
import com.framgia.library.calendardayview.DayView;
import com.framgia.library.calendardayview.EventPopup;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;

/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public class CdvDecorationDefault implements CdvDecoration {

    private Context mContext;

    private EventView.OnEventClickListener mEventClickListener;

    private EventPopup.OnEventPopupClickListener mPopupClickListener;

    public CdvDecorationDefault(Context context) {
        this.mContext = context;
    }

    @Override
    public EventView getEventView(IEvent event, Rect eventBound, int hourHeight,
            int seperateHeight) {
        EventView eventView = new EventView(mContext);
        eventView.setEvent(event);
        eventView.setPosition(eventBound, -hourHeight, hourHeight - seperateHeight * 2);
        eventView.setOnEventClickListener(mEventClickListener);
        return eventView;
    }

    @Override
    public EventPopup getEventPopup(IEvent event, EventView eventView, Rect eventBound,
            int hourHeight, int seperateHeight) {
        EventPopup popup = new EventPopup(mContext);
        popup.setEvent(event);
        popup.setPosition(eventBound);
        popup.setOnPopupClickListener(mPopupClickListener);
        popup.hide();
        eventView.addPopupView(popup);
        return popup;
    }

    @Override
    public DayView getDayView(int hour) {
        DayView dayView = new DayView(mContext);
        dayView.setText(String.format("%1$2s:00", hour));
        return dayView;
    }

    public void setOnEventClickListener(EventView.OnEventClickListener listener) {
        this.mEventClickListener = listener;
    }

    public void setOnPopupClickListener(EventPopup.OnEventPopupClickListener listener) {
        this.mPopupClickListener = listener;
    }
}

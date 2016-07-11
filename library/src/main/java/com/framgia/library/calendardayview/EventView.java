package com.framgia.library.calendardayview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.framgia.library.calendardayview.data.IEvent;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
public class EventView extends FrameLayout {

    protected IEvent mEvent;

    protected EventPopup mPopup;

    protected Rect mPosition;

    protected OnEventClickListener mEventClickListener;

    public EventView(Context context) {
        super(context);
        init(null);
    }

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_event, this, true);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEventClickListener!= null){
                    mEventClickListener.onEventClick(EventView.this,getEvent());
                }
            }
        });
    }

    public void setOnEventClickListener(OnEventClickListener listener){
        this.mEventClickListener = listener;
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        throw new UnsupportedOperationException("you should use setOnEventClickListener()");
    }

    public EventPopup getPopup(){
        return mPopup;
    }

    public void addPopupView(EventPopup popup) {
        this.mPopup = popup;
    }

    public IEvent getEvent() {
        return this.mEvent;
    }

    public void setEvent(IEvent event) {
        this.mEvent = event;
    }

    public void setPosition(Rect rect){
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = rect.top;
        params.height = rect.height();
        params.leftMargin = rect.left;
        setLayoutParams(params);
    }

    public interface OnEventClickListener {
        void onEventClick(EventView view, IEvent data);
    }
}

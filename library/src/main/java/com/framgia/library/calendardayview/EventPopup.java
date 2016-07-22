package com.framgia.library.calendardayview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopupEvent;

/**
 * Created by FRAMGIA\pham.van.khac on 08/07/2016.
 */
public class EventPopup extends FrameLayout {

    protected IEvent mEvent;
    protected OnEventPopupClickListener mPopupClickListener;
    protected int mShowDuration = 3000;
    protected TextView mQuote;
    protected CardView mCardView;
    protected ImageView mImvStart;
    protected ImageView mImvEnd;
    protected TextView mTitle;
    protected TextView mDescription;
    protected AlphaAnimation mFadeOutAnim = new AlphaAnimation(1.0f, 0.0f);
    protected Animation.AnimationListener mFadeOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setAlpha(1);
            setVisibility(View.INVISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Runnable mHidePopup = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public EventPopup(Context context) {
        super(context);
        init(null);
    }

    public EventPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EventPopup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_event_popup, this, true);

        mDescription = (TextView) findViewById(R.id.desc);
        mTitle = (TextView) findViewById(R.id.title);
        mImvEnd = (ImageView) findViewById(R.id.imv_end);
        mImvStart = (ImageView) findViewById(R.id.image_start);
        mQuote = (TextView) findViewById(R.id.quote);
        mCardView = (CardView) findViewById(R.id.cardview);

        mQuote.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupClickListener != null) {
                    mPopupClickListener.onQuoteClick(v, mEvent, mEvent.getPopup());
                }
            }
        });

        mCardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupClickListener != null) {
                    mPopupClickListener.onPopupClick(EventPopup.this, mEvent, mEvent.getPopup());
                }
            }
        });
    }

    public void show() {
        setVisibility(View.VISIBLE);
        if (this.mEvent.getPopup().isAutohide()) {
            removeCallbacks(mHidePopup);
            postDelayed(mHidePopup, mShowDuration);
        }
    }

    public void hide() {
        if (mFadeOutAnim.hasStarted() && !mFadeOutAnim.hasEnded()) {
            return;
        }
        mFadeOutAnim = new AlphaAnimation(getAlpha(), 0.0f);
        setVisibility(View.VISIBLE);
        mFadeOutAnim.setAnimationListener(mFadeOutListener);
        mFadeOutAnim.setDuration(200);
        startAnimation(mFadeOutAnim);
    }

    public void setPosition(Rect rect) {
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = rect.top + 2;
        params.leftMargin = rect.left;
        setLayoutParams(params);
    }

    public void setEvent(IEvent event) {
        this.mEvent = event;
        mDescription.setText(String.valueOf(event.getPopup().getDescription()));
        mTitle.setText(String.valueOf(event.getPopup().getTitle()));
        mImvStart.setImageBitmap(event.getPopup().getImageStart());
        mImvEnd.setImageBitmap(event.getPopup().getImageEnd());
        mQuote.setText(String.valueOf(event.getPopup().getQuote()));
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        throw new UnsupportedOperationException("you should use setOnEventClickListener()");
    }

    public void setOnPopupClickListener(OnEventPopupClickListener listener) {
        this.mPopupClickListener = listener;
    }

    public interface OnEventPopupClickListener {
        void onPopupClick(EventPopup view, IEvent event, IPopupEvent data);

        void onQuoteClick(View view, IEvent event, IPopupEvent data);
    }
}

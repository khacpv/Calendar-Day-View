package com.framgia.sample.calendardayview;

import android.graphics.Bitmap;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopupEvent;
import java.util.Calendar;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
public class Event implements IEvent, IPopupEvent {

    private long mId;
    private Calendar mStartTime;
    private Calendar mEndTime;
    private String mName;
    private String mLocation;
    private int mColor;

    // popup
    private Bitmap mImage;
    private String mTitle;
    private String mDescription;
    private String mQuote;

    public Event() {

    }

    public Event(long mId, Calendar mStartTime, Calendar mEndTime, String mName, String mLocation,
            int mColor) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mName = mName;
        this.mLocation = mLocation;
        this.mColor = mColor;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public Calendar getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Calendar startTime) {
        this.mStartTime = startTime;
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Calendar endTime) {
        this.mEndTime = endTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public int getColor() {
        return mColor;
    }

    @Override
    public IPopupEvent getPopup() {
        return this;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setBitmap(Bitmap image) {
        this.mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getQuote() {
        return mQuote;
    }

    @Override
    public Bitmap getImageStart() {
        return getImage();
    }

    @Override
    public Bitmap getImageEnd() {
        return null;
    }

    @Override
    public Boolean isAutohide() {
        return false;
    }

    public void setQuote(String quote) {
        this.mQuote = quote;
    }
}

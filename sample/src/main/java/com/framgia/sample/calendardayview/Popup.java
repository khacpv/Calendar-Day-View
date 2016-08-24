package com.framgia.sample.calendardayview;

import android.graphics.Bitmap;
import com.framgia.library.calendardayview.data.IPopup;
import java.util.Calendar;

/**
 * Created by FRAMGIA\pham.van.khac on 8/24/16.
 */
public class Popup implements IPopup {

    Calendar startTime;
    Calendar endTime;

    Bitmap imageStart;
    Bitmap imageEnd;

    String title;

    String description;

    String quote;

    public Popup() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String getQuote() {
        return quote;
    }

    public void setImageStart(Bitmap imageStart) {
        this.imageStart = imageStart;
    }

    @Override
    public Bitmap getImageStart() {
        return imageStart;
    }

    public void setImageEnd(Bitmap imageEnd) {
        this.imageEnd = imageEnd;
    }

    @Override
    public Bitmap getImageEnd() {
        return imageEnd;
    }

    @Override
    public Boolean isAutohide() {
        return false;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    @Override
    public Calendar getStartTime() {
        return startTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @Override
    public Calendar getEndTime() {
        return endTime;
    }
}

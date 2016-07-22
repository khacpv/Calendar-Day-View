package com.framgia.library.calendardayview.decoration;

import android.graphics.Rect;
import com.framgia.library.calendardayview.DayView;
import com.framgia.library.calendardayview.EventPopup;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;

/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public interface CdvDecoration {

    EventView getEventView(IEvent event, Rect eventBound, int hourHeight, int seperateHeight);

    EventPopup getEventPopup(IEvent event, EventView eventView, Rect eventBound, int hourHeight,
            int seperateHeight);

    DayView getDayView(int hour);
}

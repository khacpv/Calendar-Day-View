package com.framgia.library.calendardayview.decoration;

import android.graphics.Rect;
import com.framgia.library.calendardayview.DayView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;

/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public interface CdvDecoration {

    EventView getEventView(IEvent event, Rect eventBound, int hourHeight, int seperateHeight);

    PopupView getPopupView(IPopup popup, Rect eventBound, int hourHeight, int seperateHeight);

    DayView getDayView(int hour);
}

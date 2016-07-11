package com.framgia.library.calendardayview.data;

import android.graphics.Bitmap;

/**
 * Created by FRAMGIA\pham.van.khac on 11/07/2016.
 */
public interface IPopupEvent {

    String getTitle();

    String getDescription();

    String getQuote();

    Bitmap getImageStart();

    Bitmap getImageEnd();

    Boolean isAutohide();
}

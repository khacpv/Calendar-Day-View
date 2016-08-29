package com.framgia.library.calendardayview.data;

import android.graphics.Bitmap;
import java.util.Calendar;

/**
 * Created by FRAMGIA\pham.van.khac on 11/07/2016.
 */
public interface IPopup extends ITimeDuration {

    String getTitle();

    String getDescription();

    String getQuote();

    String getImageStart();

    String getImageEnd();

    Boolean isAutohide();
}

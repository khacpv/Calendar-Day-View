package com.framgia.sample.calendardayview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CalendarDayView dayView;

    ArrayList<IEvent> events;
    ArrayList<IPopup> popups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayView = (CalendarDayView) findViewById(R.id.calendar);
        dayView.setLimitTime(9, 22);

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnEventClickListener(
            new EventView.OnEventClickListener() {
                @Override
                public void onEventClick(EventView view, IEvent data) {
                    Log.e("TAG", "onEventClick:" + data.getName());
                }

                @Override
                public void onEventViewClick(View view, EventView eventView, IEvent data) {
                    Log.e("TAG", "onEventViewClick:" + data.getName());
                    if (data instanceof Event) {
                        // change event (ex: set event color)
                        dayView.setEvents(events);
                    }
                }
            });

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnPopupClickListener(
            new PopupView.OnEventPopupClickListener() {
                @Override
                public void onPopupClick(PopupView view, IPopup data) {
                    Log.e("TAG", "onPopupClick:" + data.getTitle());
                }

                @Override
                public void onQuoteClick(PopupView view, IPopup data) {
                    Log.e("TAG", "onQuoteClick:" + data.getTitle());
                }

                @Override
                public void onLoadData(PopupView view, ImageView start, ImageView end,
                    IPopup data) {
                    start.setImageResource(R.drawable.avatar);
                }
            });

        events = new ArrayList<>();

        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 11);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event(1, timeStart, timeEnd, "Event", "Hockaido", eventColor);

            events.add(event);
        }

        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor1);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 18);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 20);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event(1, timeStart, timeEnd, "Another event", "Hockaido", eventColor);

            events.add(event);
        }

        popups = new ArrayList<>();

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 12);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 13);
            timeEnd.set(Calendar.MINUTE, 30);

            Popup popup = new Popup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setImageStart("http://sample.com/image.png");
            popup.setTitle("event 1 with title");
            popup.setDescription("Yuong alsdf");
            popups.add(popup);
        }

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 20);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 22);
            timeEnd.set(Calendar.MINUTE, 0);

            Popup popup = new Popup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setImageStart("http://sample.com/image.png");
            popup.setTitle("event 2 with title");
            popup.setDescription("Yuong alsdf");
            popups.add(popup);
        }

        dayView.setEvents(events);
        dayView.setPopups(popups);
    }
}

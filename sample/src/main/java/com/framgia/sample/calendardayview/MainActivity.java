package com.framgia.sample.calendardayview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventPopup;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    CalendarDayView dayView;

    ArrayList<IEvent> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayView = (CalendarDayView)findViewById(R.id.calendar);
        dayView.addOnEventClickListener(new EventView.OnEventClickListener(){
            @Override
            public void onEventClick(EventView view, IEvent data) {
                EventPopup popup = view.getPopup();
                if (popup != null) {
                    popup.show();
                }
            }
        });

        events = new ArrayList<>();
        {
            int eventColor = getResources().getColor(R.color.eventColor1);
            Calendar timeStart = Calendar.getInstance();
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.add(Calendar.HOUR_OF_DAY, 1);
            Event event = new Event(0, timeStart, timeEnd, "event 0", "house", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 0 user request");
            event.setDescription("Yuong des;laskf");
            event.setQuote("my quote test");
            events.add(event);
        }

        {
            int eventColor = getResources().getColor(R.color.eventColor2);
            Calendar timeStart = Calendar.getInstance();
            timeStart.add(Calendar.HOUR_OF_DAY,3);
            timeStart.set(Calendar.MINUTE,0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.add(Calendar.HOUR_OF_DAY, 1);
            Event event = new Event(1, timeStart, timeEnd, "event 1", "home", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 1 with title");
            event.setDescription("Yuong alsdf");
            event.setQuote("my quote test");
            events.add(event);
        }

        {
            int eventColor = getResources().getColor(R.color.eventColor3);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY,3);
            timeStart.set(Calendar.MINUTE,0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.add(Calendar.HOUR_OF_DAY, 2);
            timeEnd.add(Calendar.MINUTE,30);
            Event event = new Event(2, timeStart, timeEnd, "event 1", "home", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 2 this is test");
            event.setDescription("Yuong alsdf");
            event.setQuote("Google map");
            events.add(event);
        }

        {
            int eventColor = getResources().getColor(R.color.eventColor4);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY,8);
            timeStart.set(Calendar.MINUTE,15);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.add(Calendar.HOUR_OF_DAY, 1);
            timeEnd.add(Calendar.MINUTE,30);
            Event event = new Event(3, timeStart, timeEnd, "event 6", "house", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 2 this is test");
            event.setDescription("Yuong alsdf");
            event.setQuote("Google map");
            events.add(event);
        }

        dayView.setEvents(events);
    }
}

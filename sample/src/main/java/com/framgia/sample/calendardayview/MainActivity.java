package com.framgia.sample.calendardayview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventPopup;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopupEvent;
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
        dayView.setOnEventClickListener(new EventView.OnEventClickListener(){
            @Override
            public void onEventClick(EventView view, IEvent data) {
                EventPopup popup = view.getPopup();
                if (popup != null) {
                    popup.show();
                }
            }
        });

        dayView.setOnPopupClickListener(new EventPopup.OnEventPopupClickListener() {
            @Override
            public void onPopupClick(EventPopup view, IEvent event, IPopupEvent data) {
                Log.e("TAG", "onPopupClick:" + event.getName());
            }

            @Override
            public void onQuoteClick(View view, IEvent event, IPopupEvent data) {
                Log.e("TAG", "onQuoteClick:" + event.getName());
            }
        });

        events = new ArrayList<>();

        {
            int eventColor = getResources().getColor(R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY,3);
            timeStart.set(Calendar.MINUTE,30);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 8);
            timeEnd.set(Calendar.MINUTE,30);
            Event event = new Event(1, timeStart, timeEnd, "event 1", "home", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 1 with title");
            event.setDescription("Yuong alsdf");
            event.setQuote("my quote test");
            events.add(event);
        }

        {
            int eventColor = getResources().getColor(R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY,10);
            timeStart.set(Calendar.MINUTE,0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 12);
            timeEnd.set(Calendar.MINUTE,30);
            Event event = new Event(2, timeStart, timeEnd, "event 2", "work", eventColor);
            event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
            event.setTitle("event 2 with title");
            event.setDescription("Click me");
            event.setQuote("Here map");
            events.add(event);
        }


        dayView.setEvents(events);
    }
}

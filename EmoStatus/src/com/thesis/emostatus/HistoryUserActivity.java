package com.thesis.emostatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapters.InfoArrayAdapter;
import adapters.ThreeCompArrayAdapter;
import calendar.ui.CalendarButton;
import calendar.util.CalendarUtil;
import persistance.EmoStatus;
import persistance.HistoryDay;
import persistance.InfoComponent;

import static android.widget.LinearLayout.LayoutParams;

public class HistoryUserActivity extends Fragment {
    private View rootView;
    private ScrollView scrollViewCalendar;
    private LayoutInflater inflater;
    private int actualMonth;
    private int actualYear;
    private AlertDialog alertDatePickerDialog;
    private List<HistoryDay> history;
    private ScrollView sv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = inflater;

        sv = new ScrollView(getActivity());

        EmoStatus app = (EmoStatus)getActivity().getApplicationContext();
        history = app.getHistoryActualUserMonitorized();

        addDaysToListLayout();
        return sv;
    }

    private void addDaysToListLayout() {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        sv.removeAllViews();

        if(history.size() == 0)
            noHistoryFound(ll);
        else
            for(HistoryDay hist : history){
                addDay(ll, hist);
            }


        sv.addView(ll);
    }

    private void addDaysWeekToListLayout() {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        sv.removeAllViews();
        Calendar today = Calendar.getInstance();
        int week = today.get(Calendar.WEEK_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int i = 0;
        for(HistoryDay hist : history){
            Date date = hist.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            if(cal.get(Calendar.MONTH) == month)
                if(cal.get(Calendar.WEEK_OF_MONTH) == week){
                    addDay(ll,hist);
                    i++;
                }
                else
                    break;
            else
                break;
        }

        if(i == 0)
            noHistoryFound(ll);

        sv.addView(ll);

    }

    private void addDay(LinearLayout ll, HistoryDay hist) {
        TextView day_date = new TextView(getActivity());
        day_date.setText(hist.getDay_date());
        day_date.setPadding(15,10,0,10);
        day_date.setTypeface(null, Typeface.BOLD);
        day_date.setBackgroundColor(Color.LTGRAY);
        ll.addView(day_date);

        ListView day = new ListView(getActivity());
        day.setHeaderDividersEnabled(true);
        ThreeCompArrayAdapter adapter = new ThreeCompArrayAdapter(getActivity(),hist.getHistory());
        day.setAdapter(adapter);
        day.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                90*hist.getHistory().size(),1f*hist.getHistory().size()));
        day_date.setVerticalScrollBarEnabled(false);

        day.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogInfo(view);
            }
        });

        ll.addView(day);
    }

    private void noHistoryFound(LinearLayout ll) {
        TextView mssg = new TextView(getActivity());
        mssg.setText("No se encontró historial");
        mssg.setPadding(15, 10, 0, 10);
        mssg.setTextColor(Color.GRAY);
        mssg.setTypeface(null, Typeface.BOLD);
        ll.addView(mssg);
    }

    private void showDialogInfo(View view) {
        TextView title = (TextView)view.findViewById(R.id.title);
        String mood = title.getText().toString();
        if(mood.indexOf("Triste") != -1){
            int init = mood.indexOf("(");
            String percentage = mood.substring(init+1,mood.length()-1);
            showInfoDialog(mood,"El sistema detectó que la persona está triste, con un "
                    + percentage + " de exactitud.");
        }
        else {
            showInfoDialog(mood,"El sistema detectó que la persona se encuentra en estado "+
                    "normal o neutral.");
        }

    }

    public void showInfoDialog(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    public void showDateOptionsDialog(){
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Historial");
        alertDialog.setMessage("Elija cómo ver el historial:");
        ListView list1 = new ListView(getActivity());
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        addDaysToListLayout();
                        alertDialog.dismiss();
                        break;
                    case 1:
                        addDaysWeekToListLayout();
                        alertDialog.dismiss();
                        break;
                    case 2:
                        alertDialog.dismiss();
                        showDatePickerDialog();
                        break;
                }
            }
        });

        List<String> val = new ArrayList<String>();
        val.add("Ver todos");
        val.add("Ver esta semana");
        val.add("Ver día en calendario");

        list1.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, val));
        list1.setPadding(30,5,30,5);
        alertDialog.setView(list1);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    public void showDatePickerDialog(){
        alertDatePickerDialog = new AlertDialog.Builder(getActivity()).create();
        rootView = inflater.inflate(R.layout.calendar_view, alertDatePickerDialog.getListView());

        scrollViewCalendar = (ScrollView) rootView.findViewById(R.id.scrollViewCalendar);
        fillCalendarView();
        setClickListener(rootView.findViewById(R.id.buttonPrevMonth));
        setClickListener(rootView.findViewById(R.id.buttonNextMonth));

        alertDatePickerDialog.setView(rootView);
        alertDatePickerDialog.show();
    }

    private void fillCalendarView(){
        Calendar calendar = Calendar.getInstance();

        LinearLayout calendarMonthsLayout = new LinearLayout(getActivity());
        calendarMonthsLayout.setOrientation(LinearLayout.VERTICAL);
        calendarMonthsLayout.setId(R.id.monthViewL);

        actualMonth = calendar.get(Calendar.MONTH);
        actualYear = calendar.get(Calendar.YEAR);
        calendarMonthsLayout.addView(getMonthLayout(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)));

        rootView.findViewById(R.id.buttonNextMonth).setEnabled(false);
        LinearLayout symbols = (LinearLayout) inflater.inflate(R.layout.symbols_status_layout, null);
        calendarMonthsLayout.addView(symbols);
        scrollViewCalendar.addView(calendarMonthsLayout);
    }


    private LinearLayout getMonthLayout(int month, int year){
        LinearLayout monthsLayout = (LinearLayout) inflater.inflate(R.layout.month_view, null);
        LinearLayout monthDayButtonsContainer = (LinearLayout) monthsLayout.findViewById(R.id.monthDayButtonsContainer);
        LinearLayout weekDaysLabelsLayout = (LinearLayout) monthsLayout.findViewById(R.id.weekDaysLabelsLayout);
        TextView textViewMonthName = (TextView) monthsLayout.findViewById(R.id.textViewMonthName);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(calendar.getTime());
        Log.d("Developer","Calendar before: "+calendar.getTime().before(calendarEnd.getTime()));
        calendarEnd.add(Calendar.MONTH, 1);
        textViewMonthName.setText(CalendarUtil.getMonthNameFromLocale(month)+ " "+year);
        setWeekDaysLabels(weekDaysLabelsLayout);
        LinearLayout weekLayout = getWeekLinearLayout();
        for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
            weekLayout.addView(getEmptyButton());
        }
        while (calendar.getTime().before(calendarEnd.getTime())) {
            CalendarButton calendarButton = new CalendarButton(getActivity(), calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            setClickListener(calendarButton);
            if(CalendarUtil.isDateBeforeToday(calendar) || CalendarUtil.isTodayInCalendar(calendar)){
                HistoryDay hist = hasHistory(calendar);
                if(hist != null){
                    calendarButton.setEnabled(true);
                    calendarButton.setHist(hist);
                    if(hist.wasSad())
                        calendarButton.setBackgroundResource(R.drawable.selectable_item_sad_background);
                    else
                        calendarButton.setBackgroundResource(R.drawable.selectable_item_background);
                }
                else {
                    calendarButton.setEnabled(false);
                    calendarButton.setBackgroundResource(R.drawable.selectable_item_background);
                }
            }
            if(CalendarUtil.isDateAfterToday(calendar)){
                calendarButton.setEnabled(false);
                calendarButton.setBackgroundResource(R.drawable.selectable_item_background);
            }

            weekLayout.addView(calendarButton);
            if(calendar.get(Calendar.DAY_OF_WEEK) == 7){
                monthDayButtonsContainer.addView(weekLayout);
                weekLayout = getWeekLinearLayout();
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        if(calendar.get(Calendar.DAY_OF_WEEK) != 1)
            for (int i = calendar.get(Calendar.DAY_OF_WEEK); i < 8; i++) {
                weekLayout.addView(getEmptyButton());
            }

        monthDayButtonsContainer.addView(weekLayout);

        monthsLayout.setId(R.id.monthView);

        return monthsLayout;
    }

    private HistoryDay hasHistory(Calendar calendar) {
        Date date;
        Calendar cal = Calendar.getInstance();
        for(HistoryDay hist : history){
            date = hist.getDate();
            cal.setTime(date);
            if(cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
                if(cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
                    if(cal.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH))
                        return  hist;
                    else if(cal.get(Calendar.MONTH) < calendar.get(Calendar.MONTH))
                        return null;
                else if(cal.get(Calendar.MONTH) < calendar.get(Calendar.MONTH))
                    return null;

            else if(cal.get(Calendar.YEAR) < calendar.get(Calendar.YEAR))
                    return null;
        }
        return null;
    }

    private void setWeekDaysLabels(LinearLayout weekDaysLabelsLayout){
        TextView sunday, monday, tuesday, wednesday, thursday, friday, saturday;
        sunday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelSunday);
        sunday.setText(CalendarUtil.getShortWeekdayName(1));
        monday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelMonday);
        monday.setText(CalendarUtil.getShortWeekdayName(2));
        tuesday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelTuesday);
        tuesday.setText(CalendarUtil.getShortWeekdayName(3));
        wednesday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelWednesday);
        wednesday.setText(CalendarUtil.getShortWeekdayName(4));
        thursday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelThursday);
        thursday.setText(CalendarUtil.getShortWeekdayName(5));
        friday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelFriday);
        friday.setText(CalendarUtil.getShortWeekdayName(6));
        saturday = (TextView) weekDaysLabelsLayout.findViewById(R.id.weekDayLabelSaturday);
        saturday.setText(CalendarUtil.getShortWeekdayName(7));
    }

    private LinearLayout getWeekLinearLayout(){
        LinearLayout weekLayout = new LinearLayout(getActivity());
        weekLayout.setOrientation(LinearLayout.HORIZONTAL);
        weekLayout.setBackgroundColor(Color.WHITE);
        weekLayout.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        weekLayout.setLayoutParams(params);
        return  weekLayout;
    }

    public void setClickListener(View view) {

        switch (view.getId()){
            case R.id.buttonPrevMonth:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        LinearLayout l = (LinearLayout)scrollViewCalendar.findViewById(R.id.monthViewL);
                        l.removeView(l.findViewById(R.id.monthView));
                        l.removeView(l.findViewById(R.id.symbols_layout));
                        actualMonth = actualMonth - 1;
                        if(actualMonth == -1){
                            actualYear --;
                            actualMonth = 11;
                        }
                        l.addView(getMonthLayout(actualMonth, actualYear));
                        LinearLayout symbols = (LinearLayout) inflater.inflate(R.layout.symbols_status_layout, null);
                        l.addView(symbols);
                        if(actualMonth < calendar.get(Calendar.MONTH))
                            rootView.findViewById(R.id.buttonNextMonth).setEnabled(true);
                    }
                });
                break;

            case R.id.buttonNextMonth:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        LinearLayout l = (LinearLayout)scrollViewCalendar.findViewById(R.id.monthViewL);
                        l.removeView(l.findViewById(R.id.monthView));
                        l.removeView(l.findViewById(R.id.symbols_layout));
                        actualMonth = (actualMonth + 1) % 12;
                        if(actualMonth == 0)
                            actualYear ++;
                        l.addView(getMonthLayout(actualMonth, actualYear));
                        LinearLayout symbols = (LinearLayout) inflater.inflate(R.layout.symbols_status_layout, null);
                        l.addView(symbols);
                        if(actualMonth >= calendar.get(Calendar.MONTH) && actualYear == calendar.get(Calendar.YEAR) )
                            rootView.findViewById(R.id.buttonNextMonth).setEnabled(false);
                    }
                });
                break;

            default:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        CalendarButton calendarButton = ((CalendarButton) view);
                        calendar.set(calendarButton.getYear(), calendarButton.getMonth(), calendarButton.getDay());
                        alertDatePickerDialog.dismiss();
                        setDayHist(calendarButton.getHist());
                    }
                });
                break;

        }
    }

    private void setDayHist(HistoryDay hist) {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        sv.removeAllViews();

        addDay(ll, hist);
        sv.addView(ll);
    }


    private Button getEmptyButton(){
        Button button = new Button(getActivity());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        button.setLayoutParams(param);
        button.setEnabled(false);
        button.setBackgroundResource(R.drawable.selectable_item_background);
        return button;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.calendar:
                Log.i("calendar","calendar");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
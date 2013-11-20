package com.thesis.emostatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import adapters.ThreeCompArrayAdapter;
import persistance.EmoStatus;
import persistance.HistoryDay;

import static android.widget.LinearLayout.LayoutParams;

public class HistoryUserActivity extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView sv = new ScrollView(getActivity());
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        addDaysToListLayout(ll);
        sv.addView(ll);
        return sv;
    }

    private void addDaysToListLayout(LinearLayout ll) {
        EmoStatus app = (EmoStatus)getActivity().getApplicationContext();
        List<HistoryDay> history = app.getHistoryActualUserMonitorized();

        for(HistoryDay hist : history){
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

    public void showDatePickerDialog(){

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
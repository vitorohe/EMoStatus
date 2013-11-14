package com.thesis.emostatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;
import persistance.InfoComponent;
import persistance.OptionComponent;
import persistance.OptionInfoComponent;

public class AlertsUserActivity extends Fragment {
        LayoutInflater inflaterA;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflaterA = inflater;
        ScrollView sv = new ScrollView(getActivity());
        sv.setFillViewport(true);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        addOptionsToListLayout(ll);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        sv.addView(ll);
        return sv;
    }

    private void addOptionsToListLayout(LinearLayout ll) {
        EmoStatus app = (EmoStatus)getActivity().getApplicationContext();
        boolean email_enabled = true;
        boolean phone_enabled = false;
        boolean emonot_enabled = true;
        //----- SMS -----
        List<OptionComponent> opts1 = new ArrayList<OptionComponent>();
        opts1.add(new OptionComponent("SMS", R.drawable.icon_sms, phone_enabled));

        ListView options1 = new ListView(getActivity());
        OptionArrayAdapter adapter1 = new OptionArrayAdapter(getActivity(),opts1);
        options1.setAdapter(adapter1);
        options1.setFooterDividersEnabled(true);
        ll.addView(options1);

        List<InfoComponent> smsOptsInfo = new ArrayList<InfoComponent>();
        smsOptsInfo.add(new InfoComponent("95115226",R.id.alert_sms,phone_enabled));

        ListView smsOpt = new ListView(getActivity());
        InfoArrayAdapter adapter11 = new InfoArrayAdapter(getActivity(),smsOptsInfo);
        smsOpt.setAdapter(adapter11);
        smsOpt.setEnabled(phone_enabled);
        smsOpt.setId(R.id.list_alert_sms);
        smsOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog("SMS", view);
            }
        });

        ll.addView(smsOpt);

        //----- EMAIL -----
        List<OptionComponent> opts2 = new ArrayList<OptionComponent>();
        opts2.add(new OptionComponent("Correo electrónico", R.drawable.icon_email, email_enabled));

        ListView options2 = new ListView(getActivity());
        OptionArrayAdapter adapter2 = new OptionArrayAdapter(getActivity(),opts2);
        options2.setAdapter(adapter2);
        ll.addView(options2);

        List<InfoComponent> emailOptsInfo = new ArrayList<InfoComponent>();
        emailOptsInfo.add(new InfoComponent("vitorohe@gmail.com",R.id.alert_email, email_enabled));

        ListView emailOpt = new ListView(getActivity());
        InfoArrayAdapter adapter21 = new InfoArrayAdapter(getActivity(),emailOptsInfo);
        emailOpt.setAdapter(adapter21);
        emailOpt.setEnabled(email_enabled);
        emailOpt.setId(R.id.list_alert_email);
        emailOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog("EMAIL", view);
            }
        });

        ll.addView(emailOpt);


        //----- EmoStatus -----
        List<OptionComponent> opts3 = new ArrayList<OptionComponent>();
        opts3.add(new OptionComponent("EmoStatus notificación", R.drawable.icon_emonot, emonot_enabled));

        ListView options3 = new ListView(getActivity());
        OptionArrayAdapter adapter3 = new OptionArrayAdapter(getActivity(),opts3);
        options3.setAdapter(adapter3);
        ll.addView(options3);
    }

    private void showDialog(String opt, final View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        if(opt.equals("SMS"))
            alertDialog.setTitle("Número de teléfono");
        else
            alertDialog.setTitle("Correo electrónico");

        final EditText texfield = new EditText(getActivity());
        if(opt.equals("SMS"))
            texfield.setInputType(InputType.TYPE_CLASS_PHONE);
        else
            texfield.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        alertDialog.setView(texfield);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                TextView title = (TextView)view.findViewById(R.id.title);
                if(texfield.getText().length() != 0)
                    title.setText(texfield.getText());
                else{
                    Toast.makeText(getActivity(), "Campo vacío, los cambios no se guardaron",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.show();

    }
}
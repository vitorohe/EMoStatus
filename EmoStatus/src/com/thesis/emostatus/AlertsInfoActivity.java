package com.thesis.emostatus;

/**
 * Created by vito on 25-11-13.
 */

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlertsInfoActivity extends ExpandableListActivity {
    private static final String NAME = "NAME";
    private static final String IS_EVEN = "IS_EVEN";

    private ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();

        /*    SMS    */

        Map<String, String> curGroupMap1 = new HashMap<String, String>();
        groupData.add(curGroupMap1);
        curGroupMap1.put(NAME, "SMS");

        List<Map<String, String>> children1 = new ArrayList<Map<String, String>>();

        Map<String, String> curChildMap11 = new HashMap<String, String>();
        children1.add(curChildMap11);
        curChildMap11.put(IS_EVEN, "La aplicación enviará un SMS al número de teléfono especificado.");


        Map<String, String> curChildMap12 = new HashMap<String, String>();
        children1.add(curChildMap12);
        curChildMap12.put(IS_EVEN, "El costo del envío del SMS es cargado a tu número telefónico.");
        childData.add(children1);

        /*    EMAIL    */

        Map<String, String> curGroupMap2 = new HashMap<String, String>();
        groupData.add(curGroupMap2);
        curGroupMap2.put(NAME, "Correo Electrónico");

        List<Map<String, String>> children2 = new ArrayList<Map<String, String>>();

        Map<String, String> curChildMap21 = new HashMap<String, String>();
        children2.add(curChildMap21);
        curChildMap21.put(IS_EVEN, "La aplicación enviará un correo a la dirección de correo especificada.");
        childData.add(children2);

        /*    NOTIFICATION    */

        Map<String, String> curGroupMap3 = new HashMap<String, String>();
        groupData.add(curGroupMap3);
        curGroupMap3.put(NAME, "EmoStatus Notificación");

        List<Map<String, String>> children3 = new ArrayList<Map<String, String>>();

        Map<String, String> curChildMap31 = new HashMap<String, String>();
        children3.add(curChildMap31);
        curChildMap31.put(IS_EVEN, "La aplicación generará una notificación en el teléfono.");
        childData.add(children3);
        // Set up our adapter
        mAdapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { NAME},
                new int[] { android.R.id.text1},
                childData,
                R.layout.info_info,
                new String[] {IS_EVEN },
                new int[] { R.id.title}
        );
        setListAdapter(mAdapter);
    }

}

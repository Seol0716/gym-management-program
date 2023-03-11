package com.example.iotweight;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Vector;


public class mainfrag extends Fragment {
    test mainActivity;
    public void onAttach(Context context) {

        super.onAttach(context);
        mainActivity = (test) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainfrag, container, false);
        String id = getArguments().getString("id");
        TextView weight = (TextView) v.findViewById(R.id.wei_value);
        TextView height = (TextView) v.findViewById(R.id.ki_value);
        TextView date = (TextView) v.findViewById(R.id.pro);
        TextView fp = (TextView) v.findViewById(R.id.fingerprint_value);
        TextView bmiv = (TextView) v.findViewById(R.id.bmi_value);
        Button rfbutton = (Button) v.findViewById(R.id.pre_button);
        rfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent(getContext(), test.class);
                re.putExtra("id", id);
                startActivity(re);
                mainActivity.finish();
            }
        });
        Vector<String> wh = getwh(id);
        String w = wh.get(0);
        String h = wh.get(1);
        String d = wh.get(2);
        String f = wh.get(3);
        weight.setText(w + "kg");
        height.setText(h  + "cm");
        date.setText(d);
        fp.setText(f);
        if(!w.equals("없음") && !h.equals("없음")){
            double bhei = Double.parseDouble(h)/100;
            double bmi = Math.round((Double.parseDouble(w) / (bhei*bhei)) * 100) / 100.0;
            bmiv.setText(Double.toString(bmi));
        }


        return v;

    }
    public Vector<String> getwh(String id){
        Vector<String> v = new Vector<String>();
        try{
            Registermainfrag ra = new Registermainfrag();
            String result = ra.execute(id).get();
            JSONObject json = new JSONObject(result);
            JSONArray jArr = json.getJSONArray("List");
            json = jArr.getJSONObject(0);
            String sc = json.getString("result");
            json = jArr.getJSONObject(1);
            String weight = json.getString("weight");
            json = jArr.getJSONObject(2);
            String height = json.getString("height");
            json = jArr.getJSONObject(3);
            String date = json.getString("date");
            json = jArr.getJSONObject(4);
            String fingerprint = json.getString("fingerprint");
            if(sc.equals("성공")){
                v.add(weight);
                v.add(height);
                v.add(date);
                v.add(fingerprint);
            }
            else{
                v.add("없음");
                v.add("없음");
                v.add("없음");
                v.add("없음");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            v.add("실패");
            return v;
        }
        return v;
    }


}
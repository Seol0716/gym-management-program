package com.example.iotweight;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public calfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static calfrag newInstance(String param1, String param2) {
        calfrag fragment = new calfrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calfrag, container, false);
        String id = getArguments().getString("id");
        String height = getArguments().getString("height");
        CalendarView cv = (CalendarView) v.findViewById(R.id.cal);
        TextView weightvalue = (TextView) v.findViewById(R.id.wv);
        TextView bmi = (TextView) v.findViewById(R.id.bmiv);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        String date = Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(day);
        String weight = getweight(id, date);
        if(weight.equals("없음")){
            weightvalue.setText("--");
            bmi.setText("--");
        }
        else {
            weightvalue.setText(weight + "kg");
            double bh = Double.parseDouble(height)/100;
            double dbmi = Math.round((Double.parseDouble(weight) / (bh*bh)) * 100) / 100.0;
            bmi.setText(Double.toString(dbmi));

        }

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(dayOfMonth);
                String weight = getweight(id, date);
                if(weight.equals("없음")){
                    weightvalue.setText("--");
                    bmi.setText("--");
                }
                else {
                    weightvalue.setText(weight + "kg");
                    double bh = Double.parseDouble(height)/100;
                    double dbmi = Math.round((Double.parseDouble(weight) / (bh*bh)) * 100) / 100.0;
                    bmi.setText(Double.toString(dbmi));

                }
            }
        });
        return v;
    }
    public String getweight(String id, String date){
        String weight = "";
        try{
            RegisterCal ra = new RegisterCal();
            weight = ra.execute(id, date).get();
        }
        catch(Exception e){
            e.printStackTrace();
            return weight;
        }
        return weight;
    }

}
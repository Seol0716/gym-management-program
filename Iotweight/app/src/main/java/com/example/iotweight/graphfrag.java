package com.example.iotweight;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link graphfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class graphfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public graphfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment graphfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static graphfrag newInstance(String param1, String param2) {
        graphfrag fragment = new graphfrag();
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
        View v = inflater.inflate(R.layout.graphfrag, container, false);
        String id = getArguments().getString("id");
        LineChart chart = (LineChart) v.findViewById(R.id.fragment_bluetooth_chat_linechart);

        ArrayList<Entry> values = new ArrayList<>();
        Vector<String[]> dayweight = getdayweight(id);
        Log.i("aa", dayweight.get(0)[0]);
        for (int i = 0; i < dayweight.size(); i++) {
            float weight = Float.parseFloat(dayweight.get(i)[1]);
            values.add(new Entry(i, weight));
        }


        LineDataSet set1 = new LineDataSet(values, "DataSet 1");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String sValue = String.valueOf((int)value + "$");
                return sValue;
            }
        });
        // black lines and points
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String sValue = dayweight.get((int)value)[0];
                return sValue;
            }
        });
        set1.setColor(Color.BLUE);
        set1.setCircleColor(Color.RED);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        // set data
        chart.setData(data);
        return v;
    }

    public Vector<String[]> getdayweight(String id){
        Vector<String[]> v = new Vector<String[]>();
        try{
            RegisterRequest ra = new RegisterRequest();
            String result = ra.execute(id).get();
            Log.i("reuslt", result);
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jArr = jsonObject.getJSONArray("List");
            Log.i("reuslt", result);
            for(int i=0; i<jArr.length(); i++) {
                JSONObject jsonObject2 = jArr.getJSONObject(i);
                String day = jsonObject2.getString("day");
                String weight = jsonObject2.getString("weight");
                String [] s = new String[2];
                s[0] = day;
                s[1] = weight;
                v.add(s);
            }
        }
        catch(Exception e){

        }
        return v;
    }
}
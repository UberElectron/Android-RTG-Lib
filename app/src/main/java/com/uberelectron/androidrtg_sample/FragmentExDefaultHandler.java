package com.uberelectron.androidrtg_sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.uberelectron.androidrtg.RTG_Surface;
import com.uberelectron.androidrtg_sample.RTG.MyApp;

public class FragmentExDefaultHandler extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_example, container, false);

        initComponents(v);

        return v;
    }

    private void initComponents(View v)
    {
        RTG_Surface s = v.findViewById(R.id.mySurface);
        s.setApp(MyApp.class);

        TextView titleText = v.findViewById(R.id.ActSampleTitleText);
        titleText.setText(R.string.exFragExDefaultHandlerTitle);

        Button btBack = v.findViewById(R.id.btEx_Back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mng = getParentFragmentManager();
                mng.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerView, FragmentList.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}

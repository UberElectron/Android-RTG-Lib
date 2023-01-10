package com.uberelectron.androidrtg_sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.uberelectron.androidrtg.RTG_Surface;
import com.uberelectron.androidrtg_sample.RTG.MyApp;
import com.uberelectron.androidrtg_sample.RTG.MyHandler;

public class FragmentExCustomHandler extends Fragment
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
        RTG_Surface mySurface = v.findViewById(R.id.mySurface);
        mySurface.setApp(MyApp.class);
        mySurface.setHandlerClass(MyHandler.class);

        mySurface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyHandler h = (MyHandler) mySurface.getThread().getHandler();
                h.sendTouch();
                return true;
            }
        });

        TextView titleText = v.findViewById(R.id.ActSampleTitleText);
        titleText.setText(R.string.exFragExCustomHandlerTitle);

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

package com.uberelectron.androidrtg_sample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentList extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        initComponents(v);

        return v;
    }

    private void initComponents(View v)
    {
        Button btBack = v.findViewById(R.id.btFragList_Back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });

        Button btEx1 = v.findViewById(R.id.btFragList_Ex1);
        btEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(FragmentExDefaultHandler.class);
            }
        });

        Button btEx2 = v.findViewById(R.id.btFragList_Ex2);
        btEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(FragmentExCustomHandler.class);
            }
        });
    }

    private void changeFragment(Class<?extends Fragment> frag_class)
    {
        FragmentManager mng = getParentFragmentManager();
        mng.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, frag_class, null)
                .addToBackStack(null)
                .commit();
    }
}
package com.rubik.apppractise3_volleysimple.app.main.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.rubik.apppractise3_volleysimple.R;
import com.rubik.apppractise3_volleysimple.app.main.adapters.MyRecycleHistorycAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HystoricFragment extends Fragment {

    public static MyRecycleHistorycAdapter myAdapter;


    public HystoricFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);

        iniRecycleView(recyclerView);
            // Inflate the layout for this fragment
        return viewGroup;
    }

    private void iniRecycleView(RecyclerView recyclerView) {

        myAdapter = new MyRecycleHistorycAdapter(); //, getActivity()
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, GridLayout.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

}

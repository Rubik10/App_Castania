package com.rubik.apppractise3_volleysimple.app.main.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.rubik.apppractise3_volleysimple.Activity.DetailActivity;
import com.rubik.apppractise3_volleysimple.R;
import com.rubik.apppractise3_volleysimple.app.main.RecycleItemClickListener;
import com.rubik.apppractise3_volleysimple.app.main.RequestVolley;
import com.rubik.apppractise3_volleysimple.app.main.UploadHistoy;
import com.rubik.apppractise3_volleysimple.app.main.adapters.MyRecycleProducsAdapter;
import com.rubik.apppractise3_volleysimple.model.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    public static List<Product> listProducts = new ArrayList<>();

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
        iniRecyclerView(recyclerView);

        return viewGroup;
    }

    private void iniRecyclerView(RecyclerView recyclerView) {

        MyRecycleProducsAdapter myAdapter = new MyRecycleProducsAdapter(getActivity()); //, listProducts
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayout.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);


            // When the user selects the histotical tab
        recyclerView.addOnItemTouchListener(
                new RecycleItemClickListener(getActivity().getApplicationContext(), new RecycleItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        try {
                            String url = listProducts.get(position).getImageURL();
                            String tittle = listProducts.get(position).getTitulo();
                            CharSequence fecha = (DateFormat.format("dd-MM-yyyy HH:mm:ss", new java.util.Date()).toString());

                                // Insert into db new historic registry
                            UploadHistoy.uploadHistoryc(getContext(),tittle,url, view);
                                // Open the detail activity
                            IntentDetail(position);
                                // Manually add the new selected product for the history tab
                            RequestVolley.listSelected.add(new Product(url,tittle, Timestamp.valueOf(fecha.toString())));
                                // Update de the adapter of the recycleView (history tab)
                            HystoricFragment.myAdapter.notifyDataSetChanged();


                        } catch (Exception e) { e.printStackTrace(); }
                    }
                })
        );

        myAdapter.notifyDataSetChanged();
    }

    private void IntentDetail(int pos) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
             //Put data
        intent.putExtra("Title",listProducts.get(pos).getTitulo());
        intent.putExtra("Precio",listProducts.get(pos).getPvp());
        intent.putExtra("Unidades",listProducts.get(pos).getUnidades());
        intent.putExtra("Descripcion",listProducts.get(pos).getDescripcion());
        intent.putExtra("Imagen",listProducts.get(pos).getImageURL() );

        getContext().startActivity(intent);
    }


}

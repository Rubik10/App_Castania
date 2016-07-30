package com.rubik.apppractise3_volleysimple;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rubik.apppractise3_volleysimple.app.main.RequestVolley;
import com.rubik.apppractise3_volleysimple.app.main.adapters.ViewPagerAdapter;
import com.rubik.apppractise3_volleysimple.app.main.fragments.HystoricFragment;
import com.rubik.apppractise3_volleysimple.app.main.fragments.ProductFragment;

/*
    FALTA (ENTRE 1000 COSAS) CREAR UN BROACASTRECEIVER QUE COMPRUEBE TOO EL RATO LA CONXEXION, PARA CUANDO NO
    HAYA CARGAR LA INFO DE LA CACHE DE VOLLEY
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        initControls();
    }

    private void initControls() {

        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void initViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment(), "PRODUCTS");
        adapter.addFragment(new HystoricFragment(), "HISTORY");
        viewPager.setAdapter(adapter);

            // When the user selects the histotical tab -> update the results
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {    // histocial tab
                        Log.d(TAG,"TAB Historico seleccioanda");
                   RequestVolley.JSONArrayRequest(context);
                  // HystoricFragment.myAdapter.notifyDataSetChanged();   //Update adapter
                }
               adapter.notifyDataSetChanged();
            }
            @Override public void onPageSelected(int position) {}
            @Override public void onPageScrollStateChanged(int state) {}
        });
    }

}

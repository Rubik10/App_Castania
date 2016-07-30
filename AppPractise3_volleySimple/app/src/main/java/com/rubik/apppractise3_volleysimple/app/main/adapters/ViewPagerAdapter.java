package com.rubik.apppractise3_volleysimple.app.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rubik on 29/7/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragments = new ArrayList<>();
    private final List<String> listTitleFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    public void addFragment(Fragment fragment, String title) {
        listFragments.add(fragment);
        listTitleFragments.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitleFragments.get(position);
    }
}


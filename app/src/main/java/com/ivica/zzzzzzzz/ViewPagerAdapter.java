package com.ivica.zzzzzzzz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ivica on 11/25/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"MPlayer","Koraci"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                fragment_a fragmentA = new fragment_a();
                return fragmentA;

            case 1:
                fragment_b fragmentB = new fragment_b();
                return  fragmentB;

        }




        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position] ;
    }


}

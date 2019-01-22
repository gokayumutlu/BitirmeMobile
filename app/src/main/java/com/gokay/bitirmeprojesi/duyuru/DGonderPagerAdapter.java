package com.gokay.bitirmeprojesi.duyuru;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DGonderPagerAdapter extends FragmentPagerAdapter {

    public DGonderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                SinifDFragment sinifDFragment=new SinifDFragment();
                return sinifDFragment;

            case 1:
                VeliDFragment veliDFragment=new VeliDFragment();
                return veliDFragment;

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Sınıf";

            case 1:
                return "Veli";

            default: return null;
        }
    }
}

package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home_Fragment();
            case 1:
                return new Sports_Fragment();
            case 2:
                return new Health_Fragment();
            case 3:
                return new Science_Fragment();
            case 4:
                return new Entertainment_Fragment();
            case 5:
                return new Technology_Fragment();
            default:
                return null; // Return a default fragment if the position is invalid
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

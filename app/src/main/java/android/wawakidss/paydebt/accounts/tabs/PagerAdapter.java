package android.wawakidss.paydebt.accounts.tabs;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private static final String TAG = "PagerAdapter";

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "IOweToFragment");
                return new IOweToFragment();

            case 1:
                Log.d(TAG, "IOweToFragment");
                return new OweToMeFragment();

            default:
                return null;
        }
    }

    public int getCount() {
        return numOfTabs;
    }
}

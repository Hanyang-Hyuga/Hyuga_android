package hanyang.smash.hyuga;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JEEWOOYOON on 2016. 1. 25..
 */
public class TapPageAdpater extends FragmentStatePagerAdapter{
    public TapPageAdpater (FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //Fragement for Android Tab
                return new sub1();
            case 1:
                //Fragment for Ios Tab
                return new sub2();
            case 2:
                //Fragment for Windows Tab
                return new sub3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

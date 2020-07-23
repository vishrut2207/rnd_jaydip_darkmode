package ui.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ui.fragment.FragmentTab_2;
import ui.fragment.FragmentTab_3;
import ui.fragment.FragmentTab_4;
import ui.fragment.FragmentTab_5;
import ui.fragment.FragmentTab_7;
import ui.fragment.FragmnetTab_1;
import ui.fragment.FragmnetTab_6;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    private int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmnetTab_1 f1 = new FragmnetTab_1();
                return f1;
            case 1:
                FragmentTab_2 f2 = new FragmentTab_2();
                return f2;
            case 2:
                FragmentTab_3 f3 = new FragmentTab_3();
                return f3;
            case 3:
                FragmentTab_4 f4 = new FragmentTab_4();
                return f4;
            case 4:
                FragmentTab_5 f5 = new FragmentTab_5();
                return f5;
            case 5:
                FragmnetTab_6 f6 = new FragmnetTab_6();
                return f6;
            case 6:
                FragmentTab_7 f7 = new FragmentTab_7();
                return f7;
            case 7:
                FragmentTab_7 f8 = new FragmentTab_7();
                return f8;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
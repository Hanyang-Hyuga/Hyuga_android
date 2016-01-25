package hanyang.smash.hyuga;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
//    CustomAdapter mAdapter;
//    ListView listView;
//    Elements titles;
//    Elements thumbnails;
//    Document doc;
//    ProgressDialog mProgressDialog;
//    android.support.v4.view.ViewPager Tab;
    TabPagerAdapter TabAdapter;
//    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        ViewPager Tab = (ViewPager) findViewById(R.id.viewpager);
        Tab.setAdapter(TabAdapter);
    }
}

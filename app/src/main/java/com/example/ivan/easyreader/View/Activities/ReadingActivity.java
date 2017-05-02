package com.example.ivan.easyreader.View.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.View.Adapters.MyFragmentPagerAdapter;
import com.example.ivan.easyreader.View.Fragments.PageFragment;


public class ReadingActivity extends FragmentActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        defineWindowDensity();
        defineLineHeight();
    }

    private void defineLineHeight() {
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.measure(0, 0);       //must call measure!
        PageFragment.setLineHeight(textView.getMeasuredHeight());
    }

    private void defineWindowDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        PageFragment.setWindowHeigth(metrics.heightPixels);
        PageFragment.setWindowWidth(metrics.widthPixels);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.clearModelComponent();
    }
}
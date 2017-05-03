package com.example.ivan.easyreader.View.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.Utils.RxBus;
import com.example.ivan.easyreader.View.Adapters.MyFragmentPagerAdapter;
import com.example.ivan.easyreader.View.Fragments.PageFragment;

import javax.inject.Inject;


public class ReadingActivity extends FragmentActivity{

    @Inject
    RxBus rxBus;
    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reading);
        App.getComponent().injectReadingActivity(this);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        defineWindowDensity();
        defineLineHeight();
        rxBus.filteredObservable(Boolean.class)
                .subscribe(isEndOfBook -> {if(!isEndOfBook.booleanValue())
            pagerAdapter.increasePageCount();
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    private void defineLineHeight() {
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.measure(0, 0);
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
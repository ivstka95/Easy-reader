package com.example.ivan.easyreader.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.easyreader.Presenter.Constants;
import com.example.ivan.easyreader.Presenter.Presenters.StartingActivityPresenter;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.View.Fragments.PageFragment;
import com.example.ivan.easyreader.View.Interfaces.StartingActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartingActivity extends MvpAppCompatActivity implements StartingActivityView {
    @InjectPresenter
    StartingActivityPresenter presenter;
    @BindView(R.id.cvAllFiles)
    CardView cvAllFiles;

    @OnClick(R.id.cvAllFiles)
    public void cvAllFilesClicked() {
        presenter.onCVAllFilesClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startAllFilesActivity() {
        startActivity(new Intent(this, AllFilesActivity.class));
    }

    @Override
    public void setUpUI() {
        ButterKnife.bind(this);
    }
}

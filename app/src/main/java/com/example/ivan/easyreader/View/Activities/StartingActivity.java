package com.example.ivan.easyreader.View.Activities;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.easyreader.Presenter.Presenters.StartingActivityPresenter;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.Utils.ItemClickSupport;
import com.example.ivan.easyreader.View.Adapters.RecentBooksAdapter;
import com.example.ivan.easyreader.View.Interfaces.StartingActivityView;

import java.util.Collections;

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

    @BindView(R.id.recentBooksRV)
    RecyclerView recentBooksRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
    }

    @Override
    public void startAllFilesActivity() {
        startActivity(new Intent(this, AllFilesActivity.class));
    }

    @Override
    public void showWarningDialod() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No connection!")
                .setMessage("Without connection translation feature is unavailable. Would you like to go to settings and fix it?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.settings",
                                "com.android.settings.Settings$DataUsageSummaryActivity"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    @Override
    public void setUpUI() {
        ButterKnife.bind(this);
        RecentBooksAdapter recentBooksAdapter = new RecentBooksAdapter(presenter.getRecentBooks());
        LinearLayoutManager verticalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recentBooksRV.setLayoutManager(verticalLayoutManagaer);
        recentBooksRV.setAdapter(recentBooksAdapter);
        ItemClickSupport.addTo(recentBooksRV).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                presenter.rvItemClicked(recentBooksAdapter.getItem(position));
            }
        });
    }
}

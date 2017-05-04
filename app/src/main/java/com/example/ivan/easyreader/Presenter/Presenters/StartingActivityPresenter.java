package com.example.ivan.easyreader.Presenter.Presenters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.easyreader.Model.RecentBooks;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Utils.RxBus;
import com.example.ivan.easyreader.View.Activities.ReadingActivity;
import com.example.ivan.easyreader.View.Interfaces.IStartingActivityView;

import java.io.File;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by Myryk on 25.04.2017.
 */
@InjectViewState
public class StartingActivityPresenter extends BasePresenter<IStartingActivityView> {
    @Inject
    Context context;
    @Inject
    RxBus rxBus;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getComponent().injectStartingActivityPresenter(this);
        getViewState().setUpUI();
        if (!isNetworkConnected())
            getViewState().showWarningDialod();
    }

    public void onCVAllFilesClicked() {
        getViewState().startAllFilesActivity();
    }

    public Set<String> getRecentBooks() {
        return RecentBooks.getRecentBooks();
    }

    public void rvItemClicked(String book) {
        App.plusModelComponent();
        Intent intent = new Intent(context, ReadingActivity.class);
        rxBus.post(new File(book));
        getViewState().startActivity(intent);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}

package com.example.ivan.easyreader.Presenter.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.easyreader.View.Interfaces.StartingActivityView;

/**
 * Created by Myryk on 25.04.2017.
 */
@InjectViewState
public class StartingActivityPresenter extends BasePresenter<StartingActivityView>{
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    getViewState().setUpUI();
    }

    public void onCVAllFilesClicked() {
        getViewState().startAllFilesActivity();
    }
}

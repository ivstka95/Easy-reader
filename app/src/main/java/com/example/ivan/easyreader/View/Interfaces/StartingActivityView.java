package com.example.ivan.easyreader.View.Interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Myryk on 25.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface StartingActivityView extends MvpView {
    void setUpUI();

    void startAllFilesActivity();
}

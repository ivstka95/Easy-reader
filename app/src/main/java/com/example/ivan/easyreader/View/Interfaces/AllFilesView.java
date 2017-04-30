package com.example.ivan.easyreader.View.Interfaces;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ivan.easyreader.Model.DirectoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myryk on 25.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface AllFilesView extends MvpView {
    void setUpUI();

    void finishActivity();

    void showSortDialog();

    void updateItemsList(List items);

    void startActivity(Intent intent);

    void updateHorizontalList(List horizontalList);
}

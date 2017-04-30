package com.example.ivan.easyreader.Presenter.Presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.easyreader.Model.Book;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Presenter.Presenters.BasePresenter;
import com.example.ivan.easyreader.View.Interfaces.iPageFragmentView;

import javax.inject.Inject;

/**
 * Created by Myryk on 28.04.2017.
 */
@InjectViewState
public class PageFragmentPresenter extends BasePresenter<iPageFragmentView> {

    @Inject
    Book book;
    @Override
    protected void onFirstViewAttach() {

        App.plusModelComponent().injectPageFragmentPresenter(this);
        super.onFirstViewAttach();
    }
}

package com.example.ivan.easyreader.DI;

import com.example.ivan.easyreader.Presenter.Presenters.PageFragmentPresenter;

import dagger.Subcomponent;

/**
 * Created by Ivan on 30.03.2017.
 */
@Subcomponent(modules = {ModelModule.class})
@ReadingScope
public interface ModelComponent {
    void injectPageFragmentPresenter(PageFragmentPresenter pageFragmentPresenter);
}

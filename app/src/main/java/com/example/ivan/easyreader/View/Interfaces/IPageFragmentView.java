package com.example.ivan.easyreader.View.Interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ivan.easyreader.Model.Translation.Tr;
import com.example.ivan.easyreader.Model.Translation.Translation;

import java.util.List;

/**
 * Created by Myryk on 28.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IPageFragmentView extends MvpView{
    void showTranslations(Translation translation);

    void showErrorToast();
}

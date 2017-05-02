package com.example.ivan.easyreader.View.Interfaces;

import com.arellomobile.mvp.MvpView;
import com.example.ivan.easyreader.Model.Translation.Tr;
import com.example.ivan.easyreader.Model.Translation.Translation;

import java.util.List;

/**
 * Created by Myryk on 28.04.2017.
 */

public interface iPageFragmentView extends MvpView{
    void showTranslations(Translation translation);
}

package com.example.ivan.easyreader.View.Fragments;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.easyreader.Model.Translation.Translation;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Presenter.Presenters.PageFragmentPresenter;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.View.Interfaces.iPageFragmentView;

import javax.inject.Inject;

import static android.provider.UserDictionary.Words.WORD;
import static android.provider.VoicemailContract.Voicemails.TRANSCRIPTION;
import static com.example.ivan.easyreader.Presenter.Constants.ARGUMENT_PAGE_NUMBER;
import static com.example.ivan.easyreader.Presenter.Constants.TRANSLATIONS;


public class PageFragment extends MvpAppCompatFragment implements iPageFragmentView {
    @InjectPresenter
    PageFragmentPresenter presenter;
    @Inject
    Context context;
    private static int windowHeigth;
    private static int windowWidth;
    private static int lineHeight;
    private static TextView lastWord = null;
    int pageNumber;
    int backColor;

    public static void setWindowHeigth(int windowHeigth) {
        PageFragment.windowHeigth = windowHeigth;
    }

    public static void setWindowWidth(int windowWidth) {
        PageFragment.windowWidth = windowWidth;
    }

    public static void setLineHeight(int lineHeight) {
        PageFragment.lineHeight = lineHeight;
    }

    public static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.plusModelComponent().injectPageFragmentPresenter(presenter);
        App.getComponent().injectPageFragment(this);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view;
        if (pageNumber < presenter.getSavedPagesCount())
            view = (ViewGroup) presenter.getSavedPage(pageNumber);
        else {
            int countHeight = 0;
            view = (ViewGroup) inflater.inflate(R.layout.fragment_page, null);
            view.setBackgroundColor(backColor);
            while (windowHeigth - 38 - countHeight >= lineHeight) {
                int countWidth = 0;
                ViewGroup line = (ViewGroup) inflater.inflate(R.layout.line_layout, null);
                boolean lineIsReady = false;
                while (!lineIsReady) {
                    if (lastWord == null) {
                        String word = presenter.getAWord() + " ";
                        lastWord = new TextView(context);
                        lastWord.setText(word);
                        lastWord.setTextColor(0xAA000000);
                        lastWord.setTextSize(20);
                        lastWord.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                presenter.getTranslation(word);
                            }
                        });
                        lastWord.measure(0, 0);
                        if (windowWidth - countWidth >= lastWord.getMeasuredWidth()) {
                            line.addView(lastWord, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            countWidth += lastWord.getMeasuredWidth();
                            lastWord = null;
                        } else {
                            countHeight += lineHeight;
                            view.addView(line, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            lineIsReady = true;
                        }
                    } else {
                        line.addView(lastWord, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        countWidth += lastWord.getMeasuredWidth();
                        lastWord = null;
                    }
                }
            }
            presenter.savePage(view);
        }
        return view;
    }

    @Override
    public void showTranslations(Translation translation) {
        BottomSheetDialogFragment bottomSheetDialogFragment = new TranslationBottomSheetDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putString(WORD, translation.getWord());
        arguments.putString(TRANSCRIPTION, translation.getTranscription());
        arguments.putStringArrayList(TRANSLATIONS, translation.getTanslations());
        bottomSheetDialogFragment.setArguments(arguments);
        bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
    }
}
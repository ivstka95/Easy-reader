package com.example.ivan.easyreader.Presenter.Presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.easyreader.Model.API.YandexAPI;
import com.example.ivan.easyreader.Model.Book;
import com.example.ivan.easyreader.Model.Translation.Translation;
import com.example.ivan.easyreader.Model.Translation.WordArticle;
import com.example.ivan.easyreader.View.Interfaces.IPageFragmentView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Myryk on 28.04.2017.
 */
@InjectViewState
public class PageFragmentPresenter extends BasePresenter<IPageFragmentView> {

    @Inject
    Book book;
    @Inject
    YandexAPI yandexAPI;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public String getAWord() {
        return book.readWord();
    }

    public int getSavedPagesCount() {
        return book.getSavedPageCount();
    }

    public View getSavedPage(int pageNumber) {
        return book.getSavedPage(pageNumber);
    }

    public void savePage(View view) {
        book.savePage(view);
    }

    public void getTranslation(String text) {
        Translation translation = new Translation();
        translation.setWord(text.toUpperCase());
        Observable<WordArticle> translationObservable = yandexAPI.getTranslation(text);
        Subscription subscription = translationObservable.map(wordArticle -> wordArticle.getDef())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(defs -> Observable.from(defs))
                .doOnNext(def -> translation.setTranscription(def.getTs()))
                .map(def -> def.getTr())
                .flatMap(trs -> Observable.from(trs))
                .doOnNext(tr -> translation.getTanslations().add(tr.getText()))
                .toList()
                .subscribe(trs -> {
                    if (translation.getTanslations().size() > 0)
                        getViewState().showTranslations(translation);
                    else getViewState().showErrorToast();
                }, throwable -> throwable.printStackTrace());
        unsubscribeOnDestroy(subscription);
    }

    public boolean isEndOfBook() {
        return book.isEndOfBook();
    }
}

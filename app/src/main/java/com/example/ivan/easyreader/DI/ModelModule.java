package com.example.ivan.easyreader.DI;

import android.support.annotation.NonNull;

import com.example.ivan.easyreader.Model.API.YandexAPI;
import com.example.ivan.easyreader.Model.Book;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Utils.RxBus;

import java.io.File;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Myryk on 30.04.2017.
 */
@Module
public class ModelModule {
    @Inject
    RxBus rxBus;
    private File bookFile;

    public ModelModule() {
        App.getComponent().injectModelModule(this);
        rxBus.filteredObservable(File.class)
                .subscribe(uri -> {
                    this.bookFile = uri;
                });
    }

    @Provides
    @ReadingScope
    public Book provideBook() {
        return new Book(bookFile);
    }

    @Provides
    @NonNull
    @ReadingScope
    public YandexAPI provideYandexAPI() {
        Retrofit yandexRetrofit = new Retrofit.Builder()
                .baseUrl("https://dictionary.yandex.net/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YandexAPI yandexAPI = yandexRetrofit.create(YandexAPI.class);
        return yandexAPI;
    }
}

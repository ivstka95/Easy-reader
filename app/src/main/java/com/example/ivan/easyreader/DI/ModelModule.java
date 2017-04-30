package com.example.ivan.easyreader.DI;

import android.net.Uri;
import android.util.Log;

import com.example.ivan.easyreader.Model.Book;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Utils.RxBus;

import java.io.File;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

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
        Log.w("RXBUS", "" + rxBus);
        rxBus.filteredObservable(File.class)
                .subscribe(uri -> {
                    this.bookFile = uri;
                    Log.w("module got book", uri.getPath());
                });
    }

    @Provides
    @ReadingScope
    public Book provideBook() {
        Log.w("module provides book", "(----------)");
        return new Book(bookFile);
    }
}

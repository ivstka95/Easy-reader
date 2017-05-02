package com.example.ivan.easyreader.DI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.example.ivan.easyreader.Utils.Comparators.CompName;
import com.example.ivan.easyreader.Utils.RxBus;

import java.util.Comparator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan on 18.03.2017.
 */
@Module
public class AppModule {

    Context appContext;

    public AppModule(@NonNull Context context) {
        this.appContext = context;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) this.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    public RxBus provideRxBus() {
        return new RxBus();
    }

    @Provides
    @Singleton
    public Comparator provideComparator() {
        return new CompName();
    }
}

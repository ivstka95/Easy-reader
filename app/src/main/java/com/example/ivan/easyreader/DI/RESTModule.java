package com.example.ivan.easyreader.DI;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 27.03.2017.
 */
@Module
public class RESTModule {
//
//    @Provides
//    @NonNull
//    @ReadingScope
//    public PrivatAPI providePrivatAPI() {
//        Retrofit privatRetrofit = new Retrofit.Builder()
//                .baseUrl("https://api.privatbank.ua/p24api/")
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        PrivatAPI privatAPI = privatRetrofit.create(PrivatAPI.class);
//        return privatAPI;
//    }

}

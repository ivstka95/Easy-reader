package com.example.ivan.easyreader.DI;

import dagger.Subcomponent;

/**
 * Created by Ivan on 30.03.2017.
 */
@Subcomponent(modules = {RESTModule.class})
@ReadingScope
public interface RESTComponent {

//    void injectPrivatAPI(CurrencyExchangePresenter currencyExchangePresenter);
//
//    void injectEspnAPI(NewsPresenter newsPresenter);
}

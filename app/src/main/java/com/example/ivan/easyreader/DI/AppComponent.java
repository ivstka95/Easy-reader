package com.example.ivan.easyreader.DI;

import com.example.ivan.easyreader.Presenter.Presenters.AllFilesPresenter;
import com.example.ivan.easyreader.View.Adapters.DirectoryItemAdapter;
import com.example.ivan.easyreader.View.Adapters.QuickPathAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan on 18.03.2017.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void injectAllFilesPresenter(AllFilesPresenter allFilesPresenter);

    void injectDirectoryItemAdapter(DirectoryItemAdapter directoryItemAdapter);

    void injectQuickPathAdapter(QuickPathAdapter quickPathAdapter);

    void injectModelModule(ModelModule modelModule);

    RESTComponent plusRESTComponent(RESTModule restModule);
    ModelComponent plusModelComponent(ModelModule modelModule);

}

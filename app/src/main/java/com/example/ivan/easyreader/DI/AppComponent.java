package com.example.ivan.easyreader.DI;

import com.example.ivan.easyreader.Presenter.Presenters.AllFilesPresenter;
import com.example.ivan.easyreader.Presenter.Presenters.StartingActivityPresenter;
import com.example.ivan.easyreader.View.Activities.ReadingActivity;
import com.example.ivan.easyreader.View.Adapters.DirectoryItemAdapter;
import com.example.ivan.easyreader.View.Adapters.QuickPathAdapter;
import com.example.ivan.easyreader.View.Adapters.RecentBooksAdapter;
import com.example.ivan.easyreader.View.Fragments.PageFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan on 18.03.2017.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void injectAllFilesPresenter(AllFilesPresenter allFilesPresenter);

    void injectStartingActivityPresenter(StartingActivityPresenter startingActivityPresenter);

    void injectRecentBooksAdapter(RecentBooksAdapter recentBooksAdapter);

    void injectDirectoryItemAdapter(DirectoryItemAdapter directoryItemAdapter);

    void injectQuickPathAdapter(QuickPathAdapter quickPathAdapter);

    void injectModelModule(ModelModule modelModule);

    void injectPageFragment(PageFragment pageFragment);

    ModelComponent plusModelComponent(ModelModule modelModule);

    void injectReadingActivity(ReadingActivity readingActivity);
}

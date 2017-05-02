package com.example.ivan.easyreader.Presenter.Presenters;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.easyreader.Model.DirectoryItem;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.Utils.Comparators.CompDate;
import com.example.ivan.easyreader.Utils.Comparators.CompName;
import com.example.ivan.easyreader.Utils.Comparators.CompSize;
import com.example.ivan.easyreader.Utils.RxBus;
import com.example.ivan.easyreader.View.Activities.ReadingActivity;
import com.example.ivan.easyreader.View.Interfaces.AllFilesView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Myryk on 25.04.2017.
 */
@InjectViewState
public class AllFilesPresenter extends BasePresenter<AllFilesView> {
    @Inject
    RxBus rxBus;
    private List<String> horizontalList;
    private List items = new ArrayList<DirectoryItem>();
    private String path = "/";
    @Inject
    Comparator comparator;
    private String[] sortVariants = {"Size", "Date", "Name"};
    @Inject
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getComponent().injectAllFilesPresenter(this);
        getViewState().setUpUI();
    }


    private String getQiuckPath(int index) {
        String quickPath = "/";
        for (int i = 0; i <= index; i++) {
            quickPath += horizontalList.get(i) + "/";
        }
        return quickPath;
    }

    private String cutPath(String path) {
        do {
            path = path.substring(0, path.length() - 1);
        } while (path.charAt(path.length() - 1) != '/');
        return path;
    }

    public void refreshList() {
        items.clear();
        File dir = new File(path);
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    File file1 = new File(dir, file);
                    if (file1.isDirectory())
                        items.add(new DirectoryItem(path, file));
                    else {
                        DirectoryItem directoryItem = new DirectoryItem(path, file);
                        if (directoryItem.getType().equalsIgnoreCase(".txt"))
                            items.add(new DirectoryItem(path, file));
                    }
                }
            }
        }
        horizontalList = getCurrentPathButtonsList();
        if (horizontalList.size() > 1)
            horizontalList.remove(0);
        getViewState().updateHorizontalList(horizontalList);
        Collections.sort(items, comparator);
        getViewState().updateItemsList(items);
    }

    public List<String> getCurrentPathButtonsList() {
        List<String> buttons = new ArrayList<String>(Arrays.asList(path.split("/")));
        return buttons;
    }

    public void onBackPressed() {
        if (path.equals("/"))
            getViewState().finishActivity();
        else
            path = cutPath(path);
        refreshList();
    }

    public void bSortClicked() {
        getViewState().showSortDialog();
    }

    public String[] getSortVariants() {
        return sortVariants;
    }

    //    TODO make sort button in menu
    public void onDialogItemClicked(int which) {
        if (which == 0)
            comparator = new CompSize();
        if (which == 1)
            comparator = new CompDate();
        if (which == 2)
            comparator = new CompName();
        Collections.sort(items, comparator);
        getViewState().updateItemsList(items);
    }

    public void itemClicked(int position) {
        DirectoryItem file = (DirectoryItem) items.get(position);
        String filename = file.getFilepath();
        File intentFile = new File(filename);
        if (intentFile.isDirectory()) {
            path = filename;
            refreshList();
        }
        if (intentFile.isFile()) {
            App.plusModelComponent();
            Intent intent = new Intent(context, ReadingActivity.class);
            rxBus.post(new File(file.getFilepath()));
            getViewState().startActivity(intent);
        }
    }

    public void rvItemClicked(int position) {
        if (position == 0) {
            path = "/";
            refreshList();
            return;
        }
        path = getQiuckPath(position - 1);
        refreshList();
    }
}
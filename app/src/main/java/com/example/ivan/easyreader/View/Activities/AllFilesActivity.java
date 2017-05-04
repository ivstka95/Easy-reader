package com.example.ivan.easyreader.View.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ivan.easyreader.Presenter.Presenters.AllFilesPresenter;
import com.example.ivan.easyreader.Utils.ItemClickSupport;
import com.example.ivan.easyreader.R;
import com.example.ivan.easyreader.View.Interfaces.IAllFilesView;
import com.example.ivan.easyreader.View.Adapters.DirectoryItemAdapter;
import com.example.ivan.easyreader.View.Adapters.QuickPathAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllFilesActivity extends MvpAppCompatActivity implements IAllFilesView {

    private DirectoryItemAdapter directoryItemAdapter;
    private QuickPathAdapter quickPathAdapter;

    @BindView(R.id.horizontal_recycler_view)
    RecyclerView horizontal_recycler_view;
    @BindView(R.id.listView)
    ListView listView;

    @InjectPresenter
    AllFilesPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_files);
    }

    @Override
    protected void onResume() {
        presenter.refreshList();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();

    }

    @Override
    public void setUpUI() {
        ButterKnife.bind(this);
        presenter.setContext(this);
        quickPathAdapter = new QuickPathAdapter(presenter.getCurrentPathButtonsList());
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(quickPathAdapter);
        directoryItemAdapter = new DirectoryItemAdapter(this, R.layout.layout_list_item);
        listView.setAdapter(directoryItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.itemClicked(position);
            }
        });
        ItemClickSupport.addTo(horizontal_recycler_view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                presenter.rvItemClicked(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            presenter.bSortClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showSortDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setIcon(R.drawable.sort)
                .setItems(presenter.getSortVariants(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.onDialogItemClicked(which);
                    }
                });

        builder.create();
        builder.show();
    }

    @Override
    public void updateItemsList(List items) {
        directoryItemAdapter.updateList(items);
    }

    @Override
    public void updateHorizontalList(List horizontalList) {
        quickPathAdapter.updateHorizontalList(horizontalList);
    }

}
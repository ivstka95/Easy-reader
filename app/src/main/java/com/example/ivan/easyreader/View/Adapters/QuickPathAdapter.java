package com.example.ivan.easyreader.View.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuickPathAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> horizontalList;
    @Inject
    LayoutInflater inflater;

    public QuickPathAdapter(List<String> horizontalList) {
        App.getComponent().injectQuickPathAdapter(this);
        this.horizontalList = horizontalList;
    }


    public void updateHorizontalList(List<String> horizontalList) {
        this.horizontalList.clear();
        this.horizontalList.add("");
        this.horizontalList.addAll(horizontalList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = inflater.inflate(R.layout.folder_item, parent, false);
            return new FolderView(v);
        } else {
            v = inflater.inflate(R.layout.root_folder_item, parent, false);
            return new RootFolderView(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FolderView)
            ((FolderView) holder).tvFolder.setText(horizontalList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 0;
    }


    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    static class FolderView extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFolder)
        TextView tvFolder;

        FolderView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class RootFolderView extends RecyclerView.ViewHolder {
        public RootFolderView(View view) {
            super(view);
        }
    }

}

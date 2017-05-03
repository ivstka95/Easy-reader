package com.example.ivan.easyreader.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.easyreader.Model.DirectoryItem;
import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Иван on 12.01.2017.
 */

public class DirectoryItemAdapter extends ArrayAdapter<DirectoryItem> {
    private List<DirectoryItem> list;
    @Inject
    Context context;
    @Inject
    LayoutInflater inflater;

    public DirectoryItemAdapter(Context context, int resource) {
        super(context, resource);
        list = new ArrayList<>();
        App.getComponent().injectDirectoryItemAdapter(this);
    }

    public void updateList(List<DirectoryItem> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public List<DirectoryItem> getList() {
        return list;
    }

    @Nullable
    @Override
    public DirectoryItem getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_list_item, parent, false);
        }

        ImageView ivIcon = (ImageView) view.findViewById(R.id.ivIcon);

        final DirectoryItem file = (DirectoryItem) list.get(position);
        if (new File(file.getFilepath()).isDirectory()) {
            ivIcon.setImageResource(R.drawable.folder);
        } else {
            if (file.getType().equalsIgnoreCase(".txt")) {
                Picasso.with(context)
                        .load(R.drawable.txt)
                        .fit()
                        .into(ivIcon);
            }
        }

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(list.get(position).getName());
        TextView tvLastModified = (TextView) view.findViewById(R.id.tvLastModified);
        tvLastModified.setText(list.get(position).getLastModified());
        TextView tvSize = (TextView) view.findViewById(R.id.tvSize);
        tvSize.setText("");
        if (new File(file.getFilepath()).isFile())
            tvSize.setText(list.get(position).getSize());
        return view;
    }
}

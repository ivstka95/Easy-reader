package com.example.ivan.easyreader.View.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.easyreader.Presenter.App;
import com.example.ivan.easyreader.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Myryk on 03.05.2017.
 */

public class RecentBooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> books;
    @Inject
    LayoutInflater inflater;

    public RecentBooksAdapter(Set<String> recentBooks) {
        App.getComponent().injectRecentBooksAdapter(this);
        books = new ArrayList<String>();
        for (String book :
                recentBooks) {
            books.add(book);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_book_item, parent, false);
        return new BookView(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BookView) holder).tvBookName.setText(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public String getItem(int position) {
        return books.get(position);
    }

    static class BookView extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBookName)
        TextView tvBookName;

        BookView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
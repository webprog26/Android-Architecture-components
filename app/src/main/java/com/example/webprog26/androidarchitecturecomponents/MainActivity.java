package com.example.webprog26.androidarchitecturecomponents;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.webprog26.androidarchitecturecomponents.ui.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnLongClickListener, Observer<List<BorrowModel>> {

    //Link to the article: https://android.jlelse.eu/android-architecture-components-room-livedata-and-viewmodel-fca5da39e26b


    private RecyclerViewAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fab_add_note)
    FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabAddNote.setOnClickListener((final View view) -> {
            AddItemActivity.startActivity(this);
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onLongClick(View v) {
        final BorrowModel borrowModel = (BorrowModel) v.getTag();
        if (borrowModel != null) {
            listViewModel.deleteItem(borrowModel);
            return true;
        }
        return false;
    }

    @Override
    public void onChanged(@Nullable List<BorrowModel> borrowModels) {
        adapter.updateItems(borrowModels);
    }

    @Override
    protected Observer<List<BorrowModel>> getObserver() {
        return this;
    }
}

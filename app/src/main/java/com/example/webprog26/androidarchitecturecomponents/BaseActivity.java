package com.example.webprog26.androidarchitecturecomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by webprog26 on 20.04.18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected BorrowedListViewModel listViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);


        listViewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        final Observer<List<BorrowModel>> listObserver = getObserver();

        if (listObserver != null) {

            listViewModel.getItemAndPersonList().observe(BaseActivity.this, getObserver());

        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @Nullable
    protected abstract Observer<List<BorrowModel>> getObserver();
}

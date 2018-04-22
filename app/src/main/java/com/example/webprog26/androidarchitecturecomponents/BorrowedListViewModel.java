package com.example.webprog26.androidarchitecturecomponents;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by webprog26 on 20.04.18.
 */

public class BorrowedListViewModel extends AndroidViewModel{

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(@NonNull final Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowModelItems();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(@NonNull final BorrowModel borrowModel) {
        new DeleteAsyncTask(appDatabase).execute(borrowModel);
    }

    public void addItem(@NonNull final BorrowModel borrowModel) {
        new AddAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class DeleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private final AppDatabase appDatabase;

        DeleteAsyncTask(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            appDatabase.itemAndPersonModel().deleteBorrow(borrowModels[0]);
            return null;
        }
    }

    private static class AddAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private final AppDatabase appDatabase;

        AddAsyncTask(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            appDatabase.itemAndPersonModel().addBorrow(borrowModels[0]);
            return null;
        }
    }
}

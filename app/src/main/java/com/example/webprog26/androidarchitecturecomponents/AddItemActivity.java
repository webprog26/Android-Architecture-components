package com.example.webprog26.androidarchitecturecomponents;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class AddItemActivity extends BaseActivity {

    @BindView(R.id.til_person_name)
    TextInputLayout tilPersonName;

    @BindView(R.id.til_item_name)
    TextInputLayout tilItemName;

    @BindView(R.id.btn_add_note)
    Button btnAddNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnAddNote.setOnClickListener((final View view) -> {
            final String itemName = getUserInput(tilItemName);
            final String personName = getUserInput(tilPersonName);
            final Date currentDate = new Date();

            final BorrowModel borrowModel = BorrowModel.fromUserInput(itemName, personName, currentDate);

            if (borrowModel != null) {
                listViewModel.addItem(borrowModel);
                Toast.makeText(this.getApplicationContext(), "Note added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_item;
    }

    @Override
    protected Observer<List<BorrowModel>> getObserver() {
        return null;
    }

    public static void startActivity(@NonNull final Context context) {
        context.startActivity(new Intent(context, AddItemActivity.class));
    }

    @Nullable
    private static String getUserInput(@NonNull final TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null ? editText.getText().toString() : null;
    }
}

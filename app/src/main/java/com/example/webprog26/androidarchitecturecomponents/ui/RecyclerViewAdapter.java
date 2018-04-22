package com.example.webprog26.androidarchitecturecomponents.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webprog26.androidarchitecturecomponents.BorrowModel;
import com.example.webprog26.androidarchitecturecomponents.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webprog26 on 20.04.18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    private final List<BorrowModel> borrowModelList;
    private final View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<BorrowModel> borrowModelList, View.OnLongClickListener longClickListener) {
        this.borrowModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(borrowModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void updateItems(final List<BorrowModel> borrowModels) {
        borrowModelList.addAll(borrowModels);
        notifyDataSetChanged();
    }

     class RecyclerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.itemTextView)
        TextView itemTextView;

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.dateTextView)
        TextView dateTextView;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressWarnings("deprecation")
        void bind(@NonNull final BorrowModel borrowModel) {
            itemTextView.setText(borrowModel.getItemName());
            nameTextView.setText(borrowModel.getPersonName());
            dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0, 12));
            itemView.setTag(borrowModel);
            itemView.setOnLongClickListener(longClickListener);
        }
    }
}

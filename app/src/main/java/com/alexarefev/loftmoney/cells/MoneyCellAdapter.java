package com.alexarefev.loftmoney.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexarefev.loftmoney.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MoneyCellAdapter extends RecyclerView.Adapter<MoneyCellAdapter.MoneyViewHolder> {

    private List<MoneyItem> moneyItemList = new ArrayList<>();

    public MoneyCellAdapterClick moneyCellAdapterClick;

    public void setData(List<MoneyItem> moneyItems) {
        moneyItemList.clear();
        moneyItemList.addAll(moneyItems);

        notifyDataSetChanged();
    }

    public void setMoneyCellAdapterClick(MoneyCellAdapterClick moneyCellAdapterClick) {
        this.moneyCellAdapterClick = moneyCellAdapterClick;
    }

    @NotNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MoneyViewHolder(layoutInflater.inflate(R.layout.cell_money, parent, false),
                moneyCellAdapterClick);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MoneyCellAdapter.MoneyViewHolder holder, int position) {
        holder.bind(moneyItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return moneyItemList.size();
    }

    static class MoneyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView valueTextView;
        private MoneyCellAdapterClick moneyCellAdapterClick;


        public MoneyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView, MoneyCellAdapterClick moneyCellAdapterClick) {
            super(itemView);

            this.moneyCellAdapterClick = moneyCellAdapterClick;

            titleTextView = itemView.findViewById(R.id.moneyCellTitleView);
            valueTextView = itemView.findViewById(R.id.moneyCellValueView);
        }

    public void bind(final MoneyItem moneyItem) {
            titleTextView.setText(moneyItem.getTitle());
            valueTextView.setText(moneyItem.getValue());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (moneyCellAdapterClick != null) {
                        moneyCellAdapterClick.onCellClick(moneyItem);
                    }
                }
            });

    }
    }
}

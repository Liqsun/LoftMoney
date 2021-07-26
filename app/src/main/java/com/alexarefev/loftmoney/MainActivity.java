package com.alexarefev.loftmoney;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.alexarefev.loftmoney.cells.MoneyCellAdapter;
import com.alexarefev.loftmoney.cells.MoneyCellAdapterClick;
import com.alexarefev.loftmoney.cells.MoneyItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsView;
    private MoneyCellAdapter moneyCellAdapter = new MoneyCellAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configureRecyclerView();
        generateMoney();
    }

    private void configureRecyclerView() {
        itemsView = findViewById(R.id.itemsView);
        itemsView.setAdapter(moneyCellAdapter);

        moneyCellAdapter.moneyCellAdapterClick = new MoneyCellAdapterClick() {
            @Override
            public void onCellClick(MoneyItem moneyItem) {
                Toast.makeText(getApplicationContext(), "Click-click!", Toast.LENGTH_LONG).show();
            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        itemsView.setLayoutManager(layoutManager);
    }

    private void generateMoney() {
        List<MoneyItem> moneyItems = new ArrayList<>();
        moneyItems.add(new MoneyItem("Xbox", "45 000"));
        moneyItems.add(new MoneyItem("Salary", "139 000"));

        moneyCellAdapter.setData(moneyItems);
    }

    }

package com.alexarefev.loftmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private ItemsAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_budget, null);

        Button callAddButton = view.findViewById(R.id.call_add_item_activity);
        callAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                startActivityForResult(new Intent(getActivity(), AddItemActivity.class),
                        REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        mAdapter = new ItemsAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.addItem(new Item("XBox", 139000));
        mAdapter.addItem(new Item("Жвачка", 70));
        mAdapter.addItem(new Item("Последний альбом Леонтьева", 5000));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;


    }

    @Override
    public void onActivityResult(
            final int requestCode, final int resultCode, @Nullable final Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        int price;
        try {
            price = Integer.parseInt(data.getStringExtra("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mAdapter.addItem(new Item(data.getStringExtra("name"), price));
        }
    }

}

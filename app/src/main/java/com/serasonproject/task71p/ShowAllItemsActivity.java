package com.serasonproject.task71p;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class ShowAllItemsActivity extends AppCompatActivity implements ItemAdapter.OnRowClickListener {

    RecyclerView itemRecyclerView;
    ItemAdapter itemAdapter;
    List<LostFoundItem> itemList = new ArrayList<>();

    DbConnection dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_all_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dc = new DbConnection(this);
        loadItems();

        itemRecyclerView = findViewById(R.id.rvAllItems);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(itemList, this, this);
        itemRecyclerView.setAdapter(itemAdapter);
    }

    private void loadItems() {
        itemList = dc.getAllItems();
    }

    @Override
    public void onItemClick(int position) {
        int tId = itemList.get(position).getItemId();

        Intent i = new Intent(getApplicationContext(), DeleteItemActivity.class);
        i.putExtra("DeleteItem", String.valueOf(tId));
        startActivity(i);
        finish();
    }
}
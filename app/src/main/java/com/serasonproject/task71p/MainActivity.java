package com.serasonproject.task71p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnHomeNew;
    Button btnHomeAll;

//    ArrayList<LostFoundItem> iList = new ArrayList<LostFoundItem>();
    public DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelp = new DatabaseHelper(getApplicationContext());

        btnHomeNew = findViewById(R.id.btnMANew);
        btnHomeNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(getApplicationContext(), NewItemActivity.class);
                startActivity(int1);
            }
        });

        btnHomeAll = findViewById(R.id.btnMAAll);
        btnHomeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(getApplicationContext(), ShowAllItemsActivity.class);
                startActivity(int2);
            }
        });
    }
}
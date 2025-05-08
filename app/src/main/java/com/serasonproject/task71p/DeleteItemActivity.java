package com.serasonproject.task71p;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeleteItemActivity extends AppCompatActivity {
    DbConnection ds;

    LostFoundItem itemToDelete;

    TextView typeName;
    TextView timeSince;
    TextView location;

    Button btnDel;
    TextView errMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ds = new DbConnection(this);

        int tId = 0;
        String identifier = getIntent().getStringExtra("DeleteItem");
        tId = Integer.parseInt(identifier);
        itemToDelete = ds.selectItem(tId);

        String tn = itemToDelete.getItemType() + " " + itemToDelete.getItemName();
        typeName = findViewById(R.id.tvTypeName);
        typeName.setText(tn);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date dateToday = Calendar.getInstance().getTime();
        String todayString = format1.format(dateToday);

        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        timeSince = findViewById(R.id.tvTimeSince);
        String since = "";
        Date itemLogDate = Calendar.getInstance().getTime(); // temp valid date for use both in try/catch
        try {
            itemLogDate = format1.parse(itemToDelete.getiDate());
            if (itemLogDate.getTime() > dateToday.getTime()) {
                throw new RuntimeException("RuntimeException: Future date.");
            } else {
                // with some help from: https://www.tutorialspoint.com/how-to-get-the-difference-between-two-dates-in-android
                long dateDiff = Math.abs(itemLogDate.getTime() - dateToday.getTime());
                long diffCalc = dateDiff / (24 * 60 * 60 * 1000);
                String dayDifference = Long.toString(diffCalc);
                since = dayDifference + " days ago. " + itemToDelete.getItemType() + " on " + format2.format(itemLogDate) + ".";
                timeSince.setText(since);
            }
        } catch (Exception e) {
            since = "A future date was logged (" + format2.format(itemLogDate) + ").";
            timeSince.setText(since);
        }

        location = findViewById(R.id.tvLocation);
        location.setText(itemToDelete.getiLoc());

        Intent intent1 = new Intent(getApplicationContext(), ShowAllItemsActivity.class);

        btnDel = findViewById(R.id.btnDelete);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ds.deleteItem(itemToDelete);
                    Toast.makeText(getApplicationContext(), "The item has been deleted.", Toast.LENGTH_LONG).show();
                    startActivity(intent1);
                    finish();
                } catch (Exception e) {
                    errMsg.setText("An error occurred. Could not delete. (" + e + ").");
                    Toast.makeText(getApplicationContext(), "An error occurred. Could not delete. (\" + e + \").", Toast.LENGTH_LONG).show();
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }

    public void getRecord(int id) {
        itemToDelete = ds.selectItem(id);
    }
}
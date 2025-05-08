package com.serasonproject.task71p;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class NewItemActivity extends AppCompatActivity {
    LostFoundItem newItem;
    DbConnection ds;

    RadioButton rbLost;
    RadioButton rbFound;
    String iType;

    EditText iName;
    EditText iPhone;
    EditText iDesc;
    EditText iLoc;

    Button btnLog;
    String dateToSave;
    int dateToSaveYear;
    int dateToSaveMonth;
    int dateToSaveDay;
    private DatePickerDialog datePickerDialog;

    Button btnSave;
    TextView errMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ds = new DbConnection(this);

        initDatePicker();
        btnLog = findViewById(R.id.bLog);
        btnLog.setText(getTodaysDate());

        // set up fields to display
        rbLost = (RadioButton) findViewById(R.id.rbLost);
        rbFound = (RadioButton) findViewById(R.id.rbFound);
        iName = findViewById(R.id.etName);
        iPhone = findViewById(R.id.etPhone);
        iDesc = findViewById(R.id.etDesc);
        iLoc = findViewById(R.id.etLoc);
        // date field above

        btnSave = findViewById(R.id.btnSave);
        errMsg = findViewById(R.id.tvErr);

        Intent i = new Intent(getApplicationContext(), ShowAllItemsActivity.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rbLost.isChecked() && !rbFound.isChecked()) {
                    errMsg.setText("Select Lost or Found.");
                } else if (TextUtils.isEmpty(iName.getText().toString().trim())) {
                    errMsg.setText("Item name cannot be blank.");
                } else if (TextUtils.isEmpty(iPhone.getText().toString().trim())) {
                    errMsg.setText("Contact phone number cannot be blank.");
                } else if (TextUtils.isEmpty(iDesc.getText().toString().trim())) {
                    errMsg.setText("Item description cannot be blank.");
                } else if (TextUtils.isEmpty(iLoc.getText().toString().trim())) {
                    errMsg.setText("Location cannot be blank.");
                } else {
                    // try to add data into a new record and catch error(s)
                    try {
                        if(rbLost.isChecked()) {
                            iType = "Lost";
                        }
                        else if(rbFound.isChecked()) {
                            iType = "Found";
                        }
                        newItem = new LostFoundItem(iType, iName.getText().toString(), iPhone.getText().toString(), iDesc.getText().toString(), dateToSave, iLoc.getText().toString());
                        ds.addItem(newItem);
                        Toast.makeText(getApplicationContext(), "Your item has been saved.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        errMsg.setText("An error occurred and could not save. (" + e + ").");
                        Toast.makeText(getApplicationContext(), "An error occurred and could not save. (\" + e + \").", Toast.LENGTH_LONG).show();
                    }

                    startActivity(i);
                    finish(); // to remove state saving of inputs
                }
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                btnLog.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        dateToSave = year + getMonthFormat(month) + String.format("%02d", day);
        return day + "/" + getMonthFormat(month) + "/" + year;
    }

    // DatePicker and related date methods
    // Referred to some YouTube tutorials and modified them where necessary (e.g. https://www.youtube.com/watch?v=qCoidM98zNk)
    private String getMonthFormat(int month) {
        return String.format("%02d", month);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}
package com.example.samlee.lighting;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class calendarUser extends AppCompatActivity {
    DatabaseHelperUser myDb;
    private static final String TAG = "calendarUser";
    private TextView txtDate,txtdatadate;
    private Button button,button1,btn_back;
    String date,x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_user);




        myDb = new DatabaseHelperUser(this);
        txtDate = (TextView) findViewById(R.id.textView);
        txtdatadate = (TextView) findViewById(R.id.textView8);
        btn_back = (Button)findViewById(R.id.button_back);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);

        Intent incomingInteger = getIntent();
        date = incomingInteger.getStringExtra("date");
        txtDate.setText(date);

        x = txtDate.getText().toString();
        //txtdatadate.setText(x);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calendarUser.this, mainmenu_user.class);
                startActivity(its);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calendarUser.this, calendarActivityUser.class);
                startActivity(its);

            }
        });
        viewAlluserxreport();


    }

    private void viewAlluserxreport() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    //show message
                    //showMessage("Error ","Nothing found");
                    //showAlert();
                    //return;
                }
                StringBuffer buffer = new StringBuffer();
                    while ((res.moveToNext())) {
                        if(x.equals(res.getString(1))){
                            //txtdatadate.setText(buffer.append("ID = " + res.getString(0) + "\n"));
                            txtdatadate.setText(buffer.append("วันที่ : " + res.getString(1) + "\n"));
                            txtdatadate.setText(buffer.append("เลขอ่านล่าสุด : " + res.getString(2) + "\n"));
                            txtdatadate.setText(buffer.append("หน่วยที่ใช้ไป : " + res.getString(3) + "\n"));
                            txtdatadate.setText(buffer.append("จำนวนเงินทั้งหมด : " + res.getString(4) + "\n\n"));
                    }
                }
            }
        });
    }
    private void showAlert() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.alert);
        objAlert.setTitle("Error");
        objAlert.setMessage("ไม่สามารถอ่านค่าได้");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

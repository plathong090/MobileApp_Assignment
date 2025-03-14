package com.example.assignment06_6506021612048;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {
    MyDB sqLiteDatabase;
    TextView showdisplay;
    Button displaybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);

        sqLiteDatabase = new MyDB(this);
        showdisplay = findViewById(R.id.shodisplay);
        displaybtn = findViewById(R.id.displaybtn);
        displaybtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Cursor res = sqLiteDatabase.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("Id : " + res.getString(0) + "\n");
                stringBuffer.append("Name : " + res.getString(1) + "\n");
                stringBuffer.append("Price : " + res.getString(2) + "\n");
                stringBuffer.append("Profit : " + res.getString(3) + "\n");
            }
            showdisplay.setText(stringBuffer.toString());
            Toast.makeText(this, "ดึงข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ไม่มีข้อมูลให้แสดง", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.assignment06_6506021612048;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameedittext , priceedittext,profiredittext;
    MyDB sqLiteDatabase;
    Button addbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        sqLiteDatabase = new MyDB(this);

        addbtn = findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);

        nameedittext = findViewById(R.id.nameeditText);
        priceedittext = findViewById(R.id.priceeditText);
        profiredittext = findViewById(R.id.profiteditText);
    }

    @Override
    public void onClick(View view) {
        String nameStr = nameedittext.getText().toString();
        String priceStr = priceedittext.getText().toString();
        String profitStr = profiredittext.getText().toString();

        if (nameStr.isEmpty() || priceStr.isEmpty() || profitStr.isEmpty()) {
            Toast.makeText(this, "โปรดกรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        double profitRate = Double.parseDouble(profitStr);
        boolean result = sqLiteDatabase.addProduct(nameStr, price, profitRate);

        if (result) {
            nameedittext.setText("");
            priceedittext.setText("");
            profiredittext.setText("");
            Toast.makeText(this, "เพิ่มสินค้าเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ไม่สามารถเพิ่มสินค้า", Toast.LENGTH_SHORT).show();
        }
    }
}
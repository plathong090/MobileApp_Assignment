package com.example.assignment06_6506021612048;

import android.database.Cursor;
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

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    MyDB sqLiteDatabase;
    EditText idInput, nameInput, priceInput, profitInput;
    Button searchBtn, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);

        sqLiteDatabase = new MyDB(this);

        idInput = findViewById(R.id.idinput);
        nameInput = findViewById(R.id.nameinput);
        priceInput = findViewById(R.id.priceinput);
        profitInput = findViewById(R.id.profitinput);
        searchBtn = findViewById(R.id.searchbtn);
        updateBtn = findViewById(R.id.updatebtn);

        searchBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.searchbtn) {
            searchProduct();
        } else if (view.getId() == R.id.updatebtn) {
            updateProduct();
        }
    }

    private void searchProduct() {
        String productId = idInput.getText().toString();

        if (productId.isEmpty()) {
            Toast.makeText(this, "โปรดกรอกรหัสสินค้า", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor res = sqLiteDatabase.getDataById(productId);

        if (res != null && res.getCount() > 0) {
            res.moveToFirst();

            nameInput.setText(res.getString(1));
            priceInput.setText(res.getString(2));
            profitInput.setText(res.getString(3));
        } else {
            Toast.makeText(this, "ไม่พบข้อมูลสินค้า", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateProduct() {
        String productId = idInput.getText().toString();
        String name = nameInput.getText().toString();
        String priceStr = priceInput.getText().toString();
        String profitStr = profitInput.getText().toString();

        if (productId.isEmpty() || name.isEmpty() || priceStr.isEmpty() || profitStr.isEmpty()) {
            Toast.makeText(this, "โปรดกรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        double profit = Double.parseDouble(profitStr);

        boolean isUpdated = sqLiteDatabase.updateData(productId, name, price, profit);

        if (isUpdated) {
            idInput.setText("");
            nameInput.setText("");
            priceInput.setText("");
            profitInput.setText("");
            Toast.makeText(this, "ปรับปรุงข้อมูลสินค้าสำเร็จ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ปรับปรุงข้อมูลสินค้าไม่สำเร็จ", Toast.LENGTH_SHORT).show();
        }
    }
}
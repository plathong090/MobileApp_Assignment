package com.example.assignment06_6506021612048;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {
    MyDB DB;
    EditText idinput, nameinput, priceinput, profitinput;
    Button searchbtn, deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        DB = new MyDB(this);

        idinput = findViewById(R.id.idinput);
        nameinput = findViewById(R.id.nameinput);
        priceinput = findViewById(R.id.priceinput);
        profitinput = findViewById(R.id.profitinput);
        searchbtn = findViewById(R.id.searchbtn);
        deletebtn = findViewById(R.id.updatebtn);

        searchbtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == searchbtn) {
            String id = idinput.getText().toString().trim();

            if (!id.isEmpty()) {
                Cursor res = DB.getDataById(id);
                if (res != null && res.moveToFirst()) {
                    nameinput.setText(res.getString(1));
                    priceinput.setText(res.getString(2));
                    profitinput.setText(res.getString(3));
                } else {
                    Toast.makeText(this, "ไม่พบข้อมูลสินค้า", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "กรุณากรอกรหัสสินค้า", Toast.LENGTH_SHORT).show();
            }
        } else if (v == deletebtn) {
            String id = idinput.getText().toString().trim();
            if (!id.isEmpty()) {
                boolean result = DB.deleteData(id);
                if (result) {
                    Toast.makeText(this, "ลบสินค้าสำเร็จ", Toast.LENGTH_SHORT).show();
                    nameinput.setText("");
                    priceinput.setText("");
                    profitinput.setText("");
                    idinput.setText("");
                } else {
                    Toast.makeText(this, "ไม่สามารถลบสินค้าได้", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "กรุณากรอกรหัสสินค้า", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

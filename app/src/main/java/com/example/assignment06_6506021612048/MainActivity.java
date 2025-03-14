package com.example.assignment06_6506021612048;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button addbtn = findViewById(R.id.addbtn);
        Button displaybtn = findViewById(R.id.displaybtn);
        Button editbtn = findViewById(R.id.editbtn);
        Button deletebtn = findViewById(R.id.deletebtn);

        addbtn.setOnClickListener(this);
        displaybtn.setOnClickListener(this);
        editbtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addbtn) {
            Intent addpage = new Intent(MainActivity.this , AddActivity.class);
            startActivity(addpage);
        } else if (view.getId() == R.id.displaybtn) {
            Intent displaypage = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(displaypage);
        } else if (view.getId() == R.id.editbtn) {
            Intent editpage = new Intent(MainActivity.this, EditActivity.class);
            startActivity(editpage);
        } else if (view.getId() == R.id.deletebtn) {
            Intent deletepage = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(deletepage);
        }
    }
}
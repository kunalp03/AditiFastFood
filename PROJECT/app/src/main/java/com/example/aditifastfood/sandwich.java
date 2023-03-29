package com.example.aditifastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;

public class sandwich extends AppCompatActivity {

    TextView t1, rs1;
    Button sw;
    SharedPreferences sp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView52);
        rs1 = findViewById(R.id.textView49);
        sw = findViewById(R.id.button35);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("sandwich",t1.getText().toString()).apply();
                sp.edit().putString("sandwichprice",rs1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(sandwich.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(sandwich.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(sandwich.this, Cart.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;

        }
    }
    private void writeToFile(String filename, String content, String cprice1){
        File path = getApplicationContext().getFilesDir();

        try {
            FileOutputStream writer = new FileOutputStream(new File(path, filename));
            writer.write(content.getBytes());
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
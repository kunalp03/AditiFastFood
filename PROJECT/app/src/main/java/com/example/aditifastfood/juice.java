package com.example.aditifastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;

public class juice extends AppCompatActivity {

    TextView t1, rs1;
    TextView teatext, teaprice;
    TextView coketxt, cokeprice;
    Button juice, Tea, coke;
    SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juice);

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView40);
        rs1 = findViewById(R.id.textView37);
        juice = findViewById(R.id.button29);

        teatext = findViewById(R.id.textView38);
        teaprice = findViewById(R.id.textView39);
        Tea = findViewById(R.id.button30);

        coketxt = findViewById(R.id.textView36);
        cokeprice = findViewById(R.id.textView41);
        coke = findViewById(R.id.button31);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        coke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("juice",coketxt.getText().toString()).apply();
                sp.edit().putString("juiceprice",cokeprice.getText().toString()).apply();
                String content = new String(coketxt.getText().toString());
                String cprice1 = new String(cokeprice.getText().toString());
                writeToFile("juice.txt", content, cprice1);
                Toast.makeText(juice.this, coketxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        Tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("juice",teatext.getText().toString()).apply();
                sp.edit().putString("juiceprice",teaprice.getText().toString()).apply();
                String content = new String(teatext.getText().toString());
                String cprice1 = new String(teaprice.getText().toString());
                writeToFile("juice.txt", content, cprice1);
                Toast.makeText(juice.this, teatext.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("juice",t1.getText().toString()).apply();
                sp.edit().putString("juiceprice",rs1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("juice.txt", content, cprice1);
                Toast.makeText(juice.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(juice.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(juice.this, Cart.class);
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
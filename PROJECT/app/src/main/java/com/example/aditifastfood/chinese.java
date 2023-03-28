package com.example.aditifastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

public class chinese extends AppCompatActivity {
    TextView t1, rs1;
    Spinner count;
    TextView manchuriantxt, manchurianprice;
    Button b1, manchurianbtn;
    SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);
        sp=getSharedPreferences("data",MODE_PRIVATE);
        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        manchuriantxt = findViewById(R.id.textView15);
        manchurianprice = findViewById(R.id.textView21);
        manchurianbtn = findViewById(R.id.button21);
        b1 = findViewById(R.id.button20);
        t1 = findViewById(R.id.textView22);
        rs1 = findViewById(R.id.textView18);

        manchurianbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("cart1",manchuriantxt.getText().toString()).apply();
                sp.edit().putString("cart1price",manchurianprice.getText().toString()).apply();
                String content = new String(manchuriantxt.getText().toString());
                String cprice1 = new String(manchurianprice.getText().toString());
                writeToFile("chinese1.txt", content, cprice1);
                Toast.makeText(chinese.this, manchuriantxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("cart1",t1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("chinese1.txt", content, cprice1);
                sp.edit().putString("cart1price",rs1.getText().toString()).apply();
                Toast.makeText(chinese.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(chinese.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(chinese.this, Cart.class);
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
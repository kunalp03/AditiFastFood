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
    TextView cheesetxt, cheeseprice;
    TextView grilledtxt, grilledprice;
    Button sw, cheesebtn, grilled;
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

        cheesetxt = findViewById(R.id.textView48);
        cheeseprice = findViewById(R.id.textView51);
        cheesebtn = findViewById(R.id.button36);

        grilledtxt = findViewById(R.id.textView50);
        grilledprice = findViewById(R.id.textView53);
        grilled = findViewById(R.id.button37);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        grilled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("sandwich",grilledtxt.getText().toString()).apply();
                sp.edit().putString("sandwichprice",grilledprice.getText().toString()).apply();
                String content = new String(grilledtxt.getText().toString());
                String cprice1 = new String(grilledprice.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(sandwich.this, grilledtxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        cheesebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("sandwich",cheesetxt.getText().toString()).apply();
                sp.edit().putString("sandwichprice",cheeseprice.getText().toString()).apply();
                String content = new String(cheesetxt.getText().toString());
                String cprice1 = new String(cheeseprice.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(sandwich.this, cheesetxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
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
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

public class pizza extends AppCompatActivity {
    BottomNavigationView bv;
    Button b1;
    TextView t1, rs1;
    TextView cheeseptxt, cheesepprice;
    TextView maxpizzatxt, maxpizzaprice;
    Button pizza, cheesep, maxpizza;
    SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView46);
        rs1 = findViewById(R.id.textView43);
        pizza = findViewById(R.id.button32);

        cheeseptxt = findViewById(R.id.textView42);
        cheesepprice = findViewById(R.id.textView45);
        cheesep = findViewById(R.id.button33);

        maxpizzatxt = findViewById(R.id.textView44);
        maxpizzaprice = findViewById(R.id.textView47);
        maxpizza = findViewById(R.id.button34);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        maxpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("pizza",maxpizzatxt.getText().toString()).apply();
                sp.edit().putString("pizzaprice",maxpizzaprice.getText().toString()).apply();
                String content = new String(maxpizzatxt.getText().toString());
                String cprice1 = new String(maxpizzaprice.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(pizza.this, maxpizzatxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        cheesep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("pizza",cheeseptxt.getText().toString()).apply();
                sp.edit().putString("pizzaprice",cheesepprice.getText().toString()).apply();
                String content = new String(cheeseptxt.getText().toString());
                String cprice1 = new String(cheesepprice.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(pizza.this, cheeseptxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("pizza",t1.getText().toString()).apply();
                sp.edit().putString("pizzaprice",rs1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("sandwich.txt", content, cprice1);
                Toast.makeText(pizza.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(pizza.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(pizza.this, Cart.class);
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
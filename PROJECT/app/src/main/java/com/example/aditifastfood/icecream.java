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

public class icecream extends AppCompatActivity {

    TextView t1, rs1;
    TextView candytxt, candyprice;
    TextView cuptxt, cupprice;
    Button ice, candy, cup;
    SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecream);

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView30);
        rs1 = findViewById(R.id.textView31);
        ice = findViewById(R.id.button26);

        candytxt = findViewById(R.id.textView32);
        candyprice = findViewById(R.id.textView33);
        candy = findViewById(R.id.button27);

        cuptxt = findViewById(R.id.textView34);
        cupprice = findViewById(R.id.textView35);
        cup = findViewById(R.id.button28);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        cup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("icecream",cuptxt.getText().toString()).apply();
                sp.edit().putString("icecreamprice",cupprice.getText().toString()).apply();
                String content = new String(cuptxt.getText().toString());
                String cprice1 = new String(cupprice.getText().toString());
                writeToFile("icecream.txt", content, cprice1);
                Toast.makeText(icecream.this, cuptxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        candy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("icecream",candytxt.getText().toString()).apply();
                sp.edit().putString("icecreamprice",candyprice.getText().toString()).apply();
                String content = new String(candytxt.getText().toString());
                String cprice1 = new String(candyprice.getText().toString());
                writeToFile("icecream.txt", content, cprice1);
                Toast.makeText(icecream.this, candytxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("icecream",t1.getText().toString()).apply();
                sp.edit().putString("icecreamprice",rs1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("icecream.txt", content, cprice1);
                Toast.makeText(icecream.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(icecream.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(icecream.this, Cart.class);
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
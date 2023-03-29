package com.example.aditifastfood;

import androidx.appcompat.app.AppCompatActivity;

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

public class fastfood extends AppCompatActivity {
    BottomNavigationView bv;
    TextView t1, rs1;
    TextView pavbhajitxt, pavbhajiprice;
    Button vada, pavbhaji;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastfood);

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView29);
        rs1 = findViewById(R.id.textView25);
        vada = findViewById(R.id.button23);

        pavbhajitxt = findViewById(R.id.textView26);
        pavbhajiprice = findViewById(R.id.textView27);
        pavbhaji = findViewById(R.id.button24);

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.home).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        pavbhaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("vada",pavbhajitxt.getText().toString()).apply();
                sp.edit().putString("vadaprice",pavbhajiprice.getText().toString()).apply();
                String content = new String(pavbhajitxt.getText().toString());
                String cprice1 = new String(pavbhajiprice.getText().toString());
                writeToFile("fastf.txt", content, cprice1);
                Toast.makeText(fastfood.this, pavbhajitxt.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        vada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("vada",t1.getText().toString()).apply();
                sp.edit().putString("vadaprice",rs1.getText().toString()).apply();
                String content = new String(t1.getText().toString());
                String cprice1 = new String(rs1.getText().toString());
                writeToFile("fastf.txt", content, cprice1);
                Toast.makeText(fastfood.this, t1.getText()+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(fastfood.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(fastfood.this, Cart.class);
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
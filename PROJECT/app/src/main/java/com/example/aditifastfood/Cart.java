package com.example.aditifastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;

public class Cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String total[] = {"1", "2", "3", "4", "5"};
    Spinner count;
    BottomNavigationView bv;
    SharedPreferences sp;
    TextView t1;
    TextView prc1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        count = findViewById(R.id.spinner);
        ArrayAdapter ar = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, total);
        count.setAdapter(ar);
        count.setOnItemSelectedListener(this);


        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView54);
        prc1 = findViewById(R.id.textView55);
        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.cart).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

            if (bv.getMenu().findItem(R.id.cart).isChecked()) {
                String content = readFromFile("chinese1.txt");
                t1.setText(sp.getString("cart1", "Cart is empty"));
                prc1.setText(sp.getString("cart1price", ""));
        }
    }

    public void myClickItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(Cart.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(Cart.this, Cart.class);
                String content = readFromFile("chinese1.txt");
                t1.setText(sp.getString("cart1",null));
                startActivity(intent2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;

        }
    }

    private String readFromFile(String fileName){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte b[] = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(b);
            return new String(b);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Double price = Double.parseDouble(sp.getString("cart1price","0"));
        Double cal = Double.parseDouble(total[position]);
        Double rate = price*cal;
        Integer a=rate.intValue();
        prc1.setText(a.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
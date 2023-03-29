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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

public class Cart extends AppCompatActivity implements PaymentResultListener {

    String total[] = {"1", "2", "3", "4", "5"};
    EditText e1;
    TextView sum;

    BottomNavigationView bv;
    SharedPreferences sp;
    TextView t1;
    TextView prc1;
    TextView vadatxt, vadaprice;
    TextView swtxt, swprice;
    TextView pizzatxt, pizzaprice;
    TextView icetxt, iceprice;
    TextView juicetxt, juiceprice;
    Button clear;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        //pay
        findViewById(R.id.ord2).setOnClickListener(v -> pay());

        sp=getSharedPreferences("data",MODE_PRIVATE);
        t1 = findViewById(R.id.textView54);
        prc1 = findViewById(R.id.textView55);

        vadatxt = findViewById(R.id.textView56);
        vadaprice = findViewById(R.id.textView57);

        swtxt = findViewById(R.id.textView58);
        swprice = findViewById(R.id.textView59);

        pizzatxt = findViewById(R.id.textView60);
        pizzaprice = findViewById(R.id.textView61);

        icetxt = findViewById(R.id.textView62);
        iceprice = findViewById(R.id.textView63);

        juicetxt = findViewById(R.id.textView64);
        juiceprice = findViewById(R.id.textView65);

        clear = findViewById(R.id.clr);
        
        sum = findViewById(R.id.textView67);
        

        bv = findViewById(R.id.bottomNavigationView);
        bv.getMenu().findItem(R.id.cart).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        Integer calculation = 0;
            if (bv.getMenu().findItem(R.id.cart).isChecked()) {
                String content = readFromFile("chinese1.txt");
                t1.setText(sp.getString("cart1", ""));
                prc1.setText(sp.getString("cart1price", ""));
                if(!prc1.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(prc1.getText().toString());
                    sum.setText(calculation.toString());
                }

                String content2 = readFromFile("fastf.txt");
                vadatxt.setText(sp.getString("vada", ""));
                vadaprice.setText(sp.getString("vadaprice", ""));
                if(!vadaprice.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(vadaprice.getText().toString());
                    sum.setText(calculation.toString());
                }
//                calculation = Integer.parseInt(vadaprice.getText().toString());


                String content3 = readFromFile("sandwich.txt");
                swtxt.setText(sp.getString("sandwich", ""));
                swprice.setText(sp.getString("sandwichprice", ""));
                if(!swprice.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(swprice.getText().toString());
                    sum.setText(calculation.toString());
                }


                String content4 = readFromFile("pizza.txt");
                pizzatxt.setText(sp.getString("pizza", ""));
                pizzaprice.setText(sp.getString("pizzaprice", ""));
                if(!pizzaprice.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(pizzaprice.getText().toString());
                    sum.setText(calculation.toString());
                }


                String content5 = readFromFile("icecream.txt");
                icetxt.setText(sp.getString("icecream", ""));
                iceprice.setText(sp.getString("icecreamprice", ""));
                if(!iceprice.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(iceprice.getText().toString());
                    sum.setText(calculation.toString());
                }


                String content6 = readFromFile("juice.txt");
                juicetxt.setText(sp.getString("juice", ""));
                juiceprice.setText(sp.getString("juiceprice", ""));
                if(!juiceprice.getText().toString().isEmpty()){
                    calculation =calculation + Integer.parseInt(juiceprice.getText().toString());
                    sum.setText(calculation.toString());
                }
            }

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sp.edit().putString("cart1","").apply();
                    sp.edit().putString("cart1price","").apply();
                    t1.setText("");
                    prc1.setText("");

                    sp.edit().putString("vada","").apply();
                    sp.edit().putString("vadaprice","").apply();
                    vadatxt.setText("");
                    vadaprice.setText("");

                    sp.edit().putString("sandwich","").apply();
                    sp.edit().putString("sandwichprice","").apply();
                    swtxt.setText("");
                    swprice.setText("");

                    sp.edit().putString("pizza","").apply();
                    sp.edit().putString("pizzaprice","").apply();
                    pizzatxt.setText("");
                    pizzaprice.setText("");

                    sp.edit().putString("icecream","").apply();
                    sp.edit().putString("icecreamprice","").apply();
                    icetxt.setText("");
                    iceprice.setText("");

                    sp.edit().putString("juice","").apply();
                    sp.edit().putString("juiceprice","").apply();
                    juicetxt.setText("");
                    juiceprice.setText("");

                    sum.setText("0");
                }
            });
    }
    private void pay()
    {
        Checkout co = new Checkout();
        JSONObject object = new JSONObject();
        try {
            object.put("name","Sameep Hedaoo");
            object.put("desc","This is a test");
            object.put("currency","INR");
            object.put("amount","10000");

            JSONObject prefill = new JSONObject();
            prefill.put("contact","8109477448");
            prefill.put("email","sameephedaoo@gmail.com");
            prefill.put("prefill",prefill);
            co.open(Cart.this,object);
        } catch (JSONException e) {
            throw new RuntimeException(e);
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
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Cart.this, timer.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            Double price = Double.parseDouble(sp.getString("cart1price", "0"));
//            Double cal = Double.parseDouble(total[position]);
//            Double rate = price * cal;
//            Integer a = rate.intValue();
//            prc1.setText(a.toString());
//
//            Double price2 = Double.parseDouble(sp.getString("vadaprice", "0"));
//            Double cal2 = Double.parseDouble(total[position]);
//            Double rate2 = price2 * cal2;
//            Integer a2 = rate2.intValue();
//            vadaprice.setText(a2.toString());
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
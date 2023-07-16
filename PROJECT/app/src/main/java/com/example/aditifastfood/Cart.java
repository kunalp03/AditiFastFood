package com.example.aditifastfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

public class Cart extends AppCompatActivity implements PaymentResultListener {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    SharedPreferences sp;
    Checkout co;
    JSONObject object;

    String userId;
    Integer calculation = 0;

    TextView t1, prc1, vadatxt, vadaprice, swtxt, swprice, pizzatxt, pizzaprice, icetxt, iceprice, juicetxt, juiceprice, sum;
    Button clear;

    BottomNavigationView bv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        co = new Checkout();
        object = new JSONObject();

        sp = getSharedPreferences("data", MODE_PRIVATE);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

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
        sum = findViewById(R.id.textView67);
        clear = findViewById(R.id.clr);
        bv = findViewById(R.id.bottomNavigationView);

        bv.getMenu().findItem(R.id.cart).setChecked(true);
        bv.setOnNavigationItemSelectedListener(item -> {
            myClickItem(item);
            return true;
        });

        loadCartItems();
        updateCalculation();

        clear.setOnClickListener(v -> {
            clearCartItems();
            updateCalculation();
            sum.setText("0");
        });

        findViewById(R.id.ord2).setOnClickListener(v -> pay());
    }

    private void loadCartItems() {
        t1.setText(sp.getString("cart1", ""));
        prc1.setText(sp.getString("cart1price", ""));
        vadatxt.setText(sp.getString("vada", ""));
        vadaprice.setText(sp.getString("vadaprice", ""));
        swtxt.setText(sp.getString("sandwich", ""));
        swprice.setText(sp.getString("sandwichprice", ""));
        pizzatxt.setText(sp.getString("pizza", ""));
        pizzaprice.setText(sp.getString("pizzaprice", ""));
        icetxt.setText(sp.getString("icecream", ""));
        iceprice.setText(sp.getString("icecreamprice", ""));
        juicetxt.setText(sp.getString("juice", ""));
        juiceprice.setText(sp.getString("juiceprice", ""));
    }

    private void clearCartItems() {
        sp.edit().remove("cart1").apply();
        sp.edit().remove("cart1price").apply();
        sp.edit().remove("vada").apply();
        sp.edit().remove("vadaprice").apply();
        sp.edit().remove("sandwich").apply();
        sp.edit().remove("sandwichprice").apply();
        sp.edit().remove("pizza").apply();
        sp.edit().remove("pizzaprice").apply();
        sp.edit().remove("icecream").apply();
        sp.edit().remove("icecreamprice").apply();
        sp.edit().remove("juice").apply();
        sp.edit().remove("juiceprice").apply();
        t1.setText("");
        prc1.setText("");
        vadatxt.setText("");
        vadaprice.setText("");
        swtxt.setText("");
        swprice.setText("");
        pizzatxt.setText("");
        pizzaprice.setText("");
        icetxt.setText("");
        iceprice.setText("");
        juicetxt.setText("");
        juiceprice.setText("");
    }

    private void updateCalculation() {
        calculation = 0;
        calculation += getNumericValue(prc1);
        calculation += getNumericValue(vadaprice);
        calculation += getNumericValue(swprice);
        calculation += getNumericValue(pizzaprice);
        calculation += getNumericValue(iceprice);
        calculation += getNumericValue(juiceprice);
        sum.setText(calculation.toString());
    }

    private int getNumericValue(TextView textView) {
        String text = textView.getText().toString();
        if (!text.isEmpty()) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    private void pay() {
        int sumtotal = calculation * 100;

        if (sumtotal == 0) {
            Toast.makeText(this, "Cannot proceed with an empty cart. Add items to the cart.", Toast.LENGTH_SHORT).show();
            return;
        }

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    String email = value.getString("email");
                    String name = value.getString("fName");
                    String mobno = value.getString("phone");

                    try {
                        object.put("name", name);
                        object.put("desc", "This is a test");
                        object.put("currency", "INR");
                        object.put("amount", sumtotal);

                        JSONObject prefill = new JSONObject();
                        prefill.put("contact", mobno);
                        prefill.put("email", email);
                        object.put("prefill", prefill);
                        co.open(Cart.this, object);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void myClickItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(Cart.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.cart:
                Intent intent2 = new Intent(Cart.this, Cart.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
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
}

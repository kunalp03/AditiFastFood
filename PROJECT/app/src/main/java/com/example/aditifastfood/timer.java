package com.example.aditifastfood;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;

        import android.app.Activity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import java.time.LocalDateTime;

public class timer extends AppCompatActivity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Get a reference to the TimePicker widget
        timePicker = findViewById(R.id.timePicker);
        Button finish = findViewById(R.id.button4);
        Button homepage = findViewById(R.id.button6);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView timeText = findViewById(R.id.textView68);
        TextView ordernum = findViewById(R.id.textView67);
        // Set a listener for when the user changes the selected time
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Retrieve the selected time
                String selectedTime = hourOfDay + ":" + minute;
                // get the current date and time
                LocalDateTime currentDateTime = LocalDateTime.now();
                String ordernumber = hourOfDay +""+ minute+""+currentDateTime;
//                Toast.makeText(timer.this, "" + selectedTime, Toast.LENGTH_SHORT).show();
//                Toast.makeText(timer.this, "" + ordernumber, Toast.LENGTH_LONG).show();

                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(timer.this, "Your order will be ready at : " + selectedTime, Toast.LENGTH_SHORT).show();
                        Toast.makeText(timer.this, "Your order number is: " + ordernumber, Toast.LENGTH_SHORT).show();
                        timeText.setText("Order Time:"+selectedTime);
                        ordernum.setText("Order Number:"+ordernumber);
                    }
                });
                homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(timer.this,Home.class);
                        startActivity(intent);
                    }
                });
            }
     });
}
}

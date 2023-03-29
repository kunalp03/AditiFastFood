package com.example.aditifastfood;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Build;
        import android.os.Bundle;

        import android.app.Activity;
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

                Toast.makeText(timer.this, "Your order will be ready at : " + selectedTime, Toast.LENGTH_SHORT).show();
                Toast.makeText(timer.this, "Your order number is " + ordernumber, Toast.LENGTH_LONG).show();
            }
     });
}
}
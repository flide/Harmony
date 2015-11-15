package inc.flide.rentalharmony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class RentDivisionInputActivity extends AppCompatActivity {

    EditText totalRentEditText;
    NumberPicker numberOfPeopleNumberPicker;

    Double totalRent;
    Integer numberOfPeopleSharing;

    public static String TOTAL_RENT_KEY = "TOTAL_RENT";
    public static String TOTAL_TENENTS_KEY = "TOTAL_TENENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_division_input);

        totalRentEditText = (EditText) findViewById(R.id.totalRent);
        numberOfPeopleNumberPicker = (NumberPicker) findViewById(R.id.numberOfPeople);

        numberOfPeopleNumberPicker.setMinValue(2);
        numberOfPeopleNumberPicker.setMaxValue(10);
        numberOfPeopleNumberPicker.setWrapSelectorWheel(false);

    }

    public void onContinueButtonPressed(View view){
        if(isInputValid()){
            Intent getFlatAndTenentDetailsIntent = new Intent(this, GetFlatAndTenentDetailsActivity.class);

            getFlatAndTenentDetailsIntent.putExtra(TOTAL_RENT_KEY, totalRent );
            getFlatAndTenentDetailsIntent.putExtra(TOTAL_TENENTS_KEY, numberOfPeopleSharing);

            startActivity(getFlatAndTenentDetailsIntent);

        }else{
            Toast.makeText(getApplicationContext(), "Invalid value for total Rent!!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isInputValid() {
        try {
            totalRent = Double.parseDouble(totalRentEditText.getText().toString());
            numberOfPeopleSharing = numberOfPeopleNumberPicker.getValue();
            if(totalRent.isNaN() || totalRent <= 0){
                return false;
            }else{
                return true;
            }
        }catch (NumberFormatException nfe){
            return false;
        }
    }
}

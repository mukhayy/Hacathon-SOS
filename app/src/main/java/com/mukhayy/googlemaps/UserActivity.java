package com.mukhayy.googlemaps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    //private static final String KEY_INDEX = "index";
    private EditText carNumber, carModel, phoneNumber;
    private Button save;

    SharedPreferences sPref;
    final String SAVED_CARNUMBER = "saved_carNumber";
    final String SAVED_CARMODEL = "saved_carModel";
    final String SAVED_PHONENUMBER = "saved_phoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        init();

        sPref = getPreferences(MODE_PRIVATE);
        if(!sPref.getString(SAVED_CARMODEL, "").equals("")
                && !sPref.getString(SAVED_CARNUMBER, "").equals("")
                && !sPref.getString(SAVED_PHONENUMBER, "").equals("")){
           loadText();
        }else{
           Toast.makeText(this, "Please enter info", Toast.LENGTH_SHORT).show();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String numberCar = carNumber.getText().toString().trim();
                final String modelCar = carModel.getText().toString().trim();
                final String phoNumber = phoneNumber.getText().toString().trim();

                saveText();
                loadText();
                Intent intent = new Intent(UserActivity.this, MapActivity.class);
                intent.putExtra("numberCar", numberCar);
                intent.putExtra("modelCar", modelCar);
                intent.putExtra("phoNumber", phoNumber);
                startActivity(intent);
            }
        });

    }


    private void init(){
        carNumber = findViewById(R.id.carNumber);
        carModel = findViewById(R.id.carModel);
        phoneNumber = findViewById(R.id.phoneNumber);
        save = findViewById(R.id.btnSave);

    }


    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_CARMODEL, carModel.getText().toString());
        ed.putString(SAVED_CARNUMBER, carNumber.getText().toString());
        ed.putString(SAVED_PHONENUMBER, phoneNumber.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedCarModel = sPref.getString(SAVED_CARMODEL, "");
        String savedCarNumber = sPref.getString(SAVED_CARNUMBER, "");
        String savedPhoneNumber = sPref.getString(SAVED_PHONENUMBER, "");

        carNumber.setText(savedCarNumber);
        carModel.setText(savedCarModel);
        phoneNumber.setText(savedPhoneNumber);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}

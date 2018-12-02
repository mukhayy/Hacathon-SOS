package com.mukhayy.googlemaps;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mukhayy.googlemaps.API.APIService;
import com.mukhayy.googlemaps.API.APIUtils;
import com.mukhayy.googlemaps.Models.MakeOrder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private CheckBox sOne, sTwo, sThree;
    private Button order;
    private RelativeLayout otherServices;
    private EditText editOtherServices;
    private APIService apiService;
    SharedPreferences sPref;
    String service = "";
    String comment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();


        apiService = APIUtils.getAPIService();

        sThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if (isChecked) {
                    otherServices.setVisibility(View.VISIBLE);
                }else {
                    otherServices.setVisibility(View.GONE);
                    comment = "";
                }
            }

        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String address = getIntent().getStringExtra("addressSearch");
                 String numberCar = getIntent().getStringExtra("numberCar");
                 String modelCar = getIntent().getStringExtra("modelCar");
                 String phoNumber = getIntent().getStringExtra("phoNumber");

                if(sThree.isChecked()&& editOtherServices.getText().toString().length() == 0 ) {
                    editOtherServices.setError("required");
                }else{
                    if(sThree.isChecked())comment = editOtherServices.getText().toString();
                    if (sOne.isChecked()){
                        service += sOne.getText().toString().trim();
                    }
                    if (sTwo.isChecked()){
                        service += sTwo.getText().toString().trim();
                    }

                    Toast.makeText(getApplicationContext(), service+address+comment+numberCar+modelCar+phoNumber, Toast.LENGTH_LONG).show();
                    MakeOrder order = new MakeOrder(numberCar, phoNumber, modelCar, service, address, comment);
                    apiService.savePost(order).enqueue(new Callback<MakeOrder>() {
                        @Override
                        public void onResponse(Call<MakeOrder> call, Response<MakeOrder> response) {
                            if (response.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                                Log.i("OnResponse", "post submitted to API." + response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<MakeOrder> call, Throwable t) {
                            Log.e("onFailure", "Unable to submit post to API." + t);
                        }
                    });

                    // comment = "";
                    service = "";

                }
            }
        });
    }

    private void init(){
        sOne =(CheckBox) findViewById(R.id.sOne);
        sTwo =(CheckBox) findViewById(R.id.sTwo);
        sThree =(CheckBox) findViewById(R.id.sThree);
        order = findViewById(R.id.btnOrder);
        otherServices = findViewById(R.id.otherServices);
        editOtherServices = findViewById(R.id.editOtherServices);
    }
}

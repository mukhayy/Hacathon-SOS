package com.mukhayy.googlemaps.API;

import com.mukhayy.googlemaps.Models.MakeOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("makeorder.php")
    Call<MakeOrder> savePost(@Body MakeOrder makeOrder);

    /*@Field("car_num") String car_num,
                             @Field("phone_num") String phone_num,
                             @Field("model") String model,
                             @Field("services") String services,
                             @Field("address") String address,
                             @Field("comment") String comment
                             */

}

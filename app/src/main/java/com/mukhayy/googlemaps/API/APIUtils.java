package com.mukhayy.googlemaps.API;

public class APIUtils {

    private APIUtils(){}

    public static final String baseUrl = "http://192.168.12.24/hackaton/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(baseUrl).create(APIService.class);
    }

}

package com.mukhayy.googlemaps.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MakeOrder {


    @SerializedName("car_num")
    @Expose
    String car_num;
    @SerializedName("phone_num")
    @Expose
    String phone_num;
    @SerializedName("model")
    @Expose
    String model;
    @SerializedName("services")
    @Expose
    String services;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("comment")
    @Expose
    String comment;



    public MakeOrder(String car_num, String phone_num, String model, String services, String address, String comment) {
        this.car_num = car_num;
        this.phone_num = phone_num;
        this.model = model;
        this.services = services;
        this.address = address;
        this.comment = comment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   @Override
    public String toString() {
       return "MakeOrder{" +
               "car_num='" + car_num + '\'' +
               ", phone_num='" + phone_num + '\'' +
               ", model='" + model + '\'' +
               ", services='" + services + '\'' +
               ", address='" + address + '\'' +
               ", comment='" + comment + '\'' +
               '}';
   }
}

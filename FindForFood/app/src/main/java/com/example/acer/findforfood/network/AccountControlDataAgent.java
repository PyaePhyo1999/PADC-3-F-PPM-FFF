package com.example.acer.findforfood.network;

/**
 * Created by Acer on 1/27/2018.
 */

public interface AccountControlDataAgent {
    void loginUser(String phoneNo,String password);
    void RegisterUser(String phoneNo,String password,String name);
}

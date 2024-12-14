package com.example.sorting.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public interface SortJsonInterface {
    public JsonObject sort(JsonObject obj);
}

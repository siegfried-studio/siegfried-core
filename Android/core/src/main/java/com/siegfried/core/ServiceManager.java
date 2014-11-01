package com.siegfried.core;

import com.androidquery.AQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Chao-Chih Lin on 11/1/14.
 */
public class ServiceManager {
    private static final String TAG = "ServiceManager";
    private static final String HOST = "http://private-cf6fc-siegfried.apiary-mock.com";
    private static final String API_SIGNUP = HOST + "/users/signup";
    private static final String API_SIGNIN = HOST + "/users/signin";

    private static final String FK_ACCOUNT = "account";
    private static final String FK_PASSWORD = "password";



    private static ServiceManager _instance;
    public static ServiceManager getInstance() {
        if (_instance == null) {
            _instance = new ServiceManager();
        }
        return _instance;
    }

    public boolean signUp(AQuery aq, String account, String password, APIHandler handler) {
        JSONObject json = new JSONObject();
        try {
            json.put(FK_ACCOUNT, account);
            json.put(FK_PASSWORD, password);
            doAPIPost(aq, API_SIGNUP, json, handler);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean signIn(AQuery aq, String account, String password, APIHandler handler) {
        JSONObject json = new JSONObject();
        try {
            json.put(FK_ACCOUNT, account);
            json.put(FK_PASSWORD, password);
            doAPIPost(aq, API_SIGNUP, json, handler);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void doAPIGet(AQuery aq, String api, APIHandler handler) {
        aq.ajax(api, JSONObject.class, handler);
    }

    // HTTP POST JSON RAW
    private void doAPIPost(AQuery aq, String api, JSONObject json, APIHandler handler) {
        aq.post(api, json, JSONObject.class, handler);
    }

    // HTTP Post MultiPart/Form
    private void doAPIPost(AQuery aq, String api, Map<String, Object> formParams, APIHandler handler) {
        aq.ajax(api, formParams, JSONObject.class, handler);
    }


}

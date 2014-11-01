package com.siegfried.core;

import android.util.Log;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dokinkon on 11/1/14.
 */
public abstract class APIHandler extends AjaxCallback<JSONObject> {

    @Override
    public void callback(String url, JSONObject json, AjaxStatus status) {

        onResponseAvailable();
        if (json == null) {
            Log.e(TAG, "ajax.status.err_message:" + status.getError());
            Log.e(TAG, "ajax.status.code:" + status.getCode());
            onError(APIError.connectionError());
        } else {
            handleJSONResponse(json);
        }
    }

    private static final String TAG = "APIHandler";
    /**
     * 用來知會UI Thread(關閉Loading Dialog)
     */
    public abstract void onResponseAvailable();

    /**
     * Notify UI Thread we got some problems.
     *
     * @param error
     */
    public abstract void onError(APIError error);

    public void handleJSONResponse(JSONObject json) {


        try {
            int errorCode = json.getInt("status");
            if (errorCode!=APIError.NO_ERROR) {
                Log.e(TAG, "api.response.error:" + errorCode);
                onError(APIError.fromErrorCode(errorCode));
            } else {
                handleRestData(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(APIError.unknownError());
        }
    }

    protected void handleRestData(JSONObject json) {

    }



}


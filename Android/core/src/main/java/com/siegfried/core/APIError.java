package com.siegfried.core;

/**
 * Created by dokinkon on 11/1/14.
 */
public class APIError {
    public static final int NO_ERROR = 200;
    public static final int CONNECTION_ERROR = 1024;
    public static final int UNKNOWN_ERROR = -1;

    private int _errorCode;

    public int getErrorCode() {
        return _errorCode;
    }

    public static APIError fromErrorCode(int errorCode) {
        APIError error = new APIError();
        error._errorCode = errorCode;
        return error;
    }

    private static APIError _unknownErrorInstance;
    public static APIError unknownError() {
        if (_unknownErrorInstance == null) {
            _unknownErrorInstance = new APIError();
            _unknownErrorInstance._errorCode = UNKNOWN_ERROR;
        }
        return _unknownErrorInstance;
    }

    private static APIError _connectionErrorInstance;
    public static APIError connectionError() {
        if (_connectionErrorInstance == null) {
            _connectionErrorInstance = new APIError();
            _connectionErrorInstance._errorCode = CONNECTION_ERROR;
        }
        return _connectionErrorInstance;
    }
}

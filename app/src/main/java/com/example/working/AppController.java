package com.example.working;

import android.app.Application;
import android.app.DownloadManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.Collection;
import java.util.List;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static AppController mInstance ;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }
    public static synchronized AppController getmInstance(){
        return mInstance;

    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRquestQueue(Request<T> request , String tag){
        request.setTag((TextUtils.isEmpty(tag) ? TAG :  tag));
        getmRequestQueue().add(request);
    }

    public <T> void addToRquestQueue(Request<T> request ){
        request.setTag(TAG);
        getmRequestQueue().add(request);
    }


    public void cancelPending(Object tag){
        if (mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }



}



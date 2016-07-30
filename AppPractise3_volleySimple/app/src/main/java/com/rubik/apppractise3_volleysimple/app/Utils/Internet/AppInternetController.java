package com.rubik.apppractise3_volleysimple.app.Utils.Internet;

import android.app.Application;

/**
 * Created by Rubik on 28/7/16.
 */
public class AppInternetController extends Application{

    private static AppInternetController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized AppInternetController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(BroadcastInternetReceiver.BroadcastInternetReceiverListener listener) {
        BroadcastInternetReceiver.broadcastInternetReceiverListener = listener;
    }
}

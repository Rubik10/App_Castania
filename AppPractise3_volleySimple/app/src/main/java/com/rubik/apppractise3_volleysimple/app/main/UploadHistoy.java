package com.rubik.apppractise3_volleysimple.app.main;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rubik.apppractise3_volleysimple.app.Utils.UtilsApp;
import com.rubik.apppractise3_volleysimple.app.volley.AppConfig_Server;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Rubik on 29/7/16.
 */
public class UploadHistoy {

    private static String KEY_IMAGE = "image";
    private static String KEY_TITLE = "tittle";

    public UploadHistoy (Context context) {

    }

    public static void uploadHistoryc(final Context context, final String tittle, final String url,final View view){

            //Showing the progress dialog
       // final ProgressDialog loading = ProgressDialog.show(context,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                AppConfig_Server.URL_BASE + AppConfig_Server.URL_PUT_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        UtilsApp.showSnackBar(view,"New historical record inserted");
                        //Showing toast message of the response
                      //  Toast.makeText(getContext(), s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        //Showing toast
                      //  Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("Volley error -> ", volleyError.getMessage());
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                    //Image URL
                String image = url;//getStringImage(bitmap);
                     //Getting Image title
                String name = tittle.trim();
                     //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();
                     //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_TITLE, name);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}

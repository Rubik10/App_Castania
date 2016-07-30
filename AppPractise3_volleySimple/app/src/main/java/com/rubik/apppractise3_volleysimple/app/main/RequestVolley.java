package com.rubik.apppractise3_volleysimple.app.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rubik.apppractise3_volleysimple.app.main.fragments.HystoricFragment;
import com.rubik.apppractise3_volleysimple.app.main.fragments.ProductFragment;
import com.rubik.apppractise3_volleysimple.app.volley.AppConfig_Server;
import com.rubik.apppractise3_volleysimple.app.volley.AppController;
import com.rubik.apppractise3_volleysimple.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestVolley {

    private static final String TAG = RequestVolley.class.getSimpleName();
    private Context context;
    public static List<Product> listSelected = new ArrayList<>();

    static ProgressDialog pDialog;

    public RequestVolley (Context context) {
        this.context = context;
    }



    public static void JSONRequest(Context context) {

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Crear nueva cola de peticiones
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Nueva petición JSONObject
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                AppConfig_Server.URL_BASE + AppConfig_Server.URL_JSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, response.toString());
                        ProductFragment.listProducts = parseJsonObject(response);
                        //notifyDataSetChanged();
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                        pDialog.hide();
                    }
                }
        );

        // Añadir petición a la cola
        requestQueue.add(jsonObjectRequest);
    }

    public static void JSONArrayRequest(Context context) {

        // Crear nueva cola de peticiones
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Nueva petición JSONObject
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                AppConfig_Server.URL_BASE + AppConfig_Server.URL_GET_HISTORY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        VolleyLog.d(TAG, response.toString());
                        listSelected = parseJsonArray(response);
                        HystoricFragment.myAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        // Añadir petición a la cola
        requestQueue.add(jsonObjectRequest);
    }

    public static List<Product> parseJsonObject (JSONObject jsonObject){
         // Variables locales
        List<Product> listProducts = new ArrayList<>();
        JSONArray jsonArray = null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("items");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Product products = new Product(
                            objeto.getString("titulo"),
                            objeto.getString("imagen"),
                            objeto.getInt("uds") ,
                            objeto.getDouble("precio") ,
                            objeto.getString("descripcion")
                            );

                    listProducts.add(products);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {e.printStackTrace();}

        return listProducts;
    }

    public static List<Product> parseJsonArray (JSONArray jsonArray){
        List<Product> listProducts = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Product products = new Product (
                            objeto.getString("imgUrl"),
                            objeto.getString("tittle"),
                            Timestamp.valueOf(objeto.getString("fecha")));

                    listProducts.add(products);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        return listProducts;
    }


    public static void loadJSONImage(final ImageView imgView , String imageURl) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageLoader.get(AppConfig_Server.URL_BASE + imageURl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (null != response.getBitmap()) {
                    imgView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error al cargar la imagen: " + error.getMessage());
            }
        });
        // TODO: Probar - Load placeholder image and error image
       /* imageLoader.get(URL_BASE + imageURl, ImageLoader.getImageListener(
                imgView, R.drawable.ico_loading, R.drawable.ico_error)); */
    }



    private void getCache () {
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        cache.initialize();
        Cache.Entry entry = cache.get(AppConfig_Server.URL_BASE + AppConfig_Server.URL_JSON);

        if (entry != null) { //cache.get(URL_BASE + URL_JSON)

            String data = null;
            try {
                data = new String(entry.data, "UTF-8");
                JSONObject jsonobj = new JSONObject(new String(entry.data, "UTF-8"));
               ProductFragment.listProducts = parseJsonObject(jsonobj);

                Log.d("Extry Cache", data);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } finally {
                pDialog.hide();
            }
        }

    }


}
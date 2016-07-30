package com.rubik.apppractise3_volleysimple.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubik.apppractise3_volleysimple.R;
import com.rubik.apppractise3_volleysimple.app.volley.AppConfig_Server;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        final String nombre = intent.getExtras().getString("Title");
        final Double precio = intent.getExtras().getDouble("Precio");
        final int unidades = intent.getExtras().getInt("Unidades");
        final String descripcion = intent.getExtras().getString("Descripcion");
        final String image = intent.getExtras().getString("Imagen");

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ITALY); // xk coño no esta ESPAÑAAAA?¿?¿?¿
       // format.setCurrency(Currency.getInstance("ESP"));
        String pvpFormatter = format.format(precio);

        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        Picasso.with(this).load(AppConfig_Server.URL_BASE + image).fit().into(imageView);

        TextView txtNombre = (TextView) findViewById(R.id.txtName);
        TextView txUnidades = (TextView) findViewById(R.id.txtUnidades);
        TextView txtPvp = (TextView) findViewById(R.id.txtPvp);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        txtNombre.setText("Titulo : " + nombre);
        txUnidades.setText("Unidades : " + unidades + " uds.");
        txtPvp.setText("Precio : " + pvpFormatter  );
        txtDescription.setText("Descripcion :  "  + descripcion );

    }

}

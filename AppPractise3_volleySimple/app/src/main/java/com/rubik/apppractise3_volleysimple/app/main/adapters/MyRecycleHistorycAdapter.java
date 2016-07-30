package com.rubik.apppractise3_volleysimple.app.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubik.apppractise3_volleysimple.R;
import com.rubik.apppractise3_volleysimple.app.main.RequestVolley;
import com.rubik.apppractise3_volleysimple.model.Product;

/**
 * Created by Rubik on 29/7/16.
 */
public class MyRecycleHistorycAdapter extends RecyclerView.Adapter<MyRecycleHistorycAdapter.MyViewHolder>{

    private static final String TAG = MyRecycleHistorycAdapter.class.getSimpleName();

    public MyRecycleHistorycAdapter() { //Context context
      // listProductsSelected =  (List<Product>) activity.getIntent().getSerializableExtra("listProducts");
    }

    @Override
    public MyRecycleHistorycAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyRecycleHistorycAdapter.MyViewHolder holder, int position) {

        Product product = RequestVolley.listSelected.get(position);

        holder.txtTittle.setText(product.getTitulo());
        CharSequence fecha = (DateFormat.format("dd-MM-yyyy HH:mm", product.getFecha()));
        holder.txtFecha.setText(fecha);
        RequestVolley.loadJSONImage(holder.imageView, product.getImageURL());
    }

    @Override
    public int getItemCount() {return RequestVolley.listSelected != null ? RequestVolley.listSelected.size() : 0;}


         public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView txtTittle;
            private TextView txtFecha;
            LinearLayout borderLayout;

            public MyViewHolder(View view) {
                super(view);

                imageView = (ImageView) view.findViewById(R.id.thumbnail);
                txtTittle = (TextView) view.findViewById(R.id.title);
                txtFecha = (TextView) view.findViewById(R.id.fecha);
                txtFecha.setVisibility(View.VISIBLE);
                // borderLayout = (LinearLayout) view.findViewById(R.id.linearLayoutBoder);
            }
    }
}

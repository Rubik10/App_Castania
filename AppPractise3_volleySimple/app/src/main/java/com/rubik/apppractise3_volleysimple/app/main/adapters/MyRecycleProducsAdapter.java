package com.rubik.apppractise3_volleysimple.app.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubik.apppractise3_volleysimple.R;
import com.rubik.apppractise3_volleysimple.app.main.RequestVolley;
import com.rubik.apppractise3_volleysimple.app.main.fragments.ProductFragment;
import com.rubik.apppractise3_volleysimple.model.Product;

/**
 * Created by Rubik on 29/7/16.
 */
public class MyRecycleProducsAdapter extends RecyclerView.Adapter<MyRecycleProducsAdapter.MyViewHolder> {

  private static final String TAG = MyRecycleProducsAdapter.class.getSimpleName();
  private Context context;

 // public static List<Product> listProducts;

  public MyRecycleProducsAdapter(Context context ) { //,List<Product> listProducts
    this.context = context;
    //this.listProducts = listProducts;

    RequestVolley.JSONRequest(this.context);
  }

  @Override
  public MyRecycleProducsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(MyRecycleProducsAdapter.MyViewHolder holder, int position) {

    Product product = ProductFragment.listProducts.get(position);

    holder.txtTittle.setText(product.getTitulo());
    RequestVolley.loadJSONImage(holder.imageView, product.getImageURL());

    /*holder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, HistorycFragment.class);
        intent.putExtra("listProducts", (Serializable) listProducts);
      }
    });*/

  }

  @Override
  public int getItemCount() {return ProductFragment.listProducts != null ? ProductFragment.listProducts.size() : 0;}



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
          txtFecha.setVisibility(View.INVISIBLE);
          // borderLayout = (LinearLayout) view.findViewById(R.id.linearLayoutBoder);
        }
  }

}

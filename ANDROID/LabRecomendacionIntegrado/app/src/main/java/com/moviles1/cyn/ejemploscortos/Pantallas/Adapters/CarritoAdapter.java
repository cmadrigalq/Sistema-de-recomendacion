package com.moviles1.cyn.ejemploscortos.Pantallas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Listeners.OnAdapterListener;
import com.moviles1.cyn.ejemploscortos.Listeners.OnCarritoListener;
import com.moviles1.cyn.ejemploscortos.Pantallas.CarritoActivity;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 17/4/2018.
 */

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ProductViewHolder>
        implements OnCarritoListener {
    //this context we will use to inflate the layout
    private Context mCtx;
    CarritoActivity padre;
    //we are storing all the products in a list
    private List<Producto> productList;
    private OnAdapterListener onAdapterListener;
    double precioFinal = 0.0;

    public void setOnAdapterListener(OnAdapterListener onAdapterListener) {
        this.onAdapterListener = onAdapterListener;
    }

    //getting the context and product list with constructor
    public CarritoAdapter(Context mCtx, List<Producto> productList,CarritoActivity padre) {
        this.padre = padre;
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CarritoAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.carritolist_layout, null);
        return new CarritoAdapter.ProductViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(final CarritoAdapter.ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Producto product = productList.get(position);
        //init spinner Items
        final ArrayList<String> spinnerItems = new ArrayList<>();
        for(int i = 1; i <= 25; i++){
            spinnerItems.add(Integer.toString(i));
        }
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
        ArrayAdapter adapter = new ArrayAdapter<String>(mCtx,
                android.R.layout.simple_spinner_dropdown_item,spinnerItems);
        holder.spinner.setAdapter(adapter);
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                int cantidad =  Integer.parseInt((String) adapterView.getItemAtPosition(pos));
                double precioXcantidad = product.getPrice() * (double) cantidad;
                product.setCantidad(cantidad);
                holder.textViewPrice.setText("₡"+String.valueOf(precioXcantidad));
                CarritoAdapter.this.calcularSubTotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ...
            }
        });
    }

    public void calcularSubTotal(){
        this.padre.upDateTextViewPrecio();
    }

    @Override
    public void quitarDelCarrito(int position) {
        onAdapterListener.onDeleteProductClicked(position,productList.get(position).getTitle(),productList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void precioTotal(Double precio) {
        onAdapterListener.calcularPrecioFinal(precio);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewPrice;
        Spinner spinner;
        ImageView imageView;
        ImageButton imageButton;

        public ProductViewHolder(View itemView, final OnCarritoListener onCarritoListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            spinner = itemView.findViewById(R.id.spinner);
            imageButton = itemView.findViewById(R.id.idQuitar);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onCarritoListener.quitarDelCarrito(position);
                    }
                }
            });
        }
    }

}

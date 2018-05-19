package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Listeners.OnAdapterListener;
import com.moviles1.cyn.ejemploscortos.Pantallas.Adapters.ProductAdapter;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Producto> productoList;
    List<Producto> listaProductosCarrito;
    Model model;
    Button btnVerCarrito;
    public static final int request_Code=1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Catálogo de Productos");

        productoList = model.getProductoList();
        listaProductosCarrito = model.getListaProductosCarrito();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this, productoList);
        adapter.setOnAdapterListener(new OnAdapterListener() {
            @Override
            public void onProductoClicked(int position, String nombreProducto, int id) {
                if(estaEnCarrito(nombreProducto,id) == false) {
                    listaProductosCarrito.add(productoList.get(position));
                    Toast toast = Toast.makeText(getApplicationContext(), "Agregado: " + nombreProducto, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Producto producto = productoList.get(position);
                    for(int i = 0; i < listaProductosCarrito.size(); i++){
                        if(producto.getTitle().equals(listaProductosCarrito.get(i).getTitle())){
                            listaProductosCarrito.remove(i);
                            Toast toast = Toast.makeText(getApplicationContext(), "Removido: " + nombreProducto, Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                    }
                }
            }
            @Override
            public void calcularPrecioFinal(Double precio) {

            }

            @Override
            public void onDeleteProductClicked(int position, String title, int id) {

            }
        });
        recyclerView.setAdapter(adapter);
        btnVerCarrito =  (Button) findViewById(R.id.btnVerCarrito);
        btnVerCarrito.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, CarritoActivity.class);
                startActivity(i);
            }
        });
    }



    public boolean estaEnCarrito(String nombre, int id){
        boolean flag = false;
        for(int i = 0; i < listaProductosCarrito.size(); i++){
            if(listaProductosCarrito.get(i).getTitle().equals(nombre) &&
                    listaProductosCarrito.get(i).getId() == id){
                flag = true;
            }
        }
        return flag;
    }

}

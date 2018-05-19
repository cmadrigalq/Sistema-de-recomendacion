package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.moviles1.cyn.ejemploscortos.Entidades.Historial;
import com.moviles1.cyn.ejemploscortos.HTTPS.Proxy;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.List;

public class HistorialAct extends AppCompatActivity {
    String usuario;
    List<Historial> histo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        setTitle("Historial de compras de "+usuario);
        histo = Proxy.instancia().listarHistorial(usuario);
        cargarTabla();
    }
    private void limpiaTabla(TableLayout tabla){
        int tam = tabla.getChildCount();
        for(int i=1; i<tam; i++){
            View fila = tabla.getChildAt(i);
            if(fila instanceof TableRow)
                ((ViewGroup) fila).removeAllViews();
        }
    }
    void cargarTabla(){
        TableLayout tabla = findViewById(R.id.tabla);
        limpiaTabla(tabla);
        TableRow row;
        for(Historial h : this.histo){
            row = new TableRow(getBaseContext());
            row.addView(getTextView(h.getCompra().getProducto().getShortdesc().substring(2,17),false));
            row.addView(getTextView(h.getCompra().getCantidad().toString(),true));
            row.addView(getTextView(h.getFecha().toLocaleString(),false));
            tabla.addView(row);
        }
    }
    TextView getTextView(String val,boolean center){
        TextView nuevo = new TextView(getBaseContext());
        if(center)nuevo.setGravity(Gravity.CENTER);
        else nuevo.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
        nuevo.setPadding(15,15,15,15);
        nuevo.setText(val);

        return nuevo;
    }
}

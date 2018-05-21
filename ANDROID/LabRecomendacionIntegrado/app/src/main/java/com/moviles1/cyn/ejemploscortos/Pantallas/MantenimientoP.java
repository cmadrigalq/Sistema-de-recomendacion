package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.HTTPS.Proxy;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.List;

public class MantenimientoP extends AppCompatActivity {

    List<Producto> lp;
    Model model;
    public static final int request_Code = 1;
    Proxy proxy = Proxy.instancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_p);
        setTitle("Mantenimiento de Productos");
        lp = this.model.getProductoList();
        dibujaTabla(lp);

        Button buscar = findViewById(R.id.btnBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dibujaTabla(lp);
            }
        });

        Button nuevoUser = findViewById(R.id.btnNuevo);
        nuevoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNuevo = new Intent(MantenimientoP.this, FormP.class);
                startActivityForResult(intentNuevo, request_Code);
            }
        });
    }

    @Override
    public void finish() {
        model.setProductoList(lp);
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == request_Code) && (resultCode == RESULT_OK)) {
            if (data.hasExtra("accion")) {
                String acc = data.getStringExtra("accion");
                Producto p = (Producto) data.getExtras().getSerializable("pro");
                if (acc.equals("nuevo")) {
                    agregarP(p);
                }
                if (acc.equals("editado")) {
                    String id = data.getStringExtra("codigo");
                    editarP( p);
                }
            }
        }
    }

    private void editarP(Producto u) {
        Boolean res = proxy.updateProducto(new Integer(u.getId()).toString(), u.getTitle(), u.getShortdesc(), u.getPrice());
        if (res) {
            for (int i = 0; i < lp.size(); i++) {
                if (lp.get(i).getId() == u.getId()) {
                    lp.set(i, u);
                    break;
                }
            }
            dibujaTabla(lp);
        }
        mostrarToast("Petición enviada, por favor, verifique");
    }

    private void agregarP(Producto u) {
        Boolean res = proxy.addProducto(String.valueOf(u.getId()), u.getTitle(), u.getShortdesc(), new Double(u.getPrice()).intValue());
        if (res) {
            lp.add(u);
            dibujaTabla(lp);
        }
        mostrarToast("Petición enviada, por favor, verifique");
    }

    private void mostrarToast(String mensaje) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView tv = new TextView(MantenimientoP.this);
        tv.setBackgroundColor(Color.GREEN);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(25);
        Typeface t = Typeface.create("arial", Typeface.NORMAL);
        tv.setTypeface(t);
        tv.setPadding(10, 10, 10, 10);
        tv.setText(mensaje);
        toast.setView(tv);
        toast.show();
    }

    private void dibujaTabla(List<Producto> u) {
        TableLayout tabla = findViewById(R.id.idTable);
        limpiaTabla(tabla);
        TableRow row;
        TextView tv1;
        TextView tv2;
        ImageButton btnEdit;
        ImageButton btnElim;
        EditText filtro = super.findViewById(R.id.filtro);
        String filtroStr = filtro.getText().toString();
        TextView buscUserName = findViewById(R.id.txBuscUserName);
        for (int i = 0; i < u.size(); i++) {
            if(!filtroStr.trim().isEmpty() && !String.valueOf(u.get(i).getId()).contains(filtroStr))
                continue;
            row = new TableRow(getBaseContext());
            tv1 = new TextView(getBaseContext());
            tv2 = new TextView(getBaseContext());
            btnEdit = new ImageButton(getBaseContext());
            btnEdit.setImageResource(R.drawable.ic_if_new_24_103173);
            btnElim = new ImageButton(getBaseContext());
            btnElim.setImageResource(R.drawable.ic_if_delete_301791);
            tv1.setGravity(Gravity.CENTER);
            tv2.setGravity(Gravity.CENTER);
            tv1.setPadding(15, 15, 15, 15);
            tv2.setPadding(15, 15, 15, 15);
            tv1.setText(String.valueOf(u.get(i).getId()));
            if(u.get(i).getTitle().length() >= 10)
                tv2.setText(u.get(i).getTitle().substring(0, 10));
            else tv2.setText(u.get(i).getTitle());
            final Producto p = u.get(i);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iEditar = new Intent(MantenimientoP.this, FormP.class);
                    iEditar.putExtra("p", p);
                    iEditar.putExtra("edit", 1);
                    startActivityForResult(iEditar, request_Code);
                }
            });
            btnElim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MantenimientoP.this);
                    builder.setTitle("Eliminar").setMessage("¿Desea eliminar el Producto?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    eliminaP(p.getId());
                                }
                            })
                            .setNegativeButton("No", null).setCancelable(false);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
            row.addView(tv1);
            row.addView(tv2);
            row.addView(btnEdit);
            row.addView(btnElim);
            tabla.addView(row);

        }
    }

    private void limpiaTabla(TableLayout tabla) {
        int tam = tabla.getChildCount();
        for (int i = 1; i < tam; i++) {
            View fila = tabla.getChildAt(i);
            if (fila instanceof TableRow) {
                ((ViewGroup) fila).removeAllViews();
            }
        }
    }

    private void eliminaP(Integer id) {
        Boolean res = proxy.deleteProducto(id.toString());
        if (res) {
            for (int i = 0; i < lp.size(); i++) {
                if (lp.get(i).getId() == id) {
                    lp.remove(i);
                    break;
                }
            }
            dibujaTabla(lp);
        }
        mostrarToast("Petición enviada, por favor, verifique");
    }
}

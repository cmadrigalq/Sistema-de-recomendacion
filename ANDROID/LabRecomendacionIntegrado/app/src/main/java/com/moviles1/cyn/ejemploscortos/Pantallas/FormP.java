package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.R;

public class FormP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_productos);
        setTitle("Nuevo Producto");

        if (getIntent().hasExtra("edit")) {
            setTitle("Editar Producto");
            Producto pro = (Producto) getIntent().getExtras().getSerializable("p");
            TextView c = findViewById(R.id.codigo);
            c.setEnabled(false);
            c.setText(String.valueOf(pro.getId()));

            TextView n = findViewById(R.id.Nombre);
            n.setText(pro.getTitle());

            TextView d = findViewById(R.id.Desc);
            d.setText(pro.getShortdesc());

            TextView p = findViewById(R.id.precio);
            Integer pre = new Double(pro.getPrice()).intValue();
            p.setText(String.valueOf(pre));
        }

        Button aceptar = findViewById(R.id.btnAceptarU);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    TextView userName = findViewById(R.id.codigo);
                    userName.setEnabled(false);

                    EditText c = findViewById(R.id.codigo);
                    EditText n = findViewById(R.id.Nombre);
                    EditText d = findViewById(R.id.Desc);
                    EditText p = findViewById(R.id.precio);

                    Producto pr = new Producto();
                    pr.setId(Integer.valueOf(c.getText().toString()));
                    pr.setTitle(n.getText().toString());
                    pr.setShortdesc(d.getText().toString());
                    pr.setPrice(Integer.valueOf(p.getText().toString()));

                    Intent data = new Intent();
                    data.putExtra("pro", pr);
                    if (getIntent().hasExtra("edit")) {
                        data.putExtra("accion", "editado");
                    } else {
                        data.putExtra("accion", "nuevo");
                    }
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });

        Button cancelar = findViewById(R.id.btnCancelarU);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    public boolean validaForm() {
        boolean ok = true;
        EditText c = findViewById(R.id.codigo);
        EditText n = findViewById(R.id.Nombre);
        EditText d = findViewById(R.id.Desc);
        EditText p = findViewById(R.id.precio);
        if (c.getText().toString().equals("")) {
            ok = false;
            Toast.makeText(getApplicationContext(), "Ingrese el código", Toast.LENGTH_SHORT).show();
        }
        if (n.getText().toString().equals("")) {
            ok = false;
            Toast.makeText(getApplicationContext(), "Ingrese el nombre", Toast.LENGTH_SHORT).show();
        }
        if (d.getText().toString().equals("")) {
            ok = false;
            Toast.makeText(getApplicationContext(), "Ingrese la descripción", Toast.LENGTH_SHORT).show();
        }
        if (p.getText().toString().equals("")) {

            ok = false;
            Toast.makeText(getApplicationContext(), "Ingrese la descripción", Toast.LENGTH_SHORT).show();
        }
        try {
            Integer a = Integer.valueOf(p.getText().toString());
        }catch (Exception ex){
            ok = false;
            Toast.makeText(getApplicationContext(), "Ingrese un precio valido", Toast.LENGTH_SHORT).show();
        }
        try {
            Integer a = Integer.valueOf(c.getText().toString());
        }catch (Exception ex){
            ok = false;
            Toast.makeText(getApplicationContext(), "EL código debe ser númerico", Toast.LENGTH_SHORT).show();
        }
        return ok;
    }

}

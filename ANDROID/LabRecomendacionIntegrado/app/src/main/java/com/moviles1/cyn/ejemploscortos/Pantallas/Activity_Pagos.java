package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.HTTPS.Proxy;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity_Pagos extends AppCompatActivity {
    RadioButton rb1;
    RadioButton rb2;
    Spinner spn;
    Double total;
    Calendar mCalendar = Calendar.getInstance();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pagos);
        setTitle("PANTALLA DE PAGO");
        alt2();
        llenarMeses();
        llenarAnnos();
        Intent intent = getIntent();
        total = intent.getDoubleExtra(CarritoActivity.TOTAL_A_PAGAR,0.0);
        setTitle(String.format("Total: "+String.valueOf(total)));
        Button b = findViewById(R.id.btnPay);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(validar()){
                    ProgressBar p = Activity_Pagos.this.findViewById(R.id.progressBar);
                    p.setProgress(p.getMax());
                    mostrarMensaje("La transacción se realizó con exito! Gracias por preferirnos.");
                    Proxy proxy = Proxy.instancia();
                    for(Producto pr : Model.listaProductosCarrito){
                        proxy.addCompra(Model.getUsuarioActivo().getUserName(),
                                        String.valueOf(pr.getId()),
                                        pr.getCantidad()
                                );
                    }
                    Model.listaProductosCarrito.clear();
                    Intent i = new Intent(Activity_Pagos.this, MainActivity.class);
                    startActivity(i);
                }
            }
            
        });
    }

    boolean validar(){
        boolean result = true;
        String msg = "";
        if(!Activity_Pagos.this.rb1.isChecked() && !Activity_Pagos.this.rb2.isChecked()){
            msg += "Debe seleccionar un método de pago.";
            result = false;
        }
        TextView txt = findViewById(R.id.txt1);
        if( txt.getText().toString().trim().isEmpty()  ){
            msg += "Debe ingresar el nombre que aparece en la tarjeta.";
            result = false;
        }else{
            String vars[] = txt.getText().toString().split(" ");
            if(vars.length > 4) {
                msg += "Debe ingresar el nombre que aparece en la tarjeta.";
                result = false;
            }
        }
        if( ((TextView)findViewById(R.id.txt2)).getText().toString().trim().isEmpty()  ){
            msg += "Debe ingresar el número de la tarjeta.";
            result = false;
        }else {
            try {
                int aux = Integer.valueOf(((TextView)findViewById(R.id.txt2)).getText().toString());
            } catch (Exception ex) {
                msg += "Revise el número de tarjeta";
                result = false;
            }
        }
        TextView ccv = findViewById(R.id.cvv);
        if( ccv.getText().toString().trim() == "" && ccv.getText().length() < 3  ){
            //no esta en blanco                      tiene más de 3 digitos
            msg += "Revise el cvv.";
            result = false;
        }else {
            try {
                int aux = Integer.valueOf(ccv.getText().toString());
            } catch (Exception ex) {
                msg += "Revise el cvv.";
                result = false;
            }
        }
        if(!result)
            mostrarMensaje(msg);
        return result;
    }

    void mostrarMensaje(String msg){
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG);
        toast.show();
    }


    void alt2(){
        rb1 = (RadioButton) super.findViewById(R.id.radioCredit);
        rb2 = (RadioButton) super.findViewById(R.id.radioPaypal);
        View.OnClickListener evento = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RadioButton ck = (RadioButton)v;
                if(ck.getId() == rb1.getId()){
                    rb2.setChecked(false);
                }else{
                    rb1.setChecked(false);
                }
            }
        };
        rb1.setOnClickListener(evento);
        rb2.setOnClickListener(evento);
    }
    void llenarMeses(){
        List<String> meses = new ArrayList<>();
        for(int i = 1; i < 13 ; i++){
            meses.add(String.valueOf(i));
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,meses);
        ((Spinner)findViewById(R.id.spin2)).setAdapter(adapter);
    }
    void llenarAnnos(){
        int current = mCalendar.get(Calendar.YEAR);
        List<String> annos = new ArrayList<>();
        for(int i = current; i<current+5;i++){
            annos.add(String.valueOf(i));
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,annos);
        ((Spinner)findViewById(R.id.spin1)).setAdapter(adapter);
    }
    
    
    
}


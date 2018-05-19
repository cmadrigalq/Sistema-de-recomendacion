package com.moviles1.cyn.ejemploscortos.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Entidades.Usuario;
import com.moviles1.cyn.ejemploscortos.R;

import java.util.ArrayList;
import java.util.List;

public class FormularioUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);
        setTitle("Nuevo Usuario");
        dibujaSpinerTipoUsuario("");

        if(getIntent().hasExtra("usuarioEdit")){
            setTitle("Editar Usuario");
            Usuario usuarioEditable = (Usuario) getIntent().getExtras().getSerializable("usuarioEdit");
            TextView userName = findViewById(R.id.txUserName);
            TextView contrasena = findViewById(R.id.txContrasena);
            userName.setText(usuarioEditable.getUserName());
            contrasena.setText(usuarioEditable.getPassword());
            Spinner spin = findViewById(R.id.spinTipoPerfil);
            int tipoUsuario = usuarioEditable.getTipo();
            String seleccionado = "Administrador";
            if(tipoUsuario == 2)
                seleccionado = "Cliente";
            spin.setSelection(getIndex(spin, seleccionado));
        }

        Button aceptar = findViewById(R.id.btnAceptarU);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaForm()) {
                    TextView userName = findViewById(R.id.txUserName);
                    userName.setEnabled(false);
                    TextView contrasena = findViewById(R.id.txContrasena);
                    Spinner spin = findViewById(R.id.spinTipoPerfil);
                    String seleccionado = spin.getSelectedItem().toString();
                    String ced = userName.getText().toString();
                    String pass = contrasena.getText().toString();
                    int tipoUsuario = 2;
                    if ("Administrador".equals(seleccionado))
                        tipoUsuario = 1;

                    Usuario u = new Usuario(ced, pass,new ArrayList<Producto>(), tipoUsuario);

                    Intent data = new Intent();
                    if (getIntent().hasExtra("usuarioEdit")) {
                        Usuario userEditado = (Usuario) getIntent().getExtras().getSerializable("usuarioEdit");
                        data.putExtra("accion", "editado");
                        data.putExtra("UserName", userEditado.getUserName());
                        data.putExtra("ContraseñaUsuario", userEditado.getPassword());
                        data.putExtra("UsuarioEditado", u);
                        setResult(RESULT_OK, data);
                        finish();
                    } else {
                        data.putExtra("accion", "nuevo");
                        data.putExtra("NuevoUsuario", u);
                        setResult(RESULT_OK, data);
                        finish();
                    }
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

    public boolean validaForm(){
        boolean ok=true;
        EditText tvCed = findViewById(R.id.txUserName);
        EditText tvCont = findViewById(R.id.txContrasena);
        Spinner spinPerf = findViewById(R.id.spinTipoPerfil);
        //Valida UserName
        if(tvCed.getText().toString().equals("")){
            ok=false;
            tvCed.setError("Escriba el nombre de usuario");
            tvCed.requestFocus();
        }
        //Valida Contraseña
        if(tvCont.getText().toString().equals("")){
            ok=false;
            tvCont.setError("Escriba la Contraseña");
            tvCont.requestFocus();
        }
        //Valida Perfil
        if(spinPerf.getSelectedItem().toString().equals("")){
            ok=false;
            TextView errorPerfiles = (TextView)spinPerf.getSelectedView();
            errorPerfiles.setError("Seleccione un Perfil");
            errorPerfiles.requestFocus();
        }
        return ok;
    }

    private void dibujaSpinerTipoUsuario(String seleccionado){
        Spinner spiner = findViewById(R.id.spinTipoPerfil);
        List<String> values = new ArrayList<>();
        int posSeleccionado = 0;
        values.add("Administrador");
        values.add("Cliente");
        if("Cliente".equals(seleccionado))
            posSeleccionado=1;
        ArrayAdapter<String> comboAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values);
        spiner.setAdapter(comboAdapter);
        spiner.setSelection(posSeleccionado);
    }

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}

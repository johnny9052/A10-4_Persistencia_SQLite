package ejemplofinalgeoreferenciacion.johnny.a10_4_persistencia_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Controlador.CtlUsuario;
import Modelo.Usuario;


public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCedula, txtEdad;
    CtlUsuario controlador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtEdad = (EditText) findViewById(R.id.txtEdad);

        controlador = new CtlUsuario(this);
    }


    public void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
    }

    public void guardar(View view) {
        String cedula = txtCedula.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        int edad = Integer.parseInt(txtEdad.getText().toString());

        if (controlador.guardarUsuario(cedula, nombre, apellido, edad)) {
            Toast.makeText(this, "Almacenado correctamente", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "Error almacenando informacion",
                    Toast.LENGTH_SHORT).show();
        }

        limpiar();
    }

    public void consulta(View v) {
        String cedula = txtCedula.getText().toString();
        Usuario usuario = controlador.buscarUsuario(cedula);
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtEdad.setText(usuario.getEdad() + "");
        } else {
            Toast.makeText(this, "No se encuentra", Toast.LENGTH_SHORT).show();
        }
    }

    public void baja(View view) {

        String cedula = txtCedula.getText().toString();

        if (controlador.eliminarUsuario(cedula)) {
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "No se encuentra", Toast.LENGTH_SHORT).show();
        }

        limpiar();

    }

    public void modificacion(View v) {

        String cedula = txtCedula.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        int edad = Integer.parseInt(txtEdad.getText().toString());

        if (controlador.modificarUsuario(cedula, nombre, apellido, edad)) {
            Toast.makeText(this, "Modificado exitosamente", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "No se encuentra", Toast.LENGTH_SHORT).show();
        }

        limpiar();

    }

    public void listar(View view) {
        Intent intent = new Intent(this, ListadoUsuariosActivity.class);
        startActivity(intent);
    }

}

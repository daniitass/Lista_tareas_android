package com.example.lista_tareas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias UI
        TextInputLayout textInputLayoutTitulo = findViewById(R.id.textInputLayoutTitulo);
        TextInputEditText editTextTitulo = findViewById(R.id.editTextTitulo);
        MaterialSwitch switchUrgente = findViewById(R.id.switchUrgente);
        Spinner spinnerEstado = findViewById(R.id.spinnerEstado);
        MaterialButton btnGuardar = findViewById(R.id.btnGuardar);
        MaterialButton btnCancelar = findViewById(R.id.btnCancelar);

        // Opciones del Spinner
        String[] estados = {"Pendiente", "En progreso", "Completada", "No Aplica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // Botón Guardar
        btnGuardar.setOnClickListener(v -> {
            String titulo = editTextTitulo.getText() != null ? editTextTitulo.getText().toString().trim() : "";

            if (titulo.isEmpty()) {
                textInputLayoutTitulo.setError("El título no puede estar vacío");
                return;
            } else {
                textInputLayoutTitulo.setError(null);
            }

            String urgente = switchUrgente.isChecked() ? "Sí" : "No";
            String estado = spinnerEstado.getSelectedItem().toString();

            // Ejemplo de autor fijo
            String autor = "Juan Pérez";
            StringBuilder inicialesBuilder = new StringBuilder();
            for (String parte : autor.split(" ")) {
                if (!parte.isEmpty()) {
                    inicialesBuilder.append(parte.charAt(0));
                }
            }
            String iniciales = inicialesBuilder.toString();

            // Mensaje multilínea
            String mensaje = "Tarea guardada correctamente ✔\n"
                    + "Título: " + titulo + "\n"
                    + "Autor: " + iniciales + "\n"
                    + "Urgente: " + urgente + "\n"
                    + "Estado: " + estado;

            // Mostrar AlertDialog
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Recordatorio guardado ✔")
                    .setMessage(mensaje)
                    .setPositiveButton("OK", null)
                    .show();
        });

        // Botón Cancelar -> cerrar app
        btnCancelar.setOnClickListener(v -> finishAffinity());
    }
}

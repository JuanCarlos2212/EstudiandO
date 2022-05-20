package com.example.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordatorioDocente extends AppCompatActivity {

    Button agregarRecordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio_docente);
        agregarRecordatorio = (Button) findViewById(R.id.buttonAgregarDocente);

        agregarRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),agregar_recordatorio.class);
                startActivity(intent);
            }
        });
    }
}
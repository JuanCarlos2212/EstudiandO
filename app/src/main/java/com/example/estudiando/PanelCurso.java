package com.example.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PanelCurso extends AppCompatActivity {

    Button buttonTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_curso);
        buttonTareas = (Button) findViewById(R.id.button4);

        buttonTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecordatorioDocente.class);
                startActivity(intent);
            }
        });
    }
}
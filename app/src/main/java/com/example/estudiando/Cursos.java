package com.example.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cursos extends AppCompatActivity {

    Button buttonmatematica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        buttonmatematica = (Button) findViewById(R.id.buttonMatematica);

        buttonmatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PanelCurso.class);
                startActivity(intent);
            }
        });

    }
}
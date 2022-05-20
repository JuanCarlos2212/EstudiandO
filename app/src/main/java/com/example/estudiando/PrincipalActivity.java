package com.example.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;


public class PrincipalActivity extends AppCompatActivity {

    ImageView imageViewCurso, imageViewHorario ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        imageViewCurso = (ImageView) findViewById(R.id.imageViewCursos);
        imageViewHorario = (ImageView) findViewById(R.id.imageViewHorario);

        imageViewCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Cursos.class);
                startActivity(intent);
            }
        });
        imageViewHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "falta implementar este boton", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void buscarCursos(String URL){

    }



}
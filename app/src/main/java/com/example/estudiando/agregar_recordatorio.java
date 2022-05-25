package com.example.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class agregar_recordatorio extends AppCompatActivity {

    EditText edtTitulo ,edtRecordatorio;
    Button buttonAgregar,buttonCancelar;
    ImageView imgVmicTitulo, imgVmicRecortatorio;
    private static final int REQ_CODE_SPEECH_INPUT=100;
    private static final int REQ_CODE_SPEECH_INPUT_2=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recordatorio);
        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtRecordatorio =(EditText) findViewById(R.id.edtRecordatorio);
        buttonAgregar = (Button) findViewById(R.id.buttonAgregar);
        buttonCancelar =(Button) findViewById(R.id.buttonCancelar);
        imgVmicRecortatorio = (ImageView) findViewById(R.id.imgVMicrecordatorio);
        imgVmicTitulo = (ImageView) findViewById(R.id.imgVMicTitulo);


        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio( "http://192.168.1.7:8080/appMobileEstudiando/insertar_Recordatorio.php");
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecordatorioDocente.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        imgVmicRecortatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { iniciarEntradaVozRecordatorio();}
        });
        imgVmicTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { iniciarEntradaVozTitulo(); }
        });
    }

    private void iniciarEntradaVozTitulo() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());//idioma del telefono
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Titulo del recordatorio");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtTitulo.setText(result.get(0));

                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_2:{
                if(resultCode==RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtRecordatorio.setText(result.get(0));

                }
                break;
            }
        }
    }
    private void iniciarEntradaVozRecordatorio() {

        Intent intento = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intento.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intento.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());//idioma del telefono
        intento.putExtra(RecognizerIntent.EXTRA_PROMPT, "Descripcion del recordatorio");
        try {
            startActivityForResult(intento,REQ_CODE_SPEECH_INPUT_2);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }



    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Agregado Exitosamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("titulo",edtTitulo.getText().toString());
                parametros.put("descripcion",edtRecordatorio.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
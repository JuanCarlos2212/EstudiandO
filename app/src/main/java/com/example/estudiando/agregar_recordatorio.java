package com.example.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class agregar_recordatorio extends AppCompatActivity {

    EditText edtTitulo ,edtRecordatorio;
    Button buttonAgregar,buttonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recordatorio);
        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtRecordatorio =(EditText) findViewById(R.id.edtRecordatorio);
        buttonAgregar = (Button) findViewById(R.id.buttonAgregar);
        buttonCancelar =(Button) findViewById(R.id.buttonCancelar);

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
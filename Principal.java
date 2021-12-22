package com.example.probando;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void jugador1(View view){
        ArrayList<Formulario> lista = new ArrayList<>();
        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lista);  //Envio un array vacio para que no explote el programa, ya que la siguiente actividad espera un array
        intent.putExtra("BUNDLE",args);
        String mensaje = "no";
        intent.putExtra("si", mensaje);
        startActivity(intent);
    }

    public void jugador2(View view) {
        ArrayList<Formulario> lista = new ArrayList<>();
        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lista);
        intent.putExtra("BUNDLE",args);
        String mensaje = "ok";
        intent.putExtra("si", mensaje);
        startActivity(intent);
    }
    public void generar(View view){
        ArrayList<Formulario> lista = new ArrayList<>();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lista);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
    public void salir(View view){

        ActivityCompat.finishAffinity(this);    //Salir de la app
    }
}
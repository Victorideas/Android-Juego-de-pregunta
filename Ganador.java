package com.example.probando;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Ganador extends AppCompatActivity {
    TextView jugador;
    TextView Contador;
    TextView hasGanado;
    ArrayList<Formulario> lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);
        jugador = (TextView) findViewById(R.id.T_jugador);
        Contador = (TextView) findViewById(R.id.T_Puntos);
        hasGanado = (TextView) findViewById(R.id.hasganado);
        Intent intent = getIntent();
        String mensaje = intent.getStringExtra(Preguntas.pasarGanador); //Recibimos el ganador de la partida
        String punt = intent.getStringExtra(Preguntas.pasarPuntos);     //Recibimos los puento de la partida
        if (mensaje.equals("Empate")){
            hasGanado.setVisibility(View.INVISIBLE);    //Si es empate ocualtamos el texto de ¡Has ganado!
        }
        jugador.setText(mensaje);
        Contador.setText(punt + " puntos.");    //Imprimimos los resultados del ganador

        //Recibo el array con las preguntas creadas
        Bundle args = intent.getBundleExtra("BUNDLE");
        lis = (ArrayList<Formulario>) args.getSerializable("ARRAYLIST");



    }
    public void volver(View view){ //Boton Jugador1

        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lis);
        intent.putExtra("BUNDLE",args);
        String numJugadores = "No";  //Indica que es solo un jugador
        intent.putExtra("si", numJugadores); // Manda la orden
        startActivity(intent);
    }
    public void volver2(View view){  //Boton Jugador2

        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lis);
        intent.putExtra("BUNDLE",args);
        String numJugadores = "ok";  //Indica que son 2 jugadores
        intent.putExtra("si", numJugadores); // Manda la orden
        startActivity(intent);
    }
    public void generar(View view){  //Boton Crear nuevas preguntas
        Intent intent = new Intent(this, MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lis);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
    public void exit (View view){
        ActivityCompat.finishAffinity(this);
    }  //Boton salir de la app
}
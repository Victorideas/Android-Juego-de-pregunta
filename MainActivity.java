package com.example.probando;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText T_Pregunta;
    EditText T_Respuesta1;
    EditText T_Respuesta2;
    EditText T_Respuesta3;
    EditText T_correcta;
    Button botton;

    ArrayList <Formulario> lista;
    ArrayList <Formulario> lisRegreso; //Lista de preguntas que viene de Ganador.class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T_Pregunta = (EditText) findViewById(R.id.TextPregunta);
        T_Respuesta1 = (EditText) findViewById(R.id.TextRespuesta1);
        T_Respuesta2 = (EditText) findViewById(R.id.TextRespuesta2);
        T_Respuesta3 = (EditText) findViewById(R.id.TextRespuesta3);
        T_correcta = (EditText) findViewById(R.id.Text_correcto);
        botton = (Button) findViewById(R.id.boton);

        Intent intent = getIntent();    //Recibo el array con las preguntas creadas
        Bundle args = intent.getBundleExtra("BUNDLE");
        lisRegreso = (ArrayList<Formulario>) args.getSerializable("ARRAYLIST");
        int b = lisRegreso.size();      //Mido el tamaño del array recibido
        String mensaj = String.valueOf(b);
        Toast.makeText(this, mensaj, Toast.LENGTH_SHORT).show(); //Muestro por pantalla cuantas preguntas se han creado
        lista = new ArrayList<Formulario>(lisRegreso); //Creamos la lista inicial  + la lista que recibimos
    }

    public void llamada (View view){

        String pregunta = T_Pregunta.getText().toString();  //Cojo los datos de cada caja de texto
        String respuesta1 = T_Respuesta1.getText().toString();
        String respuesta2 = T_Respuesta2.getText().toString();
        String respuesta3 = T_Respuesta3.getText().toString();
        String correcta = T_correcta.getText().toString();

        lista.add(new Formulario(pregunta, respuesta1, respuesta2, respuesta3, correcta)); //Guardo la pregunta
        int b = lista.size();
        String mensaj = String.valueOf(b);
        Toast.makeText(this, mensaj, Toast.LENGTH_SHORT).show(); //Muestro por pantalla cuantas preguntas se han creado
        T_Pregunta.setText("");
        T_Respuesta1.setText("");
        T_Respuesta2.setText("");       //Limpio campos de texto para seguir agregando preguntas
        T_Respuesta3.setText("");
        T_correcta.setText("");

    }
    public void principal(View view){
        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lista); //Enviamos el array con las preguntas creadas
        intent.putExtra("BUNDLE",args);
        String mensaje = "No";                  //Indica que es solo un jugador
        intent.putExtra("si", mensaje);   // Manda la orden
        startActivity(intent);
    }
    public void Juga2Preguntas(View view){
        Intent intent = new Intent(this, Preguntas.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)lista);
        intent.putExtra("BUNDLE",args);
        String mensaje = "ok";                  //Indica que son dos jugadores
        intent.putExtra("si", mensaje);   //Manda la orden
        startActivity(intent);
    }

}
package com.example.probando;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Preguntas extends AppCompatActivity {
    ArrayList<Formulario> lista;
    int[] mostradas;    //Array donde se guardan las preguntas que ya han salido
    int longitud;       //Se guarda el nº de preguntas que existen
    int pregu;          //Nos indica que pregunta (posicion de array), ha salido por pantalla
    int puntos = 0;     //Puntos jugador 1
    int puntos2 = 0;    //Puntos jugador 2
    int conta = 0;      //Controla que todas las preguntas salen
    boolean turno = true; //Indica que jugador esta activo True = Jugador 1
    TextView T_Pregunta;
    Button T_Respuesta1;
    Button T_Respuesta2;
    Button T_Respuesta3;
    TextView T_Contador1;
    TextView T_Contador2;
    TextView T_Jugador1;
    TextView T_Jugador2;
    TextView Final;
    static String pasarGanador = "hola";    //Pasa el jugador que ha ganado la partida a otra actividad
    static String pasarPuntos = "adios";    //Pasa puntos ganador
    String men; //Guarda el numero de jugadores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        T_Pregunta = (TextView) findViewById(R.id.Pregunta);
        T_Respuesta1 = (Button) findViewById(R.id.R1);
        T_Respuesta2 = (Button) findViewById(R.id.R2);
        T_Respuesta3 = (Button) findViewById(R.id.R3);
        T_Contador1 = (TextView) findViewById(R.id.contador1);
        T_Contador2 = (TextView) findViewById(R.id.contador2);
        T_Jugador1 = (TextView) findViewById(R.id.Jugador1);
        T_Jugador2 = (TextView) findViewById(R.id.Jugador2);

        Intent intent = getIntent();    //Recibo el array con las preguntas creadas
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Formulario> lis = (ArrayList<Formulario>) args.getSerializable("ARRAYLIST");


        men = intent.getStringExtra("si"); // Recibo el mensaje de cuantos jugadores van a jugar

        if (men.equals("ok")) {  //Si juegan 2 personas activo el menu para dos jugadores
            T_Jugador2.setVisibility(View.VISIBLE);
            T_Contador2.setVisibility(View.VISIBLE);
        }
        lista = new ArrayList<>(lis);   //Creo array nuevo y le sumo las preguntas creadas

        if (lista.size() < 10) {  // Comprobacion si es un array nuevo o uno existente

            lista.add(new Formulario("¿Un Byte son?", "16 Bits", "8 Bits", "32 Bits", "2"));
            lista.add(new Formulario("¿En que año el hombre piso la Luna por primera vez?", "1851", "1935", "1969", "3"));
            lista.add(new Formulario("¿Cual es la red social mas usada del mundo?", "Instagram", "Twiter", "Facebook", "3"));
            lista.add(new Formulario("¿Cual es el unico mamifero que no puede saltar?", "Jirafa", "Elefante", "Cocodrilo", "2"));
            lista.add(new Formulario("¿Que marca vende mas telefonos en el mundo?", "Samsung", "Apple", "Xiaomi", "1"));
            lista.add(new Formulario("¿En que año se descubrio America?", "1582", "1492", "1482", "2"));
            lista.add(new Formulario("¿Cuántos estados tiene integrados Estados Unidos?", "72", "42", "50", "3"));
            lista.add(new Formulario("¿Cual es el idioma mas hablado del mundo?", "El chino", "El español", "El ingles", "1"));
            lista.add(new Formulario("¿Cuál es el río más largo de España?", "El Tajo", "El Duero", "El Ebro", "3"));
            lista.add(new Formulario("¿Cuál es el pais mas pequeño del mundo?", "Andorra", "El Vaticano", "Cuba", "2"));
            longitud = lista.size();

        } else {
            lista = lis;
            longitud = lista.size();

        }


        mostradas = new int[longitud]; //Creo un array con todas las posiciones a 0 y cuando las preguntas salen, se cambia su valor a 1 y asi no se repiten
        for (int i = 0; i < longitud; i++) {
            mostradas[i] = 0;
        }
        pregu = leer(); //Muestra la pregunta por pantalla y guarda la posicion del array para consultar la respuesta correcta

        T_Jugador1.setBackgroundColor(R.color.purple_200);  //Ponemos la marca en el jugador 1
        T_Jugador2.setBackgroundColor(Color.TRANSPARENT);
    }

    public int leer() {
        int a = 0;

        do {
            a = (int) (Math.random() * longitud); //Crea un numero aleatorio entre el numero de preguntas existentes
            if (mostradas[a] == 0) { //Comprueba si ha salido o no. Si es =0 es que aun no ha salido

                mostradas[a] = 1;
                T_Pregunta.setText(lista.get(a).getPregunta());     //Carga la pregunta y sus respuestas
                T_Respuesta1.setText(lista.get(a).getRespuesta1());
                T_Respuesta2.setText(lista.get(a).getRespuesta2());
                T_Respuesta3.setText(lista.get(a).getRespuesta3());
                break;
            }
        } while (conta != longitud); //Longitus = al numero d preguntas que hay. mientras sea diferente es que aun no han salido todas
        conta++;
        if ((conta - 1) == longitud) { //Controlo que se hayan mostrado todas las preguntas
            int jugador1 = Integer.parseInt(T_Contador1.getText().toString()); //Compruebo los puntos
            int jugador2 = Integer.parseInt(T_Contador2.getText().toString());

            if (jugador1 > jugador2) { //Si el Jugador 1 tiene mas puntos
                String ganador = T_Jugador1.getText().toString();
                String pun = T_Contador1.getText().toString();
                Intent intent = new Intent(this, Ganador.class);
                intent.putExtra(pasarGanador, ganador);
                intent.putExtra(pasarPuntos, pun);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) lista);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            } else if (jugador2 > jugador1) { //Si el Jugador 2 tiene mas puntos
                String ganado = T_Jugador2.getText().toString();
                String pun2 = T_Contador2.getText().toString();
                Intent intent = new Intent(this, Ganador.class);
                intent.putExtra(pasarGanador, ganado);
                intent.putExtra(pasarPuntos, pun2);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) lista);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            } else {    //Empate
                String ganad = "Empate";
                String pun3 = T_Contador2.getText().toString();
                Intent intent = new Intent(this, Ganador.class);
                intent.putExtra(pasarGanador, ganad);
                intent.putExtra(pasarPuntos, pun3);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) lista);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            }

        }
        return a;
    }

    @SuppressLint("ResourceAsColor")
    public void respuesta(String n) {
        String correct = lista.get(pregu).getCorrecto(); //Obtengo la respuesta correcta
        if (correct.equals(n)) {    //Compruebo si coincide con la marcada por el jugador
            if (men.equals("ok")) { //Comprueblo si son 2 jugadores

                if (turno == true) {    //Jugador 1
                    puntos = puntos + 100;
                    turno = false;
                    String valor = String.valueOf(puntos);
                    T_Contador1.setText(valor);
                    T_Jugador2.setBackgroundColor(R.color.purple_200); //El color cambio de jugador
                    T_Jugador1.setBackgroundColor(Color.TRANSPARENT);

                } else {    //Jugador 2
                    puntos2 = puntos2 + 100;
                    turno = true;
                    String valor2 = String.valueOf(puntos2);
                    T_Contador2.setText(valor2);
                    T_Jugador1.setBackgroundColor(R.color.purple_200);  //El color cambio de jugador
                    T_Jugador2.setBackgroundColor(Color.TRANSPARENT);
                }
            } else {    //Cuenta los puntos si solo juega un jugador
                puntos = puntos + 100;
                String valor = String.valueOf(puntos);
                T_Contador1.setText(valor);
            }
        } else {    //Si la respuesta no es correcta, cambia el turno del jugador
            if ((men.equals("ok")) && turno == true) {
                turno = false;
                T_Jugador2.setBackgroundColor(R.color.purple_200);
                T_Jugador1.setBackgroundColor(Color.TRANSPARENT);
            } else {
                turno = true;
                T_Jugador1.setBackgroundColor(R.color.purple_200);
                T_Jugador2.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        pregu = leer(); //Lanza nueva pregunta
    }

    public void respuesta1(View view) {     //Respuesta que marca el jugador en la pantalla
        respuesta("1");
    }

    public void respuesta2(View view) {
        respuesta("2");
    }

    public void respuesta3(View view) {
        respuesta("3");
    }
}
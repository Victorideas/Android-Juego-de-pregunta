package com.example.probando;

import java.io.Serializable;

public class Formulario implements Serializable {
    String pregunta;
    String respuesta1;
    String respuesta2;
    String respuesta3;
    String correcto;


    Formulario (String a, String b, String c, String d, String e){
        this.pregunta = a;
        this.respuesta1 = b;
        this.respuesta2 = c;
        this.respuesta3 = d;
        this.correcto = e;
    }

    public String getCorrecto() {
        return correcto;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }
}

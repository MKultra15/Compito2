package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio {
    String nome;
    ArrayList <Persona> persone = new ArrayList<>();

    public Messaggio(String nome, ArrayList<Persona> persone) {
        this.nome = nome;
        this.persone = persone;
    }

    public Messaggio() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Persona> getPersone() {
        return persone;
    }

    public void setPersone(ArrayList<Persona> persone) {
        this.persone = persone;
    }

}

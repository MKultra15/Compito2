package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio {
    String nomeNazione;
    ArrayList <Persona> persone = new ArrayList<>();

    public Messaggio(String nomeNazione, ArrayList<Persona> persone) {
        this.nomeNazione = nomeNazione;
        this.persone = persone;
    }

    public Messaggio() {
    }

    public String getNomeNazione() {
        return nomeNazione;
    }

    public void setNomeNazione(String nomeNazione) {
        this.nomeNazione = nomeNazione;
    }

    public ArrayList<Persona> getPersone() {
        return persone;
    }

    public void setPersone(ArrayList<Persona> persone) {
        this.persone = persone;
    }

}

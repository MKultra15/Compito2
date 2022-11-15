package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    String nomeServer = "localhost";
    int portaServer = 1510;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    ObjectMapper mapper = new ObjectMapper();
    String selezione = null;
    Persona uno = new Persona("livia", "filip", "Romania");
    Persona due = new Persona("mirko", "simoncini", "Italia");
    Persona tre = new Persona("sofia", "rossi", "Polonia");
    
    public Socket connetti()
    {
        try
        {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            System.out.println("CLIENT: connesso cosa vuoi fare: 1) AGGIUNGI PERSONE 2) RICHIESTA ELENCO PER NAZIONE 3) RICHIESTA ELENCO COMPLETO \n");
            selezione = tastiera.readLine();
            if(selezione == "1"){
                this.aggiungi();
            }else if(selezione == "2"){
                this.
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connesione!");
            System.exit(1);
        }

        return miosocket;
    }

    public void aggiungi(){
        try{
            ArrayList<Persona> persone = new ArrayList<>();
            persone.add(uno);
            persone.add(tre);

            Messaggio m = new Messaggio(null , persone);
            outVersoServer.writeBytes(mapper.writeValueAsString(m) + "\n");
            System.out.println("CLIENT: Persone inviate");
            System.out.println("CLIENT: Attesa risposta server");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

}

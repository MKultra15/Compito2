package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
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
    String nazione = null;
    int s = 0;
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
            System.out.println("CLIENT: connesso cosa vuoi fare: 1) AGGIUNGI PERSONE 2) RICHIESTA ELENCO PER NAZIONE 3) RICHIESTA ELENCO COMPLETO 4) DISCONNETTI\n");
            selezione = tastiera.readLine();
            s = Integer.parseInt(selezione);
            if(s == 1){
                this.aggiungi();
            }else if(s == 2){
                System.out.println("CLIENT: inserisci la nazione: ");
                nazione = tastiera.readLine();
                this.richiediNazione();
            }else if(s == 3){
                this.listaIntera();
            }else if(s == 4){
                System.out.println("CLIENT: in chiusura");
                miosocket.close();
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
            String stringa_ricevuta = inDalServer.readLine();
            Messaggio a = mapper.readValue(stringa_ricevuta, Messaggio.class);
            if(a.getNomeNazione() == null && a.getPersone() == null){
                System.out.println("CLIENT: Il server ha aggiunto");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void richiediNazione(){
        try{
            Messaggio m = new Messaggio(nazione , null);
            outVersoServer.writeBytes(mapper.writeValueAsString(m) + "\n");
            System.out.println("CLIENT: Nazione richiesta");
            String stringa_ricevuta = inDalServer.readLine();
            Messaggio a = mapper.readValue(stringa_ricevuta, Messaggio.class);
            for(int i = 0; i < a.getPersone().size(); i++){
                System.out.println("nome: " + a.getPersone().get(i).getNome() + " cognome: " + a.getPersone().get(i).getCognome() + " nazione: " + a.getPersone().get(i).getNazioneDiResidenza());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void listaIntera(){
        try{
            Messaggio m = new Messaggio(null , null);
            outVersoServer.writeBytes(mapper.writeValueAsString(m) + "\n");
            System.out.println("CLIENT: Lista richiesta");
            String stringa_ricevuta = inDalServer.readLine();
            Messaggio a = mapper.readValue(stringa_ricevuta, Messaggio.class);
            for(int i = 0; i < a.getPersone().size(); i++){
                System.out.println("nome: " + a.getPersone().get(i).getNome() + " cognome: " + a.getPersone().get(i).getCognome() + " nazione: " + a.getPersone().get(i).getNazioneDiResidenza());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}

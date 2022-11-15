package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Persona> anagrafe = new ArrayList<>();

    public ServerThread(Socket c){
        this.client = c;
    }

    public void run(){
        try {
            this.comunica();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void comunica () throws Exception
    {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            String stringa_ricevuta = inDalClient.readLine();
            Messaggio messaggio = mapper.readValue(stringa_ricevuta, Messaggio.class);
            if (messaggio.getnomeNazione() == null){
                for(int i = 0; i < messaggio.getPersone().size(); i++){
                    anagrafe.add(messaggio.getPersone().get(i));
                }
                System.out.println("SERVER: Le persone sono state aggiunte");
                Messaggio m = new Messaggio(null , null);
                outVersoClient.writeBytes(mapper.writeValueAsString(m) + "\n");
            } else if(messaggio.getnomeNazione() == null && messaggio.getPersone() == null){
                System.out.println("SERVER: Invio lista delle persone");
                Messaggio a = new Messaggio(null , anagrafe);
                outVersoClient.writeBytes(mapper.writeValueAsString(a) + "\n");
            }else{
                ArrayList<Persona> nazionespecifica = new ArrayList<>();
                for(int i = 0; i < anagrafe.size(); i++){
                    if(anagrafe.get(i).getNazioneDiResidenza() == messaggio.getnomeNazione()){
                        nazionespecifica.add(anagrafe.get(i));
                    }
                }
                System.out.println("SERVER: Invio lista delle persone provenienti da: " + messaggio.getnomeNazione());
                Messaggio n = new Messaggio(null , nazionespecifica);
                outVersoClient.writeBytes(mapper.writeValueAsString(n) + "\n");
            }
        }
    }
}

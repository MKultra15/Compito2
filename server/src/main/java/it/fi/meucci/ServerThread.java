package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket){
        this.client = socket;
        try
        {
            server = new ServerSocket(1510);
        }
        catch(Exception e)
        {
            System.out.println("SERVER: server off");
        }
    }

    public void run()
    {
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public Socket attendi()
    {
        try
        {
            System.out.println("SERVER: avviato");
            client = server.accept();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("SERVER: errore!");
            System.exit(1);
        }
        return client;
    }

    public void comunica () throws Exception
    {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
        }
    }
}

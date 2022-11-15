package it.fi.meucci;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
    private ServerSocket serverSocket;      // socket
    private ArrayList<ServerThread> lista_thread = new ArrayList<>();

    public MainServer(){
    }

    public void avvia(){
        try {
            System.out.println("Avvio");
            this.serverSocket = new ServerSocket(1510);

            while (true) {                              

                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                this.lista_thread.add(thread);
                thread.start();                

            }

        } catch (Exception e) {
            System.out.println("SERVER: spento");
        }        
    }
}

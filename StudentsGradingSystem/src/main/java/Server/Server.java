package Server;

import Database.connectionDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

class Server
    {
    static connectionDB connection = new connectionDB();
    static Connection con = null;

    public static void main(String[] args)
        {
        ServerSocket server = null;
        con = connection.connectDB();
        try
            {

            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            while (true)
                {

                Socket client = server.accept();

                System.out.println("New client connected" + client.getInetAddress().getHostAddress());

                ClientHandler clientSock = new ClientHandler(client);

                new Thread(clientSock).start();
                }
            }
        catch (IOException e)
            {
            e.printStackTrace();
            } finally
            {
            if (server != null)
                {
                try
                    {
                    server.close();
                    }
                catch (IOException e)
                    {
                    e.printStackTrace();
                    }
                }
            }
        }
    }
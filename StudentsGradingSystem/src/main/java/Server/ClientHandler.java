package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class ClientHandler implements Runnable
    {
    private PrintWriter out = null;
    private BufferedReader in = null;
    private final Socket clientSocket;
    private  String name, password, coures;
    private  int id ;

    // Constructor
    public ClientHandler(Socket socket)
        {
        this.clientSocket = socket;
        }

    public void run()
        {
        try
            {
            // get the outputstream of client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            login();
            do
                {
                sendCourse(id);
                sendInformation();
                } while (in.readLine().equalsIgnoreCase("return"));

            }
        catch (IOException e)
            {
            e.printStackTrace();
            } finally
            {
            try
                {
                if (out != null)
                    {
                    out.close();
                    }
                if (in != null)
                    {
                    in.close();
                    clientSocket.close();
                    }
                }
            catch (IOException e)
                {
                e.printStackTrace();
                }
            }
        }

    private void login()
        {
        try
            {
            name = in.readLine();
            password = in.readLine();
            if (Service.isStudent(name, password))
                {
                id = Service.getID(name, password);
                out.println("Valid");
                }
            else
                {
                out.println("Invalid");
                login();
                }
            }
        catch (Exception e)
            {
            throw new RuntimeException(e);
            }
        }
    private void sendCourse(int id)
        {
        List<String> courses = Service.getCourses(id);
        int i =0;
        while (i<courses.size())
            {
            out.println(courses.get(i));
            i++;
            }
        }

    private void sendInformation()
        {
        try
            {
            coures = in.readLine();
            out.println(Service.getMark(coures, id));
            out.println(Service.getAvg(coures));
            out.println(Service.getMedian(coures));
            out.println(Service.gethigh(coures));
            out.println(Service.getLow(coures));
            }
        catch (Exception e)
            {
            throw new RuntimeException(e);
            }
        }

    }

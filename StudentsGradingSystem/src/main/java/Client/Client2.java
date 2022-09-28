package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Client class
class Client2
    {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter out;
    static BufferedReader in;
    static String name;
    static String password;
    static String course;

    public static void main(String[] args)
        {
        try
            {
            Socket socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            login();
            chooseCourse();

            }
        catch (IOException e)
            {
            e.printStackTrace();
            }
        }

    public static void login()
        {
        while (true)
            {
            System.out.print("Enter your Name : ");
            name = sc.next();
            System.out.println();
            System.out.print("Enter you Password : ");
            password = sc.next();
            if (isValid(name, password))
                {
                break;
                }
            System.out.println("Invalid");
            System.out.println("The Name or Password is not correct !!");
            }
        }

    public static boolean isValid(String name, String password)
        {
        out.println(name);
        out.println(password);
        try
            {
            return in.readLine().equalsIgnoreCase("valid");
            }
        catch (IOException e)
            {
            throw new RuntimeException(e);
            }
        }

    public static void chooseCourse()
        {
        String input ;
        System.out.println("Welcome " + name);
        try
            {
            while (true)
                {
                input = "continue";
                printCourse();
                System.out.print("Choose a course : ");
                System.out.println();
                course = sc.next();
                out.println(course);
                System.out.println("mark is " + in.readLine());
                System.out.println("Avg is " + in.readLine());
                System.out.println("Median is " + in.readLine());
                System.out.println("high is " + in.readLine());
                System.out.println("low is " + in.readLine());
                while (!input.equalsIgnoreCase("R") && !input.equalsIgnoreCase("E") )
                    {
                    System.out.println();
                    System.out.println("Enter \"E\" >> if you want leave OR \"R\" >> for return to Courses");
                    input = sc.next();
                    }
                if (input.equalsIgnoreCase("E"))
                    {
                    out.println("exit");
                    System.out.println("Goodbye " + name );
                    break;
                    }
                out.println("return");
                }
            }
        catch (IOException e)
            {
            throw new RuntimeException(e);
            }

        }

    public static void printCourse()
        {
        try
            {
            String course = in.readLine();
            while (!course.equalsIgnoreCase("stop"))
                {
                System.out.print(course + " ");
                course = in.readLine();
                }
            System.out.println();
            }
        catch (IOException e)
            {
            throw new RuntimeException(e);
            }
        }

    }
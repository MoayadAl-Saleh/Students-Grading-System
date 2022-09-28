package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service
    {
    static Connection con = Server.con;
    static PreparedStatement p = null;
    static ResultSet rs = null;


    public static boolean isStudent(String name, String password)
        {
        try
            {
            boolean isNotEmpty;
            String sql2 = "select * from tbstudent where namestudent=\"" + name + "\" and passwordstudent = \"" + password + "\";";
            p = con.prepareStatement(sql2);
            rs = p.executeQuery();
            isNotEmpty = rs.isBeforeFirst();
            return isNotEmpty;
            }
        catch (Exception e)
            {
            throw new RuntimeException(e);
            }
        }

    public static List<String> getCourses(int id)
        {
        List<String> courses = new ArrayList<>();
        try
            {
            String sql = "SELECT namecourse FROM tbcourse WHERE idcourse  IN (SELECT idcourse FROM tbmark WHERE idstudent =\"" + id + "\");";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next())
                {
                courses.add(rs.getString(1));
                }
            courses.add("Stop");
            return courses;
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }
    public static int getID(String name, String password)
        {
        try
            {
            String sql2 = "select * from tbstudent where namestudent=\"" + name + "\" and passwordstudent = \"" + password + "\";";
            p = con.prepareStatement(sql2);
            rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
            }
        catch (Exception e)
            {
            throw new RuntimeException(e);
            }
        }
    public static int getMark(String coures , int id)
        {
        try
            {
            String sql = "select mark from tbmark where idcourse in (select idcourse from tbcourse where namecourse = \"" + coures + "\") and idstudent =" + id;
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }

    public static double getAvg(String coures)
        {
        try
            {
            String sql = "select AVG(mark) from tbmark where idcourse in (select idcourse from tbcourse where namecourse = \"" + coures + "\");";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            return rs.getDouble(1);
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }

    public static int gethigh(String coures )
        {
        try
            {
            String sql = "select MAX(mark) from tbmark where idcourse in (select idcourse from tbcourse where namecourse = \"" + coures + "\");";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }

    public static int getLow(String coures )
        {
        try
            {
            String sql = "select MIN(mark) from tbmark where idcourse in (select idcourse from tbcourse where namecourse = \"" + coures + "\");";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }

    public static double getMedian(String coures)
        {
        try
            {
            List<Integer> list = new ArrayList<>();
            int n = 0;
            double median = 0;
            String sql = "select mark from tbmark where idcourse in (select idcourse from tbcourse where namecourse = \"" + coures + "\");";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next())
                {
                n++;
                list.add(rs.getInt(1));
                }
            list.sort(Integer::compareTo);
            if (n % 2 == 1)
                {
                median = list.get((n + 1) / 2 - 1);
                }
            else
                {
                median = (list.get(n / 2 - 1) + list.get(n / 2)) / 2;
                }
            return median;
            }
        catch (SQLException e)
            {
            throw new RuntimeException(e);
            }
        }
    }

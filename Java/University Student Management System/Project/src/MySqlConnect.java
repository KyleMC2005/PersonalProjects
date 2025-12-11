package src;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
public class MySqlConnect {
    // Create the Singleton instance mysqlConn
    private static Connection mysqlConn = null;

    //this block will run only once when the class is loaded into memory
    static {
        String host = "";
        String dbName = "";
        String dbUser = "";
        String password = "";


        File myObj = new File("..\\Project\\dbConnect.txt");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner reader = new Scanner(myObj)) {
            while (reader.hasNextLine()) {
                 host = reader.nextLine();
                 dbName = reader.nextLine();
                 dbUser = reader.nextLine();
                 password = reader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading dbConnect.txt.");
            e.printStackTrace();
        }


            String url = "jdbc:mysql://" + host + ":3306/";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // try to connect
                mysqlConn = DriverManager.getConnection(url + dbName, dbUser,
                        password);
                System.out.println("MySQL Db Connection is successful");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        public static Connection getMysqlConnection ()
        {
            return mysqlConn;
        }
    }
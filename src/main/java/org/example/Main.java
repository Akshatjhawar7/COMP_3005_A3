package org.example;

import java.sql.*;
import java.util.Scanner;


public class Main {
    static Connection connect;
    public static Connection connection(){ //Establishes connection to postgresql
        String url = "jdbc:postgresql://localhost:5432/A3";
        String user = "postgres";
        String password = "akshat";

        try{
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url,user,password);
            if(connect != null){
                return connect;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }
    public static void getAllStudents(){//Displays all the current students to the console
        try {
            Statement statement = connect.createStatement();
            statement.executeQuery("Select * from students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date){//Adds student to table with given information
        String insertSQL = "INSERT INTO students (first_name,last_name,email,enrollment_date) VALUES (?,?,?,?)";
        try(PreparedStatement pstmt = connect.prepareStatement(insertSQL)) {
            pstmt.setString(1,first_name);
            pstmt.setString(2,last_name);
            pstmt.setString(3,email);
            pstmt.setDate(4,enrollment_date);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Element added");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateEmail(int student_id,String new_email){//Updates email of given student
        String updateSQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try(PreparedStatement pstmt = connect.prepareStatement(updateSQL)) {
            pstmt.setString(1,new_email);
            pstmt.setInt(2,student_id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Element updated");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteStudent(int student_id){//Deletes given student
        String deleteSQL = "DELETE FROM students WHERE student_id = ?";
        try(PreparedStatement pstmt = connect.prepareStatement(deleteSQL)) {
            pstmt.setInt(1,student_id);
            int rowsDeleted = pstmt.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Element deleted");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){//Calls all function and inputs for the program
        connection();
        if(connect != null){
            Scanner scanner = new Scanner(System.in);
            getAllStudents();
            System.out.println("Enter first name");
            String fn = scanner.nextLine();
            System.out.println("Enter last name");
            String ln = scanner.nextLine();
            System.out.println("Enter email");
            String e = scanner.nextLine();
            System.out.println("Enter enrollment date(yyyy-mm-dd");
            String date = scanner.nextLine();
            Date ed = Date.valueOf(date);
            addStudent(fn,ln,e,ed);
            System.out.println("Enter id of student you want to update");
            int stu = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new email of selected student");
            String new_em = scanner.nextLine();
            updateEmail(stu,new_em);
            System.out.println("Enter id you want to delete");
            int s_id = scanner.nextInt();
            deleteStudent(s_id);
        }
    }
}
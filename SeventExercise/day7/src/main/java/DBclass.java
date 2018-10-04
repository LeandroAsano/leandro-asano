import DB.ConnectionConfig;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.rmi.server.ExportException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.Executor;

public class DBclass {

    public static ResultSet showTable(Statement stmt, String query){
        ResultSet result=null;
        try{
            result = stmt.executeQuery(query);
            while (result.next()){
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    System.out.print(result.getMetaData().getColumnName(i)+": "+result.getString(i)+"\n");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String updatePrice(Statement stmt,String query){
        Scanner input = new Scanner(System.in);
        int optionsid;
        int optionpid;
        float price;
        showTable(stmt,query);

        System.out.print("select sid:");
        optionsid=input.nextInt();
        System.out.print("select pid ");
        optionpid=input.nextInt();
        System.out.print("enter price:");
        price=input.nextFloat();

        try {
            stmt.executeUpdate("update catalog set cost="+price+" where sid="+optionsid+" and pid="+optionpid);
            return "Update executed!";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        String query="";

        Connection connection = null;
        try{
            ResultSet result=null;
            connection = ConnectionConfig.getConnection();
            Statement stmt = connection.createStatement();
            System.out.println("select an option \n 1-saved statments \n 2-enter query \n 3-update a supplier " +
                    "\n 4-update price of item by \n 5-remove supplier" );
            int option= input.nextInt();
            switch (option){
            case 1:{
                    String queryonlygreen= "select s.sname, count(*) as cant \n" +
                        " from (suppliers s inner join catalog c on c.sid=s.sid) \n" +
                        " inner join parts p on c.pid=p.pid\n" +
                        " where (p.color='green' and c.sid not in \n" +
                        " (select c2.sid from catalog c2 inner join parts p2 on c2.pid=p2.pid \n" +
                        " where p2.color not like 'green'))\n" +
                        " group by s.sname";
                    String queryredandgreen = " select s.sname, max(c.cost) as cost\n" +
                        " from (catalog c inner join parts p on c.pid=p.pid) \n" +
                        " inner join suppliers s on c.sid=s.sid\n" +
                        " where (p.color='green' and c.sid in \n" +
                        " (select c2.sid from parts p2 inner join catalog c2 on c2.pid=p2.pid where p2.color='red'))\n" +
                        " group by c.sid;\n";
                     System.out.println("1-show only green suppliers and the quantity of that parts " +
                        " \n 2-show supplier that supply an green and red part and the max cost of an part that supplies");
                     int choice = input.nextInt();
                      if (choice == 1){
                           result = stmt.executeQuery(queryonlygreen);

                          while (result.next()){
                              String name = result.getString("sname");
                              String cant = result.getString("cant");
                              System.out.println("name: "+name+" cant: "+cant);
                          }
                      } else if (choice==2){
                          result = stmt.executeQuery(queryredandgreen);
                          while (result.next()){
                              for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                                  System.out.println(result.getMetaData().getColumnName(i)+": "+result.getString(i));
                              }
                          }

                      } else{
                          System.out.println("invalid choice");
                      }
              }break;
              case 2:{
                  System.out.println("enter query");
                  input.nextLine();
                  String enteredquery=input.nextLine();
                  result = stmt.executeQuery(enteredquery);
                  while (result.next()){
                      for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                          System.out.println(result.getMetaData().getColumnName(i)+": "+result.getString(i));
                      }
                  }

              }break;
              case 3:{
                  String queryparameter= "Select * from suppliers";
                  result=showTable(stmt,queryparameter);
                  System.out.println("\n 1-name supplier \n 2-adrress supplier ");
                  int update=input.nextInt();
                  System.out.println("enter code of supplier");
                  int code=input.nextInt();
                  if (update==1){
                      System.out.println("enter name");
                      String name=input.next();
                      stmt.executeUpdate("update suppliers set sname='"+name+"' where sid="+code);

                  } else if (update==2){
                      System.out.println("enter addres");
                      String address=input.next();
                      stmt.executeUpdate("update suppliers set address='"+address+"' where sid="+code);
                      System.out.println("Update executed!");
                  }else {
                      System.out.println("invalid value");
                  }

              }break;
              case 4:{
                  String queryparameter="select c.sid,c.pid,p.pname,c.cost from suppliers as s inner join catalog as c inner join parts as p \n" +
                          " on s.sid=c.sid and c.pid=p.pid";
                  System.out.println(updatePrice(stmt,queryparameter));
              }break;
              case 5:{
                  int codesup;
                  String queryparameter="select * from suppliers";
                  showTable(stmt,queryparameter);
                  System.out.println("enter code of supplier");
                  codesup=input.nextInt();
                  stmt.executeUpdate("delete from suppliers where sid="+codesup);
                  System.out.println("Delete success!");
                  stmt.executeQuery("select * from catalog;");
              }break;
                default:
                    System.out.println("invalid choice"); }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

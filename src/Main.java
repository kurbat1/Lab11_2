import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLNoDataFoundException {
        String userName = "postgres";
        String password = "12345";
        String Url = "jdbc:postgresql://localhost:5432/postgres";
        Class.forName("org.postgresql.Driver");
        SQL sql = new SQL("localhost", "postgres", "12345");
        sql.addEmp(7999, "LORD", "MANAGER", 7566,  4599, 200 ,20);
        System.out.println("-----------Employee add---------------");
        System.out.println("--------------------------------------");
        sql.getEmployee(7999);
        System.out.println("-----------Get employee that we add---");
        System.out.println("--------------------------------------");
        sql.getAll();
        System.out.println("-----------Get All in EMP-------------");
        System.out.println("--------------------------------------");
        sql.getEmpInfo(7999);
        System.out.println("--------------------------------------");
        sql.deleteEmp(7999);
        System.out.println("Warning!!! Employee was deleted!");
        System.out.println("--------------------------------------");
    }
//        try (Connection conn = DriverManager.getConnection(Url, userName, password);
//       Statement statement = conn.createStatement()) {
////            statement.executeUpdate("INSERT  into emp values (7999, 'LORD', 'ANALYST', 7566, date '1982-01-01', 4599, 200 ,20)");
//            System.out.println("We're connected");
//            ResultSet set = statement.executeQuery("select  * from emp");
////            int col = set.getMetaData().getColumnCount();
////            while(set.next() == true) {
////                for (int i = 1; i < col; i++) {
////                    System.out.print(set.getString(i) + "\t" + "\t");
////                }
////                System.out.println();
////
////                }
//
//
//       } catch (SQLException e) {
//           e.printStackTrace();
//       }
//    }

}

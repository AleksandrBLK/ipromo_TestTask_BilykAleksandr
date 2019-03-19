package ipromo_base.repository;
import ipromo_base.model.StockClass;
import java.sql.*;
import java.util.ArrayList;


public class JDBC_Repository {

    private static String user = "root";
    private static String url = "jdbc:mysql://localhost:3306/testipromo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String password = "3199518893Bilyk";


    public static int Update(int serialNumber, int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                String sql = "update stock set serialnumber = ? where id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, String.valueOf(serialNumber));
                    preparedStatement.setInt(2, id);

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static ArrayList<StockClass> select() {

        ArrayList<StockClass> stocks = new ArrayList<StockClass>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM stock");
                while (resultSet.next()) {

                    String id = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String serialnumber = resultSet.getString(3);
                    StockClass stockClass = new StockClass(id, name, serialnumber);
                    stocks.add(stockClass);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return stocks;
    }


}

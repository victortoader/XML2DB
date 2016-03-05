package victortoader.org;


import java.sql.*;

/**
 * Created by Vic on 2/21/2016.
 */
public class JDBCMain {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/UserInfo";
    static final String USER = "root";
    static final String PASS = "admin";



    static void insertRecordIntoDbUserTable(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement prepstatement = null;

        String insertTableSQL = "insert into User_table (userMessage, User_Name) values (?, ?)";

        try {
            conn = getDBConnection();
            prepstatement = conn.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);


            prepstatement.setString(1, user.getUserName());
            prepstatement.setString(2, user.getUserMessage());
            System.out.println(prepstatement);


            int affectedRows = prepstatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, 0 rows affected.");

            }
            try (ResultSet generatedKeys = prepstatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                    System.out.println("Genrated Id: " + generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


        } catch (SQLException e)

        {
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
            System.out.println("Victor-ie totala!");
            if (prepstatement != null) {
                prepstatement.close();
            }

            if (conn != null)

            {
                conn.close();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection conn = null;

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException e) {
            System.out.print(e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        ;


        return conn;

    }
}

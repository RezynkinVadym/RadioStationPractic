package data_base;
import java.sql.*;

public class DBHandler extends ConstDB {//клас для взаємодії з бд
    Connection connection;
    private final String selectAll = "SELECT * FROM ";
    public Connection getDBConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, DB_USER, DB_PASS);
        return connection;
    }

    public ResultSet getAllBroadcasters(){
        String query = selectAll + BROADCASTERS_TABLE;
        return executeSelect(query);
    }
    public ResultSet getAllSongs(){
        String query = selectAll + SONGS_TABLE;
        return executeSelect(query);
    }
    public ResultSet getAllInterviews(){
        String query = selectAll + INTERVIEW_TABLE;
        return executeSelect(query);
    }
    public ResultSet getAllAdvertising(){
        String query = selectAll + ADVERTISING_TABLE;
        return executeSelect(query);
    }
    private ResultSet executeSelect(String query){
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }

}

package DAO;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojos.Student;

public class StudentDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public StudentDAO(String jdbcURL, String jdbcUsername) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = "";
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertData(Student st) throws SQLException {
        String sql = "INSERT INTO registration (firstname, email, password) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, st.getFname());
        statement.setString(2, st.getEmail());
        statement.setString(3, st.getPass());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Student> listAllData() throws SQLException {
        List<Student> listStudent = new ArrayList<>();
         
        String sql = "SELECT * FROM registration";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String Fname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String pass = resultSet.getString("password");
             
            Student st = new Student(id, Fname, email, pass);
            listStudent.add(st);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listStudent;
    }
     
    public boolean deleteStudent(Student st) throws SQLException {
        String sql = "DELETE FROM registration where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, st.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateStudent(Student st) throws SQLException {
        String sql = "UPDATE registration SET firstname = ?, email = ?, password = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, st.getFname());
        statement.setString(2, st.getEmail());
        statement.setString(3, st.getPass());
        statement.setInt(4, st.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Student getStudent(int id) throws SQLException {
        Student st = null;
        String sql = "SELECT * FROM registration WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String Fname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String pass = resultSet.getString("password");
             
            st = new Student(id, Fname, email, pass);
        }
         
        resultSet.close();
        statement.close();
         
        return st;
    }
}

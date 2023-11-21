
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Koneksi_Database {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mahasiswa"; 

        return DriverManager.getConnection(url);
    }

    public static void fetchDataByNIM(String nim, javax.swing.JTextField jTextField2, javax.swing.JTextField jTextField3, javax.swing.JTextField jTextField4) {
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM db_mahasiswa WHERE nim=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nim);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        //menemukan record
                        String nama = resultSet.getString("nama");
                        String email = resultSet.getString("email");

                        // menemukan nilai textfield
                        jTextField2.setText(nama);
                        jTextField3.setText(email);
                        jTextField4.setText(""); 
                    } else {
                        // tidak menemukan
                        jTextField2.setText("");
                        jTextField3.setText("");
                        jTextField4.setText("Record not found");
                    }
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
}
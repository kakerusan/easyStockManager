package thirdGroup.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * \n" +
                "FROM goods");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("goodsname"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

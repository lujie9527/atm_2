package dao;


import DBUtils.Utils;
import model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    //登录
    public static User getUserByCardAndPassword(String card, String password) {
        User user = null;
        String sql = "select card,password from user where card=? and password=?";
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, card);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("card");
                long userId = resultSet.getLong("password");

                user = new User();
                user.setNumber(card);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    //修改密码
    public static void changePasswordByNum(String number, String password) {
        String sql = "update user set password=? where number=?";
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(password));
            statement.setString(2, number);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserByCardAndPassword("777888999", "123456");
        System.out.println(user);
    }

    //查询余额
    public User getMoneyByNumber(String number) {
        User user = null;
        String sql = "select money,name from user where card=?";
        try {
            Connection connection = Utils.getConnection();//连接数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);//识别sql语句
            preparedStatement.setString(1, number);//将number的值转换成sql语句
            ResultSet resultSet = preparedStatement.executeQuery();//获取数据

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String money = resultSet.getString("money");
                //BigDecimal money = resultSet.getBigDecimal("money");
                user = new User();
                user.setNumber(number);
                user.setName(name);
                user.setMoney(BigDecimal.valueOf(Long.valueOf(money)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //转账、取款、存款，更新余额
    public void updateMoneyByNum(String number, BigDecimal money) {
        String sql = "update user set money=? where number=?";
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(money));
            statement.setString(2, number);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

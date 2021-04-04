package pos.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import pos.java.jdbc.model.User;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(final User user) {
        try {
            final String sql = "INSERT INTO tbl_user (id, nome, email) VALUES (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong(1, user.getId());
            insert.setString(2, user.getNome());
            insert.setString(3, user.getEmail());
            insert.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

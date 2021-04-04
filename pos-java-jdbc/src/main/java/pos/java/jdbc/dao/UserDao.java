package pos.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pos.java.jdbc.model.User;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(final User user) {
        try {
            final String sql = "INSERT INTO tbl_user (nome, email) VALUES (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, user.getNome());
            insert.setString(2, user.getEmail());
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

    public List<User> listar() {
        try {
            List<User> usuarios = new ArrayList<>();
            final String sql = "SELECT * FROM tbl_user";
            final PreparedStatement select = connection.prepareStatement(sql);
            final ResultSet resultado = select.executeQuery();
            while (resultado.next()) {
                User user = User.of(
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    resultado.getString("email")
                );
                usuarios.add(user);
            }

            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

	public User buscarPorId(long id) {
		try {
           final String sql = "SELECT * FROM tbl_user WHERE id = " + id;
           final PreparedStatement select = connection.prepareStatement(sql);
           final ResultSet resultado = select.executeQuery();

           User userFound = null;
           while (resultado.next()) {
                userFound = User.of(
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    resultado.getString("email")
                );
           }

           return userFound;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	public void atualizar(User user) {
        try {
            final String sql = "UPDATE tbl_user SET nome = ?, email = ? WHERE id = " + user.getId();
            final PreparedStatement update = connection.prepareStatement(sql);
            update.setString(1, user.getNome());
            update.setString(2, user.getEmail());
            update.execute();
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

	public void deletar(final Long id) {
        try {
            final String sql = "DELETE FROM tbl_user WHERE id = " + id;
            final PreparedStatement delete = connection.prepareStatement(sql);
            delete.execute();
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

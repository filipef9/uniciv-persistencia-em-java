package pos.java.jdbc.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import pos.java.jdbc.conexao.SingleConnection;
import pos.java.jdbc.model.User;

public class UserDaoTest {

    private Connection connection;
    private Connection connectionSpy;
    private UserDao userDao;
    private User user;

    @Test
    public void save_user() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);
        user = User.of(null, "John Doe", "john.doe@gmail.com");

        // act:
        userDao.salvar(user);

        // assert:
        try {
            verify(connectionSpy, times(2)).prepareStatement(anyString());
            verify(connectionSpy, times(1)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void list_all_users() {
        // arrange:
        connection = SingleConnection.getConnection();
        userDao = new UserDao(connection);

        // act:
        List<User> usuarios = userDao.listar();

        // assert:
       assertThat(usuarios, is(not(empty())));
    }

    @Test
    public void find_by_id() {
        // arrange:
        connection = SingleConnection.getConnection();
        userDao = new UserDao(connection);

        // act:
        final User userFound = userDao.buscarPorId(1L);

        // assert:
        assertThat(userFound, is(notNullValue()));
    }

    @Test
    public void update_user() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);
        user = User.of(1005L, "Jane Doe", "jane.doe@gmail.com");

        // act:
        userDao.atualizar(user);

        // assert:
        try {
            verify(connectionSpy, times(1)).prepareStatement(anyString());
            verify(connectionSpy, times(1)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

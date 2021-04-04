package pos.java.jdbc.dao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;

import org.junit.Test;

import pos.java.jdbc.conexao.SingleConnection;
import pos.java.jdbc.model.User;

public class UserDaoTest {

    private Connection connection;
    private Connection connectionSpy;
    private UserDao userDao;
    private User user;

    @Test
    public void salvar_user() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);
        user = User.of(1005L, "John Doe", "john.doe@gmail.com");

        // act:
        userDao.salvar(user);

        // assert:
        try {
            verify(connectionSpy, times(1)).prepareStatement(anyString());
            verify(connectionSpy, times(1)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

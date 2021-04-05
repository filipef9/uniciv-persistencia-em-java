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
import pos.java.jdbc.model.Phone;
import pos.java.jdbc.model.User;
import pos.java.jdbc.vo.UserWithPhoneVo;

public class UserDaoTest {

    private Connection connection;
    private Connection connectionSpy;
    private UserDao userDao;
    private User user;
    private Phone phone;

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
            verify(connectionSpy, times(1)).prepareStatement(anyString());
            verify(connectionSpy, times(1)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save_phone() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);
        phone = Phone.of(null, "(61) 98113-1295", "celular", 1L);

        // act:
        userDao.salvarTelefone(phone);

        // assert:
        try {
            verify(connectionSpy, times(1)).prepareStatement(anyString());
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
    public void search_user_with_phone_by_id() {
        // arrange:
        connection = SingleConnection.getConnection();
        userDao = new UserDao(connection);

        // act:
        List<UserWithPhoneVo> usuarios = userDao.listarUsuariosComTelefone(1L);

        // assert:
        assertThat(usuarios, is(notNullValue()));
        assertThat(usuarios, is(not(empty())));
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

    @Test
    public void delete_user() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);

        // act:
        userDao.deletar(1006L);

        // assert:
        try {
            verify(connectionSpy, times(1)).prepareStatement(anyString());
            verify(connectionSpy, times(1)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void delete_phones_from_a_user() {
        // arrange:
        connection = SingleConnection.getConnection();
        connectionSpy = spy(connection);
        userDao = new UserDao(connectionSpy);

        List<User> usuarios = userDao.listar();
        final User user = usuarios.get(usuarios.size() - 1);

        final Phone phone1 = Phone.of(null, "(29) 99150-1549", "celular", user.getId());
        userDao.salvarTelefone(phone1);
        final Phone phone2 = Phone.of(null, "(62) 90700-1888", "celular", user.getId());
        userDao.salvarTelefone(phone2);
        final Phone phone3 = Phone.of(null, "(81) 90282-0976", "celular", user.getId());
        userDao.salvarTelefone(phone3);

        // act:
        userDao.deletarTelefonesDoUsuario(user.getId());

        // assert:
        try {
            verify(connectionSpy, times(6)).prepareStatement(anyString());
            verify(connectionSpy, times(5)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

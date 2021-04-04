package pos.java.jdbc.conexao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.Is.is;

import java.sql.Connection;

import org.junit.Test;

public class SingleConnectionTest {

    private Connection connection;

    @Test
    public void get_connection() {
        _arrange:
        connection = null;

        _act:
        connection = SingleConnection.getConnection();

        _assert:
        assertThat(connection, is(notNullValue()));
    }
}

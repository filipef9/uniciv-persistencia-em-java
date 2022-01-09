package pos.java.jdbc.conexao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import java.sql.Connection;

import org.junit.Test;

public class SingleConnectionTest {

    private Connection connection;

    @Test
    public void get_connection() {
        // arrange:
        connection = null;

        // act:
        connection = SingleConnection.getConnection();

        // assert:
        assertThat(connection, is(notNullValue()));
    }
}

package pos.java.jdbc.model;

public final class User {

    private final Long id;
    private final String nome;
    private final String email;

    private User (Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public static User of(final Long id, final String nome, final String email) {
        return new User(id, nome, email);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }



}

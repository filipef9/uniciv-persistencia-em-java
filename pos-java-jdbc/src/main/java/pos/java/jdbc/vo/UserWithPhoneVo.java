package pos.java.jdbc.vo;

public final class UserWithPhoneVo {

    private final String nome;
    private final String telefone;
    private final String email;

    private UserWithPhoneVo(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public static UserWithPhoneVo of(final String nome, final String telefone, final String email) {
        return new UserWithPhoneVo(nome, telefone, email);
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserWithPhone [nome = " + nome + ", telefone = " + telefone + ", email = " + email + "]";
    }

}

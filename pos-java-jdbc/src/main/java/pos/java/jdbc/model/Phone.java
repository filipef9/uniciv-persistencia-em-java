package pos.java.jdbc.model;

public final class Phone {

    private final Long id;
    private final String numero;
    private final String tipo;
    private final Long idUser;

    private Phone(Long id, String numero, String tipo, Long idUser) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.idUser = idUser;
    }

    public static Phone of(final Long id, final String numero, final String tipo, final Long idUser) {
        return new Phone(id, numero, tipo, idUser);
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getIdUser() {
        return idUser;
    }

}

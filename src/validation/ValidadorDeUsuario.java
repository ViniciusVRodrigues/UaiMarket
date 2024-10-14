package validation;

public interface ValidadorDeUsuario {
    boolean validarCodigo(String codigo);
    String gerarCodigo();
}

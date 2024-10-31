package validation;
//utilizado no adapter
public interface ValidadorDeUsuario {
    boolean validarCodigo(String codigo);
    String gerarCodigo();
}

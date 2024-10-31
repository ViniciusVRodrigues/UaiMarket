package validation;
//utilizado no adapter
public class AdaptadorValidadorExterno implements ValidadorDeUsuario {
    private ValidadorExterno validadorExterno;

    public AdaptadorValidadorExterno(){
        validadorExterno = new ValidadorExterno();
    }

    @Override
    public boolean validarCodigo(String codigo) {
        return validadorExterno.validarUsuario(codigo);
    }

    @Override
    public String gerarCodigo() {
        return validadorExterno.gerarCodigo();
    }
}

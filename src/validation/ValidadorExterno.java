package validation;
//utilizado no adapter
public class ValidadorExterno {
    private String codigo;
    public String gerarCodigo(){
        codigo = "123456";
        return "123456";
    }

    public boolean validarUsuario(String codigo){
        return this.codigo.equals(codigo);
    }
}

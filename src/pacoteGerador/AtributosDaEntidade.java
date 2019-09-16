package pacoteGerador;

// @author Radames
public class AtributosDaEntidade {
  
  private String atributo;
  private String tipo;
  private String largura;

    public AtributosDaEntidade() {
    }

    public AtributosDaEntidade(String atributo, String tipo, String largura) {
        this.atributo = atributo;
        this.tipo = tipo;
        this.largura = largura;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLargura() {
        return largura;
    }
  
  
}

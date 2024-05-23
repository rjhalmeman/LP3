package Entidades;

// @author Radames (usando gerador de código) 15:29:51 23/05/2024
public class Produto {private int idProduto;private String nomeProduto;private int quantidadeEmEstoque;private String UnidadeDeMedida_siglaUnidadeDeMedida;
public Produto(){

}public Produto(int idProduto,String nomeProduto,int quantidadeEmEstoque,String UnidadeDeMedida_siglaUnidadeDeMedida){this.idProduto = idProduto;this.nomeProduto = nomeProduto;this.quantidadeEmEstoque = quantidadeEmEstoque;this.UnidadeDeMedida_siglaUnidadeDeMedida = UnidadeDeMedida_siglaUnidadeDeMedida;}

public int getIdProduto(){return idProduto;
}
public String getNomeProduto(){return nomeProduto;
}
public int getQuantidadeEmEstoque(){return quantidadeEmEstoque;
}
public String getUnidadeDeMedida_siglaUnidadeDeMedida(){return UnidadeDeMedida_siglaUnidadeDeMedida;
}
public void setIdProduto(int idProduto){this.idProduto = idProduto;
}
public void setNomeProduto(String nomeProduto){this.nomeProduto = nomeProduto;
}
public void setQuantidadeEmEstoque(int quantidadeEmEstoque){this.quantidadeEmEstoque = quantidadeEmEstoque;
}
public void setUnidadeDeMedida_siglaUnidadeDeMedida(String UnidadeDeMedida_siglaUnidadeDeMedida){this.UnidadeDeMedida_siglaUnidadeDeMedida = UnidadeDeMedida_siglaUnidadeDeMedida;
}    public String toString() {return this.idProduto+";"+this.nomeProduto+";"+this.quantidadeEmEstoque+";"+this.UnidadeDeMedida_siglaUnidadeDeMedida;}}
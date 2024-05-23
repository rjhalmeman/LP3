package Entidades;

// @author Radames (usando gerador de código) 15:28:50 23/05/2024
public class Endereco {private int idEndereco;private String logradouro;private String numero;private String referencia;private String cep;private int Cidade_idCidade;
public Endereco(){

}public Endereco(int idEndereco,String logradouro,String numero,String referencia,String cep,int Cidade_idCidade){this.idEndereco = idEndereco;this.logradouro = logradouro;this.numero = numero;this.referencia = referencia;this.cep = cep;this.Cidade_idCidade = Cidade_idCidade;}

public int getIdEndereco(){return idEndereco;
}
public String getLogradouro(){return logradouro;
}
public String getNumero(){return numero;
}
public String getReferencia(){return referencia;
}
public String getCep(){return cep;
}
public int getCidade_idCidade(){return Cidade_idCidade;
}
public void setIdEndereco(int idEndereco){this.idEndereco = idEndereco;
}
public void setLogradouro(String logradouro){this.logradouro = logradouro;
}
public void setNumero(String numero){this.numero = numero;
}
public void setReferencia(String referencia){this.referencia = referencia;
}
public void setCep(String cep){this.cep = cep;
}
public void setCidade_idCidade(int Cidade_idCidade){this.Cidade_idCidade = Cidade_idCidade;
}    public String toString() {return this.idEndereco+";"+this.logradouro+";"+this.numero+";"+this.referencia+";"+this.cep+";"+this.Cidade_idCidade;}}
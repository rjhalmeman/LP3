package ProgramaGerado;// @author Radames
public class Jogo {private int id;private String tipo;private String nome;private String plataforma;private String preco;
public Jogo(){

}public Jogo(int id,String tipo,String nome,String plataforma,String preco){this.id = id;this.tipo = tipo;this.nome = nome;this.plataforma = plataforma;this.preco = preco;}

public int getId(){return id;
}
public String getTipo(){return tipo;
}
public String getNome(){return nome;
}
public String getPlataforma(){return plataforma;
}
public String getPreco(){return preco;
}
public void setId(int id){this.id = id;
}
public void setTipo(String tipo){this.tipo = tipo;
}
public void setNome(String nome){this.nome = nome;
}
public void setPlataforma(String plataforma){this.plataforma = plataforma;
}
public void setPreco(String preco){this.preco = preco;
}    public String toStringCSV() {return this.id+";"+this.tipo+";"+this.nome+";"+this.plataforma+";"+this.preco;}}
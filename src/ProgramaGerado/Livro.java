package ProgramaGerado;// @author Radames
public class Livro {private int idLivro;private String nomeLivro;private String nomeAutor;
public Livro(){

}public Livro(int idLivro,String nomeLivro,String nomeAutor){this.idLivro = idLivro;this.nomeLivro = nomeLivro;this.nomeAutor = nomeAutor;}

public int getIdLivro(){return idLivro;
}
public String getNomeLivro(){return nomeLivro;
}
public String getNomeAutor(){return nomeAutor;
}
public void setIdLivro(int idLivro){this.idLivro = idLivro;
}
public void setNomeLivro(String nomeLivro){this.nomeLivro = nomeLivro;
}
public void setNomeAutor(String nomeAutor){this.nomeAutor = nomeAutor;
}    public String toStringCSV() {return this.idLivro+";"+this.nomeLivro+";"+this.nomeAutor;}}
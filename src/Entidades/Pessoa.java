package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    private String cpf_pessoa;
    private String nome_pessoa;    
    private Date data_de_nascimento_pessoa;    
    private int cidade_id_cidade;    

    public Pessoa() {
    }

    public Pessoa(String cpf_pessoa, String nome_pessoa, Date data_de_nascimento_pessoa, int cidade_id_cidade) {
        this.cpf_pessoa = cpf_pessoa;
        this.nome_pessoa = nome_pessoa;
        this.data_de_nascimento_pessoa = data_de_nascimento_pessoa;
        this.cidade_id_cidade = cidade_id_cidade;
    }

    public String getCpf_pessoa() {
        return cpf_pessoa;
    }

    public void setCpf_pessoa(String cpf_pessoa) {
        this.cpf_pessoa = cpf_pessoa;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public Date getData_de_nascimento_pessoa() {
        return data_de_nascimento_pessoa;
    }

    public void setData_de_nascimento_pessoa(Date data_de_nascimento_pessoa) {
        this.data_de_nascimento_pessoa = data_de_nascimento_pessoa;
    }

    public int getCidade_id_cidade() {
        return cidade_id_cidade;
    }

    public void setCidade_id_cidade(int cidade_id_cidade) {
        this.cidade_id_cidade = cidade_id_cidade;
    }

    @Override
    public String toString() {
        return "PessoaLogin{" + "cpf_pessoa=" + cpf_pessoa + ", nome_pessoa=" + nome_pessoa + ", data_de_nascimento_pessoa=" 
                + new SimpleDateFormat("dd/MM/yyyy").format(data_de_nascimento_pessoa) + ", cidade_id_cidade=" + cidade_id_cidade + '}';
    }

    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

// @author Radames
public class ContaBco {

    private String data;
    private String ag;
    private String descricao;
    private String doc;
    private String cod;
    private String operacao;
    private String valor;

    public ContaBco() {
    }

    public ContaBco(String data, String ag, String descricao, String doc, String cod, String operacao, String valor) {
        this.data = data;
        this.ag = ag;
        this.descricao = descricao;
        this.doc = doc;
        this.cod = cod;
        this.operacao = operacao;
        this.valor = valor;
    }

   
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}

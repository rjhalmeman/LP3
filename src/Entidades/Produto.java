/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author belly
 */
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_produto")
    private Integer idProduto;
    @Basic(optional = false)
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "descricao_produto")
    private String descricaoProduto;
    @Basic(optional = false)
    @Column(name = "tempo_preparo")
    private String tempoPreparo;
    @JoinColumn(name = "categoria_id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private Categoria categoriaIdCategoria;
    @Lob
    @Column(name = "imagem")
    private byte[] imagem;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(Integer idProduto, String nomeProduto, String tempoPreparo) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.tempoPreparo = tempoPreparo;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Categoria getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(Categoria categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Produto[ idProduto=" + idProduto + " ]";
    }

    public String toString2() {
        return idProduto + "-" + nomeProduto;
    }
}

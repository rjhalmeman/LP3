package Entidades;

// @author Radames (usando gerador de código) 10:26:54 05/06/2024
import java.util.Date;

public class Pedido {

    private int idPedido;
    private Date dataDoPedido;
    private String ClientePessoaCpfPessoa;

    public Pedido() {

    }

    public Pedido(int idPedido, Date dataDoPedido, String ClientePessoaCpfPessoa) {
        this.idPedido = idPedido;
        this.dataDoPedido = dataDoPedido;
        this.ClientePessoaCpfPessoa = ClientePessoaCpfPessoa;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Date getDataDoPedido() {
        return dataDoPedido;
    }

    public String getClientePessoaCpfPessoa() {
        return ClientePessoaCpfPessoa;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setDataDoPedido(Date dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public void setClientePessoaCpfPessoa(String ClientePessoaCpfPessoa) {
        this.ClientePessoaCpfPessoa = ClientePessoaCpfPessoa;
    }

    public String toString() {
        return this.idPedido + ";" + this.dataDoPedido + ";" + this.ClientePessoaCpfPessoa;
    }
}

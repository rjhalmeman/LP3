package TestesModoTexto;

import DAOs.DAOCargo;
import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import DAOs.DAOPedidoHasProduto;
import DAOs.DAOPessoa;
import DAOs.DAOProduto;
import Entidades.Cargo;
import Entidades.Cliente;
import Entidades.Funcionario;
import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import Entidades.Pessoa;
import java.util.Date;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class AdicionarDadosParaTestes {

    public static void main(String[] args) {

        Cargo cargo;
        DAOCargo daoCargo = new DAOCargo();

        cargo = new Cargo(1, "Gerente");
        if (daoCargo.obter(cargo.getIdCargo()) == null) {
            daoCargo.inserir(cargo);
        }

        cargo = new Cargo(2, "Vendedor");
        if (daoCargo.obter(cargo.getIdCargo()) == null) {
            daoCargo.inserir(cargo);
        }

        Pessoa pessoa;
        DAOPessoa daoPessoa = new DAOPessoa();

        pessoa = new Pessoa("1", "Huguinho");
        if (daoPessoa.obter(pessoa.getCpfPessoa()) == null) {
            daoPessoa.inserir(pessoa);
        }
        pessoa = new Pessoa("2", "Zezinho");
        if (daoPessoa.obter(pessoa.getCpfPessoa()) == null) {
            daoPessoa.inserir(pessoa);
        }

        pessoa = new Pessoa("3", "Luizinho");
        if (daoPessoa.obter(pessoa.getCpfPessoa()) == null) {
            daoPessoa.inserir(pessoa);
        }
        pessoa = new Pessoa("4", "Berola");
        if (daoPessoa.obter(pessoa.getCpfPessoa()) == null) {
            daoPessoa.inserir(pessoa);
        }

        pessoa = new Pessoa("5", "Timocréia");
        if (daoPessoa.obter(pessoa.getCpfPessoa()) == null) {
            daoPessoa.inserir(pessoa);
        }

        Funcionario funcionario;
        DAOFuncionario daoFuncionario = new DAOFuncionario();

        funcionario = new Funcionario();
        if (daoFuncionario.obter("1") == null) {
            funcionario.setPessoaCpfPessoa("1");
            funcionario.setCargoIdCargo(new DAOCargo().obter(1));
            funcionario.setDataCadastro(new Date());
            daoFuncionario.inserir(funcionario);
        }
        funcionario = new Funcionario();
        if (daoFuncionario.obter("2") == null) {
            funcionario.setPessoaCpfPessoa("2");
            funcionario.setCargoIdCargo(new DAOCargo().obter(2));
            funcionario.setDataCadastro(new Date());
            daoFuncionario.inserir(funcionario);
        }
        funcionario = new Funcionario();
        if (daoFuncionario.obter("3") == null) {
            funcionario.setPessoaCpfPessoa("3");
            funcionario.setCargoIdCargo(new DAOCargo().obter(2));
            funcionario.setDataCadastro(new Date());
            daoFuncionario.inserir(funcionario);
        }

        Cliente cliente;
        DAOCliente daoCliente = new DAOCliente();

        cliente = new Cliente();
        if (daoCliente.obter("4") == null) {
            cliente.setPessoaCpfPessoa("4");
            cliente.setDataCadastro(new Date());
            cliente.setRendaCliente(5000.01);
            daoCliente.inserir(cliente);
        }
        cliente = new Cliente();
        if (daoCliente.obter("5") == null) {
            cliente.setPessoaCpfPessoa("5");
            cliente.setDataCadastro(new Date());
            cliente.setRendaCliente(8000.00);
            daoCliente.inserir(cliente);
        }

        PedidoHasProduto pedidoHasProduto;
        PedidoHasProdutoPK pedidoHasProdutoPK;
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        
        pedidoHasProdutoPK = new PedidoHasProdutoPK(1, 1);
        pedidoHasProduto = new DAOPedidoHasProduto().obter(pedidoHasProdutoPK);
        if (pedidoHasProduto == null) {
            pedidoHasProduto = new PedidoHasProduto(pedidoHasProdutoPK);
            pedidoHasProduto.setProduto(new DAOProduto().obter(1));
            pedidoHasProduto.setPrecoUnitario(10.0);
            pedidoHasProduto.setQuantidadeProdutoPedido(5);
            pedidoHasProduto.setDescontoUnitario(1.0);
            daoPedidoHasProduto.inserir(pedidoHasProduto);
        }
        
         pedidoHasProdutoPK = new PedidoHasProdutoPK(1, 2);
        pedidoHasProduto = new DAOPedidoHasProduto().obter(pedidoHasProdutoPK);
        if (pedidoHasProduto == null) {
            pedidoHasProduto = new PedidoHasProduto(pedidoHasProdutoPK);
            pedidoHasProduto.setProduto(new DAOProduto().obter(1));
            pedidoHasProduto.setPrecoUnitario(2.0);
            pedidoHasProduto.setQuantidadeProdutoPedido(2);
            pedidoHasProduto.setDescontoUnitario(0.2);
            daoPedidoHasProduto.inserir(pedidoHasProduto);
        } 

        System.out.println("feito");
    }

}

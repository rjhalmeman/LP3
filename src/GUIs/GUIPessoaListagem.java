package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import Entidades.Cliente;
import Entidades.Funcionario;
import Entidades.Pessoa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

// @author Radames
public class GUIPessoaListagem extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    public GUIPessoaListagem(List<Pessoa> listaPessoa, int posX, int posY, Dimension dimensao) {
        setTitle("Listagem de Pessoa");
        setSize(dimensao);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();

        String[] colunas = new String[]{"cpfPessoa",
            "nomePessoa",
            "fotoPessoa",
            "eMailPessoa",
            "cliente (renda)",
            "funcionario",};

        String[][] dados = new String[0][3];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);

        scroll.setViewportView(tabela);
      
        DAOCliente daoCliente = new DAOCliente();
        Cliente c;
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        Funcionario f;
        for (int i = 0; i < listaPessoa.size(); i++) {
            c = daoCliente.obter(listaPessoa.get(i).getCpfPessoa());
            f = daoFuncionario.obter(listaPessoa.get(i).getCpfPessoa());
            
            String[] linha = new String[]{String.valueOf(listaPessoa.get(i).getCpfPessoa()),
                String.valueOf(listaPessoa.get(i).getNomePessoa()),
                String.valueOf(listaPessoa.get(i).getFotoPessoa()),
                String.valueOf(listaPessoa.get(i).getEMailPessoa()),
                String.valueOf(c!=null?c.getRendaCliente():"---"),
                String.valueOf(f!=null?f.getCargoIdCargo().getNomeCargo():"-- Não é funcionário --")
            };
            model.addRow(linha);
        }

        // scroll.add(ta);
        painelTa.add(scroll);

        cp.add(toolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);

        setLocation(posX + 20, posY + 20);
        setVisible(true);//faz a janela ficar visÃ­vel        
    }
}

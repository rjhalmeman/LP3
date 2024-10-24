///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package GUIs;
//
//import DAOs.DAOCliente;
//import DAOs.DAOFuncionario;
//import Entidades.Cliente;
//import Entidades.Funcionario;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JToolBar;
//import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//import javax.swing.table.DefaultTableModel;
//
///**
// *
// * @author belly
// */
//public class ListaClientesGUI extends JDialog {
//    JPanel painelTa = new JPanel();
//    JScrollPane scroll = new JScrollPane();
//
//    
//     public ListaClientesGUI(int posX, int posY, Dimension dimensao){
//         setTitle("Lista de Clientes");
//        setSize(dimensao); //tamanho da janela
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //libera ao sair (tira da memoria a classe
//        setLayout(new BorderLayout()); //informa qual gerenciador de layout será usado
//        setBackground(Color.CYAN); //cor do fundo da janela
//        setModal(true);
//        Container cp = getContentPane(); //container principal, para adicionar nele os outros componentes
//
//        JToolBar JToolBar = new JToolBar();
//
//        DAOCliente daoCliente = new DAOCliente();
//        List<Cliente> listaCliente = daoCliente.listInOrderId();
//
//        String[] colunas = new String[]{"cpf", "nome"};
//        String[][] dados = new String[listaCliente.size()][colunas.length];
//        String aux[];
//        for (int i = 0; i < listaCliente.size(); i++) {
//            aux = listaCliente.get(i).toString2().split(";");
//            for (int j = 0; j < colunas.length; j++) {
//                dados[i][j] = aux[j];
//            }
//        }
//
//        DefaultTableModel model = new DefaultTableModel(dados, colunas);
//        JTable tabela = new JTable(model);
//
//        scroll.setViewportView(tabela);
//        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
//        tabela.setRowHeight(40);
//        tabela.setEnabled(false);
//        // scroll.add(ta);
//        painelTa.add(scroll);
//
//        cp.add(JToolBar, BorderLayout.NORTH);
//        cp.add(scroll, BorderLayout.CENTER);
//
//        setLocation(posX + 20, posY + 20);
//        setVisible(true); //faz a janela ficar visível 
//     }
//}
package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOClientes;
import Entidades.Clientes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import tools.DateTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import tools.DiretorioDaAplicacao;
import tools.ImagemAjustada;
import tools.CopiarArquivos;
import tools.JanelaPesquisar;

/**
 *
 * @author belly 25/04/2023 - 18:18:34
 */
public class ListaClientesGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JButton btBuscar = new JButton("Buscar");
    JButton btListar = new JButton("Listar");
    JButton btFechar = new JButton("Fechar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));

    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbNaoEncontrado = new JLabel("Não Encontrado");
    DAOClientes daoViewclientes = new DAOClientes();
    Clientes clientes = new Clientes();
    String[] colunas = new String[]{"cod", "nome", "tel"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public ListaClientesGUI(int posX, int posY, Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Lista de Clientes");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(btBuscar);
        pnNorte.add(btListar);
        pnNorte.add(btFechar);
        pnNorte.setBackground(new Color(221, 184, 146));
        btFechar.setVisible(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        pnCentro.setBackground(new Color(221, 184, 146));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBackground(Color.white);
        btListar.setBackground(Color.white);
        btFechar.setBackground(Color.white);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        cardLayout = new CardLayout();
        pnCentro.setLayout(cardLayout);
        tabela.setBackground(Color.BLACK);
        tabela.setForeground(Color.WHITE);

        pnCentro.add(pnListagem, "listagem");
        tabela.setEnabled(false);
        pnCentro.add(lbNaoEncontrado);
        lbNaoEncontrado.setVisible(false);

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (clientes != null) {
                        List<String> listaViewClientes = daoViewclientes.listInOrderNomeStrings("id");
                        if (listaViewClientes.size() > 0) {
                            Point lc = btBuscar.getLocationOnScreen();
                            lc.x = lc.x + btBuscar.getWidth();
                            String selectedItem = new JanelaPesquisar(listaViewClientes, lc.x, lc.y).getValorRetornado();
                            if (!selectedItem.equals("")) {
                                String aux = selectedItem.substring(0, 14);
                                String[] aux2;
                                String[] colunas = new String[]{"CPF", "NOME", "RECEBEER NOTIFICAÇÃO", "DATA DE CADASTRO"};
                                String[][] dados = new String[1][colunas.length];
                                for (int i = 0; i < 1; i++) {
                                    aux2 = daoViewclientes.obter((aux)).toString().split(";");
                                    for (int j = 0; j < colunas.length; j++) {
                                        dados[i][j] = aux2[j];
                                    }
                                }
                                cardLayout.show(pnCentro, "listagem");
                                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                                pnListagem.add(scrollTabela);
                                scrollTabela.setViewportView(tabela);
                                model.setDataVector(dados, colunas);

                                scrollTabela.setPreferredSize(new Dimension(1000, 180));
                                pack();
                                btFechar.setVisible(true);
                            }
                        }
                    } else {//não achou na lista
                        //mostrar botão incluir
                        lbNaoEncontrado.setVisible(true);

                    }

                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Clientes> listaClientes = daoViewclientes.listInOrderId();
                String[] colunas = new String[]{"CPF", "NOME", "RECEBEER NOTIFICAÇÃO", "DATA DE CADASTRO"};
                String[][] dados = new String[listaClientes.size()][colunas.length];
                String aux[];
//                viewclientes = daoViewclientes.obter(Integer.valueOf(tfCod.getText()));
                for (int i = 0; i < listaClientes.size(); i++) {

                    aux = listaClientes.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }

                cardLayout.show(pnCentro, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                scrollTabela.setPreferredSize(new Dimension(1000, 180));
                pack();
                btFechar.setVisible(true);

            }

        }
        );

        btFechar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                pnListagem.remove(scrollTabela);
                pack();
                btFechar.setVisible(false);
            }
        }
        );
        btListar.doClick();
        setModal(
                true);
        pack();

        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);

    }//fim do contrutor de GUI
} //fim da classe

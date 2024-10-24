/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import DAOs.DAOFuncionario;
import Entidades.Funcionario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author belly
 */
public class ListaFuncionariosGUI extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();

    public ListaFuncionariosGUI(int posX, int posY, Dimension dimensao) {
        setTitle("Lista de Funcionários");
        setSize(dimensao); //tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //libera ao sair (tira da memoria a classe
        setLayout(new BorderLayout()); //informa qual gerenciador de layout será usado
        setBackground(Color.CYAN); //cor do fundo da janela
        setModal(true);
        Container cp = getContentPane(); //container principal, para adicionar nele os outros componentes

        JToolBar JToolBar = new JToolBar();

        DAOFuncionario daoFuncionario = new DAOFuncionario();
        List<Funcionario> listaFuncionario = daoFuncionario.listInOrderId();

        String[] colunas = new String[]{"cpf", "nome"};
        String[][] dados = new String[listaFuncionario.size()][colunas.length];
        String aux[];
        for (int i = 0; i < listaFuncionario.size(); i++) {
            aux = listaFuncionario.get(i).toString2().split(";");
            for (int j = 0; j < colunas.length; j++) {
                dados[i][j] = aux[j];
            }
        }

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);

        scroll.setViewportView(tabela);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tabela.setEnabled(false);
        // scroll.add(ta);
        painelTa.add(scroll);

        cp.add(JToolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);

        setLocation(posX + 20, posY + 20);
        setVisible(true); //faz a janela ficar visível        
    }
}

package GUIs;

import Entidades.Categoria;
import DAOs.DAOCategoria;
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

/**
 *
 * @author belly 23/06/2023 - 10:54:10
 */
public class CategoriaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btFechar = new JButton("Fechar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbIdCategoria = new JLabel("ID:");
    JTextField tfIdCategoria = new JTextField(10);
    JLabel lbNomeCategoria = new JLabel("Nome:");
    JTextField tfNomeCategoria = new JTextField(20);
    DAOCategoria daoCategoria = new DAOCategoria();
    Categoria categoria = new Categoria();
    String[] colunas = new String[]{"idCategoria", "nomeCategoria"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public CategoriaGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Categoria");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdCategoria);
        pnNorte.add(tfIdCategoria);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btFechar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.setBackground(new Color(221, 184, 146));
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btFechar.setVisible(false);
        btCancelar.setVisible(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        pnCentro.add(lbNomeCategoria);
        pnCentro.add(tfNomeCategoria);
        lbIdCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfIdCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbIdCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        tfIdCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        lbNomeCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfNomeCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbNomeCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        tfNomeCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        lbIdCategoria.setBackground(new Color(221, 184, 146));
        lbIdCategoria.setForeground(Color.BLACK);
        lbNomeCategoria.setBackground(new Color(221, 184, 146));
        lbNomeCategoria.setForeground(Color.BLACK);
        pnCentro.setBackground(new Color(221, 184, 146));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btSalvar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAlterar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btExcluir.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCancelar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAlterar.setBorder(BorderFactory.createLineBorder(Color.black));
        btExcluir.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBackground(Color.white);
        btCancelar.setBackground(Color.white);
        btAdicionar.setBackground(Color.white);
        btSalvar.setBackground(Color.white);
        btAlterar.setBackground(Color.white);
        btExcluir.setBackground(Color.white);
        btListar.setBackground(Color.white);
        btFechar.setBackground(Color.white);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tfNomeCategoria.setEditable(false);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    categoria = daoCategoria.obter(Integer.valueOf(tfIdCategoria.getText()));
                    if (categoria != null) {//achou o categoria na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeCategoria.setText(categoria.getNomeCategoria());
                        tfNomeCategoria.setEditable(false);
                        tfNomeCategoria.setEnabled(true);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNomeCategoria.setText("");
                        tfNomeCategoria.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdCategoria.setEnabled(false);
                tfNomeCategoria.requestFocus();
                tfNomeCategoria.setEditable(true);
                tfNomeCategoria.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (acao.equals("adicionar")) {
                        categoria = new Categoria();
                    }
                    Categoria categoriaAntigo = categoria;
                    categoria.setIdCategoria(Integer.valueOf(tfIdCategoria.getText()));
                    categoria.setNomeCategoria(tfNomeCategoria.getText());
                    if (acao.equals("adicionar")) {
                        daoCategoria.inserir(categoria);
                    } else {
                        daoCategoria.atualizar(categoria);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdCategoria.setEnabled(true);
                    tfIdCategoria.setEditable(true);
                    tfIdCategoria.requestFocus();
                    tfIdCategoria.setText("");
                    tfNomeCategoria.setEnabled(false);
                    tfNomeCategoria.setEditable(false);
                    tfNomeCategoria.setText("");
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Tente Novamente", "Erro Ao Salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdCategoria.setEditable(false);
                tfNomeCategoria.requestFocus();
                tfNomeCategoria.setEditable(true);
                tfNomeCategoria.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdCategoria.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdCategoria.setEnabled(true);
                tfIdCategoria.setEditable(true);
                tfIdCategoria.requestFocus();
                tfIdCategoria.setText("");
                tfNomeCategoria.setText("");
                tfNomeCategoria.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCategoria.remover(categoria);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Categoria> listaCategoria = daoCategoria.listInOrderId();
                String[] colunas = new String[]{"idCategoria", "nomeCategoria"};
                String[][] dados = new String[listaCategoria.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaCategoria.size(); i++) {
                    aux = listaCategoria.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                scrollTabela.setPreferredSize(new Dimension(1000, 180));
                pack();
                btFechar.setVisible(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfIdCategoria.setText("");
                tfIdCategoria.requestFocus();
                tfIdCategoria.setEnabled(true);
                tfIdCategoria.setEditable(true);
                tfNomeCategoria.setText("");
                tfNomeCategoria.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });
        btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnListagem.remove(scrollTabela);
                pack();
                btFechar.setVisible(false);
            }
        });

        setModal(true);
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do contrutor de GUI
} //fim da classe

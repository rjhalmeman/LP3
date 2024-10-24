package GUIs;

import Entidades.PrecoProduto;
import DAOs.DAOPrecoProduto;
import DAOs.DAOProduto;
import Entidades.PrecoProdutoPK;
import Entidades.Produto;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
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
import tools.JanelaPesquisar;

/**
 *
 * @author belly 27/06/2023 - 11:35:47
 */
public class PrecoProdutoGUI extends JDialog {

    Icon buscar = new ImageIcon("src/icones/retrieve.png");
    Icon add = new ImageIcon("src/icones/create.png");
    Icon salvar = new ImageIcon("src/icones/save.png");
    Icon alterar = new ImageIcon("src/icones/update.png");
    Icon excluir = new ImageIcon("src/icones/delete.png");
    Icon listar = new ImageIcon("src/icones/list.png");
    Icon fechar = new ImageIcon("src/icones/fechar.png");
    Icon cancel = new ImageIcon("src/icones/cancelar1.png");
    Icon loc = new ImageIcon("src/icones/localizar.png");

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton(buscar);
    JButton btAdicionar = new JButton(add);
    JButton btSalvar = new JButton(salvar);
    JButton btAlterar = new JButton(alterar);
    JButton btExcluir = new JButton(excluir);
    JButton btListar = new JButton(listar);
    JButton btFechar = new JButton(fechar);
    JButton btCancelar = new JButton(cancel);
    JButton btLocalizar = new JButton(loc);
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btAdicionar.setVisible(c);

        btLocalizar.setVisible(r);
        btAlterar.setEnabled(u);
        btExcluir.setEnabled(d);
        btListar.setVisible(r);
    }

    public void mostrarBotoes(boolean visível) {
        btAdicionar.setVisible(visível);

        btLocalizar.setVisible(visível);
        btAlterar.setVisible(visível);
        btExcluir.setVisible(visível);
        btListar.setVisible(visível);
        btSalvar.setVisible(!visível);
        btCancelar.setVisible(!visível);
    }

    private void habilitarAtributos(boolean precoProduto, boolean produto) {
        if (precoProduto) {
            tfIdProduto1.requestFocus();
            tfIdProduto1.selectAll();
        }
        tfIdProduto1.setEnabled(precoProduto);
        tfIdProduto1.setEditable(precoProduto);
        tfIdProduto1.setEditable(produto);

    }

    public void zerarAtributos() {
        tfPrecoProduto.setText("");
    }

//////////////////// - mutável - /////////////////////////
    JLabel lbAviso = new JLabel("");
    JLabel lbIdProduto1 = new JLabel("ID:");
    JTextField tfIdProduto1 = new JTextField(10);
    JLabel lbDataPreco = new JLabel("Data:");
    DateTextField tfDataPreco = new DateTextField();
    JLabel lbPrecoProduto = new JLabel("Preço:");
    JTextField tfPrecoProduto = new JTextField(10);
    DAOProduto daoProduto = new DAOProduto();
    DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
    PrecoProduto precoProduto = new PrecoProduto();
    Produto produto = new Produto();
    PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
    String[] colunas = new String[]{"ID", "DATA", "PREÇO"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public PrecoProdutoGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - PrecoProduto");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnAvisos, BorderLayout.PAGE_END);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdProduto1);
        pnNorte.add(tfIdProduto1);
        pnNorte.add(lbDataPreco);
        pnNorte.add(tfDataPreco);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btLocalizar);
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
        pnCentro.setLayout(new GridLayout(1, 2));
        pnCentro.add(lbPrecoProduto);
        pnCentro.add(tfPrecoProduto);
        lbIdProduto1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfIdProduto1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbIdProduto1.setBorder(BorderFactory.createLineBorder(Color.black));
        tfIdProduto1.setBorder(BorderFactory.createLineBorder(Color.black));
        lbDataPreco.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDataPreco.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbDataPreco.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDataPreco.setBorder(BorderFactory.createLineBorder(Color.black));
        lbPrecoProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfPrecoProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbPrecoProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        tfPrecoProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        lbIdProduto1.setBackground(new Color(221, 184, 146));
        lbIdProduto1.setForeground(Color.BLACK);
        lbDataPreco.setBackground(new Color(221, 184, 146));
        lbDataPreco.setForeground(Color.BLACK);
        lbPrecoProduto.setBackground(new Color(221, 184, 146));
        lbPrecoProduto.setForeground(Color.BLACK);
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
        btLocalizar.setBackground(Color.white);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tfDataPreco.setText("");
        tfDataPreco.setEditable(false);
        tfPrecoProduto.setEditable(false);
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
                    precoProdutoPK.setProdutoIdProduto(Integer.valueOf(tfIdProduto1.getText()));
                    Date dt = cf.converteDeStringParaDate(tfDataPreco.getText());
                    precoProdutoPK.setDataPreco(dt);
                    precoProduto = daoPrecoProduto.obter(precoProdutoPK);
                    if (precoProduto != null) {//achou o precoProduto na lista
                        tfPrecoProduto.setText(String.valueOf(precoProduto.getPreco()));
                        //mostrar
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        atvBotoes(true, true, false, false);
                        zerarAtributos();
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
                tfIdProduto1.setEnabled(false);
                tfDataPreco.requestFocus();
                tfDataPreco.setEditable(true);
                tfDataPreco.setEnabled(true);
                tfPrecoProduto.setEditable(true);
                tfPrecoProduto.setEnabled(true);
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

                if (acao.equals("adicionar")) {
                    precoProduto = new PrecoProduto();
                }
                precoProdutoPK.setProdutoIdProduto(Integer.valueOf(tfIdProduto1.getText()));
                try {
                    precoProdutoPK.setDataPreco(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataPreco.getText()));
                } catch (Exception ex) {
                    Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                precoProduto.setPrecoProdutoPK(precoProdutoPK);
                precoProduto.setPreco(Double.valueOf(tfPrecoProduto.getText()));
                if (acao.equals("adicionar")) {
                    daoPrecoProduto.inserir(precoProduto);
                } else {
                    daoPrecoProduto.atualizar(precoProduto);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfIdProduto1.setEnabled(true);
                tfIdProduto1.setEditable(true);
                tfIdProduto1.requestFocus();
                tfIdProduto1.setText("");
                tfDataPreco.setEnabled(false);
                tfDataPreco.setEditable(false);
                tfDataPreco.setText("");
                tfPrecoProduto.setEnabled(false);
                tfPrecoProduto.setEditable(false);
                tfPrecoProduto.setText("");

            }
        }
        );

// listener Alterar
        btAlterar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdProduto1.setEditable(false);
                tfDataPreco.requestFocus();
                tfDataPreco.setEditable(true);
                tfDataPreco.setEnabled(true);
                tfPrecoProduto.setEditable(true);
                tfPrecoProduto.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdProduto1.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        }
        );

        btLocalizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                precoProdutoPK = new PrecoProdutoPK();
                tfIdProduto1.setText(tfIdProduto1.getText().trim()); // caso tenham sido digitados espaços

                if (tfIdProduto1.getText().equals("")) {
                    List< String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        if (listaAuxiliar.size() > 0) {
                            Point lc = btLocalizar.getLocationOnScreen();
                            lc.x = lc.x + btLocalizar.getWidth();
                            String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                    lc.x,
                                    lc.y).getValorRetornado();
                            if (!selectedItem.equals("")) {
                                String aux[] = selectedItem.split("-");
                                System.out.println(aux[0]);
                                tfIdProduto1.setText(aux[0]);
                                btBuscar.doClick();
                            } else {
                                tfPrecoProduto.requestFocus();
                                tfPrecoProduto.selectAll();
                            }
                        }
                    }
                }

//                    textFieldIdProduto.solicitarFoco();
//                    textFieldIdProduto.selecioneTodos();
                btAdicionar.setVisible(false);

            }
        }
        );

// listener Excluir
        btExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdProduto1.setEnabled(true);
                tfIdProduto1.setEditable(true);
                tfIdProduto1.requestFocus();
                tfIdProduto1.setText("");
                tfDataPreco.setText("");
                tfDataPreco.setEditable(false);
                tfPrecoProduto.setText("");
                tfPrecoProduto.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoPrecoProduto.remover(precoProduto);
                }
            }
        }
        );

// listener Listar
        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.listInOrderId();
                String[] colunas = new String[]{"idProduto1", "dataPreco", "precoProduto"};
                String[][] dados = new String[listaPrecoProduto.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPrecoProduto.size(); i++) {
                    aux = listaPrecoProduto.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {

                        dados[i][j] = aux[j];
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
            }
        }
        );

// listener Cancelar
        btCancelar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btCancelar.setVisible(false);
                tfIdProduto1.setText("");
                tfIdProduto1.requestFocus();
                tfIdProduto1.setEnabled(true);
                tfIdProduto1.setEditable(true);
                tfDataPreco.setText("");
                tfDataPreco.setEditable(false);
                tfPrecoProduto.setText("");
                tfPrecoProduto.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

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

        setModal(
                true);
        Date d = new Date();
        tfDataPreco.setText(d);
        pack();

        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);
    }//fim do contrutor de GUI
} //fim da classe

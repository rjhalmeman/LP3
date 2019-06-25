package GUIs;

import DAOs.*;
import Entidades.*;
import myUtil.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import myUtil.UsarGridBagLayout;

public class GUIProduto extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeNext = new ImageIcon(getClass().getResource("/icones/next.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnNext = new JButton(iconeNext);
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelIdProduto = new JLabel("IdProduto");
    JTextField textFieldIdProduto = new JTextField(20);
    JLabel labelNomeProduto = new JLabel("NomeProduto");
    JTextField textFieldNomeProduto = new JTextField(20);
    JLabel labelQuantidadeEstoqueProduto = new JLabel("QuantidadeEstoqueProduto");
    JTextField textFieldQuantidadeEstoqueProduto = new JTextField(20);
    JLabel labelStatusIdStatus = new JLabel("StatusIdStatus");
    JTextField textFieldStatusIdStatus = new JTextField(20);
    JLabel labelUnidadeDeMedidaIdUnidadeDeMedida = new JLabel("UnidadeDeMedidaIdUnidadeDeMedida");
    JTextField textFieldUnidadeDeMedidaIdUnidadeDeMedida = new JTextField(20);

//Daos para FK
    DAOStatus daoStatus = new DAOStatus();
    DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();

//Entidades para FK
    Status status = new Status();
    UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOProduto daoProduto = new DAOProduto();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Produto produto;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnNext.setEnabled(r);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnNext.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idProduto, boolean nomeProduto, boolean quantidadeEstoqueProduto, boolean statusIdStatus, boolean unidadeDeMedidaIdUnidadeDeMedida) {
        if (idProduto) {
            textFieldIdProduto.requestFocus();
            textFieldIdProduto.selectAll();
        }
        textFieldIdProduto.setEnabled(idProduto);
        textFieldIdProduto.setEditable(idProduto);
        textFieldNomeProduto.setEditable(nomeProduto);
        textFieldQuantidadeEstoqueProduto.setEditable(quantidadeEstoqueProduto);
        textFieldStatusIdStatus.setEditable(statusIdStatus);
        textFieldUnidadeDeMedidaIdUnidadeDeMedida.setEditable(unidadeDeMedidaIdUnidadeDeMedida);

    }

    public void zerarAtributos() {
        textFieldNomeProduto.setText("");
        textFieldQuantidadeEstoqueProduto.setText("");
        textFieldStatusIdStatus.setText("");
        textFieldUnidadeDeMedidaIdUnidadeDeMedida.setText("");
    }
    Color corPadrao = labelIdProduto.getBackground();

    public GUIProduto(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Produto");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelIdProduto);
        toolbar1.add(textFieldIdProduto);
        toolbar1.add(btnRetrieve);
        toolbar1.add(btnCreate);
        toolbar1.add(btnNext);
        toolbar1.add(btnUpdate);
        toolbar1.add(btnDelete);
        toolbar1.add(btnSave);
        toolbar1.add(btnCancel);
        toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();
        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelNomeProduto, textFieldNomeProduto, corPadrao);
        usarGridBagLayout.add(labelQuantidadeEstoqueProduto, textFieldQuantidadeEstoqueProduto, corPadrao);
        usarGridBagLayout.add(labelStatusIdStatus, textFieldStatusIdStatus, Color.yellow);
        usarGridBagLayout.add(labelUnidadeDeMedidaIdUnidadeDeMedida, textFieldUnidadeDeMedidaIdUnidadeDeMedida, Color.yellow);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdProduto.requestFocus();
        textFieldIdProduto.selectAll();
        textFieldIdProduto.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdProduto e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = new Produto();
                textFieldIdProduto.setText(textFieldIdProduto.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdProduto.getText().equals("")) {
                    List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdProduto.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdProduto.requestFocus();
                            textFieldIdProduto.selectAll();
                        }
                    }

                    textFieldIdProduto.requestFocus();
                    textFieldIdProduto.selectAll();
                } else {
                    try {
                        produto.setIdProduto(Integer.valueOf(textFieldIdProduto.getText()));
                        produto = daoProduto.obter(produto.getIdProduto());
                        if (produto != null) { //se encontrou na lista
                            textFieldNomeProduto.setText(String.valueOf(produto.getNomeProduto()));
                            textFieldQuantidadeEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEstoqueProduto()));
                            textFieldStatusIdStatus.setText(String.valueOf(produto.getStatusIdStatus().getIdStatus() + "-" + produto.getStatusIdStatus().getNomeStatus()));
                            textFieldUnidadeDeMedidaIdUnidadeDeMedida.setText(String.valueOf(produto.getUnidadeDeMedidaIdUnidadeDeMedida().getIdUnidadeDeMedida() + "-" + produto.getUnidadeDeMedidaIdUnidadeDeMedida().getNomeUnidadeDeMedida()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdProduto.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdProduto.setOpaque(true);
                        textFieldIdProduto.selectAll();
                        textFieldIdProduto.requestFocus();
                        textFieldIdProduto.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true);
                textFieldNomeProduto.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoProduto.autoIdProduto();
                textFieldIdProduto.setText(String.valueOf(prox));
                btnRetrieve.doClick();
                btnCreate.doClick();
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    produto = new Produto();
                }
                try {
                    produto.setIdProduto(Integer.valueOf((textFieldIdProduto.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdProduto.setBackground(Color.red);
                }
                produto.setNomeProduto(String.valueOf(textFieldNomeProduto.getText()));
                try {
                    produto.setQuantidadeEstoqueProduto(Integer.valueOf((textFieldQuantidadeEstoqueProduto.getText())));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldQuantidadeEstoqueProduto.setBackground(Color.red);
                }
                produto.setStatusIdStatus(daoStatus.obter(Integer.valueOf(textFieldStatusIdStatus.getText().split("-")[0])));
                produto.setUnidadeDeMedidaIdUnidadeDeMedida(daoUnidadeDeMedida.obter(String.valueOf(textFieldUnidadeDeMedidaIdUnidadeDeMedida.getText().split("-")[0])));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoProduto.inserir(produto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoProduto.atualizar(produto);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIProdutoListagem guiProdutoListagem = new GUIProdutoListagem(daoProduto.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + produto.getNomeProduto() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoProduto.remover(produto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeProduto.requestFocus();
                    textFieldNomeProduto.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldStatusIdStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoStatus.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldStatusIdStatus.getBounds().y + textFieldStatusIdStatus.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldStatusIdStatus.setText(selectedItem);

                        //preparar para salvar
                        status = daoStatus.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldStatusIdStatus.requestFocus();
                        textFieldStatusIdStatus.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldUnidadeDeMedidaIdUnidadeDeMedida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoUnidadeDeMedida.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldUnidadeDeMedidaIdUnidadeDeMedida.getBounds().y + textFieldUnidadeDeMedidaIdUnidadeDeMedida.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldUnidadeDeMedidaIdUnidadeDeMedida.setText(selectedItem);

                        //preparar para salvar
                        unidadeDeMedida = daoUnidadeDeMedida.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldUnidadeDeMedidaIdUnidadeDeMedida.requestFocus();
                        textFieldUnidadeDeMedidaIdUnidadeDeMedida.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldNomeProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeProduto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeProduto.setBackground(corPadrao);
            }
        });
        textFieldQuantidadeEstoqueProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldQuantidadeEstoqueProduto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldQuantidadeEstoqueProduto.setBackground(corPadrao);
            }
        });
        textFieldStatusIdStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldStatusIdStatus.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldStatusIdStatus.setBackground(Color.yellow);
            }
        });
        textFieldUnidadeDeMedidaIdUnidadeDeMedida.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldUnidadeDeMedidaIdUnidadeDeMedida.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldUnidadeDeMedidaIdUnidadeDeMedida.setBackground(Color.yellow);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        pack();
        setModal(true);
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIProduto(new Point(880, 250), new Dimension(800, 600));
    }
}

package GUIs;

import DAOs.*;
import Entidades.*;
import Main.TrabalhandoComDatas;
import myUtil.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JComboBox;

import myUtil.UsarGridBagLayout;

public class GUIPrecoProduto extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));    
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelIdProduto = new JLabel("IdProduto");
    JTextField textFieldIdProduto = new JTextField(20);
    JLabel labelDataProduto = new JLabel("Data");
    DateTextField dateTextFieldDataProduto = new DateTextField();
    JLabel labelPrecoProduto = new JLabel("Preço Produto");
    JTextField textFieldPrecoProduto = new JTextField(20);

//Daos para FK
    DAOProduto daoProduto = new DAOProduto();

//Entidades para FK
    Produto produto = new Produto();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();

    PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    PrecoProduto precoProduto;
    TrabalhandoComDatas cf = new TrabalhandoComDatas();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);

        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);

        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean precoProduto, boolean produto) {
        if (precoProduto) {
            textFieldIdProduto.requestFocus();
            textFieldIdProduto.selectAll();
        }
        textFieldIdProduto.setEnabled(precoProduto);
        textFieldIdProduto.setEditable(precoProduto);
        dateTextFieldDataProduto.setEditable(precoProduto);
        textFieldPrecoProduto.setEditable(produto);

    }

    public void zerarAtributos() {
        textFieldPrecoProduto.setText("");
    }
    Color corPadrao = labelIdProduto.getBackground();

    public GUIPrecoProduto(Point posicao, Dimension dimensao) {
        setTitle("CRUD - PrecoProduto");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
        btnCreate.setToolTipText("Inserir novo registro");

        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelIdProduto);
        toolbar1.add(textFieldIdProduto);
        toolbar1.add(labelDataProduto);
        toolbar1.add(dateTextFieldDataProduto);
        toolbar1.add(btnRetrieve);
        toolbar1.add(btnCreate);

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
        usarGridBagLayout.add(labelPrecoProduto, textFieldPrecoProduto, Color.yellow);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdProduto.requestFocus();
        textFieldIdProduto.selectAll();
        textFieldIdProduto.setBackground(Color.GREEN);
        labelAviso.setText("Digite um PrecoProduto e clic [Pesquisar]");
        
         JComboBox comboBoxProduto = new JComboBox();

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
                precoProdutoPK = new PrecoProdutoPK();
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
                        
                        //obter o nome do produto, da tabela produto
                        DAOProduto daoProd = new DAOProduto();
                        Produto prod = daoProd.obter(Integer.valueOf(textFieldIdProduto.getText()));
                        String nomeProd = "";
                        if (prod!=null) {
                            nomeProd = prod.getNomeProduto();
                        }
                        

                        precoProdutoPK.setProdutoIdProduto(Integer.valueOf(textFieldIdProduto.getText()));
                        Date dt = cf.converteDeStringParaDate(dateTextFieldDataProduto.getText());
                        precoProdutoPK.setDataPrecoProduto(dt);
                        precoProduto = daoPrecoProduto.obter(precoProdutoPK);
                        if (precoProduto != null) { //se encontrou 
                            textFieldPrecoProduto.setText(String.valueOf(precoProduto.getPrecoProduto()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false);
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
                habilitarAtributos(false, true);
                textFieldPrecoProduto.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    precoProduto = new PrecoProduto();
                }
                precoProdutoPK.setProdutoIdProduto(Integer.valueOf(textFieldIdProduto.getText()));
                precoProdutoPK.setDataPrecoProduto(cf.converteDeStringParaDate(dateTextFieldDataProduto.getText()));
                precoProduto.setPrecoProdutoPK(precoProdutoPK);
                precoProduto.setPrecoProduto(Double.valueOf(textFieldPrecoProduto.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPrecoProduto.inserir(precoProduto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPrecoProduto.atualizar(precoProduto);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false);
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
                habilitarAtributos(true, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIPrecoProdutoListagem guiPrecoProdutoListagem = new GUIPrecoProdutoListagem(daoPrecoProduto.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + precoProduto.getProduto() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPrecoProduto.remover(precoProduto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldPrecoProduto.requestFocus();
                    textFieldPrecoProduto.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldPrecoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldPrecoProduto.getBounds().y + textFieldPrecoProduto.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldPrecoProduto.setText(selectedItem);

                        //preparar para salvar
                        produto = daoProduto.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldPrecoProduto.requestFocus();
                        textFieldPrecoProduto.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum precoProduto cadastrado.");
                }
            }
        });
        textFieldPrecoProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldPrecoProduto.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldPrecoProduto.setBackground(Color.yellow);
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
        new GUIPrecoProduto(new Point(880, 250), new Dimension(800, 600));
    }
}

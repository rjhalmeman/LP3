package GUIs;

import DAOs.*;
import Entidades.*;
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

import myUtil.UsarGridBagLayout;

public class GUIUnidadeDeMedida extends JDialog {

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

    JLabel labelIdUnidadeDeMedida = new JLabel("IdUnidadeDeMedida");
    JTextField textFieldIdUnidadeDeMedida = new JTextField(20);
    JLabel labelNomeUnidadeDeMedida = new JLabel("NomeUnidadeDeMedida");
    JTextField textFieldNomeUnidadeDeMedida = new JTextField(20);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    UnidadeDeMedida unidadeDeMedida;

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

    private void habilitarAtributos(boolean idUnidadeDeMedida, boolean nomeUnidadeDeMedida) {
        if (idUnidadeDeMedida) {
            textFieldIdUnidadeDeMedida.requestFocus();
            textFieldIdUnidadeDeMedida.selectAll();
        }
        textFieldIdUnidadeDeMedida.setEnabled(idUnidadeDeMedida);
        textFieldIdUnidadeDeMedida.setEditable(idUnidadeDeMedida);
        textFieldNomeUnidadeDeMedida.setEditable(nomeUnidadeDeMedida);

    }

    public void zerarAtributos() {
        textFieldNomeUnidadeDeMedida.setText("");
    }
    Color corPadrao = labelIdUnidadeDeMedida.getBackground();

    public GUIUnidadeDeMedida(Point posicao, Dimension dimensao) {
        setTitle("CRUD - UnidadeDeMedida");
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
        toolbar1.add(labelIdUnidadeDeMedida);
        toolbar1.add(textFieldIdUnidadeDeMedida);
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
        usarGridBagLayout.add(labelNomeUnidadeDeMedida, textFieldNomeUnidadeDeMedida, corPadrao);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdUnidadeDeMedida.requestFocus();
        textFieldIdUnidadeDeMedida.selectAll();
        textFieldIdUnidadeDeMedida.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdUnidadeDeMedida e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdUnidadeDeMedida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                unidadeDeMedida = new UnidadeDeMedida();
                textFieldIdUnidadeDeMedida.setText(textFieldIdUnidadeDeMedida.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdUnidadeDeMedida.getText().equals("")) {
                    List<String> listaAuxiliar = daoUnidadeDeMedida.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdUnidadeDeMedida.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdUnidadeDeMedida.requestFocus();
                            textFieldIdUnidadeDeMedida.selectAll();
                        }
                    }

                    textFieldIdUnidadeDeMedida.requestFocus();
                    textFieldIdUnidadeDeMedida.selectAll();
                } else {
                    try {
                        unidadeDeMedida.setIdUnidadeDeMedida(String.valueOf(textFieldIdUnidadeDeMedida.getText()));
                        unidadeDeMedida = daoUnidadeDeMedida.obter(unidadeDeMedida.getIdUnidadeDeMedida());
                        if (unidadeDeMedida != null) { //se encontrou na lista
                            textFieldNomeUnidadeDeMedida.setText(String.valueOf(unidadeDeMedida.getNomeUnidadeDeMedida()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdUnidadeDeMedida.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdUnidadeDeMedida.setOpaque(true);
                        textFieldIdUnidadeDeMedida.selectAll();
                        textFieldIdUnidadeDeMedida.requestFocus();
                        textFieldIdUnidadeDeMedida.setBackground(Color.red);
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
                textFieldNomeUnidadeDeMedida.requestFocus();
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
                    unidadeDeMedida = new UnidadeDeMedida();
                }
                unidadeDeMedida.setIdUnidadeDeMedida(String.valueOf(textFieldIdUnidadeDeMedida.getText()));
                unidadeDeMedida.setNomeUnidadeDeMedida(String.valueOf(textFieldNomeUnidadeDeMedida.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoUnidadeDeMedida.inserir(unidadeDeMedida);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoUnidadeDeMedida.atualizar(unidadeDeMedida);
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
                GUIUnidadeDeMedidaListagem guiUnidadeDeMedidaListagem = new GUIUnidadeDeMedidaListagem(daoUnidadeDeMedida.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
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
                        "Confirma a exclusão do registro <ID = " + unidadeDeMedida.getNomeUnidadeDeMedida() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoUnidadeDeMedida.remover(unidadeDeMedida);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeUnidadeDeMedida.requestFocus();
                    textFieldNomeUnidadeDeMedida.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldNomeUnidadeDeMedida.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeUnidadeDeMedida.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeUnidadeDeMedida.setBackground(corPadrao);
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
        new GUIUnidadeDeMedida(new Point(880, 250), new Dimension(800, 600));
    }
}

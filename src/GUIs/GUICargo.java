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

public class GUICargo extends JDialog {

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

    JLabel labelIdCargo = new JLabel("IdCargo");
    JTextField textFieldIdCargo = new JTextField(20);
    JLabel labelNomeCargo = new JLabel("NomeCargo");
    JTextField textFieldNomeCargo = new JTextField(20);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOCargo daoCargo = new DAOCargo();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Cargo cargo;

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

    private void habilitarAtributos(boolean idCargo, boolean nomeCargo) {
        if (idCargo) {
            textFieldIdCargo.requestFocus();
            textFieldIdCargo.selectAll();
        }
        textFieldIdCargo.setEnabled(idCargo);
        textFieldIdCargo.setEditable(idCargo);
        textFieldNomeCargo.setEditable(nomeCargo);

    }

    public void zerarAtributos() {
        textFieldNomeCargo.setText("");
    }
    Color corPadrao = labelIdCargo.getBackground();

    public GUICargo(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Cargo");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelIdCargo);
        toolbar1.add(textFieldIdCargo);
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
        usarGridBagLayout.add(labelNomeCargo, textFieldNomeCargo, corPadrao);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdCargo.requestFocus();
        textFieldIdCargo.selectAll();
        textFieldIdCargo.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdCargo e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cargo = new Cargo();
                textFieldIdCargo.setText(textFieldIdCargo.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdCargo.getText().equals("")) {
                    List<String> listaAuxiliar = daoCargo.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdCargo.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdCargo.requestFocus();
                            textFieldIdCargo.selectAll();
                        }
                    }

                    textFieldIdCargo.requestFocus();
                    textFieldIdCargo.selectAll();
                } else {
                    try {
                        cargo.setIdCargo(Integer.valueOf(textFieldIdCargo.getText()));
                        cargo = daoCargo.obter(cargo.getIdCargo());
                        if (cargo != null) { //se encontrou na lista
                            textFieldNomeCargo.setText(String.valueOf(cargo.getNomeCargo()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdCargo.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdCargo.setOpaque(true);
                        textFieldIdCargo.selectAll();
                        textFieldIdCargo.requestFocus();
                        textFieldIdCargo.setBackground(Color.red);
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
                textFieldNomeCargo.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoCargo.autoIdCargo();
                textFieldIdCargo.setText(String.valueOf(prox));
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
                    cargo = new Cargo();
                }
                try {
                    cargo.setIdCargo(Integer.valueOf((textFieldIdCargo.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdCargo.setBackground(Color.red);
                }
                cargo.setNomeCargo(String.valueOf(textFieldNomeCargo.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoCargo.inserir(cargo);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoCargo.atualizar(cargo);
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
                GUICargoListagem guiCargoListagem = new GUICargoListagem(daoCargo.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
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
                        "Confirma a exclusão do registro <ID = " + cargo.getNomeCargo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoCargo.remover(cargo);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeCargo.requestFocus();
                    textFieldNomeCargo.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldNomeCargo.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeCargo.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeCargo.setBackground(corPadrao);
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
        new GUICargo(new Point(880, 250), new Dimension(800, 600));
    }
}

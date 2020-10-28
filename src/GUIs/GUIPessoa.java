package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JCheckBox;
import myUtil.DateTextField;

import myUtil.UsarGridBagLayout;

public class GUIPessoa extends JDialog {

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

    JLabel labelCpfPessoa = new JLabel("CpfPessoa");
    JTextField textFieldCpfPessoa = new JTextField(20);
    JLabel labelNomePessoa = new JLabel("NomePessoa");
    JTextField textFieldNomePessoa = new JTextField(20);
    JLabel labelFotoPessoa = new JLabel("FotoPessoa");
    JTextField textFieldFotoPessoa = new JTextField(20);
    JLabel labelEMailPessoa = new JLabel("EMailPessoa");
    JTextField textFieldEMailPessoa = new JTextField(20);

    JLabel labelDataCadastroCliente = new JLabel("Data do Cadastro");
    DateTextField textFieldDataCadastroCliente = new DateTextField();
    JLabel labelRendaCliente = new JLabel("Renda Cliente");
    JTextField textFieldRendaCliente = new JTextField(20);

    JLabel labelDataCadastroFuncionario = new JLabel("Data do Cadastro");
    DateTextField textFieldDataCadastroFuncionario = new DateTextField();
    JLabel labelCargoFuncionario = new JLabel("Cargo");
    JTextField textFieldCargoFuncionario = new JTextField(20);

    JLabel labelFuncionario = new JLabel("Funcionario");
    JTextField textFieldFuncionario = new JTextField(20);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");
    JPanel pnCF = new JPanel();

    String acao = "";//variavel para facilitar insert e update
    DAOPessoa daoPessoa = new DAOPessoa();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Pessoa pessoa;
    //   CardLayout cardLayout = new CardLayout();
    JPanel pnDireita = new JPanel(new GridLayout(2, 1));
    JPanel pnCliente = new JPanel(new GridLayout(4, 1));
    JPanel pnClienteA = new JPanel(new BorderLayout());
    JPanel pnClienteB = new JPanel(new BorderLayout());
    JPanel pnClienteC = new JPanel(new BorderLayout());
    JPanel pnClienteD = new JPanel(new BorderLayout());

    JPanel pnFuncionario = new JPanel(new GridLayout(3, 1));
    JPanel pnFuncionarioA = new JPanel();
    JPanel pnFuncionarioB = new JPanel();
    JPanel pnFuncionarioC = new JPanel();

    JCheckBox cbCliente = new JCheckBox("Cliente");
    JCheckBox cbFuncionario = new JCheckBox("Funcionário");

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

    private void habilitarAtributos(boolean cpfPessoa, boolean nomePessoa, boolean fotoPessoa, boolean eMailPessoa, boolean cliente, boolean funcionario) {
        if (cpfPessoa) {
            textFieldCpfPessoa.requestFocus();
            textFieldCpfPessoa.selectAll();
        }
        textFieldCpfPessoa.setEnabled(cpfPessoa);
        textFieldCpfPessoa.setEditable(cpfPessoa);
        textFieldNomePessoa.setEditable(nomePessoa);
        textFieldFotoPessoa.setEditable(fotoPessoa);
        textFieldEMailPessoa.setEditable(eMailPessoa);

    }

    public void zerarAtributos() {
        textFieldNomePessoa.setText("");
        textFieldFotoPessoa.setText("");
        textFieldEMailPessoa.setText("");
        textFieldDataCadastroCliente.setText("");
        textFieldFuncionario.setText("");
    }
    Color corPadrao = labelCpfPessoa.getBackground();

    public GUIPessoa(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Pessoa");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelCpfPessoa);
        toolbar1.add(textFieldCpfPessoa);
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
        usarGridBagLayout.add(labelNomePessoa, textFieldNomePessoa, corPadrao);
        usarGridBagLayout.add(labelFotoPessoa, textFieldFotoPessoa, corPadrao);
        usarGridBagLayout.add(labelEMailPessoa, textFieldEMailPessoa, corPadrao);
        usarGridBagLayout.add(new JLabel("Tipo"), pnCF);
        pnCF.setBackground(Color.pink);
        pnCF.add(cbCliente);
        pnCF.add(cbFuncionario);

        pnAvisos.add(labelAviso);

        pnDireita.setBackground(Color.green);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        cp.add(pnDireita, BorderLayout.EAST);

        pnDireita.add(pnCliente);
        pnDireita.add(pnFuncionario);

        pnClienteA.setBackground(Color.cyan);
        pnFuncionarioA.setBackground(Color.pink);
        pnFuncionario.add(pnFuncionarioA);
        pnFuncionario.add(pnFuncionarioB);
        pnFuncionario.add(pnFuncionarioC);

        pnFuncionarioA.add(new JLabel("Funcionário"));
        pnFuncionarioB.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnFuncionarioC.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnFuncionarioB.add(labelDataCadastroFuncionario);
        pnFuncionarioB.add(textFieldDataCadastroFuncionario);
        pnFuncionarioC.add(labelCargoFuncionario);
        pnFuncionarioC.add(textFieldCargoFuncionario);

        pnCliente.add(pnClienteA);
        pnCliente.add(pnClienteB);
        pnCliente.add(pnClienteC);
        pnCliente.add(pnClienteD);

        //   cardLayout.show(pnDireita, "cliente");
        pnClienteA.add(new JLabel("Cliente"));
        pnClienteB.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnClienteC.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnClienteB.add(labelDataCadastroCliente);
        pnClienteB.add(textFieldDataCadastroCliente);
        pnClienteC.add(labelRendaCliente);
        pnClienteC.add(textFieldRendaCliente);

        textFieldCpfPessoa.requestFocus();
        textFieldCpfPessoa.selectAll();
        textFieldCpfPessoa.setBackground(Color.GREEN);
        labelAviso.setText("Digite um CpfPessoa e clic [Pesquisar]");
        cbCliente.setSelected(false);
        cbFuncionario.setSelected(false);
        pnCliente.setVisible(false);
        pnFuncionario.setVisible(false);

//--------------- listeners ----------------- 
        cbCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCliente.setVisible(cbCliente.isSelected());
                //  labelAviso.setText("cli");
            }
        });
        cbFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnFuncionario.setVisible(cbFuncionario.isSelected());
                // labelAviso.setText("func");
            }
        });

        textFieldCpfPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pessoa = new Pessoa();
                textFieldCpfPessoa.setText(textFieldCpfPessoa.getText().trim());//caso tenham sido digitados espaços

                if (textFieldCpfPessoa.getText().equals("")) {
                    List<String> listaAuxiliar = daoPessoa.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldCpfPessoa.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldCpfPessoa.requestFocus();
                            textFieldCpfPessoa.selectAll();
                        }
                    }

                    textFieldCpfPessoa.requestFocus();
                    textFieldCpfPessoa.selectAll();
                } else {
                    try {
                        pessoa.setCpfPessoa(String.valueOf(textFieldCpfPessoa.getText()));
                        pessoa = daoPessoa.obter(pessoa.getCpfPessoa());
                        if (pessoa != null) { //se encontrou
                            textFieldNomePessoa.setText(String.valueOf(pessoa.getNomePessoa()));
                            textFieldFotoPessoa.setText(String.valueOf(pessoa.getFotoPessoa()));
                            textFieldEMailPessoa.setText(String.valueOf(pessoa.getEMailPessoa()));
                            textFieldDataCadastroCliente.setText(String.valueOf(pessoa.getCliente()));
                            textFieldFuncionario.setText(String.valueOf(pessoa.getFuncionario()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";

                            DAOCliente daoCliente = new DAOCliente();
                            Cliente cliente = daoCliente.obter(pessoa.getCpfPessoa());

                            if (cliente != null) { //é cliente
                                cbCliente.setSelected(true);
                                pnCliente.setVisible(true);
                                textFieldDataCadastroCliente.setText(cliente.getDataCadastro());
                                textFieldRendaCliente.setText(String.valueOf(cliente.getRendaCliente()));
                            } else {
                                cbCliente.setSelected(false);
                                pnCliente.setVisible(false);
                            }
                            DAOFuncionario daoFuncionario = new DAOFuncionario();
                            Funcionario funcionario = daoFuncionario.obter(pessoa.getCpfPessoa());
                            if (funcionario != null) {
                                cbFuncionario.setSelected(true);
                                pnFuncionario.setVisible(true);
                                textFieldDataCadastroCliente.setText(funcionario.getDataCadastro());
                                textFieldRendaCliente.setText(String.valueOf(funcionario.getCargoIdCargo()));
                            } else {
                                cbFuncionario.setSelected(false);
                                pnFuncionario.setVisible(false);
                            }

                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldCpfPessoa.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldCpfPessoa.setOpaque(true);
                        textFieldCpfPessoa.selectAll();
                        textFieldCpfPessoa.requestFocus();
                        textFieldCpfPessoa.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true, true);
                textFieldNomePessoa.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoPessoa.autoCpfPessoa();
                textFieldCpfPessoa.setText(String.valueOf(prox));
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
                    pessoa = new Pessoa();
                }
                pessoa.setCpfPessoa(String.valueOf(textFieldCpfPessoa.getText()));
                pessoa.setNomePessoa(String.valueOf(textFieldNomePessoa.getText()));
                pessoa.setFotoPessoa(String.valueOf(textFieldFotoPessoa.getText()));
                pessoa.setEMailPessoa(String.valueOf(textFieldEMailPessoa.getText()));
//                pessoa.setCliente(Cliente.valueOf(textFieldDataCadastroCliente.getText()));
//                pessoa.setFuncionario(Funcionario.valueOf(textFieldFuncionario.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPessoa.inserir(pessoa);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPessoa.atualizar(pessoa);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIPessoaListagem guiPessoaListagem = new GUIPessoaListagem(daoPessoa.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + pessoa.getNomePessoa() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPessoa.remover(pessoa);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomePessoa.requestFocus();
                    textFieldNomePessoa.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldNomePessoa.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomePessoa.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomePessoa.setBackground(corPadrao);
            }
        });
        textFieldFotoPessoa.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldFotoPessoa.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldFotoPessoa.setBackground(corPadrao);
            }
        });
        textFieldEMailPessoa.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEMailPessoa.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEMailPessoa.setBackground(corPadrao);
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
        new GUIPessoa(new Point(880, 250), new Dimension(800, 600));
    }
}

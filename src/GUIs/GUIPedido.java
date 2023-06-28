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
import java.util.ArrayList;

import myUtil.UsarGridBagLayout;

public class GUIPedido extends JDialog {

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

    JLabel labelIdPedido = new JLabel("IdPedido");
    JTextField textFieldIdPedido = new JTextField(20);
    JLabel labelDataPedido = new JLabel("DataPedido");
    DateTextField textFieldDataPedido = new DateTextField();
    JLabel labelClientePessoaCpfPessoa = new JLabel("ClientePessoaCpfPessoa");
    JTextField textFieldClientePessoaCpfPessoa = new JTextField(20);
    JLabel labelFuncionarioPessoaCpfPessoa = new JLabel("FuncionarioPessoaCpfPessoa");
    JTextField textFieldFuncionarioPessoaCpfPessoa = new JTextField(20);

//Daos para FK
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    DAOPessoa daoPessoa = new DAOPessoa();

//Entidades para FK
    Cliente cliente = new Cliente();
    Funcionario funcionario = new Funcionario();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOPedido daoPedido = new DAOPedido();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Pedido pedido;

    ConversorDeDatas conversorDeDatas = new ConversorDeDatas();

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

    private void habilitarAtributos(boolean idPedido, boolean dataPedido, boolean clientePessoaCpfPessoa, boolean funcionarioPessoaCpfPessoa, boolean pedidoHasProdutoCollection) {
        if (idPedido) {
            textFieldIdPedido.requestFocus();
            textFieldIdPedido.selectAll();
        }
        textFieldIdPedido.setEnabled(idPedido);
        textFieldIdPedido.setEditable(idPedido);
        textFieldDataPedido.setEditable(dataPedido);
        textFieldClientePessoaCpfPessoa.setEditable(clientePessoaCpfPessoa);
        textFieldFuncionarioPessoaCpfPessoa.setEditable(funcionarioPessoaCpfPessoa);

    }

    public void zerarAtributos() {
        textFieldDataPedido.setText("");
        textFieldClientePessoaCpfPessoa.setText("");
        textFieldFuncionarioPessoaCpfPessoa.setText("");
    }
    Color corPadrao = labelIdPedido.getBackground();

    public GUIPedido(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Pedido");
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
        toolbar1.add(labelIdPedido);
        toolbar1.add(textFieldIdPedido);
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
        JPanel centroPedido = new JPanel();
        JPanel centroItensDoPedido = new JPanel(new BorderLayout());

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(centroPedido, BorderLayout.NORTH);
        centro.add(centroItensDoPedido, BorderLayout.CENTER);

        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centroPedido);
        usarGridBagLayout.add(labelDataPedido, textFieldDataPedido, corPadrao);
        usarGridBagLayout.add(labelClientePessoaCpfPessoa, textFieldClientePessoaCpfPessoa, Color.yellow);
        usarGridBagLayout.add(labelFuncionarioPessoaCpfPessoa, textFieldFuncionarioPessoaCpfPessoa, Color.yellow);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);

        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdPedido.requestFocus();
        textFieldIdPedido.selectAll();
        textFieldIdPedido.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdPedido e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pedido = new Pedido();
                textFieldIdPedido.setText(textFieldIdPedido.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdPedido.getText().equals("")) {
                    List<String> listaAuxiliar = new ArrayList<>();
                    List<Pedido> lp = daoPedido.list();
                    for (Pedido pedido : lp) {
                        listaAuxiliar.add(""
                                + pedido.getIdPedido()
                                + "-" + conversorDeDatas.getDateParaStringBr(pedido.getDataPedido())
                                + "-" + daoPessoa.obter(pedido.getClientePessoaCpfPessoa().getPessoaCpfPessoa()).getNomePessoa());
                    }

                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdPedido.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdPedido.requestFocus();
                            textFieldIdPedido.selectAll();
                        }
                    }

                    textFieldIdPedido.requestFocus();
                    textFieldIdPedido.selectAll();
                } else {// se não é vazio
                    try {
                        pedido.setIdPedido(Integer.valueOf(textFieldIdPedido.getText()));
                        pedido = daoPedido.obter(pedido.getIdPedido());
                        if (pedido != null) { //se encontrou na lista

                            GUIPedidoHasProdutoJTable guiPedidoHasProdutoBeamJTable = new GUIPedidoHasProdutoJTable(centroItensDoPedido, pedido.getIdPedido());
                            pack();

                            textFieldDataPedido.setText(sdf.format(pedido.getDataPedido()));
                            textFieldClientePessoaCpfPessoa.setText(pedido.getClientePessoaCpfPessoa().getPessoaCpfPessoa() + "-"
                                    + daoPessoa.obter(String.valueOf(pedido.getClientePessoaCpfPessoa().getPessoaCpfPessoa())).getNomePessoa());

                            textFieldFuncionarioPessoaCpfPessoa.setText(String.valueOf(pedido.getFuncionarioPessoaCpfPessoa().getPessoaCpfPessoa() + "-"
                                    + daoPessoa.obter(String.valueOf(pedido.getFuncionarioPessoaCpfPessoa().getPessoaCpfPessoa())).getNomePessoa()));

                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdPedido.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdPedido.setOpaque(true);
                        textFieldIdPedido.selectAll();
                        textFieldIdPedido.requestFocus();
                        textFieldIdPedido.setBackground(Color.red);
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
                textFieldDataPedido.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";              
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoPedido.autoIdPedido();
                textFieldIdPedido.setText(String.valueOf(prox));
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
                    pedido = new Pedido();
                }
                try {
                    pedido.setIdPedido(Integer.valueOf((textFieldIdPedido.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdPedido.setBackground(Color.red);
                }
                try {
                    sdf.setLenient(false);
                    pedido.setDataPedido(sdf.parse((textFieldDataPedido.getText())));
                } catch (Exception erro3) {
                    deuRuim = true;
                    textFieldDataPedido.setBackground(Color.red);
                }

                String cpfCliente = String.valueOf(textFieldClientePessoaCpfPessoa.getText().split("-")[0]);
                Cliente cli = daoCliente.obter(cpfCliente);
                pedido.setClientePessoaCpfPessoa(cli);
                if (cli == null) {
                    deuRuim = true;
                    textFieldClientePessoaCpfPessoa.setBackground(Color.red);
                }

                try {
                    pedido.setFuncionarioPessoaCpfPessoa(daoFuncionario.obter(String.valueOf(textFieldFuncionarioPessoaCpfPessoa.getText().split("-")[0])));

                } catch (Exception e) {
                    deuRuim = true;
                    textFieldFuncionarioPessoaCpfPessoa.setBackground(Color.red);
                }

                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPedido.inserir(pedido);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPedido.atualizar(pedido);
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
                GUIPedidoListagem guiPedidoListagem = new GUIPedidoListagem(daoPedido.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
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
                        "Confirma a exclusão do registro <ID = " + pedido.getDataPedido() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPedido.remover(pedido);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldDataPedido.requestFocus();
                    textFieldDataPedido.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldClientePessoaCpfPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = new ArrayList<>();
                List<Cliente> listaCliente = daoCliente.listInOrderId(); //busca a lista de clientes

                for (Cliente c : listaCliente) {//para cada funcionário na lista, buscar o nome dele na tabela pessoa
                    listaAuxiliar.add(c.getPessoaCpfPessoa() + "-" + daoPessoa.obter(c.getPessoaCpfPessoa()).getNomePessoa());
                }
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldClientePessoaCpfPessoa.getBounds().y + textFieldClientePessoaCpfPessoa.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldClientePessoaCpfPessoa.setText(selectedItem);

                        //preparar para salvar
                        cliente = daoCliente.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldClientePessoaCpfPessoa.requestFocus();
                        textFieldClientePessoaCpfPessoa.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum pedido cadastrado.");
                }
            }
        });
        textFieldFuncionarioPessoaCpfPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = new ArrayList<>();
                List<Funcionario> listaFuncionario = daoFuncionario.listInOrderId(); //busca a lista de funcionários

                for (Funcionario funcionario : listaFuncionario) {//para cada funcionário na lista, buscar o nome dele na tabela pessoa
                    listaAuxiliar.add(funcionario.getPessoaCpfPessoa() + "-" + daoPessoa.obter(funcionario.getPessoaCpfPessoa()).getNomePessoa());
                }

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldFuncionarioPessoaCpfPessoa.getBounds().y + textFieldFuncionarioPessoaCpfPessoa.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldFuncionarioPessoaCpfPessoa.setText(selectedItem);

                        //preparar para salvar
                        funcionario = daoFuncionario.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldFuncionarioPessoaCpfPessoa.requestFocus();
                        textFieldFuncionarioPessoaCpfPessoa.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum pedido cadastrado.");
                }
            }
        });
        textFieldDataPedido.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataPedido.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataPedido.setBackground(corPadrao);
            }
        });
        textFieldClientePessoaCpfPessoa.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldClientePessoaCpfPessoa.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldClientePessoaCpfPessoa.setBackground(Color.yellow);
            }
        });
        textFieldFuncionarioPessoaCpfPessoa.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldFuncionarioPessoaCpfPessoa.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldFuncionarioPessoaCpfPessoa.setBackground(Color.yellow);
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
        new GUIPedido(new Point(880, 250), new Dimension(800, 600));
    }
}

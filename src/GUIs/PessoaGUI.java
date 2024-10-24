package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import Entidades.Pessoa;
import DAOs.DAOPessoa;
import Entidades.Cliente;
import Entidades.Funcionario;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
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
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.text.MaskFormatter;
import tools.JanelaPesquisar;

/**
 *
 * @author belly 02/06/2023 - 10:50:51
 */
public class PessoaGUI extends JDialog {

    MaskFormatter telFormat = new MaskFormatter();
    MaskFormatter cpfFormat = new MaskFormatter();

    Icon buscar = new ImageIcon("src/icones/retrieve.png");
    Icon add = new ImageIcon("src/icones/create.png");
    Icon salvar = new ImageIcon("src/icones/save.png");
    Icon alterar = new ImageIcon("src/icones/update.png");
    Icon excluir = new ImageIcon("src/icones/delete.png");
    Icon listar = new ImageIcon("src/icones/list.png");
    Icon fechar = new ImageIcon("src/icones/fechar.png");
    Icon cancel = new ImageIcon("src/icones/cancelar1.png");
    Icon loc = new ImageIcon("src/icones/localizar.png");
    Icon check = new ImageIcon("src/check-mark.png");
    Icon wrong = new ImageIcon("src/medico.png");

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JPanel pnAt = new JPanel();
    JTabbedPane pnAbas = new JTabbedPane();
    JPanel pncliente = new JPanel(new GridLayout(3, 2));
    JPanel clientebt = new JPanel();
    JPanel pnfuncionario = new JPanel(new GridLayout(6, 2));
    JPanel funcionariobt = new JPanel();

    JButton btBuscar = new JButton(buscar);
    JButton btAdicionar = new JButton(add);
    JButton btSalvar = new JButton(salvar);
    JButton btSalvar2 = new JButton("Salvar");
    JButton btSalvar3 = new JButton("Salvar");
    JButton btCadatrarCliente = new JButton("Cadastrar Cliente");
    JButton btAtualizarCliente = new JButton("Atualizar Cliente");
    JButton btCadatrarFuncionario = new JButton("Cadastrar Funcionário");
    JButton btAtualizarFuncionario = new JButton("Atualizar Funcionário");
    JButton btAlterar = new JButton(alterar);
    JButton btExcluir = new JButton(excluir);
    JButton btListar = new JButton(listar);
    JButton btFechar = new JButton(fechar);
    JButton btCancelar = new JButton(cancel);
    JButton btLocalizar = new JButton(loc);
    JCheckBox cbNotificacao = new JCheckBox("Receber Notificacao", false);
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));

    private CardLayout cardLayout;
    private CardLayout cardLayout2;

//////////////////// - mutável - /////////////////////////
    JLabel lbCpf = new JLabel("CPF:");
    JTextField tfCpf = new JFormattedTextField(cpfFormat);

    JLabel lbAvisos = new JLabel("");
    JLabel lbNome = new JLabel("Nome:");
    JTextField tfNome = new JTextField(20);
    JLabel lbCargo = new JLabel("Cargo:");
    JTextField tfCargo = new JTextField(20);
    JLabel lbSalario = new JLabel("Salário:");
    JTextField tfSalario = new JTextField(20);
    JLabel lbTel = new JLabel("Celular:");
    JTextField tfTel = new JFormattedTextField(telFormat);
    JLabel lbTelFuncionario = new JLabel("Telefone:");
    JTextField tfTelFuncionario = new JFormattedTextField(telFormat);
    JLabel lbDatanascimento = new JLabel("Data de Nascimento:");
    DateTextField tfDatanascimento = new DateTextField();
    JLabel dataCadastro = new JLabel("Data do Cadastro:");
    DateTextField tfDataCadastro = new DateTextField();
    JLabel dataInicio = new JLabel("Data de Início:");
    DateTextField tfDataInicio = new DateTextField();
    JLabel dataDemissao = new JLabel("Data de Demissão:");
    DateTextField tfDataDemissao = new DateTextField();
    JLabel lbEmail = new JLabel("E-mail:");
    JTextField tfEmail = new JTextField(20);
    JLabel lbVazio = new JLabel("");
    DAOPessoa daoPessoa = new DAOPessoa();
    Pessoa pessoa = new Pessoa();
    Cliente cliente = new Cliente();
    Funcionario funcionario = new Funcionario();
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();

    String[] colunas = new String[]{"cpf", "nome", "tel", "datanascimento", "email"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public PessoaGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Pessoa");
        pnAbas.add(("Funcionário"), pnfuncionario);
        pnAbas.add("Cliente", pncliente);
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnAt, BorderLayout.PAGE_END);
        pnAt.add(pnAbas);

        pncliente.add(cbNotificacao);
        pncliente.add(lbVazio);
        pncliente.add(dataCadastro);
        pncliente.add(tfDataCadastro);
        pncliente.add(clientebt);
        pnfuncionario.add(lbCargo);
        pnfuncionario.add(tfCargo);
        pnfuncionario.add(lbSalario);
        pnfuncionario.add(tfSalario);
        pnfuncionario.add(lbTelFuncionario);
        pnfuncionario.add(tfTelFuncionario);
        pnfuncionario.add(dataInicio);
        pnfuncionario.add(tfDataInicio);
        pnfuncionario.add(dataDemissao);
        pnfuncionario.add(tfDataDemissao);
        pnfuncionario.add(funcionariobt);
        funcionario = new Funcionario();

        try {
            telFormat.setMask("(##) #####-####");

        } catch (ParseException ex) {
            Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cpfFormat.setMask("###.###.###-##");
        } catch (ParseException ex) {
            Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        clientebt.setLayout(new FlowLayout(FlowLayout.LEFT));
        funcionariobt.setLayout(new FlowLayout(FlowLayout.LEFT));
        clientebt.add(btCadatrarCliente);
        clientebt.add(btAtualizarCliente);
        clientebt.add(btSalvar3);
        funcionariobt.add(btCadatrarFuncionario);
        funcionariobt.add(btAtualizarFuncionario);
        funcionariobt.add(btSalvar2);
        pnNorte.add(lbCpf);
        pnNorte.add(tfCpf);
        pnNorte.add(btBuscar);
        pnNorte.add(btLocalizar);
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
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbTel);
        pnCentro.add(tfTel);
        pnCentro.add(lbDatanascimento);
        pnCentro.add(tfDatanascimento);
        pnCentro.add(lbEmail);
        pnCentro.add(tfEmail);
        lbCpf.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfCpf.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbCpf.setBorder(BorderFactory.createLineBorder(Color.black));
        tfCpf.setBorder(BorderFactory.createLineBorder(Color.black));
        lbNome.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfNome.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbNome.setBorder(BorderFactory.createLineBorder(Color.black));
        tfNome.setBorder(BorderFactory.createLineBorder(Color.black));
        lbVazio.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        cbNotificacao.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbVazio.setBorder(BorderFactory.createLineBorder(Color.black));
        cbNotificacao.setBorder(BorderFactory.createLineBorder(Color.black));
        pnAbas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        dataCadastro.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        dataCadastro.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDataCadastro.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDataCadastro.setBorder(BorderFactory.createLineBorder(Color.black));
        lbTel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfTel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbTel.setBorder(BorderFactory.createLineBorder(Color.black));
        tfTel.setBorder(BorderFactory.createLineBorder(Color.black));
        lbDatanascimento.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDatanascimento.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbDatanascimento.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDatanascimento.setBorder(BorderFactory.createLineBorder(Color.black));
        lbEmail.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfEmail.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbEmail.setBorder(BorderFactory.createLineBorder(Color.black));
        tfEmail.setBorder(BorderFactory.createLineBorder(Color.black));
        lbCpf.setBackground(new Color(221, 184, 146));
        lbCpf.setForeground(Color.BLACK);
        lbNome.setBackground(new Color(221, 184, 146));
        lbNome.setForeground(Color.BLACK);
        lbTel.setBackground(new Color(221, 184, 146));
        lbTel.setForeground(Color.BLACK);
        lbDatanascimento.setBackground(new Color(221, 184, 146));
        lbDatanascimento.setForeground(Color.BLACK);
        lbEmail.setBackground(new Color(221, 184, 146));
        lbEmail.setForeground(Color.BLACK);
        pnCentro.setBackground(new Color(221, 184, 146));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btSalvar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAlterar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btExcluir.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCancelar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCadatrarCliente.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btCadatrarFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btAtualizarCliente.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btAtualizarFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btSalvar2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btSalvar3.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfCargo.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        lbCargo.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfSalario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        lbSalario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfDataInicio.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        dataInicio.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfDataDemissao.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        dataDemissao.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfTelFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        lbTelFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        cbNotificacao.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        tfDataCadastro.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        dataCadastro.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        btCadatrarCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        btCadatrarFuncionario.setBorder(BorderFactory.createLineBorder(Color.black));
        btAtualizarCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        btAtualizarFuncionario.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar2.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar3.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAlterar.setBorder(BorderFactory.createLineBorder(Color.black));
        btExcluir.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btLocalizar.setBorder(BorderFactory.createLineBorder(Color.black));
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
        tfNome.setEditable(false);
        tfTel.setEditable(false);
        tfDatanascimento.setText("");
        tfDatanascimento.setEditable(false);
        tfEmail.setEditable(false);
        cardLayout = new CardLayout();
        cardLayout2 = new CardLayout();
        pnSul.setLayout(cardLayout);
        pnAt.setLayout(cardLayout2);

        pnSul.add(pnListagem, "listagem");
        pnAt.add(pnAbas, "abas");
        tabela.setEnabled(false);

        pnAbas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && pnAbas.indexAtLocation(evt.getX(), evt.getY()) == 0) {
                    ListaFuncionariosGUI listaFuncionariosGUI = new ListaFuncionariosGUI(getBounds().x, getBounds().y, new Dimension(800, 600));
                }
                if (evt.getClickCount() == 2 && pnAbas.indexAtLocation(evt.getX(), evt.getY()) == 1) {
                    ListaClientesGUI listaClientesGUI = new ListaClientesGUI(getBounds().x, getBounds().y, new Dimension(800, 600));
                }
            }
        });
// listener Buscar
        btBuscar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    pessoa = daoPessoa.obter(tfCpf.getText());
                    if (pessoa != null) {//achou o pessoa na lista
                        //mostrar
                        btFechar.doClick();
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(pessoa.getNomePessoa());
                        tfNome.setEditable(false);
                        tfNome.setEnabled(true);
                        tfTel.setText(pessoa.getTelPessoa());
                        tfTel.setEditable(false);
                        tfTel.setEnabled(true);
                        tfDatanascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDataDeNascimento()));
                        tfDatanascimento.setEditable(false);
                        tfEmail.setText(pessoa.getEmailPessoa());
                        tfEmail.setEditable(false);
                        tfEmail.setEnabled(true);

                        funcionario = daoFuncionario.obter(tfCpf.getText());
                        if (funcionario != null) {
                            cardLayout2.show(pnAt, "abas");
                            tfCargo.setText(funcionario.getCargo());
                            tfSalario.setText(String.valueOf(funcionario.getSalarioFuncionario()));
                            tfTelFuncionario.setText(funcionario.getTelFuncionario());
                            tfDataInicio.setText(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getDataInicio()));
                            tfDataDemissao.setText(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getDataDemissao()));
                            btCadatrarFuncionario.setVisible(false);
                            btSalvar2.setVisible(false);
                            btAtualizarFuncionario.setVisible(true);
                            tfCargo.setEditable(false);
                            tfSalario.setEditable(false);
                            tfDataInicio.setEditable(false);
                            tfDataDemissao.setEditable(false);
                            tfTelFuncionario.setEditable(false);

                        } else {
                            btCadatrarFuncionario.setVisible(true);
                            btAtualizarFuncionario.setVisible(false);
                            btSalvar2.setVisible(false);
                        }
                        cliente = daoCliente.obter(tfCpf.getText());
                        if (cliente != null) {
                            cardLayout2.show(pnAt, "abas");
                            lbAvisos.setText((lbAvisos.getText() + "CLIENTE"));
                            cbNotificacao.setSelected(cliente.getReceberNotificacao() == 1 ? true : false);
                            tfDataCadastro.setText(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataCadastro()));
                            btCadatrarCliente.setVisible(false);
                            tfDataCadastro.setEditable(false);
                            cbNotificacao.setEnabled(false);
                            btSalvar3.setVisible(false);
                            btAtualizarCliente.setVisible(true);
                            cbNotificacao.setEnabled(false);

                        } else {
                            btCadatrarCliente.setVisible(true);
                            btAtualizarCliente.setVisible(false);
                            btSalvar2.setVisible(false);
                        }
                        pack();
                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfCargo.setEditable(false);
                        tfSalario.setEditable(false);
                        tfDataInicio.setEditable(false);
                        tfDataDemissao.setEditable(false);
                        tfTelFuncionario.setEditable(false);
                        tfDataCadastro.setEnabled(false);
                        cbNotificacao.setEnabled(false);
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfNome.setEditable(false);
                        tfTel.setText("");
                        tfTel.setEditable(false);
                        tfDatanascimento.setText("");
                        tfDataInicio.setText("");
                        tfDataDemissao.setText("");
                        tfSalario.setText("");
                        tfTelFuncionario.setText("");
                        tfCargo.setText("");
                        tfDatanascimento.setEditable(false);
                        tfEmail.setText("");
                        tfEmail.setEditable(false);
                        btAtualizarCliente.setVisible(false);
                        btAtualizarFuncionario.setVisible(false);
                        btSalvar3.setVisible(false);
                        btSalvar2.setVisible(false);
                        tfDataCadastro.setEditable(false);
                        cbNotificacao.setEnabled(false);
                        tfCargo.setEditable(false);
                        tfSalario.setEditable(false);
                        tfDataInicio.setEditable(false);
                        tfDataDemissao.setEditable(false);
                        tfTelFuncionario.setEditable(false);

                    }
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        );

// listener Adicionar
        btAdicionar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfCpf.setEnabled(false);
                tfNome.requestFocus();
                tfNome.setEditable(true);
                tfNome.setEnabled(true);
                tfTel.setEditable(true);
                tfTel.setEnabled(true);
                tfDatanascimento.setEditable(true);
                tfDatanascimento.setEnabled(true);
                tfEmail.setEditable(true);
                tfEmail.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        }
        );

// listener Salvar
        btSalvar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    if (acao.equals("adicionar")) {
                        pessoa = new Pessoa();
                    }
                    pessoa.setCpfPessoa(tfCpf.getText());
                    pessoa.setNomePessoa(tfNome.getText());
                    pessoa.setTelPessoa(tfTel.getText());
                    try {
                        pessoa.setDataDeNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(tfDatanascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pessoa.setEmailPessoa(tfEmail.getText());
                    if (acao.equals("adicionar")) {
                        daoPessoa.inserir(pessoa);
                    } else {
                        daoPessoa.atualizar(pessoa);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfCpf.setEnabled(true);
                    tfCpf.setEditable(true);
                    tfCpf.requestFocus();
                    tfCpf.setText("");
                    tfNome.setEnabled(false);
                    tfNome.setEditable(false);
                    tfNome.setText("");
                    tfTel.setEnabled(false);
                    tfTel.setEditable(false);
                    tfTel.setText("");
                    tfDatanascimento.setEnabled(false);
                    tfDatanascimento.setEditable(false);
                    tfDatanascimento.setText("");
                    tfEmail.setEnabled(false);
                    tfEmail.setEditable(false);
                    tfEmail.setText("");
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Tente Novamente", "Erro Ao Salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        );
        btSalvar2.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (acao.equals("adicionar")) {
                    funcionario = new Funcionario();

                    funcionario.setPessoaCpfPessoa(tfCpf.getText());
                    funcionario.setCargo(tfCargo.getText());
                    funcionario.setTelFuncionario(tfTelFuncionario.getText());
                    funcionario.setSalarioFuncionario(Double.parseDouble(tfSalario.getText()));

                    try {
                        funcionario.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataInicio.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        funcionario.setDataDemissao(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataDemissao.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    daoFuncionario.inserir(funcionario);
                } else {
                    funcionario.setCargo(tfCargo.getText());
                    funcionario.setPessoaCpfPessoa(tfCpf.getText());
                    funcionario.setSalarioFuncionario(Double.valueOf(tfSalario.getText()));
                    try {
                        funcionario.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataInicio.getText()));
                        funcionario.setDataDemissao(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataDemissao.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    funcionario.setTelFuncionario(tfTelFuncionario.getText());
                    daoFuncionario.atualizar(funcionario);
                }
                btSalvar2.setVisible(false);
                tfCpf.setText("");
                tfNome.setText("");
                tfTel.setText("");
                tfDatanascimento.setText("");
                tfEmail.setText("");
                cbNotificacao.setSelected(false);
                tfDataCadastro.setText("");
                tfCargo.setText("");
                tfSalario.setText("");
                tfDataInicio.setText("");
                tfDataDemissao.setText("");
                tfTelFuncionario.setText("");
            }

        }
        );
        btSalvar3.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Short not;
                if (cbNotificacao.isSelected()) {
                    not = 1;
                } else {
                    not = 0;
                }
                if (acao.equals("adicionar")) {
                    cliente = new Cliente();

                    DAOs.DAOCliente daoCliente = new DAOs.DAOCliente();
                    cliente.setPessoaCpfPessoa(tfCpf.getText());
                    try {
                        cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataCadastro.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    cliente.setReceberNotificacao(not);
                    daoCliente.inserir(cliente);
                } else {
                    try {
                        cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataCadastro.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(PessoaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cliente.setReceberNotificacao(not);
                    cliente.setPessoaCpfPessoa(tfCpf.getText());
                    daoCliente.atualizar(cliente);
                }
                btSalvar3.setVisible(false);
                cbNotificacao.setSelected(false);
                tfDataCadastro.setText("");
                tfCpf.setText("");
                tfNome.setText("");
                tfTel.setText("");
                tfDatanascimento.setText("");
                tfEmail.setText("");
                tfCargo.setText("");
                tfSalario.setText("");
                tfDataInicio.setText("");
                tfDataDemissao.setText("");
                tfTelFuncionario.setText("");
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
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                tfNome.setEditable(true);
                tfNome.setEnabled(true);
                tfTel.setEditable(true);
                tfTel.setEnabled(true);
                tfDatanascimento.setEditable(true);
                tfDatanascimento.setEnabled(true);
                tfEmail.setEditable(true);
                tfEmail.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfCpf.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        }
        );

        btAtualizarCliente.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btSalvar3.setVisible(true);
                cbNotificacao.setEnabled(true);
                tfDataCadastro.setEditable(true);
                tfDataCadastro.setEnabled(true);
                acao = "atualizar";

            }
        }
        );
        btAtualizarFuncionario.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfCargo.setEnabled(true);
                tfSalario.setEnabled(true);
                tfDataInicio.setEnabled(true);
                tfDataDemissao.setEnabled(true);
                tfTelFuncionario.setEnabled(true);
                tfCargo.setEditable(true);
                tfSalario.setEditable(true);
                tfTelFuncionario.setEditable(true);
                tfDataInicio.setEditable(true);
                tfDataDemissao.setEditable(true);
                btSalvar2.setVisible(true);
                acao = "atualizar";

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
                tfCpf.setEnabled(true);
                tfCpf.setEditable(true);
                tfCpf.requestFocus();
                tfCpf.setText("");
                tfNome.setText("");
                tfNome.setEditable(false);
                tfTel.setText("");
                tfTel.setEditable(false);
                tfDatanascimento.setText("");
                tfDatanascimento.setEditable(false);
                tfEmail.setText("");
                tfEmail.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoPessoa.remover(pessoa);
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
                cp.remove(pnAt);
                List<Pessoa> listaPessoa = daoPessoa.listInOrderId();
                String[] colunas = new String[]{"cpf", "nome", "tel", "datanascimento", "email"};
                String[][] dados = new String[listaPessoa.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPessoa.size(); i++) {
                    aux = listaPessoa.get(i).toString().split(";");
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
        }
        );

        btLocalizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DAOPessoa dao = new DAOPessoa();
                List<String> listaAuxiliar = dao.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = btLocalizar.getLocationOnScreen();
                    lc.x = lc.x + btLocalizar.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String aux = selectedItem.substring(0, 14);
                        tfCpf.setText(aux);
                        btBuscar.doClick();
                    } else {
                        tfNome.requestFocus();
                        tfNome.selectAll();
                    }
                }
                btAdicionar.setVisible(false);
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
                tfCpf.setText("");
                tfCpf.requestFocus();
                tfCpf.setEnabled(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfTel.setText("");
                tfTel.setEditable(false);
                tfDatanascimento.setText("");
                tfDatanascimento.setEditable(false);
                tfEmail.setText("");
                tfEmail.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfDataCadastro.setText("");
                tfNome.setText("");
                tfTel.setText("");
                tfDatanascimento.setText("");
                tfEmail.setText("");
                tfCargo.setText("");
                tfSalario.setText("");
                tfDataInicio.setText("");
                tfDataDemissao.setText("");
                tfTelFuncionario.setText("");
                cbNotificacao.setSelected(false);

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
        btCadatrarCliente.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfCpf.setEnabled(false);
                cbNotificacao.setEnabled(true);
                tfDataCadastro.setEnabled(true);
                tfDataCadastro.setEditable(true);
                acao = "adicionar";
            }
        }
        );
        btCadatrarFuncionario.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfCpf.setEnabled(false);
                tfCargo.requestFocus();
                tfCargo.setEnabled(true);
                tfSalario.setEnabled(true);
                tfDataInicio.setEnabled(true);
                tfDataDemissao.setEnabled(true);
                tfDataInicio.setEditable(true);
                tfDataDemissao.setEditable(true);
                tfTelFuncionario.setEditable(true);
                tfTelFuncionario.setEnabled(true);
                tfCargo.setEditable(true);
                btSalvar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        }
        );
        setModal(
                true);
        telFormat.install(
                (JFormattedTextField) tfTel);
        cpfFormat.install(
                (JFormattedTextField) tfCpf);
        pack();

        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);
    }//fim do contrutor de GUI
} //fim da classe

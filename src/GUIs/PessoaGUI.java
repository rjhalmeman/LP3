package GUIs;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import Main.CaixaDeFerramentas;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import myUtil.CentroDoMonitorMaior;

/**
 *
 * @author radames
 */
public class PessoaGUI extends JDialog { //variáreis globais

    //carregar imagens dos icones
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    DAOPessoa daoPessoa = new DAOPessoa();
    Pessoa pessoa = new Pessoa();
    JLabel lbCpfPessoa = new JLabel("CpfPessoa");
    JTextField tfCpfPessoa = new JTextField(20);
    JLabel lbNomePessoa = new JLabel("NomePessoa");
    JTextField tfNomePessoa = new JTextField(60);
    JLabel lbDataNascimentoPessoa = new JLabel("DataNascimentoPessoa");
    JTextField tfDataNascimentoPessoa = new JTextField(10);
    JLabel lbEnderecoIdEndereco = new JLabel("EnderecoIdEndereco");
    JTextField tfEnderecoIdEndereco = new JTextField(10);
    JLabel lbAviso = new JLabel("");

    JButton btBuscar = new JButton(iconeRetrieve);
    JButton btAdicionar = new JButton(iconeCreate);
    JButton btSalvar = new JButton(iconeSave);
    JButton btAlterar = new JButton(iconeUpdate);
    JButton btExcluir = new JButton(iconeDelete);
    JButton btListar = new JButton(iconeListar);
    JButton btCancelar = new JButton(iconeCancel);

    String acao;

    CaixaDeFerramentas cf = new CaixaDeFerramentas();
    JToolBar jToolbar = new JToolBar();

    public PessoaGUI() {

        //componentes visuais
        setTitle("CRUD Pessoa");
        cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.DARK_GRAY);

        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));
        pnNorte.add(jToolbar);
        jToolbar.add(lbCpfPessoa);
        jToolbar.add(tfCpfPessoa);
        jToolbar.add(btBuscar);
        jToolbar.add(btAdicionar);
        jToolbar.add(btAlterar);
        jToolbar.add(btExcluir);
        jToolbar.add(btListar);
        jToolbar.add(btSalvar);
        jToolbar.add(btCancelar);

        btBuscar.setToolTipText("Buscar");
        btAdicionar.setToolTipText("Adicionar novo registro");
        btAlterar.setToolTipText("Alterar um registro");
        btExcluir.setToolTipText("Excluir um registro");
        btListar.setToolTipText("Listagem");
        btSalvar.setToolTipText("Salvar dados do registro");
        btCancelar.setToolTipText("Cancelar edição (sair sem salvar)");
        pnCentro.setLayout(new GridLayout(4, 2));
        pnCentro.add(lbNomePessoa);
        pnCentro.add(tfNomePessoa);
        pnCentro.add(lbDataNascimentoPessoa);
        pnCentro.add(tfDataNascimentoPessoa);
        pnCentro.add(lbEnderecoIdEndereco);
        pnCentro.add(tfEnderecoIdEndereco);
        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfCpfPessoa.setEditable(true);
        tfNomePessoa.setEditable(false);
        tfDataNascimentoPessoa.setEditable(false);
        tfEnderecoIdEndereco.setEditable(false);
        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);
//Listeners .............................................................
        tfCpfPessoa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um CpfPessoa");
                tfCpfPessoa.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfCpfPessoa.setBackground(Color.white);
            }
        });
        ////////////    buscar      ////////////

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfCpfPessoa.getText().isEmpty()) {
                    tfCpfPessoa.requestFocus();
                } else if (tfCpfPessoa.getText().length() > tfCpfPessoa.getColumns()) {
                    tfCpfPessoa.requestFocus();
                    tfCpfPessoa.selectAll();
                    JOptionPane.showMessageDialog(cp, "Excede a quantidade máxima de caracteres. Máximo = " + tfCpfPessoa.getColumns());
                } else {
                    pessoa = daoPessoa.obter(tfCpfPessoa.getText(), "CpfPessoa");

                    if (pessoa == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfNomePessoa.setText("");
                        tfDataNascimentoPessoa.setText("");
                        tfEnderecoIdEndereco.setText("");
                    } else {//encontra na lista
                        tfCpfPessoa.setText(pessoa.getCpfPessoa());
                        tfNomePessoa.setText(pessoa.getNomePessoa());
                        tfDataNascimentoPessoa.setText(cf.converteDeDateParaString(pessoa.getDataNascimentoPessoa()));
                        tfEnderecoIdEndereco.setText(String.valueOf(pessoa.getEnderecoIdEndereco()));
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btListar.setVisible(false);
                        lbAviso.setText("Encontrou o registro");

                        //ajustar o combobox
                    }
                }
            }
        });
        ////////////    adicionar      ////////////

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfCpfPessoa.setEditable(false);
                tfNomePessoa.setEditable(true);
                tfNomePessoa.requestFocus();
                tfNomePessoa.setText("");
                tfNomePessoa.setEditable(true);
                tfDataNascimentoPessoa.setText("");
                tfDataNascimentoPessoa.setEditable(true);
                tfEnderecoIdEndereco.setText("");
                tfEnderecoIdEndereco.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionando";
            }
        });
        ////////////    alterar      ////////////

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfCpfPessoa.setEditable(false);
                tfEnderecoIdEndereco.setEditable(true);
                tfEnderecoIdEndereco.requestFocus();
                tfNomePessoa.setEditable(true);
                tfDataNascimentoPessoa.setEditable(true);
                tfEnderecoIdEndereco.setEditable(true);
                btAlterar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btExcluir.setVisible(false);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "alterando";
                lbAviso.setText("Alterando o registro");
            }
        });
        ////////////    salvar      ////////////

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuErro = false;
                if (acao.equals("adicionando")) {
                    pessoa = new Pessoa();
                }
                try {

                    if (tfCpfPessoa.getText().length() > tfCpfPessoa.getColumns()) {
                        int x = 3 / 0;//vai causar um erro
                    }
                    pessoa.setCpfPessoa(tfCpfPessoa.getText());
                } catch (Exception e) {
                    tfCpfPessoa.setBackground(Color.red);
                    deuErro = true;
                }
                try {

                    if (tfNomePessoa.getText().length() > tfNomePessoa.getColumns()) {
                        int x = 3 / 0;//vai causar um erro
                    }
                    pessoa.setNomePessoa(tfNomePessoa.getText());
                } catch (Exception e) {
                    tfNomePessoa.setBackground(Color.red);
                    deuErro = true;
                }
                try {
                    Date dt = cf.converteDeStringParaDate(tfDataNascimentoPessoa.getText());
                    if (dt != null) {
                        pessoa.setDataNascimentoPessoa(dt);
                    } else {
                        int x = 3/0;//vai forçar um erro
                    }

                } catch (Exception e) {
                    tfDataNascimentoPessoa.setBackground(Color.red);
                    deuErro = true;
                }
                try {
                    pessoa.setEnderecoIdEndereco(Integer.valueOf(tfEnderecoIdEndereco.getText()));
                } catch (Exception e) {
                    tfEnderecoIdEndereco.setBackground(Color.red);
                    deuErro = true;
                }

                if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoPessoa.inserir(pessoa);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        daoPessoa.atualizar(pessoa, "cpfPessoa", pessoa.getCpfPessoa());
                        lbAviso.setText("Alterou o registro");
                    }
                    tfCpfPessoa.requestFocus();
                    tfCpfPessoa.setText("");
                    tfCpfPessoa.setEditable(true);
                    tfCpfPessoa.setBackground(Color.white);
                    tfNomePessoa.setText("");
                    tfNomePessoa.setEditable(false);
                    tfNomePessoa.setBackground(Color.white);
                    tfDataNascimentoPessoa.setText("");
                    tfDataNascimentoPessoa.setEditable(false);
                    tfDataNascimentoPessoa.setBackground(Color.white);
                    tfEnderecoIdEndereco.setText("");
                    tfEnderecoIdEndereco.setEditable(false);
                    tfEnderecoIdEndereco.setBackground(Color.white);
                    btBuscar.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btListar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(cp, "Erro nos dados. É necessário corrigir");
                }
            }
        });
        ////////////    cancelar      ////////////

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfCpfPessoa.requestFocus();
                tfCpfPessoa.setText("");
                tfCpfPessoa.setEditable(true);
                tfCpfPessoa.setBackground(Color.white);
                tfNomePessoa.setText("");
                tfNomePessoa.setEditable(false);
                tfNomePessoa.setBackground(Color.white);
                tfDataNascimentoPessoa.setText("");
                tfDataNascimentoPessoa.setEditable(false);
                tfDataNascimentoPessoa.setBackground(Color.white);
                tfEnderecoIdEndereco.setText("");
                tfEnderecoIdEndereco.setEditable(false);
                tfEnderecoIdEndereco.setBackground(Color.white);
                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                lbAviso.setText("");
            }
        });

        ////////////    excluir      ////////////
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.
                        showConfirmDialog(cp, "Confirma a exclusão?", "Excluindo", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                if (opcao == JOptionPane.YES_NO_OPTION) {
                    daoPessoa.excluir(pessoa.getCpfPessoa(), "cpfPessoa");
                }
                tfCpfPessoa.setText("");
                tfNomePessoa.setText("");
                tfNomePessoa.setEditable(false);
                tfDataNascimentoPessoa.setText("");
                tfDataNascimentoPessoa.setEditable(false);
                tfEnderecoIdEndereco.setText("");
                tfEnderecoIdEndereco.setEditable(false);
                tfCpfPessoa.requestFocus();
                tfCpfPessoa.setText("");
                tfCpfPessoa.setEditable(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                lbAviso.setText("");
            }
        });
        ////////////    listar      ////////////

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbAviso.setText("Relatório");
                Point coordenadas = getLocation();//pega as coordenadas da guiPai
                Dimension dimensao = getSize();
                String idSelecionado
                        = new PessoaGUIListar(daoPessoa, coordenadas, dimensao).getIdSelecionado();
                tfCpfPessoa.setText(idSelecionado);
                btBuscar.doClick();
            }
        });
        ////////////    ao fechar a GUI      ////////////

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        ////////////    finalizando      ////////////

        setSize(800, 200);
        // pack();
        setLocation(new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        PessoaGUI pessoaGUI = new PessoaGUI();
    }
} //fim da classe

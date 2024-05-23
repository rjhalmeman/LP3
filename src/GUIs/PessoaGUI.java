package GUIs;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import Main.CaixaDeFerramentas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import javax.swing.JToolBar;


/**
 *
 * @author radames
 */
public class PessoaGUI extends JFrame {

    //variáreis globais
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
    
    
    JLabel lbCpfPessoa = new JLabel("Cpf");
    JTextField tfCpfPessoa = new JTextField(15);



    DAOPessoa daoPessoa = new DAOPessoa();
    Pessoa pessoa = new Pessoa();
    JLabel lbAviso = new JLabel("xxxx");

    JLabel lbNomePessoa = new JLabel("Nome");
    JTextField tfNomePessoa = new JTextField(40);
    JLabel lbDataNascimentoPessoa = new JLabel("Data de nascimento");
    JTextField tfDataNascimentoPessoa = new JTextField(20);
    JLabel lbEndereco_idEndereco = new JLabel("Endereço");
    JTextField tfEndereco_idEndereco = new JTextField(10);
    
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
        setTitle("CRUD Pessoa - acesso direto ao BD - 2024");
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

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNomePessoa);
        pnCentro.add(tfNomePessoa);
        pnCentro.add(lbDataNascimentoPessoa);
        pnCentro.add(tfDataNascimentoPessoa);
        pnCentro.add(lbEndereco_idEndereco);
        pnCentro.add(tfEndereco_idEndereco);

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
        tfEndereco_idEndereco.setEditable(false);
        tfDataNascimentoPessoa.setEditable(false);

        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);

        //listeners
        tfCpfPessoa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um Cpf");
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

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfCpfPessoa.getText().isEmpty()) {
                    tfCpfPessoa.requestFocus();
                } else {
                    pessoa = daoPessoa.obter(tfCpfPessoa.getText(),"cpfPessoa");
                    //daoPessoa.obter("222","cpfPessoa");
                    if (pessoa == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfNomePessoa.setText("");
                        tfEndereco_idEndereco.setText("");
                        tfDataNascimentoPessoa.setText("");
                    } else {//encontra na lista
                        tfCpfPessoa.setText(String.valueOf(pessoa.getCpfPessoa()));
                        tfNomePessoa.setText(pessoa.getNomePessoa());
                        tfEndereco_idEndereco.setText(String.valueOf(pessoa.getEndereco_idEndereco()));
                        tfDataNascimentoPessoa.setText(cf.converteDeDateParaString(pessoa.getDataNascimentoPessoa()));
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btListar.setVisible(false);
                        lbAviso.setText("Encontrou o registro");
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                tfCpfPessoa.setEditable(false);
                tfNomePessoa.setEditable(true);
                tfEndereco_idEndereco.setEditable(true);
                tfDataNascimentoPessoa.setEditable(true);

                tfNomePessoa.requestFocus();
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionando";
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfNomePessoa.requestFocus();
                tfCpfPessoa.setEditable(false);
                tfNomePessoa.setEditable(true);
                tfEndereco_idEndereco.setEditable(true);
                tfDataNascimentoPessoa.setEditable(true);
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

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuErro = false;
                
                if (acao.equals("adicionando")) {
                    pessoa = new Pessoa();
                }

                pessoa.setCpfPessoa(tfCpfPessoa.getText());
                pessoa.setNomePessoa(tfNomePessoa.getText());
                try {
                    pessoa.setEndereco_idEndereco(Integer.parseInt(tfEndereco_idEndereco.getText()));

                } catch (NumberFormatException e) {
                    tfEndereco_idEndereco.setBackground(Color.yellow);
                    deuErro = true;
                }
                Date dt = cf.converteDeStringParaDate(tfDataNascimentoPessoa.getText());
                if (dt != null) {
                    pessoa.setDataNascimentoPessoa(dt);

                } else {
                    tfDataNascimentoPessoa.setBackground(Color.yellow);
                    deuErro = true;
                }

                if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoPessoa.inserir(pessoa);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        daoPessoa.atualizar(pessoa,"cpfPessoa",pessoa.getCpfPessoa());
                        lbAviso.setText("Alterou o registro");
                    }

                    tfEndereco_idEndereco.setBackground(Color.white);
                    tfDataNascimentoPessoa.setBackground(Color.white);

                    tfCpfPessoa.setText("");
                    tfNomePessoa.setText("");
                    tfEndereco_idEndereco.setText("");
                    tfDataNascimentoPessoa.setText("");
                    tfCpfPessoa.requestFocus();
                    tfCpfPessoa.setEditable(true);
                    tfNomePessoa.setEditable(false);
                    tfEndereco_idEndereco.setEditable(false);
                    tfDataNascimentoPessoa.setEditable(false);

                    btBuscar.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btListar.setVisible(true);
                }
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfCpfPessoa.setText("");
                tfNomePessoa.setText("");
                tfEndereco_idEndereco.setText("");
                tfDataNascimentoPessoa.setText("");
                tfCpfPessoa.requestFocus();
                tfCpfPessoa.setEditable(true);
                tfNomePessoa.setEditable(false);
                tfEndereco_idEndereco.setEditable(false);
                tfDataNascimentoPessoa.setEditable(false);

                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                lbAviso.setText("");
            }
        });

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
                tfEndereco_idEndereco.setText("");
                tfDataNascimentoPessoa.setText("");
                tfCpfPessoa.requestFocus();
                tfCpfPessoa.setEditable(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                lbAviso.setText("");
            }
        });
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

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                dispose();

            }
        });

        //setSize(800, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

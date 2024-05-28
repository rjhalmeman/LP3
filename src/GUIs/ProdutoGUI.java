package GUIs;

import DAOs.DAOProduto;
import DAOs.DAOUnidadeDeMedida;
import Entidades.Produto;
import Entidades.UnidadeDeMedida;
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
import javax.swing.JDialog;
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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import myUtil.CentroDoMonitorMaior;

/**
 *
 * @author radames
 */
public class ProdutoGUI extends JDialog {

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

    JLabel lbIdProduto = new JLabel("Id");
    JTextField tfIdProduto = new JTextField(15);

    DAOProduto daoProduto = new DAOProduto();
    Produto produto = new Produto();
    JLabel lbAviso = new JLabel("xxxx");

    JLabel lbNomeProduto = new JLabel("Nome");
    JTextField tfNomeProduto = new JTextField(40);

    JLabel lbSiglaUnidadeDeMedida = new JLabel("Sigla da Unidade de Medida");
//    JTextField tfSiglaUnidadeDeMedida = new JTextField(20);
    JComboBox comboSiglaUnidadeDeMedida = new JComboBox();

    JLabel lbQuantidadeEmEstoque = new JLabel("Quantidade em estoque");
    JTextField tfQuantidadeEmEstoque = new JTextField(10);

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

    DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
    List<String> listaUnidadeDeMedida = daoUnidadeDeMedida.listaParaCombobox();
    DefaultComboBoxModel<String> comboBoxModel;

    public ProdutoGUI() {

        //componentes visuais
        setTitle("CRUD Produto - acesso direto ao BD - 2024");
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
        jToolbar.add(lbIdProduto);
        jToolbar.add(tfIdProduto);
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
        pnCentro.add(lbNomeProduto);
        pnCentro.add(tfNomeProduto);
        pnCentro.add(lbSiglaUnidadeDeMedida);
        pnCentro.add(comboSiglaUnidadeDeMedida);
        pnCentro.add(lbQuantidadeEmEstoque);
        pnCentro.add(tfQuantidadeEmEstoque);

        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfIdProduto.setEditable(true);
        tfNomeProduto.setEditable(false);
        tfQuantidadeEmEstoque.setEditable(false);
        comboSiglaUnidadeDeMedida.setEditable(false);
        comboSiglaUnidadeDeMedida.setEnabled(false);

        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);

        //listeners
        tfIdProduto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um Id");
                tfIdProduto.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfIdProduto.setBackground(Color.white);
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                comboSiglaUnidadeDeMedida.setEditable(false);
                comboSiglaUnidadeDeMedida.setEnabled(false);

                listaUnidadeDeMedida = daoUnidadeDeMedida.listarComoStrings();
                comboSiglaUnidadeDeMedida.addItem(daoUnidadeDeMedida);

                List<String> listaUnidadeDeMedida = daoUnidadeDeMedida.listaParaCombobox();
                comboBoxModel = new DefaultComboBoxModel<>(listaUnidadeDeMedida.toArray(new String[0]));
                comboSiglaUnidadeDeMedida.setModel(comboBoxModel);

                if (tfIdProduto.getText().isEmpty()) {
                    tfIdProduto.requestFocus();
                } else {
                    produto = daoProduto.obter(tfIdProduto.getText(), "idProduto");
                    //daoProduto.obter("222","idProduto");
                    if (produto == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfNomeProduto.setText("");
                        tfQuantidadeEmEstoque.setText("");
                        comboSiglaUnidadeDeMedida.setSelectedIndex(0);

                    } else {//encontra na lista
                        tfIdProduto.setText(String.valueOf(produto.getIdProduto()));
                        tfNomeProduto.setText(produto.getNomeProduto());
                        tfQuantidadeEmEstoque.setText(String.valueOf(produto.getQuantidadeEmEstoque()));
                        UnidadeDeMedida UM = daoUnidadeDeMedida.obter(produto.getUnidadeDeMedidaSiglaUnidadeDeMedida(), "siglaUnidadeDeMedida");

                        comboBoxModel.setSelectedItem(UM.getSiglaUnidadeDeMedida() + " - " + UM.getNomeUnidadeDeMedida());
                        // System.out.println("um -> " + produto.getUnidadeDeMedidaSiglaUnidadeDeMedida());
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

                tfIdProduto.setEditable(false);
                tfNomeProduto.setEditable(true);
                tfQuantidadeEmEstoque.setEditable(true);
                comboSiglaUnidadeDeMedida.setEditable(true);
                comboSiglaUnidadeDeMedida.setEnabled(true);

                tfNomeProduto.requestFocus();
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
                tfNomeProduto.requestFocus();
                tfIdProduto.setEditable(false);
                tfNomeProduto.setEditable(true);
                tfQuantidadeEmEstoque.setEditable(true);
                comboSiglaUnidadeDeMedida.setEditable(true);
                comboSiglaUnidadeDeMedida.setEnabled(true);
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
                    produto = new Produto();
                }

                produto.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                produto.setNomeProduto(tfNomeProduto.getText());
                try {
                    produto.setQuantidadeEmEstoque(Integer.parseInt(tfQuantidadeEmEstoque.getText()));

                } catch (NumberFormatException e) {
                    tfQuantidadeEmEstoque.setBackground(Color.yellow);
                    deuErro = true;
                }

                DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
                UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
                String ss = String.valueOf(comboSiglaUnidadeDeMedida.getSelectedItem());
                String idUM = ss.split("-")[0];
                unidadeDeMedida = daoUnidadeDeMedida.obter(idUM, "siglaUnidadeDeMedida");
                if (unidadeDeMedida != null) {
                    produto.setUnidadeDeMedidaSiglaUnidadeDeMedida(idUM); ///ajustar 
                    comboSiglaUnidadeDeMedida.setBackground(Color.white);
                } else {
                    comboSiglaUnidadeDeMedida.setBackground(Color.red);
                    JOptionPane.showMessageDialog(cp, ss+"\n Chave estrangeira não encontrada");
                    deuErro = true;
                }

                if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoProduto.inserir(produto);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        String status = daoProduto.atualizar(produto, "idProduto", produto.getIdProduto());
                        if (status.equals("OK")) {
                            lbAviso.setText("Alterou o registro");
                        } else {
                            lbAviso.setText(status);
                            JOptionPane.showMessageDialog(cp, status);
                        }
                    }

                    tfQuantidadeEmEstoque.setBackground(Color.white);
                    comboSiglaUnidadeDeMedida.setBackground(Color.white);

                    tfIdProduto.setText("");
                    tfNomeProduto.setText("");
                    tfQuantidadeEmEstoque.setText("");
                    comboSiglaUnidadeDeMedida.setSelectedIndex(0);
                    tfIdProduto.requestFocus();
                    tfIdProduto.setEditable(true);
                    tfNomeProduto.setEditable(false);
                    tfQuantidadeEmEstoque.setEditable(false);
                    comboSiglaUnidadeDeMedida.setEditable(false);
                    comboSiglaUnidadeDeMedida.setEnabled(false);

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
                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfQuantidadeEmEstoque.setText("");
                comboSiglaUnidadeDeMedida.setSelectedIndex(0);
                tfIdProduto.requestFocus();
                tfIdProduto.setEditable(true);
                tfNomeProduto.setEditable(false);
                tfQuantidadeEmEstoque.setEditable(false);
                comboSiglaUnidadeDeMedida.setEditable(false);
                comboSiglaUnidadeDeMedida.setEnabled(false);

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
                    daoProduto.excluir(produto.getIdProduto(), "idProduto");
                }
                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfQuantidadeEmEstoque.setText("");
                comboSiglaUnidadeDeMedida.setSelectedIndex(0);
                tfIdProduto.requestFocus();
                tfIdProduto.setEditable(true);
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
                        = new ProdutoGUIListar(daoProduto, coordenadas, dimensao).getIdSelecionado();
                tfIdProduto.setText(idSelecionado);
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

        setSize(800, 300);
        //pack();
         setLocation(new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        ProdutoGUI produtoGUI = new ProdutoGUI();
    }

}

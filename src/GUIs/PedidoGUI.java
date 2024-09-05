package GUIs;

import DAOs.DAOPedido;
import Entidades.Pedido;
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
public class PedidoGUI extends JDialog { //variáreis globais

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
    JPanel pnCentro = new JPanel(new BorderLayout());
    JPanel pnCentroPedido = new JPanel();
    JPanel pnCentroItensDoPedido = new JPanel(new GridLayout(1,1));
    JPanel pnCentroTotalizacaoDoPedido = new JPanel(new GridLayout(1,1));
    
    JPanel pnSul = new JPanel();

    JLabel lbIdPedido = new JLabel("IdPedido");
    JTextField tfIdPedido = new JTextField(20);
    JLabel lbClientePessoaCpfPessoa = new JLabel("Cpf do cliente");
    JTextField tfClientePessoaCpfPessoa = new JTextField(60);
    JLabel lbDataDoPedido = new JLabel("Data do Pedido");
    JTextField tfDataNascimentoPedido = new JTextField(10);
    
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

    DAOPedido daoPedido = new DAOPedido();
    Pedido pedido = new Pedido();

    public PedidoGUI() {

        //componentes visuais
        setTitle("CRUD Pedido");
        cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.LIGHT_GRAY);
        
        pnSul.setBackground(Color.DARK_GRAY);

        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));
        pnNorte.add(jToolbar);
        jToolbar.add(lbIdPedido);
        jToolbar.add(tfIdPedido);
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
        
        
        pnCentro.setBackground(Color.blue);
        pnCentro.add(pnCentroPedido, BorderLayout.NORTH);
        
        pnCentroPedido.setLayout(new GridLayout(4, 2));
        pnCentroPedido.add(lbClientePessoaCpfPessoa);
        pnCentroPedido.add(tfClientePessoaCpfPessoa);
        pnCentroPedido.add(lbDataDoPedido);
        pnCentroPedido.add(tfDataNascimentoPedido);
       
        
        pnCentroItensDoPedido = new PedidoItensDoPedidoPainelGUI(1);
          
        pnCentro.add(pnCentroItensDoPedido,BorderLayout.CENTER);
               
        pnCentro.add(pnCentroTotalizacaoDoPedido,BorderLayout.SOUTH);
        pnCentroTotalizacaoDoPedido.setBackground(Color.cyan);
        pnCentroTotalizacaoDoPedido.add(new JLabel("totais"));
        
        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfIdPedido.setEditable(true);
        tfClientePessoaCpfPessoa.setEditable(false);
        tfDataNascimentoPedido.setEditable(false);
        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);
//Listeners .............................................................
        tfIdPedido.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um IdPedido");
                tfIdPedido.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfIdPedido.setBackground(Color.white);
            }
        });
        ////////////    buscar      ////////////

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfIdPedido.getText().isEmpty()) {
                    tfIdPedido.requestFocus();
                } else if (tfIdPedido.getText().length() > tfIdPedido.getColumns()) {
                    tfIdPedido.requestFocus();
                    tfIdPedido.selectAll();
                    JOptionPane.showMessageDialog(cp, "Excede a quantidade máxima de caracteres. Máximo = " + tfIdPedido.getColumns());
                } else {
                    pedido = daoPedido.obter(tfIdPedido.getText(), "IdPedido");

                    if (pedido == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfClientePessoaCpfPessoa.setText("");
                        tfDataNascimentoPedido.setText("");
                    } else {//encontra na lista
                        tfIdPedido.setText(String.valueOf(pedido.getIdPedido()));
                        tfClientePessoaCpfPessoa.setText(pedido.getClientePessoaCpfPessoa());
                        tfDataNascimentoPedido.setText(cf.converteDeDateParaString(pedido.getDataDoPedido()));
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
                tfIdPedido.setEditable(false);
                tfClientePessoaCpfPessoa.setEditable(true);
                tfClientePessoaCpfPessoa.requestFocus();
                tfClientePessoaCpfPessoa.setText("");
                tfClientePessoaCpfPessoa.setEditable(true);
                tfDataNascimentoPedido.setText("");
                tfDataNascimentoPedido.setEditable(true);
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
                tfIdPedido.setEditable(false);
                tfClientePessoaCpfPessoa.setEditable(true);
                tfDataNascimentoPedido.setEditable(true);
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
                    pedido = new Pedido();
                }
                try {

                    if (tfIdPedido.getText().length() > tfIdPedido.getColumns()) {
                        int x = 3 / 0;//vai causar um erro
                    }
                    pedido.setIdPedido(Integer.valueOf(tfIdPedido.getText()));
                } catch (Exception e) {
                    tfIdPedido.setBackground(Color.red);
                    deuErro = true;
                }
                try {

                    if (tfClientePessoaCpfPessoa.getText().length() > tfClientePessoaCpfPessoa.getColumns()) {
                        int x = 3 / 0;//vai causar um erro
                    }
                    pedido.setClientePessoaCpfPessoa(tfClientePessoaCpfPessoa.getText());
                } catch (Exception e) {
                    tfClientePessoaCpfPessoa.setBackground(Color.red);
                    deuErro = true;
                }
                try {
                    Date dt = cf.converteDeStringParaDate(tfDataNascimentoPedido.getText());
                    if (dt != null) {
                        pedido.setDataDoPedido(dt);
                    } else {
                        int x = 3 / 0;//vai forçar um erro
                    }

                } catch (Exception e) {
                    tfDataNascimentoPedido.setBackground(Color.red);
                    deuErro = true;
                }
               

                if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoPedido.inserir(pedido);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        daoPedido.atualizar(pedido, "cpfPedido", pedido.getIdPedido());
                        lbAviso.setText("Alterou o registro");
                    }
                    tfIdPedido.requestFocus();
                    tfIdPedido.setText("");
                    tfIdPedido.setEditable(true);
                    tfIdPedido.setBackground(Color.white);
                    tfClientePessoaCpfPessoa.setText("");
                    tfClientePessoaCpfPessoa.setEditable(false);
                    tfClientePessoaCpfPessoa.setBackground(Color.white);
                    tfDataNascimentoPedido.setText("");
                    tfDataNascimentoPedido.setEditable(false);
                    tfDataNascimentoPedido.setBackground(Color.white);
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
                tfIdPedido.requestFocus();
                tfIdPedido.setText("");
                tfIdPedido.setEditable(true);
                tfIdPedido.setBackground(Color.white);
                tfClientePessoaCpfPessoa.setText("");
                tfClientePessoaCpfPessoa.setEditable(false);
                tfClientePessoaCpfPessoa.setBackground(Color.white);
                tfDataNascimentoPedido.setText("");
                tfDataNascimentoPedido.setEditable(false);
                tfDataNascimentoPedido.setBackground(Color.white);
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
                    daoPedido.excluir(pedido.getIdPedido(), "cpfPedido");
                }
                tfIdPedido.setText("");
                tfClientePessoaCpfPessoa.setText("");
                tfClientePessoaCpfPessoa.setEditable(false);
                tfDataNascimentoPedido.setText("");
                tfDataNascimentoPedido.setEditable(false);
                tfIdPedido.requestFocus();
                tfIdPedido.setText("");
                tfIdPedido.setEditable(true);
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
                        = new PedidoGUIListar(daoPedido, coordenadas, dimensao).getIdSelecionado();
                tfIdPedido.setText(idSelecionado);
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

        setSize(800, 700);
        // pack();
        setLocation(new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        PedidoGUI pedidoGUI = new PedidoGUI();
    }
} //fim da classe

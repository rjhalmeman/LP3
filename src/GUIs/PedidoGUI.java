package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import Entidades.Pedido;
import DAOs.DAOPedido;
import DAOs.DAOPedidoHasProduto;
import DAOs.DAOPessoa;
import DAOs.DAOPrecoProduto;
import DAOs.DAOProduto;
import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import Entidades.Pedido_;
import Entidades.Pessoa;
import Entidades.PrecoProduto;
import Entidades.Produto;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import tools.CaixaDeFerramentas;
import tools.DateTextField;
import tools.JanelaPesquisar;

/**
 *
 * @author belly 23/06/2023 - 18:42:40
 */
public class PedidoGUI extends JFrame {

    Icon buscar = new ImageIcon("src/icones/retrieve.png");
    Icon add = new ImageIcon("src/icones/create.png");
    Icon salvar = new ImageIcon("src/icones/save.png");
    Icon alterar = new ImageIcon("src/icones/update.png");
    Icon excluir = new ImageIcon("src/icones/delete.png");
    Icon listar = new ImageIcon("src/icones/list.png");
    Icon fechar = new ImageIcon("src/icones/fechar.png");
    Icon cancel = new ImageIcon("src/icones/cancelar1.png");
    Icon loc = new ImageIcon("src/icones/localizar.png");
    Icon cursor = new ImageIcon("src/icones/cursor.png");

    Container cp;
    JPanel pnNorte = new JPanel(new BorderLayout());

    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton(buscar);
    JButton btAdicionar = new JButton(add);
    JButton btSalvar = new JButton(salvar);
    JButton btAlterar = new JButton(alterar);
    JButton btExcluir = new JButton(excluir);
    JButton btListar = new JButton(listar);
    JButton btFechar = new JButton(fechar);
    JButton btCancelar = new JButton(cancel);
    JButton btLocalizar = new JButton(loc);
    JButton btLocalizarCliente = new JButton(cursor);
    JButton btLocalizarFuncionario = new JButton(cursor);
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    JPanel pnN1 = new JPanel();
    JPanel pnN2 = new JPanel(new GridLayout(3, 3));

    private CardLayout cardLayout;
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    String[] coluna = new String[]{"ID Pedido", "Produto", "Quantidade", "Preço Unitário"};
    String[][] dado = new String[0][coluna.length];

    DefaultTableModel model = new DefaultTableModel(dado, coluna);
    JTable tabela = new JTable(model);

    private DefaultTableModel tableModel;
    private JTable table;

    private double calcularTotalDoPedido() {
        double total = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String quantidade = String.valueOf(tableModel.getValueAt(row, 1));
            String preco = String.valueOf(tableModel.getValueAt(row, 2));
            Double quant = Double.valueOf(quantidade);
            Double p = Double.valueOf(preco);
            total += quant * p;
        }
        return total;

    }

    private double buscarPrecoProduto() {
        int row = table.getSelectedRow();
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();

        String id = String.valueOf(table.getValueAt(row, 0));
        String[] aux4 = id.split("-");

        double preco = 0;
        List<PrecoProduto> listapreco = daoPrecoProduto.listInOrderNome();
        for (int i = 0; i < listapreco.size(); i++) {
            if (Integer.valueOf(aux4[0]) == listapreco.get(i).getPrecoProdutoPK().getProdutoIdProduto()) {
                preco = listapreco.get(i).getPreco();
            }
        }
        return preco;
    }

    private void calcularTotal() {
        double totalparcial = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String quantidade = String.valueOf(tableModel.getValueAt(row, 1));
            String preco = String.valueOf(tableModel.getValueAt(row, 2));
            int quant = Integer.parseInt(quantidade);
            double p = Double.valueOf(preco);
            totalparcial = quant * p;
            tableModel.setValueAt(totalparcial, row, 3);
        }
    }

//    public void imprimir(List<PedidoHasProduto> itensDoPedido, int id) {
//        Date date = new Date();
//        PedidoHasProdutoPK pedidoHasProdutoPK = new PedidoHasProdutoPK();
//        PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
//        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
//        Produto produto = new Produto();
//        DAOProduto daoProduto = new DAOProduto();
//
//        System.out.println("======== Nota Fiscal ========");
//        System.out.println("Data: " + date);
//        System.out.println("----------------------------");
//        int i = 0;
//        for (PedidoHasProduto item : itensDoPedido) {
//                pedidoHasProdutoPK.setPedidoIdPedido(id);
//                pedidoHasProdutoPK.setProdutoIdProduto(pedidoHasProdutoPK.getProdutoIdProduto());
//
//                pedidoHasProduto = daoPedidoHasProduto.obter(pedidoHasProdutoPK);
//                produto = daoProduto.obter(pedidoHasProdutoPK.getProdutoIdProduto());
//            System.out.println("oi");
//                System.out.println(produto.getNomeProduto() + " - Preço: " + item.getPrecoUnitario()
//                        + " - Quantidade: " + item.getQuantidade() + " - Total: " + (item.getPrecoUnitario() * item.getQuantidade()));
//            
//
//        }
//        double total = calcularTotalDoPedido();
//        System.out.println("----------------------------");
//        System.out.println("Total: " + total);
//        System.out.println("=============================");
//    }
//////////////////// - mutável - /////////////////////////
    JLabel lbIdPedido = new JLabel("ID:");
    JTextField tfIdPedido = new JTextField(10);
    JLabel lbCliente = new JLabel("Cliente:");
    JTextField tfCliente = new JTextField(10);
    JLabel lbFuncionario = new JLabel("Funcionário:");
    JTextField tfFuncionario = new JTextField(10);
    JLabel lbDataHoraPedido = new JLabel("Horário do Pedido:");
    DateTextField tfDataHoraPedido = new DateTextField();
    DAOPedido daoPedido = new DAOPedido();
    Pedido pedido = new Pedido();
    String[] colunas = new String[]{"idPedido", "dataHoraPedido"};
    String[][] dados = new String[0][colunas.length];

    JPanel pnSulItens = new JPanel(new GridLayout(2, 1));
    JPanel pnSulItensAtributos = new JPanel(new GridLayout(2, 3));
    //"ID Pedido", "Produto", "Quantidade", "Preço Unitário"
    JLabel lbIdProduto = new JLabel("Produto");
    JTextField tfIdProduto = new JTextField(5);
    JLabel lbQuantidade = new JLabel("Quantidade");
    JTextField tfQuantidade = new JTextField(5);
    JLabel lbPrecoUnitario = new JLabel("Preço Unitário");
    JTextField tfPrecoUnitario = new JTextField(5);

    JPanel pnSulItensBotoes = new JPanel(new GridLayout(1, 3));

    JButton btExcluirItem = new JButton(excluir);
    JButton addItemNoPedido = new JButton(add);
    JButton fecharPedidoButton = new JButton("Finalizar");

    DAOProduto daoProduto = new DAOProduto();
    Produto produto = new Produto();

    public PedidoGUI() {
        Dimension tamanhoPersonalizado = new Dimension(100, 30);
        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 22);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("PRODUTO");
        tableModel.addColumn("QUANTIDADE");
        tableModel.addColumn("PREÇO UNITÁRIO");
        tableModel.addColumn("TOTAL");
        table = new JTable(tableModel);

        pnCentro.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        pnCentro.add(scrollPane, BorderLayout.CENTER);

//        DefaultTableModel tableModel = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                // Todas as células são editáveis
//                return true;
//            }
//        };
        tfIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProd = Integer.valueOf(tfIdProduto.getText());
                Produto produto = new DAOProduto().obter(idProd);
                if (produto != null) {
                    tfIdProduto.setText(idProd + "-" + produto.getNomeProduto());
                    tfQuantidade.setText("1");
                    //buscar o preco atual na tabela preço produto
                    tfPrecoUnitario.setText("5.50");
                }
            }
        });

        addItemNoPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfIdPedido.getText().isBlank() && !tfIdProduto.getText().isBlank()) {

                    // Adiciona uma nova linha vazia à tabela
                    // tableModel.addRow(new Vector());
                    int idPedido = Integer.valueOf(tfIdPedido.getText());

                    String aux[] = tfIdProduto.getText().split("-");
                    //System.out.println("aux " + aux[0]);
                    int idProduto = Integer.valueOf(aux[0].trim());

                    int quantidade = Integer.valueOf(tfQuantidade.getText());;
                    double precoUnitario = Double.valueOf(tfPrecoUnitario.getText());
                    double total = quantidade * precoUnitario;

                    // Adiciona uma nova linha com os valores definidos
                    tableModel.addRow(new Object[]{tfIdProduto.getText(), quantidade, precoUnitario, total});
                } else {
                    System.out.println("preencha");
                }
            }
        });

        btExcluirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adiciona uma nova linha vazia à tabela
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                }

            }
        });

        List<Produto> listaProduto = daoProduto.listInOrderId();
        String aux;
        String[] produtos = new String[listaProduto.size()];
        for (int i = 0; i < listaProduto.size(); i++) {
            aux = listaProduto.get(i).toString2();
            produtos[i] = aux;
        }
        JComboBox jComboBox = new JComboBox(produtos);
        jComboBox.setPreferredSize(tamanhoPersonalizado);
        jComboBox.setFont(fontePersonalizada);

        // Crie um DefaultCellEditor personalizado para usar o JComboBox
        TableCellEditor produtoEditor = new DefaultCellEditor(jComboBox);
        table.getColumnModel().getColumn(0).setCellEditor(produtoEditor);

        SpinnerEditor spinnerEditor = new SpinnerEditor();
        table.getColumnModel().getColumn(1).setCellEditor(spinnerEditor);

        // Cria um botão para fechar o pedido
        fecharPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = calcularTotalDoPedido();
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 22)));
                int response = JOptionPane.showConfirmDialog(cp, "Deseja Fechar o Pedido? \n Total: R$ " + total, "Confirmação",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
                        PedidoHasProdutoPK pedidoHasProdutoPK = new PedidoHasProdutoPK();
                        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();

                        String id = String.valueOf(table.getValueAt(row, 0));
                        String quant = String.valueOf(table.getValueAt(row, 1));
                        String preco = String.valueOf(table.getValueAt(row, 2));
                        String[] aux3 = id.split("-");
                        pedidoHasProdutoPK.setPedidoIdPedido(Integer.valueOf(tfIdPedido.getText()));
                        pedidoHasProdutoPK.setProdutoIdProduto(Integer.valueOf(aux3[0]));
                        pedidoHasProduto.setPedidoHasProdutoPK(pedidoHasProdutoPK);
                        pedidoHasProduto.setPrecoUnitario(Double.valueOf(preco));
                        pedidoHasProduto.setQuantidade(Integer.valueOf(quant));
                        if (acao.equals("adicionar")) {
                            daoPedidoHasProduto.inserir(pedidoHasProduto);
                        } else {
                            daoPedidoHasProduto.atualizar(pedidoHasProduto);
                        }
                    }

                }

            }
        });

        // Crie um editor personalizado que não permite edição
        TableCellEditor nonEditableEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return null; // Retorna um componente nulo, impedindo a edição
            }
        };

        // Configure o editor personalizado para a coluna bloqueada (índice da coluna 2)
        table.getColumnModel().getColumn(2).setCellEditor(nonEditableEditor);
        table.getColumnModel().getColumn(3).setCellEditor(nonEditableEditor);

        // Cria um painel para o crud pedidoHasProduto     
        pnSulItensAtributos.add(lbIdProduto);
        pnSulItensAtributos.add(lbQuantidade);
        pnSulItensAtributos.add(lbPrecoUnitario);
        pnSulItensAtributos.add(tfIdProduto);
        pnSulItensAtributos.add(tfQuantidade);
        pnSulItensAtributos.add(tfPrecoUnitario);

        pnSulItens.add(pnSulItensAtributos);

        pnSulItensAtributos.setBackground(Color.red);

        pnSulItensBotoes.add(btExcluirItem);
        pnSulItensBotoes.add(addItemNoPedido);
        pnSulItensBotoes.add(fecharPedidoButton);
        pnSulItens.add(pnSulItensBotoes);
        pnCentro.add(pnSulItens, BorderLayout.SOUTH);

        pnCentro.setSize(500, 300);
        pnCentro.setVisible(true);

        pnCentro.setSize(400, 300);
        pnCentro.setVisible(true);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Pedido");
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        //cp.add(table, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnN1.setLayout(new FlowLayout(FlowLayout.LEFT));

        pnN1.add(lbIdPedido);
        pnN1.add(tfIdPedido);
        pnN1.add(btBuscar);
        pnN1.add(btAdicionar);
        pnN1.add(btAlterar);
        pnN1.add(btExcluir);
        pnN1.add(btListar);
        pnN1.add(btFechar);
        pnN1.add(btSalvar);
        pnN1.add(btCancelar);
        pnN1.setBackground(new Color(221, 184, 146));
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btFechar.setVisible(false);
        btCancelar.setVisible(false);
        pnN2.add(lbCliente);
        pnN2.add(tfCliente);
        pnN2.add(btLocalizarCliente);
        pnN2.add(lbFuncionario);
        pnN2.add(tfFuncionario);
        pnN2.add(btLocalizarFuncionario);
        pnN2.add(lbDataHoraPedido);
        pnN2.add(tfDataHoraPedido);
        pnNorte.add(pnN1, BorderLayout.NORTH);
        pnNorte.add(pnN2, BorderLayout.CENTER);

        lbIdPedido.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfIdPedido.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbIdPedido.setBorder(BorderFactory.createLineBorder(Color.black));
        tfIdPedido.setBorder(BorderFactory.createLineBorder(Color.black));
        lbDataHoraPedido.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDataHoraPedido.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbDataHoraPedido.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDataHoraPedido.setBorder(BorderFactory.createLineBorder(Color.black));
        lbCliente.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfCliente.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        tfCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        lbFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfFuncionario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbFuncionario.setBorder(BorderFactory.createLineBorder(Color.black));
        tfFuncionario.setBorder(BorderFactory.createLineBorder(Color.black));
        lbIdPedido.setBackground(new Color(221, 184, 146));
        lbIdPedido.setForeground(Color.BLACK);
        lbDataHoraPedido.setBackground(new Color(221, 184, 146));
        lbDataHoraPedido.setForeground(Color.BLACK);
        pnCentro.setBackground(new Color(221, 184, 146));
        fecharPedidoButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btSalvar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAlterar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btExcluir.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCancelar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        fecharPedidoButton.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAlterar.setBorder(BorderFactory.createLineBorder(Color.black));
        btExcluir.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btLocalizarCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        btLocalizarFuncionario.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBackground(Color.white);
        btCancelar.setBackground(Color.white);
        btAdicionar.setBackground(Color.white);
        btSalvar.setBackground(Color.white);
        btAlterar.setBackground(Color.white);
        btExcluir.setBackground(Color.white);
        btListar.setBackground(Color.white);
        btFechar.setBackground(Color.white);
        btLocalizarCliente.setBackground(Color.lightGray);
        btLocalizarFuncionario.setBackground(Color.lightGray);
        table.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        table.setRowHeight(40);

        tfDataHoraPedido.setText("");
        tfDataHoraPedido.setEditable(false);
        tfCliente.setEditable(false);
        tfFuncionario.setEditable(false);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);
        pnSul.add(pnListagem, "listagem");
        btLocalizarCliente.setEnabled(false);
        btLocalizarFuncionario.setEnabled(false);
        table.setEnabled(false);
// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = tableModel.getRowCount();
                int n = 0;
                while (x > 0) {
                    tableModel.removeRow(n);
                    x = tableModel.getRowCount();
                }
                DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
                PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
                PedidoHasProdutoPK pedidoHasProdutoPK = new PedidoHasProdutoPK();
                Produto produto1 = new Produto();
                DAOProduto daoProduto1 = new DAOProduto();
                try {
                    pedido = daoPedido.obter(Integer.valueOf(tfIdPedido.getText()));
                    if (pedido != null) {//achou o pedido na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfCliente.setText(String.valueOf(pedido.getClientePessoaCpfPessoa()));
                        tfFuncionario.setText(String.valueOf(pedido.getFuncionarioPessoaCpfPessoa1()));
                        tfDataHoraPedido.setText(new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDatahoraPedido()));
                        tfDataHoraPedido.setEditable(false);

                        List<PedidoHasProduto> itensDoPedido = daoPedidoHasProduto.list();
                        int i = 0;
                        for (PedidoHasProduto item : itensDoPedido) {
                            pedidoHasProdutoPK = item.getPedidoHasProdutoPK();
                            if (pedidoHasProdutoPK.getPedidoIdPedido() == Integer.valueOf(tfIdPedido.getText())) {
                                pedidoHasProdutoPK.setPedidoIdPedido(Integer.valueOf(tfIdPedido.getText()));
                                pedidoHasProdutoPK.setProdutoIdProduto(pedidoHasProdutoPK.getProdutoIdProduto());

                                pedidoHasProduto = daoPedidoHasProduto.obter(pedidoHasProdutoPK);
                                tableModel.addRow(new Vector());
                                produto1 = daoProduto1.obter(pedidoHasProdutoPK.getProdutoIdProduto());

                                tableModel.setValueAt((pedidoHasProdutoPK.getProdutoIdProduto() + "-" + produto1.getNomeProduto()), i, 0);
                                tableModel.setValueAt(pedidoHasProduto.getQuantidade(), i, 1);
                                tableModel.setValueAt(pedidoHasProduto.getPrecoUnitario(), i, 2);
                                calcularTotal();

                                i++;
                            }
                        }

                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfDataHoraPedido.setText("");
                        tfCliente.setText("");
                        tfFuncionario.setText("");
                        tfDataHoraPedido.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                    List<PedidoHasProduto> itensDoPedido = daoPedidoHasProduto.list();
                    for (PedidoHasProduto item : itensDoPedido) {
                        System.out.println(item);
                    }
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdPedido.setEnabled(false);
                tfDataHoraPedido.requestFocus();
                tfDataHoraPedido.setEditable(true);
                tfDataHoraPedido.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btLocalizarCliente.setEnabled(true);
                btLocalizarFuncionario.setEnabled(true);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOCliente daoCliente = new DAOCliente();
                DAOFuncionario daoFuncionario = new DAOFuncionario();
                PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
                DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
                try {
                    if (acao.equals("adicionar")) {
                        pedido = new Pedido();
                        pedidoHasProduto = new PedidoHasProduto();
                    }
                    pedido.setIdPedido(Integer.valueOf(tfIdPedido.getText()));
                    Date dataHoraAtual = new Date();

                    CaixaDeFerramentas cf = new CaixaDeFerramentas();
                    pedido.setDatahoraPedido(cf.converteDeStringParaDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataHoraAtual)));
                    pedido.setClientePessoaCpfPessoa((daoCliente.obter(String.valueOf(tfCliente.getText()))));
                    pedido.setFuncionarioPessoaCpfPessoa1((daoFuncionario.obter(String.valueOf(tfFuncionario.getText()))));

                    if (acao.equals("adicionar")) {
                        daoPedido.inserir(pedido);
                    } else {
                        daoPedido.atualizar(pedido);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdPedido.setEnabled(true);
                    tfIdPedido.setEditable(true);
                    tfIdPedido.requestFocus();
                    tfIdPedido.setText("");
                    tfDataHoraPedido.setEnabled(false);
                    tfDataHoraPedido.setEditable(false);
                    tfDataHoraPedido.setText("");
                    tfCliente.setText("");
                    tfFuncionario.setText("");
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Tente Novamente", "Erro Ao Salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdPedido.setEditable(false);
                tfDataHoraPedido.requestFocus();
                tfDataHoraPedido.setEditable(true);
                tfDataHoraPedido.setEnabled(true);
                btLocalizarCliente.setEnabled(true);
                btLocalizarFuncionario.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdPedido.setEnabled(true);
                btExcluir.setVisible(false);
                table.setEnabled(true);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdPedido.setEnabled(true);
                tfIdPedido.setEditable(true);
                tfIdPedido.requestFocus();
                tfIdPedido.setText("");
                tfCliente.setText("");
                tfFuncionario.setText("");
                tfDataHoraPedido.setText("");
                tfDataHoraPedido.setEditable(false);
                btAlterar.setVisible(false);
                int x = tableModel.getRowCount();
                int n = 0;
                while (x > 0) {
                    tableModel.removeRow(n);
                    x = tableModel.getRowCount();
                }
                if (response == JOptionPane.YES_OPTION) {
                    daoPedido.remover(pedido);
                }
            }
        });
        // listener Listar
        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<Pedido> listaPedido = daoPedido.listInOrderId();
                String[] colunas = new String[]{"ID Pedido", "Cliente", "Funcionário", "Data"};
                String[][] dados = new String[listaPedido.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPedido.size(); i++) {
                    aux = listaPedido.get(i).toString().split(";");
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

        // para calcular o total automaticamente
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                calcularTotal();
//                calcularTotal();
//                addButton.doClick();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_RIGHT) {
                    int row = table.getSelectedRow();
                    tableModel.setValueAt(buscarPrecoProduto(), row, 2);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        btLocalizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOCliente dao = new DAOCliente();
                DAOPessoa daoPessoa = new DAOPessoa();
                List<String> listaAuxiliar = dao.listInOrderNomeStrings("id");
                List<String> listaAuxiliar2 = new ArrayList<>();
                for (int i = 0; i < listaAuxiliar.size(); i++) {
                    Pessoa pessoa = new Pessoa();
                    pessoa = daoPessoa.obter(listaAuxiliar.get(i));
                    String nome = pessoa.getNomePessoa();
                    listaAuxiliar2.add((listaAuxiliar.get(i)) + ";" + nome);
                }

                if (listaAuxiliar.size() > 0) {
                    Point lc = btLocalizarCliente.getLocationOnScreen();
                    lc.x = lc.x + btLocalizarCliente.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar2,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String aux[] = selectedItem.split(";");
                        tfCliente.setText(aux[0]);
                    }
                }
                btAdicionar.setVisible(false);
            }
        });
        btLocalizarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOFuncionario dao = new DAOFuncionario();
                DAOPessoa daoPessoa = new DAOPessoa();
                List<String> listaAuxiliar = dao.listInOrderNomeStrings2("id");
                List<String> listaAuxiliar2 = new ArrayList<>();
                String nome;
                Pessoa pessoa;
                for (int i = 0; i < listaAuxiliar.size(); i++) {
                    pessoa = new Pessoa();
                    pessoa = daoPessoa.obter(listaAuxiliar.get(i));
                    listaAuxiliar2.add((listaAuxiliar.get(i)) + ";" + pessoa.getNomePessoa());
                }

                if (listaAuxiliar.size() > 0) {

                    Point lc = btLocalizarFuncionario.getLocationOnScreen();
                    lc.x = lc.x + btLocalizarFuncionario.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar2,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        pessoa = new Pessoa();
                        String aux[] = selectedItem.split(";");
                        tfFuncionario.setText(aux[0]);
                    }
                }
                btAdicionar.setVisible(false);
            }
        });
// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfIdPedido.setText("");
                tfIdPedido.requestFocus();
                tfIdPedido.setEnabled(true);
                tfIdPedido.setEditable(true);
                tfCliente.setText("");
                tfFuncionario.setText("");
                tfDataHoraPedido.setText("");
                tfDataHoraPedido.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                int x = tableModel.getRowCount();
                int n = 0;
                while (x > 0) {
                    tableModel.removeRow(n);
                    x = tableModel.getRowCount();
                }

            }
        });
        btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnListagem.remove(scrollTabela);
                pack();
                btFechar.setVisible(false);
            }
        });

//        setModal(true);
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }//fim do contrutor de GUI
// Classe para o editor personalizado com JSpinner

    private static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        private final JSpinner spinner;
        Dimension tamanhoPersonalizado = new Dimension(100, 30);
        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 24);

        public SpinnerEditor() {
            spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // (valorInicial, valorMinimo, valorMaximo, passo)
        }

        @Override
        public Object getCellEditorValue() {
            spinner.setPreferredSize(tamanhoPersonalizado);
            spinner.setFont(fontePersonalizada);
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setFont(fontePersonalizada);
            spinner.setPreferredSize(tamanhoPersonalizado);
            return spinner;
        }
    }

}//fim da classe


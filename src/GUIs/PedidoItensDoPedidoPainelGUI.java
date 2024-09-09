package GUIs;

import DAOs.DAOPedidoHasProduto;
import DAOs.DAOProduto;
import Entidades.PedidoHasProduto;
import Entidades.Produto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Classe para criar o painel do pedido com uma tabela de itens.
 *
 * @author radames
 */
public class PedidoItensDoPedidoPainelGUI extends JPanel {

    private int idPedido;

    private JTable table;
    private PedidoTableModel tableModel;

    JButton btAdicionarLinha = new JButton("Adicionar linha");
    JButton btTotalizar = new JButton("Totalizar");

    private void teclouIns() {
        //System.out.println("teclou ins");
        adicionarLinha(idPedido);
    }

    private void adicionarLinha(int idPedido) {
        // Cria um array para armazenar a nova linha
        Object[] novaLinha = new Object[5];

        // Preenche o campo idPedido automaticamente com o valor passado no construtor
        novaLinha[0] = idPedido;

        // Preenche os outros campos com valores padrão
        novaLinha[1] = ""; // Produto (pode ficar vazio para ser preenchido pelo usuário)
        novaLinha[2] = 1; // Quantidade (pode ser ajustada)
        novaLinha[3] = 0.0; // Preço Unitário (pode ser ajustado)
        novaLinha[4] = 0.0; // Subtotal (será calculado com base em quantidade * preço unitário)

        // Adiciona a nova linha ao modelo da tabela
        Object[][] dadosAtualizados = new Object[tableModel.getRowCount() + 1][5];

        // Copia os dados antigos para o novo array
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                dadosAtualizados[i][j] = tableModel.getValueAt(i, j);
            }
        }

        // Insere a nova linha no final
        dadosAtualizados[tableModel.getRowCount()] = novaLinha;

        // Atualiza o modelo da tabela com os novos dados
        tableModel.setDados(dadosAtualizados);
        tableModel.fireTableDataChanged();

        // Opcional: seleciona automaticamente a nova linha adicionada
        table.setRowSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
    }

    public PedidoItensDoPedidoPainelGUI(Integer idPedido) {

        this.idPedido = idPedido;

        JPanel pnTabela = new JPanel(new GridLayout(1, 1));
        JPanel pnControlesDaTabela = new JPanel(new GridLayout(1, 3));

        // Configurar layout
        setLayout(new BorderLayout());

        // Criar o modelo de tabela
        tableModel = new PedidoTableModel();

        // Criar a tabela
        table = new JTable(tableModel);

        // Tornar a coluna idPedido invisível
        TableColumn idPedidoColumn = table.getColumnModel().getColumn(0);
        idPedidoColumn.setMinWidth(0);
        idPedidoColumn.setMaxWidth(0);
        idPedidoColumn.setPreferredWidth(0);

        // Adicionar JComboBox como editor de célula para a coluna idProduto
        DAOProduto daoProduto = new DAOProduto();
        List<Produto> lp = daoProduto.listar();
        PedidoHasProduto phpAux = new PedidoHasProduto();

        String produtos[] = new String[lp.size()];
        int i = 0;
        for (Produto p : lp) {
            produtos[i] = p.getIdProduto() + "-" + p.getNomeProduto();
            i++;
        }
        //busca os dados na tabela has...
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        List<String> phpDeUmPedidoEspecifico = daoPedidoHasProduto.listarEmOrdemDePedidoProduto(String.valueOf(idPedido));

        //o tableModel precisa do dados em uma matriz
        Object[][] dados = new Object[phpDeUmPedidoEspecifico.size()][5]; // 5 é a quantidade de colunas da matriz

        i = 0;
        for (String string : phpDeUmPedidoEspecifico) {
            // System.out.println(string);
            String campos[] = string.split(",");// a string é um toString (ver na classe de entidade)
            String valor[];
            String ss = "";
            for (int j = 0; j < campos.length; j++) {
                valor = campos[j].split("=");
                ss += valor[1] + ",";
            }
            //System.out.println(ss);
            String aux[] = ss.split(",");
            phpAux.setQuantidade(Integer.valueOf(aux[0]));
            phpAux.setPrecoUnitarioProduto(Double.valueOf(aux[1]));
            phpAux.setProdutoIdProduto(Integer.valueOf(aux[2]));
            phpAux.setPedidoIdPedido(Integer.valueOf(aux[3]));

            dados[i][0] = phpAux.getPedidoIdPedido();
            dados[i][1] = phpAux.getProdutoIdProduto();
            dados[i][2] = phpAux.getQuantidade();
            dados[i][3] = phpAux.getPrecoUnitarioProduto();
            dados[i][4] = phpAux.getQuantidade() * phpAux.getPrecoUnitarioProduto();
            i++;
        }

        //System.exit(0);
        //  Object[][] dados = {
        //   {1, "Produto 3", 1, 3.0, 10.0},
        //      {1, "Produto 4", 2, 5.0, 30.0}
        //  };
        tableModel.setDados(dados);
        tableModel.fireTableDataChanged();

        JComboBox<String> comboBox = new JComboBox<>(produtos);
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));

        // Ajustar a largura das colunas quantidade, precoUnitario e subtotal
        ajustarLarguraColunas();

        // Adicionar formatação para precoUnitario e subtotal (duas casas decimais)
        DefaultTableCellRenderer decimalRenderer = new DefaultTableCellRenderer();
        decimalRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        //decimalRenderer.set(new DecimalFormat("#,##0.00"));

        table.getColumnModel().getColumn(3).setCellRenderer(decimalRenderer); // precoUnitario
        table.getColumnModel().getColumn(4).setCellRenderer(decimalRenderer); // subtotal

        // Adicionar os paineis
        pnTabela.add(new JScrollPane(table));

        add(pnTabela, BorderLayout.CENTER);
        add(pnControlesDaTabela, BorderLayout.SOUTH);
        pnControlesDaTabela.add(btAdicionarLinha);
        pnControlesDaTabela.add(btTotalizar);
        pnControlesDaTabela.setBackground(Color.red);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    // Detecta se foi uma atualização nos dados
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    // Se a coluna for -1, isso significa que uma linha inteira foi atualizada
                    if (column == TableModelEvent.ALL_COLUMNS) {
                        System.out.println("Linha " + row + " foi alterada.");
                    } else {
                        System.out.println("Célula alterada - Linha: " + row + ", Coluna: " + column);

                        //produto, quantidade, preco
                        List<Object> rowData = new ArrayList<>();

                        // Itera sobre todas as colunas da linha
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            Object value = tableModel.getValueAt(row, j);
                            rowData.add(value);  // Adiciona o valor da célula à lista
                            System.out.println("linha " + rowData);
                        }

                        PedidoHasProduto phpAux = new PedidoHasProduto();
                        phpAux.setPedidoIdPedido(Integer.valueOf(rowData.get(0) + ""));
                        String prod = String.valueOf(rowData.get(1));
                        phpAux.setProdutoIdProduto(Integer.valueOf(prod.split("-")[0]));
                        phpAux.setQuantidade(Integer.valueOf(rowData.get(2).toString()));
                        phpAux.setPrecoUnitarioProduto(Double.valueOf(rowData.get(3).toString()));

                        System.out.println(phpAux.toString());
                    }
                }
            }
        });

        //listeners do painel itens (has)
        btAdicionarLinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLinha(idPedido);
            }
        });
        btTotalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j < dados.length; j++) {
                    for (int k = 0; k < 5; k++) {
                        System.out.print(dados[j][k] + " - ");
                    }
                    System.out.println("");
                }
            }
        });

        // KeyListener para detectar a tecla INS, incluirá novo item na tabela
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_INSERT) {
                    //adicionarLinha(); // Chama o método para adicionar uma linha
                    teclouIns();
                }
            }
        });

    }

    // Método para ajustar a largura das colunas
    private void ajustarLarguraColunas() {
        TableColumn quantidadeColumn = table.getColumnModel().getColumn(2);
        TableColumn precoUnitarioColumn = table.getColumnModel().getColumn(3);
        TableColumn subtotalColumn = table.getColumnModel().getColumn(4);

        quantidadeColumn.setPreferredWidth(80); // Aproximadamente 8 caracteres
        precoUnitarioColumn.setPreferredWidth(80);
        subtotalColumn.setPreferredWidth(80);
    }

    // Modelo de tabela customizado
    class PedidoTableModel extends AbstractTableModel {

        private String[] colunas = {"idPedido", "idProduto", "quantidade", "precoUnitario", "subtotal"};
        private Object[][] dados = {
            {1, "Produto 1", 1, 10.0, 10.0}
        };

        public void setDados(Object[][] dados) {
            this.dados = dados;
        }

        @Override
        public int getRowCount() {
            return dados.length;
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return dados[rowIndex][columnIndex];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            dados[rowIndex][columnIndex] = aValue;

            // Recalcula o subtotal quando a quantidade ou o preço unitário mudar
            if (columnIndex == 2 || columnIndex == 3) {
                int quantidade = (int) dados[rowIndex][2];
                double precoUnitario = (double) dados[rowIndex][3];
                double subtotal = quantidade * precoUnitario;
                dados[rowIndex][4] = subtotal;
                fireTableCellUpdated(rowIndex, 4); // Atualiza a célula do subtotal
            }

            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Permitir edição de todas as células, exceto a do subtotal
            return columnIndex != 4;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                case 1:
                    return String.class;
                case 2:
                    return Integer.class;
                case 3:
                    return Double.class;
                case 4:
                    return Double.class;
                default:
                    return Object.class;
            }

        }

    }

}

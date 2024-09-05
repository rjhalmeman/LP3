package GUIs;

import DAOs.DAOProduto;
import Entidades.Produto;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Classe para criar o painel do pedido com uma tabela de itens.
 * 
 * @author radames
 */
public class PedidoItensDoPedidoPainelGUI extends JPanel {

    private JTable table;
    private PedidoTableModel tableModel;

    public PedidoItensDoPedidoPainelGUI(int idPedido) {
        
        
        
        
        // Configurar layout
        setLayout(new GridLayout(1, 1));

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
        
        String produtos[] = new String[lp.size()];
        int i=0;
        for (Produto p : lp) {
            produtos[i] =  p.getIdProduto()+"-"+p.getNomeProduto();
            i++;
        }
        
        
        
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

        // Adicionar a tabela ao painel com JScrollPane
        add(new JScrollPane(table));
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
            {1, "Produto 1", 1, 10.0, 10.0},
            {1, "Produto 2", 2, 15.0, 30.0}
        };

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
                case 0: return Integer.class;
                case 1: return String.class;
                case 2: return Integer.class;
                case 3: return Double.class;
                case 4: return Double.class;
                default: return Object.class;
            }
        }
    }
}

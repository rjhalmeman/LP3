package GUIs;

import DAOs.DAOPedidoHasProduto;
import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class GUIPedidoHasProdutoJTable extends JPanel {

//  ------------------------------------------------------------------------------------------------------ 
    private Container cp;
    private final JPanel painelAvisos = new JPanel();
    private final JButton btnAdd = new JButton("Adicionar");
    private final JButton btnRem = new JButton("Remover");
    private final JButton btnCarregar = new JButton("Carregar dados");

    private JTable table = new JTable();
    private PedidoHasProdutoTableModel tableModel;
    private JButton btnInserir = new JButton("Tecla INS = Insere novo registro");

    private DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();

    public GUIPedidoHasProdutoJTable(JPanel pnExterno, int idPedido) {
        cp = pnExterno;
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, painelAvisos);

        List< PedidoHasProduto> lista = new ArrayList<>();
        tableModel = new PedidoHasProdutoTableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        painelAvisos.add(btnInserir);
        painelAvisos.add(new JLabel("   --   "));
        painelAvisos.add(new JLabel("Tecla DEL = Exclui registro selecionado"));
        painelAvisos.setBackground(Color.cyan);

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
        im.put(enterKey, "Action.insert");

        actionMap.put("Action.insert", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAdd.doClick();
            }
        });

//---------------------------------- button delete -----------------------------
        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        im.put(delKey, "Action.delete");

        actionMap.put("Action.delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (table.getSelectedRow() >= 0) {

                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(cp,
                            "Confirma a exclusão da PedidoHasProduto [" + tableModel.getValue(table.getSelectedRow()).getPedido().getIdPedido() + " - "
                            + tableModel.getValue(table.getSelectedRow()).getPedido().getIdPedido() + "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                        btnRem.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a PedidoHasProduto a ser excluída");
                }
            }
        });
        
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnAdd.doClick();
            }
        });
        

//========================================== botão add ============================================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoHasProduto php = new PedidoHasProduto();
                PedidoHasProdutoPK pedidoHasProdutoPK = new PedidoHasProdutoPK();
                pedidoHasProdutoPK.setPedidoIdPedido(idPedido);
                Object[] itens = {"1", "2", "3"};
                Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um item", "Opçao", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]); //
                String s = selectedValue.toString();
               // System.out.println("s "+s);
                pedidoHasProdutoPK.setProdutoIdProduto(Integer.valueOf(s));
                php.setPedidoHasProdutoPK(pedidoHasProdutoPK);

                php.setQuantidadeProdutoPedido(0);
                php.setPrecoUnitario(0.0);
                php.setDescontoUnitario(0.0);
                daoPedidoHasProduto.inserir(php);
                tableModel.onAdd(php);
                tableModel.fireTableDataChanged();
            }
        }
        );//============================================ botao remover =======================================================

        btnRem.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    PedidoHasProduto pedidoHasProdutoBeam = tableModel.getValue(table.getSelectedRow());
                    daoPedidoHasProduto.remover(pedidoHasProdutoBeam);
                    tableModel.onRemove(table.getSelectedRows());

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        }
        );//============================================ botao carregar =======================================================

        btnCarregar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
                try {
                    List<PedidoHasProduto> lc = daoPedidoHasProduto.listPedidosPorId(idPedido);

                    tableModel.setDados(lc);
                    tableModel.fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        }
        );
//============================================ listener table =======================================================

        tableModel.addTableModelListener(
                new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e
            ) {
                // if (tableModel.mudou) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    PedidoHasProduto c = tableModel.getValue(table.getSelectedRow());
                    daoPedidoHasProduto.atualizar(c);
                }
                //}
            }
        }
        );//============================================ fim do construtor gui =======================================================

        btnCarregar.doClick();//carrega os dados 

    } //fim do construtor da GUI

//============================================ date render =======================================================
    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

//============================================ date editor =======================================================
    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private final JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }

}

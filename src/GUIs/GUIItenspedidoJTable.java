package GUIs;

import DAOs.DAOItensPedido;
import Entidades.ItensPedido;
import myUtil.CentroDoMonitorMaior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.Dimension;
import java.awt.Point;

public class GUIItenspedidoJTable extends JDialog {

//  ------------------------------------------------------------------------------------------------------ 
    private Container cp;
    private final JPanel pnCentroNorte = new JPanel();
    private final JButton btnAdd = new JButton("Adicionar");
    private final JButton btnRem = new JButton("Remover");
    private final JButton btnCarregar = new JButton("Carregar dados");

    private JTable table = new JTable();
    private ItenspedidoTableModel tableModel;

    private DAOItensPedido daoItensPedido = new DAOItensPedido();

    public GUIItenspedidoJTable(Point posicao, Dimension dimensao) {

        setTitle("CRUD ItenspedidoX");
        setLayout(new FlowLayout());
        setSize(dimensao);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, pnCentroNorte);

        List< ItensPedido> lista = new ArrayList<>();
        tableModel = new ItenspedidoTableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        pnCentroNorte.add(new JLabel("Tecla INS = Insere novo registro"));
        pnCentroNorte.add(new JLabel("   --   "));
        pnCentroNorte.add(new JLabel("Tecla DEL = Exclui registro selecionado"));
        pnCentroNorte.setBackground(Color.cyan);

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
                            "Confirma a exclusão da Itenspedido [" + tableModel.getValue(table.getSelectedRow()).getItensPedidoPK().getProdutoIdProduto()+ " - "
                            + tableModel.getValue(table.getSelectedRow()).getItensPedidoPK().getPedidoIdPedido()+ "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                        btnRem.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a Itenspedido a ser excluída");
                }
            }
        });

//========================================== fechar a janela ============================================
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                dispose();
            }
        });
//========================================== botão add ============================================

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ItenspedidoX itenspedidoX = new ItenspedidoX();
//               //////////////////////////// itenspedidoX.setPedidoIdPedido(daoItensPedido.autoIdItenspedido());
//                itenspedidoX.setProdutoIdProduto(0);
//                itenspedidoX.setQuantidade(0);
//                itenspedidoX.setValorUnitario(0.0);
//                itenspedidoX.setDesconto(0.0);
//                daoItensPedido.inserir(itenspedidoX);
//                tableModel.onAdd(itenspedidoX);
//                tableModel.fireTableDataChanged();
            }
        });//============================================ botao remover =======================================================

        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
//                    ItenspedidoX itenspedidoX = tableModel.getValue(table.getSelectedRow());
//                    daoItensPedido.remover(itenspedidoX);
                    tableModel.onRemove(table.getSelectedRows());

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        });//============================================ botao carregar =======================================================

        btnCarregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                DAOItenspedidoX daoItenspedidoX = new DAOItenspedidoX();
//                try {
//                    List<ItenspedidoX> lc = daoItenspedidoX.list();
//                    tableModel.setDados(lc);
//                    tableModel.fireTableDataChanged();
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
//                }
            }

        });
//============================================ listener table =======================================================

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
//                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
//                    ItenspedidoX c = tableModel.getValue(table.getSelectedRow());
//                    daoItensPedido.atualizar(c);
//                }
                //}
            }
        });//============================================ fim do construtor gui =======================================================

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();

        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        btnCarregar.doClick();//carrega os dados 

        setModal(true);
        setVisible(true);

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

    public static void main(String[] args) {
        new GUIItenspedidoJTable(new Point(880, 250), new Dimension(800, 600));
    }
}

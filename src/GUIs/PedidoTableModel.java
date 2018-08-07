package GUIs;

import Entidades.Pedido;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import Entidades.Cliente;
import DAOs.DAOCliente;

public class PedidoTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, Date.class, Cliente.class};
    private final String colunas[] = new String[]{"idPedido", "dataPedido", "Cliente"};
    private List<Pedido> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public PedidoTableModel(List<Pedido> dados) {
        this.dados = dados;
    }

    public void setDados(List<Pedido> dados) {
        this.dados = dados;
    }

    public List<Pedido> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Pedido pedido = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedido.getIdPedido();
            case 1:
                return pedido.getDataPedido();
            case 2:
                return pedido.getClienteIdCliente().getNomeCliente();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

// Para impedir que exista duplicidade na chave primaria
    public boolean chaveExiste(String chave) {
        for (Pedido x : dados) {
            if (x.getIdPedido().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Pedido pedido = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    pedido.setIdPedido((Integer) aValue);
                }
                break;
            case 1:
                pedido.setDataPedido((Date) aValue);
                break;
            case 2:
                aaa = String.valueOf(aValue).split("-");
                Cliente vCliente = new DAOCliente().obter(Integer.valueOf(aaa[0].trim()));
                pedido.setClienteIdCliente(vCliente);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Pedido getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Pedido pedido) {
        return dados.indexOf(pedido);
    }

    public void onAdd(Pedido pedido) {
        dados.add(pedido);
        fireTableRowsInserted(indexOf(pedido), indexOf(pedido));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

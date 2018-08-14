package GUIs;

import Entidades.ItensPedido;
import Entidades.ItensPedido;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ItenspedidoTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, Integer.class, Integer.class, double.class, Double.class};
    private final String colunas[] = new String[]{"pedidoIdPedido", "produtoIdProduto", "quantidade", "valorUnitario", "desconto"};
    private List<ItensPedido> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public ItenspedidoTableModel(List<ItensPedido> dados) {
        this.dados = dados;
    }

    public void setDados(List<ItensPedido> dados) {
        this.dados = dados;
    }

    public List<ItensPedido> getDados() {
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

        ItensPedido itenspedidoX = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return 1;//itenspedidoX.getPedidoIdPedido();
            case 1:
                return 2;//itenspedidoX.getProdutoIdProduto();
            case 2:
                return itenspedidoX.getQuantidade();
            case 3:
                return itenspedidoX.getValorUnitario();
            case 4:
                return itenspedidoX.getDesconto();
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
        for (ItensPedido x : dados) {
//            if (x.getPedidoIdPedido().equals(chave)) {
//                return true;
//            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        ItensPedido itenspedidoX = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                 //   itenspedidoX.setPedidoIdPedido((Integer) aValue);
                }
                break;
            case 1:
              //  itenspedidoX.setProdutoIdProduto((Integer) aValue);
                break;
            case 2:
                itenspedidoX.setQuantidade((Integer) aValue);
                break;
            case 3:
                itenspedidoX.setValorUnitario((Double) aValue);
                break;
            case 4:
                itenspedidoX.setDesconto((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public ItensPedido getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(ItensPedido itenspedidoX) {
        return dados.indexOf(itenspedidoX);
    }

    public void onAdd(ItensPedido itenspedidoX) {
        dados.add(itenspedidoX);
        fireTableRowsInserted(indexOf(itenspedidoX), indexOf(itenspedidoX));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

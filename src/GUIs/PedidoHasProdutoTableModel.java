package GUIs;

import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PedidoHasProdutoTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, Integer.class, Integer.class, Double.class, Double.class};
    private final String colunas[] = new String[]{"pedidoIdPedido", "produtoIdProduto", "quantidadeProdutoPedido", "precoUnitario", "descontoUnitario","subTotal"};
    private List<PedidoHasProduto> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private int idPedido = 0; // xxxxxxxxxxxxxx

    public PedidoHasProdutoTableModel(List<PedidoHasProduto> dados) {
        this.dados = dados;
        if (dados.size()>0) {
            idPedido = dados.get(0).getPedido().getIdPedido();
        }
    }

    public void setDados(List<PedidoHasProduto> dados) {
        this.dados = dados;
    }

    public List<PedidoHasProduto> getDados() {
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

        PedidoHasProduto pedidoHasProduto = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedidoHasProduto.getPedidoHasProdutoPK().getPedidoIdPedido();
            case 1:
                return pedidoHasProduto.getPedidoHasProdutoPK().getProdutoIdProduto();
            case 2:
                return pedidoHasProduto.getQuantidadeProdutoPedido();
            case 3:
                return pedidoHasProduto.getPrecoUnitario();
            case 4:
                return pedidoHasProduto.getDescontoUnitario();
            case 5:
                return 55;
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
    public boolean chaveExiste(int idPedido, int idProduto) {
        for (PedidoHasProduto x : dados) {
            if ((x.getPedido().getIdPedido() == idPedido) && (x.getProduto().getIdProduto() == idProduto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        PedidoHasProduto pedidoHasProduto = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
//                if (!chaveExiste(idPedido, (Integer) aValue)) {
//                 // xxxxxxxx   pedidoHasProduto.setPedidoIdPedido((Integer) aValue);
//                }
                break;
            case 1:
                if (!chaveExiste(idPedido, (Integer) aValue)) {
                    PedidoHasProdutoPK phpPK = new PedidoHasProdutoPK();
                    phpPK.setPedidoIdPedido(idPedido);
                    phpPK.setProdutoIdProduto((Integer) aValue);
                    pedidoHasProduto.setPedidoHasProdutoPK(phpPK);
                    break;
                }
                case 2:
                pedidoHasProduto.setQuantidadeProdutoPedido((Integer) aValue);
                break;
            case 3:
                pedidoHasProduto.setPrecoUnitario((Double) aValue);
                break;
            case 4:
                pedidoHasProduto.setDescontoUnitario((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
        }

    

    public PedidoHasProduto getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(PedidoHasProduto pedidoHasProduto) {
        return dados.indexOf(pedidoHasProduto);
    }

    public void onAdd(PedidoHasProduto pedidoHasProduto) {
        dados.add(pedidoHasProduto);
        fireTableRowsInserted(indexOf(pedidoHasProduto), indexOf(pedidoHasProduto));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

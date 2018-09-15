package GUIs;

import Entidades.Fornecedor;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;


public class FornecedorTableModel extends AbstractTableModel {




//  ------------------------------------------------------------------------------------------------------ 
private final Class classes[] = new Class[]{Integer.class,String.class};
private final String colunas[] = new String[]{"idFornecedor","nomeFornecedor"};
 private List<Fornecedor> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
   public FornecedorTableModel(List<Fornecedor> dados) {
        this.dados = dados;
    }

    public void setDados(List<Fornecedor> dados) {
        this.dados = dados;
    }

    public List<Fornecedor> getDados() {
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

        Fornecedor fornecedor = dados.get(rowIndex);
        switch (columnIndex) {case 0:
   return fornecedor.getIdFornecedor();
case 1:
   return fornecedor.getNomeFornecedor();
 default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }
 @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex==0) {
            return false;
        }
        return true;
    }

// Para impedir que exista duplicidade na chave primaria
    public boolean chaveExiste(String chave) {
        for (Fornecedor x : dados) {
            if (x.getIdFornecedor().equals(chave)) {
                return true;
            }
        }
        return false;
    }@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    String aaa[];
        Fornecedor fornecedor = dados.get(rowIndex);
        switch (columnIndex) {case 0:
   if (!chaveExiste((String) aValue)) { 
fornecedor.setIdFornecedor((Integer) aValue);
}
 break;
case 1:
   fornecedor.setNomeFornecedor((String) aValue);
 break;
   default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

 public Fornecedor getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Fornecedor fornecedor) {
        return dados.indexOf(fornecedor);
    }

    public void onAdd(Fornecedor fornecedor) {
        dados.add(fornecedor);
        fireTableRowsInserted(indexOf(fornecedor), indexOf(fornecedor));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }}
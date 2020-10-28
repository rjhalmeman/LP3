package GUIs;

import Entidades.UnidadeDeMedida;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class UnidadeDeMedidaTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{String.class, String.class};
    private final String colunas[] = new String[]{"idUnidadeDeMedida", "nomeUnidadeDeMedida"};
    private List<UnidadeDeMedida> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public UnidadeDeMedidaTableModel(List<UnidadeDeMedida> dados) {
        this.dados = dados;
    }

    public void setDados(List<UnidadeDeMedida> dados) {
        this.dados = dados;
    }

    public List<UnidadeDeMedida> getDados() {
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

        UnidadeDeMedida unidadeDeMedida = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return unidadeDeMedida.getIdUnidadeDeMedida();
            case 1:
                return unidadeDeMedida.getNomeUnidadeDeMedida();
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
        for (UnidadeDeMedida x : dados) {
            if (x.getIdUnidadeDeMedida().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        UnidadeDeMedida unidadeDeMedida = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    unidadeDeMedida.setIdUnidadeDeMedida((String) aValue);
                }
                break;
            case 1:
                unidadeDeMedida.setNomeUnidadeDeMedida((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public UnidadeDeMedida getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(UnidadeDeMedida unidadeDeMedida) {
        return dados.indexOf(unidadeDeMedida);
    }

    public void onAdd(UnidadeDeMedida unidadeDeMedida) {
        dados.add(unidadeDeMedida);
        fireTableRowsInserted(indexOf(unidadeDeMedida), indexOf(unidadeDeMedida));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

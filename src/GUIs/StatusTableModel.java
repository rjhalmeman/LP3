package GUIs;

import Entidades.Status;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class StatusTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, String.class};
    private final String colunas[] = new String[]{"idStatus", "nomeStatus"};
    private List<Status> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public StatusTableModel(List<Status> dados) {
        this.dados = dados;
    }

    public void setDados(List<Status> dados) {
        this.dados = dados;
    }

    public List<Status> getDados() {
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

        Status status = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return status.getIdStatus();
            case 1:
                return status.getNomeStatus();
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
        for (Status x : dados) {
            if (x.getIdStatus().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Status status = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    status.setIdStatus((Integer) aValue);
                }
                break;
            case 1:
                status.setNomeStatus((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Status getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Status status) {
        return dados.indexOf(status);
    }

    public void onAdd(Status status) {
        dados.add(status);
        fireTableRowsInserted(indexOf(status), indexOf(status));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

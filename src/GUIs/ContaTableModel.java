package GUIs;


import daos.DAOTipoConta;
import entidades.Conta;
import entidades.TipoConta;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContaTableModel extends AbstractTableModel {

    private final Class classes[] = new Class[]{Integer.class, String.class, TipoConta.class, Double.class, Double.class};
    private final String colunas[] = new String[]{"id", "Nome", "Tipo", "Saldo", "Limite"};
    private List<Conta> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
  

    public ContaTableModel(List<Conta> dados) {
        this.dados = dados;
    }

    public void setDados(List<Conta> dados) {
        this.dados = dados;
    }

    public List<Conta> getDados() {
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

        Conta s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdConta();
            case 1:
                return s.getNomeConta();
            case 2:
                return s.getTipoContaIdTipoConta().getNomeTipoConta();
            case 3:
                return s.getSaldo();
            case 4:
                return s.getLimite();
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

    // Para impedir que existam duas pessoas com o mesmo CPF
    public boolean chaveExiste(String chave) {
        for (Conta x : dados) {
            if (x.getIdConta().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

      //  mudou = true;
        Conta s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    s.setIdConta((Integer) aValue);
                }
                break;
            case 1:
                s.setNomeConta((String) aValue);
                break;
            case 2:             
                String [] aux = String.valueOf(aValue).split("-");
                TipoConta tc = new DAOTipoConta().obter(Integer.valueOf(aux[0]));
                
                s.setTipoContaIdTipoConta(tc);
                break;
            case 3:
                s.setSaldo((Double) aValue);
                break;
            case 4:
                s.setLimite((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Conta getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Conta conta) {
        return dados.indexOf(conta);
    }

    public void onAdd(Conta conta) {
        dados.add(conta);
        fireTableRowsInserted(indexOf(conta), indexOf(conta));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}

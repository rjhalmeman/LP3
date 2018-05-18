package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import daos.DAOConta;
import daos.DAOLancamento;
import entidades.Conta;
import entidades.Lancamento;
import java.util.List;

public class ObterSaldo {

    public double getCreditos(int idConta) {
        DAOConta daoConta = new DAOConta();
        Conta c = daoConta.obter(idConta);
        List<Lancamento> ll = new DAOLancamento().getDebitosOuCreditos(idConta, "C");
        double creditos=0;
        for (Lancamento l : ll) {
            creditos += l.getValor();
        }
        
        return creditos;
    }
    
    public double getDebitos(int idConta) {
        DAOConta daoConta = new DAOConta();
        Conta c = daoConta.obter(idConta);
        List<Lancamento> ll = new DAOLancamento().getDebitosOuCreditos(idConta, "D");
        double creditos=0;
        for (Lancamento l : ll) {
            creditos += l.getValor();
        }
        
        return creditos;
    }
}

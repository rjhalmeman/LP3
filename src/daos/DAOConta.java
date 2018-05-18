package daos;

import entidades.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

public class DAOConta extends DAOGenerico<Conta> {

    public DAOConta() {
        super(Conta.class);
    }

    public int autoIdConta() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idConta) FROM Conta e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

   

    public List<Conta> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Conta e WHERE e.nomeConta LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Conta> listById(int id) {
        return em.createQuery("SELECT e FROM Conta e WHERE e.idConta = :id").setParameter("id", id).getResultList();
    }
    
   

    public List<Conta> listInOrderNome() {
        return em.createQuery("SELECT e FROM Conta e ORDER BY e.nomeConta").getResultList();
    }

    public List<Conta> listInOrderId() {
        return em.createQuery("SELECT e FROM Conta e ORDER BY e.idConta").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Conta> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdConta() + "-" + lf.get(i).getNomeConta());
        }
        return ls;
    }
}

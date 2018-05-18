package daos;

import entidades.TipoConta;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoConta extends DAOGenerico<TipoConta> {

    public DAOTipoConta() {
        super(TipoConta.class);
    }

    public int autoIdTipoConta() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoConta) FROM TipoConta e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoConta> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoConta e WHERE e.nomeTipoConta LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoConta> listById(int id) {
        return em.createQuery("SELECT e FROM TipoConta e WHERE e.idTipoConta = :id").setParameter("id", id).getResultList();
    }

    public List<TipoConta> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoConta e ORDER BY e.nomeTipoConta").getResultList();
    }

    public List<TipoConta> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoConta e ORDER BY e.idTipoConta").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoConta> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoConta() + "-" + lf.get(i).getNomeTipoConta());
        }
        return ls;
    }
}

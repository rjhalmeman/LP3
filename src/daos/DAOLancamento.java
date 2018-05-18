package daos;

import static daos.DAOGenerico.em;
import entidades.Lancamento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOLancamento extends DAOGenerico<Lancamento> {

    public DAOLancamento() {
        super(Lancamento.class);
    }

    public int autoIdLancamento() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idLancamento) FROM Lancamento e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }
     public List<Lancamento> getDebitosOuCreditos(int id,String tipoLancamento) {
        return em.createQuery("SELECT e FROM Lancamento e WHERE e.contaIdConta.idConta = :id AND e.tipoLancamentoIdTipo.idTipo = :tipo")
                .setParameter("id", id).setParameter("tipo", tipoLancamento).getResultList();
    }

    public List<Lancamento> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Lancamento e WHERE e.descricao LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Lancamento> listById(int id) {
        return em.createQuery("SELECT e FROM Lancamento e WHERE e.idLancamento = :id").setParameter("id", id).getResultList();
    }

    public List<Lancamento> listByContaId(int id) {
        return em.createQuery("SELECT e FROM Lancamento e WHERE e.contaIdConta.idConta = :id").setParameter("id", id).getResultList();
    }

    public List<Lancamento> listByContaIntervalo(int idConta, Date dataInicio, Date dataFim) {
        return em.createQuery("SELECT e FROM Lancamento e WHERE e.contaIdConta.idConta = :id "
                + "AND e.dataLancamento>= :dtIn AND e.dataLancamento<= :dtFim").setParameter("id", idConta).setParameter("dtIn", dataInicio).setParameter("id", idConta).setParameter("dtFim", dataFim).getResultList();
    }

    public List<Lancamento> listInOrderNome() {
        return em.createQuery("SELECT e FROM Lancamento e ORDER BY e.nomeLancamento").getResultList();
    }

    public List<Lancamento> listInOrderId() {
        return em.createQuery("SELECT e FROM Lancamento e ORDER BY e.idLancamento").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Lancamento> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdLancamento() + "-" + lf.get(i).getDescricao());
        }
        return ls;
    }
}

package daos;

import static daos.DAOGenerico.em;
import entidades.Estado;


import java.util.ArrayList;
import java.util.List;

public class DAOEstado extends DAOGenerico<Estado> {

    public DAOEstado() {
        super(Estado.class);
    }

    public List<Estado> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Estado e WHERE e.nomeEstado LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Estado> listById(int id) {
        return em.createQuery("SELECT e FROM Estado e WHERE e.idEstado = :id").setParameter("id", id).getResultList();
    }

    public List<Estado> listInOrderNome() {
        return em.createQuery("SELECT e FROM Estado e ORDER BY e.nomeEstado").getResultList();
    }

   

    public List<String> listInOrderNomeStrings() {
        List<Estado> lf = listInOrderNome();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdEstado() + "-" + lf.get(i).getNomeEstado());
        }
        return ls;
    }
    
    public String[] listInOrderNomeStringsArray() {
        List<Estado> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getIdEstado() + "-" + lf.get(i).getNomeEstado());
        }
        return ls;
    }
}

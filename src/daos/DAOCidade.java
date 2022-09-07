package daos;

import static daos.DAOGenerico.em;
import entidades.Cidade;


import java.util.ArrayList;
import java.util.List;

public class DAOCidade extends DAOGenerico<Cidade> {

    public DAOCidade() {
        super(Cidade.class);
    }

    public List<Cidade> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cidade e WHERE e.nomeCidade LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Cidade> listById(int id) {
        return em.createQuery("SELECT e FROM Cidade e WHERE e.idCidade = :id").setParameter("id", id).getResultList();
    }

    public List<Cidade> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cidade e ORDER BY e.nomeCidade").getResultList();
    }

   

    public List<String> listInOrderNomeStrings() {
        List<Cidade> lf = listInOrderNome();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdCidade() + "-" + lf.get(i).getNomeCidade());
        }
        return ls;
    }
    
     public String[] listInOrderNomeStringsArray(String siglaEstado) {
        List<Cidade> lf = em.createQuery("SELECT e FROM Cidade e WHERE e.estadoIdEstado.idEstado = :id").setParameter("id", siglaEstado).getResultList();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getIdCidade()+ "-" + lf.get(i).getNomeCidade());
        }
        return ls;
    }
}

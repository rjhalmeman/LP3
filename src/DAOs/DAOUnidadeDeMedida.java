package DAOs;

import Entidades.UnidadeDeMedida;
import java.util.ArrayList;
import java.util.List;

public class DAOUnidadeDeMedida extends DAOGenerico<UnidadeDeMedida> {

    private final List<UnidadeDeMedida> lista = new ArrayList<>();

    public DAOUnidadeDeMedida() {
        super(UnidadeDeMedida.class);
    }

    public int autoIdUnidadeDeMedida() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idUnidadeDeMedida) FROM UnidadeDeMedida e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<UnidadeDeMedida> listByNome(String nome) {
        return em.createQuery("SELECT e FROM UnidadeDeMedida e WHERE e.idUnidadeDeMedida LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<UnidadeDeMedida> listById(int id) {
        return em.createQuery("SELECT e FROM UnidadeDeMedida + e WHERE e.nomeUnidadeDeMedida= :id").setParameter("id", id).getResultList();
    }

    public List<UnidadeDeMedida> listInOrderNome() {
        return em.createQuery("SELECT e FROM UnidadeDeMedida e ORDER BY e.nomeUnidadeDeMedida").getResultList();
    }

    public List<UnidadeDeMedida> listInOrderId() {
        return em.createQuery("SELECT e FROM UnidadeDeMedida e ORDER BY e.idUnidadeDeMedida").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<UnidadeDeMedida> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdUnidadeDeMedida() + "-" + lf.get(i).getNomeUnidadeDeMedida());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
        List<UnidadeDeMedida> listaUnidadeDeMedida = daoUnidadeDeMedida.list();
        for (UnidadeDeMedida unidadeDeMedida : listaUnidadeDeMedida) {
            System.out.println(unidadeDeMedida.getIdUnidadeDeMedida() + "-" + unidadeDeMedida.getNomeUnidadeDeMedida());
        }
    }
}

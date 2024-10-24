package DAOs;

import Entidades.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOCategoria extends DAOGenerico<Categoria> {

    private List<Categoria> lista = new ArrayList<>();

    public DAOCategoria() {
        super(Categoria.class);
    }

    public int autoIdCliente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaCpfPessoa) FROM + nomeDaClasse +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Categoria> listByNome(String cpfCliente) {
        return em.createQuery("SELECT e FROM Categoria e WHERE e.idCatedoria) LIKE :idCategoria").setParameter("idCategoria", "%" + cpfCliente + "%").getResultList();
    }

    public List<Categoria> listById(int id) {
        return em.createQuery("SELECT e FROM Categoria + e WHERE e.idCatedoria= :id").setParameter("id", id).getResultList();
    }

    public List<Categoria> listInOrderNome() {
        return em.createQuery("SELECT e FROM Categoria e ORDER BY e.nomeCategoria").getResultList();
    }

    public List<Categoria> listInOrderId() {
        return em.createQuery("SELECT e FROM Categoria e ORDER BY e.idCategoria").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Categoria> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getNomeCategoria());
        }
        return ls;
    }

    public String[] listInOrderNomeStringsArray() {
        List< Categoria> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdCategoria() + "-" + lf.get(i).getNomeCategoria());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOCategoria daoCategoria = new DAOCategoria();
        List<Categoria> listaCategoria = daoCategoria.list();
        for (Categoria categoria : listaCategoria) {
            System.out.println(categoria.getIdCategoria() + "-" + categoria.getNomeCategoria());
        }
    }
}

package DAOs;

import Entidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class DAOProduto extends DAOGenerico<Produto> {

    private final List<Produto> lista = new ArrayList<>();

    public DAOProduto() {
        super(Produto.class);
    }

    public int autoId() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Produto e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Produto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.id LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Produto> listById(int id) {
        return em.createQuery("SELECT e FROM Produto + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<Produto> listInOrderNome() {
        return em.createQuery("SELECT e FROM Produto e ORDER BY e.nome").getResultList();
    }

    public List<Produto> listInOrderId() {
        return em.createQuery("SELECT e FROM Produto e ORDER BY e.id").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Produto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getId() + "-" + lf.get(i).getNome());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOProduto daoProduto = new DAOProduto();
        List<Produto> listaProduto = daoProduto.list();
        for (Produto contato : listaProduto) {
            System.out.println(contato.getId() + "-" + contato.getNome());
        }
    }
}

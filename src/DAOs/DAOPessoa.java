package DAOs;

import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOPessoa extends DAOGenerico<Pessoa> {

    private List<Pessoa> lista = new ArrayList<>();

    public DAOPessoa() {
        super(Pessoa.class);
    }

    public int autoIdPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpfPessoa) FROM + nomeDaClasse +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pessoa> listByNome(String nomeCliente) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.cpfPessoa) LIKE :nomePessoa").setParameter("nomePessoa", "%" + nomeCliente + "%").getResultList();
    }

    public List<Pessoa> listById(int id) {
        return em.createQuery("SELECT e FROM Pessoa + e WHERE e.cpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Pessoa> listInOrderNome() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.nomePessoa").getResultList();
    }

    public List<Pessoa> listInOrderId() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.cpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pessoa> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCpfPessoa() + "-" + lf.get(i).getNomePessoa());
        }
        return ls;
    }

    public String[] listInOrderNomeStringsArray(String categoria) {
        List< Pessoa> lf = em.createQuery("SELECT e FROM Pessoa e WHERE e.cpfPessoa :id").setParameter("id", categoria).getResultList();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getCpfPessoa() + "-" + lf.get(i).getNomePessoa());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<Pessoa> listaPessoa = daoPessoa.list();
        for (Pessoa pessoa : listaPessoa) {
            System.out.println(pessoa.getCpfPessoa() + "-" + pessoa.getNomePessoa());
        }
    }
}

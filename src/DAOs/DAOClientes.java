package DAOs;

import Entidades.Clientes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOClientes extends DAOGenerico<Clientes> {

    private List<Clientes> lista = new ArrayList<>();

    public DAOClientes() {
        super(Clientes.class);
    }

    public int autoIdClientes() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaCpfPessoa) FROM + Clientes +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Clientes> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Clientes e WHERE e.pessoaCpfPessoa) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Clientes> listById(int id) {
        return em.createQuery("SELECT e FROM Clientes + e WHERE e.pessoaCpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Clientes> listInOrderNome() {
        return em.createQuery("SELECT e FROM Clientes e ORDER BY e.nomeCliente").getResultList();
    }

    public List<Clientes> listInOrderId() {
        return em.createQuery("SELECT e FROM Clientes e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Clientes> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaCpfPessoa() + "-" + lf.get(i).getNomePessoa());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOClientes daoClientes = new DAOClientes();
        List<Clientes> listaClientes = daoClientes.list();
        for (Clientes viewClientes : listaClientes) {
            System.out.println(viewClientes.getPessoaCpfPessoa() + "-" + viewClientes.getNomePessoa());
        }
    }
}

package DAOs;

import Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOCliente extends DAOGenerico<Cliente> {

    private List<Cliente> lista = new ArrayList<>();

    public DAOCliente() {
        super(Cliente.class);
    }

    public int autoIdCliente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaCpfPessoa) FROM + nomeDaClasse +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cliente> listByNome(String cpfCliente) {
        return em.createQuery("SELECT e FROM Cliente e WHERE e.pessoaCpfPessoa) LIKE :cpfCliente").setParameter("cpfCliente", "%" + cpfCliente + "%").getResultList();
    }

    public List<Cliente> listById(int id) {
        return em.createQuery("SELECT e FROM Cliente + e WHERE e.pessoaCpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Cliente> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cliente e ORDER BY e.nomeCliente").getResultList();
    }

    public List<Cliente> listInOrderId() {
        return em.createQuery("SELECT e FROM Cliente e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cliente> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaCpfPessoa());
        }
        return ls;
    }
//public static void main(String[] args) {
//        DAOCliente daoCliente = new DAOCliente();
//        List<Cliente> listaCliente = daoCliente.list();
//        for (Cliente pessoa: listaCliente ) {
//            System.out.println(pessoa.getCpfCliente()+ "-" + pessoa.getNomeCliente());
//        }
//    }
}

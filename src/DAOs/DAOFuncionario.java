package DAOs;

import Entidades.Funcionario;
import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOFuncionario extends DAOGenerico<Funcionario> {

    private List<Funcionario> lista = new ArrayList<>();

    public DAOFuncionario() {
        super(Funcionario.class);
    }

    public int autoIdFuncionario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpfPessoa) FROM + nomeDaClasse +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Funcionario> listByNome(String nomeCliente) {
        return em.createQuery("SELECT e FROM Funcionario e WHERE e.pessoaCpfPessoa) LIKE :cpfFuncionario").setParameter("cpfFuncionario", "%" + nomeCliente + "%").getResultList();
    }

    public List<Funcionario> listById(int id) {
        return em.createQuery("SELECT e FROM Funcionario + e WHERE e.pessoaCpfPessoa= :id").setParameter("id", id).getResultList();
    }

    public List<Funcionario> listInOrderNome() {
        return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.cpfFuncionario").getResultList();
    }

    public List<Funcionario> listInOrderId() {
        return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.pessoaCpfPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Funcionario> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaCpfPessoa() + "-" + lf.get(i).getCargo());
        }
        return ls;
    }

    public List<String> listInOrderNomeStrings2(String qualOrdem) {
        List<Funcionario> lf;
        Pessoa pessoa = new Pessoa();
        DAOPessoa daoPessoa = new DAOPessoa();
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

    public static void main(String[] args) {
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        List<Funcionario> listaFuncionario = daoFuncionario.list();
        for (Funcionario funcionario : listaFuncionario) {
            System.out.println(funcionario.getPessoaCpfPessoa() + "-" + funcionario.getCargo());
        }
    }
}

package DAOs;

import Entidades.Fornecedor;
import java.util.ArrayList;
import java.util.List;

public class DAOFornecedor extends DAOGenerico<Fornecedor> {

private List<Fornecedor> lista = new ArrayList<>();    public DAOFornecedor(){
        super(Fornecedor.class);
    }

    public int autoIdFornecedor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idFornecedor) FROM Fornecedor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Fornecedor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Fornecedor e WHERE e.idFornecedor LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Fornecedor> listById(int id) {
        return em.createQuery("SELECT e FROM Fornecedor + e WHERE e.nomeFornecedor= :id").setParameter("id", id).getResultList();
    }

    public List<Fornecedor> listInOrderNome() {
        return em.createQuery("SELECT e FROM Fornecedor e ORDER BY e.nomeFornecedor").getResultList();
    }

    public List<Fornecedor> listInOrderId() {
        return em.createQuery("SELECT e FROM Fornecedor e ORDER BY e.idFornecedor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Fornecedor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdFornecedor() + "-" + lf.get(i).getNomeFornecedor());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOFornecedor daoFornecedor = new DAOFornecedor();
        List<Fornecedor> listaFornecedor = daoFornecedor.list();
        for (Fornecedor fornecedor : listaFornecedor) {
            System.out.println(fornecedor.getIdFornecedor()+"-"+fornecedor.getNomeFornecedor());
        }
    }}
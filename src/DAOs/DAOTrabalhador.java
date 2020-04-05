package DAOs;

import Entidades.Trabalhador;
import java.util.ArrayList;
import java.util.List;

public class DAOTrabalhador extends DAOGenerico<Trabalhador> {

    private List<Trabalhador> lista = new ArrayList<>();

    public DAOTrabalhador() {
        super(Trabalhador.class);
    }

    public int autoIdTrabalhador() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpf) FROM Trabalhador e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Trabalhador> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Trabalhador e WHERE e.cpf) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Trabalhador> listById(int id) {
        return em.createQuery("SELECT e FROM Trabalhador + e WHERE e.cpf= :id").setParameter("id", id).getResultList();
    }

    public List<Trabalhador> listInOrderNome() {
        return em.createQuery("SELECT e FROM Trabalhador e ORDER BY e.nome").getResultList();
    }

    public List<Trabalhador> listInOrderId() {
        return em.createQuery("SELECT e FROM Trabalhador e ORDER BY e.cpf").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Trabalhador> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCpf()+ "-" + lf.get(i).getNome());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTrabalhador daoTrabalhador = new DAOTrabalhador();
        List<Trabalhador> listaTrabalhador = daoTrabalhador.list();
        for (Trabalhador trabalhador : listaTrabalhador) {
            System.out.println(trabalhador.getCpf()+ "-" + trabalhador.getNome());
        }
    }
}

package DAOs;

import Entidades.Cargo;
import java.util.ArrayList;
import java.util.List;

public class DAOCargo extends DAOGenerico<Cargo> {

private final List<Cargo> lista = new ArrayList<>();    public DAOCargo(){
        super(Cargo.class);
    }

    public int autoIdCargo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idCargo) FROM Cargo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cargo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cargo e WHERE e.idCargo LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Cargo> listById(int id) {
        return em.createQuery("SELECT e FROM Cargo + e WHERE e.nomeCargo= :id").setParameter("id", id).getResultList();
    }

    public List<Cargo> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cargo e ORDER BY e.nomeCargo").getResultList();
    }

    public List<Cargo> listInOrderId() {
        return em.createQuery("SELECT e FROM Cargo e ORDER BY e.idCargo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cargo> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdCargo() + "-" + lf.get(i).getNomeCargo());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOCargo daoCargo = new DAOCargo();
        List<Cargo> listaCargo = daoCargo.list();
        for (Cargo cargo : listaCargo) {
            System.out.println(cargo.getIdCargo()+"-"+cargo.getNomeCargo());
        }
    }}
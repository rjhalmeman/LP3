package DAOs;

import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

private final List<PrecoProduto> lista = new ArrayList<>();    public DAOPrecoProduto(){
        super(PrecoProduto.class);
    }


    //esse método foi criado para que a pesquisa pudesse ser feita em uma chave primária composta por 2 atributos
    public PrecoProduto obter(PrecoProdutoPK precoProdutoPK) {
        return em.find(PrecoProduto.class, precoProdutoPK);
    }

    public List<PrecoProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.precoProduto LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProduto + e WHERE e.produto= :id").setParameter("id", id).getResultList();
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.produto").getResultList();
    }

    public List<PrecoProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.precoProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PrecoProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProduto() + "-" + lf.get(i).getProduto());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
        for (PrecoProduto precoProduto : listaPrecoProduto) {
            System.out.println(precoProduto.getPrecoProduto()+"-"+precoProduto.getProduto());
        }
    }}
package daos;

import entidades.TipoLancamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;



public class DAOTipoLancamento extends DAOGenerico<TipoLancamento> {

private List<TipoLancamento> lista = new ArrayList<>();    public DAOTipoLancamento(){
        super(TipoLancamento.class);
    }

    public int autoIdTipoLancamento() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoLancamento) FROM TipoLancamento) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoLancamento> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoLancamento e WHERE e.nomeTipoLancamento) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoLancamento> listById(int id) {
        return em.createQuery("SELECT e FROM TipoLancamento + e WHERE e.idTipoLancamento= :id").setParameter("id", id).getResultList();
    }

    public List<TipoLancamento> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoLancamento e ORDER BY e.nomeTipoLancamento").getResultList();
    }

    public List<TipoLancamento> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoLancamento e ORDER BY e.idTipoLancamento").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoLancamento> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipo()+ "-" + lf.get(i).getNomeTipo());
        }
        return ls;
    }



public String converteDateEmString(Date dataDeNascimento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = null;
        try {
            dt = sdf.format(dataDeNascimento);
        } catch (Exception err) {
            System.out.println("Erro na data.");
        }
        return dt;
    }

    public Date converteStringEmDate(String dataDeNascimento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = sdf.parse(dataDeNascimento);
        } catch (Exception err) {
            System.out.println("Erro na data.");
        }

        return dt;
    }
public void salvarArquivo(String arquivo) {

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaSTR = new ArrayList<>();
        for (TipoLancamento linha : lista) {
            listaSTR.add(linha.toString());
        }
        manipulaArquivo.salvarArquivo(arquivo, listaSTR);
    }}

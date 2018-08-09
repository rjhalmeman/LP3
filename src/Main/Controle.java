package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    List<Contato> lista = new ArrayList<>();

    public Controle() {
   
    }

    public Contato buscar(int chave) {
        for (int i = 0; i < lista.size(); i++) {
            if (chave==lista.get(i).getId()) {
                return lista.get(i);//se encontrou, retorna a linha toda (um contato)
            }
        }
        return null; //se não encontrou na lista, retorna um contato nulo
    }

    public void inserir(Contato contato) {
        lista.add(contato);
    }

    void alterar(Contato contatoOriginal, Contato contatoAlterado) {
        lista.set(lista.indexOf(contatoOriginal), contatoAlterado);
    }

    public List<String> listar() {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            ls.add(""
                    + lista.get(i).getId() + ";"
                    + lista.get(i).getNome() + ";"
                    + lista.get(i).getEndereco()
            );
        }
        return ls;
    }
    
    public void excluir(Contato contato){
        lista.remove(contato);
    }

}
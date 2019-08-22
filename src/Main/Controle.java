package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    List<Trabalhador> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 
      
    }

    public void adicionar(Trabalhador trabalhador) {
        lista.add(trabalhador);
    }

    public List<Trabalhador> listar() {
        return lista;
    }

    public Trabalhador buscar(String cpf) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCpf().equals(cpf)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Trabalhador trabalhador, Trabalhador trabalhadorAntigo) {
        lista.set(lista.indexOf(trabalhadorAntigo), trabalhador);

    }

    public void excluir(Trabalhador trabalhador) {
        lista.remove(trabalhador);
    }

}
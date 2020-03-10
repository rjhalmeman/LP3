
package Main;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<Pessoa> listaPessoa = daoPessoa.list();
        for (int i = 0; i < listaPessoa.size(); i++) {
      System.out.println(listaPessoa.get(i).getIdPessoa()+"-"+
                    listaPessoa.get(i).getNomePessoa());
        }
        Pessoa pessoa = new Pessoa(); 
        pessoa.setIdPessoa(4332);
        pessoa.setNomePessoa("Ultimo exemplo de hoje");
        pessoa.setDataNascimentoPessoa(new Date());
        
        daoPessoa.inserir(pessoa);
        
    }
    
}

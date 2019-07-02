package Main;

import DAOs.DAOPrecoProduto;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radames
 */
public class TestesComEntidadePk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrecoProduto precoProduto = new PrecoProduto();
        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
        
        precoProdutoPK.setProdutoIdProduto(2);
        try {
            precoProdutoPK.setDataPrecoProduto(new SimpleDateFormat("dd/MM/yyyy").parse("02/07/2019"));
        } catch (ParseException ex) {
            Logger.getLogger(TestesComEntidadePk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        precoProduto.setPrecoProdutoPK(precoProdutoPK);
       // precoProduto.setPrecoProduto(5.00);
        
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
       // daoPrecoProduto.inserir(precoProduto);
        
        precoProduto = daoPrecoProduto.obter(precoProdutoPK);
        if (precoProduto!=null) {
            System.out.println("O preco da "
                    +precoProduto.getProduto().getNomeProduto()+
                    " custa "+ precoProduto.getPrecoProduto());
        }
        
        
        
        
        
    }
    
}

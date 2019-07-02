package DAOs;

import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProdutoPK extends DAOGenerico<PrecoProdutoPK> {

    private final List<PrecoProdutoPK> lista = new ArrayList<>();

    public DAOPrecoProdutoPK() {
        super(PrecoProdutoPK.class);
    }
    
}

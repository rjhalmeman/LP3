package beans;

/**
 *
 * @author radames
 */
public class BeanPessoa {

    private int id;
    private String nome;

    public BeanPessoa() {
    }

    public BeanPessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BeanPessoa{" + "id=" + id + ", nome=" + nome + '}';
    }
    
    
    
}

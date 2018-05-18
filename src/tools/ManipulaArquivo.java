package tools;

// @author Radames
import entidades.ContaBco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ManipulaArquivo {

    public ManipulaArquivo() {
    }

    public ArrayList<ContaBco> leCSV(String caminho) {
        BufferedReader in;
        ArrayList<ContaBco> lista = new ArrayList<>();
        try {
            String linha;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "ISO-8859-1"));
            // in = new BufferedReader(new FileReader(caminho));
            String aux;
            if ((linha = in.readLine()) != null) {
            }

            while ((linha = in.readLine()) != null) {
                String conteudo[] = linha.split(",");
                ContaBco c = new ContaBco();
                for (int i = 0; i < conteudo.length; i++) {
                    switch (i) {
                        case 0:
                            aux = conteudo[i].trim();
                            aux = aux.substring(4, 6) + "/" + aux.substring(1, 3) + "/" + aux.substring(7, 11);
                            c.setData(aux);
                            break;
                        case 1:
                            aux = conteudo[i].trim();
                            c.setAg(conteudo[i]);
                            break;
                        case 2:

                            c.setDescricao(conteudo[i].substring(1, conteudo[i].length() - 1));

                            break;
                        case 3:
                            c.setDoc(conteudo[i]);

                            break;
                        case 4:
                            c.setCod(conteudo[i]);

                            break;
                        case 5:
                            aux = conteudo[i].substring(1, conteudo[i].length() - 1);
                            aux = aux.trim();
                            String a2 = "";
                            for (int j = 0; j < aux.length(); j++) {
                                if (aux.substring(j, j + 1).equals(".")) {
                                    a2 += ",";
                                } else {
                                    a2 += aux.substring(j, j + 1);
                                }
                            }
                            aux = a2;
                            if (aux.substring(0, 1).equals("-")) {
                                c.setOperacao("D");
                                aux = aux.substring(1);
                            } else {
                                c.setOperacao("C");
                            }
                            c.setValor(aux);

                            break;
                    }
                }
                lista.add(c);
//                for (int i = 0; i < lista.size(); i++) {
//                    System.out.println(lista.get(i).getData() + " - " + lista.get(i).getDescricao());
//
//                }

            }
            return lista;
        } catch (Exception ex) {
            System.out.println("Erro...");
            return null;
        }
    }

    public List<String> abrirArquivo(String caminho) {
        List<String> texto = new ArrayList<String>();
        try {
            //OpenFile
            FileReader arquivo = new FileReader(caminho);
            BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
            String linha = conteudoDoArquivo.readLine();
            while (linha != null) {
                texto.add(linha);
                linha = conteudoDoArquivo.readLine();
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            texto = null;
            System.err.println("Erro: " + e.getMessage());
        }
        return texto;
    }

    public int salvarArquivo(String caminho, List<String> texto) {
        try {
            // Create file 
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i));//+ System.getProperty("line.separator")); // 
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return 1; //houve erro
        }
        return 0;
    }
}

package myUtil;

// @author Radames
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ManipulaArquivo {

    public ManipulaArquivo() {
    }

    public List<String> abrirArquivo(String caminho) {
        List<String> texto = new ArrayList<String>();
        try {
            //OpenFile
            File arquivo = new File(caminho);
            BufferedReader conteudoDoArquivo = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "cp5348"));
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
            FileOutputStream file = new FileOutputStream(new File(caminho));
            BufferedWriter conteudoDoArquivo = new BufferedWriter(new OutputStreamWriter(file,"UTF-8"));
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i) + System.getProperty("line.separator")); // 
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return 1; //houve erro
        }
        return 0;
    }
}

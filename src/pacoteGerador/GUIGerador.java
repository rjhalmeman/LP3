package pacoteGerador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUIGerador extends JFrame {

    ManipulaArquivo file = new ManipulaArquivo();
    List<String> ultimaClasse = new ArrayList<String>();
    JLabel processamento = new JLabel("");
    JLabel labelArquivodeorigem = new JLabel("Arquivo de origem");
    JTextField textFieldArquivodeorigem = new JTextField(50);
    JPanel aviso = new JPanel();
    Container cp;
    JPanel centro = new JPanel();
    JButton btnGerarClassePrincipal = new JButton("Classe de Entidade");
    JButton btnGerarClasseGUI = new JButton("Classe GUI");
    JButton btnClasseDeControle = new JButton("Classe de controle");
    JButton btnClasseGUIListagem = new JButton("Classe GUI Listagem");
    JPanel sul = new JPanel();

    public GUIGerador() {
        setTitle("Gerador");
        setSize(600, 400);//tamanho da janela
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        cp = getContentPane();//container principal, para adicionar nele os outros componentes

        centro.setLayout(new GridLayout(3, 1));

        centro.add(labelArquivodeorigem);
        centro.add(textFieldArquivodeorigem);

        aviso.setLayout(new FlowLayout());
        aviso.add(processamento);

        centro.add(aviso);

        sul.setLayout(new FlowLayout());
        sul.add(btnGerarClassePrincipal);

        sul.add(btnClasseDeControle);
        sul.add(btnGerarClasseGUI);
        sul.add(btnClasseGUIListagem);
        cp.add(sul, BorderLayout.SOUTH);
        cp.add(centro, BorderLayout.CENTER);

        //carregar ultimaClasseGerada
        //busca em um arquivo texto, a última classe que foi gerada
        ultimaClasse = file.abrirArquivo("src/pacoteGerador/ultimaClasseGerada");
        textFieldArquivodeorigem.setText(ultimaClasse.get(0));

        pack();
        // setLocationRelativeTo(this); // posiciona no centro da tela principal
        setVisible(true);//faz a janela ficar visível

        //listeners
        textFieldArquivodeorigem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                aviso.setBackground(Color.CYAN);
                processamento.setText(" Digite o nome da classe que será gerada... ");
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ultimaClasse.clear();
                if (!textFieldArquivodeorigem.getText().equals("")) {
                    ultimaClasse.add(textFieldArquivodeorigem.getText());
                    file.salvarArquivo("src/pacoteGerador/ultimaClasseGerada", ultimaClasse);
                }
                // Sai do sistema  
                System.exit(0);
            }
        });

        btnGerarClassePrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Gerador g = new Gerador(textFieldArquivodeorigem.getText());
                g.gerarClasseDeEntidade();
            }
        });

        btnGerarClasseGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Gerador g = new Gerador(textFieldArquivodeorigem.getText());
                g.GerarGui();
                aviso.setBackground(Color.GREEN);
                processamento.setText(" Processamento concluído ");

            }
        });

        btnClasseDeControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gerador g = new Gerador(textFieldArquivodeorigem.getText());
                g.gerarClasseDeControle();
                aviso.setBackground(Color.GREEN);
                processamento.setText(" Processamento concluído ");
            }
        });
        btnClasseGUIListagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gerador g = new Gerador(textFieldArquivodeorigem.getText());
                g.gerarGUIListagem();
                aviso.setBackground(Color.GREEN);
                processamento.setText(" Processamento concluído ");
            }
        });
    }
}

package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.CentroDoMonitorMaior;

public class MenuPrincipal extends JFrame {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JLabel lbTitulo = new JLabel("Gerenciador Financeiro");

    Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    JLabel labelComImagemDeTamanhoDiferente = new JLabel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menuCadastros = new JMenu("Cadastros");
    JMenuItem cadConta = new JMenuItem("Conta");
    JMenuItem cadTipoConta = new JMenuItem("Tipo Conta");
   

  

     Point p;

    public MenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle(lbTitulo.getText());

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);

        //para ajustar o tamanho de uma imagem
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/imagens/DER_controle_contas.png"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance((int) dimensao.getWidth()-20, (int) dimensao.getHeight()-80, Image.SCALE_FAST));

            labelComImagemDeTamanhoDiferente = new JLabel();
            labelComImagemDeTamanhoDiferente.setIcon(icone);
        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }

        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);

        menuCadastros.add(cadConta);
        menuCadastros.add(cadTipoConta);
       

        cadConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new ContaGUI_JTable();
            }
        });
        cadTipoConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new TipoContaGUI_JTable();
            }
        });
          

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                System.exit(0);
            }
        });

        
       // pack();
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    }
}

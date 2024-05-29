package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myUtil.CentroDoMonitorMaior;

public class MenuPrincipalGUI extends JFrame {

    private final Container cp;
    public Point p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
    private final JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private final JLabel lbTitulo = new JLabel("");
    private final Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel imagemCentral = new JLabel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuCadastros = new JMenu("Cadastros");
    private final JMenu menuOutros = new JMenu("Outros");
//------------------------ Itens do Menu ----------------------------
    private final JMenuItem crudGUIUnidadeDeMedida = new JMenuItem("UnidadeDeMedida");
    private final JMenuItem crudGUICargo = new JMenuItem("Cargo");
    private final JMenuItem crudGUIPessoa = new JMenuItem("Pessoa");
    private final JMenuItem crudGUIProduto = new JMenuItem("Produto");

    private final JMenuItem mostrarDER = new JMenuItem("Mostrar DER");
    
    boolean qualImagem = false;

    public MenuPrincipalGUI(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        String titulo = "LojaBase - Acesso Direto ao DB - 2024";
        lbTitulo.setText(titulo);
        setTitle(titulo);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);

        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudGUIUnidadeDeMedida);
        menuCadastros.add(crudGUICargo);
        menuCadastros.add(crudGUIPessoa);
        menuCadastros.add(crudGUIProduto);
        menuBar.add(menuOutros);
        menuOutros.add(mostrarDER);

        //ImagemComTamanhoAjustado ita = new ImagemComTamanhoAjustado();
        //imagemCentral.setIcon(ita.getImagem(1300, 1000, "/DER/LojaBasica.png"));
        //imagem original, não redimensionada
        pnCentro.add(imagemCentral);

        

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/DER_Dump/techM.png"));
        imagemCentral.setIcon(imageIcon);
        int largura = imagemCentral.getIcon().getIconWidth();
        int altura = imagemCentral.getIcon().getIconHeight();
        pnCentro.setSize(new Dimension(largura, altura));
        pack();

        imagemCentral.setVisible(true);

        mostrarDER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qualImagem = !qualImagem;
                if (qualImagem) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/DER_Dump/LojaBasica.png"));
                    imagemCentral.setIcon(imageIcon);
                    int largura = imagemCentral.getIcon().getIconWidth();
                    int altura = imagemCentral.getIcon().getIconHeight();
                    pnCentro.setSize(new Dimension(largura, altura));

                } else {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/DER_Dump/techM.png"));
                    imagemCentral.setIcon(imageIcon);
                    int largura = imagemCentral.getIcon().getIconWidth();
                    int altura = imagemCentral.getIcon().getIconHeight();
                    pnCentro.setSize(new Dimension(largura, altura));
                  
                }

                pack();
                setLocation(p);
            }
        });

        crudGUIUnidadeDeMedida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnidadeDeMedidaGUI unidadeDeMedidaGui = new UnidadeDeMedidaGUI();
            }
        });

        crudGUICargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargoGui cargoGui = new CargoGui();
            }
        });

        crudGUIPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PessoaGUI pessoaGUI = new PessoaGUI();
            }
        });
        crudGUIProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoGUI produtoGUI = new ProdutoGUI();
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        // pack();
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new MenuPrincipalGUI(new Dimension(800, 600));
    }
}

package GUIs;

import myUtil.ImagemComTamanhoAjustado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel imagemCentral = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
    private JMenu menuOutros = new JMenu("Outros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudGUIUnidadeDeMedida = new JMenuItem("UnidadeDeMedida");
    private JMenuItem crudGUICargo = new JMenuItem("Cargo");
    private JMenuItem crudGUIPessoa = new JMenuItem("Pessoa");
    private JMenuItem crudGUIProduto = new JMenuItem("Produto");

    private JMenuItem mostrarDER = new JMenuItem("Mostrar DER");

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

        ImagemComTamanhoAjustado ita = new ImagemComTamanhoAjustado();
        imagemCentral.setIcon(ita.getImagem(1300, 1000, "/DER/LojaBasica.png"));

        pnCentro.add(imagemCentral);

        imagemCentral.setVisible(false);

        mostrarDER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(1300, 1000);
                imagemCentral.setVisible(!imagemCentral.isVisible());
                setLocationRelativeTo(null);
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

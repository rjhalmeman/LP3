package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myUtil.CentroDoMonitorMaior;

public class GUIMenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("LP3_2018_JPA_Sistema_Vendas");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudStatus = new JMenuItem("Status");
    private JMenuItem crudPrecoProduto = new JMenuItem("PrecoProduto");
    private JMenuItem crudProduto = new JMenuItem("Produto");

    public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("LP3_2018_JPA_Sistema_Vendas");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudStatus);
        menuCadastros.add(crudPrecoProduto);
        menuCadastros.add(crudProduto);
       
        
        crudStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIStatus guiStatus = new GUIStatus(p, dimensao);
            }
        });

        crudPrecoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("dimensao "+dimensao.getWidth()+"-"+dimensao.getHeight());
                GUIPrecoProdutoPK guiPrecoProduto = new GUIPrecoProdutoPK(getLocationOnScreen(), dimensao);
            }
        });

        crudProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProduto guiProduto = new GUIProduto(p, dimensao);
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUIMenuPrincipal(new Dimension(800, 600));
    }
}

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
import myUtil.CentroDoMonitorMaior;public class GUIMenuPrincipal extends JFrame {
    private Container cp;
    private Point p;    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("LP3_JPA_Exemplo_Produto_Status_1toN");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar(); private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
private JMenuItem crudProduto = new JMenuItem("Produto");
private JMenuItem crudStatus = new JMenuItem("Status");
public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("LP3_JPA_Exemplo_Produto_Status_1toN");

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
menuCadastros.add(crudProduto);
menuCadastros.add(crudStatus);
    crudProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProduto guiProduto = new GUIProduto(p, dimensao);
            }
        });

    crudStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIStatus guiStatus = new GUIStatus(p, dimensao);
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);} //fecha o contrutor
    public static void main(String[] args) {
        new GUIMenuPrincipal(new Dimension(800, 600));
    }
}
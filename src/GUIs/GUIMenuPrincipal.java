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
import javax.swing.ImageIcon;
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
    private JLabel lbTitulo = new JLabel("Loja_Zero_v02 - Radames");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudGUIStatusJTable = new JMenuItem("StatusJTable");
    private JMenuItem crudGUIProduto = new JMenuItem("Produto");
    private JMenuItem crudGUIUnidadeDeMedida = new JMenuItem("UnidadeDeMedida");
    private JMenuItem crudGUIPrecoProduto = new JMenuItem("Preço do produto com entidade PK");

    public GUIMenuPrincipal(Dimension dimensao) {
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
            ImageIcon icone = new ImageIcon(getClass().getResource("/icones/DER_loja_zero.png"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(800, 800, Image.SCALE_FAST));

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
        menuCadastros.add(crudGUIStatusJTable);
        menuCadastros.add(crudGUIProduto);
        menuCadastros.add(crudGUIUnidadeDeMedida);
        menuCadastros.add(crudGUIPrecoProduto);
        crudGUIStatusJTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIStatusJTable crudGUIStatusJTable = new GUIStatusJTable(p, dimensao);
            }
        });

        crudGUIProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProduto crudGUIProduto = new GUIProduto(p, dimensao);
            }
        });

        crudGUIUnidadeDeMedida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIUnidadeDeMedida crudGUIUnidadeDeMedida = new GUIUnidadeDeMedida(p, dimensao);
            }
        });
        crudGUIPrecoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPrecoProduto crudPP = new GUIPrecoProduto(p, dimensao);
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

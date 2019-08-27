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
    private JLabel lbTitulo = new JLabel("SistemaVendas_Exemplo_MtoN");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudGUIStatusJTable = new JMenuItem("StatusJTable");
    private JMenuItem crudGUIProduto = new JMenuItem("Produto");
    private JMenuItem crudGUICliente = new JMenuItem("Cliente");
    private JMenuItem crudGUIPedido = new JMenuItem("Pedido");
    private JMenuItem crudGUIFornecedorJTable = new JMenuItem("FornecedorJTable");

    public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle(lbTitulo.getText());

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
        menuCadastros.add(crudGUIStatusJTable);
        menuCadastros.add(crudGUIProduto);
        menuCadastros.add(crudGUICliente);
        menuCadastros.add(crudGUIPedido);
        menuCadastros.add(crudGUIFornecedorJTable);
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

        crudGUICliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICliente crudGUICliente = new GUICliente(p, dimensao);
            }
        });

        crudGUIPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPedido crudGUIPedido = new GUIPedido(p, dimensao);
            }
        });

        crudGUIFornecedorJTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFornecedorJTable crudGUIFornecedorJTable = new GUIFornecedorJTable(p, dimensao);
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

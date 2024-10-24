package Main;

import GUIs.CategoriaGUI;
import GUIs.PedidoGUI;
import GUIs.PessoaGUI;
import GUIs.PrecoProdutoGUI;
import GUIs.ProdutoGUI;
//import GUIs.ViewClientesGUI;
//import GUIs.ViewClienteGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author radames
 */
public class Menu_GUI extends JDialog {

    Container cp;

    JPanel pn1 = new JPanel();
    JMenuBar menuBar = new JMenuBar();

    JButton btPessoa = new JButton("<html>Pessoa</html>");
    JButton btCategoria = new JButton("<html>Categoria</html>");
    JButton btPedido = new JButton("Pedido");
    JButton btProduto = new JButton("<html>Produto</html>");
    JButton btPrecoProduto = new JButton("<html>Preço Produtos</html>");
    JButton btViewCardapio = new JButton("<html>Visualizar</html> ");

    JMenu cadastrosMenu = new JMenu("   Cadastros   ");
    JMenu pedidoMenu = new JMenu("     Pedido     ");
    JMenu cardapioMenu = new JMenu("   Cardápio   ");

    ImageIcon img = new ImageIcon("src/icones/coffe_shop.png");
//    Image imagemMenor = coffe.getScaledInstance(600, 835, Image.SCALE_SMOOTH);
    JLabel lbImagem = new JLabel(img);

    public Menu_GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CAFETERIA");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pn1.setLayout(new GridLayout(2, 1));

        cadastrosMenu.add(btPessoa);

        cardapioMenu.add(btProduto);
        cardapioMenu.add(btPrecoProduto);
        cardapioMenu.add(btCategoria);

        pedidoMenu.add(btPedido);

        cadastrosMenu.setIcon(new ImageIcon(("src/icones/cadastro.png")));
        pedidoMenu.setIcon(new ImageIcon(("src/icones/pedido.png")));
        cardapioMenu.setIcon(new ImageIcon(("src/icones/cardapio.png")));

        menuBar.add(cadastrosMenu);
        menuBar.add(pedidoMenu);
        menuBar.add(cardapioMenu);

        cp.add(lbImagem, BorderLayout.CENTER);
        cp.add(menuBar, BorderLayout.NORTH);

        cadastrosMenu.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
        pedidoMenu.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
        cardapioMenu.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
        btPessoa.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
        btCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
        btPedido.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
        btProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
        btViewCardapio.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
        btPrecoProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));

        cadastrosMenu.setBorder(BorderFactory.createLineBorder(Color.black));
        pedidoMenu.setBorder(BorderFactory.createLineBorder(Color.black));
        cardapioMenu.setBorder(BorderFactory.createLineBorder(Color.black));

        cp.setBackground(new Color(221, 184, 146));
        menuBar.setBackground(new Color(221, 184, 146));
        btPessoa.setBackground(new Color(0, 0, 0));
        btPessoa.setForeground(Color.WHITE);
        btPedido.setBackground(new Color(0, 0, 0));
        btPedido.setForeground(Color.WHITE);
        btPrecoProduto.setBackground(new Color(0, 0, 0));
        btPrecoProduto.setForeground(Color.WHITE);
        btProduto.setBackground(new Color(0, 0, 0));
        btCategoria.setBackground(new Color(0, 0, 0));
        btProduto.setForeground(Color.WHITE);
        btCategoria.setForeground(Color.WHITE);
        btViewCardapio.setBackground(new Color(0, 0, 0));
        btViewCardapio.setForeground(Color.WHITE);

        btPessoa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                PessoaGUI pessoaGUI = new PessoaGUI();
            }
        }
        );

        btPedido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                PedidoGUI pedidoGUI = new PedidoGUI();
            }
        }
        );
        btPrecoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                PrecoProdutoGUI precoProdutoGUI = new PrecoProdutoGUI();
            }
        }
        );
        btCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                CategoriaGUI categoriaGUI = new CategoriaGUI();
            }
        }
        );

        btProduto.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                ProdutoGUI itensCardapioGUI = new ProdutoGUI();
            }
        }
        );
        pack();
        setLocationRelativeTo(
                null);
        setVisible(
                true);

    }

}

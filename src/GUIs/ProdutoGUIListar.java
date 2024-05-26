package GUIs;

import DAOs.DAOProduto;
import Entidades.Produto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radames
 */
class ProdutoGUIListar extends JDialog {

    Container cp;
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    
    JButton btSair = new JButton("Sair");

    String[] colunas = {"Id", "Nome", "Quantidade", "Unid.Medida"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    JScrollPane scrollTabela = new JScrollPane();
    private String idSelecionado;

    public ProdutoGUIListar(DAOProduto daoProduto, Point coordenadas, Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        //  setTitle("Relatório de produto");

        setUndecorated(true);//sem barra de título
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        
        pnCentro.setLayout(new GridLayout(1, 1));

        pnCentro.add(scrollTabela);
        pnSul.add(btSair);
        
        List<Produto> listaDados = daoProduto.listar();
//        for (Produto listaDado : listaDados) {
//            System.out.println(listaDado);
//        }
//        
//        System.exit(0);

        if (!listaDados.isEmpty()) {
            Object[][] dados = new Object[listaDados.size()][colunas.length];
            String aux[];
           
            for (int i = 0; i < listaDados.size(); i++) {
                aux = listaDados.get(i).toString().split(";");
                for (int j = 0; j < colunas.length; j++) {
                    dados[i][j] = aux[j];
                }
            }
            scrollTabela.setPreferredSize(tabela.getPreferredSize());

            scrollTabela.setViewportView(tabela);
            model.setDataVector(dados, colunas);
        }
        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow != -1) {
                    Object value = tabela.getValueAt(selectedRow, 0);
                    idSelecionado = String.valueOf(value);
                    dispose();
                }
            }
        });

        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(dimensao);
        setLocation((int) coordenadas.getX(), (int) coordenadas.getY() + 30);

//        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String getIdSelecionado() {
        return idSelecionado;
    }

}

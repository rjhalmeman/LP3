package GUIs;

import tools.CentroDoMonitorMaior;
import daos.DAOCidade;
import daos.DAOEstado;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {

    private final Container cp;
    private final JPanel pnNorte = new JPanel(new GridLayout(1, 1));
    private final JPanel pnCentro = new JPanel(new GridLayout(4, 1));
    private final JPanel pnSul = new JPanel(new GridLayout(3, 1));
    JComboBox cbEstado = new JComboBox();
    JComboBox cbCidade = new JComboBox();

    public GUI() {
        setSize(800, 170);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Exemplo Combobox com BD");

        pnNorte.add(new JLabel("Escolha o estado"));

        pnCentro.add(cbEstado);
        pnCentro.add(new JLabel(""));
        pnCentro.add(new JLabel(""));
        pnCentro.add(cbCidade);

        pnSul.add(new JLabel(" "));
        pnSul.add(new JLabel(" "));
        pnSul.add(new JLabel(" "));

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        DAOEstado daoEstado = new DAOEstado();
        String[] listaEstado = daoEstado.listInOrderNomeStringsArray();
        for (String s : listaEstado) {
            cbEstado.addItem(s);
        }

        cbEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] aux = listaEstado[cbEstado.getSelectedIndex()].split("-");

               //System.out.println("selecionado=> " + aux[0]);
                DAOCidade daoCidade = new DAOCidade();
                String[] listaCidade = daoCidade.listInOrderNomeStringsArray(aux[0]);
                cbCidade.removeAllItems();
                for (String s : listaCidade) {
                    cbCidade.addItem(s);
                }
            }
        }
        );

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }
}

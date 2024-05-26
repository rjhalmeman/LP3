package GUIs;

import DAOs.DAOCargo;
import Entidades.Cargo;
import Main.CaixaDeFerramentas;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JToolBar;


/**
 *
 * @author radames
 */
public class CargoGui extends JDialog {

    //variáreis globais
    //carregar imagens dos icones
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    
    
    JLabel lbIdCargo = new JLabel("IdCargo");
    JTextField tfIdCargo = new JTextField(15);



    DAOCargo daoCargo = new DAOCargo();
    Cargo cargo = new Cargo();
    JLabel lbAviso = new JLabel("xxxx");

    JLabel lbNomeCargo = new JLabel("Nome");
    JTextField tfNomeCargo = new JTextField(40);
   
    
    JButton btBuscar = new JButton(iconeRetrieve);
    JButton btAdicionar = new JButton(iconeCreate);
    JButton btSalvar = new JButton(iconeSave);
    JButton btAlterar = new JButton(iconeUpdate);
    JButton btExcluir = new JButton(iconeDelete);
    JButton btListar = new JButton(iconeListar);
    JButton btCancelar = new JButton(iconeCancel);

    String acao;
   
    CaixaDeFerramentas cf = new CaixaDeFerramentas();
    JToolBar jToolbar = new JToolBar();

    public CargoGui() {
        
        //componentes visuais
        setTitle("CRUD Cargo - acesso direto ao BD - 2024");
        cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.DARK_GRAY);

        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));
        pnNorte.add(jToolbar);
        jToolbar.add(lbIdCargo);
        jToolbar.add(tfIdCargo);
        jToolbar.add(btBuscar);
        jToolbar.add(btAdicionar);
        jToolbar.add(btAlterar);
        jToolbar.add(btExcluir);
        jToolbar.add(btListar);
        jToolbar.add(btSalvar);
        jToolbar.add(btCancelar);

        btBuscar.setToolTipText("Buscar");
        btAdicionar.setToolTipText("Adicionar novo registro");
        btAlterar.setToolTipText("Alterar um registro");
        btExcluir.setToolTipText("Excluir um registro");
        btListar.setToolTipText("Listagem");
        btSalvar.setToolTipText("Salvar dados do registro");
        btCancelar.setToolTipText("Cancelar edição (sair sem salvar)");

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNomeCargo);
        pnCentro.add(tfNomeCargo);
       
        pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);
        tfIdCargo.setEditable(true);
        tfNomeCargo.setEditable(false);
      

        lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);

        //listeners
        tfIdCargo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um IdCargo");
                tfIdCargo.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfIdCargo.setBackground(Color.white);
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfIdCargo.getText().isEmpty()) {
                    tfIdCargo.requestFocus();
                } else {
                    cargo = daoCargo.obter(tfIdCargo.getText(),"idCargo");
                    
                    //daoCargo.obter("222","idCargo");
                    if (cargo == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

                        tfNomeCargo.setText("");
                      
                    } else {//encontra na lista
                        tfIdCargo.setText(String.valueOf(cargo.getIdCargo()));
                        tfNomeCargo.setText(cargo.getNomeCargo());
                       
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btListar.setVisible(false);
                        lbAviso.setText("Encontrou o registro");
                        
                        //ajustar o combobox
                            
                        
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                tfIdCargo.setEditable(false);
                tfNomeCargo.setEditable(true);
              
                tfNomeCargo.requestFocus();
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionando";
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfNomeCargo.requestFocus();
                tfIdCargo.setEditable(false);
                tfNomeCargo.setEditable(true);
                
                btAlterar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btExcluir.setVisible(false);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "alterando";
                lbAviso.setText("Alterando o registro");
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuErro = false;
                
                if (acao.equals("adicionando")) {
                    cargo = new Cargo();
                }

                cargo.setIdCargo(Integer.parseInt(tfIdCargo.getText()));
                cargo.setNomeCargo(tfNomeCargo.getText());
                
                
               
                if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoCargo.inserir(cargo);
                        lbAviso.setText("Inseriu o registro");
                    } else {
                        daoCargo.atualizar(cargo,"idCargo",cargo.getIdCargo());
                        lbAviso.setText("Alterou o registro");
                    }

                 

                    tfIdCargo.setText("");
                    tfNomeCargo.setText("");
                  
                    tfIdCargo.requestFocus();
                    tfIdCargo.setEditable(true);
                    tfNomeCargo.setEditable(false);
                   
                    btBuscar.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btListar.setVisible(true);
                }
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfIdCargo.setText("");
                tfNomeCargo.setText("");
               
                tfIdCargo.requestFocus();
                tfIdCargo.setEditable(true);
                tfNomeCargo.setEditable(false);
                
                btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                lbAviso.setText("");
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.
                        showConfirmDialog(cp, "Confirma a exclusão?", "Excluindo", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                if (opcao == JOptionPane.YES_NO_OPTION) {
                    daoCargo.excluir(cargo.getIdCargo(), "idCargo");
                }
                tfIdCargo.setText("");
                tfNomeCargo.setText("");
               
                tfIdCargo.requestFocus();
                tfIdCargo.setEditable(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                lbAviso.setText("");
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbAviso.setText("Relatório");
                Point coordenadas = getLocation();//pega as coordenadas da guiPai
                Dimension dimensao = getSize();
                String idSelecionado
                        = new CargoGUIListar(daoCargo, coordenadas, dimensao).getIdSelecionado();
                tfIdCargo.setText(idSelecionado);
                btBuscar.doClick();
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                dispose();

            }
        });

        setSize(800, 200);
       // pack();
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
    public static void main(String[] args) {
        CargoGui cargoGui = new CargoGui();
    }
}

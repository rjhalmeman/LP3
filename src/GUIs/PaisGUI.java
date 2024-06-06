package GUIs;

import DAOs.DAOPais;
import Entidades.Pais;
import Main.CaixaDeFerramentas;
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
import myUtil.CentroDoMonitorMaior;


/**
 *
 * @author radames
 */public class PaisGUI extends JDialog { //variáreis globais
    
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
    JPanel pnSul = new JPanel();DAOPais daoPais = new DAOPais();
    Pais pais = new Pais();JLabel lbIdPais = new JLabel("IdPais");JTextField tfIdPais = new JTextField(10);
JLabel lbNomePais = new JLabel("NomePais");JTextField tfNomePais = new JTextField(50);
JLabel lbSiglaPais = new JLabel("SiglaPais");JTextField tfSiglaPais = new JTextField(3);
JLabel lbAviso = new JLabel("");
    
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

public PaisGUI() {
        
        //componentes visuais
        setTitle("CRUD Pais");
 cp = getContentPane();

        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.setBackground(Color.white);
        pnSul.setBackground(Color.DARK_GRAY);

        pnNorte.setLayout(new FlowLayout((int) LEFT_ALIGNMENT));
        pnNorte.add(jToolbar);  jToolbar.add(lbIdPais);
        jToolbar.add(tfIdPais);jToolbar.add(btBuscar);
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
        btCancelar.setToolTipText("Cancelar edição (sair sem salvar)");pnCentro.setLayout(new GridLayout(3, 2));pnCentro.add(lbNomePais);pnCentro.add(tfNomePais);pnCentro.add(lbSiglaPais);pnCentro.add(tfSiglaPais);pnSul.add(lbAviso);

        //status inicial
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btListar.setVisible(true);tfIdPais.setEditable(true);tfNomePais.setEditable(false);tfSiglaPais.setEditable(false); lbAviso.setOpaque(true);
        lbAviso.setBackground(Color.BLACK);
        // Definir a cor da fonte como branca
        lbAviso.setForeground(Color.WHITE);

        // Definir a fonte em negrito
        Font fonte = lbAviso.getFont();
        Font fonteNegrito = new Font(fonte.getFontName(), Font.BOLD, fonte.getSize());
        lbAviso.setFont(fonteNegrito);
//Listeners .............................................................
  tfIdPais.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                lbAviso.setText("Digite um IdPais");
                tfIdPais.setBackground(Color.green);
                btAdicionar.setVisible(false);
                btAlterar.setVisible(false);

                btExcluir.setVisible(false);
                if (!btSalvar.isVisible()) {
                    btListar.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfIdPais.setBackground(Color.white);
            }
        });
 ////////////    buscar      ////////////

btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tfIdPais.getText().isEmpty()) {
                    tfIdPais.requestFocus();
                } else if (tfIdPais.getText().length() > tfIdPais.getColumns()) {
                    tfIdPais.requestFocus();
                    tfIdPais.selectAll();
                    JOptionPane.showMessageDialog(cp,"Excede a quantidade máxima de caracteres. Máximo = "+ tfIdPais.getColumns());
                } else {                    pais = daoPais.obter(tfIdPais.getText(),"IdPais");
                    
                    if (pais == null) {//não achou na lista
                        lbAviso.setText("Não achou na lista");
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);

tfNomePais.setText("");tfSiglaPais.setText("");                    } else {//encontra na lista
tfIdPais.setText(String.valueOf(pais.getIdPais()));tfNomePais.setText(pais.getNomePais());tfSiglaPais.setText(pais.getSiglaPais());btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btListar.setVisible(false);
                        lbAviso.setText("Encontrou o registro");
                        
                        //ajustar o combobox
                            
                        
                    }
                }
            }
        });
 ////////////    adicionar      ////////////

 btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfIdPais.setEditable(false);
                tfNomePais.setEditable(true);              
                tfNomePais.requestFocus();
 tfNomePais.setText("");
tfNomePais.setEditable(true);
 tfSiglaPais.setText("");
tfSiglaPais.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btExcluir.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionando";
            }
        });
 ////////////    alterar      ////////////

 btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfIdPais.setEditable(false);
                tfSiglaPais.setEditable(true);
                tfSiglaPais.requestFocus();
tfNomePais.setEditable(true);
tfSiglaPais.setEditable(true);
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
 ////////////    salvar      ////////////

 btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuErro = false; if (acao.equals("adicionando")) {
                    pais = new Pais();
                } try {
                    pais.setIdPais(Integer.valueOf(tfIdPais.getText()));
                } catch (Exception e) {
                    tfIdPais.setBackground(Color.red);
                    deuErro = true;
                }
 try {

 if (tfNomePais.getText().length()>tfNomePais.getColumns()) {
                        int x = 3/0;//vai causar um erro
                    }
                    pais.setNomePais(tfNomePais.getText());
                } catch (Exception e) {
                    tfNomePais.setBackground(Color.red);
                    deuErro = true;
                }
 try {

 if (tfSiglaPais.getText().length()>tfSiglaPais.getColumns()) {
                        int x = 3/0;//vai causar um erro
                    }
                    pais.setSiglaPais(tfSiglaPais.getText());
                } catch (Exception e) {
                    tfSiglaPais.setBackground(Color.red);
                    deuErro = true;
                }

if (!deuErro) {
                    if ("adicionando".equals(acao)) {
                        daoPais.inserir(pais);
                        lbAviso.setText("Inseriu o registro");
                    } else {
           daoPais.atualizar(pais, "idPais", pais.getIdPais());
                        lbAviso.setText("Alterou o registro");
                    } tfIdPais.requestFocus();
                    tfIdPais.setText("");
                    tfIdPais.setEditable(true);
                    tfIdPais.setBackground(Color.white); tfNomePais.setText("");
                    tfNomePais.setEditable(false);
                    tfNomePais.setBackground(Color.white); tfSiglaPais.setText("");
                    tfSiglaPais.setEditable(false);
                    tfSiglaPais.setBackground(Color.white);  btBuscar.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btListar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(cp, "Erro nos dados. É necessário corrigir");
                }
            }
        });
 ////////////    cancelar      ////////////

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
 tfIdPais.requestFocus();
                    tfIdPais.setText("");
                    tfIdPais.setEditable(true);
                    tfIdPais.setBackground(Color.white); tfNomePais.setText("");
                    tfNomePais.setEditable(false);
                    tfNomePais.setBackground(Color.white); tfSiglaPais.setText("");
                    tfSiglaPais.setEditable(false);
                    tfSiglaPais.setBackground(Color.white); btBuscar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                lbAviso.setText("");
            }
        });


 ////////////    excluir      ////////////

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.
                        showConfirmDialog(cp, "Confirma a exclusão?", "Excluindo", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                if (opcao == JOptionPane.YES_NO_OPTION) {
                    daoPais.excluir(pais.getIdPais(), "idPais");
                }
                tfIdPais.setText("");
 tfNomePais.setText("");
                    tfNomePais.setEditable(false);
 tfSiglaPais.setText("");
                    tfSiglaPais.setEditable(false);
 tfIdPais.requestFocus();
                    tfIdPais.setText("");
                    tfIdPais.setEditable(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                lbAviso.setText("");
            }
        });
 ////////////    listar      ////////////

btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbAviso.setText("Relatório");
                Point coordenadas = getLocation();//pega as coordenadas da guiPai
                Dimension dimensao = getSize();
                String idSelecionado
                        = new PaisGUIListar(daoPais, coordenadas, dimensao).getIdSelecionado();
                tfIdPais.setText(idSelecionado);
                btBuscar.doClick();
            }
        });
 ////////////    ao fechar a GUI      ////////////

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //antes de sair do sistema, grava os dados da lista de forma permanente (persiste os dados)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
 ////////////    finalizando      ////////////

 setSize(800, 200);
        // pack();
        setLocation(new CentroDoMonitorMaior().getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        PaisGUI paisGUI = new PaisGUI();
    }
} //fim da classe
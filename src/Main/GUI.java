package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

/**
 *
 * @author radames
 */
class GUI extends JFrame {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel pnSul = new JPanel(cardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));

    private JLabel lbId = new JLabel("<html><font color='red'> ID </font>");
    private JLabel lbNome = new JLabel("Nome");
    private JLabel lbEndereco = new JLabel("Endereço");

    private JTextField tfId = new JTextField(5);
    private JTextField tfNome = new JTextField(30);
    private JTextField tfEndereco = new JTextField(30);

    private JButton btBuscar = new JButton("<html><center>"
                 + "<font color=#000fdd>Buscar</font>");
    private JButton btInserir = new JButton("Inserir");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton("Gravar");

    String[] colunas = new String[]{"Id", "Nome", "Endereço"};
    String[][] dados = new String[0][3];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    JScrollPane scrollList = new JScrollPane();

    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem

    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas

    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar

    private Controle controle = new Controle(); //essa é a classe de processamento (controle da lista de contatos)
    private Contato contato = new Contato(); //ver classe contato

    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea
    UsarGridBagLayout usarGridBagLayoutCentro = new UsarGridBagLayout(pnCentro);

    Ferramentas fer = new Ferramentas();

    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void travarTextFields(boolean campo) {
        tfId.setEditable(campo); //permite que o usuario digite nesse textField
        tfNome.setEditable(!campo);
        tfEndereco.setEditable(!campo);
        if (!campo) {
            tfNome.requestFocus();
            tfNome.selectAll();
        }
    }

    private void limparValoresDosAtributos() {
        tfNome.setText("");
        tfEndereco.setText("");
    }

    //construtor da classe GUI
    public GUI() {
        //abrir o arquivo
        List<String> listaAuxiliar = fer.abrirArquivo("Contatos.txt");
        if (listaAuxiliar != null) {//se o arquivo existe, copia os dados
            for (int i = 0; i < listaAuxiliar.size(); i++) {
                String aux[] = listaAuxiliar.get(i).split(";");
                Contato c = new Contato(Integer.valueOf(aux[0]), aux[1], aux[2]);
                controle.inserir(c);
            }
        }

        //faz com que a última linha do 
        //jTextArea seja exibida
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.add(lbId);
        pnNorte.add(tfId);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btListar);
        
        

        usarGridBagLayoutCentro.add(lbNome, tfNome, lbEndereco, tfEndereco);

        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("log"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");

        pnSul.setBackground(Color.red);

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btInserir.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);

        travarTextFields(true);
        textAreaMsg.setEditable(false);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btGravar.doClick();
                // Sai   
                dispose();
            }
        });

        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //converter a lista<contato> em lista de string
                List<String> listaStr = controle.listar();
                fer.salvarArquivo("Contatos.txt", listaStr);

            }
        });

// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {

            if (tfId.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Querido usuário, vazio não pode ");
            } else {
                try {
                    tfId.setBackground(Color.green);
                    travarTextFields(true);
                    cardLayout.show(pnSul, "pnMsg");
                    contato = controle.buscar(Integer.valueOf(tfId.getText()));
                    if (contato == null) { //nao achou
                        btInserir.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        limparValoresDosAtributos();
                        setLog("Não achou na lista, pode inserir se quiser...");
                    } else { //achou
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfId.setText(String.valueOf(contato.getId()));
                        tfNome.setText(contato.getNome());
                        tfEndereco.setText(contato.getEndereco());
                        setLog("Achou [" + contato.getId() + "-" + contato.getNome() + "]");

                    }
                } catch (Exception x) {
                    tfId.selectAll();
                    tfId.requestFocus();
                    tfId.setBackground(Color.red);
                    setLog("Erro no tipo de dados da chave. (" + x.getMessage() + ")");
                }//else
            }
            tfId.selectAll();
            tfId.requestFocus();
        });

//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Contato contatoOriginal = contato; //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                contato = new Contato(); //criar um novo contato
            }
            //transfere os valores da GUI para classe contato
            contato.setId(Integer.valueOf(tfId.getText()));
            contato.setNome(tfNome.getText());
            contato.setEndereco(tfEndereco.getText());

            if (inserindo) { //a variavel inserindo é preenchida nos
                controle.inserir(contato);
                setLog("Inseriu [" + contato.getId() + "-" + contato.getNome() + "]");
            } else {//alterar
                controle.alterar(contatoOriginal, contato);
                setLog("Alterou [" + contato.getId() + "-" + contato.getNome() + "]");
            }

            //voltar para tela inicial
            tfId.requestFocus();
            tfId.selectAll();
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            limparValoresDosAtributos();
            travarTextFields(true);
        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfId.requestFocus();
                tfId.selectAll();
                btInserir.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                travarTextFields(true);
                limparValoresDosAtributos();
                setLog("Cancelou a alteração ou inclusão do registro");
            }
        });

//############################# BOTAO ALTERAR #################################
        btAlterar.addActionListener((ActionEvent e) -> {
            tfNome.requestFocus();
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            btListar.setVisible(false);
            inserindo = false;
            travarTextFields(false);
            setLog("Alterando um registro existente");
        });
//||||||||||||||||||||||||||| BOTÃO INSERIR |||||||||||||||||||||||||||||||||||
        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNome.requestFocus();
                btInserir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
                travarTextFields(false);
                limparValoresDosAtributos();
                setLog("Inserindo um novo registro");
            }
        });

//======================= LISTAR =============================================
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "pnLst");
                scrollList.setPreferredSize(tabela.getPreferredSize());
                pnSulListagem.add(scrollList);
                scrollList.setViewportView(tabela);
                List<String> listaDeContatos = controle.listar();//busca a lista de contatos
                String[] aux;
                colunas = new String[]{"Id", "Nome", "Endereço"};
                dados = new String[0][3];
                model.setDataVector(dados, colunas);
                for (int i = 0; i < listaDeContatos.size(); i++) {
                    aux = listaDeContatos.get(i).split(";");
                    String[] linha = new String[]{aux[0], aux[1], aux[2]};
                    // String[] linha = new String[]{"João", "15", "Masculino"};
                    model.addRow(linha);
                }
            }
        });
//***************************** EXCLUIR *************************************
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(cp, "Vai excluir [ "
                        + tfId.getText() + "-" + tfNome.getText() + "]?", "Exclui da lista", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    controle.excluir(contato);
                    setLog("Excluiu [" + contato.getId() + "-" + contato.getNome() + "]");

                }
            }
        });

//$$$$$$$$$$$$$$ FIM DOS LISTENERS $$$$$$$$$$$$$
        // parâmetros para janela inicial
        setSize(700, 200);
        setTitle("Crud contatos");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

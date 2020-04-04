package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class GUI extends JFrame {

    private Container cp;

    private JLabel lbCpf = new JLabel("CPF");
    private JTextField tfCpf = new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField(50);
    private JLabel lbSalario = new JLabel("Salário");
    private JTextField tfSalario = new JTextField(20);
    private JCheckBox cbAposentado = new JCheckBox("Aposentado", false);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btLocalizar = new JButton("Localizar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btCarregarDados = new JButton("Carregar");
    private JButton btGravar = new JButton("Gravar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();

    private String acao = "";
    private String chavePrimaria = "";

    private Controle controle = new Controle();
    private Trabalhador trabalhador = new Trabalhador();

    String[] colunas = new String[]{"Id", "Nome", "Endereço", "Aposentado"};
    String[][] dados = new String[0][4];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;

    public GUI() {

        String caminhoENomeDoArquivo = "DadosTrabalhador.csv"; //csv
      

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(600, 400);
        setTitle("CRUD Canguru - V7 - Janela Pesquisar");
        setLocationRelativeTo(null);//centro do monitor

        cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        painelSul.setLayout(cardLayout);

        painel1.add(scrollTexto);
        painel2.add(scrollTabela);

        texto.setText("\n\n\n\n\n\n");//5 linhas de tamanho
        scrollTexto.setViewportView(texto);

        painelSul.add(painel1, "Avisos");
        painelSul.add(painel2, "Listagem");
        tabela.setEnabled(false);

        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);

        painelCentro.setLayout(new GridLayout(3, 2));

        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbSalario);
        painelCentro.add(tfSalario);
        painelCentro.add(cbAposentado);

        toolBar.add(lbCpf);
        toolBar.add(tfCpf);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
        toolBar.add(btLocalizar);
        toolBar.add(btListar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);

        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        tfNome.setEditable(false);
        tfSalario.setEditable(false);//atributos começam bloqueados
        cbAposentado.setEnabled(false);
        texto.setEditable(false);

        btCarregarDados.addActionListener(new ActionListener() {//pega a lista de string e transforma em uma lista de trabalhador - volta
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo(); //classe para facilitar o trabalho com arquivos
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) { //só dá para carregar dados se o arquivo existir
                    String aux[];
                    Trabalhador t;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);//traz os dados em formato string - csv
                    for (String linha : listaStringCsv) {//para cada linha da lista - csv
                        aux = linha.split(";");//divida os campos nos ;
                        t = new Trabalhador(aux[0], aux[1], Double.valueOf(aux[2]), Boolean.valueOf(aux[3].equals("true") ? true : false));//crie um objeto Trabalhador e preencha com dados.
                        controle.adicionar(t); //adicione na lista
                    }
                    cardLayout.show(painelSul, "Listagem");
                }
            }
        });

        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1) converter de uma list<trabalhador> para list<string>
                List<Trabalhador> listaTrabalhador = controle.listar();//obtem a lista toda - pega a lista trabalhador
                List<String> listaTrabalhadorEmFormatoStringCSV = controle.listStrings(); //transforma em uma lista de string
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaTrabalhadorEmFormatoStringCSV);//csv
                
            }
        });

        btLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = controle.listStrings();
                if (listaAuxiliar.size() > 0) {//se a lista não estiver vazia abre a janela
                    Point lc = btLocalizar.getLocationOnScreen();//precisa mexer aqui para centralizar a janela// o ponto onde a janela vai abrir
                    lc.x = lc.x + btLocalizar.getWidth();//um pouco para frente do botão localizar
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();////matriz x coluna y é linha - posicionamento da janela
                    if (!selectedItem.equals("")) {//pega toda a linha 
                        String[] aux = selectedItem.split(";");//divide no ponto e virgula
                        tfCpf.setText(aux[0]);//pega so cpf e preenche no textfield cpf
                        btBuscar.doClick();//faca um clique,  faz a mesma coisa que o buscar clicando automaticamente
                    } else {
                        tfCpf.requestFocus();
                        tfCpf.selectAll();
                    }
                }
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);

                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                if (tfCpf.getText().trim().isEmpty()) {
                    trabalhador = new Trabalhador();
                    tfCpf.setText(tfCpf.getText().trim());//caso tenham sido digitados espaços

                    tfCpf.requestFocus();
                    tfCpf.selectAll();

                } else {
                    chavePrimaria = tfCpf.getText();//para uso no adicionar
                    trabalhador = controle.buscar(tfCpf.getText());
                    if (trabalhador == null) {//nao encontrou
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfSalario.setText("");
                        cbAposentado.setSelected(false);
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");//limpa o campo texto

                    } else {//encontrou
                        tfNome.setText(trabalhador.getNome());
                        tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                        cbAposentado.setSelected(trabalhador.isAposentado());
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btLocalizar.setVisible(true);
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");//limpa o campo texto
                        tfNome.setEditable(false);
                        tfSalario.setEditable(false);//atributos começam bloqueados
                        cbAposentado.setEnabled(false);
                    }
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btGravar.setVisible(true);
                btBuscar.setVisible(false);
                btLocalizar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);

                btAdicionar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btLocalizar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                tfCpf.requestFocus();
                tfCpf.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");//limpa o campo texto
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setEnabled(false);
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Trabalhador trabalhadorAntigo = trabalhador;
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    trabalhador.setAposentado(cbAposentado.isSelected());
                    controle.alterar(trabalhador, trabalhadorAntigo);
                    texto.setText("Registro alterado\n\n\n\n\n");//limpa o campo texto
                } else {//adicionar
                    trabalhador = new Trabalhador();
                    trabalhador.setCpf(tfCpf.getText());
                    trabalhador.setNome(tfNome.getText());
                    trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                    trabalhador.setAposentado(cbAposentado.isSelected());
                    controle.adicionar(trabalhador);
                    texto.setText("Foi adicionado um novo registro\n\n\n\n\n");//limpa o campo texto
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btLocalizar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                tfCpf.requestFocus();
                tfCpf.selectAll();
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setSelected(false);
                cbAposentado.setEnabled(true);
            }

        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfCpf.setText(chavePrimaria);//para retornar ao valor original (caso o usuário mude e tente enganar o programa)
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + trabalhador.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(trabalhador);
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfCpf.setEditable(true);
                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                cbAposentado.setEnabled(true);
                tfCpf.requestFocus();
                tfCpf.selectAll();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                texto.setText("Excluiu o registro de " + trabalhador.getCpf() + " - " + trabalhador.getNome() + "\n\n\n\n\n");//limpa o campo texto
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Trabalhador> lt = controle.listar();

//                String[] colunas = {"Id", "Nome", "Salário"};
//                Object[][] dados = {
//                    {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
//                    {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
//                    {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
//                };
                String[] colunas = {"Id", "Nome", "Salário", "Aposentado"};

                Object[][] dados = new Object[lt.size()][colunas.length];
                String aux[];
                for (int i = 0; i < lt.size(); i++) {
                    aux = lt.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(painelSul, "Listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                painel2.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                btGravar.doClick();
                // Sai da classe
                dispose();
            }
        });

        setVisible(true);

        //depois que a tela ficou visível, clic o botão automaticamente.
        btCarregarDados.doClick();//execute o listener do btCarregarDados
    }

}

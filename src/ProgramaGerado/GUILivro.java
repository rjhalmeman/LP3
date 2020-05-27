package ProgramaGerado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class GUILivro extends JFrame {
ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);JLabel labelIdLivro = new JLabel("IdLivro");
JTextField textFieldIdLivro = new JTextField(0);
JLabel labelNomeLivro = new JLabel("NomeLivro");
JTextField textFieldNomeLivro = new JTextField(30);
JLabel labelNomeAutor = new JLabel("NomeAutor");
JTextField textFieldNomeAutor = new JTextField(30);
  JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel(""); String acao = "";//variavel para facilitar insert e update
    ControleDaListaLivro cl = new ControleDaListaLivro();
Livro livro;
Livro livroOriginal;    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }
 public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }
private void habilitarAtributos(boolean idLivro,boolean nomeLivro,boolean nomeAutor){if (idLivro){textFieldIdLivro.requestFocus();textFieldIdLivro.selectAll();
}textFieldIdLivro.setEnabled(idLivro);textFieldIdLivro.setEditable(idLivro);textFieldNomeLivro.setEditable(nomeLivro);textFieldNomeAutor.setEditable(nomeAutor);}

public void zerarAtributos() {textFieldNomeLivro.setText("");textFieldNomeAutor.setText("");}
public GUILivro() {
        setTitle("Livro");
        try {
            File arq = new File("Livro.dat"); //tenta abrir o arquivo
            if (arq.exists()) { //se o arquivo já existe, abre e lê os dados
                cl.abrirArquivo("Livro.dat");
            }
        } catch (Exception e) {
            System.out.println("arquivo não encontrado");
        } setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
habilitarAtributos(true,false,false);btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
 JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdLivro);
        Toolbar1.add(textFieldIdLivro);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);  btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(4 , 2));centro.add(labelIdLivro);
centro.add(textFieldIdLivro);
centro.add(labelNomeLivro);
centro.add(textFieldNomeLivro);
centro.add(labelNomeAutor);
centro.add(textFieldNomeAutor);
aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);textFieldIdLivro.requestFocus();textFieldIdLivro.selectAll();textFieldIdLivro.setBackground(Color.GREEN);labelAviso.setText("Digite uma placa e clic [Pesquisar]");
                     // setLocationRelativeTo(null); // posiciona no centro da tela principal
        setLocation(300, 200);
        setVisible(true);//faz a janela ficar visível  

// Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {livro = new Livro();textFieldIdLivro.setText(textFieldIdLivro.getText().trim());//caso tenham sido digitados espaços

if (textFieldIdLivro.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldIdLivro.requestFocus();
                    textFieldIdLivro.selectAll();
                } else {livro.setIdLivro(Integer.valueOf(textFieldIdLivro.getText()));
                   livro =  cl.buscarComPesquisaBinaria(livro);
                    if (livro != null) { //se encontrou na lista
textFieldNomeLivro.setText(livro.getNomeLivro());textFieldNomeAutor.setText(livro.getNomeAutor());atvBotoes(false, true, true, true);
habilitarAtributos(true,false,false);                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos();
                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
    }
        });

  btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false,true,true);
                textFieldNomeLivro.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {livro = new Livro();livro.setIdLivro(Integer.valueOf(textFieldIdLivro.getText()));livro.setNomeLivro(textFieldNomeLivro.getText());livro.setNomeAutor(textFieldNomeAutor.getText());             cl.inserir(livro);
                    habilitarAtributos(true,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                     labelAviso.setText("Registro inserido...");
                } else {  //acao = update
livro.setIdLivro(Integer.valueOf(textFieldIdLivro.getText()));livro.setNomeLivro(textFieldNomeLivro.getText());livro.setNomeAutor(textFieldNomeAutor.getText());                    cl.alterar(livroOriginal,livro);
                    mostrarBotoes(true);
                    habilitarAtributos(true,false,false);
                    atvBotoes(false, true, false, false);
                     labelAviso.setText("Registro atualizado...");
                }
            }
        });  btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true,false,false);
                mostrarBotoes(true);
            }
        });  btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                acao = "list";
                GUIListagemLivro guiListagem = new GUIListagemLivro(cl.getLista());
            }
        }); btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false,true,true);
            }
        });
//---------------------------------------------------------
   btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + livro.getIdLivro() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.excluir(livro);
                    zerarAtributos();
                    textFieldIdLivro.requestFocus();
                    textFieldIdLivro.selectAll();
                }
            }
        }); textFieldIdLivro.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdLivro.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma IdLivro e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textFieldIdLivro.setBackground(Color.white);
            }
        });textFieldIdLivro.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdLivro.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdLivro.setBackground(Color.white);
            }
        });
textFieldNomeLivro.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeLivro.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeLivro.setBackground(Color.white);
            }
        });
textFieldNomeAutor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeAutor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeAutor.setBackground(Color.white);
            }
        });
 setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                cl.salvarArquivo("Livro.dat");
                // Sai do sistema  
                System.exit(0);
            }
        });    }
public static void main(String[] args) {
        new GUILivro();
    }}
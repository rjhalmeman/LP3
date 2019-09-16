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

public class GUICidade extends JFrame {
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
    JButton btnList = new JButton(iconeListar);JLabel labelCodigo = new JLabel("Codigo");
JTextField textFieldCodigo = new JTextField(4);
JLabel labelNome = new JLabel("Nome");
JTextField textFieldNome = new JTextField(50);
JLabel labelEstado = new JLabel("Estado");
JTextField textFieldEstado = new JTextField(2);
JLabel labelCep = new JLabel("Cep");
JTextField textFieldCep = new JTextField(9);
  JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel(""); String acao = "";//variavel para facilitar insert e update
    ControleDaListaCidade cl = new ControleDaListaCidade();
Cidade cidade;
Cidade cidadeOriginal;    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
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
private void habilitarAtributos(boolean codigo,boolean nome,boolean estado,boolean cep){if (codigo){textFieldCodigo.requestFocus();textFieldCodigo.selectAll();
}textFieldCodigo.setEnabled(codigo);textFieldCodigo.setEditable(codigo);textFieldNome.setEditable(nome);textFieldEstado.setEditable(estado);textFieldCep.setEditable(cep);}

public void zerarAtributos() {textFieldNome.setText("");textFieldEstado.setText("");textFieldCep.setText("");}
public GUICidade() {
        setTitle("Cidade");
        try {
            File arq = new File("Cidade.dat"); //tenta abrir o arquivo
            if (arq.exists()) { //se o arquivo já existe, abre e lê os dados
                cl.abrirArquivo("Cidade.dat");
            }
        } catch (Exception e) {
            System.out.println("arquivo não encontrado");
        } setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
habilitarAtributos(true,false,false,false);btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
 JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelCodigo);
        Toolbar1.add(textFieldCodigo);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);  btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(5 , 2));centro.add(labelCodigo);
centro.add(textFieldCodigo);
centro.add(labelNome);
centro.add(textFieldNome);
centro.add(labelEstado);
centro.add(textFieldEstado);
centro.add(labelCep);
centro.add(textFieldCep);
aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);textFieldCodigo.requestFocus();textFieldCodigo.selectAll();textFieldCodigo.setBackground(Color.GREEN);labelAviso.setText("Digite uma placa e clic [Pesquisar]");
                     // setLocationRelativeTo(null); // posiciona no centro da tela principal
        setLocation(300, 200);
        setVisible(true);//faz a janela ficar visível  

// Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {cidade = new Cidade();textFieldCodigo.setText(textFieldCodigo.getText().trim());//caso tenham sido digitados espaços

if (textFieldCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldCodigo.requestFocus();
                    textFieldCodigo.selectAll();
                } else {cidade.setCodigo(Integer.valueOf(textFieldCodigo.getText()));
                   cidade =  cl.buscarComPesquisaBinaria(cidade);
                    if (cidade != null) { //se encontrou na lista
textFieldNome.setText(cidade.getNome());textFieldEstado.setText(cidade.getEstado());textFieldCep.setText(cidade.getCep());atvBotoes(false, true, true, true);
habilitarAtributos(true,false,false,false);                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
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
                habilitarAtributos(false,true,true,true);
                textFieldNome.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {cidade = new Cidade();cidade.setCodigo(Integer.valueOf(textFieldCodigo.getText()));cidade.setNome(textFieldNome.getText());cidade.setEstado(textFieldEstado.getText());cidade.setCep(textFieldCep.getText());             cl.inserir(cidade);
                    habilitarAtributos(true,false,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                     labelAviso.setText("Registro inserido...");
                } else {  //acao = update
cidade.setCodigo(Integer.valueOf(textFieldCodigo.getText()));cidade.setNome(textFieldNome.getText());cidade.setEstado(textFieldEstado.getText());cidade.setCep(textFieldCep.getText());                    cl.alterar(cidadeOriginal,cidade);
                    mostrarBotoes(true);
                    habilitarAtributos(true,false,false,false);
                    atvBotoes(false, true, false, false);
                     labelAviso.setText("Registro atualizado...");
                }
            }
        });  btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true,false,false,false);
                mostrarBotoes(true);
            }
        });  btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                acao = "list";
                GUIListagemCidade guiListagem = new GUIListagemCidade(cl.getLista());
            }
        }); btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false,true,true,true);
            }
        });
//---------------------------------------------------------
   btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cidade.getCodigo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.excluir(cidade);
                    zerarAtributos();
                    textFieldCodigo.requestFocus();
                    textFieldCodigo.selectAll();
                }
            }
        }); textFieldCodigo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCodigo.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Codigo e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textFieldCodigo.setBackground(Color.white);
            }
        });textFieldCodigo.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCodigo.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCodigo.setBackground(Color.white);
            }
        });
textFieldNome.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNome.setBackground(Color.white);
            }
        });
textFieldEstado.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEstado.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEstado.setBackground(Color.white);
            }
        });
textFieldCep.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCep.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCep.setBackground(Color.white);
            }
        });
 setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                cl.salvarArquivo("Cidade.dat");
                // Sai do sistema  
                System.exit(0);
            }
        });    }
public static void main(String[] args) {
        new GUICidade();
    }}
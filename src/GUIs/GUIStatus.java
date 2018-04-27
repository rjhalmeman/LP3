package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;import javax.swing.JDialog;
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
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class GUIStatus extends JDialog {
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
    JButton btnList = new JButton(iconeListar);

JLabel labelIdStatus = new JLabel("IdStatus");JTextField textFieldIdStatus = new JTextField(20);
JLabel labelNomeStatus = new JLabel("NomeStatus");JTextField textFieldNomeStatus = new JTextField(20);


JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

 String acao = "";//variavel para facilitar insert e update
    DAOStatus daoStatus = new     DAOStatus();
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
Status status;
    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
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
private void habilitarAtributos(boolean idStatus,boolean nomeStatus){
if (idStatus){
textFieldIdStatus.requestFocus();
textFieldIdStatus.selectAll();
}textFieldIdStatus.setEnabled(idStatus);
textFieldIdStatus.setEditable(idStatus);
textFieldNomeStatus.setEditable(nomeStatus);

}

public void zerarAtributos() {
textFieldNomeStatus.setText("");
}
Color corPadrao = labelIdStatus.getBackground();
public GUIStatus(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Status");
 setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
habilitarAtributos(true,false);btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
 JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdStatus);
        Toolbar1.add(textFieldIdStatus);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);  btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2 , 2));centro.add(labelNomeStatus);
centro.add(textFieldNomeStatus);
pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
textFieldIdStatus.requestFocus();
textFieldIdStatus.selectAll();
textFieldIdStatus.setBackground(Color.GREEN);
labelAviso.setText("Digite um IdStatus e clic [Pesquisar]");


//--------------- listeners ----------------- 
  textFieldIdStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

 

//-----------------------------  btnRetrieve ------------------------------------------
     btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {status = new Status();textFieldIdStatus.setText(textFieldIdStatus.getText().trim());//caso tenham sido digitados espaços

if (textFieldIdStatus.getText().equals("")) {
 List<String> listaAuxiliar = daoStatus.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
   Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdStatus.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdStatus.requestFocus();
                            textFieldIdStatus.selectAll();
                        }
                    }

                    textFieldIdStatus.requestFocus();
                    textFieldIdStatus.selectAll();                } else {
 try{
status.setIdStatus(Integer.valueOf(textFieldIdStatus.getText()));
status = daoStatus.obter(status.getIdStatus());
if (status != null) { //se encontrou na lista
textFieldNomeStatus.setText(String.valueOf(status.getNomeStatus()));atvBotoes(false, true, true, true);
habilitarAtributos(true,false);                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos();
                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                  textFieldIdStatus.setBackground(Color.green);                    }
catch (Exception x) {
                        textFieldIdStatus.setOpaque(true);
                        textFieldIdStatus.selectAll();
                        textFieldIdStatus.requestFocus();
                        textFieldIdStatus.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - "+x.getMessage());
                    }  
}
}
      });

  btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false,true);
                textFieldNomeStatus.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

//-----------------------------  SAVE ------------------------------------------
btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
 boolean deuRuim = false;
 if (acao.equals("insert")) {
status = new Status();}
try{
status.setIdStatus(Integer.valueOf((textFieldIdStatus.getText())));} catch (Exception erro2){ 
 deuRuim = true;
textFieldIdStatus.setBackground(Color.red);
} 
status.setNomeStatus(String.valueOf(textFieldNomeStatus.getText()));
 if (!deuRuim) { if (acao.equals("insert")) {
daoStatus.inserir(status);                     labelAviso.setText("Registro inserido.");
} else {
daoStatus.atualizar(status);                     labelAviso.setText("Registro alterado.");
}
                    habilitarAtributos(true,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
}//!deu ruim
else {
labelAviso.setText("Erro nos dados - corrija");
labelAviso.setBackground(Color.red);
}
}
});  btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true,false);
                mostrarBotoes(true);
        }});  btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                acao = "list";
                GUIStatusListagem guiStatusListagem = new GUIStatusListagem( daoStatus.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        }); btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false,true);
            }
        });   btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + status.getNomeStatus() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoStatus.remover(status);
                    zerarAtributos();
                     mostrarBotoes(true);
                    atvBotoes(false, true, false, false);                    textFieldNomeStatus.requestFocus();
                    textFieldNomeStatus.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
textFieldNomeStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeStatus.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeStatus.setBackground(corPadrao);
            }
        });
 setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        }); setModal(true);
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }
public static void main(String[] args) {
        new GUIStatus(new Point(880, 250), new Dimension(800, 600));
    }}
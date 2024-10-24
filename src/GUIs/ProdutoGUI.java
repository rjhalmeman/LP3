package GUIs;

import DAOs.DAOCategoria;
import Entidades.Produto;
import DAOs.DAOProduto;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.ImagemAjustada;

/**
 *
 * @author belly 23/06/2023 - 07:44:12
 */
public class ProdutoGUI extends JDialog {

    Icon buscar = new ImageIcon("src/icones/retrieve.png");
    Icon add = new ImageIcon("src/icones/create.png");
    Icon salvar = new ImageIcon("src/icones/save.png");
    Icon alterar = new ImageIcon("src/icones/update.png");
    Icon excluir = new ImageIcon("src/icones/delete.png");
    Icon listar = new ImageIcon("src/icones/list.png");
    Icon fechar = new ImageIcon("src/icones/fechar.png");
    Icon cancel = new ImageIcon("src/icones/cancelar1.png");
    Icon loc = new ImageIcon("src/icones/localizar.png");

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();

    JButton btBuscar = new JButton(buscar);
    JButton btAdicionar = new JButton(add);
    JButton btSalvar = new JButton(salvar);
    JButton btAlterar = new JButton(alterar);
    JButton btExcluir = new JButton(excluir);
    JButton btListar = new JButton(listar);
    JButton btFechar = new JButton(fechar);
    JButton btCancelar = new JButton(cancel);
    JButton btLocalizar = new JButton(loc);
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    JComboBox<String> comboBox = new JComboBox<>();
    private final JButton btLerImagemSalvaNoBD = new JButton("Abrir Imagem");
    private final JButton btAbrirArquivoImagemEArmazenarNoBD = new JButton("Enviar imagem");
    private final JLabel lbImagem = new JLabel("");
    private byte[] imagemData;

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbIdProduto = new JLabel("ID:");
    JTextField tfIdProduto = new JTextField(10);
    JLabel lbNomeProduto = new JLabel("Nome:");
    JTextField tfNomeProduto = new JTextField(20);
    JLabel lbDescricaoProduto = new JLabel("Descrição:");
    JTextField tfDescricaoProduto = new JTextField(50);
    JLabel lbTempoPreparo = new JLabel("Tempo de Preparo:");
    JLabel lbCategoria = new JLabel("Categoria");
    JTextField tfTempoPreparo = new JTextField(10);
    JComboBox cbCategoria = new JComboBox();
    DAOProduto daoProduto = new DAOProduto();
    Produto produto = new Produto();
    String[] colunas = new String[]{"idProduto", "nomeProduto", "descricaoProduto", "tempoPreparo", "categoriaIdCategoria"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public ProdutoGUI() {
        DAOCategoria daoCategoria = new DAOCategoria();
        String listaCategoria[] = daoCategoria.listInOrderNomeStringsArray();
        for (String s : listaCategoria) {
            cbCategoria.addItem(s);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Produto");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdProduto);
        pnNorte.add(tfIdProduto);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btFechar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.setBackground(new Color(221, 184, 146));
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btFechar.setVisible(false);
        btCancelar.setVisible(false);
        pnCentro.setLayout(new GridLayout(5, 2));
        pnCentro.add(lbNomeProduto);
        pnCentro.add(tfNomeProduto);
        pnCentro.add(lbDescricaoProduto);
        pnCentro.add(tfDescricaoProduto);
        pnCentro.add(lbTempoPreparo);
        pnCentro.add(tfTempoPreparo);
        pnCentro.add(lbCategoria);
        pnCentro.add(cbCategoria);
        pnCentro.add(btAbrirArquivoImagemEArmazenarNoBD);
        pnCentro.add(btLerImagemSalvaNoBD);
        lbIdProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfIdProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbIdProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        tfIdProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        lbNomeProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfNomeProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbNomeProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        tfNomeProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        lbDescricaoProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfDescricaoProduto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbDescricaoProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        tfDescricaoProduto.setBorder(BorderFactory.createLineBorder(Color.black));
        lbTempoPreparo.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        tfTempoPreparo.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbTempoPreparo.setBorder(BorderFactory.createLineBorder(Color.black));
        tfTempoPreparo.setBorder(BorderFactory.createLineBorder(Color.black));
        lbCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        cbCategoria.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        lbCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        cbCategoria.setBorder(BorderFactory.createLineBorder(Color.black));
        lbIdProduto.setBackground(new Color(221, 184, 146));
        lbIdProduto.setForeground(Color.BLACK);
        lbNomeProduto.setBackground(new Color(221, 184, 146));
        lbNomeProduto.setForeground(Color.BLACK);
        lbDescricaoProduto.setBackground(new Color(221, 184, 146));
        lbDescricaoProduto.setForeground(Color.BLACK);
        lbTempoPreparo.setBackground(new Color(221, 184, 146));
        lbTempoPreparo.setForeground(Color.BLACK);
        pnCentro.setBackground(new Color(221, 184, 146));
        btBuscar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAdicionar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btSalvar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAlterar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btExcluir.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btListar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btFechar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btCancelar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btLerImagemSalvaNoBD.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btAbrirArquivoImagemEArmazenarNoBD.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        btBuscar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.black));
        btSalvar.setBorder(BorderFactory.createLineBorder(Color.black));
        btAlterar.setBorder(BorderFactory.createLineBorder(Color.black));
        btExcluir.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btFechar.setBorder(BorderFactory.createLineBorder(Color.black));
        btListar.setBorder(BorderFactory.createLineBorder(Color.black));
        btLerImagemSalvaNoBD.setBorder(BorderFactory.createLineBorder(Color.black));
        btAbrirArquivoImagemEArmazenarNoBD.setBorder(BorderFactory.createLineBorder(Color.black));
        btBuscar.setBackground(Color.white);
        btCancelar.setBackground(Color.white);
        btAdicionar.setBackground(Color.white);
        btSalvar.setBackground(Color.white);
        btAlterar.setBackground(Color.white);
        btExcluir.setBackground(Color.white);
        btListar.setBackground(Color.white);
        btFechar.setBackground(Color.white);
        btLerImagemSalvaNoBD.setBackground(Color.white);
        btAbrirArquivoImagemEArmazenarNoBD.setBackground(Color.white);
        tabela.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tfNomeProduto.setEditable(false);
        tfDescricaoProduto.setEditable(false);
        tfTempoPreparo.setEditable(false);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);
        btAbrirArquivoImagemEArmazenarNoBD.setEnabled(false);
        btLerImagemSalvaNoBD.setEnabled(false);
        btAbrirArquivoImagemEArmazenarNoBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfIdProduto.getText().trim().isEmpty()) {

                    JFileChooser jFileChoose = new JFileChooser();
                    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "png", "jpg", "bmp", "jpeg");
                    jFileChoose.setAcceptAllFileFilterUsed(false);
                    jFileChoose.addChoosableFileFilter(filtro);

                    int respostDoFileChooser = jFileChoose.showOpenDialog(cp);

                    if (respostDoFileChooser == JFileChooser.APPROVE_OPTION) {
                        File arquivoSelecionado = jFileChoose.getSelectedFile();
                        String caminho = arquivoSelecionado.getAbsolutePath();
                        // System.out.println(caminho);

                        ImagemAjustada imagemAjustada = new ImagemAjustada();
                        int largura = 500;
                        int altura = 500;
                        ImageIcon imageIcon = imagemAjustada.getImagemAjustada(largura, altura, caminho);
                        if (imageIcon != null) {
                            lbImagem.setIcon(imageIcon);
                            Image image = imageIcon.getImage();

                            BufferedImage bufferedImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
                            bufferedImage.getGraphics().drawImage(image, 0, 0, null);

                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            try {
                                ImageIO.write(bufferedImage, "jpg", outputStream);
                                outputStream.close();
                            } catch (IOException ex) {
                                System.out.println("erro na conversao da imagem");
                            }

                            byte[] conteudoArquivo = outputStream.toByteArray();

                            produto = daoProduto.obter(Integer.valueOf(tfIdProduto.getText()));
                            if (produto != null) {
                                // Atribuir o array de bytes ao produto
                                produto.setImagem(conteudoArquivo);
                                daoProduto.atualizar(produto);
                            }

                        } else {
                            System.out.println("nao achou a imagem");
                        }

                    } else {
                        System.out.println("Nenhum arquivo selecionado.");
                    }
                } else {
                    System.out.println("digite um id");
                }

            }
        });

        btLerImagemSalvaNoBD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame imagemFrame = new JFrame("Imagem");
                    imagemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    if (!tfIdProduto.getText().trim().isEmpty()) {
                        produto = daoProduto.obter(Integer.valueOf(tfIdProduto.getText()));
                        try {
                            imagemData = produto.getImagem();
                            ImageIcon imageIcon = new ImageIcon(imagemData);
                            JLabel imagemLabel = new JLabel(imageIcon);
                            imagemFrame.getContentPane().add(imagemLabel);
                            //imagemFrame.toFront();
                            imagemFrame.pack();
                            JOptionPane.showMessageDialog(imagemFrame, imagemLabel, "Imagem", 3);

                        } catch (Exception eee) {
                            System.out.println("Sem imagem");
                        }

                    } else {
                        System.out.println("digite um id ou escolha um produto");
                    }

                });
            }
        });

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    produto = daoProduto.obter(Integer.valueOf(tfIdProduto.getText()));
                    if (produto != null) {//achou o produto na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeProduto.setText(produto.getNomeProduto());
                        tfNomeProduto.setEditable(false);
                        tfNomeProduto.setEnabled(true);
                        btAbrirArquivoImagemEArmazenarNoBD.setEnabled(true);
                        btLerImagemSalvaNoBD.setEnabled(true);
                        tfDescricaoProduto.setText(produto.getDescricaoProduto());
                        tfDescricaoProduto.setEditable(false);
                        tfDescricaoProduto.setEnabled(true);
                        tfTempoPreparo.setText(produto.getTempoPreparo());
                        tfTempoPreparo.setEditable(false);
                        tfTempoPreparo.setEnabled(true);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNomeProduto.setText("");
                        tfNomeProduto.setEditable(false);
                        tfDescricaoProduto.setText("");
                        tfDescricaoProduto.setEditable(false);
                        tfTempoPreparo.setText("");
                        tfTempoPreparo.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Chave Inválida", "Erro Ao Buscar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdProduto.setEnabled(false);
                tfNomeProduto.requestFocus();
                tfNomeProduto.setEditable(true);
                tfNomeProduto.setEnabled(true);
                tfDescricaoProduto.setEditable(true);
                tfDescricaoProduto.setEnabled(true);
                tfTempoPreparo.setEditable(true);
                tfTempoPreparo.setEnabled(true);
                btAbrirArquivoImagemEArmazenarNoBD.setEnabled(true);
                btLerImagemSalvaNoBD.setEnabled(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (acao.equals("adicionar")) {
                        produto = new Produto();
                    }
                    produto.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                    produto.setNomeProduto(tfNomeProduto.getText());
                    produto.setDescricaoProduto(tfDescricaoProduto.getText());
                    produto.setTempoPreparo(tfTempoPreparo.getText());
                    String i = (String) cbCategoria.getSelectedItem();
                    String aux = (i.split("-"))[0];
                    DAOCategoria daoCategoria = new DAOCategoria();
                    produto.setCategoriaIdCategoria(daoCategoria.obter(Integer.valueOf(aux)));
                    if (acao.equals("adicionar")) {
                        daoProduto.inserir(produto);
                    } else {
                        daoProduto.atualizar(produto);
                    }

                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdProduto.setEnabled(true);
                    tfIdProduto.setEditable(true);
                    tfIdProduto.requestFocus();
                    tfIdProduto.setText("");
                    tfNomeProduto.setEnabled(false);
                    tfNomeProduto.setEditable(false);
                    tfNomeProduto.setText("");
                    tfDescricaoProduto.setEnabled(false);
                    tfDescricaoProduto.setEditable(false);
                    tfDescricaoProduto.setText("");
                    tfTempoPreparo.setEnabled(false);
                    tfTempoPreparo.setEditable(false);
                    tfTempoPreparo.setText("");

                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(cp, "Tente Novamente", "Erro Ao Salvar", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdProduto.setEditable(false);
                tfNomeProduto.requestFocus();
                tfNomeProduto.setEditable(true);
                tfNomeProduto.setEnabled(true);
                tfDescricaoProduto.setEditable(true);
                tfDescricaoProduto.setEnabled(true);
                tfTempoPreparo.setEditable(true);
                tfTempoPreparo.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdProduto.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfIdProduto.requestFocus();
                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfNomeProduto.setEditable(false);
                tfDescricaoProduto.setText("");
                tfDescricaoProduto.setEditable(false);
                tfTempoPreparo.setText("");
                tfTempoPreparo.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoProduto.remover(produto);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Produto> listaProduto = daoProduto.listInOrderId();
                String[] colunas = new String[]{"ID", "Produto", "Descrição", "Tempo de Preparo", "Categoria"};
                String[][] dados = new String[listaProduto.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaProduto.size(); i++) {
                    aux = listaProduto.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                scrollTabela.setPreferredSize(new Dimension(1000, 180));
                pack();
                btFechar.setVisible(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfIdProduto.setText("");
                tfIdProduto.requestFocus();
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfNomeProduto.setText("");
                tfNomeProduto.setEditable(false);
                tfDescricaoProduto.setText("");
                tfDescricaoProduto.setEditable(false);
                tfTempoPreparo.setText("");
                tfTempoPreparo.setEditable(false);
                btAbrirArquivoImagemEArmazenarNoBD.setEnabled(false);
                btLerImagemSalvaNoBD.setEnabled(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });

        cbCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] aux = listaCategoria[cbCategoria.getSelectedIndex()].split("-");

                //System.out.println("selecionado=> " + aux[0]);
                DAOProduto daoProduto = new DAOProduto();
                String[] listaCidade = daoProduto.listInOrderNomeStringsArray(aux[0]);
                cbCategoria.removeAllItems();
                for (String s : listaCidade) {
                    cbCategoria.addItem(s);
                }
            }
        }
        );

        btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnListagem.remove(scrollTabela);
                pack();
                btFechar.setVisible(false);
            }
        });
        setModal(true);
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do contrutor de GUI

} //fim da classe

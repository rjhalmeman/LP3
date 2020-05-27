package pacoteGerador;

// @author Radames
import java.util.ArrayList;
import java.util.List;

public class Gerador {

    private List<String> textoDescritivo = new ArrayList<String>();
    ManipulaArquivo file = new ManipulaArquivo();
    List<String> cg = new ArrayList<String>();
    //classeGerada é uma lista de strings que conterá o código fonte que será gerado
    String arq;
    //salvar o texto que foi gerado e está armazenado na lista cg
    String pastaDestino = "src/ProgramaGerado/";
    String arquivoDestino = pastaDestino + "/GUI" + arq + ".java";

    public Gerador(String arq) {
        this.arq = arq;
    }

    public String primMaius(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public String primMinus(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
    }

    public List<String> getTextoDescritivo() {
        //lê o arquivo descritivo da classe que será gerada e transfere para uma lista de strings
        textoDescritivo = file.abrirArquivo("src/txts/" + arq + ".txt");
        return textoDescritivo;
    }

    //-----------------------------------------------  Entidade  ---------------------------------
    public void gerarClasseDeEntidade() {
        getTextoDescritivo();

        //imports
        cg.clear();
        cg.add("package ProgramaGerado;");//gera o código no pacote do gerador

        cg.add("// @author Radames\n"
                + "public class " + arq + " {");

        //atributos
        String[] aux;
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("private " + aux[1] + " " + aux[0] + ";");
        }

        //construtores
        cg.add("\npublic " + arq + "(){\n\n}");

        String parametros = "";
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            parametros = parametros + aux[1] + " " + aux[0] + ",";
        }
        cg.add("public " + arq + "(" + parametros.substring(0, parametros.length() - 1) + "){");

        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("this." + aux[0] + " = " + aux[0] + ";");
        }
        cg.add("}\n");

        //gets 
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("\npublic " + aux[1] + " get" + primMaius(aux[0]) + "(){");
            cg.add("return " + aux[0] + ";\n}");
        }

        //sets
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("\npublic void set" + primMaius(aux[0]) + "(" + aux[1] + " " + aux[0] + "){");
            cg.add("this." + aux[0] + " = " + aux[0] + ";\n}");
        }

        //toString
        cg.add(
                "    public String toStringCSV() {");
        String s = "";
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            s = s + "this." + aux[0] + "+\";\"+";
        }

        cg.add("return " + s.substring(0, s.length() - 5) + ";");
        cg.add("}}");
        arquivoDestino = pastaDestino + arq + ".java";
        //salva no arquivoDestino a cg
        int salvarArquivo = file.salvarArquivo(arquivoDestino, cg);

        System.out.println("A classe " + arq + ".java foi gerada");

    }

    //-----------------------------------------------  Controle  ---------------------------------
    public void gerarClasseDeControle() {
        getTextoDescritivo();
        cg.clear();
        cg.add("package ProgramaGerado;");//gera o código no pacote do gerador
        cg.add("\n"
                + "// @author Radames\n"
                + "import java.io.BufferedReader;\n"
                + "import java.io.BufferedWriter;\n"
                + "import java.io.File;\n"
                + "import java.io.FileReader;\n"
                + "import java.io.FileWriter;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.Collections;\n"
                + "import java.util.Comparator;\n"
                + "import java.util.List;\n\n"
                + "public class ControleDaLista" + arq + " {\n");
        cg.add("private List<" + arq + "> lista = new ArrayList<>();\n");
        //comparator
        String s[] = textoDescritivo.get(0).split(";");

        cg.add("//esse comparator será usado para ordenação e busca binária\n"
                + "    private Comparator<" + arq + "> comparator = new Comparator<" + arq + ">() {\n"
                + "        @Override\n"
                + "        public int compare(" + arq + " c1, " + arq + " c2) {\n"
                + "            return Integer.valueOf(c1.get" + primMaius(s[0]) + "()).compareTo(Integer.valueOf(c2.get" + primMaius(s[0]) + "()));\n"
                + "        }\n"
                + "    };");

        cg.add("public List<" + arq + "> getLista() {\n"
                + "        return lista;\n"
                + "    }");

        cg.add("public void inserir(" + arq + " elemento) {\n"
                + "        lista.add(elemento);\n"
                + "        Collections.sort(lista, comparator);//após incluir, ordena a lista por ID\n"
                + "    }");

        cg.add("public " + arq + " buscarComPesquisaBinaria(" + arq + " elemento) {\n"
                + "        //tem que ordenar antes de pesquisar\n"
                + "        //o método chamado de pequisa binária ou busca binária é mais eficiente que pesquisa sequencial. Mas, para funcionar a lista tem que estar ordenada.\n"
                + "        //ordenaPorId(lista); //essa linha é obrigatória se nao ordenar ao inserir - (ordena por id)\n"
                + "\n"
                + "        int indice = Collections.binarySearch(lista, elemento, comparator);\n"
                + "        if (indice >= 0) {\n"
                + "            return lista.get(indice);\n"
                + "        } else {\n"
                + "            return null;\n"
                + "        }\n"
                + "    }");
        cg.add("public void excluir(" + arq + " elemento) {\n"
                + "        lista.remove(elemento);\n"
                + "    }");
        cg.add("    public void alterar(" + arq + " elementoOriginal, " + arq + " elementoAlterado) {\n"
                + "        //usa o original para localizar na lista e substitui pelo alterado\n"
                + "        lista.set(lista.indexOf(elementoOriginal), elementoAlterado);\n"
                + "    }");
        cg.add("public List<" + arq + "> abrirArquivo(String caminho) {\n"
                + "\n"
                + "        File arq = new File(caminho);\n"
                + "        if (arq.exists()) {\n"
                + "            try {\n"
                + "                //OpenFile\n"
                + "                FileReader arquivo = new FileReader(caminho);\n"
                + "                BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);\n"
                + "                String linha = conteudoDoArquivo.readLine();\n"
                + "                String aux[];\n"
                + "                " + arq + " elemento;\n"
                + "                while (linha != null) {\n"
                + "                    aux = linha.split(\";\");");

        cg.add("\n    elemento = new " + arq + "(Integer.valueOf(aux[0]),");
        String parametros = "";
        String l[];
        for (int i = 1; i < textoDescritivo.size(); i++) {
            l = textoDescritivo.get(i).split(";");
            parametros = parametros + "aux[" + i + "],";
        }

        cg.add(parametros.substring(0, parametros.length() - 1));
        cg.add(");");
        cg.add("\nlista.add(elemento);"
                + "\n"
                + "                    linha = conteudoDoArquivo.readLine();\n"
                + "                }\n"
                + "                conteudoDoArquivo.close();\n"
                + "            } catch (Exception e) {//Catch exception if any\n"
                + "                System.err.println(\"Error: \" + e.getMessage());\n"
                + "            }\n"
                + "        }\n"
                + "        return lista;\n"
                + "    }");
        cg.add(" \n\n public int salvarArquivo(String caminho) {\n"
                + "        try {\n"
                + "            // Create file \n"
                + "            FileWriter arquivo = new FileWriter(caminho);\n"
                + "            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);\n"
                + "            for (int i = 0; i < lista.size(); i++) {\n"
                + "                conteudoDoArquivo.write(lista.get(i).toStringCSV() + System.getProperty(\"line.separator\"));//+ System.getProperty(\"line.separator\")); // \n"
                + "            }\n"
                + "            conteudoDoArquivo.close();\n"
                + "        } catch (Exception e) {//Catch exception if any\n"
                + "            System.err.println(\"Error: \" + e.getMessage());\n"
                + "            return 1; //houve erro\n"
                + "        }\n"
                + "        return 0;\n"
                + "    }");
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("}");
        //=========================================

        arquivoDestino = pastaDestino + "ControleDaLista" + arq + ".java";
        //salva no arquivoDestino a cg
        int salvarArquivo = file.salvarArquivo(arquivoDestino, cg);

        System.out.println("A classe " + "ControleDaLista" + arq + ".java foi gerada");

    }

    //-----------------------------------------------  GUI  ---------------------------------
    public void GerarGui() {
        //transferir dados do arquivo texto para um List
        getTextoDescritivo();

        //inicio da geração do código fonte do novo programa 
        cg.clear();
        cg.add("package ProgramaGerado;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.FocusEvent;\n"
                + "import java.awt.event.FocusListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.io.File;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.WindowConstants;\n"
                + "\n"
                + "public class GUI" + arq + " extends JFrame {\n");

        cg.add("ImageIcon iconeCreate = new ImageIcon(getClass().getResource(\"/icones/create.png\"));\n"
                + "    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource(\"/icones/retrieve.png\"));\n"
                + "    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource(\"/icones/update.png\"));\n"
                + "    ImageIcon iconeDelete = new ImageIcon(getClass().getResource(\"/icones/delete.png\"));\n"
                + "    ImageIcon iconeSave = new ImageIcon(getClass().getResource(\"/icones/save.png\"));\n"
                + "    ImageIcon iconeCancel = new ImageIcon(getClass().getResource(\"/icones/cancel.png\"));\n"
                + "    ImageIcon iconeListar = new ImageIcon(getClass().getResource(\"/icones/list.png\"));\n"
                + "    JButton btnCreate = new JButton(iconeCreate);\n"
                + "    JButton btnRetrieve = new JButton(iconeRetrieve);\n"
                + "    JButton btnUpdate = new JButton(iconeUpdate);\n"
                + "    JButton btnDelete = new JButton(iconeDelete);\n"
                + "    JButton btnSave = new JButton(iconeSave);\n"
                + "    JButton btnCancel = new JButton(iconeCancel);\n"
                + "    JButton btnList = new JButton(iconeListar);");

        String[] aux;
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("JLabel label" + primMaius(aux[0]) + " = new JLabel(\"" + primMaius(aux[0]) + "\");" + "\n");
            cg.add("JTextField textField" + primMaius(aux[0]) + " = new JTextField(" + aux[2] + ");" + "\n");
        }
        cg.add("  JPanel aviso = new JPanel();\n"
                + "    JLabel labelAviso = new JLabel(\"\");");

        cg.add(" String acao = \"\";//variavel para facilitar insert e update\n"
                + "    ControleDaLista" + arq + " cl = new ControleDaLista" + arq + "();\n");

        cg.add(arq + " " + primMinus(arq) + ";\n"
                + arq + " " + primMinus(arq) + "Original;");

        cg.add("    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {\n"
                + "        btnCreate.setEnabled(c);\n"
                + "        btnRetrieve.setEnabled(r);\n"
                + "        btnUpdate.setEnabled(u);\n"
                + "        btnDelete.setEnabled(d);\n"
                + "        btnList.setEnabled(r);\n"
                + "    }\n");

        cg.add(" public void mostrarBotoes(boolean visivel) {\n"
                + "        btnCreate.setVisible(visivel);\n"
                + "        btnRetrieve.setVisible(visivel);\n"
                + "        btnUpdate.setVisible(visivel);\n"
                + "        btnDelete.setVisible(visivel);\n"
                + "        btnList.setVisible(visivel);\n"
                + "        btnSave.setVisible(!visivel);\n"
                + "        btnCancel.setVisible(!visivel);\n"
                + "    }\n");

        String parametros = "";
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            parametros = parametros + "boolean " + aux[0] + ",";
        }
        cg.add("private void habilitarAtributos(" + parametros.substring(0, parametros.length() - 1) + "){");
        aux = textoDescritivo.get(0).split(";");
        cg.add("if (" + aux[0] + "){");
        cg.add("textField" + primMaius(aux[0]) + ".requestFocus();");
        cg.add("textField" + primMaius(aux[0]) + ".selectAll();\n}");
        cg.add("textField" + primMaius(aux[0]) + ".setEnabled(" + aux[0] + ");");
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("textField" + primMaius(aux[0]) + ".setEditable(" + aux[0] + ");");
        }
        cg.add("}\n\n");
        cg.add("public void zerarAtributos() {");
        for (int i = 1; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("textField" + primMaius(aux[0]) + ".setText(\"\");");
        }
        cg.add("}\n");

        cg.add("public GUI" + arq + "() {\n"
                + "        setTitle(\"" + arq + "\");\n"
                + "        try {\n"
                + "            File arq = new File(\"" + arq + ".dat\"); //tenta abrir o arquivo\n"
                + "            if (arq.exists()) { //se o arquivo já existe, abre e lê os dados\n"
                + "                cl.abrirArquivo(\"" + arq + ".dat\");\n"
                + "            }\n"
                + "        } catch (Exception e) {\n"
                + "            System.out.println(\"arquivo não encontrado\");\n"
                + "        }");

        cg.add(" setSize(600, 400);//tamanho da janela\n"
                + "        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado\n"
                + "        setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "\n"
                + "        atvBotoes(false, true, false, false);\n");
        String habilitarAtributos = "";
        String habilitarAtributosInverso = "";
        for (int i = 1; i < textoDescritivo.size(); i++) {
            habilitarAtributos += ",false";
            habilitarAtributosInverso += ",true";
        }
        cg.add("habilitarAtributos(true" + habilitarAtributos + ");");

        cg.add("btnCreate.setToolTipText(\"Inserir novo registro\");\n"
                + "        btnRetrieve.setToolTipText(\"Pesquisar por chave\");\n"
                + "        btnUpdate.setToolTipText(\"Alterar\");\n"
                + "        btnDelete.setToolTipText(\"Excluir\");\n"
                + "        btnList.setToolTipText(\"Listar todos\");\n"
                + "        btnSave.setToolTipText(\"Salvar\");\n"
                + "        btnCancel.setToolTipText(\"Cancelar\");\n");

        aux = textoDescritivo.get(0).split(";");
        cg.add(" JToolBar Toolbar1 = new JToolBar();\n"
                + "        Toolbar1.add(label" + primMaius(aux[0]) + ");\n"
                + "        Toolbar1.add(textField" + primMaius(aux[0]) + ");\n"
                + "        Toolbar1.add(btnRetrieve);\n"
                + "        Toolbar1.add(btnCreate);\n"
                + "        Toolbar1.add(btnUpdate);\n"
                + "        Toolbar1.add(btnDelete);\n"
                + "        Toolbar1.add(btnSave);\n"
                + "        Toolbar1.add(btnCancel);\n"
                + "        Toolbar1.add(btnList);");

        cg.add("  btnSave.setVisible(false);\n"
                + "        btnCancel.setVisible(false);");

        cg.add("  //atributos\n"
                + "        JPanel centro = new JPanel();\n"
                + "        centro.setLayout(new GridLayout(" + (textoDescritivo.size() + 1) + " , 2));");

        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("centro.add(label" + primMaius(aux[0]) + ");\n");
            cg.add("centro.add(textField" + primMaius(aux[0]) + ");\n");
        }

        cg.add("aviso.add(labelAviso);\n"
                + "        aviso.setBackground(Color.yellow);");
        cg.add("cp.add(Toolbar1, BorderLayout.NORTH);\n"
                + "        cp.add(centro, BorderLayout.CENTER);\n"
                + "        cp.add(aviso, BorderLayout.SOUTH);");

        aux = textoDescritivo.get(0).split(";");

        cg.add("textField" + primMaius(aux[0]) + ".requestFocus();");
        cg.add("textField" + primMaius(aux[0]) + ".selectAll();");
        cg.add("textField" + primMaius(aux[0]) + ".setBackground(Color.GREEN);");
        cg.add("labelAviso.setText(\"Digite uma placa e clic [Pesquisar]\");");

        cg.add("\n                     // setLocationRelativeTo(null); // posiciona no centro da tela principal\n"
                + "        setLocation(300, 200);\n"
                + "        setVisible(true);//faz a janela ficar visível  ");

        cg.add("\n\n// Listeners\n"
                + "        btnRetrieve.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {");
        cg.add("" + primMinus(arq) + " = new " + arq + "();");
        cg.add("textField" + primMaius(aux[0]) + ".setText(textField"
                + primMaius(aux[0]) + ".getText().trim());//caso tenham sido digitados espaços\n\n");

        cg.add("if (textField" + primMaius(aux[0]) + ".getText().equals(\"\")) {\n"
                + "                    JOptionPane.showMessageDialog(null, \"Deve ser informado um valor para esse campo\");\n"
                + "                    textField" + primMaius(aux[0]) + ".requestFocus();\n"
                + "                    textField" + primMaius(aux[0]) + ".selectAll();\n"
                + "                } else {");

        cg.add(primMinus(arq) + ".set" + primMaius(aux[0]) + "(Integer.valueOf(textField" + primMaius(aux[0]) + ".getText()));\n"
                + "                   " + primMinus(arq) + " = " + " cl.buscarComPesquisaBinaria(" + primMinus(arq) + ");\n"
                + "                    if (" + primMinus(arq) + " != null) { //se encontrou na lista\n"
        );
        for (int i = 1; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("textField" + primMaius(aux[0]) + ".setText(" + primMinus(arq) + ".get" + primMaius(aux[0]) + "());");
        }

        cg.add("atvBotoes(false, true, true, true);\n");

        cg.add("habilitarAtributos(true" + habilitarAtributos + ");");

        cg.add("                        labelAviso.setText(\"Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]\");\n"
                + "                        acao = \"encontrou\";\n"
                + "                        livroOriginal = livro;"
                + "                    } else {\n"
                + "                        atvBotoes(true, true, false, false);\n"
                + "                        zerarAtributos();\n"
                + "                        labelAviso.setText(\"Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]\");\n"
                + "                    }\n"
                + "                }\n"
                + "    }\n"
                + "        });\n\n");

        aux = textoDescritivo.get(1).split(";");
        cg.add("  btnCreate.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                zerarAtributos();\n"
                + "                habilitarAtributos(false" + habilitarAtributosInverso + ");\n"
                + "                textField" + primMaius(aux[0]) + ".requestFocus();\n"
                + "                mostrarBotoes(false);\n"
                + "                labelAviso.setText(\"Preencha os campos e clic [Salvar] ou clic [Cancelar]\");\n"
                + "                acao = \"insert\";\n"
                + "            }\n"
                + "        });");

        cg.add("btnSave.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                if (acao.equals(\"insert\")) {");
        cg.add(primMinus(arq) + " = new " + primMaius(arq) + "();");
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            if (i == 0) {
                cg.add(primMinus(arq) + ".set" + primMaius(aux[0])
                        + "(Integer.valueOf(textField" + primMaius(aux[0]) + ".getText()));");
            } else {
                cg.add(primMinus(arq) + ".set" + primMaius(aux[0])
                        + "(textField" + primMaius(aux[0]) + ".getText());");
            }
        }
        cg.add("             cl.inserir(" + primMinus(arq) + ");\n"
                + "                    habilitarAtributos(true" + habilitarAtributos + ");\n"
                + "                    mostrarBotoes(true);\n"
                + "                    atvBotoes(false, true, false, false);\n"
                + "                     labelAviso.setText(\"Registro inserido...\");\n"
                + "                } else {  //acao = update\n");

        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            if (i == 0) {
                cg.add(primMinus(arq) + ".set" + primMaius(aux[0])
                        + "(Integer.valueOf(textField" + primMaius(aux[0]) + ".getText()));");
            } else {
                cg.add(primMinus(arq) + ".set" + primMaius(aux[0])
                        + "(textField" + primMaius(aux[0]) + ".getText());");
            }
        }

        cg.add("                    cl.alterar(" + primMinus(arq) + "Original," + primMinus(arq) + ");\n"
                + "                    mostrarBotoes(true);\n"
                + "                    habilitarAtributos(true" + habilitarAtributos + ");\n"
                + "                    atvBotoes(false, true, false, false);\n"
                + "                     labelAviso.setText(\"Registro atualizado...\");\n"
                + "                }\n"
                + "            }\n"
                + "        });");

        cg.add("  btnCancel.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                zerarAtributos();\n"
                + "                atvBotoes(false, true, false, false);\n"
                + "                habilitarAtributos(true" + habilitarAtributos + ");\n"
                + "                mostrarBotoes(true);\n"
                + "            }\n"
                + "        });");

        cg.add("  btnList.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "               \n"
                + "                acao = \"list\";\n"
                + "                GUIListagem" + arq + " guiListagem = new GUIListagem" + arq + "(cl.getLista());\n"
                + "            }\n"
                + "        });");

        cg.add(" btnUpdate.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                acao = \"update\";\n"
                + "                mostrarBotoes(false);\n"
                + "                habilitarAtributos(false" + habilitarAtributosInverso + ");\n"
                + "            }\n"
                + "        });");
        cg.add("\n//---------------------------------------------------------\n");
        aux = textoDescritivo.get(0).split(";");
        cg.add("   btnDelete.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,\n"
                + "                        \"Confirma a exclusão do registro <ID = \" + " + primMinus(arq)
                + ".get" + primMaius(aux[0]) + "() + \">?\", \"Confirm\",\n"
                + "                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {\n"
                + "                    labelAviso.setText(\"Registro excluído...\");\n"
                + "                    cl.excluir(" + primMinus(arq) + ");\n"
                + "                    zerarAtributos();\n"
                + "                    textField" + primMaius(aux[0]) + ".requestFocus();\n"
                + "                    textField" + primMaius(aux[0]) + ".selectAll();\n"
                + "                }\n"
                + "            }\n"
                + "        });");

        cg.add(" textField" + primMaius(aux[0]) + ".addFocusListener(new FocusListener() {\n"
                + "            @Override\n"
                + "            public void focusGained(FocusEvent fe) {\n"
                + "                textField" + primMaius(aux[0]) + ".setBackground(Color.GREEN);\n"
                + "                if (acao != \"encontrou\") {\n"
                + "                    labelAviso.setText(\"Digite uma " + primMaius(aux[0]) + " e clic [Pesquisar]\");\n"
                + "                }\n"
                + "            }\n"
                + "\n"
                + "            @Override\n"
                + "            public void focusLost(FocusEvent fe) {\n"
                + "                textField" + primMaius(aux[0]) + ".setBackground(Color.white);\n"
                + "            }\n"
                + "        });");
        for (int i = 0; i < textoDescritivo.size(); i++) {
            aux = textoDescritivo.get(i).split(";");
            cg.add("textField" + primMaius(aux[0]) + ".addFocusListener(new FocusListener() { //ao receber o foco, fica verde\n"
                    + "            @Override\n"
                    + "            public void focusGained(FocusEvent fe) {\n"
                    + "                textField" + primMaius(aux[0]) + ".setBackground(Color.GREEN);\n"
                    + "            }\n"
                    + "\n"
                    + "            @Override\n"
                    + "            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco\n"
                    + "                textField" + primMaius(aux[0]) + ".setBackground(Color.white);\n"
                    + "            }\n"
                    + "        });\n");
        }
        cg.add(" setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco\n"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                //antes de sair, salvar a lista em disco\n"
                + "                cl.salvarArquivo(\"" + arq + ".dat\");\n"
                + "                // Sai do sistema  \n"
                + "                System.exit(0);\n"
                + "            }\n"
                + "        });");
        cg.add("    }\n"
                + "public static void main(String[] args) {\n"
                + "        new GUI" + arq + "();\n"
                + "    }"
                + "}");

        arquivoDestino = pastaDestino + "GUI" + arq + ".java";
        //salva no arquivoDestino a cg
        int salvarArquivo = file.salvarArquivo(arquivoDestino, cg);

        System.out.println("A classe " + "GUI" + arq + ".java foi gerada");

    }
    //-----------------------------------------------  GUIListagem  ---------------------------------

    public void gerarGUIListagem() {
        getTextoDescritivo();

        cg.clear();
        cg.add("package ProgramaGerado;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.ScrollPane;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JToolBar;\n"
                + "\n"
                + "// @author Radames\n"
                + "public class GUIListagem" + arq + " extends JDialog {\n"
                + "JPanel painelTa = new JPanel();\n"
                + "    ScrollPane scroll = new ScrollPane();\n"
                + "    JTextArea ta = new JTextArea();\n"
                + "    public GUIListagem" + arq + "(List<" + arq + "> texto) {   \n"
                + "        setTitle(\"Listagem\");\n"
                + "        setSize(500, 180);//tamanho da janela\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe\n"
                + "        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado\n"
                + "        setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "        setModal(true);\n"
                + "        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "        \n"
                + "        JToolBar toolBar = new JToolBar();");
        cg.add("ta.setText(\"\");\n"
                + "        for (int i = 0; i < texto.size(); i++) {");
        cg.add("ta.append(texto.get(i).toStringCSV()+\"\\n\");");

        cg.add("}\n"
                + "        \n"
                + "        scroll.add(ta);\n"
                + "        painelTa.add(scroll);\n"
                + "        \n"
                + "        cp.add(toolBar, BorderLayout.NORTH);\n"
                + "        cp.add(scroll,BorderLayout.CENTER);\n"
                + "        \n"
                + "      //  setLocationRelativeTo(null); // posiciona no centro da tela principal\n"
                + "        setLocation(400, 300);\n"
                + "        setVisible(true);//faz a janela ficar visível        \n"
                + "    }\n"
                + "}");

        arquivoDestino = pastaDestino + "GUIListagem" + arq + ".java";
        //salva no arquivoDestino a cg
        int salvarArquivo = file.salvarArquivo(arquivoDestino, cg);

        System.out.println("A classe " + "ControleDaLista" + arq + ".java foi gerada");

    }
}

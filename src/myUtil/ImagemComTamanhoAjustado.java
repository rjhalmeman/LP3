package myUtil;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author radames
 */
public class ImagemComTamanhoAjustado {

    //para ajustar o tamanho de uma imagem
    public Icon getImagem(int x, int y, String caminho) {
        JLabel label = new JLabel();

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource(caminho.trim()));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(800, 600, Image.SCALE_FAST));

            label = new JLabel();
            label.setIcon(icone);
        } catch (Exception e) {
            String mensagem = "<html><u>Erro ao carregar a imagem</u>" + "<br/>" + "<i>" + caminho + "</i></html>";
            JOptionPane.showMessageDialog(null, mensagem);
        }
        return label.getIcon();
    }

}

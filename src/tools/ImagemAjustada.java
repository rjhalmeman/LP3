package tools;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author radames
 */
public class ImagemAjustada {

    ImageIcon icone;

    public ImageIcon getImagemAjustada(int largura, int altura, String caminhoDaImagem) {
        ImageIcon icon = null;

        try {
            Image image = new ImageIcon(caminhoDaImagem).getImage();

            // Verifica se a imagem excede 2000 pixels de largura ou altura
            if (image.getWidth(null) > largura || image.getHeight(null) > altura) {
                // Calcula a proporção de redimensionamento
                double proporcaoLargura = (double) largura / image.getWidth(null);
                double proporcaoAltura = (double) altura / image.getHeight(null);
                double proporcaoRedimensionamento = Math.min(proporcaoLargura, proporcaoAltura);

                // Redimensiona a imagem para atender às dimensões máximas de 2K
                int novaLargura = (int) (image.getWidth(null) * proporcaoRedimensionamento);
                int novaAltura = (int) (image.getHeight(null) * proporcaoRedimensionamento);
                Image scaledImage = image.getScaledInstance(novaLargura, novaAltura, Image.SCALE_FAST);
                icon = new ImageIcon(scaledImage);

            } else {
                // Se a imagem já estiver dentro das dimensões desejadas, utiliza a imagem original
                icon = new ImageIcon(image);
            }
        } catch (Exception e) {
            icon = null;
        }
        return icon;
    }

}

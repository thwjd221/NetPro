/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Image;
import java.net.URL;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class Base {
    public static ImageIcon Img(String path){
        Image image = null;
         try {
                URL url = new URL(path);
                image = ImageIO.read(url);
	} catch (IOException e) {
	}
        return new ImageIcon(image);
    }
}
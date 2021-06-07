
package Client;

import java.awt.Image;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Base {
    public static Image Img(String path){
        Image image = null;
         try {
                URL url = new URL(path);
                image = ImageIO.read(url);
	} catch (IOException e) {
            
	}
        return image;
    }
}
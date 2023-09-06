package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject {
	public ObjectKey () {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key_1.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

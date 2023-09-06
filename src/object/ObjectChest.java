package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject{
	public ObjectChest () {
		name = "Chest";
		collision = true;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

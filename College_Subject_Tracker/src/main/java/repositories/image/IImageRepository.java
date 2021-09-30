package repositories.image;

import javafx.scene.image.Image;
import models.Subject;

import java.util.List;
import java.util.Map;

public interface IImageRepository {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Abstract methods
	//
	public Map<String, Image> all();
	public Image addImage(String key, Image image);
	public Image deleteImage(String key);
	public Image getImage(String key);
}

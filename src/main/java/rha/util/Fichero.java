package rha.util;

import org.springframework.web.multipart.MultipartFile;

public class Fichero {
	
	public static Boolean esImagen(MultipartFile f) {
		Boolean res = false;
		
		if(
				f.getContentType().equals("image/jpeg")		||	// .jpeg .jpg	JPEG images
				f.getContentType().equals("image/png")		||	// .png			Portable Network Graphics
				f.getContentType().equals("image/gif")		||	// .gif			Graphics Interchange Format (GIF)				
				f.getContentType().equals("image/tiff")		||	// .tif .tiff	Tagged Image File Format (TIFF)
				f.getContentType().equals("image/webp")			// .webp		WEBP image				
			)		
			res = true;
		
		return res;
	}
	
	public static Boolean esPDF(MultipartFile f) {
		return (f.getContentType().equals("application/pdf"));
	}
}

package main;

import java.awt.image.BufferedImage;

public class Rotate implements Transform{
	
	BufferedImage source, result, tmp;
	double theta;
	int Xc, Yc;
	private int Xp;
	private int Yp;
	
	public void setRotationAngle(double ratio) {
		theta = ratio;
	}
	
	public void setCenterPoint(int Xcenter, int Ycenter) {
		Xc = Xcenter;
		Yc = Ycenter;
	}

	@Override
	public void setSourceData(Object src) {
		source= (BufferedImage)src;
		//result= new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	}

	@Override
	public void calculate() {
		result= new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		// perform rotation and store results in result
		//upsampling per evitare di perdere pixel durante la rotazione
		double ratio = 2.1;
		tmp = new BufferedImage((int)(source.getWidth()*ratio), (int)(source.getHeight()*ratio), source.getType());
		int band = result.getRaster().getNumBands();
		int i, j, x, y;
		for( x = 0; x < tmp.getWidth(); x++) {
			for( y = 0; y < tmp.getHeight(); y++) {
				 i = (int) (x / ratio);
				 j = (int) (y / ratio);
				for(int c = 0; c < band; c++) {
					int pixel = source.getRaster().getSample(i, j, c);
					tmp.getRaster().setSample(x, y, c, pixel);
				}
			}
		}
		
		//rotazione dell'immagine
		for( x = tmp.getWidth()/2; x < tmp.getWidth(); x++) {
			for( y = tmp.getHeight()/2; y < tmp.getHeight(); y++) {
				for(int c = 0; c < band; c++) {
					double pixel = tmp.getRaster().getSample((x+tmp.getWidth())/2, (y+tmp.getHeight())/2, c);
					rotationMatrix(x, y);
					Xp = (int)(Xp/ratio);
					Yp= (int)(Yp/ratio);
					if(Xp >= 0 && Yp >= 0 && Xp < result.getWidth() && Yp < result.getHeight() ) {
						result.getRaster().setSample(Xp, Yp, c, pixel);
					}
				}
			}			
		}
	}

	private void rotationMatrix(int x, int y) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		Xp = (int) ((x*cos) + (y*sin)) + Xc;
		Yp = (int) ((x* -cos) + (y*sin)) + Yc;
	}

	@Override
	public Object getResult() {
		return result;
	}

}

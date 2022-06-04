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
		result= new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	}

	@Override
	public void calculate() {
		// perform rotation and store results in result
		
		double ratio = 2.1;
		tmp = new BufferedImage((int)(source.getWidth()*ratio), (int)(source.getHeight()*ratio), source.getType());
		int band = result.getRaster().getNumBands();
		for(int x = 0; x < tmp.getWidth(); x++) {
			for(int y = 0; y < tmp.getHeight(); y++) {
				int i = (int) (x / ratio);
				int j = (int) (y / ratio);
				for(int c = 0; c < band; c++) {
					int pixel = source.getRaster().getSample(i, j, c);
					tmp.getRaster().setSample(x, y, c, pixel);
				}
			}
		}
		
		for(int x = tmp.getWidth()/2; x < tmp.getWidth(); x++) {
			for(int y = tmp.getHeight()/2; y < tmp.getHeight(); y++) {
				for(int c = 0; c < band; c++) {
					double pixel = tmp.getRaster().getSampleDouble(x+tmp.getWidth()/2, y+tmp.getHeight()/2, c);
					rotationMatrix(x, y);
					Xp = (int)(Xp/ratio);
					Yp=(int) (Yp/ratio);
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
		Xp = (int) ( ( x*Math.cos(theta) ) + ( y*Math.sin(theta) ) ) + Xc;
		Yp = (int) ( ( x* -( Math.cos(theta) ) ) + ( y*Math.sin(theta) ) ) + Yc;
	}

	@Override
	public Object getResult() {
		return result;
	}

}

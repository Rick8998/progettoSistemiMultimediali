package main;

import java.awt.image.BufferedImage;

public class Rotate implements Transform{
	
	private BufferedImage source, result;
	private double theta;
	private int Xc, Yc;
	
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
		
		for(int numBands = 0; numBands < source.getRaster().getNumBands(); numBands++) {
			for(int i = 0; i < source.getWidth(); i++) {
				for(int j = 0; j < source.getHeight(); j++) {
					double new_i_Val = (i - Xc) * (Math.cos(Math.toRadians(theta))) - (j - Yc) * (Math.sin(Math.toRadians(theta))) + Xc;
					double new_j_Val = (i - Xc) * (Math.sin(Math.toRadians(theta))) + (j - Yc) * (Math.cos(Math.toRadians(theta))) + Yc;
					
					if((new_i_Val < source.getWidth() && new_j_Val < source.getHeight()) && (new_i_Val >= 0 && new_j_Val >= 0)) {
						double pxl = source.getRaster().getSample((int) new_i_Val, (int) new_j_Val, numBands);
						result.getRaster().setSample(i, j, numBands, pxl);
					} else {
						result.getRaster().setSample(i, j, numBands, 0);
					}
				}
			}
		}
	}

	@Override
	public Object getResult() {
		return result;
	}
	
}

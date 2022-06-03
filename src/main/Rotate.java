package main;

import java.awt.image.BufferedImage;

public class Rotate implements Transform{
	
	BufferedImage source, result;
	double theta;
	int Xc, Yc;
	
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
		//do to...
	}

	@Override
	public Object getResult() {
		return result;
	}

}

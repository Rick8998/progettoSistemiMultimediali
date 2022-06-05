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
		double[][] matrix = invert(getMatrix());
		for(int xi = 0; xi < source.getWidth(); xi++) {
			for(int yi = 0; yi < source.getHeight(); yi++) {
				int i = xi - Xc;
				int j = yi - Yc;
				int x = (int) (i*matrix[0][0] + j*matrix[1][0])+Xc;
				int y = (int) (i*matrix[0][1] + j*matrix[1][1])+Yc;
				int sample = 0;
				int bands = source.getRaster().getNumBands();
				for(int b = 0; b < bands; b++) {
					if(x < 0 || x >= source.getWidth()) sample =0;
					else if(y < 0 ||y >= source.getHeight()) sample = 0;
					else sample = source.getRaster().getSample(x, y, b);
					result.getRaster().setSample(xi, yi, b, sample);
				}
			}
		}
	}

	@Override
	public Object getResult() {
		return result;
	}
	
	private double[][] invert(double[][] matrix) {
		double det=getDet(matrix);
		double[][] trasp=traspose(matrix);
		double[][] inverse=new double[2][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				inverse[i][j]=Math.pow(-1, i+j)*trasp[i][j]/det;
		return inverse;
	}

	private double[][] traspose(double[][] matrix) {
		double trasp[][]=new double[2][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				trasp[i][j]=matrix[2-i-1][2-j-1];
		return trasp;
	}

	/*
	 * Calcola il determinante della matrice
	 */
	private double getDet(double[][] matrix) {
		double a=matrix[0][0];
		double b=matrix[1][0];
		double c=matrix[0][1];
		double d=matrix[1][1];
		return a*d - b*c;
	}
	
	/*
	 * calcola i valori della matrice prendendo l’angolo in alto a sx, convertendolo in radianti ed calcolando coseni e seni:
	 */
	private double[][] getMatrix() {
		double degree = Math.toRadians(theta);
		double[][] matrix = new double[2][2];
		matrix[0][0]=Math.cos(degree);
		matrix[0][1]=-Math.sin(degree);
		matrix[1][0]=Math.sin(degree);
		matrix[1][1]=Math.cos(degree);
		return matrix;
	}

	

}

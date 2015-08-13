package br.com.checker.emag;
import java.awt.Color;
import java.text.DecimalFormat;

public class AvaliadorContraste {
	
	private Color corMaisClara;
	
	private Color corMaisEscura;
	
	private double contraste;
	
	private final double fatorDeLuminosidade = 0.05;
	
	private final double valorMaximoDaCorSRGB = 255.0;
	
	private final double multiplicadorDoVermelho = 0.2126;
	
	private final double multiplicadorDoVerde = 0.7152;
	
	private final double multiplicadorDoAzul = 0.0722;
	
	private final double limiteSRGB = 0.03928;
	
	private final double divisorSRGB = 12.92;
	
	private final double divisorAlternativoSRGB = 1.055;
	
	private final double fatorDeCorrecaoSRGB = 0.055;
	
	private final double potenciaSRGB = 2.4;
	
	
	/*
	 * Construtor padrão que seta as cores do avaliador como
	 * banca e branca para caso se chame o método avaliar não ocorra
	 * nenhuma Exception.
	 */
	public AvaliadorContraste() {
		this.setCores(Color.WHITE, Color.WHITE);
	}
	
	/*
	 * Construtor para passar as cores que se deseja avaliar.
	 */
	public AvaliadorContraste(Color cor1, Color cor2) {
		this.setCores(cor1, cor2);
	}
	
	
	/*
	 * Seta as cores que serão avaliadas.
	 */
	public void setCores(Color cor1, Color cor2) {
		if (cor1 != null && cor2 != null) {
			int cor1Soma = cor1.getRed() + cor1.getGreen() + cor1.getBlue();
			int cor2Soma = cor2.getRed() + cor2.getGreen() + cor2.getBlue();
			if (cor1Soma >= cor2Soma) {
				this.corMaisClara = cor1;
				this.corMaisEscura = cor2;
			} else {
				this.corMaisClara = cor2;
				this.corMaisEscura = cor1;
			}
		}
	}
	
	/*
	 * Método que calcula e armazena o contraste das cores avaliadas
	 * no atributo 'constraste'.
	 */
	public void avaliar() {
		double L1;
		double L2;
		
		L1 = calcularLuminecencia(this.corMaisClara);
		L2 = calcularLuminecencia(this.corMaisEscura);
		
		this.contraste = (L1 + this.fatorDeLuminosidade) / (L2 + fatorDeLuminosidade);
	}
	
	/*
	 * Método para calcular a luminecência de uma cor.
	 */
	private double calcularLuminecencia(Color cor) {
		double L;
		
		double R;
		double G;
		double B;
		
		double RsRGB;
		double GsRGB;
		double BsRGB;
		
		RsRGB = cor.getRed() / this.valorMaximoDaCorSRGB;
		GsRGB = cor.getGreen() / this.valorMaximoDaCorSRGB;
		BsRGB = cor.getBlue() / this.valorMaximoDaCorSRGB;
		
		if (RsRGB <= this.limiteSRGB) {
			R = RsRGB / this.divisorSRGB;
		} else {
			R = Math.pow(((RsRGB + this.fatorDeCorrecaoSRGB) / this.divisorAlternativoSRGB), this.potenciaSRGB);
		}
		
		if (GsRGB <= this.limiteSRGB) {
			G = GsRGB / this.divisorSRGB;
		} else {
			G = Math.pow(((GsRGB + this.fatorDeCorrecaoSRGB) / this.divisorAlternativoSRGB), this.potenciaSRGB);
		}
		
		if (BsRGB <= this.limiteSRGB) {
			B = BsRGB / this.divisorSRGB;
		} else {
			B = Math.pow(((BsRGB + this.fatorDeCorrecaoSRGB) / this.divisorAlternativoSRGB), this.potenciaSRGB);
		}
		
		
		L = (R * this.multiplicadorDoVermelho) + (G * this.multiplicadorDoVerde) + (B * this.multiplicadorDoAzul);
		
		return L;
	}
	
	/*
	 * Metodo que retorna o valor armazenado no atributo 'contraste'.
	 */
	public double getContraste() {
		return this.contraste;
	}
	
	/*
	 * Método que retorna o valor armazenado no atributo 'contraste'
	 * devidamente formatado.
	 */
	public String getContrasteFormatado() {
		String strContraste = String.valueOf(new DecimalFormat("#.##").format(this.contraste));
		return strContraste.replace(".", ",");
	}
}

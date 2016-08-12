package br.com.checker.emag;
import java.awt.Color;
import java.text.DecimalFormat;

/**
 * Classe para avaliar o contraste.
 */
public class AvaliadorContraste {

   private Color corMaisClara;

   private Color corMaisEscura;

   private double contraste;

   private final double fatorDeLuminosidade = 0.05;

   private final double valorMaximoDaCoredsRGB = 255.0;

   private final double multiplicadorDoVermelho = 0.2126;

   private final double multiplicadorDoVerde = 0.7152;

   private final double multiplicadorDoAzul = 0.0722;

   private final double limiteSRGB = 0.03928;

   private final double divisoredsRGB = 12.92;

   private final double divisorAlternativoSRGB = 1.055;

   private final double fatorDeCorrecaoSRGB = 0.055;

   private final double potenciaSRGB = 2.4;

   /**
    * Construtor padrão que seta as cores do avaliador como
    * banca e branca para caso se chame o método avaliar não ocorra
    * nenhuma Exception.
    */
   public AvaliadorContraste() {
     this.setCores(Color.WHITE, Color.WHITE);
   }

   /**
    * Construtor para passar as cores que se deseja avaliar.
    * @param cor1 Cor a ser avaliada
    * @param cor2 Cor a ser avaliada
    */
   public AvaliadorContraste(final Color cor1, final Color cor2) {
     this.setCores(cor1, cor2);
   }

   /**
    * Seta as cores que serão avaliadas.
    * @param cor1 Cor a ser avaliada
    * @param cor2 Cor a ser avaliada
    */
   public final void setCores(final Color cor1, final Color cor2) {
     if (cor1 != null && cor2 != null) {
        int cor1Soma = cor1.getRed() + cor1.getGreen()
                    + cor1.getBlue();
        int cor2Soma = cor2.getRed() + cor2.getGreen()
                    + cor2.getBlue();
        if (cor1Soma >= cor2Soma) {
          this.corMaisClara = cor1;
          this.corMaisEscura = cor2;
       } else {
          this.corMaisClara = cor2;
          this.corMaisEscura = cor1;
       }
    }
   }

   /**
    * Método que calcula e armazena o contraste das cores avaliadas
    * no atributo 'constraste'.
    */
   public final void avaliar() {
     double luminecencia1;
     double luminecencia2;

     luminecencia1 = calcularLuminecencia(this.corMaisClara);
     luminecencia2 = calcularLuminecencia(this.corMaisEscura);
     this.contraste = (luminecencia1 + this.fatorDeLuminosidade)
                    / (luminecencia2 + fatorDeLuminosidade);
   }

   /**
    * Método para calcular a luminecência de uma cor.
    * @param cor para ser calculado a luminecência.
    * @return luminecencia da cor
    */
   private double calcularLuminecencia(final Color cor) {
     double limite;
     double red;
     double green;
     double blue;
     double redsRGB;
     double greensRGB;
     double bluesRGB;

     redsRGB = cor.getRed() / this.valorMaximoDaCoredsRGB;
     greensRGB = cor.getGreen() / this.valorMaximoDaCoredsRGB;
     bluesRGB = cor.getBlue() / this.valorMaximoDaCoredsRGB;

     if (redsRGB <= this.limiteSRGB) {
        red = redsRGB / this.divisoredsRGB;
    } else {
        red = Math.pow(((redsRGB + this.fatorDeCorrecaoSRGB)
                   / this.divisorAlternativoSRGB), this.potenciaSRGB);
    }

     if (greensRGB <= this.limiteSRGB) {
        green = greensRGB / this.divisoredsRGB;
    } else {
        green = Math.pow(((greensRGB + this.fatorDeCorrecaoSRGB)
                    / this.divisorAlternativoSRGB), this.potenciaSRGB);
    }

     if (bluesRGB <= this.limiteSRGB) {
        blue = bluesRGB / this.divisoredsRGB;
    } else {
        blue = Math.pow(((bluesRGB + this.fatorDeCorrecaoSRGB)
                   / this.divisorAlternativoSRGB), this.potenciaSRGB);
    }

     limite = (red * this.multiplicadorDoVermelho)
          + (green * this.multiplicadorDoVerde)
          + (blue * this.multiplicadorDoAzul);

     return limite;
  }

  /**
  * Metodo que retorna o valor armazenado no atributo 'contraste'.
  * @return contraste
  */
  public final double getContraste() {
    return this.contraste;
  }

  /**
  * Método que retorna o valor armazenado no atributo 'contraste'
  * devidamente formatado.
  * @return contrasteFormatado
  */
  public final String getContrasteFormatado() {
    String strContraste = String.valueOf(new DecimalFormat("#.##")
                     .format(this.contraste));
    return strContraste.replace(".", ",");
  }
}

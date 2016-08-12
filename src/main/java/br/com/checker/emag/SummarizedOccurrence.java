package br.com.checker.emag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;


public @Getter class SummarizedOccurrence implements Comparable<SummarizedOccurrence> {
    
    public static final String EMPTY_LINES = "---"; 
    
    private String checkPoint;
    private boolean isError;
    //private Set<Integer> lines;
    private List<Integer> lines;
    private String sourceCode;
    private OccurrenceClassification type;
    private Map<String, String> mapDescription = new HashMap<String, String>();
    
    {
            mapDescription.put("1.1", "Respeitar os Padrões Web.");
            mapDescription.put("1.2","Organizar o código HTML de forma lógica e semântica.");
            mapDescription.put("1.3","Utilizar corretamente os níveis de cabeçalho.");
            mapDescription.put("1.4","Ordenar de forma lógica e intuitiva a leitura e tabulação.");
            mapDescription.put("1.5","Fornecer âncoras para ir direto a um bloco de conteúdo.");
            mapDescription.put("1.6","Não utilizar tabelas para diagramação.");
            mapDescription.put("1.7","Separar links adjacentes.");
            mapDescription.put("1.8","Dividir as áreas de informação.");
            mapDescription.put("1.9","Não abrir novas instâncias sem a solicitação do usuário.");
            mapDescription.put("2.1","Disponibilizar todas as funções da página via teclado.");
            mapDescription.put("2.2","Garantir que os objetos programáveis sejam acessíveis.");
            mapDescription.put("2.3","Não criar páginas com atualização automática periódica.");
            mapDescription.put("2.4","Não utilizar redirecionamento automático de páginas.");
            mapDescription.put("2.5","Fornecer alternativa para modificar limite de tempo.");
            mapDescription.put("2.6","Não incluir situações com intermitência de tela.");
            mapDescription.put("2.7","Assegurar o controle do usuário sobre as alterações temporais do conteúdo.");
            mapDescription.put("3.1","Identificar o idioma principal da página.");
            mapDescription.put("3.2","Informar mudança de idioma no conteúdo.");
            mapDescription.put("3.3","Oferecer um título descritivo e informativo à página.");
            mapDescription.put("3.4","Informar o usuário sobre sua localização na página.");
            mapDescription.put("3.5","Descrever links clara e sucintamente.");
            mapDescription.put("3.6","Fornecer alternativa em texto para as imagens do sítio.");
            mapDescription.put("3.7","Utilizar mapas de imagem de forma acessível.");
            mapDescription.put("3.8","Disponibilizar documentos em formatos acessíveis.");
            mapDescription.put("3.9","Em tabelas, utilizar títulos e resumos de forma apropriada.");
            mapDescription.put("3.10","Associar células de dados às células de cabeçalho.");
            mapDescription.put("3.11","Garantir a leitura e compreensão das informações.");
            mapDescription.put("3.12","Disponibilizar uma explicação para siglas, abreviaturas e palavras incomuns.");
            mapDescription.put("4.1","Oferecer contraste mínimo entre plano de fundo e primeiro plano.");
            mapDescription.put("4.2","Não utilizar apenas cor ou outras características sensoriais para diferenciar elementos.");
            mapDescription.put("4.3","Permitir redimensionamento sem perda de funcionalidade.");
            mapDescription.put("4.4","Possibilitar que o elemento com foco seja visualmente evidente.");
            mapDescription.put("5.1","Fornecer alternativa para vídeo.");
            mapDescription.put("5.2","Fornecer alternativa para áudio.");
            mapDescription.put("5.3","Oferecer audiodescrição para vídeo pré-gravado.");
            mapDescription.put("5.4","Fornecer controle de áudio para som.");
            mapDescription.put("5.5","Fornecer controle de animação.");
            mapDescription.put("6.1","Fornecer alternativa em texto para os botões de imagem de formulários.");
            mapDescription.put("6.2","Associar etiquetas aos seus campos.");
            mapDescription.put("6.3","Estabelecer uma ordem lógica de navegação.");
            mapDescription.put("6.4","Não provocar automaticamente alteração no contexto.");
            mapDescription.put("6.5","Fornecer instruções para entrada de dados.");
            mapDescription.put("6.6","Identificar e descrever erros de entrada de dados e confirmar o envio das informações.");
            mapDescription.put("6.7","Agrupar campos de formulário.");
            mapDescription.put("6.8","Fornecer estratégias de segurança específicas ao invés de CAPTCHA.");
            
    }
    
    private SummarizedOccurrence(String checkPoint, boolean isError, List<Integer> lines/*Set<Integer> lines*/,
            OccurrenceClassification type,String sourceCode) {
        
        this.checkPoint = checkPoint;
        this.isError = isError;
        this.lines = lines;
        this.type = type;
        this.sourceCode = sourceCode;
        
        this.sourceCode = this.sourceCode.replaceAll("<", "&lt;");
        this.sourceCode = this.sourceCode.replaceAll(">", "&gt;");
        this.sourceCode = this.sourceCode.replaceAll(" ", "&nbsp");
    }
    
    public static class Builder{
        
        private String checkPoint;
        private boolean isError;
        //private Set<Integer> lines = new TreeSet<Integer>();
        private List<Integer> lines = new ArrayList<Integer>();
        private StringBuilder sourceCode = new StringBuilder();
        private OccurrenceClassification type;
        
        public Builder setCheckPoint(String checkPoint) {
            this.checkPoint = checkPoint;
            return this;
        }
        
        public Builder setIsError(boolean isError) {
            this.isError = isError;
            return this;
        }
        
        public Builder setType(OccurrenceClassification type){
            this.type = type;
            return this;
        }
        
        public Builder addLine(Integer line) {
            
            if(line!=null)
                this.lines.add(line);
            
            return this;
        }
        
        public Builder addSourceCode(Occurrence occurrence) {
            this.sourceCode.append(occurrence.getLine()+": " +occurrence.getTag()+ "\n");
            
            return this;
        }
        
        public SummarizedOccurrence build() {
            return new SummarizedOccurrence(this.checkPoint, this.isError, this.lines,this.type, this.sourceCode.toString());
        }
    }
    
    public String getStringLines() {
        StringBuilder linesString = new StringBuilder();
        
        
        for(Integer line : this.lines) {
            linesString.append(line);
            linesString.append(", ");
        }
        
        return   StringUtils.isEmpty(linesString.toString()) ? EMPTY_LINES : linesString.toString().substring(0, linesString.length() -2);
    }
    
    public boolean isPossuiLinhas() {
        return this.lines != null && !this.lines.isEmpty();
    }
    
    public String getNumberOfOccurrences() { return this.lines.size() !=0 ? String.valueOf(this.lines.size()) : EMPTY_LINES ; }
    public String getDescription() { return this.mapDescription.get(this.getCheckPoint()); }

    public int compareTo(SummarizedOccurrence other) { 
        String[] checkPointSplited = checkPoint.split("\\.");
        String[] checkPointSplitedOther = other.getCheckPoint().split("\\.");
        return Integer.valueOf(checkPointSplited[0]+checkPointSplited[1]).compareTo(Integer.valueOf(checkPointSplitedOther[0]+checkPointSplitedOther[1]));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((checkPoint == null) ? 0 : checkPoint.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SummarizedOccurrence other = (SummarizedOccurrence) obj;
        if (checkPoint == null) {
            if (other.checkPoint != null)
                return false;
        } else if (!checkPoint.equals(other.checkPoint))
            return false;
        return true;
    }
    
    
    
    
}

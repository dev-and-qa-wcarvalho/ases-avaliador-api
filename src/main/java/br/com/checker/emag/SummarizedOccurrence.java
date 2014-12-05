package br.com.checker.emag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;


public @Getter class SummarizedOccurrence implements Comparable<SummarizedOccurrence> {
	
	public static final String EMPTY_LINES = "---"; 
	
	private String checkPoint;
	private boolean isError;
	private Set<Integer> lines;
	private String sourceCode;
	private OccurrenceClassification type;
	private Map<String, String> mapDescription = new HashMap<String, String>();
	
	{
			mapDescription.put("1.1", "\u00C9 essencial seguir os padr\u00F5es de desenvolvimento Web, do W3C (World Wide Web Consortium), com o intuito de maximizar a compatibilidade com atuais e futuros agentes de usu\u00E1rio.");
			mapDescription.put("1.2","O c\u00F3digo HTML deve ser organizado de forma l\u00F3gica e sem\u00E2ntica, ou seja, apresentando os elementos em uma ordem compreens\u00EDvel e correspondendo ao conte\u00FAdo desejado. Assim, marca\u00E7\u00E3o sem\u00E2ntica adequada deve ser utilizada para designar os cabe\u00E7alhos (h1, h2, h3), as listas (ul, ol, dl), texto enfatizado (strong), marca\u00E7\u00E3o de c\u00F3digo (code), marca\u00E7\u00E3o de abreviaturas (abbr), marca\u00E7\u00E3o de cita\u00E7\u00F5es longas (blockquote), etc.");
	        mapDescription.put("1.3","Os n\u00EDveis de cabe\u00E7alho devem ser utilizados de forma l\u00F3gica e sem\u00E2ntica para facilitar a leitura e compreens\u00E3o. Al\u00E9m disso, pessoas acessando uma p\u00E1gina com leitor de tela podem navegar atrav\u00E9s dos cabe\u00E7alhos, pulando de um para outro, agilizando, assim, a navega\u00E7\u00E3o. Conceitualmente, existem seis n\u00EDveis de t\u00EDtulos, sendo o h1 o mais alto, ou seja, dever\u00E1 corresponder ao t\u00EDtulo principal da p\u00E1gina. Dessa forma, cada p\u00E1gina dever\u00E1 ter apenas um h1, o qual poder\u00E1 ser substitu\u00EDdo por uma imagem, mas dever\u00E1 permanecer com seu conte\u00FAdo, mesmo que n\u00E3o visualmente, permitindo a leitura pelo leitor de tela. J\u00E1 os n\u00EDveis do h2 ao h6 poder\u00E3o ser utilizados mais de uma vez na p\u00E1gina, mas sem excesso e com l\u00F3gica textual.");
	        mapDescription.put("1.4","Deve-se criar o c\u00F3digo HTML com uma sequ\u00EAncia l\u00F3gica de leitura para percorrer links, controles de formul\u00E1rios e objetos. Essa sequ\u00EAncia \u00E9 determinada pela ordem que se encontra no c\u00F3digo HTML.");
	        mapDescription.put("1.5","Todas as fun\u00E7\u00F5es da p\u00E1gina desenvolvidas utilizando-se linguagens de script (javascript) dever\u00E3o estar dispon\u00EDveis quando for utilizado apenas o teclado. \u00C9 importante salientar que o foco n\u00E3o dever\u00E1 estar bloqueado ou fixado em um elemento da p\u00E1gina, para que o usu\u00E1rio possa mover-se pelo teclado por todos os elementos.");
	        mapDescription.put("1.6","Devem ser fornecidas \u00E2ncoras, dispon\u00EDveis na barra de acessibilidade, que apontem para links relevantes presentes na mesma p\u00E1gina. Assim, \u00E9 poss\u00EDvel ir ao bloco de conte\u00FAdo desejado. Os links devem ser colocados em lugares estrat\u00E9gicos da p\u00E1gina, como por exemplo, no in\u00EDcio e final do menu, do conte\u00FAdo, etc.");
	        mapDescription.put("1.7","As tabelas devem ser utilizadas apenas para dados tabulares e n\u00E3o para efeitos de disposi\u00E7\u00E3o dos elementos na p\u00E1gina. Para este fim, utilize as folhas de estilo.");
	        mapDescription.put("1.8","Links adjacentes devem ser separados por mais do que simples espa\u00E7os, para que n\u00E3o fiquem confusos, em especial para usu\u00E1rios que utilizam leitor de tela. Para isso, \u00E9 recomendado o uso de listas, onde cada elemento dentro da lista \u00E9 um link. As listas podem ser estilizadas com CSS para que os itens sejam mostrados da maneira desejada, como um ao lado do outro, por exemplo.");
	        mapDescription.put("1.9","N\u00E3o dever\u00E3o ocorrer mudan\u00E7as substanciais em uma p\u00E1gina. Assim, n\u00E3o devem ser utilizados:\nPop-ups\nA abertura de novas abas ou janelas\nO uso do atributo target=\u201C_blank\u201D\nMudan\u00E7as no controle do foco do teclado\nEntre outras, que n\u00E3o tenham sido solicitadas pelo usu\u00E1rio.");
	        mapDescription.put("2.1","Deve-se garantir que scripts, Flash, conte\u00FAdos din\u00E2micos e outros elementos program\u00E1veis sejam acess\u00EDveis. Se n\u00E3o for poss\u00EDvel que o elemento program\u00E1vel seja diretamente acess\u00EDvel, deve ser fornecida uma alternativa em HTML para o conte\u00FAdo. Assim, \u00E9 preciso garantir que o conte\u00FAdo e as funcionalidades de objetos program\u00E1veis sejam acess\u00EDveis aos recursos de tecnologia assistiva e que seja poss\u00EDvel navega\u00E7\u00E3o por teclado.");
	        mapDescription.put("2.2","N\u00E3o devem ser criadas p\u00E1ginas com atualiza\u00E7\u00E3o autom\u00E1tica peri\u00F3dica. Assim, n\u00E3o deve ser utilizada a meta tag refresh, nem outra forma de atualiza\u00E7\u00E3o autom\u00E1tica. P\u00E1ginas que se atualizam automaticamente podem confundir e desorientar os usu\u00E1rios, especialmente usu\u00E1rios que utilizam leitores de tela.");
	        mapDescription.put("2.3","N\u00E3o devem ser utilizadas marca\u00E7\u00F5es para redirecionar para uma nova p\u00E1gina, como a meta tag refresh. Ao inv\u00E9s disso, deve-se configurar o servidor para que o redirecionamento seja transparente para o usu\u00E1rio.");
	        mapDescription.put("2.4","Em uma p\u00E1gina onde h\u00E1 limite de tempo para realizar uma tarefa deve haver a op\u00E7\u00E3o de desligar, ajustar ou prolongar esse limite. \nEssa recomenda\u00E7\u00E3o n\u00E3o se aplica a eventos em que o limite de tempo \u00E9 absolutamente necess\u00E1rio.");
	        mapDescription.put("2.5","N\u00E3o devem ser utilizados efeitos visuais piscantes, intermitentes ou cintilantes. Em pessoas com epilepsia fotosensitiva, o cintilar ou piscar pode desencadear um ataque epil\u00E9tico. A exig\u00EAncia dessa diretriz aplica-se tamb\u00E9m para propaganda de terceiros inserida na p\u00E1gina.\nAs tags blink e marquee n\u00E3o devem ser utilizadas, mesmo em um documento escrito em XHTML personalizado.");
	        mapDescription.put("2.6","Conte\u00FAdos que \u201Cse movem\u201D, rolagens, movimenta\u00E7\u00F5es em geral ou anima\u00E7\u00F5es n\u00E3o devem ser disparadas automaticamente sem o controle do usu\u00E1rio, mesmo em propagandas na p\u00E1gina. Ao usu\u00E1rio deve ser repassado o controle sobre essas movimenta\u00E7\u00F5es (quer seja por escolha de prefer\u00EAncia de visualiza\u00E7\u00E3o da p\u00E1gina, quer por outro m\u00E9todo qualquer acess\u00EDvel a usu\u00E1rio com defici\u00EAncia). Al\u00E9m disso, o usu\u00E1rio deve ser capaz de parar e reiniciar conte\u00FAdos que se movem, sem exce\u00E7\u00E3o. A velocidade desses conte\u00FAdos tamb\u00E9m deve ser pass\u00EDvel de controle pelo usu\u00E1rio, a menos que a implementa\u00E7\u00E3o de mecanismo para alterar a velocidade seja uma tarefa de dif\u00EDcil execu\u00E7\u00E3o (se for necess\u00E1rio realizar uma escolha baseando-se nas limita\u00E7\u00F5es, prefira implementar mecanismos para reduzir a velocidade dos conte\u00FAdo no lugar de aumentar).");
	        mapDescription.put("2.7","Deve-se identificar o principal idioma utilizado nos documentos. A identifica\u00E7\u00E3o \u00E9 feita por meio do atributo lang do HTML e, para documentos XHTML, \u00E9 utilizado o xml:lang.");
	        mapDescription.put("3.1","O t\u00EDtulo da p\u00E1gina deve ser descritivo e informativo, j\u00E1 que essa informa\u00E7\u00E3o ser\u00E1 a primeira lida pelo leitor de tela, quando o usu\u00E1rio acessar a p\u00E1gina. O t\u00EDtulo \u00E9 informado pela tag &lt;title&gt;.");
	        mapDescription.put("3.2","Dever\u00E1 ser fornecido um mecanismo que permita ao usu\u00E1rio orientar-se dentro de um conjunto de p\u00E1ginas, permitindo que ele saiba onde est\u00E1 no momento. Assim, dever\u00E3o ser utilizados breadcrumbs, que s\u00E3o links naveg\u00E1veis em forma de lista hier\u00E1rquica que permitem que o usu\u00E1rio saiba qual o caminho percorrido at\u00E9 chegar \u00E0 p\u00E1gina em que se encontra no momento.");
	        mapDescription.put("3.3","Deve-se identificar claramente o destino de cada link, informando, inclusive, se o link remete a outro s\u00EDtio. Al\u00E9m disso, \u00E9 preciso que o texto do link fa\u00E7a sentido mesmo quando isolado do contexto da p\u00E1gina.\n\u00C9 preciso tomar cuidado para n\u00E3o utilizar o mesmo t\u00EDtulo para dois ou mais links que apontem para destinos diferentes.");
	        mapDescription.put("3.4","Deve ser fornecida uma descri\u00E7\u00E3o para as imagens da p\u00E1gina, utilizando-se o atributo alt. Imagens que n\u00E3o transmitem conte\u00FAdo, ou seja, imagens decorativas, devem ser inseridas por CSS.");
	        mapDescription.put("3.5","Para mapas de imagem do lado do cliente, devem ser fornecidas descri\u00E7\u00F5es atrav\u00E9s do atributo alt para cada uma das zonas ativas, ou seja, para cada um dos links que receber\u00E1 o foco.\nAl\u00E9m dos mapas de imagem do lado do cliente, existem os do lado do servidor. No entanto, \u00E9 recomendada a utiliza\u00E7\u00E3o de mapas de imagem do lado do cliente, j\u00E1 que para mapas de imagem do lado do servidor n\u00E3o \u00E9 poss\u00EDvel fornecer um alt para cada uma das zonas ativas, somente para o mapa como um todo, n\u00E3o sendo poss\u00EDvel, portanto, torn\u00E1-lo acess\u00EDvel.");
	        mapDescription.put("3.6","\nOs documentos devem ser disponibilizados preferencialmente em HTML. Tamb\u00E9m podem ser utilizados arquivos para download no formato ODF, tomando-se os cuidados para que sejam acess\u00EDveis.  Se um arquivo for disponibilizado em PDF, dever\u00E1 ser fornecida uma alternativa em HTML ou ODF. \u00C9 necess\u00E1rio, tamb\u00E9m, informar a extens\u00E3o e o tamanho do arquivo no pr\u00F3prio texto do link.");
	        mapDescription.put("3.7","O t\u00EDtulo da tabela deve ser definido pelo elemento caption e deve ser o primeiro elemento utilizado ap\u00F3s a declara\u00E7\u00E3o do elemento table. Em casos de tabelas extensas, deve ser fornecido um resumo de seus dados atrav\u00E9s do atributo summary que deve ser declarado no elemento table.");
	        mapDescription.put("3.8","Em tabelas de dados simples, a uso apropriado do elemento th para os cabe\u00E7alhos e do elemento td para as c\u00E9lulas de dados \u00E9 essencial para torn\u00E1-las acess\u00EDveis. Para incrementar a acessibilidade, deve-se utilizar os elementos thead, tbody e tfoot, para agrupar as linhas de cabe\u00E7alho, do corpo da tabela e do final, respectivamente, com exce\u00E7\u00E3o de quando a tabela possuir apenas o corpo, sem ter se\u00E7\u00F5es de cabe\u00E7alho e rodap\u00E9. O W3C sugere utilizar o tfoot antes do tbody dentro da defini\u00E7\u00E3o table para que o agente de usu\u00E1rio possa renderizar o rodap\u00E9 antes de receber todas (potencialmente numerosas) linha de dados.");
	        mapDescription.put("3.9","O texto de um s\u00EDtio deve ser de f\u00E1cil leitura e compreens\u00E3o, n\u00E3o exigindo do usu\u00E1rio um n\u00EDvel de instru\u00E7\u00E3o mais avan\u00E7ado do que o ensino fundamental completo. Quando o texto exigir uma capacidade de leitura mais avan\u00E7ada,\u00A0deve estar dispon\u00EDvel\u00A0conte\u00FAdo suplementar ou uma vers\u00E3o mais simplificada do conte\u00FAdo em texto.");
	        mapDescription.put("3.10","Deve estar dispon\u00EDvel uma explica\u00E7\u00E3o que identifique a forma completa ou o significado das abreviaturas e siglas. Para isso, deve ser utilizada a tag abbr. J\u00E1 as palavras que podem ser amb\u00EDguas, desconhecidas ou utilizadas de forma muito espec\u00EDfica, dever\u00E3o ser definidas atrav\u00E9s de um texto adjacente, uma lista de defini\u00E7\u00F5es, um gloss\u00E1rio, ou de qualquer outro modo.");
	        mapDescription.put("3.11","Se algum elemento de uma p\u00E1gina possuir conte\u00FAdo em um idioma diferente do principal, este dever\u00E1 estar identificado pelo atributo lang. Essa recomenda\u00E7\u00E3o n\u00E3o se aplica para nomes pr\u00F3prios, termos t\u00E9cnicos ou locu\u00E7\u00F5es pr\u00F3prias da l\u00EDngua que sejam compreendidas no contexto.");
	        mapDescription.put("3.12","As cores do plano de fundo e do primeiro plano dever\u00E3o ser suficientemente contrastantes para que possam ser visualizadas, tamb\u00E9m, por pessoas com baixa vis\u00E3o, com cromodefici\u00EAncias ou que utilizam monitores de v\u00EDdeo monocrom\u00E1tico.\nN\u00E3o dever\u00E3o ser utilizadas imagens atr\u00E1s do texto (background), pois acabam por dificultar a leitura e desviar a aten\u00E7\u00E3o do usu\u00E1rio.");
	        mapDescription.put("4.1","A cor ou outras caracter\u00EDsticas sensoriais, como forma, tamanho, localiza\u00E7\u00E3o visual, orienta\u00E7\u00E3o ou som n\u00E3o devem ser utilizadas como o \u00FAnico meio para transmitir informa\u00E7\u00F5es, indicar uma a\u00E7\u00E3o, pedir uma resposta ao usu\u00E1rio ou distinguir um elemento visual.");
	        mapDescription.put("4.2","A p\u00E1gina deve continuar leg\u00EDvel e funcional quando redimensionada para at\u00E9 200%. Assim, \u00E9 preciso garantir que, quando a p\u00E1gina for redimensionada, n\u00E3o ocorram sobreposi\u00E7\u00F5es de texto nem o aparecimento de uma barra horizontal.");
	        mapDescription.put("4.3","Grandes blocos de informa\u00E7\u00E3o devem ser divididos em grupos mais f\u00E1ceis de gerenciar. As divis\u00F5es mais comuns s\u00E3o \u201Ctopo\u201D, \u201Cconte\u00FAdo\u201D, \u201Cmenu\u201D e \u201Crodap\u00E9\u201D. Nas p\u00E1ginas internas deve-se manter uma mesma divis\u00E3o para que o usu\u00E1rio se familiarize mais rapidamente com a estrutura do s\u00EDtio. \u00C9 importante destacar, entretanto, que a p\u00E1gina inicial pode ter uma divis\u00E3o diferente das p\u00E1ginas internas, pois normalmente ela cont\u00E9m mais elementos. O exemplo a seguir mostra a divis\u00E3o da p\u00E1gina inicial de um s\u00EDtio contendo os blocos \u201Ctopo\u201D, \u201Cmenu\u201D, \u201Cconte\u00FAdo\u201D e \u201Crodap\u00E9\u201D, al\u00E9m da barra de acessibilidade contendo os atalhos.");
	        mapDescription.put("4.4","A \u00E1rea que recebe o foco pelo teclado deve ser claramente marcada, devendo a \u00E1rea de sele\u00E7\u00E3o ser pass\u00EDvel de ser clicada. \nPor padr\u00E3o, links e elementos de formul\u00E1rio j\u00E1 apresentam a borda destacada ao receberem o foco do teclado. Essa borda pode ser modificada via CSS, mas n\u00E3o dever\u00E1 ser removida.");
	        mapDescription.put("5.1","Deve haver uma alternativa sonora ou textual para v\u00EDdeos que n\u00E3o incluem faixas de \u00E1udio. Para v\u00EDdeos que cont\u00EAm \u00E1udio falado e no idioma natural da p\u00E1gina, devem ser fornecidas legendas. Al\u00E9m de essencial para pessoas com defici\u00EAncia visual, a alternativa em texto tamb\u00E9m \u00E9 importante para usu\u00E1rios que n\u00E3o possuem equipamento de som, que desejam apenas realizar a leitura do material ou n\u00E3o disp\u00F5em de tempo para ouvir um arquivo multim\u00EDdia. ");
	        mapDescription.put("5.2","\u00C1udio gravado deve possuir uma transcri\u00E7\u00E3o descritiva. Al\u00E9m de essencial para pessoas com defici\u00EAncia auditiva, a alternativa em texto tamb\u00E9m \u00E9 importante para usu\u00E1rios que n\u00E3o possuem equipamento de som, que desejam apenas realizar a leitura do material ou n\u00E3o disp\u00F5em de tempo para ouvir um arquivo multim\u00EDdia. Neste caso, tamb\u00E9m \u00E9 desej\u00E1vel a alternativa em Libras.");
	        mapDescription.put("5.3","V\u00EDdeos que transmitem conte\u00FAdo visual que n\u00E3o est\u00E1 dispon\u00EDvel na faixa de \u00E1udio devem possuir uma audiodescri\u00E7\u00E3o.");
	        mapDescription.put("5.4","Deve ser fornecido um mecanismo para parar, pausar, silenciar ou ajustar o volume de qualquer som que se reproduza automaticamente na p\u00E1gina.");
	        mapDescription.put("5.5","Para qualquer anima\u00E7\u00E3o que inicie automaticamente na p\u00E1gina devem ser fornecidos mecanismos para que o usu\u00E1rio possa pausar, parar ou ocultar tal anima\u00E7\u00E3o.");
	        mapDescription.put("6.1","Ao serem utilizados bot\u00F5es do tipo imagem (input type=\u201Dimage\u201D), que servem para o mesmo prop\u00F3sito do bot\u00E3o do tipo submit, deve ser fornecida uma descri\u00E7\u00E3o textual para o bot\u00E3o atrav\u00E9s do atributo alt.");
	        mapDescription.put("6.2","As etiquetas de texto (label) devem estar associadas aos seus campos (input) correspondentes no formul\u00E1rio, atrav\u00E9s dos atributos for do label e id do input, os quais dever\u00E3o ter o mesmo valor.");
	        mapDescription.put("6.3","Os elementos do formul\u00E1rio devem ser distribu\u00EDdos corretamente atrav\u00E9s do c\u00F3digo HTML, criando, assim, uma sequ\u00EAncia l\u00F3gica de navega\u00E7\u00E3o. Assim, os formul\u00E1rios devem primeiro ser codificados considerando a ordem l\u00F3gica de navega\u00E7\u00E3o para depois serem organizados visualmente via CSS.");
	        mapDescription.put("6.4","Quando um elemento de formul\u00E1rio receber o foco, n\u00E3o deve ser iniciada uma mudan\u00E7a autom\u00E1tica na p\u00E1gina que confunda ou desoriente o usu\u00E1rio. Assim, as mudan\u00E7as devem ocorrer atrav\u00E9s do acionamento de um bot\u00E3o.");
	        mapDescription.put("6.5","Para conte\u00FAdo que exigir entrada de dados por parte do usu\u00E1rio, devem ser fornecidas , quando necess\u00E1rio, instru\u00E7\u00F5es de preenchimento juntamente com as etiquetas (label).");
	        mapDescription.put("6.6","Quando um erro de entrada de dados for automaticamente detectado, o item que apresenta erro deve ser identificado e descrito ao usu\u00E1rio por texto.");
	        mapDescription.put("6.7","Dever\u00E3o ser agrupados os controles de formul\u00E1rio relacionados de maneira l\u00F3gica, utilizando-se o elemento fieldset, associando o elemento legend de forma significativa (o elemento fieldset \u00E9 \u00FAtil para agrupar, de forma l\u00F3gica, elementos do formul\u00E1rio). Para cada fieldset, \u00E9 poss\u00EDvel fornecer uma legenda que explica claramente o prop\u00F3sito ou natureza dos agrupamentos.");
	        mapDescription.put("6.8","O CAPTCHA (teste interativo humano, completamente automatizado, para distinguir computadores de seres humanos), quando utilizado, dever\u00E1 ser fornecido em forma de pergunta de interpreta\u00E7\u00E3o. Tais perguntas poder\u00E3o ser respondidas apenas por um ser humano. No entanto, \u00E9 preciso garantir que a pergunta n\u00E3o seja de dif\u00EDcil resolu\u00E7\u00E3o, permitindo que a mesma possa ser respondida por pessoas de variadas culturas e n\u00EDveis de instru\u00E7\u00E3o. Para tal, podem ser utilizadas perguntas de senso comum, como por exemplo, \u201Cqual \u00E9 a cor do c\u00E9u?\u201D ou \u201Co fogo \u00E9 quente ou frio?\u201D. Tamb\u00E9m podem ser utilizados testes matem\u00E1ticos. No entanto, \u00E9 preciso tomar cuidado para que esses testes n\u00E3o sejam facilmente \u201Cquebrados\u201D por determinados programas. Uma alternativa \u00E9 solicitar que o usu\u00E1rio escreva o resultado do teste matem\u00E1tico por extenso, como \u201Cescreva por extenso quanto \u00E9 2 + 3\u201D, ou ainda \u201Cresponda por extenso quanto \u00E9 dois mais tr\u00EAs\u201D.");
	        
	}
	
	private SummarizedOccurrence(String checkPoint, boolean isError, Set<Integer> lines,
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
		private Set<Integer> lines = new HashSet<Integer>();
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

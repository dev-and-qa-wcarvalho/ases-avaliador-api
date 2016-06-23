package br.com.checker.emag.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.text.html.HTML;

import org.hamcrest.core.IsNull;

import com.oracle.webservices.internal.api.EnvelopeStyle.Style;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.AvaliadorContraste;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.PresentationRecommendation;
import br.com.checker.emag.util.WebAgent;
import br.com.checker.emag.util.UrlSemArquiNoFinal;



public class PresentationEvaluation extends Evaluation{
	
	private PresentationEvaluation(Source document) { super(document); } 
	
	private PresentationEvaluation(Source document,String url) { 
		super(document,url);
	}
	
	public static class PresentationEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected PresentationEvaluation with(Source document) { return new PresentationEvaluation(document); }
		
		@Override
		protected PresentationEvaluation with(Source document,String url) { return new PresentationEvaluation(document,url); }
		
		public SpecificRecommendation recommendation29() { return new EvaluationRecommendation29();}
		public SpecificRecommendation recommendation30() { return new EvaluationRecommendation30();}
		public SpecificRecommendation recommendation31() { return new EvaluationRecommendation31();}
		public SpecificRecommendation recommendation32() { return new EvaluationRecommendation32();}
	}
	
	
	protected static class EvaluationRecommendation29 extends PresentationRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation29();}
	}
	
	protected static class EvaluationRecommendation30 extends PresentationRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation30();}
	}
	protected static class EvaluationRecommendation31 extends PresentationRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation31();}
	}
	protected static class EvaluationRecommendation32 extends PresentationRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation32();}
	}
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation29()); //comentado por Gibran
		//getOccurrences().addAll(checkRecommendation30()); comentado por Gibran
		//getOccurrences().addAll(checkRecommendation31()); comentado por Gibran
		getOccurrences().addAll(checkRecommendation32());
		
		return getOccurrences();
	}
	
	
	private List<Occurrence> checkRecommendation29() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		UrlSemArquiNoFinal objetoUrlSemArquiNoFinal = new UrlSemArquiNoFinal();
		
		String urlSemArquiNoFinal = objetoUrlSemArquiNoFinal.urlSemArquivoNoFinal(getUrl());
		//verificar css externo
		String href = null;
		boolean avalia = false;
				
		//Adiciona todo css externo em uma lista
		List<String> lsCssExterno = new ArrayList<String>();
		
		for(Element link2 : getDocument().getAllElements("link")) {
			href = link2.getAttributeValue("href");
			if(href.startsWith("www"))href= "http://"+ href;
			
			avalia = getUrl() != null || href.startsWith("http");
			
			if(href.contains(".css") && avalia) {
				
				
				if(!href.startsWith("http")) href = urlSemArquiNoFinal + "/" + href; 
				
				
				lsCssExterno.add(WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent());
			
			}
		}
		//Fim Adiciona todo css externo em uma lista
		
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		
		Color corAvaliar = null;
		Color foreground = null; 
		Color background = null;
		boolean achou_foreground = false;
		boolean achou_background = false;
		String styleInline = "";
		String procurarPorId = "";
		String procurarPorClasse = "";
		String cssParaBuscarCor = "";
				
		List<Element> lsCssInterno = new ArrayList<Element>();
		
		//pega css interno caso tenha
		for (Element head : getDocument().getAllElements("head")) {	
			for (Element estilo : head.getAllElements("style")) {					
				lsCssInterno.add(estilo);								
				}
			}
			
		
		//Pega todos elementos do "body"
		for (Element body : getDocument().getAllElements("body")) {	
			for (Element element : body.getAllElements()) {
				
				//verificar se o "body" possui o atributo "bgcolor"
				if(element.getStartTag().getName().equalsIgnoreCase("body"))
				{
					if(element.toString().contains("bgcolor"))
					{
						try {
							corAvaliar = new Color(Integer.parseInt(element.getAttributeValue("bgcolor").trim().replaceFirst("#",""), 16));
							if(corAvaliar != null)
							{										
								background = corAvaliar;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				//Primeiro verificar se tem css in-line
				styleInline = element.getAttributeValue("style");
				if(styleInline != null){
					
					
					//pega o valor da cor, mesmo que no final não seja atribuido o ";"									
					corAvaliar = getColor(styleInline, "color:(.*?)\\Z");
					if(corAvaliar != null)
					{										
						foreground = corAvaliar;
					}
					
					corAvaliar = getColor(styleInline, "color:(.*?)\\;");
					if(corAvaliar != null)
					{										
						foreground = corAvaliar;
					}
					
					
					//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
					corAvaliar = getColor(styleInline, "background:(.*?)\\Z");
					if(corAvaliar != null)
					{										
						background = corAvaliar;
					}
					
					corAvaliar = getColor(styleInline, "background:(.*?)\\;");
					if(corAvaliar != null)
					{										
						background = corAvaliar;
					}
					//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
					corAvaliar = getColor(styleInline, "background-color:(.*?)\\Z");
					if(corAvaliar != null)
					{										
						background = corAvaliar;
					}
														
					corAvaliar = getColor(styleInline, "background-color:(.*?)\\;");
					if(corAvaliar != null)
					{										
						background = corAvaliar;
					}				

					if(foreground != null) achou_foreground = true;
					if(background != null) achou_background = true;
								
			}else
			{
				procurarPorId = "#" + element.getStartTag().getAttributeValue("id");
				procurarPorClasse = "." + element.getStartTag().getAttributeValue("class");	
				
				//verifica css interno 
				 
				//Verifica se tem #id
				for (Element style : lsCssInterno) {
					
					//String pattern = "";
					
											
						//Coloca todo css em apenas uma linha, depois separa por blocos
						String texto = style.toString().replace("\n","");												
						texto = texto.replace(">", ">\n").replace("*/", "\n").replace(" {", "{").replace("}", " }\n").replace(" :", ":");
						cssParaBuscarCor = "";
						
						
						//vevifica 1º pelo "id"
						if(!procurarPorId.equalsIgnoreCase("#null"))
						{							
							cssParaBuscarCor = getElementoCss(texto, procurarPorId);																				
						}
						
						//Se não encontrar pelo "id" verifica pela classe e elemento
						if(cssParaBuscarCor.isEmpty() && !procurarPorClasse.equalsIgnoreCase(".null"))
						{
							cssParaBuscarCor = getElementoCss(texto, procurarPorClasse + element.getStartTag().getName());
						}
						
						//Se não encontrar pela classe e elemento, verifica pela classe
						if(cssParaBuscarCor.isEmpty() && !procurarPorClasse.equalsIgnoreCase(".null"))
						{
							cssParaBuscarCor = getElementoCss(texto, procurarPorClasse);
						}
						//Se não encontrar pela classe, verifica pelo elemento
						if(cssParaBuscarCor.isEmpty())
						{
							cssParaBuscarCor = getElementoCss(texto, element.getStartTag().getName());
						}
						
						
						
						if(!cssParaBuscarCor.isEmpty())
						{
							if(cssParaBuscarCor.contains("color:"))
							{
								corAvaliar = getColor(cssParaBuscarCor, "color:(.*?)\\Z");
								if(corAvaliar != null)
								{										
								foreground = corAvaliar;								
								
								}
							
								corAvaliar = getColor(cssParaBuscarCor, "color:(.*?)\\;");
								if(corAvaliar != null)
								{										
								foreground = corAvaliar;									
								}
							}
							
							if(cssParaBuscarCor.contains("background:") || cssParaBuscarCor.contains("background-color:"))
							{
								//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
								corAvaliar = getColor(cssParaBuscarCor, "background:(.*?)\\Z");
								if(corAvaliar != null)
								{										
									background = corAvaliar;
								}
							
								corAvaliar = getColor(cssParaBuscarCor, "background:(.*?)\\;");
								if(corAvaliar != null)
								{										
									background = corAvaliar;
								}
								//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
								corAvaliar = getColor(cssParaBuscarCor, "background-color:(.*?)\\Z");
								if(corAvaliar != null)
								{										
									background = corAvaliar;
								}
																
								corAvaliar = getColor(cssParaBuscarCor, "background-color:(.*?)\\;");
								if(corAvaliar != null)
								{										
									background = corAvaliar;
								}										
							}
							
							if(foreground != null)
								{
								achou_foreground = true;								
								}
							
							if(background != null)
								{
								achou_background = true;	
								}							
							
							if(achou_foreground || achou_background)
								{
								break;
								}												 
															
					}
					
				
				corAvaliar = null;
				foreground = null; 
				background = null;
				cssParaBuscarCor = "";
		
			}	
				
				if(!achou_foreground && !achou_background)
				{
					//verifica css externo 
					
					//Verifica se tem #id
					for (String style : lsCssExterno) {
										
						
						if(element.getAttributeValue("id") != null || element.getAttributeValue("class") != null)
						{						
							//Coloca todo css em apenas uma linha, depois separa por blocos
							String texto = style.toString().replace("\n","");												
							texto = texto.replace(">", ">\n").replace("*/", "\n").replace(" {", "{").replace("}", " }\n").replace(" :", ":");
							cssParaBuscarCor = "";
							
							//vevifica 1º pelo "id"
							if(!procurarPorId.equalsIgnoreCase("#null"))
							{							
								cssParaBuscarCor = getElementoCss(texto, procurarPorId);																				
							}
							
							//Se não encontrar pelo "id" verifica pela classe e elemento
							if(cssParaBuscarCor.isEmpty() && !procurarPorClasse.equalsIgnoreCase(".null"))
							{
								cssParaBuscarCor = getElementoCss(texto, procurarPorClasse + element.getStartTag().getName());
							}
							
							//Se não encontrar pela classe e elemento, verifica pela classe
							if(cssParaBuscarCor.isEmpty() && !procurarPorClasse.equalsIgnoreCase(".null"))
							{
								cssParaBuscarCor = getElementoCss(texto, procurarPorClasse);
							}
							//Se não encontrar pela classe, verifica pelo elemento
							if(cssParaBuscarCor.isEmpty())
							{
								cssParaBuscarCor = getElementoCss(texto, element.getStartTag().getName());
							}
							
														
							
							if(!cssParaBuscarCor.isEmpty())
							{
								if(cssParaBuscarCor.contains("color:"))
								{
									corAvaliar = getColor(cssParaBuscarCor, "color:(.*?)\\Z");
									if(corAvaliar != null)
									{										
										foreground = corAvaliar;								
									
									}
								
									corAvaliar = getColor(cssParaBuscarCor, "color:(.*?)\\;");
									if(corAvaliar != null)
									{										
										foreground = corAvaliar;									
									}
								
								}
								
								if(cssParaBuscarCor.contains("background:") || cssParaBuscarCor.contains("background-color:"))
								{
									//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
									corAvaliar = getColor(cssParaBuscarCor, "background:(.*?)\\Z");
									if(corAvaliar != null)
									{										
										background = corAvaliar;
									}
								
									corAvaliar = getColor(cssParaBuscarCor, "background:(.*?)\\;");
									if(corAvaliar != null)
									{										
										background = corAvaliar;
									}
									//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
									corAvaliar = getColor(cssParaBuscarCor, "background-color:(.*?)\\Z");
									if(corAvaliar != null)
									{										
										background = corAvaliar;
									}
																	
									corAvaliar = getColor(cssParaBuscarCor, "background-color:(.*?)\\;");
									if(corAvaliar != null)
									{										
										background = corAvaliar;
									}										
								}
								
								if(foreground != null)
									{
									achou_foreground = true;								
									}
								
								if(background != null)
									{
									achou_background = true;	
									}							
								
								if(achou_foreground || achou_background)
									{
									break;
									}												 
																
						}
						
					}
								
					
					corAvaliar = null;
					foreground = null; 
					background = null;					
					cssParaBuscarCor = "";
									
			
				}	
				}
				
				
			}
				procurarPorId = "";
				procurarPorClasse = "";
				
				if(achou_foreground== true && achou_background == true){
					
					avaliadorContraste.setCores(foreground, background);
					avaliadorContraste.avaliar();
					
					if(avaliadorContraste.getContraste() < new Double("4.5")){
						occurrences.add(buildOccurrence("4.1", false, element.toString(), element,"2"));
					
					}
					achou_foreground = false;
					achou_background = false;
				
				}
				
				//Caso seja encontrado um "foreground" e não "background" e vice-versa, será adicionado um aviso para que seja avaliado manualmente
				/*if((achou_foreground== true && achou_background == false) || (achou_foreground== false && achou_background == true)){
					
						occurrences.add(buildOccurrence("4.1", false, element.toString(), element,"2"));
				
					achou_foreground = false;
					achou_background = false;
				
				}*/
			}
		}
		return occurrences;
		}
	

	private String getElementoCss(String css, String procurarPor){
		
		 	String regex = "\\W" + procurarPor + "\\{.*\\}";
		    String retorno = "";
		   		    
		    try {
		    	 Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
				    
				    Matcher matcher = pattern.matcher(css);
			        if(matcher.find()){
			        	retorno = matcher.group();	            
			        } else {
			        	
			        	regex = procurarPor + ",.*\\}";
			        	
			        	pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
			        	
			        	matcher = pattern.matcher(css);
			        	
			        	  if(matcher.find()){
					        	retorno = matcher.group();	            
					        } else {
					        	retorno = "";
					        }
			        }
			} catch (Exception e) {
				retorno = "";
			}
		   
	        
	        return retorno;
	}
	
	private Color getColor(String css, String pattern){
		
		Color color = null; 
		
		Pattern value = Pattern.compile(pattern);
		
		Matcher match = value.matcher(css.replace(";", "\n").replace("}", "").replace("!important", ""));
		while (match.find()){
	    	String cor = match.group(1);
	    	
	    	try {
		    	if(!isTextCor(match.group(1)) && !cor.contains("url")){
			        
		        	if(cor.contains("rgb")){
		        		//System.out.println("RGB: "+match.group(1));
		        		
		        		String  str = cor.replaceAll("[^-?0-9]+", " "); 
		        		color = new Color(
		        				Integer.parseInt(str.trim().split(" ")[0]),
		        				Integer.parseInt(str.trim().split(" ")[1]),
		        				Integer.parseInt(str.trim().split(" ")[2]));
		        		break;
		        	}else{
		        		//System.out.println("EXADECIMAL: "+match.group(1));
		        		if(css.replaceAll(" ", "").contains(":#"))
		        			{
		        			color = new Color(Integer.parseInt(match.group(1).trim().replaceFirst("#",""), 16));
			        		break;		        			
		        			}		        		
		        	}	
		        }
	    	} catch (Exception e) {
	    		e.printStackTrace();
				return null;
			}
	    }	
		
		return color;
	}
	
	
	private boolean isTextCor(String cor){
		return cor.matches("[a-zA-Z\\s]+");  
	}
	
	private boolean isContrastInferior(String style){
		
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		
		/*int foreground = Integer.parseInt("000000", 16);
		int background = Integer.parseInt("ffffff", 16);*/
		boolean isProces = true;
		
		Color foregroundColor = new Color(Integer.parseInt("000000", 16)); 
		Color backgroundColor = new Color(Integer.parseInt("ffffff", 16)); 
		
		
		//foreground
	   Pattern colorValue = Pattern.compile("color:(.*?)\\;");
	   
	    Matcher match = colorValue.matcher(style);
	   
	    if(match.find()){
	    	
	    	String cor = match.group(1);
	    	
	        //System.out.println("foreground: "+cor);
	        if(!isTextCor(match.group(1)) && !cor.contains("url")){
	        
	        	if(cor.contains("rgb")){
	        		String  str = cor.replaceAll("[^-?0-9]+", " "); 
	        		
	        		foregroundColor = new Color(
	        				Integer.parseInt(str.trim().split(" ")[0]),
	        				Integer.parseInt(str.trim().split(" ")[1]),
	        				Integer.parseInt(str.trim().split(" ")[2]));
	        		
	        	}else if(isTextCor(cor.trim().replaceFirst("^#","")))foregroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	        	
	        }else  	isProces = false;
	    }
	    
	    //background
	    Pattern backgroundValue = Pattern.compile("background:(.*?)\\;");
		match = backgroundValue.matcher(style);
	    if(match.find()){
	    	String cor = match.group(1);
	    
	       //System.out.println("background: "+cor);
	       if(!isTextCor(cor) && !cor.contains("url")){
	    	   
	    	   if(cor.contains("rgb")){
	        		String  str = cor.replaceAll("[^-?0-9]+", " "); 
	        		
	        		backgroundColor = new Color(
	        				Integer.parseInt(str.trim().split(" ")[0]),
	        				Integer.parseInt(str.trim().split(" ")[1]),
	        				Integer.parseInt(str.trim().split(" ")[2]));
	    	   }else  if(isTextCor(cor.trim().replaceFirst("^#",""))) backgroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	    	   
	       }else isProces = false;
	           
	    }    
	    
	   //background
	    Pattern backgroundColorValue = Pattern.compile("background-color:(.*?)\\;");
		match = backgroundColorValue.matcher(style);
	    
		if(match.find()){
			String cor = match.group(1);
			
	        //System.out.println(cor);
	        if(!isTextCor(cor)){
	        	
	        	if(cor.contains("rgb") && !cor.contains("url")){
	        		String  str = cor.replaceAll("[^-?0-9]+", " "); 
	        		
	        		backgroundColor = new Color(
	        				Integer.parseInt(str.trim().split(" ")[0]),
	        				Integer.parseInt(str.trim().split(" ")[1]),
	        				Integer.parseInt(str.trim().split(" ")[2]));
	        	}else  if(isTextCor(cor.trim().replaceFirst("^#",""))) backgroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	        
	        }else isProces = false;
	    }
	    
		if(isProces){
			avaliadorContraste.setCores(foregroundColor,backgroundColor);
			avaliadorContraste.avaliar();
			
			if(avaliadorContraste.getContraste() < new Double("4.5")){
				System.out.println("foreground: "+foregroundColor.getRGB()+" -- "+"background"+backgroundColor.getRGB()+" Resultado: "+avaliadorContraste.getContraste());
				return true;
			}
			
		}
		
		return false;
	}
	
	private List<Occurrence> checkRecommendation30() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("4.2", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation31() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("4.3", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));
		
		return occurrences;
	}
	
	/*private String urlSemArquivoNoFinal(String url)
	{
		if(url != null)
		{
		String urlSemArquivo = "";
		boolean encontrouPonto = false;
		
	
			for(int i = url.length()-1; i >= 0; i-- )
			{
						
				urlSemArquivo = url.substring(i-1,i);
			
				if(urlSemArquivo.equalsIgnoreCase("."))
				{					
					encontrouPonto = true;					
				
				}else if(encontrouPonto && urlSemArquivo.equalsIgnoreCase("/"))
				{
					url = url.substring(0,i);
					break;
				}
					else if(!encontrouPonto && urlSemArquivo.equalsIgnoreCase("/"))					
				{
					break;
				}
			}
		}
		return url;
	}*/
	private List<Occurrence> checkRecommendation32() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		UrlSemArquiNoFinal objetoUrlSemArquiNoFinal = new UrlSemArquiNoFinal();
		
		String urlSemArquiNoFinal = objetoUrlSemArquiNoFinal.urlSemArquivoNoFinal(getUrl());
		
		
		//Caso seja encontrado o "a:focus" e "a:hover" n�o continua a verifica��o nos demais for's, bem como, n�o 
		//adiciona a ocorr�ncia
		boolean focoEncontrado = false;
		boolean passivelDeAvaliacao = false;
		
		for (Element style : getDocument().getAllElements("style")) {
			if(style.toString().contains("a:focus") && style.toString().contains("a:hover")){
				//occurrences.add(buildOccurrence("4.4", true, style.toString(), style, "1"));
				focoEncontrado = true;
				break;
			}	
		}
		if(!focoEncontrado)
		{
			for (Element style : getDocument().getAllElements("a")) {
				Attribute attribute = style.getAttributes().get("style");
				if(attribute != null)
					if(attribute.toString().contains("a:focus") && attribute.toString().contains("a:hover")){
						//occurrences.add(buildOccurrence("4.4", true, style.toString(), style, "1"));
						focoEncontrado = true;
						break;
					}		
				}
		
		}
		if(!focoEncontrado)
		{
			String href = null;
			WebAgent webAgent;
			 String content = "";
			
			boolean avalia = false;			
			
			for(Element link : getDocument().getAllElements("link")) {
				href = link.getAttributeValue("href");
				if(href.startsWith("www"))
				{
					href= "http://" + href;
				}
				
				avalia = getUrl()!=null || href.startsWith("http");
				
				if(href.contains(".css") && avalia) {
					
					if(!href.startsWith("http")) href = urlSemArquiNoFinal + href;
					href = href.replace(" ", "%20").replace("//", "/").replace(":/", "://");
					webAgent = WebAgent.from(href).withGetRequest().execute();
					
					if(webAgent.getStatusCode() != 404)
					{
					 content = webAgent.getContent();
					 passivelDeAvaliacao = true;
					 if (content!=null && content.contains("a:hover") && content.contains("a:focus")){
							
						 focoEncontrado = true;
						 break;
						 				 
					 }
					 
					}										 
					 	
				}
				
			}
		}
				
	if(!focoEncontrado && passivelDeAvaliacao)
	{
		occurrences.add(buildOccurrence("4.4", true, getDocument().getFirstStartTag().toString(), getDocument().getFirstElement(), "1"));
	}
		Collections.sort(occurrences);
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error, String tag, Element element, String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.PRESENTATION_DESIGN,criterio);
	}
	
	
	private Occurrence buildOccurrence(String code, boolean error, String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.PRESENTATION_DESIGN);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.PRESENTATION_DESIGN;}
}

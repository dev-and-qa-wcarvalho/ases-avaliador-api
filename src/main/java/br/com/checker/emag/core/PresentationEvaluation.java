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
		//getOccurrences().addAll(checkRecommendation29()); //comentado por Gibran
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
				
		List<String> lsContent = new ArrayList<String>();
		
		for(Element link2 : getDocument().getAllElements("link")) {
			href = link2.getAttributeValue("href");
			if(href.startsWith("www"))href= "http://"+ href;
			
			avalia = getUrl() != null || href.startsWith("http");
			
			if(href.contains(".css") && avalia) {
				
				
				if(!href.startsWith("http")) href = urlSemArquiNoFinal + "/" + href; 
				
				
				lsContent.add(WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent());
			}
		}
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		
		Color corAvaliar = null;
		Color foreground = null; 
		Color background = null;
		boolean achou_foreground = false;
		boolean achou_background = false;
		String styleInline = "";
		String valor_id = "";
		String valor_class = "";
		List<Attribute> lsAttribute = new ArrayList<Attribute>();
		
		//pega css interno caso tenha
		for (Element head : getDocument().getAllElements("head")) {	
			for (Element estilo : head.getAllElements("style")) {
					for (Attribute atribute : estilo.getAttributes()) {
					lsAttribute.add(atribute);
					}				
				}
			}
			
		
		//Pega todos elementos do "body"
		for (Element body : getDocument().getAllElements("body")) {	
			for (Element element : body.getAllElements()) {
				
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
				
				//verifica css interno ou externo 
				
				//Verifica se tem #id
				valor_id = element.getAttributeValue("id");
				if(valor_id != null)
				{
					for (Attribute style : lsAttribute){
						
						//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
						System.out.println("#"+ style);
						if(style.toString().equalsIgnoreCase("#"+ element.toString()))
						{
							corAvaliar = getColor(style.toString(), "color:(.*?)\\Z");
							if(corAvaliar != null)
							{										
								foreground = corAvaliar;
							}
							
							corAvaliar = getColor(style.toString(), "color:(.*?)\\;");
							if(corAvaliar != null)
							{										
								foreground = corAvaliar;
							}
																									
							//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
							corAvaliar = getColor(style.toString(), "background:(.*?)\\Z");
							if(corAvaliar != null)
							{												
								background = corAvaliar;
							}
							
							corAvaliar = getColor(style.toString(), "background:(.*?)\\;");
							if(corAvaliar != null)
							{												
								background = corAvaliar;
							}
							
							//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
							corAvaliar = getColor(style.toString(), "background-color:(.*?)\\Z");
							if(corAvaliar != null)
							{												
								background = corAvaliar;
							}

							corAvaliar = getColor(style.toString(), "background-color:(.*?)\\;");
							if(corAvaliar != null)
							{												
								background = corAvaliar;
							}
							
							if(foreground != null) achou_foreground = true;
							if(background != null) achou_background = true;
						}
						
					
				}
				
				
			}else
			{
				valor_class = element.getAttributeValue("class");
				
			}
				
				
				corAvaliar = null;
				foreground = null; 
				background = null;
				achou_foreground = false;
				achou_background = false;
			
		
			}		
			}
		}
		return occurrences;
	}

	
	
	
	private List<Occurrence> checkRecommendation292() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		UrlSemArquiNoFinal objetoUrlSemArquiNoFinal = new UrlSemArquiNoFinal();
		
		String urlSemArquiNoFinal = objetoUrlSemArquiNoFinal.urlSemArquivoNoFinal(getUrl());
		//verificar css externo
		String href = null;
		boolean avalia = false;
				
		List<String> lsContent = new ArrayList<String>();
		
		for(Element link2 : getDocument().getAllElements("link")) {
			href = link2.getAttributeValue("href");
			if(href.startsWith("www"))href= "http://"+ href;
			
			avalia = getUrl() != null || href.startsWith("http");
			
			if(href.contains(".css") && avalia) {
				
				
				if(!href.startsWith("http")) href = urlSemArquiNoFinal + "/" + href; 
				
				
				lsContent.add(WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent());
			}
		}
			
		//List<String> tags = Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6","a", "p","select", "option", "textarea", "button", "datalist", "keygen",
		//		"output", "input","label", "select","optgroup", "form","fieldset","legend");
		
		
		// Implementação comentada, em virtude de comprometimento da performance do avaliador. Lyandro Santana - 12/11/2015
		String[] attributes = {"bgcolor","h1", "h2", "h3", "h4", "h5", "h6","a", "p","select", "option", "textarea", "button", "datalist", "keygen",
				"output", "input","label", "select","optgroup", "form","fieldset","legend"};
		
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		
		System.out.println("Processando verificação de cor...");
		for (Element body : getDocument().getAllElements("body")) {	
			for (String atributo : attributes) {
						
				for (Element element : body.getAllElements(atributo)) {
		
				Color corAvaliar = null;
				Color foreground = null; 
				Color background = null;
				boolean achou_foreground = false;
				boolean achou_background = false;
				
				if(element != null ){
						
					
						//for (String attribute : attributes) {
								//System.out.println("Verificando: "+attribute);
						
								//Verificar IN-LINE
								String styleInline = element.getAttributeValue("style");
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
									
									
								}
						
								if(achou_foreground== false || achou_background == false){
									
									String valor_class = element.getAttributeValue("class");
									
									if(valor_class != null){
										
										//Verificar css interno
										for (Element style : getDocument().getAllElements("style")){
											
											//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
											corAvaliar = getColor(style.toString(), "color:(.*?)\\Z");
											if(corAvaliar != null)
											{										
												foreground = corAvaliar;
											}
											
											corAvaliar = getColor(style.toString(), "color:(.*?)\\;");
											if(corAvaliar != null)
											{										
												foreground = corAvaliar;
											}
											
											//foreground = getColor(style.toString(), "color:(.*?)\\;");
											//background = getColor(style.toString(), "background:(.*?)\\;");
											//background = getColor(style.toString(), "background-color:(.*?)\\;");
																						
											//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
											corAvaliar = getColor(style.toString(), "background:(.*?)\\Z");
											if(corAvaliar != null)
											{												
												background = corAvaliar;
											}
											
											corAvaliar = getColor(style.toString(), "background:(.*?)\\;");
											if(corAvaliar != null)
											{												
												background = corAvaliar;
											}
											
											//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
											corAvaliar = getColor(style.toString(), "background-color:(.*?)\\Z");
											if(corAvaliar != null)
											{												
												background = corAvaliar;
											}
			
											corAvaliar = getColor(style.toString(), "background-color:(.*?)\\;");
											if(corAvaliar != null)
											{												
												background = corAvaliar;
											}
											
											if(foreground != null) achou_foreground = true;
											if(background != null) achou_background = true;
										}
										
										if(achou_foreground== false || achou_background == false){
											
											//verificar css externo
											
											
											/*String href = null;
											boolean avalia = false;
											for(Element link : getDocument().getAllElements("link")) {
												href = link.getAttributeValue("href");
												if(href.startsWith("www"))href= "http://"+href;
												
												avalia = getUrl()!=null || href.startsWith("http");
												
												if(href.contains(".css") && avalia) {
													
													
													if(!href.startsWith("http")) href = getUrl()+"/"+href; 
													
													
													String content = WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent();
													*/
											for (String content : lsContent) {
																					
													//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
													corAvaliar = getColor(content.toString(), "color:(.*?)\\Z");
													if(corAvaliar != null)
													{										
														foreground = corAvaliar;
													}
													
													corAvaliar = getColor(content.toString(), "color:(.*?)\\;");
													if(corAvaliar != null)
													{										
														foreground = corAvaliar;
													}
													
													//foreground = getColor(content, "color:(.*?)\\;");
													//background = getColor(content, "background:(.*?)\\;");
													//background = getColor(content, "background-color:(.*?)\\;");
													
													//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
													corAvaliar = getColor(content, "background:(.*?)\\Z");
													if(corAvaliar != null)
													{														
														background = corAvaliar;
													}
													
													corAvaliar = getColor(content, "background:(.*?)\\;");
													if(corAvaliar != null)
													{														
														background = corAvaliar;
													}
													
													//pega o valor da cor, mesmo que no final não seja atribuido o ";"	
													corAvaliar = getColor(content, "background-color:(.*?)\\Z");
													if(corAvaliar != null)
													{
														
														background = corAvaliar;
													}
													
													corAvaliar = getColor(content, "background-color:(.*?)\\;");
													if(corAvaliar != null)
													{
														
														background = corAvaliar;
													}
			
													if(foreground != null) achou_foreground = true;
													if(background != null) achou_background = true;
													
												}
											}
										}
										
									}
								}
						
								
								if(achou_foreground== true && achou_background == true){
									
									avaliadorContraste.setCores(foreground, background);
									avaliadorContraste.avaliar();
									
									if(avaliadorContraste.getContraste() < new Double("4.5")){
										occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"2"));
										break;
									}
									
								
								}else if(achou_foreground == false && achou_background == true){
									
									//avaliadorContraste.setCores(foreground, new Color(Integer.parseInt("ffffff", 16)));
									//avaliadorContraste.avaliar();
									
									//if(avaliadorContraste.getContraste() < new Double("4.5")){
										occurrences.add(buildOccurrence("4.1", false, element.toString(), element,"3"));
										break;
									//}
									
								
								}else if(achou_foreground== true && achou_background == false){
									
									//avaliadorContraste.setCores(foreground, new Color(Integer.parseInt("ffffff", 16)));
									//avaliadorContraste.avaliar();
									
									//if(avaliadorContraste.getContraste() < new Double("4.5")){
										occurrences.add(buildOccurrence("4.1", false, element.toString(), element,"3"));
										break;
									//}									
								
								}
						
					}
				}		
			}
		System.out.println("Verificação terminada.");	
		return occurrences;
		}
	//}
		
		
		
		
		
		/*if(!temBgcolor)
			occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));*/
		
					
		
		///Collections.sort(occurrences);
		
		//Término da rotina de Avaliação de contraste
		
	//	occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));
		
			
	//}
	
	
	private Color getColor(String css, String pattern){
		
		Color color = null; 
		
		Pattern value = Pattern.compile(pattern);
		
		Matcher match = value.matcher(css);
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
		        		color = new Color(Integer.parseInt(match.group(1).trim().replaceFirst("#",""), 16));
		        		break;
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

package br.com.checker.emag.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.AvaliadorContraste;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.PresentationRecommendation;
import br.com.checker.emag.util.WebAgent;

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
		getOccurrences().addAll(checkRecommendation29());
		getOccurrences().addAll(checkRecommendation30());
		getOccurrences().addAll(checkRecommendation31());
		getOccurrences().addAll(checkRecommendation32());
		
		return getOccurrences();
	}
	
	
	
	private List<Occurrence> checkRecommendation29() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		// Implementação comentada, em virtude de comprometimento da performance do avaliador. Lyandro Santana - 12/11/2015
		/*String[] attributes = {"class","id", "bgcolor"};
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		
		System.out.println("Processando verificação de cor...");
		for (Element body : getDocument().getAllElements("body")) {
			for (Element element : body.getAllElements()) {
		
				Color foreground = null; 
				Color background = null;
				boolean achou_foreground = false;
				boolean achou_background = false;
				
				if(element != null ){
						
						for (String attribute : attributes) {
								//System.out.println("Verificando: "+attribute);
						
								//Verificar IN-LINE
								String styleInline = element.getAttributeValue("style");
								if(styleInline != null){
									
									foreground = getColor(styleInline, "color:(.*?)\\;");
									background = getColor(styleInline, "background:(.*?)\\;");
									background = getColor(styleInline, "background-color:(.*?)\\;");
									
			
									if(foreground != null) achou_foreground = true;
									if(background != null) achou_background = true;
									
									
								}
						
								if(achou_foreground== false || achou_background == false){
									
									String valor_class = element.getAttributeValue("class");
									
									if(valor_class != null){
										
										//Verificar css interno
										for (Element style : getDocument().getAllElements("style")){
											foreground = getColor(style.toString(), "color:(.*?)\\;");
											background = getColor(style.toString(), "background:(.*?)\\;");
											background = getColor(style.toString(), "background-color:(.*?)\\;");
											
			
											if(foreground != null) achou_foreground = true;
											if(background != null) achou_background = true;
										}
										
										if(achou_foreground== false || achou_background == false){
											
											//verificar css externo
											String href = null;
											boolean avalia = false;
											for(Element link : getDocument().getAllElements("link")) {
												href = link.getAttributeValue("href");
												if(href.startsWith("www"))href= "http://"+href;
												
												avalia = getUrl()!=null || href.startsWith("http");
												
												if(href.contains(".css") && avalia) {
													
													
													if(!href.startsWith("http")) href = getUrl()+"/"+href; 
													
													
													String content = WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent();
													foreground = getColor(content, "color:(.*?)\\;");
													background = getColor(content, "background:(.*?)\\;");
													background = getColor(content, "background-color:(.*?)\\;");
													
			
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
										occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"1"));
										break;
									}
									
								
								}else if(achou_foreground== true && achou_background == false){
									
									avaliadorContraste.setCores(foreground, new Color(Integer.parseInt("ffffff", 16)));
									avaliadorContraste.avaliar();
									
									if(avaliadorContraste.getContraste() < new Double("4.5")){
										occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"1"));
										break;
									}
									
								
								}else if(achou_foreground== false && achou_background == true){
									
									avaliadorContraste.setCores(new Color(Integer.parseInt("000000", 16)), background);
									avaliadorContraste.avaliar();
									
									if(avaliadorContraste.getContraste() < new Double("4.5")){
										occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"1"));
										break;
									}
								
								}
						
					}
				}		
			}
		}
		
		System.out.println("Verificação terminada.");	*/
		
		
		
		/*if(!temBgcolor)
			occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));*/
		
					
		
		///Collections.sort(occurrences);
		
		//Término da rotina de Avaliação de contraste
		
		occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));
		return occurrences;
			
	}
	
	
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
		        	}else{
		        		//System.out.println("EXADECIMAL: "+match.group(1));
		        		color = new Color(Integer.parseInt(match.group(1).trim().replaceFirst("^#",""), 16));
		        	}	
		        }
	    	} catch (Exception e) {
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
	
	private List<Occurrence> checkRecommendation32() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element style : getDocument().getAllElements("style")) {
			if(!style.toString().contains("a:focus") && !style.toString().contains("a:hover")){
				occurrences.add(buildOccurrence("4.4", true, style.toString(), style, "1"));
				break;
			}	
		}
		
		for (Element style : getDocument().getAllElements("a")) {
			Attribute attribute = style.getAttributes().get("style");
			if(attribute != null)
				if(!attribute.toString().contains("a:focus") && !attribute.toString().contains("a:hover")){
					occurrences.add(buildOccurrence("4.4", true, style.toString(), style, "1"));
					break;
				}	
		}
		
		
			String href = null;
			boolean avalia = false;
			for(Element link : getDocument().getAllElements("link")) {
				href = link.getAttributeValue("href");
				if(href.startsWith("www"))href= "http://"+href;
				
				avalia = getUrl()!=null || href.startsWith("http");
				
				if(href.contains(".css") && avalia) {
					
					if(!href.startsWith("http")) href = getUrl()+"/"+href;
					
					 String content = WebAgent.from(href.replace(" ", "%20")).withGetRequest().execute().getContent();
					
					 if (content!=null && !content.contains("a:hover") && !content.contains("a:focus")){
						 occurrences.add(buildOccurrence("4.4", true, link.toString(), link, "1"));
						 break;
					 }
				}
				
			}
		Collections.sort(occurrences);
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.PRESENTATION_DESIGN,criterio);
	}
	
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.PRESENTATION_DESIGN);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.PRESENTATION_DESIGN;}
}

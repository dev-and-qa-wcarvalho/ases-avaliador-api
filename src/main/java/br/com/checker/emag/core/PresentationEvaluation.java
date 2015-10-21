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
		
		AvaliadorContraste avaliadorContraste = new AvaliadorContraste();
		Color color = null;
		
		int foreground = Integer.parseInt("000000", 16);
		int background = Integer.parseInt("ffffff", 16);
		
		//verificar css in-line
		for (Element element : getDocument().getAllElements()) {
			
			if(element != null){
				String style = element.getAttributeValue("style");
				
				if(style != null){
					if(isContrastInferior(style)){
						occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"1"));
					}			
				}
			}
		}
		
		//verificar css in-line
		boolean isValid = false;
		for (Element element : getDocument().getAllElements("body")) {
			if(element != null){
				String style = element.getAttributeValue("style");
				
				if(style != null){
					if(isContrastInferior(style)){
						occurrences.add(buildOccurrence("4.1", true, element.toString(), element,"1"));
						isValid = true;
					}			
				}
		
				if(!isValid)
				for (Element body : getDocument().getAllElements("body")) {
					Attribute bgc = body.getAttributes().get("bgcolor");
					if(bgc != null){
						
						background = Integer.parseInt(bgc.getValue().trim().replaceFirst("^#",""), 16);
						
						avaliadorContraste.setCores(new Color(background), new Color(foreground));
						avaliadorContraste.avaliar();
						
						if(avaliadorContraste.getContraste() < new Double("4.5")){
							occurrences.add(buildOccurrence("4.1", true, body.getStartTag().toString(), body,"1"));
							isValid = true;
						}	
					}
					
					if(body.getName().equals("font")){
						String cor = element.getAttributeValue("color");
						Color foregroundV = null;
						 if(!isTextCor(cor)){
					        if(cor.contains("rgb")){
				        		String  str = cor.replaceAll("[^-?0-9]+", " "); 
				        		
				        		foregroundV = new Color(
				        				Integer.parseInt(str.trim().split(" ")[0]),
				        				Integer.parseInt(str.trim().split(" ")[1]),
				        				Integer.parseInt(str.trim().split(" ")[2]));
				        		
				        	}else foregroundV = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
					     
					        background = Integer.parseInt(bgc.getValue().trim().replaceFirst("^#",""), 16);
							
							avaliadorContraste.setCores(new Color(background), foregroundV);
							avaliadorContraste.avaliar();
							
							if(avaliadorContraste.getContraste() < new Double("4.5")){
								occurrences.add(buildOccurrence("4.1", true, body.getStartTag().toString(), body,"1"));
								isValid = true;
							}	
						 
						 }
					}
				}
			}
		}
		
		//verificar css interno
		for (Element css : getDocument().getAllElements("style")) {
			
			if(css != null){
				if(isContrastInferior(css.getContent().toString())){
					occurrences.add(buildOccurrence("4.1", true, css.toString(), css,"1"));
					isValid = true;
				}		
			}	
		
		}
	
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
				if(isContrastInferior(content)){
					occurrences.add(buildOccurrence("4.1", true, link.toString(), link,"1"));
					isValid = true;
				}
			}
		}
		
		
		
		
		/*boolean temBgcolor = false;
		for (Element bgcolor : getDocument().getAllElements("body")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				
				background = Integer.parseInt(bgc.getValue().replaceFirst("^#",""), 16);
				
				avaliadorContraste.setCores(new Color(background), new Color(foreground));
				avaliadorContraste.avaliar();
				
				if(avaliadorContraste.getContraste() < new Double("4.5"))
					occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"3"));
				
				temBgcolor = true;
			}
		}	
		
		for (Element bgcolor : getDocument().getAllElements("table")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){

				int rgb = Integer.parseInt(bgc.getValue().replaceFirst("^#",""), 16);
				color = new Color(rgb);
				avaliadorContraste.setCores(color, color);
				avaliadorContraste.avaliar();
				
				if(avaliadorContraste.getContraste() < new Double("4.5"))
					occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				
				temBgcolor = true;
			}
		}
		for (Element bgcolor : getDocument().getAllElements("td")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				
				int rgb = Integer.parseInt(bgc.getValue().replaceFirst("^#",""), 16);
				color = new Color(rgb);
				avaliadorContraste.setCores(color, color);
				avaliadorContraste.avaliar();
				
				if(avaliadorContraste.getContraste() < new Double("4.5"))
					occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				
				temBgcolor = true;
			}
		}
		for (Element bgcolor : getDocument().getAllElements("tr")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				
				int rgb = Integer.parseInt(bgc.getValue().replaceFirst("^#",""), 16);
				color = new Color(rgb);
				avaliadorContraste.setCores(color, color);
				avaliadorContraste.avaliar();
				
				if(avaliadorContraste.getContraste() < new Double("4.5"))
					occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				
				temBgcolor = true;
			}
		}	*/
		
		/*if(!temBgcolor)
			occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));*/
		Collections.sort(occurrences);
		return occurrences;
			
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
	        		
	        	}else foregroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	        	
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
	    	   }else  backgroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	    	   
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
	        	}else backgroundColor = new Color(Integer.parseInt(cor.trim().replaceFirst("^#",""), 16));
	        
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
					
					if(!href.startsWith("http")) href = getUrl()+href;
					
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

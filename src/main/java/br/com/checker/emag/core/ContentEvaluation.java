package br.com.checker.emag.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.ContentRecommendation;

public class ContentEvaluation extends Evaluation{

	private ContentEvaluation(Source document) { super(document); }
	
	private ContentEvaluation(Source document,String url) { 
		super(document,url);
	}
	
	public static class ContentEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected ContentEvaluation with(Source document) { return new ContentEvaluation(document); }
		
		@Override
		protected ContentEvaluation with(Source document,String url) { return new ContentEvaluation(document,url); }
		
		public SpecificRecommendation recommendation17() { return new EvaluationRecommendation17();}
		public SpecificRecommendation recommendation18() { return new EvaluationRecommendation18();}
		public SpecificRecommendation recommendation19() { return new EvaluationRecommendation19();}
		public SpecificRecommendation recommendation20() { return new EvaluationRecommendation20();}
		public SpecificRecommendation recommendation21() { return new EvaluationRecommendation21();}
		public SpecificRecommendation recommendation22() { return new EvaluationRecommendation22();}
		public SpecificRecommendation recommendation23() { return new EvaluationRecommendation23();}
		public SpecificRecommendation recommendation24() { return new EvaluationRecommendation24();}
		public SpecificRecommendation recommendation25() { return new EvaluationRecommendation25();}
		public SpecificRecommendation recommendation26() { return new EvaluationRecommendation26();}
		public SpecificRecommendation recommendation27() { return new EvaluationRecommendation27();}
		public SpecificRecommendation recommendation28() { return new EvaluationRecommendation28();}
		
	}
	
	protected static class EvaluationRecommendation17 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation17();}
	}
	protected static class EvaluationRecommendation18 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation18();}
	}
	protected static class EvaluationRecommendation19 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation19();}
	}
	protected static class EvaluationRecommendation20 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation20();}
	}
	protected static class EvaluationRecommendation21 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation21();}
	}
	protected static class EvaluationRecommendation22 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation22();}
	}
	protected static class EvaluationRecommendation23 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation23();}
	}
	protected static class EvaluationRecommendation24 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation24();}
	}
	protected static class EvaluationRecommendation25 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation25();}
	}
	protected static class EvaluationRecommendation26 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation26();}
	}
	protected static class EvaluationRecommendation27 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation27();}
	}
	protected static class EvaluationRecommendation28 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation28();}
	}
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation17());
		getOccurrences().addAll(checkRecommendation18());
		getOccurrences().addAll(checkRecommendation19());
		getOccurrences().addAll(checkRecommendation20());
		getOccurrences().addAll(checkRecommendation21());
		getOccurrences().addAll(checkRecommendation22());
		getOccurrences().addAll(checkRecommendation23());
		getOccurrences().addAll(checkRecommendation24());
		getOccurrences().addAll(checkRecommendation25());
		getOccurrences().addAll(checkRecommendation26());
		getOccurrences().addAll(checkRecommendation27());
		getOccurrences().addAll(checkRecommendation28());
		
		return getOccurrences();
	}
	
	
	private List<Occurrence> checkRecommendation17() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		Element html = getDocument().getFirstElement("html");
		
		if (html != null) {
			Attribute lang = html.getAttributes().get("lang");
			Attribute xmlLang = html.getAttributes().get("xml:lang");
			Attribute xmlns = html.getAttributes().get("xmlns");
			
			String tagHtml = getDocument().getFirstStartTag("html").toString();
			
			
			/*if ( lang == null && xmlLang == null){
				occurrences.add(this.buildOccurrence("3.1", true, tagHtml, html, "1"));
			}
			
			if(xmlLang != null && !(lang == null || xmlLang == null)){
				occurrences.add(this.buildOccurrence("3.1", true, tagHtml, html, "1"));
			}else if (lang != null && lang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			} else if (xmlLang != null && xmlLang.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			}else if (xmlns != null && xmlns.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			}*/
					
			
			if (lang == null){
				occurrences.add(this.buildOccurrence("3.1", true, tagHtml, html, "1"));
			}
			
			if (xmlLang == null){
				occurrences.add(this.buildOccurrence("3.1", true, tagHtml, html, "1"));
			}
			
			if (lang == null && (xmlLang != null || xmlns != null)) {
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			} else if (lang != null && lang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			} else if (xmlLang != null && xmlLang.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			}else if (xmlns != null && xmlns.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("3.1", false, tagHtml, html, "2"));
			}
			
			/*if (lang == null && xmlns != null) {
				occurrences.add(this.buildOccurrence("3.1", false, html.toString(), html, "2"));
			} else if (xmlns != null && xmlLang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("3.1", false, html.toString(), html, "2"));
			} else if (xmlns != null && xmlns.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("3.1", false, html.toString(), html, "2"));
			}
*/			
		}
		
		return occurrences;
	}
		
	
	private List<Occurrence> checkRecommendation18() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements()) {
			if(!element.getName().equals("html")){
				
				if(element.getName().equals("a") && element.getAttributeValue("href") != null){
						if(element.getAttributeValue("href").contains("/?lang=")){
							occurrences.add(this.buildOccurrence("3.2", false, element.toString(), element, "1"));
						}
				}else if(element.getAttributeValue("lang") != null)	
						occurrences.add(this.buildOccurrence("3.2", false, element.toString(), element, "1"));
			}
		}
	
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation19() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		//Element head = getDocument().getFirstElement("head");
		
		//if(head == null) {
			//occurrences.add(new Occurrence("3.3", true, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION,"1"));
		//}else {
		//if(head != null) {
			Element title = getDocument().getFirstElement("title");
			if (title == null) {
				occurrences.add(this.buildOccurrence("3.3", true, " Observa&ccedil;&atilde;o – Sem Fonte (N&atilde;o existe t&iacute;tulo na p&aacute;gina)", getDocument().getFirstElement(), "1"));
				//occurrences.add(new Occurrence("3.3", true, "Sem fonte (n�o existe t�tulo na p�gina)",OccurrenceClassification.CONTENT_INFORMATION,"1"));
			} else if (title.isEmpty()) {
				occurrences.add(buildOccurrence("3.3", true, title.toString(), title, "1"));
			}
		//}
		
		return occurrences;
	}
		
	private List<Occurrence> checkRecommendation20() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("3.4", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation21() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for(Element  link : getDocument().getAllElements("a")){
			String href = link.getAttributeValue("href");
			String title = link.getAttributeValue("title");
			String content = link.getContent().toString();
			
			if(hasEqualsContentHref(link) && isRegistroBr(content))
				occurrences.add(this.buildOccurrence("3.5", false, link.toString(), link, "1"));
			
			if(!hasContent(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"2"));
			else if(hasImgWithoutAlt(link))
					occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"2"));
			
			//if(hasTitle(link) && isNotAlt(link))
			if(hasTitle(link) && !hasContent(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"3"));
			
			/*if(!hasTitle(link) && !hasContent(link) && hasImgWithoutAlt(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"4"));*/
			
			if(hasImgWithoutAlt(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"4"));
			
			if(hasLeiaMaisDescription(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"5"));
			
			if(hasDiferenteContentSameLink(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"6"));
			
			if(isTitleEqualsContent(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"8"));
			
			if(hasSameContentDiferentLink(link))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"7"));
			
			if(link != null  && hasLongContent(link))
				occurrences.add(this.buildOccurrence("3.5", false, link.toString(), link,"9"));
	
			if(link != null  && isLinkUnavailable(link,getUrl()))
				occurrences.add(this.buildOccurrence("3.5", true, link.toString(), link,"10"));
			
		}
		
		return occurrences;
	}
	
	
	private boolean isLinkUnavailable(Element link,String url){
		
		String href = link.getAttributeValue("href");
		
		if(href!=null && href.startsWith("www"))
			href = "http://"+href;
		
		if(href!=null && !href.startsWith("http") && url !=null)	
			href = url+link.getAttributeValue("href");
		
		if(link.getAttributeValue("href") != null && !link.getAttributeValue("href").equals("#") && !link.getAttributeValue("href").equals("/") && !link.getAttributeValue("href").contains("javascript")){
			
			int[] codErro ={400, 401,402, 403,404, 405, 406, 407, 408,409, 410, 411, 412, 414,415, 416, 417, 418,422, 423,424,425,426,450,499,500,501,502,503,504,505};
			int codResponse = 0;
			
			String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]+$";
			
			if(!IsMatch(href,regex))
		    	return true;
			
			try {
				String[] test = href.split("\\../");
				String newurl="";
				for(String tes : test)
					newurl=newurl+tes.trim();
				
				newurl = newurl.replace(" ", "%20");
				
				URL u = new URL(newurl); 
				HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
				huc.setRequestMethod("GET"); 
				//huc.setRequestMethod("HEAD");
				huc.connect();
				codResponse = huc.getResponseCode();
				huc.disconnect();
			} catch (MalformedURLException e) {
				return true;
			} catch (IOException e) {
				return true;
			} 
		  
		    /*if(huc.getResponseCode() != HttpURLConnection.HTTP_OK)
		    	System.out.println(link.toString());*/
		    for(int cod : codErro)
		    	if(codResponse == cod)	return true;
	    
		}
	    
	    return false;
	}
	
	 private static boolean IsMatch(String s, String pattern) {
	        try {
	            Pattern patt = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
	            Matcher matcher = patt.matcher(s);
	            return matcher.matches();
	        } catch (RuntimeException e) {
	        return false;
	    }  
	 }    
	
	private boolean isNotAlt(Element link) {
		String alt = link.getAttributeValue("alt");
		return alt == null || StringUtils.isBlank(alt);
	}
	
	private boolean hasLongContent(Element link) {
		return link.getContent().getTextExtractor().toString().length() > 500 ? true : false;
	}
	
	private boolean hasContent(Element link) {
		return StringUtils.isNotBlank(link.getContent().getTextExtractor().toString());
	}
	private boolean hasTitle(Element link){
		String title = link.getAttributeValue("title");
		return title != null && StringUtils.isNotBlank(title);
	}
	private boolean hasImgWithoutAlt(Element link) {
		Element img = link.getFirstElement("img");
		if(img == null) return false; 
		String alt = img.getAttributeValue("alt");
		return alt == null || StringUtils.isBlank(alt);
	}
	
	private boolean hasLeiaMaisDescription(Element link){
		String title = link.getAttributeValue("title");
		String content = link.getContent().getTextExtractor().toString();
		String altImg = link.getFirstElement("img")!=null ? link.getFirstElement("img").getAttributeValue("alt") : "" ;
		
		for(String leiaMais:this.leiaMais){
		
			if(title!=null)
				if(title.toLowerCase().equals(leiaMais)) return true;
			
			if(content!=null)
				if(content.toLowerCase().equals(leiaMais)) return true;
			
			if(altImg!=null)
				if(altImg.toLowerCase().equals(leiaMais)) return true;
		}
		
		
	/*	Pattern pattern;
		for(String leiaMais:this.leiaMais){
			pattern = Pattern.compile("("+leiaMais+")");
			if(title!=null)
				if(pattern.matcher(title.toLowerCase()).find()) return true;
			
			if(content!=null)
				if(pattern.matcher(content.toLowerCase()).find()) return true;
			
			if(altImg!=null)
				if(pattern.matcher(altImg.toLowerCase()).find()) return true;
		}*/
		
		return false;
	}
	private boolean isRegistroBr(String href){
		if(StringUtils.isBlank(href)) return false;
		Pattern pattern;
		for(String registro:hrefRegistroBr){
			pattern = Pattern.compile("(.*)("+registro+").*$");
			if(pattern.matcher(href.toUpperCase()).find()) return true;
		}
		
		return false;
	}
	
	
	private boolean hasEqualsContentHref(Element link) {
		String content = link.getContent().getTextExtractor().toString();
		if(content != null && !content.isEmpty())
			content = content.replace("http://","").replaceFirst("(/$)", "");
		
		String href = link.getAttributeValue("href");
		if(href != null && !href.isEmpty())
			href = href.replace("http://","").replaceFirst("(/$)", "");
		return content.equals(href);
	}
	
	private boolean hasDiferenteContentSameLink(Element link) {
		String content = link.getContent().getTextExtractor().toString();
		String href = link.getAttributeValue("href");
		if(StringUtils.isBlank(href)) return  false;
		String otherContent;
		String otherHref;
		for(Element otherLink:getDocument().getAllElements("a")){
			otherContent = otherLink.getContent().getTextExtractor().toString();
			otherHref = otherLink.getAttributeValue("href");
			if(StringUtils.isBlank(otherHref))continue;
			if(!content.toLowerCase().equals(otherContent.toLowerCase()) && href.equals(otherHref)) return true;
		}
		return false;
	}
	
	private boolean hasSameContentDiferentLink(Element link) {
		//String content = link.getContent().getTextExtractor().toString();
		String content = link.getContent().toString();
		
		if(content != null)
			content = content.replace("http://","").replaceFirst("(/$)", "");
		
		String href = link.getAttributeValue("href");
		
		if(href != null)
			href = href.replace("http://","").replaceFirst("(/$)", "");
		
		if(StringUtils.isBlank(href)) return  false;
		String otherContent;
		String otherHref;
		
		List<String> linksVerificados = new ArrayList<String>();
		
		for(Element otherLink : getDocument().getAllElements("a")){
			
			if(otherLink.getBegin() == link.getBegin()) continue;
			otherContent = otherLink.getContent().toString();
			//otherContent = otherLink.getContent().getTextExtractor().toString();
			
			if(otherContent != null)
				otherContent = otherContent.replace("http://","").replaceFirst("(/$)", "");
			
			otherHref = otherLink.getAttributeValue("href");
			
			if(otherHref != null)
				otherHref = otherHref.replace("http://","").replaceFirst("(/$)", "");
			
			if(StringUtils.isBlank(otherHref))continue;
			if(!linksVerificados.contains(content)){
				if(content.toLowerCase().equals(otherContent.toLowerCase()) && !href.equals(otherHref)){ 
				linksVerificados.add(content);
				return true;
			}
			}	
		}
		return false;
	}
	
	private boolean isTitleEqualsContent(Element element) {
		
		String content = element.getContent().getTextExtractor().toString() ;
		String title = element.getAttributeValue("title");
		
		if(StringUtils.isBlank(title)) return false;
		
		return title.toLowerCase().equals(content.toLowerCase());
	}
	
	private List<Occurrence> checkRecommendation22() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		String[] parts = null;
		
		String[] descricoes = {"figura", "imagem", "alt", "descrição", "foto"};
		
		for (Element img : getDocument().getAllElements("img")) {
			Attribute alt = img.getAttributes().get("alt");
			if (alt == null) {
				occurrences.add(buildOccurrence("3.6", true, img.toString(), img, "1"));
			}else if(alt.getValueSegment().toString().trim().isEmpty()){
				occurrences.add(buildOccurrence("3.6", true, img.toString(), img, "2"));
			}	
			
			Attribute src = img.getAttributes().get("src");
			String contAlt = null;
			
			if(src != null && alt != null){
				String value = src.getValue();
				parts = value.toString().split("/");
				contAlt = alt.getValue();  
			
				if(parts[parts.length-1].toString().equals(contAlt))
					occurrences.add(buildOccurrence("3.6", true, img.toString(), img, "3"));
			}
			
			for(String descricao : descricoes){
				if(descricao.equalsIgnoreCase(contAlt))
					occurrences.add(buildOccurrence("3.6", true, img.toString(), img, "4"));
				
			}
			
			
		}
		
		
		Map<String, Element> aMap = new HashMap<String, Element>();
		
		
		
		for (Element img : getDocument().getAllElements("img")) {
			
			Attribute src = img.getAttributes().get("src");
			Attribute alt = img.getAttributes().get("alt");
			
			boolean isVerificado = false;
			
			if (alt != null && !alt.getValue().isEmpty()) {
				
				if(src != null)
				if(!aMap.containsKey(src.getValue())){
					
					int rowImgVerificado = this.getRow(img);
					
					for (Element imgA : getDocument().getAllElements("img")) {
						int rowImgA = this.getRow(imgA);
						
						if(rowImgA > rowImgVerificado){
						
							Attribute srcAtt = imgA.getAttributes().get("src");
							Attribute altAtt = imgA.getAttributes().get("alt");
						
							if (altAtt != null && !altAtt.getValue().isEmpty()) {
								if(alt.getValue().equals(altAtt.getValue())){
									if(srcAtt != null){
										if(!src.getValue().equals(srcAtt.getValue())){
											occurrences.add(buildOccurrence("3.6", false, imgA.toString(), imgA, "5"));
											aMap.put(srcAtt.getValue(), img);
											isVerificado = true;
										}
									}
								}
							}
						}
					}
				}
				
				if(src != null)
				aMap.put(src.getValue(), img);
				
				if(isVerificado)
					occurrences.add(buildOccurrence("3.6", false, img.toString(), img, "5"));
			}
		}
		
		
		/*Map<String, String> aMap = new HashMap<String, String>();
		
		for (Element img : getDocument().getAllElements("img")) {
			
			Attribute src = img.getAttributes().get("src");
			Attribute altAtt = img.getAttributes().get("alt");
			if (altAtt != null && !altAtt.getValue().isEmpty()) {
				if(aMap.containsKey(altAtt.getValue())){
					Attribute srcAtt = img.getAttributes().get("src");
					if(srcAtt != null){
						if(!aMap.get(altAtt.getValue()).contains("src=\""+srcAtt.getValue()+"\""))
							occurrences.add(buildOccurrence("3.6", false, img.toString(), img, "5"));
					}
				}else{
					aMap.put(altAtt.getValue(), img.toString());
				}
			}
		}*/
		
		for (Element img : getDocument().getAllElements("img")) {
			Attribute alt = img.getAttributes().get("alt");
			Attribute title = img.getAttributes().get("title");
			if (alt != null && title != null) {
				if(title.getValue().equals(alt.getValue()))
					occurrences.add(buildOccurrence("3.6", true, img.toString(), img, "6"));
			}
		}
		
		this.oder(occurrences);
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation23() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean isMap = false;
		
		for (Element table : getDocument().getAllElements("img")) {
			
			Attribute usemap = table.getAttributes().get("usemap");
			Attribute alt = table.getAttributes().get("alt");
			
			if (usemap != null && (alt == null || alt.getValue().isEmpty())){
				occurrences.add(this.buildOccurrence("3.7", true, table.toString(), table, "1"));
				isMap = true;
			}
			
		}
		
		if(isMap)
			for (Element map : getDocument().getAllElements("map")) {
				for (Element area :map.getAllElements("area")) {
					Attribute alt = area.getAttributes().get("alt");
					if(alt == null || alt.getValue().isEmpty())
						occurrences.add(this.buildOccurrence("3.7", true, map.toString(), map, "1"));
				}
			}
		
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation24() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("3.8", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation25() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table")) {
			Attribute summary = table.getAttributes().get("summary");
			
			if (summary == null || summary.getValue().equals("")) 
				occurrences.add(buildOccurrence("3.9", false, table.getStartTag().toString(), table, "1"));
			
			if(table.getAllElements("caption").isEmpty() || table.getAllElements("caption") == null)
				occurrences.add(buildOccurrence("3.9", false, table.getStartTag().toString(), table, "1"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation26() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		/*for (Element table : getDocument().getAllElements("table")) {
			occurrences.add(buildOccurrence("3.10", false, table.toString(), table, "1"));
		}*/
		
		for (Element table : getDocument().getAllElements("table")) {
			for(Element caption : table.getAllElements("caption")){
				if(caption == null || caption.isEmpty())
					occurrences.add(buildOccurrence("3.10", true, table.getStartTag().toString(), table, "1"));
			}
		}
		
		
		for (Element table : getDocument().getAllElements("table")) {
			Attribute summary = table.getAttributes().get("summary");
			
			boolean THusaScope = false;
			boolean THusaId = false;
			boolean THusaHeaders = false;
			boolean TDusaScope = false;
			boolean TDusaId= false;
			boolean TDusaHeaders = false;
			boolean usaThead = false;
			boolean usaTfoot = false;
			boolean usaTbody = false;
		 
			
			if (summary == null || summary.getValue().equals("")) 
				occurrences.add(buildOccurrence("3.10", true, table.getStartTag().toString(), table, "1"));
			
			for (Element thead : table.getAllElements("thead")) {
				if (thead != null)
					usaThead = true;
			}
			
			for (Element tfoot : table.getAllElements("tfoot")) {
				if (tfoot != null)
					usaTfoot = true;
			}
			
			for (Element tbody : table.getAllElements("tbody")) {
				if (tbody != null)
					usaTbody = true;
			}
			
			/*if(!usaThead && !usaTbody && !usaTfoot){
				
				//occurrences.add(this.buildOccurrence("3.10", true, table.getAllStartTags("table").get(0).toString(), table, "1"));
				
			for (Element th : table.getAllElements("th")) {
				Attribute scope = th.getAttributes().get("scope");
				Attribute headers = th.getAttributes().get("headers");
				Attribute id = th.getAttributes().get("id");
				if (scope != null && !scope.getValue().equals("")) {
					THusaScope = true;
				} else if (headers != null && !headers.getValue().equals("")) {
					THusaHeaders = true;
				} else if (id != null && !id.getValue().equals("")) {
					THusaId = true;
				}
				
				if(!THusaScope && !THusaHeaders  && !THusaId){
					occurrences.add(this.buildOccurrence("3.10", true, th.toString(), th, "1"));
				}
			}
			
			for (Element td : table.getAllElements("td")) {
				Attribute tdscope = td.getAttributes().get("scope");
				Attribute tdheaders = td.getAttributes().get("headers");
				Attribute tdid = td.getAttributes().get("id");
				if (tdscope != null && !tdscope.getValue().equals("")) {
					TDusaScope = true;
				} else if (tdheaders != null && !tdheaders.getValue().equals("")) {
					TDusaHeaders = true;
				} else if (tdid != null && !tdid.getValue().equals("")) {
					TDusaId = true;
				}
				
				if(!TDusaScope && !TDusaHeaders  && !TDusaId){
					occurrences.add(this.buildOccurrence("3.10", true, td.toString(), td, "1"));
				}
			}
			
		 }*/
	   }
		//Sorting
		Collections.sort(occurrences, new Comparator<Occurrence>() {
		    public int compare(Occurrence  occurrence1, Occurrence  occurrence2){
	            return  occurrence1.getLine().compareTo(occurrence2.getLine());
	        }
	    });
		
		return occurrences;

	}
	
	private List<Occurrence> checkRecommendation27() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		//String reg = "<p.*?>(.*)<\\/p.*?>";
		
		for (Element paragrafo : getDocument().getAllElements("p")) {
			
			   /* Pattern p = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
		        Matcher m = p.matcher(paragrafo);
		       
		        while(m.find()){
		        	String conteudoParagrafo = m.group(1);
		        	
		        	if(conteudoParagrafo.length() > 1000)
						occurrences.add(this.buildOccurrence("3.11", false, paragrafo.toString(), paragrafo, "1"));
		        }*/
		        
		        
			if(paragrafo.getContent().length() > 1000)
				occurrences.add(this.buildOccurrence("3.11", false, paragrafo.toString(), paragrafo, "1"));
		        
		        String align = paragrafo.getAttributeValue("align");
		        if("justify".equals(align))
		        	occurrences.add(this.buildOccurrence("3.11", true, paragrafo.toString(), paragrafo, "2"));
		        
		        String style = paragrafo.getAttributeValue("style");
			    if(style != null && style.contains("text-align:justify"))
			       occurrences.add(this.buildOccurrence("3.11", true, paragrafo.toString(), paragrafo, "2"));
		}
		
		
		for (Element tags : getDocument().getAllElements()) {
			if(!tags.getName().equals("p")){
		       String style = tags.getAttributeValue("style");
		       if(style != null && style.contains("text-align:justify"))
		    	   occurrences.add(this.buildOccurrence("3.11", true, tags.toString(), tags, "3"));
			}    
		
		}
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation28() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element abbr : getDocument().getAllElements("abbr")) {
			Attribute title = abbr.getAttributes().get("title");
				if(title == null || title.getValue().equals(""))
					occurrences.add(buildOccurrence("3.12", true, abbr.toString(), abbr, "1"));
				
			
			if(abbr.getContent() == null)
				occurrences.add(buildOccurrence("3.12", true, abbr.toString(), abbr, "1"));
		}
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.CONTENT_INFORMATION,criterio);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.CONTENT_INFORMATION;}
	
	private String[] hrefRegistroBr = {"COM","COM.BR","ECO.BR","EMP.BR","NET.BR","EDU.BR",
			"ADM.BR","ADV.BR","ARQ.BR","ATO.BR","BIO.BR","BMD.BR","CIM.BR","CNG.BR",
			"CNT.BR","ECN.BR","ENG.BR","ETI.B","FND.BR","FOT.BR","FST.BR","GGF.BR","JOR.BR",
			"LEL.BR","MAT.BR","MED.BR","MUS.BR","NOT.BR","NTR.BR","ODO.BR","PPG.BR","PRO.BR", 
			"PSC.BR","QSL.BR","SLG.BR","TAXI.BR","TEO.BR","TRD.BR","VET.BR","ZLG.BR","BLOG.BR", 
			"FLOG.BR","NOM.BR","VLOG.BR","WIKI.BR","AGR.BR","ART.BR","ESP.BR","ETC.BR","FAR.BR",
			"IMB.BR","IND.BR","INF.BR","RADIO.BR","REC.BR","SRV.BR","TMP.BR","TUR.BR","TV.BR",
			"AM.BR","COOP.BR","FM.BR","G12.BR","GOV.BR","MIL.BR","ORG.BR","PSI.BR","B.BR",
			"JUS.BR","LEG.BR","MP.BR"};
	
	private String[] leiaMais = {"clique aqui","leia mais","saiba mais","veja mais","acesse a lista", "mais"};
	
	private List<Occurrence> oder(List occurrences){
		//Sorting
		Collections.sort(occurrences, new Comparator<Occurrence>() {
		    public int compare(Occurrence  occurrence1, Occurrence  occurrence2){
	            return  occurrence1.getLine().compareTo(occurrence2.getLine());
	        }
	    });
		return occurrences;
	}
	
}

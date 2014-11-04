package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.ContentRecommendation;

public class ContentEvaluation extends Evaluation{

	private ContentEvaluation(Source document) { super(document); }
	
	public static class ContentEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected ContentEvaluation with(Source document) { return new ContentEvaluation(document); }
		
		public SpecificRecommendation recommendation16() { return new EvaluationRecommendation16();}
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
		
	}
	
	protected static class EvaluationRecommendation16 extends ContentRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation16();}
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
	
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation16());
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
		
		return getOccurrences();
	}
	
	
	
	private List<Occurrence> checkRecommendation16() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("16", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}	
	
	private List<Occurrence> checkRecommendation17() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		Element html = getDocument().getFirstElement("html");
		
		if (html != null) {
			Attribute lang = html.getAttributes().get("lang");
			Attribute xmlLang = html.getAttributes().get("xml:lang");
			Attribute xmlns = html.getAttributes().get("xmlns");
			
			if (lang == null && xmlLang == null) {
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "1"));
			} else if (lang != null && lang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "1"));
			} else if (xmlLang != null && xmlLang.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "1"));
			}
			
			if (xmlLang == null && xmlns != null) {
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "2"));
			} else if (xmlns != null && xmlLang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "2"));
			} else if (xmlns != null && xmlns.getValue().isEmpty()) { 
				occurrences.add(this.buildOccurrence("17", true, html.toString(), html, "2"));
			}
		}
		
		return occurrences;
	}
		
	
	private List<Occurrence> checkRecommendation18() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements()) {
			
			if(!element.getName().equals("html")){
				if(element.getAttributeValue("lang") != null)
					occurrences.add(this.buildOccurrence("18", false, element.toString(), element, "1"));
					
			}
		}
	
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation19() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		Element head = getDocument().getFirstElement("head");
		
		if(head == null) {
			occurrences.add(new Occurrence("19", true, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION,"1"));
		}else {
		
			Element title = head.getFirstElement("title");
			if (title == null) {
				occurrences.add(this.buildOccurrence("19", true, head.toString(), head, "1"));
			} else if (title.isEmpty()) {
				occurrences.add(buildOccurrence("19", true, title.toString(), title, "1"));
			}
		}
		
		return occurrences;
	}
		
	private List<Occurrence> checkRecommendation20() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("20", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation21() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for(Element  link : getDocument().getAllElements("a")){
			String href = link.getAttributeValue("href");
			String title = link.getAttributeValue("title");
			
			if(isRegistroBr(href))
				occurrences.add(this.buildOccurrence("21", false, link.toString(), link, "1"));
			
			if(hasTitle(link) && !hasContent(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"2"));
			
			if(!hasTitle(link) && !hasContent(link) && hasImgWithoutAlt(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"4"));
			
			if(hasLeiaMaisDescription(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"5"));
			
			if(hasDiferenteContentSameLink(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"6"));
			
			if(hasSameContentDiferentLink(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"7"));
			
			if(isTitleEqualsContent(link))
				occurrences.add(this.buildOccurrence("21", true, link.toString(), link,"8"));
			
			if(StringUtils.isNotBlank(title) && title.length() > 500)
				occurrences.add(this.buildOccurrence("21", false, link.toString(), link,"9"));
			
			
		}
		
		return occurrences;
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
		
		Pattern pattern;
		for(String leiaMais:this.leiaMais){
			pattern = Pattern.compile("("+leiaMais+")");
			if(title!=null)
				if(pattern.matcher(title.toLowerCase()).find()) return true;
			
			if(content!=null)
				if(pattern.matcher(content.toLowerCase()).find()) return true;
			
			if(altImg!=null)
				if(pattern.matcher(altImg.toLowerCase()).find()) return true;
		}
		
		return false;
	}
	private boolean isRegistroBr(String href){
		if(StringUtils.isBlank(href)) return false;
		Pattern pattern;
		for(String registro:hrefRegistroBr){
			pattern = Pattern.compile("(.*)("+registro+")$");
			if(pattern.matcher(href.toUpperCase()).find()) return true;
		}
		
		return false;
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
		String content = link.getContent().getTextExtractor().toString();
		String href = link.getAttributeValue("href");
		if(StringUtils.isBlank(href)) return  false;
		String otherContent;
		String otherHref;
		for(Element otherLink:getDocument().getAllElements("a")){
			
			if(otherLink.getBegin() == link.getBegin()) continue;
			otherContent = otherLink.getContent().getTextExtractor().toString();
			otherHref = otherLink.getAttributeValue("href");
			if(StringUtils.isBlank(otherHref))continue;
			if(content.toLowerCase().equals(otherContent.toLowerCase()) && !href.equals(otherHref)) return true;
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
				occurrences.add(buildOccurrence("22", true, img.toString(), img, "1"));
			}else if(alt.getValueSegment().toString().trim().isEmpty()){
				occurrences.add(buildOccurrence("22", true, img.toString(), img, "2"));
			}	
			
			Attribute src = img.getAttributes().get("src");
			String contAlt = null;
			
			if(src != null && alt != null){
				String value = src.getValue();
				parts = value.toString().split("/");
				contAlt = alt.getValue();  
			
				if(parts[parts.length-1].toString().equals(contAlt))
					occurrences.add(buildOccurrence("22", true, img.toString(), img, "3"));
			}
			
			for(String descricao : descricoes){
				if(descricao.equalsIgnoreCase(contAlt))
					occurrences.add(buildOccurrence("22", true, img.toString(), img, "4"));
				
			}
			
			
		}
		
		Map<String, String> aMap = new HashMap<String, String>();
		
		for (Element img : getDocument().getAllElements("img")) {
			Attribute altAtt = img.getAttributes().get("alt");
			if (altAtt != null) {
				if(aMap.containsKey(altAtt.getValue())){
					Attribute srcAtt = img.getAttributes().get("src");
					if(srcAtt != null){
						if(!aMap.get(altAtt.getValue()).contains("src=\""+srcAtt.getValue()+"\""))
							occurrences.add(buildOccurrence("22", false, img.toString(), img, "5"));
					}
				}else{
					aMap.put(altAtt.getValue(), img.toString());
				}
			}
		}
		
		for (Element img : getDocument().getAllElements("img")) {
			Attribute alt = img.getAttributes().get("alt");
			Attribute title = img.getAttributes().get("title");
			if (alt != null && title != null) {
				if(title.getValue().equals(alt.getValue()))
					occurrences.add(buildOccurrence("22", true, img.toString(), img, "6"));
			}
		}
		
		
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation23() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("img")) {
			
			Attribute usemap = table.getAttributes().get("usemap");
			Attribute alt = table.getAttributes().get("alt");

			if (usemap != null && alt == null)
				occurrences.add(this.buildOccurrence("23", true, table.toString(), table, "1"));
			
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation24() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("24", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation25() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table")) {
			Attribute summary = table.getAttributes().get("summary");
			
			if (summary == null || summary.getValue().equals("")) 
				occurrences.add(buildOccurrence("25", false, table.toString(), table, "1"));
			
			if(table.getAllElements("caption").isEmpty())
				occurrences.add(buildOccurrence("25", false, table.toString(), table, "1"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation26() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
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
				occurrences.add(buildOccurrence("26", true, table.toString(), table, "1"));
			
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
			
			if(!usaThead && !usaTbody && !usaTfoot){
			
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
					occurrences.add(this.buildOccurrence("26", true, th.toString(), th, "1"));
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
					occurrences.add(this.buildOccurrence("26", true, td.toString(), td, "1"));
				}
			}
			
		 }
	   }
		
		return occurrences;

	}
	
	private List<Occurrence> checkRecommendation27() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		String reg = "<p.*?>(.*)<\\/p.*?>";
		
		for (Element paragrafo : getDocument().getAllElements("p")) {
			
			    Pattern p = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
		        Matcher m = p.matcher(paragrafo);
		       
		        while(m.find()){
		        	String conteudoParagrafo = m.group(1);
		        	
		        	if(conteudoParagrafo.length() > 1000)
						occurrences.add(this.buildOccurrence("27", false, paragrafo.toString(), paragrafo, "1"));
		        }
		        
		        String align = paragrafo.getAttributeValue("align");
		        if("justify".equals(align))
		        	occurrences.add(this.buildOccurrence("27", true, paragrafo.toString(), paragrafo, "2"));
		}
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.CONTENT_INFORMATION,criterio);
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.CONTENT_INFORMATION);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.CONTENT_INFORMATION;}
	
	private String[] hrefRegistroBr = {"COM.BR","ECO.BR","EMP.BR","NET.BR","EDU.BR",
			"ADM.BR","ADV.BR","ARQ.BR","ATO.BR","BIO.BR","BMD.BR","CIM.BR","CNG.BR",
			"CNT.BR","ECN.BR","ENG.BR","ETI.B","FND.BR","FOT.BR","FST.BR","GGF.BR","JOR.BR",
			"LEL.BR","MAT.BR","MED.BR","MUS.BR","NOT.BR","NTR.BR","ODO.BR","PPG.BR","PRO.BR", 
			"PSC.BR","QSL.BR","SLG.BR","TAXI.BR","TEO.BR","TRD.BR","VET.BR","ZLG.BR","BLOG.BR", 
			"FLOG.BR","NOM.BR","VLOG.BR","WIKI.BR","AGR.BR","ART.BR","ESP.BR","ETC.BR","FAR.BR",
			"IMB.BR","IND.BR","INF.BR","RADIO.BR","REC.BR","SRV.BR","TMP.BR","TUR.BR","TV.BR",
			"AM.BR","COOP.BR","FM.BR","G12.BR","GOV.BR","MIL.BR","ORG.BR","PSI.BR","B.BR",
			"JUS.BR","LEG.BR","MP.BR"};
	
	private String[] leiaMais = {"clique aqui","leia mais","saiba mais","veja mais","acesse a lista"};
	
}

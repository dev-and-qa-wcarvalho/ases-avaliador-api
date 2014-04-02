package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
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
		
		Element html = getDocument().getFirstElement("html");
		
		if (html != null) {
			Attribute lang = html.getAttributes().get("lang");
			Attribute xmlLang = html.getAttributes().get("xml:lang");
			
			if (lang == null && xmlLang == null) {
				occurrences.add(this.buildOccurrence("16", true, html.toString(), html));
			} else if (lang != null && lang.getValue().isEmpty()) {
				occurrences.add(this.buildOccurrence("16", true, html.toString(), html));
			} else if (xmlLang != null && xmlLang.getValue().isEmpty()) {
				occurrences.add(buildOccurrence("16", true, html.toString(), html));
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation17() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		Element head = getDocument().getFirstElement("head");
		
		if(head == null) {
			occurrences.add(new Occurrence("17", true, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		}else {
		
			Element title = head.getFirstElement("title");
			if (title == null) {
				occurrences.add(buildOccurrence("17", true, head.toString(), head));
			} else if (title.isEmpty()) {
				occurrences.add(buildOccurrence("17", true, title.toString(), title));
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation18() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("18", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation19() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element a : getDocument().getAllElements("a")) {
			Attribute href = a.getAttributes().get("href");
			Attribute name = a.getAttributes().get("name");
			Attribute id = a.getAttributes().get("id");
			
			if (href == null && name == null && id == null) {
				occurrences.add(buildOccurrence("19", true, a.toString(), a));
			
			
			} else if(name != null){
				String nameValue = name.getValue();
				System.out.println("Name Value = "+nameValue);
				boolean nameContemHref = false; 
				
				if(!nameValue.toString().contains("#")){
					nameValue = ("#"+nameValue);
				}
				
				for (Element aHref : getDocument().getAllElements("a")) {
					String hrefValue = aHref.getAttributeValue("href");
					System.out.println(nameValue+"=="+hrefValue);
					
					if(nameValue.equals(hrefValue))
						nameContemHref = true; 
				}
				
				if(!nameContemHref)
					occurrences.add(buildOccurrence("19", true, a.toString(), a));
			}
			
			
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation20() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		for (Element img : getDocument().getAllElements("img")) {
			Attribute alt = img.getAttributes().get("alt");
			if (alt == null || StringUtils.isEmpty(alt.getValue()))
				occurrences.add(buildOccurrence("20", true, img.toString(), img));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation21() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element area : getDocument().getAllElements("area")) {
			Attribute alt = area.getAttributes().get("alt");
			if (alt == null) {
				occurrences.add(buildOccurrence("21", true, area.toString(), area));
			} else if (alt.getValue().equals("")) {
				occurrences.add(buildOccurrence("21", true, area.toString(), area));
			}
		}
		for (Element img : getDocument().getAllElements("img")) {
			Attribute ismap = img.getAttributes().get("ismap");
			if (ismap != null && ismap.getValue().equals("ismap")) {
				occurrences.add(buildOccurrence("21", false, img.toString(), img));
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation22() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element a : getDocument().getAllElements("a")) {
			Attribute href = a.getAttributes().get("href");
			if (href != null) {
				if (href.getValue().endsWith(".pdf"))
					occurrences.add(buildOccurrence("22", false, a.toString(), a));
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation23() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table")) {
			Element caption = table.getFirstElement("caption");
			Attribute summary = table.getAttributes().get("summary");

			if (caption == null) {
				occurrences.add(buildOccurrence("23", true, table .toString().split("[\\r\\n]+")[0], table));
			} else if (caption.getAllElements().isEmpty() || caption.isEmpty()) {
				occurrences.add(buildOccurrence("23", true, caption.toString(), caption));
			}

			if (summary == null) {
				occurrences.add(buildOccurrence("23", true, table.toString().split("[\\r\\n]+")[0], table));
			} else if (summary.getValue().isEmpty()) {
				occurrences.add(buildOccurrence("23", true, table.toString().split("[\\r\\n]+")[0], table));
			}
		}
		
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation24() {
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
				occurrences.add(buildOccurrence("24", true, table.toString(), table));
			
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
					occurrences.add(buildOccurrence("24", true, th.toString(), th));
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
					occurrences.add(buildOccurrence("24", true, td.toString(), td));
				}
			}
			
			}
			
			
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation25() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("25", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation26() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element abbr : getDocument().getAllElements("abbr")) {
			Attribute title = abbr.getAttributes().get("title");
				if(title == null || title.getValue().equals(""))
					occurrences.add(buildOccurrence("26", true, abbr.toString(), abbr));
				else
					occurrences.add(buildOccurrence("26", false, abbr.toString(), abbr));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation27() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("27", false, getDocument().getFirstElement().toString(),OccurrenceClassification.CONTENT_INFORMATION));
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.CONTENT_INFORMATION);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.CONTENT_INFORMATION;}
}

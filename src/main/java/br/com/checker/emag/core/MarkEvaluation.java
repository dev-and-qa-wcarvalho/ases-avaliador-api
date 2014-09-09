package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.MarkRecommendation;

public class MarkEvaluation extends Evaluation {

	private MarkEvaluation(Source document) { super(document); }
	
	public static class MarkEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected MarkEvaluation with(Source document) { return new MarkEvaluation(document); }
		
		public SpecificRecommendation recommendation1() { return new EvaluationRecommendation1();}
		public SpecificRecommendation recommendation2() { return new EvaluationRecommendation2();}
		public SpecificRecommendation recommendation3() { return new EvaluationRecommendation3();}
		public SpecificRecommendation recommendation4() { return new EvaluationRecommendation4();}
		public SpecificRecommendation recommendation5() { return new EvaluationRecommendation5();}
		public SpecificRecommendation recommendation6() { return new EvaluationRecommendation6();}
		public SpecificRecommendation recommendation7() { return new EvaluationRecommendation7();}
		public SpecificRecommendation recommendation8() { return new EvaluationRecommendation8();}
		public SpecificRecommendation recommendation9() { return new EvaluationRecommendation9();}
	}
	
	protected static class EvaluationRecommendation1 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation1();}
	}
	
	protected static class EvaluationRecommendation2 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation2();}
	}
	
	protected static class EvaluationRecommendation3 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation3();}
	}
	
	protected static class EvaluationRecommendation4 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation4();}
	}
	
	protected static class EvaluationRecommendation5 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation5();}
	}
	
	protected static class EvaluationRecommendation6 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation6();}
	}
	
	protected static class EvaluationRecommendation7 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation7();}
	}
	
	protected static class EvaluationRecommendation8 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation8();}
	}
	
	protected static class EvaluationRecommendation9 extends MarkRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation9();}
	}
	
	
	public List<Occurrence> check() {
		getOccurrences().addAll(checkRecommendation1());
		getOccurrences().addAll(checkRecommendation2());
		getOccurrences().addAll(checkRecommendation3());
		getOccurrences().addAll(checkRecommendation4());
		getOccurrences().addAll(checkRecommendation5());
		getOccurrences().addAll(checkRecommendation6());
		getOccurrences().addAll(checkRecommendation7());
		getOccurrences().addAll(checkRecommendation8());
		getOccurrences().addAll(checkRecommendation9());
		
		return getOccurrences();
	}
	
	private List<Occurrence> checkRecommendation1() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements()) {
			String value = element.getAttributeValue("style");
			
			if(value != null)
				occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "3"));
		}
		
		for (Element element : getDocument().getAllElements("style")) {
			
			if(element != null)
				occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "4"));
		}
		
		for (Element element : getDocument().getAllElements("script")) {
			
			String value = element.getAttributeValue("src");
			if(value == null)
				occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "6"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation2() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		String[] tags = {"h1","h2","h3","h4","h5","h6","a","p"};
		
		for (String tag : tags) {
		
			for (Element element : getDocument().getAllElements(tag)) {
				Attributes attribute = element.getAttributes();
					if(attribute != null)
						if(attribute.getCount()==0)
							occurrences.add(this.buildOccurrence("2", false, element.toString(), element, "1"));
			}
		
			for (Element element : getDocument().getAllElements(tag)) {
				if(element.isEmpty()){
					occurrences.add(this.buildOccurrence("2", false, element.toString(), element, "2"));
					System.out.println(element.toString());
				}	
			}
		
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation3() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		int cont = 0, niveis = 0;
		
		for (Element element : getDocument().getAllElements("h1")){
			if(cont>0){
				occurrences.add(this.buildOccurrence("3", false,element.toString(), element, "3"));
			}else
				cont++;
		}
	
		for (Element elemento2 : getDocument().getAllElements("h2")){
			if(getDocument().getAllElements("h1").size()<1)
				occurrences.add(this.buildOccurrence("3", true, elemento2.toString(), elemento2,"2")); niveis++;
		}
		
		for (Element elemento3 : getDocument().getAllElements("h3")){
			if(getDocument().getAllElements("h2").size()<1)
				occurrences.add(this.buildOccurrence("3", true, elemento3.toString(), elemento3, "2")); niveis++;
		}
		
		for (Element elemento4 : getDocument().getAllElements("h4")){
			if(getDocument().getAllElements("h3").size()<1)
				occurrences.add(this.buildOccurrence("3", true, elemento4.toString(), elemento4, "2")); niveis++;
		}
		
		for (Element elemento5 : getDocument().getAllElements("h5")){
			if(getDocument().getAllElements("h4").size()<1)
				occurrences.add(this.buildOccurrence("3", true,elemento5.toString(), elemento5, "2")); niveis++;
		}
		
		for (Element elemento6 : getDocument().getAllElements("h6")){
			if(getDocument().getAllElements("h5").size()<1)
				occurrences.add(this.buildOccurrence("3", true, elemento6.toString(), elemento6, "2")); niveis++;
		}
				
		
		String[] tags = {"h1","h2","h3","h4","h5","h6"};
		
		int contTags = 0;
		
		for (String tag : tags) {
			if(getDocument().getAllElements(tag).size() > 0)
				contTags++;
		}
		
		if(contTags==0){
			Element element = getDocument().getFirstElement("html");
			occurrences.add(this.buildOccurrence("3", true,element.toString(), element, "1"));
		}	
			
		if((contTags - cont) == 0){
			Element element = getDocument().getFirstElement("html");
			occurrences.add(this.buildOccurrence("3", false ,element.toString(), element, "3"));
		}	
		
		return occurrences;
	}
	
	/*No documento NÃO PERMITE VERIFICAÇÃO AUTOMATIZADA*/
	private List<Occurrence> checkRecommendation4() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for(Element link : this.getDocument().getAllElements("a")){
			Attribute tabIndex = link.getAttributes().get("tabindex");
			if(tabIndex!=null) {
				occurrences.add(this.buildOccurrence("4", false,link.toString(), link));
				
				if(rangeIncorretoTabeIndex(tabIndex))
					occurrences.add(this.buildOccurrence("4", false,link.toString(), link));
			}
		}
		
		for(Element input : this.getDocument().getAllElements("input")){
			
			Attribute tabIndex = input.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("4", false, input.toString(), input));
		}
		
		for(Element select : this.getDocument().getAllElements("select")){
			
			Attribute tabIndex = select.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("4", false, select.toString(), select));
			
			if(rangeIncorretoTabeIndex(tabIndex))
				occurrences.add(this.buildOccurrence("4", false,select.toString(), select));
		}
		
		for(Element textarea : this.getDocument().getAllElements("textarea")){
			
			Attribute tabIndex = textarea.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("4", false, textarea.toString(), textarea));
			
			if(rangeIncorretoTabeIndex(tabIndex))
				occurrences.add(this.buildOccurrence("4", false,textarea.toString(), textarea));
		}
		
		Element section =  this.getDocument().getFirstElement("section");
		
		if(section !=null){
			int firstSection = section.getBegin();
			for(Element nav : this.getDocument().getAllElements("nav")){
				if(nav.getBegin() < firstSection)
					occurrences.add(this.buildOccurrence("4", false,nav.toString(), nav));
			}
		}
		
		return occurrences;
	}
	
	private boolean rangeIncorretoTabeIndex(Attribute tabIndex) {
		try{
			Integer value = Integer.valueOf(tabIndex.getValue() );
			if(value <0 || value > 32767)
				return true;
		}catch(Exception e){
			return false;
		}
		return false ;
	}
	
	
	/*No documento NÃO PERMITE VERIFICAÇÃO AUTOMATIZADA*/
	private List<Occurrence> checkRecommendation5() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements()) {
			
			Attribute onmousedown = null,onkeydown = null, 
					  onmouseup = null, onkeyup = null,
					  onclick = null, onkeypress = null,
					  onmouseover = null,onfocus = null,
					  onmouseout = null, onblur = null;
			
			if (element.getAttributes() != null) {
				onmousedown = element.getAttributes().get("onmousedown");
				onkeydown = element.getAttributes().get("onkeydown");

				onmouseup = element.getAttributes().get("onmouseup");
				onkeyup = element.getAttributes().get("onkeyup");

				onclick = element.getAttributes().get("onclick");
				onkeypress = element.getAttributes().get("onkeypress");

				onmouseover = element.getAttributes().get("onmouseover");
				onfocus = element.getAttributes().get("onfocus");

				onmouseout = element.getAttributes().get("onmouseout");
				onblur = element.getAttributes().get("onblur");
			}
			
			if (onmousedown != null && onkeydown == null) {
				occurrences.add(this.buildOccurrence("5", true, element.toString(), element));
				
			} else if (onmouseup != null && onkeyup == null) {
				occurrences.add(this.buildOccurrence("5", true, element.toString(), element));
				
			} else if (onclick != null && onkeypress == null) {
				occurrences.add(this.buildOccurrence("5", true, element.toString(), element));
				
			} else if (onmouseover != null && onfocus == null) {
				occurrences.add(this.buildOccurrence("5", true, element.toString(), element));
				
			} else if (onmouseout != null && onblur == null) {
				occurrences.add(this.buildOccurrence("5", true, element.toString(), element));
			}
		}
		
		return occurrences;
	}
	
	/*No documento NÃO PERMITE VERIFICAÇÃO AUTOMATIZADA*/
	public List<Occurrence> checkRecommendation6() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		Element html = getDocument().getFirstElement("html");
		if (html != null) {
			int anchors = 0;
			for (Element link : getDocument().getAllElements("a")) {
				String hrefValue = link.getAttributeValue("href");
				if (hrefValue != null && !hrefValue.isEmpty() && hrefValue.startsWith("#"))
					anchors++;
			}
			
			if (anchors == 0)
				occurrences.add(this.buildOccurrence("6", false, html.toString(), html));
		}
		return occurrences;
	}
	
	public List<Occurrence> checkRecommendation7() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table"))
			occurrences.add(this.buildOccurrence("7", false, table.toString(), table, "1"));
		
		for (Element table : getDocument().getAllElements("table")){
			Element form = table.getFirstElement("form");
			if(form != null)
				occurrences.add(this.buildOccurrence("7", true, form.toString(), form, "2"));
		}
		
		return occurrences;
	}
	
	public List<Occurrence> checkRecommendation8() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		Element firstElement = this.getDocument().getFirstElement();
		
		if(firstElement.toString().equals("<!DOCTYPE html>")){
			for (Element header : getDocument().getAllElements("header")){
				String role = header.getAttributeValue("role");
				
				if(role!=null && "banner".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, header.toString(), header));
			}
			
			for (Element nav : getDocument().getAllElements("nav")){
				String role = nav.getAttributeValue("role");
				
				if(role!=null && "navigation".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, nav.toString(), nav));
			}
			for (Element div : getDocument().getAllElements("div")){
				String role = div.getAttributeValue("role");
				
				if(role!=null && "main".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, div.toString(), div));
			}
			
			for (Element footer : getDocument().getAllElements("footer")){
				String role = footer.getAttributeValue("role");
				
				if(role!=null && "contentinfo".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, footer.toString(), footer));
			}
			
		}
		
		return occurrences;
	}
	
	public List<Occurrence> checkRecommendation9() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		boolean hasBlankLink = false;
		
		for (Element link : getDocument().getAllElements("a")) {
			
			if(link.toString().contains("_blank")){
				hasBlankLink = true;
				occurrences.add(this.buildOccurrence("9", true, link.toString(), link, "1"));
			}
		}
		
		if(!hasBlankLink)
			occurrences.add(new Occurrence("9", false,this.getDocument().getFirstElement().toString(),OccurrenceClassification.MARK));
		
		return occurrences;
	}
	
	
	private boolean isLinkChild(Element element){
		Element parent = element.getParentElement();
		if(parent == null)
			return false;
		
		if(parent.getStartTag().toString().contains("br"))
			parent = parent.getParentElement();
		
		if(element.getParentElement().getStartTag().toString().contains("<a"))
			return true;
		else
			return isLinkChild(element.getParentElement());
		
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.MARK,criterio);
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.MARK,null);
	}
 	
	public OccurrenceClassification type () { return OccurrenceClassification.MARK;}
}

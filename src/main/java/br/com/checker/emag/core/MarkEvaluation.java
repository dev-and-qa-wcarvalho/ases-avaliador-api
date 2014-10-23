package br.com.checker.emag.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.jcabi.w3c.ValidationResponse;
import com.jcabi.w3c.ValidatorBuilder;

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
		
		try {
			ValidationResponse response =
				      new ValidatorBuilder().html().validate(getDocument().toString());
			
			if(!response.errors().isEmpty())
				occurrences.add(this.buildOccurrence("1", true, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
			else if (!response.warnings().isEmpty())
				occurrences.add(this.buildOccurrence("1", false, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		String css = "";
		for (Element element : getDocument().getAllElements("style")) 
				css+= element.getContent().toString();
			
		if(StringUtils.isNotBlank(css)){	
			try {
				ValidationResponse response =
					      new ValidatorBuilder().css().validate(css);
				
				if(!response.errors().isEmpty())
					occurrences.add(this.buildOccurrence("1", true, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
				else if (!response.warnings().isEmpty())
					occurrences.add(this.buildOccurrence("1", false, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (Element element : getDocument().getAllElements()) {
			String value = element.getAttributeValue("style");
			
			if(value != null)
				occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "3"));
		}
		
		for (Element element : getDocument().getAllElements("style")) {
			
			if(element != null)
				occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "4"));
		}
		
		for (Element element : getDocument().getAllElements()) {
			if(element.getAttributes() != null){
				String script = element.getAttributes().getTextExtractor().toString();
				if(script.contains("javascript")){
					occurrences.add(this.buildOccurrence("1", false, element.toString(), element, "5"));
				}
			}
		}
		
		for (Element element : getDocument().getAllElements("script")) {
			
			if(element != null)
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
				if(element != null)
					if(element.isEmpty()){
						occurrences.add(this.buildOccurrence("2", false, element.toString(), element, "1"));
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
				occurrences.add(this.buildOccurrence("3", false,element.toString(), element, "4"));
			}else
				cont++;
		}
	
		List<Element> elementsObj = getDocument().getAllElements();
		List<String> tagsH = new ArrayList<String>();
		
		
		for (Element htmlElement : elementsObj) {
		    if (htmlElement.getName().matches("h[1-6]")) {
		    	tagsH.add(htmlElement.getName());
		    }
		}
		
		if(!tagsH.isEmpty())
			if(tagsH.get(0).equals("h1")){
				for (int i = 1; i < tagsH.size(); i++) {
					if(!(verificarNiveis(i, tagsH) > 0)){
						Element elemento = getDocument().getFirstElement(tagsH.get(i));
						occurrences.add(this.buildOccurrence("3", true, elemento.toString(), elemento,"2")); 
					}
				}
			}else{
				Element elemento = getDocument().getFirstElement(tagsH.get(0));
				if(elemento != null)
					occurrences.add(this.buildOccurrence("3", true, elemento.toString(), elemento,"2")); 
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
	
	private List<Occurrence> checkRecommendation5() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element elemento : getDocument().getAllElements("acesskey", Pattern.compile(".*"))) {
			String acessKey = elemento.getAttributeValue("acesskey");
			
			if(duplicatedAcessKey(acessKey))
				occurrences.add(this.buildOccurrence("5", true, elemento.toString(), elemento));
		}
		
		return occurrences;
	}
	
	private boolean duplicatedAcessKey(String value){
		int count=0;
		for (Element elemento : getDocument().getAllElements("acesskey", Pattern.compile(".*"))) {
			String acessKey = elemento.getAttributeValue("acesskey");
			if(acessKey.equals(value))
				count++;
			
			if(count>1)
				return true;
			
		}
		
		return false;
	}
	
	public List<Occurrence> checkRecommendation6() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table"))
			occurrences.add(this.buildOccurrence("6", false, table.toString(), table, "1"));
		
		for (Element form : getDocument().getAllElements("form")){
			Element tagsTabela = form.getFirstElement("table");
			if(tagsTabela != null)
				occurrences.add(this.buildOccurrence("6", true, form.toString(), form, "3"));
		}
		
		return occurrences;
	}
	
	
	public List<Occurrence> checkRecommendation7() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		List<Element> element = getDocument().getAllElements();
		
		int pos = 1;
		
		for (int i = 0; i < element.size() -1 ; i++) {
			if(element.get(i).getName().equals("a"))
					if(element.get(i).getName().equals(element.get(pos).getName())){
						occurrences.add(this.buildOccurrence("7", true, element.get(i).toString()+" "+element.get(pos).toString(), element.get(i), "1"));
					}	
			pos++;
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
					occurrences.add(this.buildOccurrence("8", false, header.toString(), header,"1"));
			}
			
			for (Element nav : getDocument().getAllElements("nav")){
				String role = nav.getAttributeValue("role");
				
				if(role!=null && "navigation".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, nav.toString(), nav,"1"));
			}
			for (Element div : getDocument().getAllElements("div")){
				String role = div.getAttributeValue("role");
				
				if(role!=null && "main".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, div.toString(), div,"1"));
			}
			
			for (Element footer : getDocument().getAllElements("footer")){
				String role = footer.getAttributeValue("role");
				
				if(role!=null && "contentinfo".equals(role.toLowerCase()))
					occurrences.add(this.buildOccurrence("8", false, footer.toString(), footer,"1"));
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
	
	private int verificarNiveis(Integer posicao, List<String> tags){
		for (int i = 0; i < posicao; i++) {
			if(Integer.parseInt(tags.get(i).substring(1)) == Integer.parseInt(tags.get(posicao).substring(1))-1){
				return Integer.parseInt(tags.get(i).substring(1));
        	}
		}	
		return -1;
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

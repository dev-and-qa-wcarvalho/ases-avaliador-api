package br.com.checker.emag.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.MarkRecommendation;

import com.jcabi.w3c.Defect;
import com.jcabi.w3c.ValidationResponse;
import com.jcabi.w3c.Validator;
import com.jcabi.w3c.ValidatorBuilder;

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
			
			if (!response.warnings().isEmpty()){
				for(Defect warn :response.warnings()) {
					occurrences.add(buildOccurrence(warn.line(), warn.column(), "1.1", false, warn.source().toString().isEmpty() ? warn.message() : "["+warn.message()+"] "+warn.source(), OccurrenceClassification.MARK,"1"));
					//occurrences.add(buildOccurrence("1.1", false,warn.line(),warn.column(),OccurrenceClassification.MARK,"1"));
					//occurrences.add(this.buildOccurrence("1.1", false, warn.source().toString().isEmpty() ? warn.message() : warn.source(), getDocument().getFirstElement(), "1"));
				}
			}	
			
			if(!response.errors().isEmpty()){
				for(Defect error :response.errors()) {
					occurrences.add(buildOccurrence(error.line(), error.column(), "1.1", true, error.source().toString().isEmpty() ? error.message() : "["+error.message()+"] "+error.source(), OccurrenceClassification.MARK,"1"));
					//occurrences.add(buildOccurrence("1.1", true,error.line(),error.column(),OccurrenceClassification.MARK,"1"));
					//occurrences.add(this.buildOccurrence("1.1", true, error.source().toString().isEmpty() ? error.message() : error.source(), getDocument().getFirstElement(), "1"));
				}
			}
			/*if(!response.errors().isEmpty()){
				for(Defect error :response.errors()) {
					occurrences.add(buildOccurrence("1.1", true,error.line(),error.column(),OccurrenceClassification.MARK,"1"));
				}
			}else if (!response.warnings().isEmpty()){
				for(Defect warn :response.warnings()) {
					occurrences.add(buildOccurrence("1.1", false,warn.line(),warn.column(),OccurrenceClassification.MARK,"1"));
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String css = "";
		for (Element element : getDocument().getAllElements("style")) 
				css+= element.getContent().toString();
		
		try {
			if(StringUtils.isNotBlank(css)){
				ValidationResponse response =
					      new ValidatorBuilder().css().validate(css);
				
			
					for(Defect warn :response.warnings()) {
						//occurrences.add(buildOccurrence("1.1", false,warn.line(),warn.column(),OccurrenceClassification.MARK,"2"));
						occurrences.add(buildOccurrence(warn.line(), warn.column(), "1.1", false, warn.source().toString().isEmpty() ? warn.message() : "["+warn.message()+"] "+warn.source(), OccurrenceClassification.MARK,"2"));
						//occurrences.add(this.buildOccurrence("1.1", false, warn.source(), getDocument().getFirstElement(), "2"));
					}
			
					for(Defect error :response.errors()) {
						//occurrences.add(buildOccurrence("1.1", false,error.line(),error.column(),OccurrenceClassification.MARK,"2"));
						occurrences.add(buildOccurrence(error.line(), error.column(), "1.1", true, error.source().toString().isEmpty() ? error.message() : "["+error.message()+"] "+error.source(), OccurrenceClassification.MARK,"2"));
						///occurrences.add(this.buildOccurrence("1.1", true, error.source().toString().isEmpty() ? error.message() : error.source(), getDocument().getFirstElement(), "2"));
					}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*String css = "";
		for (Element element : getDocument().getAllElements("style")) 
				css+= element.getContent().toString();
			
		if(StringUtils.isNotBlank(css)){	
		
			try {
				ValidationResponse response =
					      new ValidatorBuilder().css().validate(css);
				
				if(!response.errors().isEmpty())
					occurrences.add(this.buildOccurrence("1.1", true, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "2"));
					//occurrences.add(this.buildOccurrence("1.1", true, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
					
				else if (!response.warnings().isEmpty())
					occurrences.add(this.buildOccurrence("1.1", false, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "2"));
					//occurrences.add(this.buildOccurrence("1.1", false, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "1"));
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		for (Element element : getDocument().getAllElements()) {
			String value = element.getAttributeValue("style");
			
			if(value != null)
				occurrences.add(this.buildOccurrence("1.1", false, element.toString(), element, "3"));
		}
		
		for (Element element : getDocument().getAllElements("style")) {
			
			if(element != null)
				occurrences.add(this.buildOccurrence("1.1", false, element.toString(), element, "4"));
		}
		
		
		List<String> eventos = new ArrayList<String>( Arrays.asList("onclick", "ondblclick")); 
		
		
		for (Element element : getDocument().getAllElements()) {
			if(element.getAttributes() != null){
				//String script = element.getAttributes().getTextExtractor().toString();
				for (Attribute  attribute : element.getAttributes()) {
					if(eventos.contains(attribute.getName()))
						occurrences.add(this.buildOccurrence("1.1", false, element.toString(), element, "5"));
				}
				
			/*	if(script.contains("javascript")){
					occurrences.add(this.buildOccurrence("1.1", false, element.toString(), element, "5"));
				}*/
			}
		}
		
		for (Element element : getDocument().getAllElements("script")) {
			//if(element.getAttributeValue("src") ==null)
			if(element != null)
				occurrences.add(this.buildOccurrence("1.1", false, element.toString(), element, "6"));
		}
		
		this.oder(occurrences);
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation2() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		List<String> tags = Arrays.asList("h1","h2","h3","h4","h5","h6","a","p");
		
			for (String tag : tags) {
				for (Element element : getDocument().getAllElements(tag)) {
					
					/*if(element.getAttributes().getCount()==0)
						occurrences.add(this.buildOccurrence("1.2", true, element.toString(), element, "1"));
					else*/ 
					
					if(element != null){
						String endTag =   element.getEndTag() == null ? "" : element.getEndTag().toString();
						
						Element img =  element.getFirstElement("img");
						
						if(img != null){
							if(img.getAttributes().get("alt") != null && img.getAttributes().get("alt").getValue().isEmpty())
									occurrences.add(this.buildOccurrence("1.2", true,element.getStartTag().toString() + endTag, element, "1"));
						}else{
							if(element.getContent().toString().isEmpty() || element.getContent().toString().trim().equals("")){
								occurrences.add(this.buildOccurrence("1.2", true, element.getStartTag().toString() + endTag, element, "1"));
							}	
						}
					}
				}
				
			}
			
			for (Element element : getDocument().getAllElements()) {
				if(!tags.contains(element.getName())){
					if(element != null){
						
						String endTag =   element.getEndTag() == null ? "" : element.getEndTag().toString();
						
						Element img =  element.getFirstElement("img");
						
						if(img != null){
							if(img.getAttributes().get("alt") != null && img.getAttributes().get("alt").getValue().isEmpty())
								occurrences.add(this.buildOccurrence("1.2", false, img.toString(), element, "1"));
								
						}else{
							if(element.getContent().toString().isEmpty() || element.getContent().toString().trim().equals(""))
								occurrences.add(this.buildOccurrence("1.2", false, element.getStartTag().toString() + endTag, element, "1"));
								
						}
					}
				}
			}
			
			this.oder(occurrences);
		
		
			/*boolean isError = false;
			for (Element element : getDocument().getAllElements()) {
				Attributes attribute = element.getAttributes();
				
				isError = tags.contains(element.getName());
				
					
				if(element.isEmpty())
					occurrences.add(this.buildOccurrence("1.2", isError, element.toString(), element, "1"));
				else if(attribute.getCount()==0)
					occurrences.add(this.buildOccurrence("1.2", isError, element.toString(), element, "1"));
			}*/
		
			
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation3() {
		
		
		//CRITERIO 4
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		int count = 0;
		
		for (Element element : getDocument().getAllElements("h1")){
			if(count>0){
				occurrences.add(this.buildOccurrence("1.3", true,element.toString(), element, "4"));
			}else
				count++;
		}
		
		/*if(count>0)
			occurrences.add(this.buildOccurrence("1.3", true,getDocument().getFirstElement("h1").toString(), getDocument().getFirstElement("h1"), "4"));
		*/
		
		//CRITERIO 3
		boolean hasOtherH = false;
		for(Element element : getDocument().getAllElements()) {
			if (element.getName().matches("h[2-6]")) {
				hasOtherH = true;
				break;
		    }
		}
		
		if(count >0 && !hasOtherH)
			occurrences.add(this.buildOccurrence("1.3", false,getDocument().getFirstElement("h1").toString(), getDocument().getFirstElement("h1"), "3"));
		
		
		//CRITERIO 2
		
		/*String[] tags = {"h6","h5","h4","h3","h2","h1"};
		//String[] tags = {"h1","h2","h3","h4","h5","h6"};
		
		
		for(int index = 0 ; index< tags.length ; index++){
			
			for(Element h : getDocument().getAllElements(tags[index])) {
				if(!hasCorrectHierarchy(index, tags))
					occurrences.add(this.buildOccurrence("1.3", true,h.toString(), h, "2"));
			}
		}*/
		
		
		
		List<Element> elementsObj = getDocument().getAllElements();
		
		int anterior = 1;
		for (Element htmlElement : elementsObj) {
		    if (htmlElement.getName().matches("h[1-6]")) {
		    	int tagId = Integer.parseInt(htmlElement.getName().substring(1));
		    		if(!(tagId <= anterior)){
		    			if(!(tagId == anterior+1))
		    				occurrences.add(this.buildOccurrence("1.3", true,htmlElement.toString(), htmlElement, "2"));
		    		}
		    	anterior = tagId;
		    }
		}
		
		if(!hasH())
			occurrences.add(this.buildOccurrence("1.3", true,"Observa&ccedil;&atilde;o - Sem fonte (os n&iacute;veis de t&iacute;tulo n&atilde;o foram utilizados)", getDocument().getFirstElement(), "1"));
		
		//Sorting
		Collections.sort(occurrences, new Comparator<Occurrence>() {
		    public int compare(Occurrence  occurrence1, Occurrence  occurrence2){
	            return  occurrence1.getLine().compareTo(occurrence2.getLine());
	        }
	    });
		
			
		return occurrences;
	}
	
	private int verificarNiveis(Integer posicao, List<String> tags){
		for (int i = 0; i < posicao; i++) {
			if(Integer.parseInt( tags.get(i).substring(1)) == Integer.parseInt(tags.get(posicao).substring(1))-1){
				return Integer.parseInt(tags.get(i).substring(1));
        	}
		}	
		return -1;
	}
	
	private boolean hasCorrectHierarchy(int index,String[] tags) {
		
		for(int i = index+1;i<tags.length;i++ ){
			if(getDocument().getFirstElement(tags[i])== null)
				return false;
		}
		return true;
	}
	
	private boolean hasH() {
		
		String[] tags = {"h6","h5","h4","h3","h2","h1"};
		for(String h : tags){
			if(getDocument().getFirstElement(h) !=null)
				return true;
		}
		
		return false;
	}
	
	/*No documento NÃO PERMITE VERIFICAÇÃO AUTOMATIZADA*/
	private List<Occurrence> checkRecommendation4() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for(Element link : this.getDocument().getAllElements("a")){
			Attribute tabIndex = link.getAttributes().get("tabindex");
			if(tabIndex!=null) {
				occurrences.add(this.buildOccurrence("1.4", false,link.toString(), link, "2"));
				
				if(rangeIncorretoTabeIndex(tabIndex))
					occurrences.add(this.buildOccurrence("1.4", false,link.toString(), link, "3"));
			}
		}
		
		for(Element input : this.getDocument().getAllElements("input")){
			
			Attribute tabIndex = input.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("1.4", false, input.toString(), input, "2"));
			
			if(rangeIncorretoTabeIndex(tabIndex))
				occurrences.add(this.buildOccurrence("1.4", false,input.toString(), input, "3"));
		}
		
		for(Element select : this.getDocument().getAllElements("select")){
			
			Attribute tabIndex = select.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("1.4", false, select.toString(), select, "2"));
			
			if(rangeIncorretoTabeIndex(tabIndex))
				occurrences.add(this.buildOccurrence("1.4", false,select.toString(), select, "3"));
		}
		
		for(Element textarea : this.getDocument().getAllElements("textarea")){
			
			Attribute tabIndex = textarea.getAttributes().get("tabindex");
			if(tabIndex!=null)
				occurrences.add(this.buildOccurrence("1.4", false, textarea.toString(), textarea, "2"));
			
			if(rangeIncorretoTabeIndex(tabIndex))
				occurrences.add(this.buildOccurrence("1.4", false,textarea.toString(), textarea, "3"));
		}
		
		/*Element section =  this.getDocument().getFirstElement("section");
		
		if(section !=null){

			int firstSectionRow =  this.getRow(section);
			for(Element nav : this.getDocument().getAllElements("nav")){
				if(this.getRow(nav) < firstSectionRow)
					occurrences.add(this.buildOccurrence("1.4", false,nav.toString(), nav, "1"));
			}
		}*/
		
		
		List<Integer> verificadsos = new ArrayList<Integer>();
		for(Element nav : this.getDocument().getAllElements("nav")){
			if(nav !=null){
				int firstNavRow =  this.getRow(nav);
				
				for(Element section : this.getDocument().getAllElements("section")){
					
					if(firstNavRow < this.getRow(section)){
						if(!verificadsos.contains(this.getRow(section))){
							occurrences.add(this.buildOccurrence("1.4", false,section.getStartTag().toString(), section, "1"));
							verificadsos.add(this.getRow(section));
						}	
						
					}	
				}
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
		
		/*Element firstLink =  getDocument().getFirstElement("href", Pattern.compile("#.*"));
		
		if(firstLink != null)
			occurrences.add(this.buildOccurrence("1.5", false, firstLink.toString(), firstLink, "4"));*/
		
		for (Element link : getDocument().getAllElements("a")) {
			String contLink = link.getAttributeValue("href");
			if(contLink != null && contLink.length() > 1 && contLink.substring(0,1).equals("#")){
				occurrences.add(this.buildOccurrence("1.5", false, link.toString(), link, "4")); break;
			}	
			
		}
		
		
		
		
		/*if(firstLink == null){
			occurrences.add(this.buildOccurrence("1.5", false, getDocument().getFirstElement().toString(), getDocument().getFirstElement(), "4"));
		}
		else if(firstLink.getAttributeValue("href") == null || !firstLink.getAttributeValue("href").contains("#")){
			occurrences.add(this.buildOccurrence("1.5", false, firstLink.toString(), firstLink, "4"));
		}*/
		
		
		boolean existAcessKey = false;
		
		for (Element area : getDocument().getAllElements("area")) {
			if(hasAcessKey(area))
				existAcessKey = true;
			/*if(!hasAcessKey(area))
				occurrences.add(this.buildOccurrence("1.5", true, area.toString(), area, "3"));*/
		}
		
		if(!existAcessKey)
		for (Element button : getDocument().getAllElements("button")) {
			if(hasAcessKey(button))
				existAcessKey = true;
			/*if(!hasAcessKey(button))
				occurrences.add(this.buildOccurrence("1.5", true, button.toString(), button, "3"));*/
		}
		
		if(!existAcessKey)
		for (Element input : getDocument().getAllElements("input")) {
			if(hasAcessKey(input))
				existAcessKey = true;
			/*if(!hasAcessKey(input))
				occurrences.add(this.buildOccurrence("1.5", true, input.toString(), input, "3"));*/
		}
		
		if(!existAcessKey)
		for (Element label : getDocument().getAllElements("label")) {
			if(hasAcessKey(label))
				existAcessKey = true;
			/*if(!hasAcessKey(label))
				occurrences.add(this.buildOccurrence("1.5", true, label.toString(), label, "3"));*/
		}
		
		if(!existAcessKey)
		for (Element legend : getDocument().getAllElements("legend")) {
			if(hasAcessKey(legend))
				existAcessKey = true;
			/*if(!hasAcessKey(legend))
				occurrences.add(this.buildOccurrence("1.5", true, legend.toString(), legend, "3"));*/
		}
		
		if(!existAcessKey)
		for (Element textarea : getDocument().getAllElements("textarea")) {
			if(hasAcessKey(textarea))
				existAcessKey = true;
			/*if(!hasAcessKey(textarea))
				occurrences.add(this.buildOccurrence("1.5", true, textarea.toString(), textarea, "3"));*/
		}
		
		String href;
		boolean existAnchor = false;
				
	
		for (Element link : getDocument().getAllElements("a")) {
			href = link.getAttributeValue("href");
			
			/*if(href != null && (href.length() > 1 && !href.substring(0,2).equals("?#"))){
				///occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "2"));
				if(!(href.length()==1 && href.substring(1).equals("#")))
				if(href.substring(1).equals("#") && !this.hasLinkSemAncora(href))
					occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "2"));
			}*/
			
			if(href != null && href.length() > 1 && !href.substring(0,2).equals("?#")){
				if(href.substring(0,1).equals("#"))
					if(!this.hasLinkRefAncora(link))
					occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "2"));
			}
			
			
			
			/*if(href != null && href.contains("#"))
				if(!hasAnchor(href.substring(1)))
					occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "2"));*/
			/*}else{
				occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "1"));
			}*/
			
			//if(href != null && href.contains("#"))
					
			/*if(href != null && href.length() == 1 && href.toString().equals("#"))
				existAnchor = true;*/
			if(href != null && href.contains("#"))
					existAnchor = true;
			
			if(hasAcessKey(link))
				existAcessKey = true;
			
			/*if(!hasAcessKey(link))
				occurrences.add(this.buildOccurrence("1.5", true, link.toString(), link, "3"));*/
		}
		
		if(!existAnchor)
			occurrences.add(this.buildOccurrence("1.5", true, "Observa&ccedil;&atilde;o - Sem fonte (n&atilde;o foram encontrados &acirc;ncoras que permitam saltar pelas diferentes se&ccedil;&otilde;es da p&aacute;gina)", getDocument().getFirstElement(), "1"));
		
		if(!existAcessKey)
			occurrences.add(this.buildOccurrence("1.5", true, "Observa&ccedil;&atilde;o - Sem fonte ( n&atilde;o existe(m) accesskey(s) na página)", getDocument().getFirstElement(), "3"));
		
		for (Element elemento : getDocument().getAllElements("accesskey", Pattern.compile(".*"))) {
			if(duplicatedAcessKey(elemento))
				occurrences.add(this.buildOccurrence("1.5", false, elemento.toString(), elemento,"5"));
		}
		
		this.oder(occurrences);
		
		return occurrences;
	}
	
	private boolean hasLinkRefAncora(Element link) {
		boolean existeAncora = false;
		String href = link.getAttributeValue("href");
		String ancora = href.substring(1, href.length());
		
		for (Element a : getDocument().getAllElements("a")){ 
			if(a.getAttributeValue("name") != null && a.getAttributeValue("name").equals(ancora)){
				existeAncora = true;
			}
		}
		
		return existeAncora;
	}
	
	private boolean hasAcessKey(Element element) {
		String acessKey = element.getAttributeValue("accesskey");
		
		return StringUtils.isNotBlank(acessKey);
	}
	private boolean hasAnchor(String href) {
		
		String id,name;
		
		for (Element element : getDocument().getAllElements()) {
			id = element.getAttributeValue("id");
			name = element.getAttributeValue("name");
			
			if(href.equals(id) || href.equals(name))
				return true;
		}
		
		return false;
	}
	
	private boolean duplicatedAcessKey(Element element){
		String value = element.getAttributeValue("accesskey");
		
		for (Element elementToCompare : getDocument().getAllElements("accesskey", Pattern.compile(".*"))) {
			String acessKey = elementToCompare.getAttributeValue("accesskey");
			if(acessKey.equals(value) && elementToCompare.getBegin() != element.getBegin())
				return true;
			
		}
		
		return false;
	}
	
	public List<Occurrence> checkRecommendation6() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element table : getDocument().getAllElements("table"))
			occurrences.add(this.buildOccurrence("1.6", false, table.getStartTag().toString(), table, "1"));
		
		
		int firstFormRow = 0;
		
		List<Integer> linhasOcorrForm = new ArrayList<Integer>();
		
		for (Element table : getDocument().getAllElements("table")){
			//Element tagsForm = table.getFirstElement("form");
			
			Element form = table.getContent().getFirstElement("form");
			
			if(form != null){
			   firstFormRow = this.getRow(form);
			   if(!linhasOcorrForm.contains(firstFormRow)){
				   occurrences.add(this.buildOccurrence("1.6", true, form.getStartTag().toString(), form, "2"));
				   linhasOcorrForm.add(firstFormRow);
			   }
			}
			
		}
		
		return occurrences;
	}
	
	
	public List<Occurrence> checkRecommendation7() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		List<Element> element = getDocument().getAllElements();
		
		int pos = 1;
		int end = 0;
		int begin = 0;
		Element firstElement = null;
		Element secondElement = null;
		for (int i = 0; i < element.size() -1 ; i++) {
			firstElement = element.get(i);
			secondElement = element.get(pos);
			if(firstElement.getEndTag() != null && firstElement.getName().equals("a"))
			 	end = firstElement.getEndTag().getEnd();
			 	begin = secondElement.getStartTag().getBegin();
				if(element.get(i).getName().equals(element.get(pos).getName()) && (end == begin) ){
					occurrences.add(this.buildOccurrence("1.7", true, element.get(i).toString()+" "+element.get(pos).toString(), element.get(i), "1"));
				}	
			pos++;
		}
		
		return occurrences;
	}
	
	public List<Occurrence> checkRecommendation8() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		Element firstElement = this.getDocument().getFirstElement();
		
		if(firstElement.toString().equals("<!DOCTYPE html>")){
			
			Element header = getDocument().getFirstElement("header");
			Element nav = getDocument().getFirstElement("nav");
			Element section = getDocument().getFirstElement("section");
			Element footer = getDocument().getFirstElement("footer");
			
			if(header == null)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag <HEADER>)", getDocument().getFirstElement(),"1"));
			
			if(nav == null)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag <NAV>)", getDocument().getFirstElement(),"1"));
			
			if(section == null)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag <SECTION>)", getDocument().getFirstElement(),"1"));
			
			if(footer == null)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag <FOOTER>)", getDocument().getFirstElement(),"1"));
		
		}else{
			
			boolean hasBanner = false;
			boolean hasNavigation = false;
			boolean hasMain = false;
			for (Element element : getDocument().getAllElements()){
				String role = element.getAttributeValue("role");
				
				if(role!=null && "banner".equals(role.toLowerCase()))
					hasBanner = true;
				
				if(role!=null && "navigation".equals(role.toLowerCase()))
					hasNavigation = true;
				
				if(role!=null && "main".equals(role.toLowerCase()))
					hasMain = true;
				
				if(hasBanner && hasNavigation && hasMain)
					break;
			}
			
			if(!hasBanner)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o  existe tag com atributo ROLE e conte&uacute;do BANNER)", getDocument().getFirstElement(),"2"));
			
			if(!hasNavigation)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag com atributo ROLE e conte&uacute;do NAVIGATION)", getDocument().getFirstElement(),"2"));
			
			if(!hasMain)
				occurrences.add(this.buildOccurrence("1.8", false, "Observa&ccedil;&atilde;o - Sem fonte (N&atilde;o existe tag com atributo ROLE e conte&uacute;do MAIN)", getDocument().getFirstElement(),"2"));
	
		}
		
		return occurrences;
	}
	
	public List<Occurrence> checkRecommendation9() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		boolean hasBlankLink = false;
		
		for (Element link : getDocument().getAllElements("a")) {
			
			if(link.toString().contains("_blank")){
				hasBlankLink = true;
				occurrences.add(this.buildOccurrence("1.9", false, link.toString(), link, "1"));
			}
		}
		
		/*if(!hasBlankLink)
			occurrences.add(new Occurrence("1.9", false,this.getDocument().getFirstElement().toString(),OccurrenceClassification.MARK));*/
		
		return occurrences;
	}
	
	private List<Occurrence> oder(List occurrences){
		//Sorting
		Collections.sort(occurrences, new Comparator<Occurrence>() {
		    public int compare(Occurrence  occurrence1, Occurrence  occurrence2){
	            return  occurrence1.getLine().compareTo(occurrence2.getLine());
	        }
	    });
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.MARK,criterio);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.MARK;}
}

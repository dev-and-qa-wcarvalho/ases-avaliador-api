package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.BehaviorRecommendation;

public class BehaviorEvaluation extends Evaluation{

	private BehaviorEvaluation(Source document) { super(document); }
	
	public static class BehaviorEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected BehaviorEvaluation with(Source document) { return new BehaviorEvaluation(document); }
		
		public SpecificRecommendation recommendation10() { return new EvaluationRecommendation10();}
		public SpecificRecommendation recommendation11() { return new EvaluationRecommendation11();}
		public SpecificRecommendation recommendation12() { return new EvaluationRecommendation12();}
		public SpecificRecommendation recommendation13() { return new EvaluationRecommendation13();}
		public SpecificRecommendation recommendation14() { return new EvaluationRecommendation14();}
		public SpecificRecommendation recommendation15() { return new EvaluationRecommendation15();}
	}
	
	protected static class EvaluationRecommendation10 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation10();}
	}
	
	protected static class EvaluationRecommendation11 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation11();}
	}
	
	protected static class EvaluationRecommendation12 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation12();}
	}
	
	protected static class EvaluationRecommendation13 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation13();}
	}
	
	protected static class EvaluationRecommendation14 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation14();}
	}
	
	protected static class EvaluationRecommendation15 extends BehaviorRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation15();}
	}
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation10());
		getOccurrences().addAll(checkRecommendation11());
		getOccurrences().addAll(checkRecommendation12());
		getOccurrences().addAll(checkRecommendation13());
		getOccurrences().addAll(checkRecommendation14());
		getOccurrences().addAll(checkRecommendation15());
		
		return getOccurrences();
	}
	
	
	
	private List<Occurrence> checkRecommendation10() {
List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements()) {
			
			Attribute onmousedown = null,onkeydown = null, 
					  onmouseup = null, onkeyup = null,
					  onclick = null, onkeypress = null,
					  onmouseover = null,onfocus = null,
					  onmouseout = null, onblur = null,dbclick = null;
			
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
				
				dbclick = element.getAttributes().get("dbclick");
			}
			
			if(dbclick!=null){
				occurrences.add(this.buildOccurrence("10", false, element.toString(), element));
			}
			
			if (onmousedown != null && onkeydown == null) {
				occurrences.add(this.buildOccurrence("10", true, element.toString(), element));
				
			} else if (onmouseup != null && onkeyup == null) {
				occurrences.add(this.buildOccurrence("10", true, element.toString(), element));
				
			} else if (onclick != null && onkeypress == null) {
				occurrences.add(this.buildOccurrence("10", true, element.toString(), element));
				
			} else if (onmouseover != null && onfocus == null) {
				occurrences.add(this.buildOccurrence("10", true, element.toString(), element));
				
			} else if (onmouseout != null && onblur == null) {
				occurrences.add(this.buildOccurrence("10", true, element.toString(), element));
			}
		}
		
		return occurrences;
	}
	
	
	
	private List<Occurrence> checkRecommendation11() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean script = false;
		boolean contAlter = false;
		
		if(!getDocument().getAllElements("script").isEmpty())
			script = true;
		
		if(script)
			if(getDocument().getAllElements("noscript").isEmpty())
				occurrences.add(this.buildOccurrence("11", true, getDocument().getFirstElement("html").toString(), getDocument().getFirstElement("html"), "1"));
			
		
		for (Element object : getDocument().getAllElements("object")) {
			
			for(Element elemnet : object.getChildElements()){
				if(!elemnet.getName().equals("param")){
					contAlter = true;
				}
			}
			
			if(!contAlter){
				occurrences.add(this.buildOccurrence("11", true, object.toString(), object, "2"));
				contAlter = false;
			}	
			
		}
		
		if(!getDocument().getAllElements("embed").isEmpty()){
			for(Element embed : getDocument().getAllElements("embed"))
				occurrences.add(this.buildOccurrence("11", false, embed.toString(), embed, "3"));
		}	
		
		if(!getDocument().getAllElements("applet").isEmpty()){
			for(Element applet : getDocument().getAllElements("applet"))
				occurrences.add(this.buildOccurrence("11", false, applet.toString(), applet, "4"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation12() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean temMetaRefresh = false;
		for (Element element : getDocument().getAllElements("meta")) {
			Attribute httpEquiv = element.getAttributes().get("http-equiv");
			
			if (httpEquiv != null && "refresh".equals(httpEquiv.getValue())) {
				occurrences.add(this.buildOccurrence("12", false, element.toString(), element, "1"));
				temMetaRefresh = true;
			}
		}
		
		if(!temMetaRefresh)
			occurrences.add(new Occurrence("12", false, this.getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation13() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element element : getDocument().getAllElements("meta")) {
			Attribute content = element.getAttributes().get("content");
			Attribute httpEquiv = element.getAttributes().get("http-equiv");
			
			boolean url = false;
			if(content != null)
				url = content.toString().contains("url");
			
			if (httpEquiv != null && "refresh".equals(httpEquiv.getValue()) && content != null && url == true) 
				occurrences.add(this.buildOccurrence("13", true, element.toString(), element, "1"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation14() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		boolean hasBlink = false;
		boolean hasMarquee = false;
		for (Element blink : getDocument().getAllElements("blink")) {
			occurrences.add(this.buildOccurrence("14", true, blink.toString(), blink));
			hasBlink = true;
		}
		
		for(Element marquee : getDocument().getAllElements("marquee")) {
			occurrences.add(this.buildOccurrence("14", true, marquee.toString(), marquee));
			hasMarquee = true;
		}
		
		if (!hasBlink && !hasMarquee)
			occurrences.add(new Occurrence("14", false, getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR,"1"));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation15() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element blink : getDocument().getAllElements("blink")) {
			occurrences.add(this.buildOccurrence("15", true, blink.toString(), blink, "1"));
		}
		
		for(Element marquee : getDocument().getAllElements("marquee")) {
			occurrences.add(this.buildOccurrence("15", true, marquee.toString(), marquee, "2"));
		}
		
		for(Element img : getDocument().getAllElements("img")) {
			Attribute src = img.getAttributes().get("src");
			if(src != null)
			if(img.getAttributeValue("src").contains(".gif")){
				occurrences.add(this.buildOccurrence("15", false, img.toString(), img, "3"));
			}
		}
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.BEHAVIOR,criterio);
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.BEHAVIOR);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.BEHAVIOR;}
}

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
		
		
		for (Element script : getDocument().getAllElements("script")) {
			Element depois = this.getDocument().getNextElement(script.getEnd());
			
			if (depois == null || !depois.getName().equals("noscript")) 
				occurrences.add(this.buildOccurrence("10", true, script.toString(), script));
		}
		
		for (Element video : this.getDocument().getAllElements("object"))
			occurrences.add(this.buildOccurrence("10",true, video.toString(), video));
		
		return occurrences;
	}
	
	
	
	private List<Occurrence> checkRecommendation11() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean temMetaRefresh = false;
		for (Element element : getDocument().getAllElements("meta")) {
			Attribute httpEquiv = element.getAttributes().get("http-equiv");
			
			if (httpEquiv != null && "refresh".equals(httpEquiv.getValue())) {
				occurrences.add(this.buildOccurrence("11", true, element.toString(), element));
				temMetaRefresh = true;
			}
		}
		
		if(!temMetaRefresh)
			occurrences.add(new Occurrence("11", false, this.getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation12() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean temMetaRefresh = false;
		for (Element element : getDocument().getAllElements("meta")) {
			Attribute httpEquiv = element.getAttributes().get("http-equiv");
			
			if (httpEquiv != null && "refresh".equals(httpEquiv.getValue())) {
				occurrences.add(this.buildOccurrence("12", true, element.toString(), element));
				temMetaRefresh = true;
			}
		}
		
		if(!temMetaRefresh)
			occurrences.add(new Occurrence("12", false, this.getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation13() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("13", false, this.getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
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
			occurrences.add(new Occurrence("14", false, getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation15() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		occurrences.add(new Occurrence("15", false, getDocument().getFirstElement().toString(),OccurrenceClassification.BEHAVIOR));
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.BEHAVIOR);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.BEHAVIOR;}
}

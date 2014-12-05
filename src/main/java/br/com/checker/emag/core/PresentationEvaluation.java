package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.PresentationRecommendation;

public class PresentationEvaluation extends Evaluation{

	private PresentationEvaluation(Source document) { super(document); }
	
	public static class PresentationEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected PresentationEvaluation with(Source document) { return new PresentationEvaluation(document); }
		
		public SpecificRecommendation recommendation28() { return new EvaluationRecommendation28();}
		public SpecificRecommendation recommendation29() { return new EvaluationRecommendation29();}
		public SpecificRecommendation recommendation30() { return new EvaluationRecommendation30();}
		public SpecificRecommendation recommendation31() { return new EvaluationRecommendation31();}
		public SpecificRecommendation recommendation32() { return new EvaluationRecommendation32();}
	}
	
	protected static class EvaluationRecommendation28 extends PresentationRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation28();}
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
		getOccurrences().addAll(checkRecommendation28());
		getOccurrences().addAll(checkRecommendation29());
		getOccurrences().addAll(checkRecommendation30());
		getOccurrences().addAll(checkRecommendation31());
		getOccurrences().addAll(checkRecommendation32());
		
		return getOccurrences();
	}
	
	private List<Occurrence> checkRecommendation28() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element abbr : getDocument().getAllElements("abbr")) {
			Attribute title = abbr.getAttributes().get("title");
				if(title == null || title.getValue().equals(""))
					occurrences.add(buildOccurrence("3.12", true, abbr.toString(), abbr, "1"));
				
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation29() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		boolean temBgcolor = false;
		for (Element bgcolor : getDocument().getAllElements("body")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				temBgcolor = true;
			}
		}
		for (Element bgcolor : getDocument().getAllElements("table")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				temBgcolor = true;
			}
		}
		for (Element bgcolor : getDocument().getAllElements("td")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				temBgcolor = true;
			}
		}
		for (Element bgcolor : getDocument().getAllElements("tr")) {
			Attribute bgc = bgcolor.getAttributes().get("bgcolor");
			if(bgc != null){
				occurrences.add(buildOccurrence("4.1", true, bgcolor.toString(), bgcolor,"1"));
				temBgcolor = true;
			}
		}	
		
		if(!temBgcolor)
			occurrences.add(new Occurrence("4.1", false, getDocument().getFirstElement().toString(),OccurrenceClassification.PRESENTATION_DESIGN));
		
		return occurrences;
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
			
			if(style.toString().contains("a:focus") || style.toString().contains("a:hover"))
				occurrences.add(buildOccurrence("4.4", false, style.toString(), style, "1"));
		}
		
		for (Element style : getDocument().getAllElements("a")) {
			Attribute attribute = style.getAttributes().get("style");
			if(attribute != null)
				if(attribute.toString().contains("a:focus") || attribute.toString().contains("a:hover"))
					occurrences.add(buildOccurrence("4.4", false, style.toString(), style, "1"));
		}
		
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

package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.MultimediaRecommendation;

public class MultimediaEvaluation extends Evaluation{

	private MultimediaEvaluation(Source document) { super(document); }
	
	public static class MultimediaEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected MultimediaEvaluation with(Source document) { return new MultimediaEvaluation(document); }
		
		public SpecificRecommendation recommendation33() { return new EvaluationRecommendation33();}
		public SpecificRecommendation recommendation34() { return new EvaluationRecommendation34();}
		public SpecificRecommendation recommendation35() { return new EvaluationRecommendation35();}
		public SpecificRecommendation recommendation36() { return new EvaluationRecommendation36();}
		public SpecificRecommendation recommendation37() { return new EvaluationRecommendation37();}
		
	}
	
	protected static class EvaluationRecommendation33 extends MultimediaRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation33();}
	}
	
	protected static class EvaluationRecommendation34 extends MultimediaRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation34();}
	}
	
	protected static class EvaluationRecommendation35 extends MultimediaRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation35();}
	}
	
	protected static class EvaluationRecommendation36 extends MultimediaRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation36();}
	}
	
	protected static class EvaluationRecommendation37 extends MultimediaRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation37();}
	}
	
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation33());
		getOccurrences().addAll(checkRecommendation34());
		getOccurrences().addAll(checkRecommendation35());
		getOccurrences().addAll(checkRecommendation36());
		getOccurrences().addAll(checkRecommendation37());
		
		return getOccurrences();
	}
	
	
	
	private List<Occurrence> checkRecommendation33() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		
		for (Element video : getDocument().getAllElements("embed")){
			
			Attribute value = video.getAttributes().get("src");
			if (value != null){
				if (value.getValue().contains(".mp4")
						|| value.getValue().contains(".avi")
						|| value.getValue().contains(".flv")
						|| value.getValue().contains(".rmvb")
						|| value.getValue().contains(".WebM")
						|| value.getValue().contains(".Ogg")) {
					occurrences.add(this.buildOccurrence("33", false, video.toString(), video, "1"));
				}
			
			}
		}
		
		for (Element video : this.getDocument().getAllElements("object")) {
				Attribute value = video.getAttributes().get("data");
				if (value != null)
					if (value.getValue().contains(".mp4")
							|| value.getValue().contains(".avi")
							|| value.getValue().contains(".flv")
							|| value.getValue().contains(".rmvb")
							|| value.getValue().contains(".WebM")
							|| value.getValue().contains(".Ogg")) {
						occurrences.add(this.buildOccurrence("33", false, video.toString(), video, "1"));
					}
				
		}
		
		for (Element video : this.getDocument().getAllElements("video")) {
			String descricao = video.getTextExtractor().toString().trim();
			if(descricao.length() == 0)
				occurrences.add(this.buildOccurrence("33", false, video.toString(), video, "1"));
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation34() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element video : getDocument().getAllElements("object")) {
			Attribute data = video.getAttributes().get("data");
			
			if(data != null && (data.getValue().contains("mp3") || data.getValue().contains("wma") || data.getValue().contains("wav")))
				occurrences.add(this.buildOccurrence("34", false, video.toString(), video, "1"));
	
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation35() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element video : getDocument().getAllElements("object"))
			occurrences.add(this.buildOccurrence("35", false, video.toString(), video, "1"));
		
		for (Element video : getDocument().getAllElements("embed"))
			occurrences.add(this.buildOccurrence("35", false, video.toString(), video, "1"));
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation36() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element musica : getDocument().getAllElements("embed")){
			

			Attribute type = musica.getAttributes().get("type");
			if (type != null)
				if (type.getValue().contains("audio/x-mpeg")
						|| type.getValue().contains("audio/basic")
						|| type.getValue().contains("audio/wav")
						|| type.getValue().contains("audio/x-wav")
						|| type.getValue().contains("audio/x-pn-realaudio")
						|| type.getValue().contains("audio/x-ms-wma")
						|| type.getValue().contains("audio/ogg")
						|| type.getValue().contains("audio/mpeg")) {
			
				String autostart = musica.getAttributes().get("autoplay").getValue();
				String hidden = musica.getAttributes().get("hidden").getValue();
				
				if(autostart != null && hidden != null){
					if(autostart.equals("true") && hidden.equals("true"))
						occurrences.add(this.buildOccurrence("36", false, musica.toString(), musica, "1"));
				}
			}
		}
		
		for (Element musica : getDocument().getAllElements("object")){
			
			Attribute type = musica.getAttributes().get("type");
			if (type != null)
				if (type.getValue().contains("audio/x-mpeg")
						|| type.getValue().contains("audio/basic")
						|| type.getValue().contains("audio/wav")
						|| type.getValue().contains("audio/x-wav")
						|| type.getValue().contains("audio/x-pn-realaudio")
						|| type.getValue().contains("audio/x-ms-wma")
						|| type.getValue().contains("audio/ogg")
						|| type.getValue().contains("audio/mpeg")) {
			
					for (Element param : musica.getAllElements("param")){
				
						String name = param.getAttributes().getValue("name");
						String value = param.getAttributes().getValue("value");
						if(name != null && value != null){
							if(name.equals("autoplay") && value.equals("false"))
								occurrences.add(this.buildOccurrence("36", false, musica.toString(), musica, "1"));
					
							if(name.equals("autostart") && value.equals("0"))
								occurrences.add(this.buildOccurrence("36", false, musica.toString(), musica, "1"));
					}
				}	
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation37() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		for (Element video : getDocument().getAllElements("object")) {
			
			if (video.toString().contains("swf")) 
				occurrences.add(this.buildOccurrence("37", false, video.toString(), video));
		}
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.MULTIMEDIA,criterio);
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.MULTIMEDIA);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.MULTIMEDIA;}
}

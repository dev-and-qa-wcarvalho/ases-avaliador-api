package br.com.checker.emag.core;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.SummarizedOccurrence;
import br.com.checker.emag.core.BehaviorEvaluation.BehaviorEvaluationBuilder;
import br.com.checker.emag.core.ContentEvaluation.ContentEvaluationBuilder;
import br.com.checker.emag.core.Evaluation.EvaluationBuilder;
import br.com.checker.emag.core.FormEvaluation.FormEvaluationBuilder;
import br.com.checker.emag.core.MarkEvaluation.MarkEvaluationBuilder;
import br.com.checker.emag.core.MultimediaEvaluation.MultimediaEvaluationBuilder;
import br.com.checker.emag.core.PresentationEvaluation.PresentationEvaluationBuilder;



public class Checker {
	
	private Source document;
	private Map<OccurrenceClassification,List<Occurrence>> occurrencesMap = new HashMap<OccurrenceClassification, List<Occurrence>>();;
	private String url;
	
	private Checker(String html) { 
		
		this.document = new Source(html);
		this.document.fullSequentialParse();
	}
	
	private Checker(String html,String url) { 
		this(html);
		this.url = url;
	}
	
	public static Checker from(String html) { return new Checker(html); }
	
	public static Checker from(String html,String url) { return new Checker(html,url); }
	
	public static Checker from(BufferedReader reader) throws IOException {

		String html = "";   
	    String linha = "";  
	    while( ( linha = reader.readLine() ) != null )  
	        html += linha;  
		
		return new Checker(html);
	}
		
	
	public static MarkEvaluationBuilder marking() { return new MarkEvaluationBuilder(); }
	public static FormEvaluationBuilder form() { return new FormEvaluationBuilder(); }
	public static BehaviorEvaluationBuilder behavior() { return new BehaviorEvaluationBuilder(); }
	public static MultimediaEvaluationBuilder multimedia() { return new MultimediaEvaluationBuilder(); }
	public static ContentEvaluationBuilder content() { return new ContentEvaluationBuilder(); }
	public static PresentationEvaluationBuilder presentation() { return new PresentationEvaluationBuilder(); }
	
	public Checker with(EvaluationBuilder builder) {
		Evaluation evaluation = builder.with(this.document,url);
		occurrencesMap.put(evaluation.type(), evaluation.check());
		return this;
	}
	
	public Checker with(SpecificRecommendation evaluation){
		evaluation.with(document,url);
		
		if(occurrencesMap.get(evaluation.type())!=null)
			occurrencesMap.get(evaluation.type()).addAll(evaluation.check());
		else
			occurrencesMap.put(evaluation.type(), evaluation.check());
		
		return this;
	}
	
	public Map<OccurrenceClassification,List<Occurrence>> check() { return this.occurrencesMap; }
	
	public List<SummarizedOccurrence>  checkSumarized() {
		
		List<SummarizedOccurrence> list = new ArrayList<SummarizedOccurrence>();
		 
		for(Entry<OccurrenceClassification, List<Occurrence>> entry :this.occurrencesMap.entrySet()) {
		
			for(Entry<OccurrenceKey, List<Occurrence>> entryGroupedOccurrence :groupOccurrencesByCode(entry.getValue()).entrySet()) {
				
				SummarizedOccurrence.Builder builder = new SummarizedOccurrence.Builder()
												.setCheckPoint(entryGroupedOccurrence.getKey().getCode())
												.setType(entry.getKey())
												.setIsError(entryGroupedOccurrence.getKey().isError);
										
				
				for(Occurrence occurrence : entryGroupedOccurrence.getValue()){
					builder.addLine(occurrence.getLine());
					builder.addSourceCode(occurrence);
				}
				
				list.add(builder.build());
					
			}
		}
		Collections.sort(list);
		return list;
	}
	
	private Map<OccurrenceKey,List<Occurrence>> groupOccurrencesByCode(List<Occurrence> occurrences) {
		
		Map<OccurrenceKey,List<Occurrence>> map = new HashMap<OccurrenceKey, List<Occurrence>>();
		OccurrenceKey occurrenceKey = null;
		for(Occurrence occurrence : occurrences) {
			occurrenceKey = new OccurrenceKey(occurrence);
			
			if(map.get(occurrenceKey) ==null) {
				map.put(occurrenceKey, new ArrayList<Occurrence>());
				map.get(occurrenceKey).add(occurrence);
			}else {
				map.get(occurrenceKey).add(occurrence);
			}
				
		}
		return map;
	}
	
	
	@EqualsAndHashCode
	private @Getter class OccurrenceKey {
		private String code;
		private boolean isError;
		
		public OccurrenceKey (Occurrence occurrence) {
			this.code = occurrence.getCode();
			this.isError = occurrence.isError();
		}
	}

}

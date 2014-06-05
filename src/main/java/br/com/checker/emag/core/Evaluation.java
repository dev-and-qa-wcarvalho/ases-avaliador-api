package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

import lombok.AccessLevel;
import lombok.Getter;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.RowColumnVector;
import net.htmlparser.jericho.Source;

public abstract class Evaluation {

	@Getter(AccessLevel.PROTECTED) private Source document;
	@Getter(AccessLevel.PROTECTED) private List<Occurrence> occurrences;
	
	protected Evaluation(Source document) {
		this.document = document;
		this.occurrences = new ArrayList<Occurrence>();
	}
	
	protected Occurrence buildOccurrence(String code,boolean error,String tag, 
			Element element,OccurrenceClassification type,
			String criterio) {
		RowColumnVector rcv = this.document.getRowColumnVector(element.getBegin());
		int line = rcv.getRow();
		int column = rcv.getColumn();
		
		return new Occurrence(line, column, code, error, tag,type,criterio);
	}
	
	protected Occurrence buildOccurrence(String code,boolean error,String tag, 
			Element element,OccurrenceClassification type) {
		RowColumnVector rcv = this.document.getRowColumnVector(element.getBegin());
		int line = rcv.getRow();
		int column = rcv.getColumn();
		
		return new Occurrence(line, column, code, error, tag,type);
	}
	
	public abstract OccurrenceClassification type ();
	public abstract List<Occurrence> check();
	
	protected static abstract class EvaluationBuilder {
		protected abstract Evaluation with(Source document);
	}
}

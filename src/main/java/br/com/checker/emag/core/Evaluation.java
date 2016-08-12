package br.com.checker.emag.core;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import lombok.AccessLevel;
import lombok.Getter;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.RowColumnVector;
import net.htmlparser.jericho.Source;

import java.util.ArrayList;
import java.util.List;


public abstract class Evaluation {
  @Getter(AccessLevel.PROTECTED) private Source document;
  @Getter(AccessLevel.PROTECTED) private List<Occurrence> occurrences;
  private String[] html;   
  @Getter(AccessLevel.PROTECTED) private String url;
    
  protected Evaluation(Source document) {
    this.document = document;
    this.occurrences = new ArrayList<Occurrence>();
    this.html = document.toString().split("\n");
  }
    
  protected Evaluation(Source document, String url) {
    this(document);
    this.url = url;
  }

  protected int getRow(Element element) {
    return this.document.getRowColumnVector(element.getBegin()).getRow();
  }
    
  protected Occurrence buildOccurrence(String code, boolean error, String tag,
        Element element, OccurrenceClassification type, String criterio) {
    RowColumnVector rcv = this.document.getRowColumnVector(element.getBegin());
    int line = rcv.getRow();
    int column = rcv.getColumn();
    
    return new Occurrence(line, column, code, error, tag,type,criterio);
  }
    
  protected Occurrence buildOccurrence(String code, boolean error, 
      int line,int column,OccurrenceClassification type,
      String criterio  ) {
    
    
    String tag = "";

    if (line == -1) {
      tag = this.html[line + 1];
    } else {
      tag = line > 0 ? this.html[line - 1] : this.html[line];
    }

    return new Occurrence(line, column, code, error, tag,type,criterio);
  }
    
  protected Occurrence buildOccurrence(String code,boolean error,String tag, 
        Element element,OccurrenceClassification type) {
    RowColumnVector rcv = this.document.getRowColumnVector(element.getBegin());
    int line = rcv.getRow();
    int column = rcv.getColumn();

    return new Occurrence(line, column, code, error, tag,type);
  }
    
  protected Occurrence buildOccurrence(int line, int column, String code, boolean error,
        String tag,
        OccurrenceClassification type,
        String criterio) {
    return new Occurrence( line, column,  code, error, tag, type, criterio);
  }

  public abstract OccurrenceClassification type();

  public abstract List<Occurrence> check();

  protected abstract static class EvaluationBuilder {

    protected abstract Evaluation with(Source document);

    protected abstract Evaluation with(Source document,String url);
  }
}

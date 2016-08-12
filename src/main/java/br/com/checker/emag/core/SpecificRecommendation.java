package br.com.checker.emag.core;

import java.util.List;

import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

public abstract class SpecificRecommendation {
    
    protected Source document ;
    protected String url ;
    
    protected void with(Source document) {this.document = document;}
    
    protected void with(Source document,String url) {
        this.document = document;
        this.url = url;
    }
        
    protected abstract List<Occurrence> check();
    protected abstract OccurrenceClassification type();
    
    protected static abstract class MarkRecommendation extends SpecificRecommendation {
        
        protected MarkEvaluation getEvaluation() { return new MarkEvaluation.MarkEvaluationBuilder().with(document,url);}
        protected OccurrenceClassification type () { return OccurrenceClassification.MARK;}
    }
    
    protected static abstract class FormRecommendation extends SpecificRecommendation {
        
        protected FormEvaluation getEvaluation() { return new FormEvaluation.FormEvaluationBuilder().with(document);}
        protected OccurrenceClassification type () { return OccurrenceClassification.FORM; }
    }
    
    protected static abstract class BehaviorRecommendation extends SpecificRecommendation {
        
        protected BehaviorEvaluation getEvaluation() { return new BehaviorEvaluation.BehaviorEvaluationBuilder().with(document);}
        protected OccurrenceClassification type () { return OccurrenceClassification.BEHAVIOR; }
    }
    
    protected static abstract class MultimediaRecommendation extends SpecificRecommendation {
        
        protected MultimediaEvaluation getEvaluation() { return new MultimediaEvaluation.MultimediaEvaluationBuilder().with(document);}
        protected OccurrenceClassification type () { return OccurrenceClassification.MULTIMEDIA; }
    }
    
    protected static abstract class ContentRecommendation extends SpecificRecommendation {
        
        protected ContentEvaluation getEvaluation() { return new ContentEvaluation.ContentEvaluationBuilder().with(document,url);}
        protected OccurrenceClassification type () { return OccurrenceClassification.CONTENT_INFORMATION; }
    }
    
    protected static abstract class PresentationRecommendation extends SpecificRecommendation {
        
        protected PresentationEvaluation getEvaluation() { return new PresentationEvaluation.PresentationEvaluationBuilder().with(document,url);}
        protected OccurrenceClassification type () { return OccurrenceClassification.PRESENTATION_DESIGN; }
    }
}

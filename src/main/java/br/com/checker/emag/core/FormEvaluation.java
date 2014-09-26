package br.com.checker.emag.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;
import br.com.checker.emag.core.SpecificRecommendation.FormRecommendation;

public class FormEvaluation extends Evaluation{

	private FormEvaluation(Source document) { super(document); }
	
	public static class FormEvaluationBuilder extends EvaluationBuilder {
		
		@Override
		protected FormEvaluation with(Source document) { return new FormEvaluation(document); }
		
		public SpecificRecommendation recommendation38() { return new EvaluationRecommendation38();}
		public SpecificRecommendation recommendation39() { return new EvaluationRecommendation39();}
		public SpecificRecommendation recommendation40() { return new EvaluationRecommendation40();}
		public SpecificRecommendation recommendation41() { return new EvaluationRecommendation41();}
		public SpecificRecommendation recommendation42() { return new EvaluationRecommendation42();}
		public SpecificRecommendation recommendation43() { return new EvaluationRecommendation43();}
		public SpecificRecommendation recommendation44() { return new EvaluationRecommendation44();}
		public SpecificRecommendation recommendation45() { return new EvaluationRecommendation45();}
	}
	
	protected static class EvaluationRecommendation38 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation38();}
	}
	
	protected static class EvaluationRecommendation39 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation39();}
	}
	
	protected static class EvaluationRecommendation40 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation40();}
	}
	
	protected static class EvaluationRecommendation41 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation41();}
	}
	
	protected static class EvaluationRecommendation42 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation42();}
	}
	
	protected static class EvaluationRecommendation43 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation43();}
	}
	
	protected static class EvaluationRecommendation44 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation44();}
	}
	
	protected static class EvaluationRecommendation45 extends FormRecommendation{
		protected List<Occurrence> check() { return getEvaluation().checkRecommendation45();}
	}
	
	public List<Occurrence> check() {
		getOccurrences().clear();
		getOccurrences().addAll(checkRecommendation38());
		getOccurrences().addAll(checkRecommendation39());
		getOccurrences().addAll(checkRecommendation40());
		getOccurrences().addAll(checkRecommendation41());
		getOccurrences().addAll(checkRecommendation42());
		getOccurrences().addAll(checkRecommendation43());
		getOccurrences().addAll(checkRecommendation44());
		getOccurrences().addAll(checkRecommendation45());
		
		return getOccurrences();
	}
	
	
	
	private List<Occurrence> checkRecommendation38() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element input : this.getDocument().getAllElements("input")) {
			Attribute type = input.getAttributes().get("type");
			if (type != null) {
				if (type.getValue().equals("image")) {
					Attribute alt = input.getAttributes().get("alt");
					if (alt == null || alt.getValue().isEmpty()) {
						occurrences.add(this.buildOccurrence("38", true, input.toString(), input, "1"));
					}
				}
				if (type.getValue().equals("submit") || type.getValue().equals("reset") || type.getValue().equals("button")) {
					Attribute value = input.getAttributes().get("value");
					if (value == null || value.getValue().isEmpty()) {
						occurrences.add(this.buildOccurrence("38", true,input.toString(), input, "1"));
					}
				}
			}
		}
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation39() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element form : this.getDocument().getAllElements("form")) {
			for (Element label : form.getAllElements("label")) {
				Attribute attrFor = label.getAttributes().get("for");
				if (attrFor == null || attrFor.getValue().isEmpty()) {
					occurrences.add(this.buildOccurrence("39", true, label.toString(), label, "1"));
				} else {
					boolean temInputEquivalente = false;
					for (Element input : form.getAllElements("input")) {
						Attribute id = input.getAttributes().get("id");
						if (id != null && id.getValue().equals(attrFor.getValue())) {
							temInputEquivalente = true;
							break;
						}
					}
					
					for (Element input : form.getAllElements("textarea")) {
						Attribute id = input.getAttributes().get("id");
						if (id != null && id.getValue().equals(attrFor.getValue())) {
							temInputEquivalente = true;
							break;
						}
					}
					
					if (!temInputEquivalente) {
						occurrences.add(this.buildOccurrence("39", true, label.toString(), label));
					}
				}
			}
			
			for (Element input : form.getAllElements("input")) {
				Attribute type = input.getAttributes().get("type");
				if (type != null) {
					if (type.getValue().equals("submit") ||
						type.getValue().equals("reset") ||
						type.getValue().equals("button") ||
						type.getValue().equals("image") ||
						type.getValue().equals("hidden")) {
						continue;
					}
				}
				Attribute attrId = input.getAttributes().get("id");
				if (attrId == null || attrId.getValue().isEmpty()) {
					System.out.println("TAG COMPLETA: "+input.toString());
					occurrences.add(this.buildOccurrence("39", true,input.toString(), input));
				} else {
					boolean temLabelEquivalente = false;
					for (Element label : form.getAllElements("label")) {
						Attribute attrFor = label.getAttributes().get("for");
						if (attrFor != null && attrFor.getValue().equals(attrId.getValue())) {
							temLabelEquivalente = true;
							break;
						}
					}
					if (!temLabelEquivalente) {
						occurrences.add(this.buildOccurrence("39", true,  input.toString(), input));
					}
				}
			}
			
			for (Element input : form.getAllElements("textarea")) {
				
				Attribute attrId = input.getAttributes().get("id");
				if (attrId == null || attrId.getValue().isEmpty()) {
					System.out.println("TAG COMPLETA: "+input.toString());
					occurrences.add(this.buildOccurrence("39", true, input.toString(), input));
				} else {
					boolean temLabelEquivalente = false;
					for (Element label : form.getAllElements("label")) {
						Attribute attrFor = label.getAttributes().get("for");
						if (attrFor != null && attrFor.getValue().equals(attrId.getValue())) {
							temLabelEquivalente = true;
							break;
						}
					}
					if (!temLabelEquivalente) {
						occurrences.add(this.buildOccurrence("39", true, input.toString(), input));
					}
				}
			}
			
			
			
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation40() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		for(Element input : this.getDocument().getAllElements("input")){
			Attribute t = input.getAttributes().get("tabindex");
			if(t!=null){
				occurrences.add(this.buildOccurrence("40", false, input.toString(), input));
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation41() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		String[] eventos = {"onchange", "onblur","onfocus", "onformchange",
				      		"onforminput","oninput","oninvalid","onreset",
				      		"onselect","onsubmit","onkeydown","onkeypress",
				      		"onkeyup", "onclick","ondblclick","ondrag",
				      		"ondragend","ondragenter","ondragleave","ondragover",
				      		"ondragstart","ondrop","onmousedown","onmousemove",
				      		"onmouseout","onmouseover","onmouseup", 
				      		"onmousewheel","onscrol"};
		
		for (Element elemento : this.getDocument().getAllElements()) {
			if(isSubmitResetOrButton(elemento)) continue;
			
			for(String evento : eventos){
				if (eventExists(elemento,evento)) occurrences.add(this.buildOccurrence("41", false, elemento.toString(), elemento));
			}
		}
		
		return occurrences;
	}
	
	private boolean eventExists(Element elemento, String event) {
		return elemento.getAttributes() != null && elemento.getAttributes().get(event) != null;
	}
	
	private boolean isSubmitResetOrButton(Element elemento){

		Attribute type = elemento.getAttributes().get("type");
		if (type != null) {
			return (type.getValue().equals("submit") ||
					type.getValue().equals("reset") ||
					type.getValue().equals("button"));
		}
		
		return false;
	}
	
	private List<Occurrence> checkRecommendation42() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element form : this.getDocument().getAllElements("form")) {
			
			for (Element input : form.getAllElements("input")) {
				Attribute type = input.getAttributes().get("type");
				if (type != null) {
					if (type.getValue().equals("submit") ||
						type.getValue().equals("reset") ||
						type.getValue().equals("button") ||
						type.getValue().equals("image") ||
						type.getValue().equals("hidden")) {
						continue;
					}
				}
				Attribute attrId = input.getAttributes().get("id");
				if (attrId == null || attrId.getValue().isEmpty()) {
					System.out.println("TAG COMPLETA: "+input.toString());
					occurrences.add(this.buildOccurrence("42", true, input.toString(), input));
				} else {
					boolean temLabelEquivalente = false;
					for (Element label : form.getAllElements("label")) {
						Attribute attrFor = label.getAttributes().get("for");
						if (attrFor != null && attrFor.getValue().equals(attrId.getValue())) {
							temLabelEquivalente = true;
							break;
						}
					}
					if (!temLabelEquivalente) {
						occurrences.add(this.buildOccurrence("42", true, input.toString(), input));
					}
				}
			}
			
			for (Element input : form.getAllElements("textarea")) {
				
				Attribute attrId = input.getAttributes().get("id");
				if (attrId == null || attrId.getValue().isEmpty()) {
					System.out.println("TAG COMPLETA: "+input.toString());
					occurrences.add(this.buildOccurrence("42", true, input.toString(), input));
				} else {
					boolean temLabelEquivalente = false;
					for (Element label : form.getAllElements("label")) {
						Attribute attrFor = label.getAttributes().get("for");
						if (attrFor != null && attrFor.getValue().equals(attrId.getValue())) {
							temLabelEquivalente = true;
							break;
						}
					}
					if (!temLabelEquivalente) {
						occurrences.add(this.buildOccurrence("42", true, input.toString(), input));
					}
				}
			}
		}
		
		return occurrences;
	}
	
	
	private List<Occurrence> checkRecommendation43() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		for (Element form : this.getDocument().getAllElements("form")) {
			occurrences.add(this.buildOccurrence("43", false, form
							.toString(), form));
		}
		
		for (Element script : this.getDocument().getAllElements("script")) {
			if(StringUtils.substringBetween(script.toString(), "alert(", ")") != null){
				occurrences.add(this.buildOccurrence("43", false, script.toString(), script, "4"));
			}	
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation44() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element form : this.getDocument().getAllElements("form")) {
			if (form.getAllElements("fieldset").isEmpty()) {
				occurrences.add(this.buildOccurrence("44", false, form
								.toString(), form, "1"));
			} else {
				for (Element fieldset : form.getAllElements("fieldset")) {
					if (fieldset.getAllElements("legend").isEmpty()) {
						occurrences.add(this.buildOccurrence("44", true,
										fieldset.toString(), fieldset, "1"));
					}
				}
			}
			for (Element select : form.getAllElements("select")) {
				if (select.getAllElements("optgroup").isEmpty()) {
					occurrences.add(this.buildOccurrence("44", false, select
									.toString(), select, "2"));
				} else {
					for (Element optgroup : select.getAllElements("optgroup")) {
						Attribute label = optgroup.getAttributes().get("label");
						if (label == null || label.getValue().equals("")) {
							occurrences.add(this.buildOccurrence("44", true,
											optgroup.toString(), optgroup, "2"));
						}
					}
				}
			}
		}
		
		return occurrences;
	}
	
	private List<Occurrence> checkRecommendation45() {
		List<Occurrence> occurrences = new ArrayList<Occurrence>();
		
		for (Element form : this.getDocument().getAllElements("form"))
			occurrences.add(this.buildOccurrence("45", false, form.toString(), form));
		
		return occurrences;
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element,
			String criterio) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.FORM,criterio);
	}
	
	private Occurrence buildOccurrence(String code, boolean error,
			String tag, Element element) {
		return super.buildOccurrence(code, error, tag, element, OccurrenceClassification.FORM);
	}
	
	public OccurrenceClassification type () { return OccurrenceClassification.FORM;}
}

package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.form;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FormEvaluationTest {

	
	@Test
	public void shouldCheckRecommedation38() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form>");
		html.append("<input type=\"image\" src=\"img_submit.gif\" alt=\"Submit\"></input>");
		html.append("<input type=\"image\" src=\"img_submit.gif\"></input> ");
		html.append("<input type=\"submit\" value=\"\"></input>");
		html.append("<input type=\"reset\" value=\"\"></input>");
		html.append("<input type=\"button\" value=\"\"></input>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation38()).check();
		
		assertEquals("Should return 4 occurrences", 4,occurrences.get(OccurrenceClassification.FORM).size());
		
		for(Occurrence occurrence :occurrences.get(OccurrenceClassification.FORM) ) {
			assertEquals("Should return Recommendation 38","38",occurrence.getCode());
			assertTrue("Recommendation 38 should be ERROR",occurrence.isError());
		}
	}
	
	
	@Test
	public void shouldCheckRecommedation40() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form>");
		html.append("<input type=\"text\" tabindex=\"1\" alt=\"Submit\"></input>");
		html.append("<input type=\"text\" src=\"img_submit.gif\"></input> ");
		html.append("<input type=\"submit\" value=\"\" tabindex=\"2\"></input>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation40()).check();
		
		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.FORM).size());
		
		for(Occurrence occurrence :occurrences.get(OccurrenceClassification.FORM) ) {
			assertEquals("Should return Recommendation 40","40",occurrence.getCode());
			assertFalse("Recommendation 40 should be WARNING",occurrence.isError());
		}
	}
	
	@Test
	public void shouldCheckRecommendation43() {
		StringBuilder html = new StringBuilder("<html><form></form></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation43()).check();
		
		assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.FORM).size());
		assertEquals("Should return Recommendation 43 ","43",occurrences.get(OccurrenceClassification.FORM).get(0).getCode());
		assertFalse("Recommendation 45 should be WARNING",occurrences.get(OccurrenceClassification.FORM).get(0).isError());
		
	}
	
	@Test
	public void shouldCheckRecommedation44() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form></form>");
		html.append("<form>");
		html.append("<fieldset></fieldset>");
		html.append("<fieldset><legend>Desc</legend></fieldset>");
		html.append("<select></select>");
		html.append("<select>");
		html.append("<optgroup label=\"Swedish Cars\">");
		html.append("<option value=\"volvo\">Volvo</option>");
		html.append("<option value=\"saab\">Saab</option>");
		html.append("</optgroup>");
		html.append("<optgroup>");
		html.append("<option value=\"mercedes\">Mercedes</option>");
		html.append("<option value=\"audi\">Audi</option>");
		html.append("</optgroup>");
		html.append("</select>"); 
		html.append("<input type=\"submit\" value=\"\" tabindex=\"2\"></input>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation44()).check();
		
		assertEquals("Should return 4 occurrences", 4,occurrences.get(OccurrenceClassification.FORM).size());
		assertFalse("Recommendation 45 should be WARNING",occurrences.get(OccurrenceClassification.FORM).get(0).isError());
		assertTrue("Recommendation 45 should be ERROR",occurrences.get(OccurrenceClassification.FORM).get(1).isError());
		assertFalse("Recommendation 45 should be WARNING",occurrences.get(OccurrenceClassification.FORM).get(2).isError());
		assertTrue("Recommendation 45 should be ERROR",occurrences.get(OccurrenceClassification.FORM).get(3).isError());
		
		
	}
	
	
	
	@Test
	public void shouldCheckRecommendation45() {
		StringBuilder html = new StringBuilder("<html><form></form></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation45()).check();
		
		assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.FORM).size());
		assertEquals("Should return Recommendation 45 ","45",occurrences.get(OccurrenceClassification.FORM).get(0).getCode());
		assertFalse("Recommendation 45 should be WARNING",occurrences.get(OccurrenceClassification.FORM).get(0).isError());
		
	}
	
	
	@Test
	public void shouldNotCheckRecommendation45() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation45()).check();
		
		assertEquals("Should return 0 occurrence", 0,occurrences.get(OccurrenceClassification.FORM).size());
	}
	
}

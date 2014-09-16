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
	public void shouldCheckRecommendation39(){
		
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form>");
		html.append("<label>teste</label>");
		html.append("<label for=\"inexistentId\">teste</label>");
		html.append("<input type=\"text\" id=\"id\"></input>");
		html.append("<label for=\"existentId\">teste</label>");
		html.append("<input type=\"text\" id=\"existentId\"></input>");
		html.append("<label for=\"existentIdA\">teste</label>");
		html.append("<textarea id=\"existentIdA\"></textarea>");
		html.append("<textarea id=\"inexistentLabel\"></textarea>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation39()).check();
		
		assertEquals("Should return 4 occurrences", 4,occurrences.get(OccurrenceClassification.FORM).size());
		
		for(Occurrence occurrence :occurrences.get(OccurrenceClassification.FORM) ) {
			assertEquals("Should return Recommendation 39","39",occurrence.getCode());
			assertTrue("Recommendation 39 should be ERROR",occurrence.isError());
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
	public void shouldCheckRecommedation41() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form onsubmit=\"test();\">");
		html.append("<input type=\"text\" onchange=\"test();\"></input>");
		html.append("<input type=\"text\" onblur=\"test();\"></input>");
		html.append("<input type=\"text\" onfocus=\"test();\"></input>");
		html.append("<select onselect=\"test();\"><option>1</option></select>");
		html.append("<input type=\"text\" ondblclick=\"test();\"></input>");
		html.append("<input type=\"text\" ondrag=\"test();\"></input>");
		html.append("<input type=\"text\" ondragend=\"test();\"></input>");
		html.append("<input type=\"text\" ondragenter=\"test();\"></input>");
		html.append("<input type=\"text\" ondragleave=\"test();\"></input>");
		html.append("<input type=\"text\" ondragover=\"test();\"></input>");
		html.append("<input type=\"text\" ondragstart=\"test();\"></input>");
		html.append("<input type=\"text\" ondrop=\"test();\"></input>");
		html.append("<input type=\"text\" onmousedown=\"test();\"></input>");
		html.append("<input type=\"text\" onmousemove=\"test();\"></input>");
		html.append("<input type=\"text\" onmouseout=\"test();\"></input>");
		html.append("<input type=\"text\" onmouseover=\"test();\"></input>");
		html.append("<input type=\"text\" onmouseup=\"test();\"></input>");
		html.append("<input type=\"text\" onmousewheel=\"test();\"></input>");
		html.append("<input type=\"text\" onscrol=\"test();\"></input>");
		html.append("<input type=\"text\"></input>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation41()).check();
		
		assertEquals("Should return 20 occurrences", 20,occurrences.get(OccurrenceClassification.FORM).size());
		
		for(Occurrence occurrence :occurrences.get(OccurrenceClassification.FORM) ) {
			assertEquals("Should return Recommendation 41","41",occurrence.getCode());
			assertFalse("Recommendation 41 should be WARNING",occurrence.isError());
		}
		
	}
	
	
	@Test
	public void shouldNotCheckRecommedation41() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form>");
		html.append("<input type=\"button\" onchange=\"test();\"></input>");
		html.append("<input type=\"reset\" onblur=\"test();\"></input>");
		html.append("<input type=\"submit\" onfocus=\"test();\"></input>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation41()).check();
		
		assertEquals("Should return 0 occurrence", 0,occurrences.get(OccurrenceClassification.FORM).size());
		
	}
	
	@Test
	public void shouldCheckRecommendation42(){
		
		StringBuilder html = new StringBuilder("<html>");
		html.append("<form>");
		html.append("<label>teste</label>");
		html.append("<label for=\"inexistentId\">teste</label>");
		html.append("<input type=\"text\" id=\"id\"></input>");
		html.append("<label for=\"existentId\">teste</label>");
		html.append("<input type=\"text\" id=\"existentId\"></input>");
		html.append("<label for=\"existentIdA\">teste</label>");
		html.append("<textarea id=\"existentIdA\"></textarea>");
		html.append("<textarea id=\"inexistentLabel\"></textarea>");
		html.append("</form>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(form().recommendation42()).check();
		
		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.FORM).size());
		
		for(Occurrence occurrence :occurrences.get(OccurrenceClassification.FORM) ) {
			assertEquals("Should return Recommendation 42","42",occurrence.getCode());
			assertTrue("Recommendation 42 should be ERROR",occurrence.isError());
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

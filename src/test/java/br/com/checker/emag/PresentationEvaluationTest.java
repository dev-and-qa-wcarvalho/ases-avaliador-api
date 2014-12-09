package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.content;
import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.presentation;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class PresentationEvaluationTest {
	
	
	@Test
	public void shouldCheckRecomendation28() {
		/*StringBuilder html = new StringBuilder("<html");
		html.append("<body bgcolor=\"#000\">")
			.append(" <table bgcolor=\"#000\">")
			.append("  <tr bgcolor=\"#000\">")
			.append("   <td bgcolor=\"#000\">")
			.append("   <td>")
			.append("  </tr>")
			.append(" </table>")
			.append("</body>")
			.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(presentation().recommendation28()).check();
		
		assertEquals("Should return 4 occurrences",4,occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).size());
		
		for(Occurrence occurrence : occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN)) {
			assertEquals("Should return Recommendation 28 occurrence","28",occurrence.getCode());
			assertTrue("Recomerndation 28 should be Error", occurrence.isError());
		}*/
		
		StringBuilder html = new StringBuilder("<html> ");
		   html.append("<abbr title=\"\"></abbr>");
		   html.append("</html>");

		   Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(presentation().recommendation28()).check();

assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).size());
assertEquals("Should return Recommendation 28","3.12",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).getCode());
assertTrue("Recommendation 26 should be ERROR",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
	
	@Test
	public void shouldCheckRecomendation28WithWarning() {
		StringBuilder html = new StringBuilder("<html> ");
		   html.append("<abbr title=\"\"></abbr>");
		   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(presentation().recommendation28()).check();
		
		assertEquals("Should return 1 occurrences",1,occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).size());
		assertTrue("Recommendation 28 should be Warning",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
	
	@Test
	public void shouldCheckRecomendation29() {
		StringBuilder html = new StringBuilder("<html");
		html.append("<body bgcolor=\"#000\">")
			.append(" <table bgcolor=\"#000\">")
			.append("  <tr bgcolor=\"#000\">")
			.append("   <td bgcolor=\"#000\">")
			.append("   <td>")
			.append("  </tr>")
			.append(" </table>")
			.append("</body>")
			.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(presentation().recommendation29()).check();
		
		assertEquals("Should return 4 occurrences",4,occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).size());
		
		for(Occurrence occurrence : occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN)) {
			assertEquals("Should return Recommendation 29 occurrence","4.1",occurrence.getCode());
			assertTrue("Recomerndation 29 should be Error", occurrence.isError());
		}
		
	}
	
	@Test
	public void shouldCheckRecomendation29WithWarning() {
		StringBuilder html = new StringBuilder("<html");
			html.append("<body>")
			.append(" <table>")
			.append("  <tr>")
			.append("   <td>")
			.append("   <td>")
			.append("  </tr>")
			.append(" </table>")
			.append("</body>")
			.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(presentation().recommendation29()).check();
		
		assertEquals("Should return 1 occurrences",1,occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).size());
		assertFalse("Recommendation 29 should be WARNING",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation30() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(presentation().recommendation30()).check();
		
		assertEquals("Should return Recommendation 30 ","4.2",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).getCode());
		assertFalse("Recommendation 30 should be WARNING",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation31() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(presentation().recommendation31()).check();
		
		assertEquals("Should return Recommendation 31 ","4.3",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).getCode());
		assertFalse("Recommendation 31 should be WARNING",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation32() {
		StringBuilder html = new StringBuilder("<html>");
		
		html.append("<head>");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
		html.append("<title>Style Test</title>");
		html.append("<style type=\"text/css\">");
		html.append("a { color: green; }");
		html.append("a:focus {color: yellow; }");
		html.append("html.append(\"a:hover { color: blue; font-weight: bold; }");
		html.append("a:active { color: red; font-style: italic; }");
		html.append("</style>");
		html.append("</head>");
		html.append("<body>");
		html.append("<a href=\"http://www.google.com\">Go to Google</a>");
		html.append("<a href=\"http://localhost:8080\" style=\"color:blue;margin-left:30px; a:focus{};\">Go to localhost</a>");
		html.append("<a href=\"http://localhost:8080\">Go to localhost</a>");
		html.append("</body>");
		html.append("</html>");
		
		
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(presentation().recommendation32()).check();
		
		assertEquals("Should return Recommendation 32 ","4.4",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).getCode());
		assertFalse("Recommendation 32 should be WARNING",occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN).get(0).isError());
		
	}
}

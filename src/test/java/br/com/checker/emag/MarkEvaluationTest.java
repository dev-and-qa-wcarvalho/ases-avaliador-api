package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.marking;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class MarkEvaluationTest {

	
	@Test
	public void shouldCheckRecommedation1() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(marking().recommendation1()).check();
		
		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.MARK).size());
		assertEquals("Should return Recommendation 1 occurrence","1",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertTrue("Recommendation 1 should be ERROR",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
		
	}
	
	@Test
	public void shouldNotCheckRecommedation1() {
		StringBuilder html = new StringBuilder("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
							.append("<html></html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(marking().recommendation1()).check();
			
			assertEquals("Shoud not return occurrences", 0,occurrences.get(OccurrenceClassification.MARK).size());
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation2() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(marking().recommendation2()).check();
		
		assertEquals("Should return Recommendation 2 occurrence","2",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 2 should be WARNING",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
		assertNull("Recoomendation 2 should not return line",occurrences.get(OccurrenceClassification.MARK).get(0).getLine());
		
	}
	
	@Test 
	public void shouldCheckRecommedation3() {
		StringBuilder html = new StringBuilder("<html>\n")
			.append("<h1>Title1</h1>\n")
			.append("<h1>Title1.2</h1>\n")
			.append("<h2>Title2</h2>\n")
			.append("<h4>Title3</h4>\n")
			.append("<h6>Title6</h6>\n")
			.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(marking().recommendation3()).check();
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 3 occurrence","3",ocorrencia.getCode());
			assertTrue("Occurrences lines should be 3 - h1 , 5- h4 or 6 - h6 ",
							ocorrencia.getLine() == 3 || ocorrencia.getLine() == 5 || ocorrencia.getLine() == 6);
		}
	}
	
	@Test
	public void shouldCheckRecommedation4() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a tabindex=\"1\">link1</a><a tabindex=\"5\">link2</a>\n")
		.append("<input type=\"text\"/>\n")
		.append("<input tabindex=\"2\" type=\"text\"/>\n")
		.append("<select tabindex=\"8\">\n")
		.append("<option>option 1</option>\n")
		.append("</select>\n")
		.append("<textarea tabindex=\"6\"/>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(marking().recommendation4()).check();
		
		assertEquals(5, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 4 occurrence","4",ocorrencia.getCode());
			assertFalse("Recommendation 4 should be WARNING",ocorrencia.isError());
		}
	}
	@Test
	public void shouldCheckTabeIndexRangeRecommendation4() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a tabindex=\"1\">link1</a><a tabindex=\"-5\">link2</a>\n")
		.append("<input type=\"text\"/>\n")
		.append("<input tabindex=\"2\" type=\"text\"/>\n")
		.append("<select tabindex=\"8\">\n")
		.append("<option>option 1</option>\n")
		.append("</select>\n")
		.append("<textarea tabindex=\"32768\"/>\n")
		.append("</html>");
		
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation4()).check();
	
		assertEquals(7, occurrences.get(OccurrenceClassification.MARK).size());
	
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 4 occurrence","4",ocorrencia.getCode());
			assertFalse("Recommendation 4 should be WARNING",ocorrencia.isError());
		}
	}
	
	@Test
	public void shouldCheckNavAndSectionRecommendation4() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<nav></nav>\n")
		.append("<section></section>\n")
		.append("</html>");
		
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation4()).check();
	
		assertEquals(1, occurrences.get(OccurrenceClassification.MARK).size());
	
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 4 occurrence","4",ocorrencia.getCode());
			assertFalse("Recommendation 4 should be WARNING",ocorrencia.isError());
		}
	}
	
	@Test
	public void shouldCheckRecommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a onclick=\"click()\">link1</a> <a onclick=\"click()\" onkeypress=\"press();\">link2</a>\n")
		.append("<a onmousedown=\"mouseDown()\">link3</a> <a onmousedown=\"mouseDown()\" onkeydown=\"keyDown();\">link4</a>\n")
		.append("<a onmouseup=\"mouseUp()\">link5</a> <a onmouseup=\"mouseUp()\" onkeyup=\"keyUp();\">link6</a>\n")
		.append("<a onmouseover=\"over()\">link7</a> <a onmouseover=\"over()\" onfocus=\"focus();\">link8</a>\n")
		.append("<a onmouseout=\"out()\">link9</a> <a onmouseout=\"out()\" onblur=\"blur();\">link10</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 5 occurrence","5",ocorrencia.getCode());
			assertTrue("Recommendation 1 should be ERROR",ocorrencia.isError());
			
		}
	
		assertEquals(5, occurrences.get(OccurrenceClassification.MARK).size());
	}
	
	@Test
	public void shouldCheckRecommedation6() {
		
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a href=\"link1.html\">Link</a>\n")
		.append("<a href=\"link2.html\">Link 2</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation6()).check();
		
		assertEquals("Should return 1 occurrence",1, occurrences.get(OccurrenceClassification.MARK).size());
		assertEquals("Should return Recommendation 6 occurrence","6",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 1 should be WARNING",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
			
	}
	
	@Test
	public void shouldNotReturnRecommendationOccurrence6() {
		
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a href=\"#anchor\">Anchor 1</a>\n")
		.append("<a href=\"#anchor2\">Anchor 2</a>\n")
		.append("<a href=\"link1.html\">Link</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation6()).check();
		
		assertEquals("Should not return occurrences",0, occurrences.get(OccurrenceClassification.MARK).size());
	}
	
	@Test
	public void shouldCheckRecommedation7() {
		
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<table></table>\n")
		.append("<table>")
		.append("<form>")
		.append("</form>\n")
		.append("<table></table>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation7()).check();
		
		assertEquals("Deve retornar 2 ocorrencia",3, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 7 occurrence","7",ocorrencia.getCode());
			assertFalse("Recommendation 1 should be WARNING",ocorrencia.isError());
			
		}
			
	}
	
	@Test
	public void shouldCheckRecommedation8() {
		
		StringBuilder html = new StringBuilder("<!DOCTYPE html>")
		.append("<html>\n")
		.append("<header role=\"banner\"></header>")
		.append("<nav role=\"navigation\"></nav>")
		.append("<nav role=\"navigationFalse\"></nav>")
		.append("<div role=\"main\"></div>")
		.append("<footer role=\"contentinfo\"></footer>")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation8()).check();
		
		assertEquals("Deve retornar 4 ocorrencia",4, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 8 occurrence","8",ocorrencia.getCode());
			assertFalse("Recommendation 8 should be WARNING",ocorrencia.isError());
		}
	}
	
	
	
	@Test
	public void shouldCheckRecommedation9WithError() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<p id=\"menu\">")
		.append("<a href=\"test.html\" target=\"_blank\">New Window</a>")
		.append("<a href=\"test2.html\" target=\"_blank\">New Window 2</a>")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation9()).check();
		
		assertEquals("Should return 2 occurrences",2, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 9 occurrence","9",ocorrencia.getCode());
			assertTrue("Recommendation 8 should be ERROR",ocorrencia.isError());
		}
	}
	
	@Test
	public void shouldCheckRecommedation9ComoWarning() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<p id=\"menu\">")
		.append("<a href=\"teste.html\">teste 1</a>")
		.append("<a href=\"teste2.html\">teste 2</a>")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation9()).check();
		
		assertEquals("Should return 1 occurrence",1, occurrences.get(OccurrenceClassification.MARK).size());
		assertEquals("Should return Recommendation 9 occurrence","9",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 9 should be WARNING to html without target blank links",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
	}
	
	
}

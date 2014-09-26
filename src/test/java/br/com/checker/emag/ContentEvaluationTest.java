package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.content;
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
public class ContentEvaluationTest {
	
	@Test
	public void shouldCheckRecommedation16() {
		
		StringBuilder html = new StringBuilder("<html> ");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation16()).check();
		
		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 16","16",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertTrue("Recommendation 17 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
	}

	
	@Test
	public void shouldCheckRecommedation17() {
		
		StringBuilder html = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation17()).check();
		
		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 17 occurrence","17",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertTrue("Recommendation 16 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
	}
	
	
	@Test
	public void shouldNotCheckRecommedation17() {
		StringBuilder html = new StringBuilder("<html lang=\"en\">");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation17()).check();
		
		assertEquals("Should not return occurrences", 0,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
	}
	
	@Test
	public void shouldCheckNotRecommedation17OnXhtml() {
		StringBuilder html = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\"> ");
		html.append("</html>");
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation17()).check();
		assertEquals("Should not return occurrences", 0,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation18() {
		StringBuilder html = new StringBuilder("<html>");
				html.append("<head>");
				html.append("<title>Title of the document</title>");
				html.append("</head>");
				html.append("<body  lang=\"en\" >");
				html.append("</body>");
				html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation18()).check();
		
		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertFalse("Recommendation 18 should be WARNING",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
		
	}
	
	@Test
	public void shouldCheckRecommedation19() {
							   
		   StringBuilder html = new StringBuilder("<html> ");
				   html.append("<head>");
				   html.append("</head>");
				   html.append("<body>");
				   html.append("<a name=\"test\" ></a>");
				   html.append("<a href=\"#testDif\" ></a>");
				   html.append("</body>");
				   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation19()).check();
		
		assertEquals("Should return 2 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 19","19",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertTrue("Recommendation 19 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
		
	}
	
	@Test
	public void shouldReturn1Recommedation19() {
		
		StringBuilder html = new StringBuilder("<html> ");
								   html.append("<head>");
								   html.append("</head>");
								   html.append("<body>");
								   html.append("<a name=\"test\" ></a>");
								   html.append("<a href=\"#test\" ></a>");
								   html.append("</body>");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation19()).check();
		
		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 19","19",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertTrue("Recommendation 19 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

	}
	
	
	@Test
	public void shouldCheckRecommedation20() {
		
		StringBuilder html = new StringBuilder("<html> ");
								   html.append("<img alt=\"\"></img>");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation20()).check();
		
		assertEquals("Should return 1 occurrences", 2,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 20","20",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertTrue("Recommendation 20 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
	}
	
	
	@Test
	public void shouldCheckRecommedation21() {
		
		StringBuilder html = new StringBuilder("<html> ");
								   html.append("<area alt=\"\"></area>");
								   html.append("<img ismap=\"ismap\"></img>");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation21()).check();
		
		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 21","21",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertEquals("Should return Recommendation 21","21",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).getCode());
		assertTrue("Recommendation 21 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
		assertFalse("Recommendation 21 should be WARNING",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).isError());
	}
	
	@Test
	public void shouldCheckRecommedation22() {
		
		StringBuilder html = new StringBuilder("<html> ");
								   html.append("<a href=\"doc.pdf\"></a>");
								   html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation22()).check();
		
		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertEquals("Should return Recommendation 22","22",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertFalse("Recommendation 22 should be WARNING",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
	}
	
	@Test
	public void shouldCheckRecommedation23() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<head>");
		html.append("<title>Title of the document</title>");
		html.append("</head>");
		html.append("<body>");
		html.append("<map name =\"shape\">");
	    html.append("<area shape = \"rect\" alt=\"parte 1\" coords=\"0, 0, 100, 100\" href=\"parte1.html\"/>");
	    html.append("<area shape = \"circle\" alt=\"parte 2\" coords=\"100, 100, 25\" href=\"parte2.html\"/>");
	    html.append("<area shape = \"poly\" alt=\"parte 3\" coords = \"116, 207, 186, 299, 49, 296\" href=\"parte3.html\"/>");
	    html.append("</map>");
	    html.append("<img src=\"imagem.jpg\" usemap=\"#shape\" />");
    	html.append("</body>");
	    html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation23()).check();
		
		assertEquals("Should return 2 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		assertTrue("Recommendation 23 should be ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
		
	}
	
	@Test
	public void shouldCheckRecomendation24(){
		
		StringBuilder html = new StringBuilder("<html> ");
		   html.append("</html>");

Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
											.with(content().recommendation24()).check();

assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
assertEquals("Should return Recommendation 24","24",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());


	}
	
	
	@Test
	public void shouldAlwaysCheckRecommendation25() {
		StringBuilder html = new StringBuilder(
				"<html><p>"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo"
				+ "</p></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation25()).check();
		
		assertEquals("Should return Recommendation 25 ","25",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).getCode());
		assertFalse("Recommendation 25 should be WARNING",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).isError());
		
	}
	
	@Test
	public void shouldCheckRecommedation26() {
		
		StringBuilder html = new StringBuilder("<html> ");
		   html.append("<table>\n");
		   html.append("<thead>\n");
		   html.append("</thead>\n");
		   html.append("<tbody>\n");
		   html.append("<tr><td id=\"id\" headers=\"headers\" scope=\"scope\"></td></tr>\n");
		   html.append("</tbody\n>");
		   html.append("<tfoot>\n");
		   html.append("</tfoot>\n");
		   html.append("</table>\n");
		   
		   html.append("<table summary=\"sumary\">\n");
		   html.append("<th>\n");
		   html.append("<td></td>\n");
		   html.append("</th>\n");
		   html.append("</table>\n");
		   
		   html.append("</html>\n");

		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
													.with(content().recommendation26()).check();
		
		assertEquals("Should return 1 occurrences", 3,occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
			assertEquals("Should return Recommendation 24 occurrence","24",ocorrencia.getCode());
			assertTrue("Recommendation 24 should be ERROR",ocorrencia.isError());
		}
	
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation27() {
		StringBuilder html = new StringBuilder("<html></html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(content().recommendation27()).check();
		
		assertEquals("Should return Recommendation 27 ","27",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
		assertFalse("Recommendation 27 should be WARNING",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
		
	}
	
}

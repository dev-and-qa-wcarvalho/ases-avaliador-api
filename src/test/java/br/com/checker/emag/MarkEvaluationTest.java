package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.marking;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class MarkEvaluationTest {

	
	@Test
	@Ignore
	public void shouldCheckRecommedation1() {
		StringBuilder html = new StringBuilder("<html>/n")
		.append("<head>")
		.append("<style>")
		.append("body {background-color:lightgrey}")
		.append("h1   {color:blue}")
		.append("p    {color:green}")
		.append("</style>")
		.append("<script>")
		.append("alert(\"javascript\");")
		.append("</script>")
		.append("</head>")
		.append("<BODY>")
		.append("<p style=\"color:green;margin-left:20px;\">Css Inline.</p>")
		.append("<a href=\"javascript:void(0)\" onclick=\"fn()\">fn is called</a>")
		.append("<a href=\"javascript:\" onclick=\"fn()\">fn is called too!</a>")
		.append("</BODY>")
		.append("</HTML>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(marking().recommendation1()).check();
		
		assertEquals("Should return 5 occurrences", 5,occurrences.get(OccurrenceClassification.MARK).size());
		assertEquals("Should return Recommendation 5 occurrence","1.1",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 1 should be ERROR",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
		
	}
	
	@Test
	@Ignore
	public void shouldNotCheckRecommedation1() {
		StringBuilder html = new StringBuilder("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
							.append("<html></html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(marking().recommendation1()).check();
			
			assertEquals("Shoud not return occurrences", 0,occurrences.get(OccurrenceClassification.MARK).size());
	}
	
	@Test
	public void shouldAlwaysCheckRecommendation2() {
		StringBuilder html = new StringBuilder("<html>")
		.append("<head>")
		.append("</head>")
		.append("<BODY>")
		.append("<h1></h1>")
		.append("<p style=\"color:green;margin-left:20px;\"></p>")
		.append("<a ></a>")
		.append("<a ></a>")
		.append("</BODY>")
		.append("</HTML>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(marking().recommendation2()).check();
		
		assertEquals("Should return Recommendation 2 occurrence","1.2",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 2 should be WARNING",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
		assertNotNull("Recoomendation 2 should not return line",occurrences.get(OccurrenceClassification.MARK).get(0).getLine());
		
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
			assertEquals("Should return Recommendation 4 occurrence","1.3",ocorrencia.getCode());
			assertTrue("Occurrences lines should be 3 - h1 , 2 - h1, 5- h4 or 6 - h6 ",
							ocorrencia.getLine() == 3 || ocorrencia.getLine() == 2 || ocorrencia.getLine() == 5 || ocorrencia.getLine() == 6);
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
			assertEquals("Should return Recommendation 4 occurrence","1.4",ocorrencia.getCode());
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
			assertEquals("Should return Recommendation 4 occurrence","1.4",ocorrencia.getCode());
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
			assertEquals("Should return Recommendation 4 occurrence","1.4",ocorrencia.getCode());
			assertFalse("Recommendation 4 should be WARNING",ocorrencia.isError());
		}
	}
	
	
	@Test
	public void shouldCheckCriterio1Recommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a href=\"a\">link1</a>\n")
		.append("<a href=\"a\">link3</a>\n")
		.append("<a href=\"#b\">link5</a>\n")
		.append("<a href=\"#c\">link5</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		
		int criterio1 = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
			assertTrue("Recommendation 1 should be ERROR",ocorrencia.isError());
			if(ocorrencia.getCriterio().equals("1"))
				criterio1++;
			
		}
		
		assertEquals("Should return 2 occurrence with criteitio 1",2,criterio1);
	
	}
	
	@Test
	public void shouldCheckCriterio2Recommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a href=\"#anchor\">link1</a>\n")
		.append("<a href=\"#anchor1\">link5</a>\n")
		.append("<a name=\"anchor\">anchor</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		
		int criterio2 = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
			assertTrue("Recommendation 1 should be ERROR",ocorrencia.isError());
			if(ocorrencia.getCriterio().equals("2"))
				criterio2++;
		}
		
		assertEquals("Should return 1 occurrence with criteitio 1",1,criterio2);
	
	}
	
	@Test
	public void shouldCheckCriterio3Recommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		
		.append("<a>link</a>\n")
		.append("<area>area</area>\n")
		.append("<button>button</button>\n")
		.append("<input></input>\n")
		.append("<label>label</a>\n")
		.append("<fieldset><legend>legend</legend></fieldset>\n")
		.append("<textarea></textarea>\n")
		
		.append("<a acesskey=\"acess0\">link</a>\n")
		.append("<area acesskey=\"acess1\">area</area>\n")
		.append("<button acesskey=\"acess2\">button</button>\n")
		.append("<input acesskey=\"acess3\"></input>\n")
		.append("<label acesskey=\"acess4\">label</a>\n")
		.append("<fieldset><legend acesskey=\"acess5\">legend</legend></fieldset>\n")
		.append("<textarea acesskey=\"acess6\"></textarea>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		
		int criterio3 = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
			assertTrue("Recommendation 1 should be ERROR",ocorrencia.isError());
			if(ocorrencia.getCriterio().equals("3"))
				criterio3++;
		}
		
		assertEquals("Should return 7 occurrence with criteitio 3",7,criterio3);
	
	}
	
	@Test
	public void shouldCheckCriterio4Recommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<base href=\"http://www.governoeletronico.gov.br/\" />\n")
		.append("<a href=\"#anchor1\">link5</a>\n")
		.append("<a name=\"anchor\">anchor</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		
		int criterio4 = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
			assertFalse("Recommendation 1 should be WARNING",ocorrencia.isError());
			if(ocorrencia.getCriterio().equals("4"))
				criterio4++;
		}
		
		assertEquals("Should return 1 occurrence with criteitio 1",1,criterio4);
	
	}
	
	@Test
	public void shouldCheckCriterio5Recommedation5() {
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a acesskey=\"a\">link1</a>\n")
		.append("<a acesskey=\"a\">link3</a>\n")
		.append("<a acesskey=\"b\">link5</a>\n")
		.append("<a acesskey=\"c\">link7</a>\n")
		.append("<a acesskey=\"d\">link9</a>\n")
		.append("<a acesskey=\"e\">link9</a>\n")
		.append("<a acesskey=\"e\">link9</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation5()).check();
		
		
		int criterio5 = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 1.5","1.5",ocorrencia.getCode());
			
			if(ocorrencia.getCriterio().equals("5")) {
				assertFalse("Recommendation 1 should be WARNING",ocorrencia.isError());
				criterio5++;
			}
		}
		
		assertEquals("Should return 4 occurrence with criteitio 5",4,criterio5);
	}
	
	@Test
	public void shouldCheckRecommedation6() {
		
		StringBuilder html = new StringBuilder("<html>\n")
		
		.append("<table>")
		.append("<tr>")
		.append("<td>")
		.append("<form></form>")
		.append("</td>")
		.append("</tr>")
		.append(".append(\"</table>")
		
		.append(".append(\"</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation6()).check();
		
		assertEquals("Should return 2 occurrence",2, occurrences.get(OccurrenceClassification.MARK).size());
		assertEquals("Should return Recommendation 6 occurrence","1.6",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertEquals("Should return Recommendation 6 occurrence","1.6",occurrences.get(OccurrenceClassification.MARK).get(1).getCode());
		assertFalse("Recommendation 1 should be WARNING",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
		assertTrue("Recommendation 1 should be ERROR",occurrences.get(OccurrenceClassification.MARK).get(1).isError());
			
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
		.append("<a href>Link 1</a>")
		.append("<a href>Link 2</a>")
		.append("<a href>Link 3</a>")
		.append("<a href>Link 4</a>")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation7()).check();
		
		assertEquals("Deve retornar 2 ocorrencia",3, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 7 occurrence","1.7",ocorrencia.getCode());
			assertTrue("Recommendation 1 should be WARNING",ocorrencia.isError());
			
		}
			
	}
	
	@Test
	public void shouldCheckRecommedationWithSecondCriterio8() {
		
		StringBuilder html = new StringBuilder("<!DOCTYPE html>")
		.append("<html>\n")
		.append("<HEADER></HEADER>\n")
		.append("<NAV></NAV>\n")
		.append("<SECTION></SECTION>\n")
		.append("<FOOTER></FOOTER>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation8()).check();
		
		assertEquals("Deve retornar 3 ocorrencia",3, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 1.8 occurrence","1.8",ocorrencia.getCode());
			assertFalse("Recommendation 8 should be WARNING",ocorrencia.isError());
			assertEquals("Should return Criterio 2","2",ocorrencia.getCriterio());
		}
		
	
	}
	
	
	@Test
	public void shouldCheckRecommedationWithFirstCriterio8() {
		
		StringBuilder html = new StringBuilder("<!DOCTYPE html>")
		.append("<html>\n")
		.append("<div role=\"banner\"></div>\n")
		.append("<div role=\"navigation\"></div>\n")
		.append("<div role=\"main\"></div>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(marking().recommendation8()).check();
		
		assertEquals("Deve retornar 4 ocorrencia",4, occurrences.get(OccurrenceClassification.MARK).size());
		
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
			assertEquals("Should return Recommendation 1.8 occurrence","1.8",ocorrencia.getCode());
			assertFalse("Recommendation 8 should be WARNING",ocorrencia.isError());
			assertEquals("Should return Criterio 1","1",ocorrencia.getCriterio());
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
			assertEquals("Should return Recommendation 9 occurrence","1.9",ocorrencia.getCode());
			assertFalse("Recommendation 8 should be WARNING",ocorrencia.isError());
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
		assertEquals("Should return Recommendation 9 occurrence","1.9",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
		assertFalse("Recommendation 9 should be WARNING to html without target blank links",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
	}
	
	
}

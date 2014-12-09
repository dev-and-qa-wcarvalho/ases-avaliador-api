package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.behavior;
import static br.com.checker.emag.core.Checker.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BehaviorEvaluationTest {

	
	@Test
	public void shouldCheckRecommedation10() {
		
		StringBuilder html = new StringBuilder("<html>\n")
		.append("<a onclick=\"click()\">link1</a> <a onclick=\"click()\" onkeypress=\"press();\">link2</a>\n")
		.append("<a onmousedown=\"mouseDown()\">link3</a> <a onmousedown=\"mouseDown()\" onkeydown=\"keyDown();\">link4</a>\n")
		.append("<a onmouseup=\"mouseUp()\">link5</a> <a onmouseup=\"mouseUp()\" onkeyup=\"keyUp();\">link6</a>\n")
		.append("<a onmouseover=\"over()\">link7</a> <a onmouseover=\"over()\" onfocus=\"focus();\">link8</a>\n")
		.append("<a onmouseout=\"out()\">link9</a> <a onmouseout=\"out()\" onblur=\"blur();\">link10</a>\n")
		.append("<a dbclick=\"dbclick()\">link10</a>\n")
		.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				.with(behavior().recommendation10()).check();
		
		int error = 0;
		int warning = 0;
		for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.BEHAVIOR)) {
			assertEquals("Should return Recommendation 10 occurrence","2.1",ocorrencia.getCode());
			if(ocorrencia.isError())
				error++;
			else
				warning++;
		}
		
		
		
		assertEquals(6, occurrences.get(OccurrenceClassification.BEHAVIOR).size());
		assertEquals(1, warning);
		assertEquals(5, error);
		
	}
	
	@Test
	public void shouldNotCheckRecommedation10() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<script></script><noscript></noscript>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation10()).check();
			
			assertEquals("Shoud not return occurrences", 0,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
	}
	
	@Test
	public void shouldCheckRecommedation11WithWarning() {
		StringBuilder html = new StringBuilder("<html>")
							.append("<head>")
							.append("<object data=\"audi.jpeg\"></object>")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation11()).check();
			
			assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertTrue("Recommendation 11 should be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
			
	}
	
	@Test
	public void shouldCheckRecommedation11WithError() {
		StringBuilder html = new StringBuilder("<html>")
							.append("<head>")
							.append("<script>")
							.append("alert(\"javascript\");")
							.append("</script>")
							.append("</head>")
							.append("</html>");
		
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation11()).check();
			
			assertTrue("Recommendation 11 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	
	@Test
	public void shouldCheckRecommedation12WithError() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<meta http-equiv=\"refresh\" content=\"20; url='http://www.exemplo.com/'\" />")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation12()).check();
			
			assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertFalse("Recommendation 12 should be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
			
	}
	
	@Test
	public void shouldCheckRecommedation12WithWarning() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<title>teste</title>")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation12()).check();
			
			assertFalse("Recommendation 12 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	@Test
	public void shouldCheckRecommedation13() {
		StringBuilder html = new StringBuilder("<meta http-equiv=\"refresh\" content=\"5; url=teste.html\">\n")
							.append("<html>\n")
							.append("<head>\n")
							.append("<title>teste</title>\n")
							.append("</head>\n")
							.append("</html>");
		
		
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation13()).check();
			assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertTrue("Recommendation 13 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	@Test
	public void shouldCheckRecommedation14WithError() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<blink></blink>")
							.append("<marquee></marquee>")
							.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(behavior().recommendation14()).check();

		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
		assertTrue("Recommendation 14 should be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	@Test
	public void shouldCheckRecommedation14WithWarning() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					.with(behavior().recommendation14()).check();

		assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
		assertFalse("Recommendation 14 should be WARNING", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	
	@Test
	public void shouldCheckRecommedation15() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<title>teste</title>")
							.append("<blink>teste</blink>")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation15()).check();
			assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertTrue("Recommendation 15 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	
	
}

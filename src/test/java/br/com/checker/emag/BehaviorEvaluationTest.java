package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.behavior;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class BehaviorEvaluationTest {

	
	@Test
	public void shouldCheckRecommedation10() {
		StringBuilder html = new StringBuilder("<html>");
		html.append("<script>alert(\"teste\");</script>");
		html.append("<object></object>");
		html.append("</html>");
		
		Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
				  													.with(behavior().recommendation10()).check();
		
		assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
		assertEquals("Should return Recommendation 10","10",occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).getCode());
		assertTrue("Recommendation 10 should be ERROR",occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
		assertTrue("Recommendation 10 should be ERROR",occurrences.get(OccurrenceClassification.BEHAVIOR).get(1).isError());
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
	public void shouldCheckRecommedation11WithError() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<meta http-equiv=\"refresh\" content=\"20; url='http://www.exemplo.com/'\" />")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation11()).check();
			
			assertEquals("Should return 1 occurrences", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertTrue("Recommendation 11 should be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
			
	}
	
	@Test
	public void shouldCheckRecommedation11WithWarning() {
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<title>teste</title>")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation11()).check();
			
			assertFalse("Recommendation 11 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
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
			assertTrue("Recommendation 12 should be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
			
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
		StringBuilder html = new StringBuilder("<<html>")
							.append("<head>")
							.append("<title>teste</title>")
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation13()).check();
			assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertFalse("Recommendation 13 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
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
							.append("</head>")
							.append("</html>");
		
			Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
					  													.with(behavior().recommendation15()).check();
			assertEquals("Should return 1 occurrence", 1,occurrences.get(OccurrenceClassification.BEHAVIOR).size());
			assertFalse("Recommendation 15 should not be ERROR", occurrences.get(OccurrenceClassification.BEHAVIOR).get(0).isError());
	}
	
	
	
}

package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.content;
import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.multimedia;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class MultimediaEvaluationTest {

    
    @Test
    public void shouldCheckRecommedation33() {
      StringBuilder html = new StringBuilder("<html>");
      html.append("<head>");
      html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
      html.append("<title>Style Test</title>");
      html.append("</head>");
      html.append("<body>");
      html.append("<embed autoplay=\"false\" src=\"VIDEO.mp4\" width=\"50\" height=\"50></embed>");
      html.append("<embed SRC=\"mymovie.mov\" WIDTH=\"200\" HEIGHT=\"100\" AUTOPLAY=\"TRUE\" LOOP=\"true\"></embed>");
      html.append("<embed width=\"400\" height=\"400\" data=\"helloworld.swf\"></embed>");
      html.append("<object width=\"400\" height=\"400\" data=\"helloworld.mp4\"></object>");
      html.append("<video id=\"video\" width=\"320\" height=\"240\" controls>");
      html.append(" <source src=\"exemplo.mp4\" type=\"video/mp4\">");
      html.append("<source src=\"exemplo.ogg\" type=\"video/ogg\">");
      html.append("</video>");
      html.append("</body>");
      html.append("</html>");
      
      Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                      .with(multimedia().recommendation33()).check();
      
      assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.MULTIMEDIA).size());
      
      for(Occurrence occurrence :occurrences.get(OccurrenceClassification.MULTIMEDIA) ) {
          assertEquals("Should return Recommendation 33","5.1",occurrence.getCode());
          assertFalse("Recommendation 33 should be WARNING",occurrence.isError());
      }
    }
    
    @Test
    public void shouldCheckRecommedation34() {
      StringBuilder html = new StringBuilder("<html>");
      html.append("<object></object>");
      html.append("<object data=\"teste.mp3\"></object>");
      html.append("<object data=\"teste.wma\"></object>");
      html.append("<object data=\"teste.wav\"></object>");
      html.append("<embed src=\"helloworld.swf\">");
      html.append("<embed>");
      html.append("<embed src=\"\">");
      
      
      html.append("</html>");
      
      Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                      .with(multimedia().recommendation34()).check();
      
      assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.MULTIMEDIA).size());
      
      for(Occurrence occurrence :occurrences.get(OccurrenceClassification.MULTIMEDIA) ) {
          assertEquals("Should return Recommendation 34","5.2",occurrence.getCode());
          assertFalse("Recommendation 34 should be WARNING",occurrence.isError());
      }
    }
    
    @Test
    public void shouldCheckRecommedation35() {
      StringBuilder html = new StringBuilder("<html>");
      html.append("<embed></embed>");
      html.append("<embed></embed>");
      html.append("<object></object>");
      html.append("<video></video>");
      html.append("<video>Teste</video>");
      html.append("<object value=\"teste.mp4\" src=\"\"></object>");
      html.append("<object value=\"teste.avi\"></object>");
      html.append("<object value=\"teste.flv\"></object>");
      html.append("<object value=\"teste.rmvb\"></object>");
      html.append("</html>");
      
      Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                      .with(multimedia().recommendation35()).check();
      
      assertEquals("Should return 2 occurrences", 2,occurrences.get(OccurrenceClassification.MULTIMEDIA).size());
      
      for(Occurrence occurrence :occurrences.get(OccurrenceClassification.MULTIMEDIA) ) {
          assertEquals("Should return Recommendation 35","5.3",occurrence.getCode());
          assertFalse("Recommendation 35 should be WARNING",occurrence.isError());
      }
    }
    
    @Test
    public void shouldAlwaysCheckRecommendation36() {
      StringBuilder html = new StringBuilder("<html>");
             html.append("<embed id=\"video1\" hidden=\"true\" src=\"Wildlife.wmv\" type=\"audio/mpeg\" autoplay=\"true\" width=\"470\" height=\"280\"></embed>");
             html.append("<object type=\"audio/x-mpeg\" data=\"fileName.mp3\" width=\"200\" height=\"16\" autoplay=\"false\">");
             html.append("<param name=\"src\" value=\"fileName.mp3\" />");
             html.append("<param name=\"controller\" value=\"true\" />"); 
             html.append("<param name=\"autoplay\" value=\"false\" />");
             html.append("<param name=\"autostart\" value=\"0\" />");
             html.append("</object>");
             
             html.append("<audio controls>");
             html.append("<source src=\"horse.ogg\" type=\"audio/ogg\">");
             html.append("</audio>");
             
             
              
             html.append("</html>");
      
      Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                      .with(multimedia().recommendation36()).check();
      assertEquals("Should return 2 occurrence", 2,occurrences.get(OccurrenceClassification.MULTIMEDIA).size());
      assertEquals("Should return Recommendation 36 ","5.4",occurrences.get(OccurrenceClassification.MULTIMEDIA).get(0).getCode());
      assertFalse("Recommendation 36 should be WARNING",occurrences.get(OccurrenceClassification.MULTIMEDIA).get(0).isError());
      
    }
    
    @Test
    public void shouldCheckRecommedation37() {
      StringBuilder html = new StringBuilder("<html>");
      html.append("<object width=\"400\" height=\"50\" data=\"bookmark.swf\"></object> ");
      html.append("</html>");
      
      Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                      .with(multimedia().recommendation37()).check();
      
      assertEquals("Should return 1 occurrences 1", 1,occurrences.get(OccurrenceClassification.MULTIMEDIA).size());
      assertEquals("Should return Recommendation 37","5.5",occurrences.get(OccurrenceClassification.MULTIMEDIA).get(0).getCode());
      assertFalse("Recommendation 37 should be WARNING",occurrences.get(OccurrenceClassification.MULTIMEDIA).get(0).isError());
    }
    
    
}

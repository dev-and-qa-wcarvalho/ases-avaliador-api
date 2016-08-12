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
        .append("</html>");
        
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
        String a = "  ";
        StringBuilder html = new StringBuilder("<html>")
        .append("<img alt=\"\"/>")
        .append("<p>")
        .append("<img alt=\"\"/>")
        .append("</p>")
        .append("<p>")
        .append("<img alt=\"teste\"/>")
        .append("</p>")
        .append("<h1>")
        .append("<img alt=\"\"/>")
        .append("</h1>")
        .append("<a href=\"teste\">")
        .append("<img alt=\"\"/>")
        .append("</a>")
        .append("<h2>")
        .append("<img alt=\"\"/>")
        .append("</h2>")
        
        /*.append("<head>")
        .append("</head>")
        .append("<BODY>")
        .append("<h1></h1>")
        .append("<p style=\"color:green;margin-left:20px;\"></p>")
        .append("<a>   </a>")
        .append("<a>"+a+"</a>")
        .append("</BODY>")*/
        .append("</HTML>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation2()).check();
        
        assertEquals("Should return Recommendation 1.2 occurrence","1.2",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
        
        int warnings=0,erros =0;
        
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.2 occurrence","1.2",ocorrencia.getCode());
            if(ocorrencia.isError())
                erros++;
            else
                warnings++;
        }
        
        assertEquals("Should return 1 erros",1,erros);
        assertEquals("Should return 1 warning",1,warnings);
        
    }
    
    @Test 
    public void shouldCheckCriterio4Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h1>Title1.2</h1>\n")
            .append("<h1>Title1.3</h1>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio4=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.3 occurrence","1.3",ocorrencia.getCode());
            if("4".equals(ocorrencia.getCriterio())){
                criterio4++;
                assertTrue("Recommendation 1.3 criterio 4 should be ERROR",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 2 occurrence with criteitio 2",2,criterio4);
    }
    
    @Test 
    public void shouldCheckCriterio3Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h1>Title1.2</h1>\n")
            .append("<h1>Title1.3</h1>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio3=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.3 occurrence","1.3",ocorrencia.getCode());
            if("3".equals(ocorrencia.getCriterio())){
                criterio3++;
                assertFalse("Recommendation 1.3 criterio 3 should be WARNING",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 1 occurrence with criteitio 3",1,criterio3);
    }
    
    
    @Test 
    public void shouldNotCheckCriterio3Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h2>Title1.2</h2>\n")
            .append("<h3>Title1.3</h3>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h2>Title1.2</h2>\n")
            .append("<h3>Title1.3</h3>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio3=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            if("3".equals(ocorrencia.getCriterio())) 
                criterio3++;
        }
        
        assertEquals("Should not return occurrences with criteitio 3",0,criterio3);
    }
    
    @Test 
    public void shouldCheckCriterio2Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h3>Title1.2</h3>\n")
            .append("<h4>Title1.3</h4>\n")
            .append("<h1>Title1.3</h1>\n")
            .append("<h4>Title1.3</h4>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio2=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.3 occurrence","1.3",ocorrencia.getCode());
            if("2".equals(ocorrencia.getCriterio())){
                criterio2++;
                assertTrue("Recommendation 1.3 criterio 2 should be ERROR",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 2 occurrence with criteitio 2",2,criterio2);
    }
    
    @Test 
    public void shouldChecNotkCriterio2Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("<h2>Title1.2</h2>\n")
            .append("<h3>Title1.3</h3>\n")
            .append("<h4>Title1.4</h4>\n")
            .append("<h5>Title1.5</h5>\n")
            .append("<h6>Title1.6</h6>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio2=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.3 occurrence","1.3",ocorrencia.getCode());
            if("2".equals(ocorrencia.getCriterio())){
                criterio2++;
            }
        }
        
        assertEquals("Should return no occurrences with criteitio 2",0,criterio2);
    }
    
    @Test 
    public void shouldChecNotkCriterio1Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("<h1>Title1</h1>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio1=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            if("1".equals(ocorrencia.getCriterio())){
                criterio1++;
            }
        }
        
        assertEquals("Should return no occurrences with criteitio 1",0,criterio1);
    }
    
    @Test 
    public void shouldCheckCriterio1Recommedation3() {
        StringBuilder html = new StringBuilder("<html>\n")
            .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                                                                    .with(marking().recommendation3()).check();
        
        int criterio1=0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.3 occurrence","1.3",ocorrencia.getCode());
            if("1".equals(ocorrencia.getCriterio())){
                criterio1++;
                assertTrue("Recommendation 1.3 criterio 1 should be ERROR",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 1 occurrence with criteitio 2",1,criterio1);
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
        StringBuilder html = new StringBuilder("<html>")
        .append("<a href=\"a\">link1</a>")
        .append("<a href=\"a\">link3</a>")
        .append("<a href=\"#b\">link5</a>")
        .append("<a href=\"#c\">link5</a>")
        .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                .with(marking().recommendation5()).check();
        
        
        int criterio1 = 0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
            
            if(ocorrencia.getCriterio().equals("1")) {
                assertTrue("Recommendation 1.5 should be ERROR",ocorrencia.isError());
                criterio1++;
            }
                
            
        }
        
        assertEquals("Should not return occurrence with criteitio 1",0,criterio1);
    
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
            
            if(ocorrencia.getCriterio().equals("3"))
                assertTrue("Recommendation 5 criteitio 3 should be ERROR",ocorrencia.isError());
            
            if(ocorrencia.getCriterio().equals("4"))
                assertFalse("Recommendation 5 criteitio 4 not ERROR",ocorrencia.isError());
            
            if(ocorrencia.getCriterio().equals("2")){
                assertTrue("Recommendation 5 criteitio 2 should be ERROR",ocorrencia.isError());
                criterio2++;
            }   
        }
        
        assertEquals("Should return 1 occurrence with criteitio 2",1,criterio2);
    
    }
    
    @Test
    public void shouldCheckCriterio3Recommedation5() {
        StringBuilder html = new StringBuilder("<html>")
        
        .append("<a>link</a>")
        .append("<area>area</area>")
        .append("<button>button</button>")
        .append("<input></input>")
        .append("<label>label</a>")
        .append("<fieldset><legend>legend</legend></fieldset>")
        .append("<textarea></textarea>")
        
        /*.append("<a accesskey=\"acess0\">link</a>")
        .append("<area accesskey=\"acess1\">area</area>")
        .append("<button accesskey=\"acess2\">button</button>")
        .append("<input accesskey=\"acess3\"></input>")
        .append("<label accesskey=\"acess4\">label</a>")
        .append("<fieldset><legend accesskey=\"acess5\">legend</legend></fieldset>")
        .append("<textarea accesskey=\"acess6\"></textarea>")*/
        .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                .with(marking().recommendation5()).check();
        
        
        int criterio3 = 0;
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 5 occurrence","1.5",ocorrencia.getCode());
            
            if(ocorrencia.getCriterio().equals("3")){
                criterio3++;
                assertTrue("Recommendation 1.5 should be ERROR",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 1 occurrence with criteitio 1",1,criterio3);
    
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
            if(ocorrencia.getCriterio().equals("4")){
                criterio4++;
                assertFalse("Recommendation 1 should be WARNING",ocorrencia.isError());
            }
        }
        
        assertEquals("Should return 1 occurrence with criteitio 1",1,criterio4);
    
    }
    
    @Test
    public void shouldCheckCriterio5Recommedation5() {
        StringBuilder html = new StringBuilder("<html>\n")
        .append("<a accesskey=\"a\">link1</a>\n")
        .append("<a accesskey=\"a\">link3</a>\n")
        .append("<a accesskey=\"b\">link5</a>\n")
        .append("<a accesskey=\"c\">link7</a>\n")
        .append("<a accesskey=\"d\">link9</a>\n")
        .append("<a accesskey=\"e\">link9</a>\n")
        .append("<a accesskey=\"e\">link9</a>\n")
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
        .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                .with(marking().recommendation8()).check();
        
        assertEquals("Deve retornar 3 ocorrencia",3, occurrences.get(OccurrenceClassification.MARK).size());
        
        for(Occurrence ocorrencia : occurrences.get(OccurrenceClassification.MARK)) {
            assertEquals("Should return Recommendation 1.8 occurrence","1.8",ocorrencia.getCode());
            assertFalse("Recommendation 8 should be WARNING",ocorrencia.isError());
            //assertEquals("Should return Criterio 2","2",ocorrencia.getCriterio());
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
            //assertEquals("Should return Criterio 1","1",ocorrencia.getCriterio());
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
        .append("<a href=\"teste2.html\" target=\"_blank\">teste 2</a>")
        .append("</html>");
        
        Map<OccurrenceClassification,List<Occurrence>> occurrences = from(html.toString())
                .with(marking().recommendation9()).check();
        
        assertEquals("Should return 1 occurrence",1, occurrences.get(OccurrenceClassification.MARK).size());
        assertEquals("Should return Recommendation 9 occurrence","1.9",occurrences.get(OccurrenceClassification.MARK).get(0).getCode());
        assertFalse("Recommendation 9 should be WARNING to html without target blank links",occurrences.get(OccurrenceClassification.MARK).get(0).isError());
    }
    
    
}

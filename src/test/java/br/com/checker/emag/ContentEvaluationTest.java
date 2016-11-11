package br.com.checker.emag;

import static br.com.checker.emag.core.Checker.content;
import static br.com.checker.emag.core.Checker.from;
import static br.com.checker.emag.core.Checker.presentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ContentEvaluationTest {

    @Test
    public void shouldCheckRecommedation17() {

        StringBuilder html = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation17()).check();

        assertEquals("Should return 3 occurrences", 3,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 17 occurrence", "3.1",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 16 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldNotCheckRecommedation17() {
        StringBuilder html = new StringBuilder(
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation17()).check();

        assertEquals("Should not return occurrences", 0,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
    }

    @Test
    public void shouldCheckNotRecommedation17OnXhtml() {
        StringBuilder html = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\"> ");
        html.append("</html>");
        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation17()).check();
        assertEquals("Should return 2 occurrences 2 ", 2,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
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

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation18()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertFalse("Recommendation 18 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

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

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation19()).check();

        assertEquals("Should return 2 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 19", "3.3",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 19 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

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

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation19()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 19", "3.3",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 19 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

    }

    @Test
    public void shouldCheckRecommedation20ForImgElementWhenAltIsEmpty() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<img alt=\"\"></img>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 20", "3.4",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertFalse("Recommendation 20 should not be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation20ForImgElementWhenAltNotExists() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<img></img>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 20", "3.4",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertFalse("Recommendation 20 should not be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation20ForImgElementWhenAltExists() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<img alt=\"testando\" />");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 0 occurrences", 0,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
    }

    @Test
    public void shouldCheckRecommedation20ForAreaElementWhenAltIsEmpty() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<area shape=\"rect\" coords=\"0,0,82,126\" href=\"sun.htm\" alt=\"\">");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 20", "3.4",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertFalse("Recommendation 20 should not be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation20ForAreaElementWhenAltNotExists() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<area shape=\"rect\" coords=\"0,0,82,126\" href=\"sun.htm\">");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 20", "3.4",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertFalse("Recommendation 20 should not be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation20ForAreaElementWhenAltExists() {
        StringBuilder html = new StringBuilder("<html> ");
        html.append("<area shape=\"rect\" coords=\"0,0,82,126\" href=\"sun.htm\" alt=\"sun\">");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation20()).check();

        assertEquals("Should return 0 occurrences", 0,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
    }

    @Test
    public void shouldCheckRecommedation21() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a href=\"www.teste.com.br\">teste</a>");
        html.append("<a title=\"teste\"></a>");
        html.append("<a title=\"teste2\" href=\"link1\">link teste</a>");
        html.append("<a title=\"teste-teste\" href=\"link2\">link teste</a>");
        html.append("<a title=\"teste\">Leia mais</a>");
        html.append("<a><img alt=\"\" src=\"teste.img\"/></a>");
        html.append("<a><img alt=\"imagem de teste\" src=\"teste.img\"/></a>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 10 occurrences", 10,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
    }

    @Test
    public void shouldCheckRecommedation21LeiaMais() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a title=\"teste\" href=\"teste.html\">clique aqui!!!</a>");
        html.append("<a title=\"teste\" href=\"teste2.html\">leia mais</a>");
        html.append("<a title=\"teste\" href=\"teste2.html\"></a>");
        html.append("<a><img alt=\"saiba mais\" src=\"teste.img\"/></a>");
        html.append("<a><img alt=\"veja mais\" src=\"teste.img\"/></a>");
        html.append("<a title=\"teste\" href=\"teste3.html\">acesse a lista</a>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 14 occurrences", 14,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 21 occurrence", "3.5", ocorrencia.getCode());
            assertTrue("Recommendation 21 should be ERROR", ocorrencia.isError());
        }
    }

    @Test
    public void shouldCheckRecommedation21SameLinkDiferentContent() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a title=\"teste 1\" href=\"teste.html\" alt=\"teste1\">Content 1</a>");
        html.append("<a title=\"teste 2\" href=\"teste.html\" alt=\"teste2\">Content 2</a>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 4 occurrences", 4,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 21 occurrence", "3.5", ocorrencia.getCode());
            assertTrue("Recommendation 21 should be ERROR", ocorrencia.isError());
        }
    }

    @Test
    public void shouldCheckRecommedation21DiferentLinkSameContent() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a title=\"teste1\" href=\"teste.html\" alt=\"teste1\">Content 1</a>");
        html.append("<a title=\"teste2\" href=\"teste-2.html\" alt\"teste2\">Content 1</a>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 4 occurrences", 4,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 21 occurrence", "3.5", ocorrencia.getCode());
            assertTrue("Recommendation 21 should be ERROR", ocorrencia.isError());
        }
    }

    @Test
    public void shouldCheckRecommedation21SameDescription() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a title=\"teste\" href=\"teste.html\" alt=\"teste 2\">Teste 2</a>");
        html.append("<a title=\"teste 2\" href=\"teste2.html\" alt=\"teste 2\">Teste 2</a>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 5 occurrences", 5,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 21 occurrence", "3.5", ocorrencia.getCode());
            assertTrue("Recommendation 21 should be ERROR", ocorrencia.isError());
        }
    }

    @Test
    public void shouldCheckRecommedation21LargDescription() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<a href=\"teste.html\"");
        html.append("title=\"qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        html.append("123");
        html.append("\"");
        html.append(">teste</a>");

        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation21()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertTrue("Recommendation 21 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion1() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append("<img src=\"smiley.gif\"  height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion3() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append("<img src=\"smiley.gif\" alt=\" \" height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion4() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append("<img src=\"/pasta/pasta/smiley.gif\" alt=\"smiley.gif\" height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion5() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append("<img src=\"/pasta/pasta/smiley.gif\" height=\"42\" width=\"42\">");
        html.append("<img src=\"/pasta/pasta/smiley.gif\" alt=\"imagem\" height=\"42\" width=\"42\">");
        html.append("<img src=\"/pasta/pasta/smiley.gif\" alt=\"alt\" height=\"42\" width=\"42\">");
        html.append("<img src=\"/pasta/pasta/smiley.gif\" alt=\"descrição\" height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 4 occurrences", 4,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion7() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append("<img src=\"smiley.gif\" alt=\"descrição\" height=\"42\" width=\"42\">");
        html.append("<img src=\"/pasta/smiley.gif\" alt=\"arquivo descrição\" height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
    }

    @Test
    public void shouldCheckRecommedation22Criterion8() {

        StringBuilder html = new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");
        html.append(" <body>");
        html.append(
                "<img src=\"smiley.gif\" alt=\"arquivo descri��o\" title=\"arquivo descri��o\" height=\"42\" width=\"42\">");
        html.append("</body>");
        html.append("</html>");
        html.append("<html> ");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation22()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 22", "3.6",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 22 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
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
        html.append(
                "<area shape = \"poly\" alt=\"parte 3\" coords = \"116, 207, 186, 299, 49, 296\" href=\"parte3.html\"/>");
        html.append("</map>");
        html.append("<img src=\"imagem.jpg\" usemap=\"#shape\" />");
        html.append("</body>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation23()).check();

        assertEquals("Should return 2 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertTrue("Recommendation 23 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

    }

    @Test
    public void shouldCheckRecomendation24() {

        StringBuilder html = new StringBuilder("<html> ");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation24()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 24", "3.8",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());

    }

    @Test
    public void shouldAlwaysCheckRecommendation25() {
        StringBuilder html = new StringBuilder("<html> ");
        html.append("<table>\n");
        html.append("<thead>\n");
        html.append("</thead>\n");
        html.append("<tbody>\n");
        html.append("</tbody\n>");
        html.append("<tfoot>\n");
        html.append("</tfoot>\n");
        html.append("</table>\n");

        html.append("<table summary=\"sumary\">\n");
        html.append("<caption>teste</caption>\n");
        html.append("<th>\n");
        html.append("<td></td>\n");
        html.append("</th>\n");
        html.append("</table>\n");

        html.append("</html>\n");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation25()).check();

        assertEquals("Should return 2 occurrences", 2,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 25 occurrence", "3.9", ocorrencia.getCode());
            assertFalse("Recommendation 25 should be WARNING", ocorrencia.isError());
        }

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

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation26()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());

        for (Occurrence ocorrencia : occurrences.get(OccurrenceClassification.CONTENT_INFORMATION)) {
            assertEquals("Should return Recommendation 26 occurrence", "3.10", ocorrencia.getCode());
        }

        assertTrue("Recommendation 26 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
        // assertTrue("Recommendation 26 should be
        // ERROR",occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).isError());

    }

    @Test
    public void shouldAlwaysCheckRecommendation27() {
        StringBuilder html = new StringBuilder("<html><p>"
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
                + "conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo conteudo" + "</p>"
                + "<p align=\"justify\"></p>" + "</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation27()).check();

        assertEquals("Should return 2 occurrences", 2,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 27 ", "3.11",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertFalse("Recommendation 27 should be WARNING",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());
        assertEquals("Should return Recommendation 27 ", "3.11",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).getCode());
        assertTrue("Recommendation 27 should be ERRIR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(1).isError());

    }

    @Test
    public void shouldCheckRecomendation28() {
        /*
         * StringBuilder html = new StringBuilder("<html");
         * html.append("<body bgcolor=\"#000\">")
         * .append(" <table bgcolor=\"#000\">")
         * .append("  <tr bgcolor=\"#000\">")
         * .append("   <td bgcolor=\"#000\">") .append("   <td>")
         * .append("  </tr>") .append(" </table>") .append("</body>")
         * .append("</html>");
         * 
         * Map<OccurrenceClassification,List<Occurrence>> occurrences =
         * from(html.toString())
         * .with(presentation().recommendation28()).check();
         * 
         * assertEquals("Should return 4 occurrences",4,occurrences.get(
         * OccurrenceClassification.PRESENTATION_DESIGN).size());
         * 
         * for(Occurrence occurrence :
         * occurrences.get(OccurrenceClassification.PRESENTATION_DESIGN)) {
         * assertEquals("Should return Recommendation 28 occurrence","28",
         * occurrence.getCode());
         * assertTrue("Recomerndation 28 should be Error",
         * occurrence.isError()); }
         */

        StringBuilder html = new StringBuilder("<html> ");
        html.append("<abbr title=\"\"></abbr>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation28()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertEquals("Should return Recommendation 28", "3.12",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).getCode());
        assertTrue("Recommendation 26 should be ERROR",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

    }

    @Test
    public void shouldCheckRecomendation28WithWarning() {
        StringBuilder html = new StringBuilder("<html> ");
        html.append("<abbr title=\"\"></abbr>");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation28()).check();

        assertEquals("Should return 1 occurrences", 1,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
        assertTrue("Recommendation 28 should be Warning",
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).get(0).isError());

    }

}

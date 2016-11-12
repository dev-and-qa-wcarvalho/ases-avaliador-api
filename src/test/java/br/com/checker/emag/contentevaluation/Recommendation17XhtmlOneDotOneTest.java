package br.com.checker.emag.contentevaluation;

import static br.com.checker.emag.core.Checker.content;
import static br.com.checker.emag.core.Checker.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.checker.emag.Occurrence;
import br.com.checker.emag.OccurrenceClassification;

@RunWith(JUnit4.class)
public class Recommendation17XhtmlOneDotOneTest {

    @Test
    public void shouldCheckLanguageDefinitionUsingLangAttribute() {
        StringBuilder html = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"pt-BR\">");
        html.append("</html>");

        Map<OccurrenceClassification, List<Occurrence>> occurrences = from(html.toString())
                .with(content().recommendation17NewImplementation()).check();

        assertEquals("Should return 0 occurrences", 0,
                occurrences.get(OccurrenceClassification.CONTENT_INFORMATION).size());
    }

    @Test
    @Ignore
    public void shouldCheckLanguageDefinitionUsingEmptyLangAttribute() {

    }

    @Test
    @Ignore
    public void shouldCheckLanguageDefinitionUsingXmlLangAttribute() {

    }

    @Test
    @Ignore
    public void shouldCheckLanguageDefinitionUsingEmptyXmlLangAttribute() {

    }

    @Test
    @Ignore
    public void shouldCheckIfLanguageIsNotDefined() {

    }

    @Test
    @Ignore
    public void shouldCheckLanguageDefinitionWhenBothLangAttributesHaveSameValue() {

    }

    @Test
    @Ignore
    public void shouldCheckLanguageDefinitionWhenBothLangAttributesHaveDifferentValues() {

    }
}

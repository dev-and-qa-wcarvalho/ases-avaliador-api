package br.com.checker.emag;

import lombok.Getter;

public enum OccurrenceClassification {

    MARK("2.1","Marcação",1,9),
    BEHAVIOR("2.2","Comportamento",10,15),
    CONTENT_INFORMATION("2.3","Conteúdo/Informação",16,28),
    PRESENTATION_DESIGN("2.4","Apresentação / Design",29,32),
    MULTIMEDIA("2.5","Multimídia",33,37),
    FORM("2.6","Formulários",38,45);
    
    OccurrenceClassification(String code,String description,int start,int end) {
      this.code = code;
      this.description = description;
      this.start = start;
      this.end = end;
    }
    
    @Getter private final String code;
    @Getter private final String description;
    @Getter private final int start;
    @Getter private final int end;
}


package br.com.checker.emag;

import lombok.Getter;

public enum OccurrenceClassification {

	MARK("2.1","Marcação"),
	BEHAVIOR("2.2","Comportamento"),
	CONTENT_INFORMATION("2.3","Conteúdo/Informação"),
	PRESENTATION_DESIGN("2.4","Apresentação / Design"),
	MULTIMEDIA("2.5","Multimídia"),
	FORM("2.6","Formulários");
	
	OccurrenceClassification(String code,String description) {
		this.code = code;
		this.description = description;
	}
	
	@Getter private final String code;
	@Getter private final String description;
}


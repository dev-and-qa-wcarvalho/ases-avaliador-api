package br.com.checker.emag;

import lombok.Getter;
import lombok.ToString;

@ToString
public @Getter class Occurrence {
	
	private Integer line;
	private Integer column;
	private String code;
	private boolean error;
	private String tag;
	private OccurrenceClassification type;
	
	public Occurrence(int line, int column, String code, boolean error,
			String tag,
			OccurrenceClassification type) {
		this.line = line;
		this.column = column;
		this.code = code;
		this.error = error;
		this.tag = tag;
		this.type = type;
	}
	
	public Occurrence(String code, boolean error,
			String tag,
			OccurrenceClassification type) {
		this.code = code;
		this.error = error;
		this.tag = tag;
		this.type = type;
	}
	
	
}

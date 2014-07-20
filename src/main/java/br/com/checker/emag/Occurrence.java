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
	private String criterio;
	
	public Occurrence(int line, int column, String code, boolean error,
			String tag,
			OccurrenceClassification type,
			String criterio) {
		this.line = line;
		this.column = column;
		this.code = code;
		this.error = error;
		this.tag = tag;
		this.type = type;
		this.criterio = criterio;
	}
	
	
	public Occurrence(int line, int column, String code, boolean error,
			String tag,
			OccurrenceClassification type) {
		this(line, column, code, error, tag,type, null);
	}
	
	public Occurrence(String code, boolean error,
			String tag,
			OccurrenceClassification type,
			String criterio) {
		this.code = code;
		this.error = error;
		this.tag = tag;
		this.type = type;
		this.criterio = criterio;
	}
	
	public Occurrence(String code, boolean error,
			String tag,
			OccurrenceClassification type) {
		
		this(code, error, tag, type,null);
	}
	
	public String getTag() {
	
		this.tag = this.tag.replaceAll("<", "&lt;");
		this.tag = this.tag.replaceAll(">", "&gt;");
		this.tag = this.tag.replaceAll(" ", "&nbsp");
		return tag;
	}
	
}

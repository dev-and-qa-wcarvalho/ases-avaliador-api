package br.com.checker.emag;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString

public @Setter @Getter  class Occurrence implements Comparable<Occurrence>{
	
	private Integer line;
	private Integer column;
	private String code;
	private boolean error;
	private String tag;
	private OccurrenceClassification type;
	private String criterio;
	private String posLineOccurrence;
	
	
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Occurrence other = (Occurrence) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		return true;
	}


	public int compareTo(Occurrence other) {

		return this.line.compareTo(other.line);
	}
	
	
	
}

package br.com.checker.emag.xml;

import lombok.Data;

@Data
public class CheckPoint {
    
  private String description;
  private int totalErrors;
  private int totalWarnings;
}

package br.com.checker.emag.xml;

import lombok.Data;

public @Data class CheckPoint {
    
    private String description;
    private int totalErrors;
    private int totalWarnings;
}

package br.com.checker.emag.xml;

import lombok.Data;

public @Data class AvaliationReport {
    
    private Result result;
    
    
    public AvaliationReport() { this.result = new Result(); }
}

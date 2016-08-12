package br.com.checker.emag.util;

public class UrlSemArquiNoFinal {
    
    public String urlSemArquivoNoFinal(String url)
    {
        if(url != null)
        {
        String urlSemArquivo = "";
        boolean encontrouPonto = false;
        
    
        for(int i = url.length()-1; i >= 0; i-- )
        {
                        
            urlSemArquivo = url.substring(i-1,i);
            
            if(encontrouPonto && urlSemArquivo.equalsIgnoreCase("."))
            {
                break;
                
            }else if(urlSemArquivo.equalsIgnoreCase("."))
            {                   
                encontrouPonto = true;                  
                
            }else  if(encontrouPonto && urlSemArquivo.equalsIgnoreCase("/"))
            {
                url = url.substring(0,i);
                break;
            }
            else if(!encontrouPonto && urlSemArquivo.equalsIgnoreCase("/"))                 
            {
                break;
            }
        }
        }
        return url;
    }

}

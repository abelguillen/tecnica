package controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.CompressResponse;

@RestController
public class Controller {

    @RequestMapping("/candidate")
    public String Candidate() {
        return "Guillén, Abel Alejandro";
    }
    
    @RequestMapping(value = "/compress", method = RequestMethod.POST, 
    		consumes = MediaType.ALL_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public CompressResponse CompressService(
            HttpEntity<String> httpEntity) throws JSONException {
    	String json = httpEntity.getBody();
    	JSONObject obj = new JSONObject(json);
    	String value = obj.getString("value");
    	return Compress(value);
    }
    
    public CompressResponse Compress(String valor) {
    	String resultado = "";
    	int contador = 0;
        char memoria = '\0';
    	
    	valor.toUpperCase();
    	
    	for(int n=0; n<valor.length(); n++) {
            char c = valor.charAt(n);
            if(n == 0) {
                memoria = c;
                contador++;
            } else if(memoria == c) {
                contador++;
            } else if(memoria != c) {
                resultado += contador + String.valueOf(memoria);
                contador = 1;
                memoria = c;
            } if(n == valor.length()-1) {
                resultado += contador + String.valueOf(memoria);
            }
        }
    	return new CompressResponse(resultado);
    }
}
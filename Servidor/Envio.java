/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

/**
 *
 * @author reds
 */
public class Envio {
    private int xy1;
    private int xy2;
    private boolean inicio;
    private String User;
	
    public int getXy1() {
		return xy1;
	}
	public void setXy1(int xy1) {
		this.xy1 = xy1;
	}
	public int getXy2() {
		return xy2;
	}
	public void setXy2(int xy2) {
		this.xy2 = xy2;
	}
	public boolean isInicio() {
		return inicio;
	}
	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	/**
	 * Metodo que serializa un objeto de la clase Envio en formato JSON como String
	 * @param e Objeto de la clase Envio a serializar
	 * @return String en formato JSON
	 * @throws JsonProcessingException Indicador en caso de error
	 */
	 public String Shipout(Envio e) throws JsonProcessingException{
			ObjectMapper mapper = new ObjectMapper();
			String json;
				json = mapper.writeValueAsString(e);
			return json;
		}
	 /**
	  * Metodo que deserializa un String con formato JSON en un objeto de la clase Envio
	  * @param json String en formato JSON a deserializar
	  * @throws JsonParseException Indicador en caso de error
	  * @throws JsonMappingException Indicador en caso de error
	  * @throws IOException Indicador en caso de error
	  */
	 public void Shipin(String json) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY);
			Envio e = mapper.readValue(json, Envio.class);
			this.xy1 = e.getXy1();
			this.xy2 = e.getXy2();
			this.inicio = e.inicio;
			this.User = e.User;
			
}
}
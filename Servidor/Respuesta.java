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
public class Respuesta {
    private boolean Dibujo;
    private boolean espera;
    private int xpos[];
    private int ypos[];

    public boolean isDibujo() {
        return Dibujo;
    }

    public boolean isEspera() {
        return espera;
    }

    public int[] getXpos() {
        return xpos;
    }

    public int[] getYpos() {
        return ypos;
    }

    public void setDibujo(boolean Dibujo) {
        this.Dibujo = Dibujo;
    }

    public void setEspera(boolean espera) {
        this.espera = espera;
    }

    public void setXpos(int[] xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int[] ypos) {
        this.ypos = ypos;
    }
/**
 * Metodo que serializa un objeto de la clase Respuesta en formato JSON como String
 * @param e Objeto de la clase Respuesta a serializar
 * @return String en formato JSON
 * @throws JsonProcessingException Indicador en caso de error
 */
	public String Ansout(Respuesta e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
			json = mapper.writeValueAsString(e);
		return json;
	}
    /**
     * Metodo que deserializa un String con formato JSON en un objeto de la clase Respuesta
     * @param json String en formato JSON a deserializar
     * @throws JsonParseException Indicador en caso de error
     * @throws JsonMappingException Indicador en caso de error
     * @throws IOException Indicador en caso de error
     */
    public void Ansin(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY);
		Respuesta a = mapper.readValue(json, Respuesta.class);
		this.Dibujo = a.Dibujo;
		this.espera = a.espera;
		this.xpos = a.xpos;
		this.ypos = a.ypos;
}
}

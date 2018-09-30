/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NET;

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
    private boolean Inicio;
    private String User;
    private String Ip;
    private int[] xpos;
    private int[] ypos;
    private String Dibujo;
    private boolean escucha;
	
	//Objeto envio en limpio
		public Envio() {
			this.xy1=0;
	        this.xy2=0;
	        this.Inicio=false;
	        this.User=null;
	        this.Ip=null;
	        this.xpos = null;
			this.ypos = null;
			Dibujo = null;
			this.escucha = false;
		}
		
		//-----------------Cliente
	    public Envio(int co1,int co2, boolean inicio, String user, String ip){
	        this.xy1=co1;
	        this.xy2=co2;
	        this.Inicio=inicio;
	        this.User=user;
	        this.Ip=ip;
	        //Valores en null
	        this.xpos = null;
			this.ypos = null;
			this.Dibujo = null;
			this.escucha = false;
	    }
	    
	    //-----------------Servidor
	    public Envio(int[] xpos, int[] ypos, String dibujo, boolean escucha) {
			this.xpos = xpos;
			this.ypos = ypos;
			this.Dibujo = dibujo;
			this.escucha = escucha;
			//Valores en null
			this.xy1=0;
	        this.xy2=0;
	        this.Inicio=false;
	        this.User=null;
	        this.Ip=null;
		}


	/**
	 * Metodo que serializa un objeto de la clase Envio en formato JSON como String
	 * @param e Objeto de la clase Envio a serializar
	 * @return String en formato JSON
	 * @throws JsonProcessingException Indicador en caso de error
	 */
	 public String Shipout() throws JsonProcessingException{
			ObjectMapper mapper = new ObjectMapper();
			String json;
			json = mapper.writeValueAsString(this);
			return json;
		}
	 /**
	  * Metodo que deserializa un String con formato JSON en un objeto de la clase Envio
	  * @param json String en formato JSON a deserializar
	  * @throws JsonParseException Indicador en caso de error
	  * @throws JsonMappingException Indicador en caso de error
	  * @throws IOException Indicador en caso de error
	  */
	 public void Shipin(String json,boolean servidor) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY);
			Envio e = mapper.readValue(json, Envio.class);
			if (servidor) {
			this.xy1=e.getXy1();
	        this.xy2=e.getXy2();
	        this.Inicio=e.isInicio();
	        this.User=e.getUser();
	        this.Ip=e.getIp();}
			else {
			this.xpos = e.getXpos();
			this.ypos = e.getYpos();
			this.Dibujo = e.getDibujo();
			this.escucha = e.isEscucha();}
			
			
}


	public int getXy1() {
		return xy1;
	}


	public int getXy2() {
		return xy2;
	}


	public boolean isInicio() {
		return Inicio;
	}


	public String getUser() {
		return User;
	}


	public String getIp() {
		return Ip;
	}


	public int[] getXpos() {
		return xpos;
	}


	public int[] getYpos() {
		return ypos;
	}


	public String getDibujo() {
		return Dibujo;
	}


	public boolean isEscucha() {
		return escucha;
	}

	public void setXy1(int xy1) {
		this.xy1 = xy1;
	}

	public void setXy2(int xy2) {
		this.xy2 = xy2;
	}

	public void setInicio(boolean inicio) {
		Inicio = inicio;
	}

	public void setUser(String user) {
		User = user;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public void setXpos(int[] xpos) {
		this.xpos = xpos;
	}

	public void setYpos(int[] ypos) {
		this.ypos = ypos;
	}

	public void setDibujo(String dibujo) {
		Dibujo = dibujo;
	}

	public void setEscucha(boolean escucha) {
		this.escucha = escucha;
	}
}

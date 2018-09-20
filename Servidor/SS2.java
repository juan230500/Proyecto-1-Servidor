package Servidor;

import java.net.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class SS2 {
	static int jugadores = 0;
	public static void main(String[] args){

		// TODO Auto-generated method stub
		
		try {
			ServerSocket server = new ServerSocket(9987);
			while (true) {
			Respuesta pack = new Respuesta();
			pack.setDibujo(true);
			pack.setEspera(true);
			pack.setXpos(null);
			pack.setYpos(null);
			Socket misocket = server.accept();
	
			DataInputStream I0 = new DataInputStream(misocket.getInputStream());
			String mensaje = I0.readUTF();
			System.out.println("Servidor recibe: "+mensaje);
			DataOutputStream O1=new DataOutputStream(misocket.getOutputStream());//inicia
			O1.writeUTF(enviar(pack));
			System.out.println("Servidor envia: "+enviar(pack));
			O1.close();//termina
			misocket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Serializa y retorna como String en formato JSON a un objeto de la clase Respuesta
	 * @param a Objeto de la clase Respuesta que se serializara
	 * @return String en formato JSON del objeto de la clase Respuesta 
	 * @throws JsonProcessingException indicador de error
	 */
	public static String enviar(Respuesta a) throws JsonProcessingException {
		return a.Ansout(a);
	}
	/**
	 * Deserializa y retorna como objeto de la clase Envio a un String en formato JSON
	 * @param a String en formato JSON por deserializar
	 * @return Objeto de la clase Envio
	 * @throws JsonParseException Indicador de error
	 * @throws JsonMappingException Indicador de error
	 * @throws IOException Indicador de error
	 */
	public Envio recibir(String a) throws JsonParseException, JsonMappingException, IOException {
		Envio e=new Envio();
		e.Shipin(a);
		return e;
	}
	public static void verificar(Envio e) {//pendiente de terminar 
		if (e.isInicio()==true && jugadores<2){
			
		}else{
			//codigo de juanpablo
		}

	}
	public static void reinicio() {//pendiente de terminar
		//se debe agregar algo para indicar que los espacios estan disponibles para nuevos jugadores
		SS2.jugadores = 0;
	}
	
}



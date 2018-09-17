package Servidor;
import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * 
 */
public class SocketServidor {
	Socket ClienteSocket = null;
	Socket ClienteSocket1 = null;
	ServerSocket ServidorSocket = null;
	ServerSocket ServidorSocket1 = null;
	BufferedReader entrada = null;
	BufferedReader entrada1 = null;
	PrintWriter salida = null;
	PrintWriter salida1 = null;
	int n = 7779;
	int n1 = 7780;
	public  void start() throws IOException{
		
		try {
			ServidorSocket = new ServerSocket(7777);
			//ServidorSocket1 = new ServerSocket(7778); 		
					}
		catch(IOException error) {
			System.err.println("No se puede escuchar en puerto 7777 o 7778");
			System.exit(1);
		}
		
		
		try {
			ClienteSocket = ServidorSocket.accept();
			//ClienteSocket1 = ServidorSocket1.accept();
		}
		catch(IOException error){
			System.err.println("Falla en la conexión");
			System.exit(1);
		}
		PrintWriter salida = new PrintWriter(ClienteSocket.getOutputStream(), true);
		PrintWriter salida1 = new PrintWriter(ClienteSocket1.getOutputStream(), true);
		BufferedReader entrada = new BufferedReader(new InputStreamReader(ClienteSocket.getInputStream()));		
		BufferedReader entrada1 = new BufferedReader(new InputStreamReader(ClienteSocket.getInputStream()));		
		String inputLine, outputLine, inputLine1, outputLine1;
		while(((inputLine = entrada.readLine())!=null)&&((inputLine1 = entrada1.readLine())!=null)) {
			outputLine = inputLine;
			outputLine1 = inputLine1;
			salida.println("Fwd:"+outputLine);
			salida1.println("Fwd1:"+outputLine1);
			if(outputLine.equals("salir")||outputLine1.equals("salir"))
				break;
		
		}
		
	}
	
	public static String Ansout(Respuesta e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
			json = mapper.writeValueAsString(e);
		return json;
	}
	public static Respuesta Ansin(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Respuesta a = mapper.readValue(json, Respuesta.class);
		return a;
	}
	
	public static String Shipout(Envio e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		json = mapper.writeValueAsString(e);
		return json;
	}
	
	public static Envio Shipin(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Envio e = mapper.readValue(json, Envio.class);
		return e;
	}
	public void close() throws IOException {
		salida.close();
		entrada.close();
		ClienteSocket.close();
		ServidorSocket.close();
	}

}

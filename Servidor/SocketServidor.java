package Servidor;
import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Interfaz.Envio;
import Interfaz.Respuesta;
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
			ServidorSocket = new ServerSocket(n);
			ServidorSocket1 = new ServerSocket(n1); 		
					}
		catch(IOException error) {
			System.err.println("No se puede escuchar en puerto 7777 o 7778");
			System.exit(1);
		}
		
		
		try {
			ClienteSocket = ServidorSocket.accept();
			ClienteSocket1 = ServidorSocket1.accept();
		}
		catch(IOException error){
			System.err.println("Falla en la conexión");
			System.exit(1);
		}
		
	}
	
	public void escuchar(Object data) throws IOException {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(ClienteSocket.getInputStream()));		
		BufferedReader entrada1 = new BufferedReader(new InputStreamReader(ClienteSocket1.getInputStream()));
		System.out.println("Cliente1:"+entrada);
		System.out.println("Cliente2:"+entrada1);
		
		
	}
	public void enviar(Respuesta data, Socket a) throws IOException {
		salida = new PrintWriter(a.getOutputStream(),true);	
		salida.println(Ansout(data));
		System.out.println(Ansout(data)); //...
	}
	public Envio recibir(Socket a) throws IOException {
		entrada = new BufferedReader(new InputStreamReader(a.getInputStream()));
		String inputline = entrada.readLine();
		return Shipin(inputline);
		System.out.println(Shipin(inputline)); //para llevar control de lo que se envia
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

package Servidor;
import java.io.*;
import java.net.*;
/*
 * 
 */
public class SocketServidor {
	public static void main(String[] args) throws IOException{
		ServerSocket ServidorSocket = null;
		try {
			ServidorSocket = new ServerSocket(7777);
		}
		catch(IOException error) {
			System.err.println("No se puede escuchar en puerto 7777");
			System.exit(1);
		}
		Socket ClienteSocket = null;
		
		try {
			ClienteSocket = ServidorSocket.accept();
		}
		catch(IOException error){
			System.err.println("Falla en la conexión");
			System.exit(1);
		}
		PrintWriter salida = new PrintWriter(ClienteSocket.getOutputStream(), true);
		BufferedReader entrada = new BufferedReader(new InputStreamReader(ClienteSocket.getInputStream()));		
		String inputLine, outputLine;
		while((inputLine = entrada.readLine())!=null) {
			outputLine = inputLine;
			salida.println("Fwd:"+outputLine);
			if(outputLine.equals("salir"))
				break;
			
		}
		salida.close();
		entrada.close();
		ClienteSocket.close();
		ServidorSocket.close();
	}
}

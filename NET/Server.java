package NET;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import adt.List;

public class Server implements Runnable {
	private ServerSocket server;
	private int Puerto;
	private  Envio Informacion;
	
	 public Server (int numero){
	        try {
	        	server=new ServerSocket(numero);
	        } catch (IOException ex) {
	            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    public Envio escuchar(){
	            Thread hilo= new Thread(this);
		    hilo.start();
	        try {
	            hilo.join();
	        } catch (InterruptedException ex) {
	            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return Informacion;}

	    @Override
	    public void run() {
	    	try {
	    		String condicion ="";
	    		 
				Socket misocket=server.accept();
				DataInputStream I0=new DataInputStream(misocket.getInputStream());
				String mensaje=I0.readUTF();
	            Envio e1=new Envio();
				e1.Shipin(mensaje,true);
				Informacion=e1;
				misocket.close();
				condicion="exit";
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}

}

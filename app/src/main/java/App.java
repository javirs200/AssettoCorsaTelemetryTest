import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.net.InetAddresses;
 
public class App {
 
    public static void main(String[] args) {
 
        //puerto del servidor
        final int PUERTO_SERVIDOR = 9996;
        //buffer donde se almacenara los mensajes
        
 
        try {
            //Obtengo la localizacion de localhost
            String ipAddress = "192.168.1.140";
            InetAddress direccionServidor = InetAddress.getByName(ipAddress);
 
            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();

            System.out.println(socketUDP.isBound());
 
            //String mensaje = "¡Hola mundo desde el cliente!";
            handsaker hs = new handsaker(1,1,0);
 
            //Convierto el mensaje a bytes
            int len = hs.toString().length();
            ByteBuffer buf = ByteBuffer.allocate(len);
            buf.putInt(hs.identifier);
            buf.putInt(hs.version);
            buf.putInt(hs.operationId);
            buf.put((byte) 0);
            buf.flip();
            
            byte[] buffer = buf.array();
            
            //Creo un datagrama
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
 
            //Lo envio con send
            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta);

             /*
             * struct handshackerResponse{
                    char carName[50];
                    char driverName[50];
                    int identifier;
                    int version;
                    char trackName[50];
                    char trackConfig[50];
                };

                cout<< sizeof(handshackerResponse);

                208
             */

            int resplen = 208;
            ByteBuffer resbuf = ByteBuffer.allocate(resplen);
            byte[] resbuffer = resbuf.array();

            //handshackerResponse hrs = new handshackerResponse();

            
           
            //Preparo la respuesta
            
            DatagramPacket peticion = new DatagramPacket(resbuffer,resbuffer.length);
 
            //Recibo la respuesta
            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");
            String mensaje = "";
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
 
            //cierro el socket
            socketUDP.close();
 
        } catch (SocketException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 
}

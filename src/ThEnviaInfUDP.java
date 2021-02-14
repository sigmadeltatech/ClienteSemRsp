import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThEnviaInfUDP extends Thread {

	String ip, idMsg;
	int port, nrMsg, interv;
	
	public ThEnviaInfUDP( String ip, int port, int nrMsg, int interv, String idMsg ) {
		this.ip = ip;
		this.port = port; this.nrMsg = nrMsg; this.interv = interv;
		this.idMsg = idMsg;
	}
	
	public void run() {
		String msgUDP = null;
		DatagramSocket socketUDP = null;
		DatagramPacket dtgmOut = null;
		for ( int i = 0; i< nrMsg; i++ ) {
			msgUDP = geraInformeUDP( i+1, idMsg );
			try {
				socketUDP = new DatagramSocket();
				dtgmOut = new DatagramPacket( msgUDP.getBytes(), msgUDP.getBytes().length, InetAddress.getByName( ip ), port );
				socketUDP.send( dtgmOut );
                socketUDP.close();
                Registro.registrarUDP( msgUDP );
                System.out.println( "\nEnviado Informe UDP: <" + msgUDP + ">"  );
				Thread.sleep(interv);
			}
			catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}
	
	private static String geraInformeUDP( int nrMsg, String idMsg ) {
		java.util.Date atual = new java.util.Date();
		java.text.SimpleDateFormat dia = new java.text.SimpleDateFormat("yyy-MM-dd");
		java.text.SimpleDateFormat hora = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		String repDtTime = dia.format(atual).toString() + "T" + hora.format(atual).toString() + "Z";
		String objTNText = idMsg + "-UDP-" + nrMsg ;
		String symb = Long.toString(System.currentTimeMillis());
		float lat = (float) -12.274257, lon = (float) -45.120850; int alt = 1234;
		String pad = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		pad += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		return (repDtTime + "$" + objTNText + "$" + symb + "$" + lat + "$" + lon + "$" + alt + "$" + pad);
		//return( "a");
	}
	
}

import java.io.PrintWriter;
import java.net.Socket;

public class ThEnviaInfTCP extends Thread {

	String ip, idMsg;
	int port, nrInf, interv, idThread;
	
	public ThEnviaInfTCP( String ip, int port, int nrInf, int interv, String idMsg ) {
		this.ip = ip;
		this.port = port; this.nrInf = nrInf; this.interv = interv;
		this.idMsg = idMsg;
	}
	
	public void run() {
		String msgTCP = null;
		Socket socketTCP = null;
		PrintWriter out = null;
		for ( int i = 0; i< nrInf; i++ ) {
			msgTCP = geraInformeTCP( idThread, i+1, idMsg );
			try {
				socketTCP = new Socket(ip, port);
				socketTCP.setTcpNoDelay(true);
				out = new PrintWriter(socketTCP.getOutputStream(), true);
				out.println(msgTCP);
				out.flush();
                socketTCP.close();
                Registro.registrarTCP( msgTCP );                
				System.out.println( "\nEnviado Informe TCP: <" + msgTCP + ">"  );
				Thread.sleep(interv);
			}
			catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static String geraInformeTCP( int idThread, int nrMsg, String idMsg ) {
		
		java.util.Date atual = new java.util.Date();
		java.text.SimpleDateFormat dia = new java.text.SimpleDateFormat("yyy-MM-dd");
		java.text.SimpleDateFormat hora = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		String repDtTime = dia.format(atual).toString() + "T" + hora.format(atual).toString() + "Z";
		String objTNText = idMsg + "-TCP-" + nrMsg ;
		String symb = Long.toString(System.currentTimeMillis());
		float lat = (float) -12.274257, lon = (float) -45.120850; int alt = 1234;
		return (repDtTime + "$" + objTNText + "$" + symb + "$" + lat + "$" + lon + "$" + alt);
	}
	
}


public class clienteSemRsp {
	
	public static void main(String[] args) {
	
		String ipServ; int nrMsg, interv;
		int portTCP = 9001, portUDP = 9002;
		if ( args.length != 4 )
			System.out.println( "Uso: cliente [TCP|UDP] IP-Serv Nr-Msg Interv-ms");
		else {
			ipServ = args[1];
			nrMsg = Integer.parseInt(args[2]);
			interv = Integer.parseInt(args[3]);
			if ( args[0].contains("UDP") ) {
				ThEnviaInfUDP thEnviaInfUDP = new ThEnviaInfUDP( ipServ, portUDP, nrMsg, interv, "Msg" );
				thEnviaInfUDP.start();
			}
			else if ( args[0].contains("TCP") ) {
				ThEnviaInfTCP thEnviaInfTCP = new ThEnviaInfTCP( ipServ, portTCP, nrMsg, interv, "Msg" );
				thEnviaInfTCP.start();
			}
			else
				System.out.println( "Ops, protocolo desconhecido!" );				
		}
	}

}

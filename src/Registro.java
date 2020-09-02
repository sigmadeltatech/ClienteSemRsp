import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Registro {
	
	static void registrarUDP( String msgUDP ) {

		try {
			File fileName = new File("Log_Cli_Sem_Rsp_UDP.csv");
			FileWriter csvwriter = new FileWriter(fileName, true);
			java.util.Date actualDate = new java.util.Date();
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(actualDate);
			csvwriter.append(formater.format(actualDate) + ",");
			csvwriter.append(msgUDP + ",");
			csvwriter.append("\n");
			csvwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void registrarTCP( String msgTCP ) {

		try {
			File fileName = new File("Log_Cli_Sem_Rsp_TCP.csv");
			FileWriter csvwriter = new FileWriter(fileName, true);
			java.util.Date actualDate = new java.util.Date();
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(actualDate);
			csvwriter.append(formater.format(actualDate) + ",");
			csvwriter.append(msgTCP + ",");
			csvwriter.append("\n");
			csvwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
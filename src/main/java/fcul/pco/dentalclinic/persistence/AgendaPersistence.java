package fcul.pco.dentalclinic.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.Agenda;
import fcul.pco.dentalclinic.domain.Appointment;
import fcul.pco.dentalclinic.domain.Doctor;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class AgendaPersistence {
	private static String dName = fcul.pco.dentalclinic.main.ApplicationConfiguration.ROOT_DIRECTORY;

	/**
	 * Saves agenda on a file with the name of the doctor id
	 * @param d. Doctor with the agenda
	 * @param a. agenda of doctor
	 * @throws IOException
	 */
	public static void save(Doctor d) throws IOException {
		String fName = Integer.toString(d.getId());
		FileWriter fw = new FileWriter(dName + fName);
		fw.write("");

		for (Appointment ap : d.getAgenda()) {
			fw.write(ap.toString() + "\n");
		}

		fw.close();		
	}

	/**
	 * Reads file and creates a agenda
	 * @param d. Doctor with the agenda
	 * @return Agenda from the file read
	 * @throws IOException 
	 */
	public static Agenda load(Doctor d) throws IOException{
		Agenda ag = new Agenda();

		String fName = Integer.toString(d.getId());
		
		try {
			Scanner f = new Scanner(new File(dName + fName));
		}
		
		catch (FileNotFoundException f) {
			save(d);
		}
		
		Scanner f = new Scanner(new File(dName + fName));
		
		while (f.hasNextLine()) {
			String l = f.nextLine();
			Appointment ap = Appointment.fromString(l);
			ag.addAppointment(ap);
		}
		f.close();

		return ag;
	}
}

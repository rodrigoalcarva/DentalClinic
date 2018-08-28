package fcul.pco.dentalclinic.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import fcul.pco.dentalclinic.domain.Doctor;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class DoctorCatalog {
	private static String fName = fcul.pco.dentalclinic.main.ApplicationConfiguration.DOCTOR_CATALOG_FILENAME;
	private static String dName = fcul.pco.dentalclinic.main.ApplicationConfiguration.ROOT_DIRECTORY;

	/**
	 * Saves a catalog to a file
	 * @param doctors. Map with all the doctors
	 * @throws IOException
	 */
	public static void save(Map<Integer, Doctor> doctors) throws IOException {

		FileWriter fw = new FileWriter(dName + fName);
		for (Doctor d : doctors.values()) {
			fw.write(d.toString() + "\n");
		}

		fw.close();
		
		
	}

	/**
	 * Loads a catalog from a file and returns a catalog instance.
	 *
	 * @throws FileNotFoundException
	 */
	public static Map<Integer, Doctor> load() throws FileNotFoundException {
		Map<Integer,Doctor> doctorCatalog = new TreeMap<Integer,Doctor>();

		Scanner f = new Scanner(new File(dName + fName));
		while (f.hasNextLine()) {
			String l = f.nextLine();
			Doctor d = Doctor.fromString(l);

			doctorCatalog.put(d.getId(), d);
		}
		f.close();

		return doctorCatalog;
	}	
}

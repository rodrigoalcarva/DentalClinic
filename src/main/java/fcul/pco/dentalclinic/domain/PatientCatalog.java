package fcul.pco.dentalclinic.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class PatientCatalog {
	static TreeMap<Integer, Patient> thePatients;
	static PatientCatalog instance;

	private PatientCatalog() {
		thePatients = new TreeMap<Integer, Patient>();
	}

	/**
	 * Gets or creates if null a pattient catalog
	 * @return patient catalog
	 */
	public static PatientCatalog getInstance() {
		if (instance == null) {
			instance = new PatientCatalog();
		}
		return instance;
	}

	/**
	 * Add a patient to the catalog
	 * @param p. patient to add
	 */
	public void addPatient(Patient p) {
		thePatients.put(p.getId(), p);
	}

	/**
	 * Get a instance of patient using the ID
	 * @param id. Id of patient
	 * @return patient
	 */
	public Patient getPatientById(int id) {
		return thePatients.get(id);
	}
	
	/**
	 * Saves a catalog to a file.
	 *
	 * @throws IOException
	 */
	public void save() throws IOException {
		fcul.pco.dentalclinic.persistence.PatientCatalog.save(thePatients);
	}

	/**
	 * Loads a catalog from a file and returns a catalog instance.
	 *
	 * @throws IOException
	 */
	public static void load() throws IOException {
		thePatients = (TreeMap<Integer, Patient>) fcul.pco.dentalclinic.persistence.PatientCatalog.load();
	}

	/**
	 * @return string the represents the catalog
	 */
	public String toString() {
		List <List<String>> table = new ArrayList<List<String>>();
		for (Patient d : thePatients.values()) {
			ArrayList<String> row = new ArrayList<String>();
			row.add(String.valueOf(d.getId()));
			row.add(d.getName());
			table.add(row);
		}
		return Utils.tableToString(table);
	}

}

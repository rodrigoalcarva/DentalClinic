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

public class DoctorCatalog {
	static TreeMap<Integer, Doctor> doctorCatalog;
	static DoctorCatalog instance;

	private DoctorCatalog() {
		doctorCatalog = new TreeMap<Integer,Doctor>();
	}

	/**
	 * Gets or creates if null a doctor catalog
	 * @return doctorCatalog
	 */
	public static DoctorCatalog getInstance() {
		if (instance == null) {
			instance = new DoctorCatalog();
		}
		return instance;
	}

	/**
	 * Add a doctor to the catalog
	 * @param d. Doctor to add
	 */
	public void addDoctor(Doctor d) {
		doctorCatalog.put(d.getId(), d);
	}
	
	/**
	 * Get a instance of doctor using the ID
	 * @param id. Id of doctor
	 * @return doctor
	 */
	public Doctor getDoctorById(int id) {
		return doctorCatalog.get(id);
	}


	/**
	 * Saves a catalog to a file.
	 *
	 * @throws IOException
	 */
	public void save() throws IOException {
		fcul.pco.dentalclinic.persistence.DoctorCatalog.save(doctorCatalog);
	}


	/**
	 * Loads a catalog from a file and returns a catalog instance.
	 *
	 * @throws IOException
	 */
	public static void load() throws IOException {
		doctorCatalog = (TreeMap<Integer, Doctor>) fcul.pco.dentalclinic.persistence.DoctorCatalog.load();
	}

	/**
	 * @return string the represents the catalog
	 */
	public String toString() {
		List<List<String>> table = new ArrayList<>();
		for (Doctor d : doctorCatalog.values()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(String.valueOf(d.getId()));
			row.add(d.getName());
			table.add(row);
		}
		return Utils.tableToString(table);
	}


}

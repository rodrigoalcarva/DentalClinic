package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class Doctor {
	private String name;
	private int id;
	private Agenda agenda;
	
	
	/**
	 * Creates a doctor instance
	 * @param id. Id of doctor
	 * @param name. Name of doctor
	 * @param agenda. Agenda of doctor
	 */
	public Doctor(int id, String name,Agenda agenda) {
		this.name = name;
		this.id = id;
		this.agenda = agenda;
	}

	private Doctor(int id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * 
	 * @return int that represents the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return string that represents the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return agenda that represents the agenda
	 * @throws IOException 
	 */
	public Agenda getAgenda() throws IOException {
		if (agenda == null) {
			agenda = Agenda.load(this);
		}
		return agenda;
	}

	/**
	 * @return string the represents the date
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId());
		sb.append("!");
		sb.append(this.getName());
		String s = sb.toString();
		return s;
	}

	/**
	 * 
	 * @param s. The string of a doctor
	 * @return a instance of doctor using the string
	 */
	public static Doctor fromString(String s) {
		String[] ds = s.split("!");
		Doctor d = new Doctor(Integer.parseInt(ds[0]), ds[1]);
		return d;
	}
}


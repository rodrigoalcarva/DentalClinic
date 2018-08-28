package fcul.pco.dentalclinic.domain;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class Patient {
	private String nome;
	private int id;

	/**
	 * Creates a instance of a patient
	 * @param id
	 * @param nome
	 */
	public Patient(int id, String nome) {
		this.id = id;
		this.nome = nome;		
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
		return nome;
	}
	
	/**
	 * @return string the represents the patient
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
	 * @param s. The string of a patient
	 * @return a instance of patient using the string
	 */
	public static Patient fromString(String s) {
		String[] ds = s.split("!");
		Patient p = new Patient (Integer.parseInt(ds[0]), ds[1]);
		return p;
	}
}

package fcul.pco.dentalclinic.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fcul.pco.dentalclinic.main.App;

public class Appointment implements Comparable<Appointment>{
	private Date data;
	private String assunto;
	private int duracao;
	private int paciente;
	
	/**
	 * Creates a Appointment instance. Arguments must specify a valid date
	 * @param data
	 * @param assunto
	 * @param duracao
	 * @param p
	 */
	public Appointment(Date data, String assunto, int duracao, Patient p) {
		this.data = data;
		this.assunto = assunto;
		this.duracao = duracao;
		this.paciente = p.getId();
	}

	/**
	 * 
	 * @return date that represents day of appointment
	 */
	public Date getData() {
		return data;
	}

	/**
	 * 
	 * @return string that represents the matter of appointment
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * 
	 * @return int that represents the duration of appointment
	 */
	public int getDuracao() {
		return duracao;
	}
	
	/**
	 * 
	 * @return patient that represents the patient of appointment
	 * @throws IOException
	 */
	public Patient getPatient() throws IOException {
		return App.getPatientCatalog().getPatientById(paciente);
	}

	/**
	 * @return string the represents the appointment
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getData());
		sb.append("@");
		sb.append(this.getAssunto());
		sb.append("@");
		sb.append(this.getDuracao());
		sb.append("@");
		try {
			sb.append(this.getPatient());
		} 
		catch (IOException e) {
			System.err.println("Error loading PatientCatalog.");
		}
		String s = sb.toString();
		return s;
	}

	/**
	 * 
	 * @param s. The string of the appointment
	 * @return a instance of a appointment using the string 
	 */
	public static Appointment fromString(String s) {
		String[] ds = s.split("@");
		Date data = Date.fromString(ds[0]);
		Patient paciente =  Patient.fromString(ds[3]);
		
		Appointment a = new Appointment(data, ds[1], Integer.parseInt(ds[2]), paciente);
		return a;
	}
	
	/**
	 * Makes a list that will get used in making a table
	 * @return list of strings
	 */
	public List<String> toRow() {
		String[] appointmentList = this.toString().split("@");
		ArrayList<String> row = new ArrayList<String>();
		
		String[] date = appointmentList[0].split("!");
		StringBuilder sb = new StringBuilder();
		sb.append(date[2]);
		sb.append("/");
		sb.append(date[1]);
		sb.append("/");
		sb.append(date[0]);
		sb.append("@");
		sb.append(date[3]);
		sb.append(":");
		sb.append(date[4]);
		
		String s = sb.toString();
		
		row.add(s);
		row.add(appointmentList[3].split("!")[1]);
		row.add(this.getAssunto());
		return row;
	}

	
	@Override
	public int compareTo(Appointment o) {
		if (this.getData().isBefore(o.getData())) {
			return -1;
		}
		if (o.getData().isBefore(this.getData())) {
			return 1;
		}
		return 0;
	}
}


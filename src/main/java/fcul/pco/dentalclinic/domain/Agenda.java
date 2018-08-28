package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
 *
 */

public class Agenda implements Iterable<Appointment>{
	private ArrayList<Appointment> appointments;

	/**
	 * Creates a agenda instance
	 */
	public Agenda() {
		this.appointments = new ArrayList<Appointment>();
	}

	/**
	 * Add a appointment to agenda
	 * @param a. Appointment to add
	 */
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}

	/**
	 * Saves agenda on a file with the name of the doctor id
	 * @param d. Doctor with the agenda
	 * @throws IOException
	 */
	public void save(Doctor d) throws IOException {
		fcul.pco.dentalclinic.persistence.AgendaPersistence.save(d);
	}

	/**
	 * Reads file and creates a agenda
	 * @param d. Doctor with the agenda
	 * @return Agenda from the file read
	 * @throws IOException 
	 */
	public static Agenda load(Doctor d) throws IOException {
		return fcul.pco.dentalclinic.persistence.AgendaPersistence.load(d);
	}
	
	/**
	 * Gets appointments of given date
	 * @param d. Date to get the appointments
	 * @return list of appointments
	 * @throws FileNotFoundException
	 */
	public List<Appointment> getDayAppointments (Date d) throws FileNotFoundException {
		List<Appointment> la = new ArrayList<Appointment>();
		for (int i = 0; i < appointments.size(); i++) {
			Date appointDate = appointments.get(i).getData();
			if (appointDate.getDia() == d.getDia() && appointDate.getMes() == d.getMes() && appointDate.getAno() == d.getAno()) {
				la.add(appointments.get(i));
			}
		}
		Collections.sort(la);
		return la;
	}

	/**
	 * Gets dates after given date
	 * @param from. Date to get the appointments from
	 * @return list of dates
	 */
	public List<Date> getNextAppointmentDates(Date from) {
		List<Date> ls =  new ArrayList<Date>();

		for (Appointment a : this.appointments) {
			Date data = a.getData();
			if (from.isBefore(data) || from.toString().equals(data.toString())) {
				ls.add(data);
			}
		}
		return ls;
	}
	

	@Override
	public Iterator<Appointment> iterator() {
		return appointments.iterator();
	}
}

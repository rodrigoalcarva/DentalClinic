package fcul.pco.dentalclinic.main;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.DoctorCatalog;
import fcul.pco.dentalclinic.domain.PatientCatalog;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class App {
	static DoctorCatalog doctorCatalog;
	static PatientCatalog thePatients;

	public static void main(String[] args) throws IOException {
		initialize();
		interactiveMode();
		executeAllUseCases();
	}

	private static void interactiveMode() throws IOException {
		try (Scanner in = new Scanner(System.in)) {
			in.useLocale(Locale.US);
			Menu.mainMenu(in);
		}
	}

	private static void initialize() {
		doctorCatalog = DoctorCatalog.getInstance();
		try {
			DoctorCatalog.load();
		} catch (IOException ex) {
			System.err.println("Error loading DoctorCatalog.");
		}
	}

	/**
	 * This method allows the application to work in non interactive mode i.e.
	 * the input is read from a file. It should be used for testing. A file,
	 * called a use-case contains a sequence of inputs that allows testing some
	 * functionalities of the application. A use-case file may contain comments
	 * (useful to make it easy to understand). Comments begin with the #
	 * character and extend until the end of the line. Here is an example of
	 * use-case file:
	 * --------
	 * # addDoctorUsecase
	 * 3
	 * 1
	 * Johnny
	 * 123
	 * 2
	 * 3
	 * 4
	 * --------
	 *
	 *
	 * @param useCaseFileName A String that represents the name of a file that
	 * contains a use-case.
	 * @throws IOException
	 * @requires the contents of the file must be correct with respect of the
	 * menus (see class Menu) and the input data expected by the application,
	 * unless the objective of the test is to verify an illegal situation.
	 */
	private static void executeUseCase(String useCaseFileName) throws IOException {
		System.out.println("Test: " + useCaseFileName);
		Scanner in = new Scanner(new File(useCaseFileName));
		in.useLocale(Locale.US);
		in.nextLine();
		Menu.mainMenu(in);
		in.close();
	}

	public static void executeAllUseCases() {
		try {
			executeUseCase("data/addDoctorUsecase");
			executeUseCase("data/addPatientUsecase");
			executeUseCase("data/makeAppointmentUsecase");
			executeUseCase("data/seeAgendaUsecase");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Gets the doctor catalog
	 * @return doctor catalog
	 * @throws IOException
	 */
	public static DoctorCatalog getDoctorCatalog() throws IOException {
		return doctorCatalog;
	}

	/**
	 * Gets the patient catalog
	 * @return patient catalog
	 * @throws IOException
	 */
	public static PatientCatalog getPatientCatalog() throws IOException {
		if (thePatients == null) {
			thePatients = PatientCatalog.getInstance();
			try {
				PatientCatalog.load();
			} catch (IOException ex) {
				System.err.println("Error loading DoctorCatalog.");
			}
		}
		
		return thePatients;
	}
}


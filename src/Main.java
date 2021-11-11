import java.util.Scanner;
import bdfi.*;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidYearException;

/**
 * 
 * @author Pedro Fernandes 60694
 * @author Guilherme Santana
 *
 */
public class Main {
	// Gender names
	public static final String MALE = "male";
	public static final String FEMALE = "female";
	public static final String OTHER = "other";
	public static final String NOT_PROVIDED = "not-provided";

	// Success messages
	public static final String PERSON_ADDED = "Person added.\n";
	public static final String SHOW_ADDED = "Show added.\n";

	// Error messages
	public static final String INVALID_YEAR = "Invalid year.\n";
	public static final String INVALID_GENDER = "Invalid gender information.\n";
	public static final String ID_PERSON_EXISTS = "idPerson exists.\n";

	/**
	 * Main class entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BDFI bdfi = new BDFIClass();

		while (runCommands(in, bdfi))
			;
	}

	/**
	 * Gets the next string separated by spaces in the scanner as a command
	 * 
	 * @param in - system in scanner
	 * @return a command
	 */
	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}

	/**
	 * Gets the next string separated by spaces in the scanner as a gender
	 * 
	 * @param in - system in scanner
	 * @return a gender
	 * @throws InvalidGenderException when information about the gender is not valid
	 */
	private static Gender getGender(Scanner in) {
		switch (in.next().toLowerCase()) {
		case MALE:
			return Gender.MALE;
		case FEMALE:
			return Gender.FEMALE;
		case OTHER:
			return Gender.OTHER;
		case NOT_PROVIDED:
			return Gender.NOT_PROVIDED;
		default:
			return Gender.INVALID;
		}
	}

	/**
	 * Runs commands on the database based on the input retrieved from the scanner
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 * @return <code>true</code> if execution should continue, <code>false</code>
	 *         otherwise
	 */
	private static boolean runCommands(Scanner in, BDFI bdfi) {
		// Variable that determines if execution should
		// continue or not
		boolean cont = true;

		switch (getCommand(in)) {
		case ADDPERSON:
			addPerson(in, bdfi);
			break;
		case ADDSHOW:
			addShow(in, bdfi);
			break;
		case UNKNOWN:
			cont = false;
			break;
		}

		return cont;
	}

	/**
	 * Inserts a professional to the database
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void addPerson(Scanner in, BDFI bdfi) {
		String id = in.next();
		int bYear = in.nextInt();
		String email = in.next();
		String phone = in.next();
		Gender gender = getGender(in);
		String name = in.nextLine().strip();
		
		try {
			bdfi.addPerson(id, bYear, email, phone, gender, name);
			System.out.printf(PERSON_ADDED);
		}
		catch (InvalidYearException e) {
			System.out.printf(INVALID_YEAR);
		}
		catch (InvalidGenderException e) {
			System.out.printf(INVALID_GENDER);
		}
	}

	private static void addShow(Scanner in, BDFI bdfi) {
		String id = in.next();
		int pYear = in.nextInt();
		String title = in.nextLine().strip();

		try {
			bdfi.addShow(id, pYear, title);
			System.out.printf(SHOW_ADDED);
		}
		catch (InvalidYearException e) {
			System.out.printf(INVALID_YEAR);
		}

	}

}

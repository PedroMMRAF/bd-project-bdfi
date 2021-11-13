import java.time.LocalDateTime;
import java.util.Scanner;
import bdfi.*;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidYearException;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
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
	public static final String UNKNOWN_COMMAND = "Unknown command.\n";
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
		BDFI bdfi = new BDFIClass(LocalDateTime.now().getYear());

		// Run until false is returned, meaning execution ended
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
			return null;
		}
	}

	/**
	 * Gets the next string separated by spaces in the scanner as a gender
	 * 
	 * @param in - system in scanner
	 * @return a gender if any valid match occurs, <code>null</code> otherwise
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
			return null;
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
		switch (getCommand(in)) {
		case ADDPERSON:
			addPerson(in, bdfi);
			break;
		case ADDSHOW:
			addShow(in, bdfi);
			break;
		case EXIT:
			// Returning false ends command execution
			return false;
		default:
			System.out.printf(UNKNOWN_COMMAND);
			break;
		}

		// Returning true continues command execution
		return true;
	}

	/**
	 * Adds a new professional to the database
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
			bdfi.addPerson(id, name, bYear, gender, email, phone);
			System.out.printf(PERSON_ADDED);
		} catch (InvalidYearException e) {
			System.out.printf(INVALID_YEAR);
		} catch (InvalidGenderException e) {
			System.out.printf(INVALID_GENDER);
		}
	}

	/**
	 * Adds a new show to the database
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void addShow(Scanner in, BDFI bdfi) {
		String id = in.next();
		int pYear = in.nextInt();
		String title = in.nextLine().strip();

		try {
			bdfi.addShow(id, pYear, title);
			System.out.printf(SHOW_ADDED);
		} catch (InvalidYearException e) {
			System.out.printf(INVALID_YEAR);
		}

	}

}

import java.time.LocalDateTime;
import java.util.Scanner;
import bdfi.*;
import bdfi.exceptions.HasPremieredException;
import bdfi.exceptions.IdPersonDoesNotExistException;
import bdfi.exceptions.IdShowDoesNotExistException;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidRatingException;
import bdfi.exceptions.InvalidYearException;
import dataStructures.Iterator;

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
	private static final String PERSON_ADDED = "Person added.\n";
	private static final String SHOW_ADDED = "Show added.\n";
	private static final String PARTICIPATION_ADDED = "Participation added.\n";
	private static final String SHOW_PREMIERED = "Successful production.\n";
	private static final String SHOW_REMOVED = "Show removed.\n";
	private static final String SHOW_TAGGED = "Tag added.\n";
	private static final String SHOW_FORMAT = "%s %s %d %f.\n";
	private static final String SHOW_RATED = "Rating applied.\n";

	// Error messages
	private static final String UNKNOWN_COMMAND = "Unknown command.\n";
	private static final String INVALID_YEAR = "Invalid year.\n";
	private static final String INVALID_GENDER = "Invalid gender information.\n";
	private static final String INVALID_RATING = "Invalid Rating.";
	private static final String PERSON_EXISTS = "idPerson exists.\n";
	private static final String PERSON_MISSING = "idPerson does not exist.\n";
	private static final String SHOW_EXISTS = "idShow exists.\n";
	private static final String SHOW_MISSING = "idShow does not exist.\n";
	private static final String SHOW_ALREADY_PREMIERED = "idShow has already completed production.\n";
	private static final String SHOW_NOT_PREMIERED = "idShow is in production.\n";

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
			return Command.UNKNOWN;
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
		case ADDPARTICIPATION:
			addParticipation(in, bdfi);
			break;
		case PREMIERE:
			premiere(in, bdfi);
			break;
		case REMOVESHOW:
			removeShow(in, bdfi);
			break;
		case TAGSHOW:
			tagShow(in, bdfi);
			break;
		case INFOSHOW:
			infoShow(in, bdfi);
			break;
		case RATESHOW:
			rateShow(in, bdfi);
			break;
		case EXIT:
			// Returning false ends command execution
			return false;
		case UNKNOWN:
			System.out.printf(UNKNOWN_COMMAND);
			in.nextLine();
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

	/**
	 * Adds the participation of a professional to a show
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void addParticipation(Scanner in, BDFI bdfi) {
		String idPerson = in.next();
		String idShow = in.next();
		String description = in.nextLine().strip();

		try {
			bdfi.addParticipation(idPerson, idShow, description);
			System.out.printf(PARTICIPATION_ADDED);
		} catch (IdPersonDoesNotExistException e) {
			System.out.printf(PERSON_MISSING);
		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		}

	}

	/**
	 * Premieres a show from the database
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void premiere(Scanner in, BDFI bdfi) {
		String idShow = in.next();
		in.nextLine();

		try {
			bdfi.premiereShow(idShow);
			System.out.printf(SHOW_PREMIERED);
		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		} catch (HasPremieredException e) {
			System.out.printf(SHOW_ALREADY_PREMIERED);
		}
	}

	/**
	 * Removes a show from the database
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void removeShow(Scanner in, BDFI bdfi) {
		String idShow = in.next();
		in.nextLine();

		try {
			bdfi.removeShow(idShow);
			System.out.printf(SHOW_REMOVED);
		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		} catch (HasPremieredException e) {
			System.out.printf(SHOW_ALREADY_PREMIERED);
		}

	}

	/**
	 * Tags a show on the database with a keyword
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void tagShow(Scanner in, BDFI bdfi) {
		String idShow = in.next();
		String tag = in.next();
		in.nextLine();

		try {
			bdfi.addTag(idShow, tag);
			System.out.printf(SHOW_TAGGED);
		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		}

	}

	/**
	 * Shows information about a show on the database
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void infoShow(Scanner in, BDFI bdfi) {
		String idShow = in.next();
		in.nextLine();

		try {
			Show show = bdfi.infoShow(idShow);

			System.out.printf(SHOW_FORMAT, show.getId(), show.getTitle(), show.getProductionYear(), show.getRating());

			Iterator<String> tags = show.tagsIterator();

			while (tags.hasNext())
				System.out.println(tags.next());

		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		}

	}
	
	/**
	 * Adds a rating to a show
	 * 
	 * @param in   - system in scanner
	 * @param bdfi - database object
	 */
	private static void rateShow(Scanner in, BDFI bdfi) {
		String idShow = in.next();
		int stars = in.nextInt();
		in.nextLine();
		
		try {
			bdfi.rateShow(idShow, stars);
		} catch (InvalidRatingException e) {
			System.out.printf(INVALID_RATING);
		} catch (IdShowDoesNotExistException e) {
			System.out.printf(SHOW_MISSING);
		} catch (HasPremieredException e) {
			System.out.printf(SHOW_NOT_PREMIERED);
		}
		
	}

}

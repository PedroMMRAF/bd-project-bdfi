import bdfi.*;
import bdfi.exceptions.*;
import dataStructures.Iterator;

import java.io.*;
import java.util.Scanner;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class Main {
    // Current year to be used by the constructor of the database
    private static final int CURRENT_YEAR = 2021;

    // BDFI save data file name
    private static final String DATA_FILE = "bdfi.dat";

    // Gender names
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final String OTHER = "other";
    private static final String NOT_PROVIDED = "not-provided";

    // Success messages
    private static final String PERSON_ADDED = "Person added.\n";
    private static final String PERSON_FORMAT = "%s %s %d %s %s %s\n";
    private static final String PARTICIPANT_FORMAT = "%s %s %d %s %s %s %s\n";
    private static final String PARTICIPATION_ADDED = "Participation added.\n";
    private static final String SHOW_ADDED = "Show added.\n";
    private static final String SHOW_PREMIERED = "Successful production.\n";
    private static final String SHOW_REMOVED = "Show removed.\n";
    private static final String SHOW_TAGGED = "Tag added.\n";
    private static final String SHOW_FORMAT = "%s %s %d %d\n";
    private static final String SHOW_RATED = "Rating applied.\n";

    // Error messages
    private static final String UNKNOWN_COMMAND = "Unknown command.\n";
    private static final String INVALID_YEAR = "Invalid year.\n";
    private static final String INVALID_GENDER = "Invalid gender information.\n";
    private static final String INVALID_RATING = "Invalid Rating.\n";
    private static final String PERSON_EXISTS = "idPerson exists.\n";
    private static final String PERSON_MISSING = "idPerson does not exist.\n";
    private static final String PERSON_NO_SHOWS = "idPerson has no shows.\n";
    private static final String SHOW_EXISTS = "idShow exists.\n";
    private static final String SHOW_MISSING = "idShow does not exist.\n";
    private static final String SHOW_ALREADY_PREMIERED = "idShow has already completed " +
            "production.\n";
    private static final String SHOW_NOT_PREMIERED = "idShow is in production.\n";
    private static final String SHOW_NO_PEOPLE = "idShow has no participations.\n";
    private static final String NO_SHOWS = "No shows.\n";
    private static final String NO_SHOWS_PREMIERED = "No finished productions.\n";
    private static final String NO_SHOWS_RATED = "No rated productions.\n";
    private static final String NO_SHOWS_WITH_RATING = "No productions with rating.\n";
    private static final String NO_SHOWS_TAGGED = "No tagged productions.\n";
    private static final String NO_SHOWS_WITH_TAG = "No shows with tag.\n";
    private static final String QUIT_MESSAGE = "Serializing and quitting...\n";

    /**
     * Main class entry point
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BDFI bdfi = loadData();

        // Run until false is returned, meaning execution ended
        while (runCommands(in, bdfi)) ;
    }

    /**
     * Loads a database object from a file, or creates a new one
     *
     * @return the loaded database instance if it existed, a new one otherwise
     */
    private static BDFI loadData() {
        BDFI bdfi = null;

        try {
            FileInputStream fp = new FileInputStream(DATA_FILE);
            ObjectInputStream op = new ObjectInputStream(fp);

            bdfi = (BDFIClass) op.readObject();

            op.close();
            fp.close();
        }
        catch (FileNotFoundException e) {
            // ignore
        }
        catch (IOException | ClassNotFoundException e) {
            // ignore
        }

        if (bdfi == null)
            bdfi = new BDFIClass(CURRENT_YEAR);

        return bdfi;
    }

    /**
     * Saves a database object into a file
     *
     * @param bdfi - database object
     */
    private static void saveData(BDFI bdfi) {
        try {
            FileOutputStream fp = new FileOutputStream(DATA_FILE);
            ObjectOutputStream op = new ObjectOutputStream(fp);

            op.writeObject(bdfi);

            op.close();
            fp.close();
        }
        catch (FileNotFoundException e) {
            // ignore
        }
        catch (IOException e) {
            // ignore
        }
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
        }
        catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Gets the next string separated by spaces in the scanner as a gender
     *
     * @param in - system in scanner
     * @return a gender if any valid match occurs, <code>null</code> otherwise
     */
    private static String getGender(Scanner in) {
        String gender = in.next();

        switch (gender.toLowerCase()) {
            case MALE:
            case FEMALE:
            case OTHER:
            case NOT_PROVIDED:
                return gender;
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
     * otherwise
     */
    private static boolean runCommands(Scanner in, BDFI bdfi) {
        boolean ret = true;

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
            case INFOPERSON:
                infoPerson(in, bdfi);
                break;
            case LISTBESTSHOWS:
                listBestShows(in, bdfi);
                break;
            case LISTPARTICIPATIONS:
                listParticipations(in, bdfi);
                break;
            case LISTSHOWS:
                listShows(in, bdfi);
                break;
            case LISTSHOWSPERSON:
                listShowsPerson(in, bdfi);
                break;
            case LISTTAGGEDSHOWS:
                listTaggedShows(in, bdfi);
                break;
            case QUIT:
                quit(in, bdfi);
                // Returning false ends command execution
                ret = false;
                break;
            case UNKNOWN:
                System.out.print(UNKNOWN_COMMAND);
                in.nextLine();
                break;
        }

        System.out.println();

        // Returning true continues command execution
        return ret;
    }

    /**
     * Prints information of a show
     *
     * @param show - the show to print the information of
     */
    private static void printShow(Show show) {
        System.out.printf(SHOW_FORMAT, show.getId(), show.getTitle(), show.getProductionYear(),
                show.getRating());
    }

    /**
     * Prints information of a person
     *
     * @param person - the person to print the information of
     */
    private static void printPerson(Person person) {
        System.out.printf(PERSON_FORMAT, person.getId(), person.getName(), person.getBirthYear(),
                person.getEmail(), person.getPhone(), person.getGender());
    }

    /**
     * Prints information of a participant
     *
     * @param participant - the participant to print the information of
     */
    private static void printParticipant(Participant participant) {
        Person person = participant.getPerson();

        System.out.printf(PARTICIPANT_FORMAT, person.getId(), person.getName(),
                person.getBirthYear(), person.getEmail(), person.getPhone(),
                person.getGender(), participant.getDescription());
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
        String gender = getGender(in);
        String name = in.nextLine().trim();

        try {
            bdfi.addPerson(id, name, bYear, gender, email, phone);
            System.out.print(PERSON_ADDED);
        }
        catch (InvalidYearException e) {
            System.out.print(INVALID_YEAR);
        }
        catch (InvalidGenderException e) {
            System.out.print(INVALID_GENDER);
        }
        catch (IdPersonExistsException e) {
            System.out.print(PERSON_EXISTS);
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
        String title = in.nextLine().trim();

        try {
            bdfi.addShow(id, pYear, title);
            System.out.print(SHOW_ADDED);
        }
        catch (InvalidYearException e) {
            System.out.print(INVALID_YEAR);
        }
        catch (IdShowExistsException e) {
            System.out.print(SHOW_EXISTS);
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
        String description = in.nextLine().trim();

        try {
            bdfi.addParticipation(idPerson, idShow, description);
            System.out.print(PARTICIPATION_ADDED);
        }
        catch (IdPersonDoesNotExistException e) {
            System.out.print(PERSON_MISSING);
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
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
            System.out.print(SHOW_PREMIERED);
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
        }
        catch (HasPremieredException e) {
            System.out.print(SHOW_ALREADY_PREMIERED);
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
            System.out.print(SHOW_REMOVED);
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
        }
        catch (HasPremieredException e) {
            System.out.print(SHOW_ALREADY_PREMIERED);
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
            System.out.print(SHOW_TAGGED);
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
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

            printShow(show);

            Iterator<String> tags = show.listTags();

            while (tags.hasNext())
                System.out.println(tags.next());

        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
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
            System.out.print(SHOW_RATED);
        }
        catch (InvalidRatingException e) {
            System.out.print(INVALID_RATING);
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
        }
        catch (HasPremieredException e) {
            System.out.print(SHOW_NOT_PREMIERED);
        }
    }

    /**
     * Shows information about a professional on the database
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void infoPerson(Scanner in, BDFI bdfi) {
        String idPerson = in.next();
        in.nextLine();

        try {
            printPerson(bdfi.infoPerson(idPerson));
        }
        catch (IdPersonDoesNotExistException e) {
            System.out.print(PERSON_MISSING);
        }
    }

    /**
     * Lists every show a professional is participating in
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void listShowsPerson(Scanner in, BDFI bdfi) {
        String idPerson = in.next();
        in.nextLine();

        try {
            Iterator<Show> it = bdfi.listShowsPerson(idPerson);

            while (it.hasNext())
                printShow(it.next());
        }
        catch (IdPersonDoesNotExistException e) {
            System.out.print(PERSON_MISSING);
        }
        catch (PersonHasNoShowsException e) {
            System.out.print(PERSON_NO_SHOWS);
        }
    }

    /**
     * Lists a show's professionals
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void listParticipations(Scanner in, BDFI bdfi) {
        String idShow = in.next();
        in.nextLine();

        try {
            Iterator<Participant> it = bdfi.listParticipations(idShow);

            while (it.hasNext())
                printParticipant(it.next());
        }
        catch (IdShowDoesNotExistException e) {
            System.out.print(SHOW_MISSING);
        }
        catch (ShowHasNoParticipantsException e) {
            System.out.print(SHOW_NO_PEOPLE);
        }
    }

    /**
     * Lists the shows with the best ratings on the database
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void listBestShows(Scanner in, BDFI bdfi) {
        in.nextLine();

        try {
            Iterator<Show> it = bdfi.listBestShows();

            while (it.hasNext())
                printShow(it.next());
        }
        catch (NoShowsInSystemException e) {
            System.out.print(NO_SHOWS);
        }
        catch (NoShowsPremieredException e) {
            System.out.print(NO_SHOWS_PREMIERED);
        }
        catch (NoRatedShowsException e) {
            System.out.print(NO_SHOWS_RATED);
        }
    }

    /**
     * Lists every show on the database with a specific rating
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void listShows(Scanner in, BDFI bdfi) {
        int rating = in.nextInt();
        in.nextLine();

        try {
            Iterator<Show> it = bdfi.listShows(rating);

            while (it.hasNext())
                printShow(it.next());
        }
        catch (InvalidRatingException e) {
            System.out.print(INVALID_RATING);
        }
        catch (NoShowsInSystemException e) {
            System.out.print(NO_SHOWS);
        }
        catch (NoShowsPremieredException e) {
            System.out.print(NO_SHOWS_PREMIERED);
        }
        catch (NoRatedShowsException e) {
            System.out.print(NO_SHOWS_RATED);
        }
    }

    /**
     * Lists every tagged show on the database
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void listTaggedShows(Scanner in, BDFI bdfi) {
        String tag = in.nextLine().trim();

        try {
            Iterator<Show> it = bdfi.listTaggedShows(tag);

            while (it.hasNext())
                printShow(it.next());
        }
        catch (NoShowsInSystemException e) {
            System.out.print(NO_SHOWS);
        }
        catch (NoTaggedShowsException e) {
            System.out.print(NO_SHOWS_TAGGED);
        }
        catch (NoShowsWithTagException e) {
            System.out.println(NO_SHOWS_WITH_TAG);
        }

    }

    /**
     * Serializes the database before finishing the program
     *
     * @param in   - system in scanner
     * @param bdfi - database object
     */
    private static void quit(Scanner in, BDFI bdfi) {
        in.nextLine();

        System.out.print(QUIT_MESSAGE);

        saveData(bdfi);
    }

}

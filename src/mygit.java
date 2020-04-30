import java.util.HashMap;
import java.util.regex.*;
/**
 * <b>mygit</b>is a simple simulator for Gits:
 * <ol>
 *     <li>init</li>
 *     <li>add</li>
 *     <li>commit</li>
 * </ol>
 *
 *
 */

public class mygit {
    /**
     * Creating a repository in the form of a {@code HashMap}
     */
    private HashMap<String, File> repository;

    /**
     * Getter for the stage
     * @return the stage contents
     */
    public HashMap<String, File> getStage() {
        return stage;
    }

    /**
     * Getter for the working tree
     * @return the contents of the working tree
     */
    public HashMap<String, File> getWorkingTree() {
        return workingTree;
    }

    /**
     * Creating the stage in the form of a {@code HashMap}
     */
    private HashMap<String, File> stage;
    /**
     * Creating the working tree in the form of a {@code HashMap}
     */
    private HashMap<String, File> workingTree;


    /**
     * Constructor for mygit simulator
     * containing a REPOSITORY, STAGE, AND WORKING TREE
     * using a {@code HashMap<>} data structure
     */
    public mygit() {

        /**
         * creating a repository using a {@code HashMap<>}
         */
        this.repository = new HashMap<>();
        /**
         * creating a stage using a {@code HashMap<>}
         */
        this.stage = new HashMap<>();
        /**
         * creating a working tree using a {@code HashMap<>}
         */
        this.workingTree = new HashMap<>();

    }

    /**
     * Getter for the repository
     * @return the repository and if the repository is null, return an error message
     */
    public HashMap<String, File> getRepository() {
	if (this.repository!=null){
		return repository;
	}
	else {
		throw new Error("Can not create Repository object");
	}	
}

    /**
     * Git <b>init</b> initialises Git by creating a new repository, otheriwse
     * will return an error if a new repository cannot be created
     *
     */
    public void init() {
        try {
            this.repository = new HashMap<>();
        } catch (Exception e) {
            System.out.println("Repository error. Can not create a new one.");
        }

    }

    /**
     * Git <b>add</b> adds a file to the stage
     * and if it cannot do so, an error message is thrown
     *
     * @param file is the file to be put on the stage
     * @exception e is the exception that is caught if the file cannot be added
     */
    public void add(String file) {
        try {
            this.stage.put(file, this.workingTree.get(file));
        } catch (Exception e) {
            System.out.println("Add file error. Can not add file to working tree.");
        }
    }

    /**
     * Git <b>create</b> creates a new file to be put onto the working directory
     * and throws an exception if unable to do so
     *
     * @param name is the nsame of the file to be created
     * @exception e is the exception that is caught if a file cannot be created in the working tree
     */
    public void create(String name) {
        try{
            this.workingTree.put(name, new File(name));          
        }
        catch (Exception e) {
            System.out.println("Sorry can not create new file.");
        }
    }

    /**
     * Git <b>touch</b> updates the version number of the file and throws an exception
     * if it is unable to do so
     *
     * @param name is the name of the file whos version number is to be updated
     * @exception e us the exception that is caught if touch cannot be executed
     */
    public void touch(String name) {
        try {
            this.workingTree.get(name).updateFileVersion();
           
        }
        catch (Exception e) {
            System.out.println("Sorry can not execute touch.");
        }
    }

    /**
     * Git <b>commit</b> is when a file is added from the stage to the repository
     */
    public void commit() {

        for (String filename : this.stage.keySet()) {

            if (this.stage.get(filename).getVersionNum() == 1) {

                this.repository.put(filename, new File(filename));

            } else {

                this.repository.get(filename).setVersionNum(this.stage.get(filename).getVersionNum());

            }

        }

        this.stage = new HashMap<>();
    }

    /**
     * This method prints out the HashMap in a formatted version
     * and throws an exception if it cannot do so
     *
     * @param map is the HashMap to be printed.
     * @exception e is the exceptiont that is caught if the HashMap cannot be printed
     */
    private void printOutHashMap(HashMap<String, File> map) {
	try{
		for (String filename : map.keySet()) {
		    System.out.printf("Filename: %s, Version; %d", filename, map.get(filename).getVersionNum());
		}
	}catch (Exception e) {
            System.out.println("Could not printOutHashMap");
        }
    }

    /**
     * <b>printCurrentStatus</b> prints the current state of
     * <ol>
     *     <li>Working Tree</li>
     *     <li>Stage</li>
     *     <li>Repository</li>
     * </ol>
     * in a formatted way
     */
    public void printCurrentStatus() {
        System.out.println("---------------------------------------------------");

        System.out.println("Current state of the working tree:");
        this.printOutHashMap(this.workingTree);

        System.out.println();

        System.out.println("Current state of the stage:");
        this.printOutHashMap(this.stage);

        System.out.println();

        System.out.println("Current state of the repository:");
        this.printOutHashMap(this.repository);
        System.out.println();

        System.out.println("---------------------------------------------------");

    }

    /**
     * This <b>Main</b> method is used for running the simulation of Git!
     *
     * @param args is the args within the main function
     */
    public static void main(String[] args) {
        String command = "";
        for (int i = 0; i < args.length; i++) {
            command += (args[i]);
        }
        mygit simulation = new mygit();
        Pattern p = Pattern.compile("\\binitcreate(.*/)*.+add(.*/)*.+(commit)?\\b");
        Matcher m = p.matcher(command);
        boolean b = m.matches();
        if (b) {
            int index = 0;

            while (index < args.length) {

                switch (args[index]) {

                case "init":
                    simulation.init();
                    index++;
                    break;

                case "add":
                    index++;
                    simulation.add(args[index]);
                    index++;
                    break;

                case "commit":
                    simulation.commit();
                    index++;
                    break;

                case "create":
                    index++;
                    simulation.create(args[index]);
                    index++;
                    break;

                case "touch":
                    index++;
                    simulation.touch(args[index]);
                    index++;
                    break;
                }

                simulation.printCurrentStatus();
            }
        } else {
            System.out
                    .println("Wrong format. Command format: 'java mygit init create <filename> add <filename> commit'");
        }

    }
}


/**
 * Class File is used to resemble a File within the Git file system
 *
 * A file is simply a name {@code fileName} and version {@code versionNum}
 *
 */
public class File {

    /**
     * Creating the <b>name</b>and<b>version number</b> variables
     * The two features that define a file
     */
    private String fileName;
    private int versionNum;

    /**
     * Constructor for the File class
     * @param fileName is passed to the constructor with the file name
     */
    public File(String fileName) {

        this.fileName = fileName;
        this.versionNum = 1; // Set the initial value for the class attribute versionNum

    }

    /**
     * <b>Getter</b> method which gets the name of the file
     * @return the filename
     */
    public String getFileName() {
        if (fileName == null) {
             throw new Error("invalid file name " + fileName);
        }


        return this.fileName;

    }

    /**
     * <b>Getter</b> for the version number to return the version number
     * @return the version number§
     */
    public int getVersionNum() {
        if (this.versionNum < 0) {
             throw new Error("invalid version number " + versionNum);
        }

        return this.versionNum;

    }

    /**
     * <b>Setter</b> for the version number
     * and when called allows the {@code versionNum} to be set to {@code num}
     * @param num The number which will be set as the {@code versionNum}
     */
    public void setVersionNum(int num){
        if (num < 0) {
             throw new Error("invalid num " + num);
        }

        this.versionNum = num;

    }

    /**
     * Method to update the version number of a file
     * This method increments the version number by one when called
     */
    public void updateFileVersion() {
        if (this.versionNum < 0) {
             throw new Error("invalid version number " + versionNum);
        }

        this.versionNum += 1;
    }

    /**
     * This {@code toString} method returns the string representation of the filename
     * @return the string representation of the filename
     */
    @Override
    public String toString(){
       if (this.fileName == null) {
             throw new Error("invalid file name " + fileName);
        }
        return this.fileName;
    }

    /**
     * Main method for running the test file
     * This Main method prints the file number and version number using Getters
     * @param args All arguments are passed to the main to be able to run the code
     */
    public static void main(String[] args) {
        File test = new File("TestFile");
        System.out.println(test.getFileName());
        System.out.println(test.getVersionNum());

    }
    
}

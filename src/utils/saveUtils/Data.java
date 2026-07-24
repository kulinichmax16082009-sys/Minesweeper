package utils.saveUtils;

import windows.BasicWindow;

import java.io.*;

/**
 * Data abstract class is used as parent of all data classes. Provides save and load ability.
 *
 * @author Maksym Kulynych
 */
public abstract class Data implements Serializable {

    /**
     * This method is used for getting a path for loading/saving.
     * @return text path to a file
     */
    protected abstract String getFilePath();

    /**
     * This method creates an empty instance of a class.
     * @return an empty instance of class
     */
    protected abstract Data createEmpty();

    /**
     * This method simply saves class parameters to a .dat file.
     */
    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream(getFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();

            fos.close();
        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while saving " + getFilePath());
        }
    }

    /**
     * This method simply creates new instance of data from .dat file
     * @return new Data instance
     */
    public Data loadData() {
        try {
            FileInputStream fis = new FileInputStream(getFilePath());
            ObjectInputStream ois = new ObjectInputStream(fis);

            Data data = (Data) ois.readObject();

            ois.close();
            fis.close();

            return data;
        } catch (Exception e) {
            return createEmpty();
        }
    }
}

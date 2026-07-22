package utils.saveUtils;

import windows.BasicWindow;

import java.io.*;

public abstract class Data implements Serializable {

    protected abstract String getFilePath();

    protected abstract Data createEmpty();

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream(getFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();

            fos.close();
        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while saving data");
        }
    }

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

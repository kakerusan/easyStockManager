package thirdGroup.bean;

public class Storage {
    private int id;
    private String storagename;
    private String storagestyle;
    private int storageID;

	public Storage() {
	}

	public Storage(int id, String storagename, String storagestyle, int storageID) {
		this.id = id;
		this.storagename = storagename;
		this.storagestyle = storagestyle;
		this.storageID = storageID;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoragename() {
        return storagename;
    }

    public void setStoragename(String storagename) {
        this.storagename = storagename;
    }

    public String getStoragestyle() {
        return storagestyle;
    }

    public void setStoragestyle(String storagestyle) {
        this.storagestyle = storagestyle;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

	@Override
    public String toString() {
		return "Storage{id = " + id + ", storagename = " + storagename + ", storagestyle = " + storagestyle + ", storageID = " + storageID + "}";
	}
}

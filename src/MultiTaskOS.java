import java.util.ArrayList;

public class MultiTaskOS extends OperatingSystem {
    private ArrayList<String> currentTask = new ArrayList<>();
    private int currentDisk = -1;
    private int taskMemory;
    private ArrayList<FileSystem> disks = new ArrayList<>();

    public MultiTaskOS(String name, String version, String architecture, Kernel oskern, int taskMemory)
    {
        super(name,version,architecture,oskern);
        this.taskMemory = taskMemory;
    }

    @Override
    protected void loadApplication(Application app) {

    }

    @Override
    protected void unloadApplication() {

    }
}

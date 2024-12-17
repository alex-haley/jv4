import java.util.ArrayList;
import java.util.Objects;

public class MultiTaskOS extends OperatingSystem {
    private ArrayList<Application> currentTask;
    private int currentDisk = -1;
    int taskMemory;
    private ArrayList<FileSystem> disks = new ArrayList<>();

    public MultiTaskOS(String name, String version, String architecture, Kernel oskern, int taskMemory)
    {
        super(name,version,architecture,oskern);
        this.taskMemory = taskMemory;
        currentTask = new ArrayList<>(taskMemory);
    }

    public void addDisk(String name, int maxLen, int blockSize)
    {
        FileSystem fs = new FileSystem(name, maxLen, blockSize);
        disks.add(fs);
        currentDisk++;
    }

    public void changeDisk(int diskIndex)
    {
        if (disks.size() < diskIndex)
        {
            System.out.println("DISK WITH THAT INDEX DOESNT EXISTS");
        }
        else
        {
            currentDisk = diskIndex;
            System.out.println("DISK HAS CHANGED. CURRENT DISK INDEX: " + currentDisk);
        }
    }

    public void list()
    {
        if (currentDisk < 0)
        {
            System.out.println("DISK NOT ATTACHED TO SYSTEM");
            System.out.println("PLEASE CREATE A DISK FILESYSTEM");
        }
        else {
            disks.get(currentDisk).getDiskSpace();
        }
    }

    public int getMem()
    {
        return taskMemory;
    }

    public int getDiskSize()
    {
        if (currentDisk < 0)
        {
            return 0;
        }
        else {
            return disks.getLast().getDiskSize();
        }
    }

    public void getDisks()
    {
        int i = 0;
        if (disks.isEmpty())
        {
            System.out.println("there is no disks initialized");
        }
        else {
            for (FileSystem fs : disks)
            {
                System.out.println(i + " : " + disks.get(i).getDiskName());
                i++;
            }
        }
    }

    public int countDisks()
    {
        int i = 0;
        if (disks.isEmpty())
        {
            return i;
        }
        else {
            for (FileSystem fs : disks)
            {
                i++;
            }
        }
        return i;
    }

    public int countSpace()
    {
        int answ;
        if (currentDisk < 0)
        {
            answ = 0;
        }
        else {
            answ = disks.get(currentDisk).getDiskSpaceCount();
        }
        return answ;
    }

    public void loadedApps()
    {
        if (currentDisk > -1)
        {
            disks.get(currentDisk).getDiskSpace();
        }
        else {
            System.out.println("THERE IS NO DISK ATTACHED TO THE SYSTEM");
        }
    }

    public Application getApp(int aid)
    {
        return currentTask.get(aid);
    }

    @Override
    protected void loadApplication(Application app) {
        if (app.getRamUsage() > taskMemory && !oskern.getMemoryIsolation())
        {
            System.out.println("CORE DUMP\nTRACE: 0f 0b 1b 03 50 d2 2b c0 e9 2a f7 ff ff b0 04 00 00 00 e8 e9\nWHY: BUFFER OVERFLOW\n");
            System.exit(1);
        }
        else if (app.getRamUsage() > taskMemory && oskern.getMemoryIsolation())
        {
            System.out.println("TASK COULDNT BE INITIALIZED, ABORTING");
            System.out.println("WHY: FOUND MEMORY LEAK");
        }
        else
        {
            if (Objects.equals(app.getPlatform(), oskern.getKernelPlatform()))
            {
                currentTask.add(app);
                System.out.println("TASK INITIALIZED:\t"+app.getName());
                disks.get(currentDisk).Write(app.getName());
            }
            else if (!Objects.equals(app.getPlatform(), oskern.getKernelPlatform()))
            {
                System.out.println("THIS APP IS NOT SUPPORTED");
                System.out.println("WHY: UNSUPPORTED PLATFORM DEFINITION");
            }
            else {
                System.out.println("COMPUTER IS BUSY\nTRY TO UNLOAD CURRENT TASK\nOR WAIT WHEN IT FINISHES");
            }
        }
    }

    @Override
    protected void unloadApplication() {
        if (!currentTask.isEmpty())
        {
            System.out.print("enter id of application: ");
            int index = Menu.scan.nextInt();
            currentTask.remove(index);
            disks.get(currentDisk).Delete(index);
            System.out.println("APPLICATION UNLOADED");
        }
        else {
            System.out.println("THERES NO APPLICATION TO UNLOAD");
        }
    }

    @Override
    public void runApplication(Application app)
    {
        if (Objects.equals(app.getPlatform(), oskern.getKernelPlatform()) && !disks.get(currentDisk).Read(disks.get(currentDisk).Find(app.getName())).isEmpty())
        {
            app.runApp();
        }
        else if (!Objects.equals(app.getPlatform(), oskern.getKernelPlatform()))
        {
            System.out.println("APPLICATION IS NOT SUPPORTED ON THIS MACHINE, ABORTED");
            System.out.println("APPLICATION RUN ON: "+ app.getPlatform() + " MACHINES");
        }
        else {
            System.out.println("ERROR: COULD NOT READ THE CONTENTS OF PROGRAM: "+ app.getName());
        }
    }
}
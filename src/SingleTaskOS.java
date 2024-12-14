import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

public class SingleTaskOS extends OperatingSystem {
  private String currentTask;
  private int currentDisk = -1;
  private int taskMemory;
  private ArrayList<FileSystem> disks = new ArrayList<>();

  public SingleTaskOS(String name, String version, String architecture, Kernel oskern, int taskMemory) 
  {
    super(name, version, architecture, oskern);
    this.taskMemory = taskMemory;
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
    disks.get(currentDisk).dir();
  }

  public int getMem()
  {
    return taskMemory;
  }

  public int getDiskSize()
  {
    return disks.getLast().getDiskSize();
  }

  @Override
  protected void loadApplication(Application app)
  {
    if (app.getRamUsage() > taskMemory && !oskern.getMemoryIsolation())
    {
      System.out.println("CORE DUMP\nTRACE: 0f 0b 1b 03 50 d2 2b c0 e9 2a f7 ff ff b0 04 00 00 00 e8 e9\nWHY: BUFFER OVERFLOW\n");
    }
    else if (app.getRamUsage() > taskMemory && oskern.getMemoryIsolation())
    {
      System.out.println("TASK COULDNT BE INITIALIZED, ABORTING");
    }
    else
    {
      if (currentTask.isEmpty() && Objects.equals(app.getPlatform(), oskern.getKernelPlatform()))
      {
        currentTask = app.getName();
        System.out.println("TASK INITIALIZED:\t"+app.getName());
        disks.get(currentDisk).Write(app.getName());
      }
      else {
        System.out.println("COMPUTER IS BUSY\nTRY TO UNLOAD CURRENT TASK\nOR WAIT WHEN IT FINISHES");
      }
    }
  }

  @Override
  protected void unloadApplication()
  {
    if (!currentTask.isEmpty())
    {
      currentTask = "";
      disks.get(currentDisk).DeleteLast();
      System.out.println("APPLICATION UNLOADED");
    }
    else {
      System.out.println("THERES NO APPLICATION TO UNLOAD");
    }
  }

  public void runApplication(Application app)
  {
    if (currentTask.equals(disks.get(currentDisk).ReadLast()) && Objects.equals(app.getPlatform(), oskern.getKernelPlatform()))
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
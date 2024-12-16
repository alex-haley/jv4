import java.util.ArrayList;

public class FileSystem {
  private String name;
  private int maxLen;
  private int blockSize;
  private int numOfBlocks;
  private ArrayList<String> diskSpace;
  private int blockCount = 0;

  public FileSystem(String name, int maxLen, int blockSize)
  {
    this.name = name;
    this.blockSize = blockSize;
    this.maxLen = maxLen;

    numOfBlocks = maxLen / blockSize;
    diskSpace = new ArrayList<String>(numOfBlocks);
  }

  public int Find(String app)
  {
    System.out.println("TRYING TO FIND: " + app);
    int i = 0;
    for (String a : diskSpace)
    {
      if (diskSpace.get(i).equals(app))
      {
        System.out.println("FOUNDED: " + app);
        return i;
      }
    }
    return i;
  }

  public void Write(String record)
  {
    System.out.println("WRITING TO MEMORY...");
    if (blockCount <= numOfBlocks)
    {
      diskSpace.add(record);
      System.out.println("SUCCESSFULLY WRITTEN INTO 0x"+ blockCount + " MEMORY ADDRESS");
      blockCount++;
    }
    else {
      System.out.println("ERROR IN MEMORY. MEMORY IS FULL");
    }

  }

  public String Read(int blockIndex)
  {
    System.out.println("READING FROM MEMORY...");
    String answer = "";
    if (diskSpace.size() >= blockIndex)
    {
      System.out.println(diskSpace.get(blockIndex));
      System.out.println("0x"+blockIndex+" MEMORY ADDRESS READ SUCCESSFULLY");
      answer = diskSpace.get(blockIndex);
    }
    return answer;
  }

  public void Delete(int blockIndex)
  {
    System.out.println("DELETING FROM MEMORY...");
    if (diskSpace.size() >= blockIndex)
    {
      diskSpace.remove(blockIndex);
      blockCount--;
      System.out.println("SUCCESSFULLY DELETED FROM 0x"+blockIndex+" MEMORY ADDRESS");
    }
    else {
      System.out.println("PROVIDED MEMORY ADDRESS DOES NOT EXIST, ABORTING");
    }
  }

  public String ReadLast()
  {
    String answer = "";
    if (!diskSpace.isEmpty())
    {
      answer = diskSpace.getLast();
    }
    else {
      answer = "THERE IS NOTHING TO DELETE";
    }
    return answer;
  }

  public void DeleteLast()
  {
    if (!diskSpace.isEmpty())
    {
      System.out.println("DELETING FROM MEMORY...");
      diskSpace.removeLast();
      System.out.println("SUCCESSFULLY DELETED");
    }
    else {
      System.out.println("THERE IS NOTHING TO DELETE");
    }
  }

  public void getDiskSpace()
  {
    if (!diskSpace.isEmpty())
    {
      int i = 0;
      for (String s : diskSpace)
      {
        System.out.println(i + " : " + diskSpace.get(i));
        i++;
      }
    }
    else {
      System.out.println("there is no loaded apps");
    }
  }

  public int getDiskSpaceCount()
  {
    int i = 0;
    if (!diskSpace.isEmpty())
    {
      for (String s : diskSpace)
      {
        i++;
      }
    }
    else {
      System.out.println("there is no loaded apps");
    }
    return i;
  }

  public int getDiskSize()
  {
    return numOfBlocks;
  }

  public int getLastIndex()
  {
    return blockCount - 1;
  }

  public String getDiskName()
  {
    return name;
  }

}

public class Application {
  private String name;
  private String type;
  private String version;
  private String platform;
  private int ramUsage;
  private boolean hasGui;

  public Application(String name, String type, String version, String platform, int ramUsage, boolean hasGui)
  {
    this.name = name;
    this.type = type;
    this.version = version;
    this.platform = platform;
    this.ramUsage = ramUsage;
    this.hasGui = hasGui;
  }

  public void runApp()
  {
    switch(type)
    {
      case "Text Editor":
        System.out.println("text editor is working");
        break;
      case "Game":
        System.out.println("game has started");
        break;
      case "Word Processor":
        System.out.println("word processor is working");
        break;
      case "Spreadsheet":
        System.out.println("spreadsheet is open now");
        break;
      case "Browser":
        System.out.println("browser is open");
        break;
      case "Graphics Software":
        System.out.println("Silicon Graphics Is here");
        break;
      default:
        System.out.println("i dont know who i am");
    }
  }

  public String getName() {return name;}
  public int getRamUsage() {return ramUsage;}
  public String getType() {return type;}
  public String getPlatform() {return platform;}

  @Override
  public String toString()
  {
    return "Application:\t"+ name + "\nversion:\t" + version + "\nplatform:\t" + platform + "\ngui:\t" + hasGui + "\n";
  }
}

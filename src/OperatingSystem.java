public abstract class OperatingSystem {
  protected String name;
  protected String version;
  protected String architecture;
  protected Kernel oskern;

  public OperatingSystem(String name, String version, String architecture, Kernel oskern)
  {
    this.name = name;
    this.version = version;
    this.architecture = architecture;
    this.oskern = oskern;
  }

  public void displayInfo()
  {
    System.out.println("OS:\t"+name);
    System.out.println("version:\t"+version);
    System.out.println("arch:\t"+architecture);
    System.out.println("kernel:\t"+oskern.getName());
  }

  public String getName()
  {
    return name;
  }

  protected abstract void loadApplication(Application app);

  protected abstract void unloadApplication();

  protected abstract void runApplication(Application app);
}

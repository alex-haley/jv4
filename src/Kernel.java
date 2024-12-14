import java.util.ArrayList;

public class Kernel {
  private final String KernelArchitecture;
  private final boolean MemoryIsolation;
  private final String KernelLibrary;
  private final String KernelPlatform;
  private final String kernName;

  public Kernel(String kernName, String KernelArchitecture, boolean MemoryIsolation, String KernelLibrary, String KernelPlatform)
  {
    this.kernName = kernName;
    this.KernelArchitecture = KernelArchitecture;
    this.MemoryIsolation = MemoryIsolation;
    this.KernelLibrary = KernelLibrary;
    this.KernelPlatform = KernelPlatform;
  }

  public boolean getMemoryIsolation() {
    return MemoryIsolation;
  }

  public String getKernelArchitecture() {
    return KernelArchitecture;
  }
  public String getKernelPlatform() {
    return KernelPlatform;
  }
  public String getKernelLibrary() {
    return KernelLibrary;
  }
  public String getName() {
    return kernName;
  }

}

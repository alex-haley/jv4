import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    public static final Scanner scan = new Scanner(System.in);
    public static ArrayList<SingleTaskOS> SOSList = new ArrayList<>();
    public static ArrayList<Kernel> KernelList = new ArrayList<>();
    public static ArrayList<Application> AppList = new ArrayList<>();

    public static void home() {
        System.out.println("OS Manager");
        System.out.println("=========================");
        System.out.println("1. Create OS");
        System.out.println("2. Create App");
        System.out.println("3. Create Kernel");
        System.out.println("4. Load OS");
        System.out.println("5. Kernel List");
        System.out.println("6. OS List");
        System.out.println("7. App List");
        System.out.println("=========================");
        System.out.println("8. exit");

        try {
            System.out.print("\nchoose option: ");
            int ch;
            ch = scan.nextInt();
            switch (ch) {
                case 1:
                    createOS();
                    break;
                case 2:
                    createApp();
                    break;
                case 3:
                    createKernel();
                    break;
                case 4:
                    loadOS();
                    break;
                case 5:
                    listKernel();
                    break;
                case 6:
                    listOS();
                case 7:
                    listApp();
                case 8:
                    return;
                default:
                    home();
            }
        } catch (InputMismatchException i) {
            System.out.println("Invalid input");
            scan.next();
            home();
        }
    }

    public static void showInterface(SingleTaskOS os) {
        System.out.println("OS BOOTED: " + os.getName());
        System.out.println("===================");
        System.out.println("1. dir");
        System.out.println("2. load application");
        System.out.println("3. unload application");
        System.out.println("4. check disk");
        System.out.println("5. add disk");
        System.out.println("6. change disk");
        System.out.println("7. show memory");
        try {
            System.out.println("> ");
            int ch;
            ch = scan.nextInt();
            switch (ch)
            {
                case 1:
                    os.list();
                    break;
                case 2:
                    if (!AppList.isEmpty())
                    {
                        int i = 0;
                        for (Application a : AppList)
                        {
                            System.out.println(i + " : " + AppList.get(i).getName());
                            i++;
                        }
                        System.out.println(" ");
                    }
                    System.out.println("> which application?");
                    System.out.println("> ");
                case 3:
                    os.unloadApplication();
                    break;
                case 4:
                    System.out.println("Heap size: " + os.getMem());
                    System.out.println("Disk size: " + os.getDiskSize());
                    break;

            }
        } catch (InputMismatchException i) {
            System.out.println("Invalid input");
            scan.next();
            showInterface(os);
        }
    }

    public static void createOS()
    {
        try {
            System.out.println("\nOS type: ");
            System.out.println("1. SingleTask");
            System.out.println("2. MultiTask");
            int ch;
            System.out.print("choose option: ");
            ch = scan.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("name of the new OS: ");
                    String name = scan.next();
                    System.out.print("version: ");
                    String ver = scan.next();
                    System.out.print("arch: ");
                    String arch = scan.next();
                    System.out.print("max memory for task: ");
                    int tMem = scan.nextInt();
                    System.out.print("Choose kernel: ");
                    int k;
                    if (!KernelList.isEmpty())
                    {
                        int i = 0;
                        for (Kernel ke : KernelList)
                        {
                            System.out.println(i + " : " + KernelList.get(i).getName());
                            i++;
                        }
                        System.out.println(" ");
                    }
                    System.out.print("? ");
                    k = scan.nextInt();
                    Kernel kern;
                    while (true)
                    {
                        if (k < 0 || k > KernelList.size())
                            k = scan.nextInt();
                        else {
                            kern = KernelList.get(k);
                            break;
                        }
                    }
                    SingleTaskOS sos = new SingleTaskOS(name, ver, arch, kern, tMem);
                    SOSList.add(sos);
                    System.out.println("\nSingleTaskOS has been created\n");
                    home();

                case 2:
                default:
                    createOS();
            }
        } catch (InputMismatchException i) {
            System.out.println("Invalid input");
            scan.next();
            home();
        }
    }

    public static void createKernel()
    {
        try {
            System.out.print("kernel name: ");
            String name = scan.next();
            String arch;
            while (true)
            {
                System.out.println("kernel arch: ");
                System.out.println("1. Monolitic");
                System.out.println("2. Micro");
                int kernarch = scan.nextInt();
                if (kernarch == 1)
                {
                    arch = "Monolitic";
                    break;
                }
                else if (kernarch == 2)
                {
                    arch = "Micro";
                    break;
                }
            }
            System.out.print("kernel lib: ");
            String kernlib = scan.next();
            String kernplat;
            label:
            while (true)
            {
                System.out.println("kernel platform: ");
                System.out.println("1. x86");
                System.out.println("2. x86-64");
                System.out.println("3. ARM");
                int kernch = scan.nextInt();
                switch (kernch) {
                    case 1:
                        kernplat = "x86";
                        break label;
                    case 2:
                        kernplat = "x86-64";
                        break label;
                    case 3:
                        kernplat = "ARM";
                        break label;
                }
            }
            boolean memiso;
            int ch;
            while (true)
            {
                System.out.print("is there memory isolation? (0,1) ");
                ch = scan.nextInt();
                if (ch == 0)
                {
                    memiso = false;
                    break;
                }
                else if (ch == 1)
                {
                    memiso = true;
                    break;
                }
            }
            Kernel kern = new Kernel(name, arch, memiso, kernlib, kernplat);
            KernelList.add(kern);

        } catch (InputMismatchException i) {
            System.out.println("Invalid input");
            scan.next();
            createKernel();
        }
    }

    public static void loadOS()
    {
        System.out.println("\nOS type: ");
        System.out.println("1. SingleTask");
        System.out.println("2. MultiTask");
        int ch;
        System.out.print("choose option: ");
        ch = scan.nextInt();
        switch (ch)
        {
            case 1:
                if (!SOSList.isEmpty()) {
                    int i = 0;
                    for (OperatingSystem s : SOSList) {
                        System.out.println(i + " : " + SOSList.get(i).getName());
                        i++;
                    }
                    System.out.println(" ");
                    System.out.println("which OS to load?");
                    int chu;
                    SingleTaskOS sos;
                    while (true) {
                        System.out.print(": ");
                        chu = scan.nextInt();
                        if (chu > 0 || chu <= SOSList.size()) {
                            sos = SOSList.get(chu);
                            showInterface(sos);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("there is no OS created");
                    break;
                }
            case 2:
                break;
            default:
                loadOS();
        }

    }

    public static void createApp()
    {

    }

    public static void listOS()
    {
        if (!SOSList.isEmpty())
        {
            int i = 0;
            for (OperatingSystem s : SOSList)
            {
                System.out.println(i + " : " + SOSList.get(i).getName());
                i++;
            }
            System.out.println(" ");
            home();
        }
        else {
            System.out.println("\nthere is no OS created\n");
            home();
        }
    }

    public static void listApp()
    {
        if (!AppList.isEmpty())
        {
            int i = 0;
            for (Application a : AppList)
            {
                System.out.println(i + " : " + AppList.get(i).getName());
                i++;
            }
            System.out.println(" ");
            home();
        }
        System.out.println("\nthere is no app created\n");
        home();
    }

    public static void listKernel()
    {
        if (!KernelList.isEmpty())
        {
            int i = 0;
            for (Kernel k : KernelList)
            {
                System.out.println(i + " : " + KernelList.get(i).getName());
                i++;
            }
            System.out.println(" ");
            home();
        }
        else {
            System.out.println("\nThere is no kernel created\n");
            home();
        }
    }
}
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
        System.out.println("7. run application");
        System.out.println("8. poweroff");
        try {
            System.out.print("> ");
            int ch;
            ch = scan.nextInt();
            switch (ch)
            {
                case 1:
                    os.list();
                    showInterface(os);
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

                    int app;
                    Application App;
                    System.out.println("> which application?");
                    apploop:
                    while (true)
                    {
                        System.out.print("> ");
                        app = scan.nextInt();
                        if (app > -1 || app <= AppList.size())
                        {
                            App = AppList.get(app);
                            break;
                        }
                        continue apploop;
                    }

                    os.loadApplication(App);
                    showInterface(os);
                    break;
                case 3:
                    os.unloadApplication();
                    showInterface(os);
                    break;
                case 4:
                    System.out.println("Heap size: " + os.getMem());
                    System.out.println("Disk size: " + os.getDiskSize());
                    showInterface(os);
                    break;
                case 5:
                    System.out.print("NAME: ");
                    String name =  scan.next();
                    System.out.print("MAXLEN: ");
                    int maxLen = scan.nextInt();
                    System.out.print("BLOCKSIZE: ");
                    int blockSize = scan.nextInt();
                    os.addDisk(name,maxLen,blockSize);
                    System.out.println("\nNEW DISK FILESYSTEM INITIALIZED\n");
                    showInterface(os);
                    break;
                case 6:
                    os.getDisks();
                    System.out.print("(choose disk)> ");
                    int disk = -1;
                    while (true)
                    {
                        disk = scan.nextInt();
                        if (disk > -1 || disk <= os.countDisks())
                        {
                            os.changeDisk(disk);
                            break;
                        }
                    }
                    showInterface(os);
                    break;
                case 7:
                    if (!AppList.isEmpty())
                    {
                        int i = 0;
                        for (Application a : AppList)
                        {
                            System.out.println(i + " : " + AppList.get(i).getName());
                            i++;
                        }
                        System.out.println(" ");
                        System.out.print("choose app to run: ");
                        int apprun = 0;

                        apploop:
                        while (true)
                        {
                            try {
                                apprun = scan.nextInt();
                                if (apprun > 0 || apprun <= AppList.size())
                                {
                                    break;
                                }
                            } catch (InputMismatchException in) {
                                scan.next();
                                continue apploop;
                            }
                        }
                        os.runApplication(AppList.get(apprun));
                        showInterface(os);
                    }
                    else {
                        System.out.println("\nTHERE IS NO APPLICATION TO RUN\n");
                        showInterface(os);
                    }
                    break;
                case 8:
                    return;
                default:
                    showInterface(os);

            }
        } catch (InputMismatchException i) {
            System.out.println("Invalid input");
            scan.next();
            showInterface(os);
        }
    }

    public static void createOS()
    {
        System.out.println("\nOS type: ");
        System.out.println("1. SingleTask");
        System.out.println("2. MultiTask");
        int ch = 0;

        chloop:
        while (true)
        {
            try {
                System.out.print("choose option: ");
                ch = scan.nextInt();
                if (ch > 0 || ch < 3)
                {
                    break;
                }
            } catch (InputMismatchException i)
            {
                scan.next();
                continue chloop;
            }
        }

        switch (ch) {
            case 1:
                System.out.print("name of the new OS: ");
                String name = scan.next();
                System.out.print("version: ");
                String ver = scan.next();
                String arch;

                kernloop:
                while (true)
                {
                    try {
                        System.out.println("kernel arch: ");
                        System.out.println("1. Monolitic");
                        System.out.println("2. Micro");
                        int kernarch = scan.nextInt();
                        if (kernarch == 1) {
                            arch = "Monolitic";
                            break;
                        } else if (kernarch == 2) {
                            arch = "Micro";
                            break;
                        }
                    } catch (InputMismatchException i) {
                        scan.next();
                        continue kernloop;
                    }
                }

                int tMem = 0;
                mloop:
                while (true)
                {
                    try {
                        System.out.print("max memory for task: ");
                        tMem = scan.nextInt();
                    } catch (InputMismatchException i) {
                        scan.next();
                        continue mloop;
                    }
                    break;
                }

                System.out.print("Choose kernel: ");
                int k = -1;
                Kernel kern;
                if (!KernelList.isEmpty())
                {
                    int i = 0;
                    for (Kernel ke : KernelList)
                    {
                        System.out.println(i + " : " + KernelList.get(i).getName());
                        i++;
                    }
                    System.out.println(" ");
                    System.out.print("? ");
                    k = scan.nextInt();
                    while (true)
                    {
                        if (k < 0 || k > KernelList.size())
                        {
                            System.out.print("? ");
                            k = scan.nextInt();
                        }
                        else {
                            kern = KernelList.get(k);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("there is no kernel created. initializing a fast-create script...");
                    kern = new Kernel(name,arch,true,name,"x86-64");
                    KernelList.add(kern);
                }

                SingleTaskOS sos = new SingleTaskOS(name, ver, arch, kern, tMem);
                SOSList.add(sos);
                System.out.println("\nSingleTaskOS has been created\n");
                home();
                break;

            case 2:
                break;
            default:
                createOS();
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
            home();

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
                    home();
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
        System.out.print("name: ");
        String name = scan.next();
        String type = "smth";
        int ch;
        int controller = -1;

        typeloop:
        while (true)
        {
            try {
                System.out.println("1. Text Editor");
                System.out.println("2. Game");
                System.out.println("3. Word Processor");
                System.out.println("4. Spreadsheet");
                System.out.println("5. Browser");
                System.out.println("6. Graphics Software");
                System.out.print("app type: ");
                ch = scan.nextInt();
                if (ch == 1) {
                    type = "Text Editor";
                    break;
                } else if (ch == 2) {
                    type = "Game";
                    break;
                } else if (ch == 3) {
                    type = "Word Processor";
                    break;
                } else if (ch == 4) {
                    type = "Spreadsheet";
                    break;
                } else if (ch == 5) {
                    type = "Browser";
                    break;
                } else if (ch == 6) {
                    type = "Graphics Software";
                    break;
                }
            } catch (InputMismatchException i) {
                scan.next();
                continue typeloop;
            }
        }

        System.out.print("version: ");
        String version = scan.next();
        String platform;
        int plat;

        platloop:
        while (true)
        {
            try {
                System.out.println("1. x86");
                System.out.println("2. x86-64");
                System.out.println("3. ARM");
                System.out.print("choose platform: ");
                plat = scan.nextInt();
                if (plat == 1) {
                    platform = "x86";
                    break;
                } else if (plat == 2) {
                    platform = "x86-64";
                    break;
                } else if (plat == 3) {
                    platform = "ARM";
                    break;
                }
            } catch (InputMismatchException i) {
                scan.next();
                continue platloop;
            }
        }

        int ramus;
        ramloop:
        while (true)
        {
            try {
                System.out.print("ram usage: ");
                ramus = scan.nextInt();
            } catch (InputMismatchException i) {
                scan.next();
                continue ramloop;
            }
            break;
        }

        Application app = new Application(name, type, version, platform, ramus);
        AppList.add(app);
        home();
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
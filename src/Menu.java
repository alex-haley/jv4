import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    public static final Scanner scan = new Scanner(System.in);

    public static void home() {
        while (true)
        {
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
                        break;
                    case 7:
                        listApp();
                        break;
                    case 8:
                        return;
                }
            } catch (InputMismatchException i) {
                System.out.println("\nInvalid input\n");
                scan.next();
            }
        }
    }

    public static void createOS()
    {
        System.out.println("\nOS type: ");
        System.out.println("1. SingleTask");
        System.out.println("2. MultiTask");
        System.out.println("3. NetworkOS");
        int ch = 0;

        chloop:
        while (true)
        {
            try {
                System.out.print("choose option: ");
                ch = scan.nextInt();
                if (ch > -1 || ch < 4)
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
            case 0:
                return;
            case 1:
                System.out.print("name of the new OS: ");
                String name = scan.next();
                System.out.print("version: ");
                String ver = scan.next();

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
                if (!OSlib.KernelList.isEmpty())
                {
                    int i = 0;
                    for (Kernel ke : OSlib.KernelList)
                    {
                        System.out.println(i + " : " + OSlib.KernelList.get(i).getName());
                        i++;
                    }
                    System.out.println(" ");
                    System.out.print("? ");
                    k = scan.nextInt();
                    while (true)
                    {
                        if (k < 0 || k > OSlib.KernelList.size())
                        {
                            System.out.print("? ");
                            k = scan.nextInt();
                        }
                        else {
                            kern = OSlib.KernelList.get(k);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("there is no kernel created. initializing a fast-create script...");
                    kern = new Kernel(name,"Monolitic",true,name,"x86-64");
                    OSlib.KernelList.add(kern);
                }

                SingleTaskOS sos = new SingleTaskOS(name, ver, kern.getKernelArchitecture(), kern, tMem);
                sos.setOSType(0);
                OSlib.storeOS(sos);
                System.out.println("\nSingleTaskOS has been created\n");
                return;

            case 2:
                System.out.print("name of the new OS: ");
                String mname = scan.next();
                System.out.print("version: ");
                String mver = scan.next();

                int mtMem = 0;
                mloop:
                while (true)
                {
                    try {
                        System.out.print("max memory for task: ");
                        mtMem = scan.nextInt();
                    } catch (InputMismatchException i) {
                        scan.next();
                        continue mloop;
                    }
                    break;
                }

                System.out.print("Choose kernel: ");
                int mk = -1;
                Kernel mkern;
                if (!OSlib.KernelList.isEmpty())
                {
                    int i = 0;
                    for (Kernel ke : OSlib.KernelList)
                    {
                        System.out.println(i + " : " + OSlib.KernelList.get(i).getName());
                        i++;
                    }
                    System.out.println(" ");
                    System.out.print("? ");
                    mk = scan.nextInt();
                    while (true)
                    {
                        if (mk < 0 || mk > OSlib.KernelList.size())
                        {
                            System.out.print("? ");
                            mk = scan.nextInt();
                        }
                        else {
                            mkern = OSlib.KernelList.get(mk);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("there is no kernel created. initializing a fast-create script...");
                    mkern = new Kernel(mname,"Monolitic",true,mname,"x86-64");
                    OSlib.KernelList.add(mkern);
                }

                MultiTaskOS mos = new MultiTaskOS(mname, mver, mkern.getKernelArchitecture(), mkern, mtMem);
                mos.setOSType(1);
                OSlib.storeOS(mos);
                System.out.println("\nMultiTaskOS has been created\n");
                return;
            case 3:
                System.out.print("name of the new OS: ");
                String nname = scan.next();
                System.out.print("version: ");
                String nver = scan.next();

                int ntMem = 0;
                mloop:
                while (true)
                {
                    try {
                        System.out.print("max memory for task: ");
                        ntMem = scan.nextInt();
                    } catch (InputMismatchException i) {
                        scan.next();
                        continue mloop;
                    }
                    break;
                }

                boolean isAuto = true;

                System.out.print("Choose kernel: ");
                int nk = -1;
                Kernel nkern;
                if (!OSlib.KernelList.isEmpty())
                {
                    int i = 0;
                    for (Kernel ke : OSlib.KernelList)
                    {
                        System.out.println(i + " : " + OSlib.KernelList.get(i).getName());
                        i++;
                    }
                    System.out.println(" ");
                    System.out.print("? ");
                    nk = scan.nextInt();
                    while (true)
                    {
                        if (nk < 0 || nk > OSlib.KernelList.size())
                        {
                            System.out.print("? ");
                            nk = scan.nextInt();
                        }
                        else {
                            nkern = OSlib.KernelList.get(nk);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("there is no kernel created. initializing a fast-create script...");
                    nkern = new Kernel(nname,"Monolitic",true,nname,"x86-64");
                    OSlib.KernelList.add(nkern);
                }

                NetworkOS nos = new NetworkOS(nname, nver, nkern.getKernelArchitecture(), nkern, ntMem, isAuto);
                nos.setOSType(2);
                OSlib.storeOS(nos);
                System.out.println("\nNetworkOS has been created\n");
                return;
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
            OSlib.storeKern(kern);
            return;

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
        System.out.println("3. Networking");
        System.out.println("0 for exit");
        int ch;

        chos:
        while (true)
        {
            try {
                System.out.print("choose option: ");
                ch = scan.nextInt();
                switch (ch)
                {
                    case 0:
                        return;
                    case 1:
                        if (!OSlib.SOSList.isEmpty()) {
                            int i = 0;
                            for (OperatingSystem s : OSlib.SOSList) {
                                System.out.println(i + " : " + OSlib.SOSList.get(i).getName());
                                i++;
                            }
                            System.out.println(" ");
                            System.out.println("which OS to load?");
                            int chu;
                            SingleTaskOS sos;
                            sosloop:
                            while (true) {
                                try {
                                    System.out.print(": ");
                                    chu = scan.nextInt();
                                    if (chu > -1 && chu <= OSlib.SOSList.size()) {
                                        sos = OSlib.SOSList.get(chu);
                                        InternalMenu.show(sos);
                                        break;
                                    }
                                    continue sosloop;
                                } catch (InputMismatchException inp) {
                                    scan.next();
                                    continue sosloop;
                                }
                            }
                        }
                        else {
                            System.out.println("there is no OS created");
                            return;
                        }
                    case 2:
                        if (!OSlib.MOSList.isEmpty()) {
                            int i = 0;
                            for (OperatingSystem m : OSlib.MOSList) {
                                System.out.println(i + " : " + OSlib.MOSList.get(i).getName());
                                i++;
                            }
                            System.out.println(" ");
                            System.out.println("which OS to load?");
                            int chu;
                            MultiTaskOS mos;
                            mosloop:
                            while (true) {
                                try {
                                    System.out.print(": ");
                                    chu = scan.nextInt();
                                    if (chu > -1 && chu <= OSlib.MOSList.size()) {
                                        mos = OSlib.MOSList.get(chu);
                                        InternalMenu.show(mos);
                                        break;
                                    }
                                    continue mosloop;
                                } catch (InputMismatchException inp) {
                                    scan.next();
                                    continue mosloop;
                                }
                            }
                        }
                        else {
                            System.out.println("there is no OS created");
                            return;
                        }
                    case 3:
                        if (!OSlib.NOSList.isEmpty()) {
                            int i = 0;
                            for (OperatingSystem n : OSlib.NOSList) {
                                System.out.println(i + " : " + OSlib.NOSList.get(i).getName());
                                i++;
                            }
                            System.out.println(" ");
                            System.out.println("which OS to load?");
                            int chu;
                            NetworkOS nos;
                            nosloop:
                            while (true) {
                                try {
                                    System.out.print(": ");
                                    chu = scan.nextInt();
                                    if (chu > -1 && chu <= OSlib.NOSList.size()) {
                                        nos = OSlib.NOSList.get(chu);
                                        InternalMenu.show(nos);
                                        break;
                                    }
                                    continue nosloop;
                                } catch (InputMismatchException inp) {
                                    scan.next();
                                    continue nosloop;
                                }
                            }
                        }
                        else {
                            System.out.println("there is no OS created");
                            return;
                        }
                }
            } catch (InputMismatchException ixc) {
                scan.next();
                continue chos;
            }
        }
    }

    public static void createApp()
    {
        System.out.print("name: ");
        String name = scan.next();
        String type;
        int ch;

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
        OSlib.storeApp(app);
        return;
    }

    public static void listOS()
    {
        OSlib.listOS();
    }

    public static void listApp()
    {
        OSlib.listApp();
    }

    public static void listKernel()
    {
        OSlib.listKernel();
    }
}
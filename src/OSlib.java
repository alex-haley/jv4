import java.util.ArrayList;

public class OSlib {
    public static ArrayList<SingleTaskOS> SOSList = new ArrayList<>();
    public static ArrayList<MultiTaskOS> MOSList = new ArrayList<>();
    public static ArrayList<NetworkOS> NOSList = new ArrayList<>();
    public static ArrayList<Kernel> KernelList = new ArrayList<>();
    public static ArrayList<Application> AppList = new ArrayList<>();

    public static void storeOS(SingleTaskOS sos)
    {
        SOSList.add(sos);
    }

    public static void storeOS(MultiTaskOS mos)
    {
        MOSList.add(mos);
    }

    public static void storeOS(NetworkOS nos)
    {
        NOSList.add(nos);
    }

    public static void storeApp(Application app)
    {
        AppList.add(app);
    }

    public static void storeKern(Kernel kern)
    {
        KernelList.add(kern);
    }

    public static void listApp()
    {
        System.out.println(" ");
        if (!AppList.isEmpty())
        {
            int i = 0;
            for (Application a : AppList)
            {
                System.out.println(i + " : " + AppList.get(i).getName());
                i++;
            }
            System.out.println(" ");
            return;
        }
        System.out.println("\nthere is no app created\n");
        return;
    }

    public static void listKernel()
    {
        System.out.println(" ");
        if (!KernelList.isEmpty())
        {
            int i = 0;
            for (Kernel k : KernelList)
            {
                System.out.println(i + " : " + KernelList.get(i).getName());
                i++;
            }
            System.out.println(" ");
            return;
        }
        else {
            System.out.println("\nThere is no kernel created\n");
            return;
        }
    }

    public static void listOS()
    {
        System.out.println(" ");
        if (!SOSList.isEmpty())
        {
            int i = 0;
            System.out.println("SingeTaskOS:");
            for (OperatingSystem s : SOSList)
            {
                System.out.println(i + " : " + SOSList.get(i).getName());
                i++;
            }
            System.out.println(" ");

            if (!MOSList.isEmpty())
            {
                int v = 0;
                System.out.println("MultiTaskOS:");
                for (OperatingSystem m : MOSList)
                {
                    System.out.println(v + " : " + MOSList.get(v).getName());
                    v++;
                }
                System.out.println(" ");
            }

            if (!NOSList.isEmpty()) {
                int f = 0;
                System.out.println("NetworkOS:");
                for (OperatingSystem n : NOSList) {
                    System.out.println(f + " : " + NOSList.get(f).getName());
                    f++;
                }
                System.out.println(" ");
            }
            return;
        }
        else if (!MOSList.isEmpty())
        {
            int v = 0;
            System.out.println("MultiTaskOS:");
            for (OperatingSystem m : MOSList)
            {
                System.out.println(v + " : " + MOSList.get(v).getName());
                v++;
            }
            System.out.println(" ");

            if (!NOSList.isEmpty()) {
                int f = 0;
                System.out.println("NetworkOS:");
                for (OperatingSystem n : NOSList) {
                    System.out.println(f + " : " + NOSList.get(f).getName());
                    f++;
                }
                System.out.println(" ");
                return;
            }
        }
        else if (!NOSList.isEmpty())
        {
            int f = 0;
            System.out.println("NetworkOS:");
            for (OperatingSystem n : NOSList) {
                System.out.println(f + " : " + NOSList.get(f).getName());
                f++;
            }
            System.out.println(" ");
            return;
        }
        else {
            System.out.println("\nthere is no OS created\n");
            return;
        }
    }
}

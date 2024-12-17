import java.util.InputMismatchException;
import java.util.Scanner;

public class InternalMenu {
    public static final Scanner scan = new Scanner(System.in);

    public static void show(SingleTaskOS os)
    {
        while (true)
        {
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
                        break;
                    case 2:
                        if (!OSlib.AppList.isEmpty())
                        {
                            int i = 0;
                            for (Application a : OSlib.AppList)
                            {
                                System.out.println(i + " : " + OSlib.AppList.get(i).getName());
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
                            if (app > -1 || app <= OSlib.AppList.size())
                            {
                                App = OSlib.AppList.get(app);
                                break;
                            }
                            continue apploop;
                        }

                        os.loadApplication(App);
                        break;
                    case 3:
                        os.unloadApplication();
                        break;
                    case 4:
                        System.out.println("Heap size: " + os.getMem());
                        System.out.println("Disk size: " + os.getDiskSize());
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
                        break;
                    case 7:
                        if (!OSlib.AppList.isEmpty())
                        {
                            int i = 0;
                            for (Application a : OSlib.AppList)
                            {
                                System.out.println(i + " : " + OSlib.AppList.get(i).getName());
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
                                    if (apprun > 0 || apprun <= OSlib.AppList.size())
                                    {
                                        break;
                                    }
                                } catch (InputMismatchException in) {
                                    scan.next();
                                    continue apploop;
                                }
                            }
                            os.runApplication(OSlib.AppList.get(apprun));
                        }
                        else {
                            System.out.println("\nTHERE IS NO APPLICATION TO RUN\n");
                        }
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

    public static void show(MultiTaskOS os)
    {
        while (true)
        {
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
                        break;
                    case 2:
                        if (os.getDiskSize() > 0)
                        {
                            if (!OSlib.AppList.isEmpty())
                            {
                                int i = 0;
                                for (Application a : OSlib.AppList)
                                {
                                    System.out.println(i + " : " + OSlib.AppList.get(i).getName());
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
                                if (app > -1 || app <= OSlib.AppList.size())
                                {
                                    App = OSlib.AppList.get(app);
                                    break;
                                }
                                continue apploop;
                            }

                            os.loadApplication(App);
                            break;
                        }
                        else {
                            System.out.println("THERE IS NO DISK IN THE SYSTEM");
                            break;
                        }
                    case 3:
                        os.unloadApplication();
                        break;
                    case 4:
                        System.out.println("Heap size: " + os.getMem());
                        System.out.println("Disk size: " + os.getDiskSize());
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
                        break;
                    case 7:
                        os.loadedApps();
                        if (os.countSpace() > 0)
                        {
                            int apps;
                            System.out.println("> which application?");
                            apploop:
                            while (true)
                            {
                                System.out.print("> ");
                                apps = scan.nextInt();
                                if (apps > -1 || apps <= os.countSpace())
                                {
                                    break;
                                }
                                continue apploop;
                            }

                            os.runApplication(os.getApp(apps));
                            break;
                        }
                        else {
                            System.out.println("THERE IS NO DISK ATTACHED TO SYSTEM");
                            break;
                        }
                    case 8:
                        return;
                }
            } catch (InputMismatchException i) {
                System.out.println("\nInvalid input\n");
                scan.next();
            }
        }
    }

    public static void show(NetworkOS nos)
    {
        while (true)
        {
            System.out.println("OS BOOTED: " + nos.getName());
            System.out.println("===================");
            System.out.println("1. dir");
            System.out.println("2. load application");
            System.out.println("3. unload application");
            System.out.println("4. check disk");
            System.out.println("5. add disk");
            System.out.println("6. change disk");
            System.out.println("7. run application");
            System.out.println("8. connect");
            System.out.println("9. send");
            System.out.println("10. exit");
            try {
                System.out.print("> ");
                int ch;
                ch = scan.nextInt();
                switch (ch)
                {
                    case 1:
                        nos.list();
                        break;
                    case 2:
                        if (nos.getDiskSize() > 0)
                        {
                            if (!OSlib.AppList.isEmpty())
                            {
                                int i = 0;
                                for (Application a : OSlib.AppList)
                                {
                                    System.out.println(i + " : " + OSlib.AppList.get(i).getName());
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
                                if (app > -1 || app <= OSlib.AppList.size())
                                {
                                    App = OSlib.AppList.get(app);
                                    break;
                                }
                                continue apploop;
                            }

                            nos.loadApplication(App);
                            break;
                        }
                        else {
                            System.out.println("THERE IS NO DISK IN THE SYSTEM");
                            break;
                        }
                    case 3:
                        nos.unloadApplication();
                        break;
                    case 4:
                        System.out.println("Heap size: " + nos.getMem());
                        System.out.println("Disk size: " + nos.getDiskSize());
                        break;
                    case 5:
                        System.out.print("NAME: ");
                        String name =  scan.next();
                        System.out.print("MAXLEN: ");
                        int maxLen = scan.nextInt();
                        System.out.print("BLOCKSIZE: ");
                        int blockSize = scan.nextInt();
                        nos.addDisk(name,maxLen,blockSize);
                        System.out.println("\nNEW DISK FILESYSTEM INITIALIZED\n");
                        break;
                    case 6:
                        nos.getDisks();
                        System.out.print("(choose disk)> ");
                        int disk = -1;
                        while (true)
                        {
                            disk = scan.nextInt();
                            if (disk > -1 || disk <= nos.countDisks())
                            {
                                nos.changeDisk(disk);
                                break;
                            }
                        }
                        break;
                    case 7:
                        nos.loadedApps();
                        if (nos.countSpace() > 0)
                        {
                            int apps;
                            System.out.println("> which application?");
                            apploop:
                            while (true)
                            {
                                System.out.print("> ");
                                apps = scan.nextInt();
                                if (apps > -1 || apps <= nos.countSpace())
                                {
                                    break;
                                }
                                continue apploop;
                            }

                            nos.runApplication(nos.getApp(apps));
                            break;
                        }
                        else {
                            System.out.println("THERE IS NO DISK ATTACHED TO SYSTEM");
                            break;
                        }
                    case 8:
                        int sx = 0;
                        for (NetworkOS osx : OSlib.NOSList)
                        {
                            System.out.println(sx + " : " + OSlib.NOSList.get(sx).getName());
                            sx++;
                        }
                        if (!OSlib.NOSList.isEmpty())
                        {
                            int indx;
                            int apps;
                            System.out.println("> which system?");
                            apploop:
                            while (true)
                            {
                                try {
                                    System.out.print("> ");
                                    apps = scan.nextInt();
                                    if (apps > -1 && apps <= OSlib.NOSList.size()) {
                                        break;
                                    }
                                    continue apploop;
                                } catch (InputMismatchException ins) {
                                    scan.next();
                                    continue apploop;
                                }
                            }

                            nos.connect(apps);
                            break;
                        }
                        else {
                            System.out.println("THERE IS NO NETWORK OS CREATED");
                            break;
                        }
                    case 9:
                        if (nos.countTasks() > 0)
                        {
                            int dsx = 0;
                            for (NetworkOS osx : OSlib.NOSList)
                            {
                                System.out.println(dsx + " : " + OSlib.NOSList.get(dsx).getName());
                                dsx++;
                            }
                            if (!OSlib.NOSList.isEmpty()) {
                                int indx;
                                System.out.println("> which system?");
                                apploop:
                                while (true) {
                                    try {
                                        System.out.print("> ");
                                        indx = scan.nextInt();
                                        if (indx > -1 && indx <= OSlib.NOSList.size()) {
                                            break;
                                        }
                                        continue apploop;
                                    } catch (InputMismatchException ins) {
                                        scan.next();
                                        continue apploop;
                                    }
                                }

                                Application appx;
                                int chs;
                                nos.viewCurTask();
                                System.out.println("> which app to send? ");
                                sysloop:
                                while (true) {
                                    try {
                                        System.out.print("> ");
                                        chs = scan.nextInt();
                                        if (chs > -1 && chs <= nos.countTasks()) {
                                            appx = nos.getApp(chs);
                                            break;
                                        }
                                        continue sysloop;
                                    } catch (InputMismatchException ins) {
                                        scan.next();
                                        continue sysloop;
                                    }
                                }

                                nos.send(indx, appx);
                                break;
                            }
                        }
                        else {
                            System.out.println("THERE IS NOTHING TO SEND");
                            break;
                        }
                    case 10:
                        return;
                }
            } catch (InputMismatchException i) {
                System.out.println("\nInvalid input\n");
                scan.next();
            }
        }
    }
}

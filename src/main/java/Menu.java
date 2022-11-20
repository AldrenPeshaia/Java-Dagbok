public class Menu {
    //Här sker första menyn i programmet.
    public static String[] alternativ = {""};

    private static String[] firstMenu = {"1. Välj användare", "2. Skapa ny användare", "3. Avsluta programmet"};

    //Här sker andra menyn i programmet
    private static String[] secondMenu = {"1. Läs dagbok", "2. Skriv inlägg", "3. Gå tillbaka"};

    //En metod som printar ut första menyn alternativ
    public static void runFirstMenu(User user){
        System.out.println("--------------------------");
        for (String s : firstMenu){
            System.out.println(s);
        }
    }
    //Metod som printar ut andra menyn alterntiv
    public static void runSecondMenu(User user){
        System.out.println("--------------------------");
        System.out.println("Aktiv användare: " + user.getUserName());
        for (String s : secondMenu){
            System.out.println(s);
        }
    }
}

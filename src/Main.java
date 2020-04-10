import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner;
    static Drink coke;
    static Drink fanta;
    static Drink soda;
    static List<Drink> choices;

    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("Otomat makinesine hoşgeldiniz.");
        double cashAmount = getCashAmount(0.0D);
        System.out.println("Devam etmek istiyor musunuz (Yes/No)?");
        boolean decision = getDecision(cashAmount);
        if (decision) {
            System.out.println("Toplam bakiye: " + cashAmount);
        } else {
            System.exit(0);
        }

        System.out.println("istediğiniz içeceği seçiniz. Stokta bulunan ürünler: ");
        Iterator var4 = choices.iterator();

        while(var4.hasNext()) {
            Drink choice = (Drink)var4.next();
            System.out.print(choice.toString());
        }

        System.out.print("Seçtiğiniz ürünü yazınız: ");
        Drink choice = getChoice();
        boolean continueAsking = true;

        while(continueAsking) {
            if (cashAmount >= choice.getPrice()) {
                System.out.println("Lütfen " + choice.getName() + " ürünüzü alınız");
                cashAmount -= choice.getPrice();
                System.out.println("Kalan bakiye: " + cashAmount);
                System.out.print("Yeni bir ürün almak ister misiniz? (YES/NO)");
                continueAsking = getDecision(cashAmount);
                if (continueAsking) {
                    System.out.print("Seçtiğiniz ürünü yazınız: ");
                    choice = getChoice();
                }
            } else {
                System.out.print("Girmiş olduğunuz bakiye yetersizdir ");
                System.out.print("Yeni bakiye eklemek ister misiniz?  (YES/NO)");
                boolean continueDecision = getDecision(cashAmount);
                if (continueDecision) {
                    System.out.println("Lütfen yeni para girişi yapınız: ");
                    cashAmount = getCashAmount(cashAmount);
                    System.out.print("Yeni bakiye: " + cashAmount);
                } else {
                    continueAsking = false;
                }
            }
        }

        System.exit(0);
    }

    private static double getCashAmount(double currentAmount) {
        System.out.println("Kullandığınız bu otomat sadece 50KR, 1TL, 5TL, 10TL kabul eder.");
        System.out.print("Lütfen para girişini yapınız: ");
        String cash = scanner.next();
        double cash1=Double.parseDouble(cash);
        if (cash1 == 0.5 || cash1== 1 || cash1 == 5 || cash1 == 10){
            return cash1+currentAmount;
        }
        else {
            System.out.println("hatalı para girişi. Tekrar Deneyiniz");
            return  getCashAmountAgain();
        }
/*
        try {
            return Double.parseDouble(cash) + currentAmount;
        } catch (NumberFormatException var4) {
            return getCashAmountAgain();
        }*/
    }

    private static boolean getDecision(Double cashAmount) {
        String decision = scanner.next();
        if (decision.equalsIgnoreCase("YES")) {
            return true;
        } else if (decision.equalsIgnoreCase("NO")) {
            System.out.println("Vazgeçtiğiniz için para iadesi yapıalcaktır");
            System.out.println("Bizi tercih ettiğiniz için teşekkür ederiz.");
            System.out.println("Para iadesini almayı unutmayınız." + cashAmount);
            return false;
        } else {
            System.out.println("Yapmış olduğunuz seçenek anlaşılmamıştır.");
            System.out.println("Lütfen tekrar isteğinizi giriniz. (YES/NO)");
            return getDecision(cashAmount);
        }
    }

    private static Drink getChoice() {
        String choice = scanner.next();
        Iterator var1 = choices.iterator();

        Drink drink;
        while(var1.hasNext()) {
            drink = (Drink)var1.next();
            if (choice.equalsIgnoreCase(drink.getName())) {
                return drink;
            }
        }

        System.out.println("Lütfen stoklarımızda bulunan arasında bir tane içecek seçiniz: ");
        var1 = choices.iterator();

        while(var1.hasNext()) {
            drink = (Drink)var1.next();
            System.out.print(choice.toString());
        }

        System.out.print("Seçtiğiniz ürünü yazınız: ");
        return getChoice();
    }

    private static double getCashAmountAgain() {
        System.out.println("Kullandığınız bu otomat sadece 50KR, 1TL, 5TL, 10TL kabul eder.");
        System.out.println("Lütfen miktarı doğru giriniz");
        System.out.print("Lütfen para girişini yapınız: ");
        String cash = scanner.next();

        try {
            return Double.parseDouble(cash);
        } catch (NumberFormatException var2) {
            return getCashAmountAgain();
        }
    }

    static {
        scanner = new Scanner(System.in);
        coke = new Drink("Kola", 15.0D);
        fanta = new Drink("Fanta", 20.0D);
        soda = new Drink("Gazoz", 30.0D);
        choices = new ArrayList(Arrays.asList(coke, fanta, soda));
    }
}


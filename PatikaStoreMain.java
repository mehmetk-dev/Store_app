import exceptions.StoreException;
import service.CustomerService;

import java.util.Scanner;

public class PatikaStoreMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("===Mehmet Store===");
            System.out.println("1-Müşteri Kayıt");
            System.out.println("2-Müşteri giriş");
            System.out.println("0-Çıkış");
            System.out.print("Yapmak istediğiniz işlemi giriniz: ");

            String choise = scanner.nextLine();

            try {
                switch (choise) {
                    case "1":
                        saveCustomer(scanner);
                        break;
                    case "2":
                        loginCustomer(scanner);
                        break;
                    case "0":
                        System.out.println("Programdan çıkılıyor...");
                        return;
                }

            } catch (StoreException e) {
                System.out.println("\u001B[31m"+ e.getMessage() +"\u001B[0m");
            }

        }
    }

    private static void loginCustomer(Scanner scanner) throws StoreException {

        System.out.print("E-Mailinizi giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email,password);
    }

    private static void saveCustomer(Scanner scanner) throws StoreException {

        System.out.print("İsminizi giriniz: ");
        String name = scanner.nextLine();
        System.out.print("E-Mailinizi giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name,email,password);
    }
}

import service.CustomerService;

import java.util.Scanner;

public class PatikaStoreMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("===Mehmet Store===");
            System.out.println("1-Müşteri Kayıt");
            System.out.println("2-Müşteri giriş");
            System.out.println("0-Çıkış");
            System.out.print("Yapmak istediğiniz işlemi giriniz: ");

            String choise = scanner.nextLine();

            switch (choise){
                case "1":
                    saveCustomer(scanner);
                    break;
                case "2":
                    loginCustomer(scanner);
                    break;
            }

        }


    }

    private static void loginCustomer(Scanner scanner) {

        System.out.print("E-Mailinizi giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email,password);
    }

    private static void saveCustomer(Scanner scanner) {

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

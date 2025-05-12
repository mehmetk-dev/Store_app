import exceptions.StoreException;
import model.enums.Role;
import service.CustomerService;
import service.UserService;

import java.util.Scanner;

public class PatikaStore {

    private static final Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();

    public static void main(String[] args) {

        while (true) {

            getMainMenu();
            String choise = scanner.nextLine();

            try {
                switch (choise) {
                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("Programdan çıkılıyor...");
                        return;
                    default:
                        System.out.println("Geçersiz işlem!");
                }

            } catch (StoreException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }

        }
    }

    private static void getCustomerMenu() throws StoreException {

        System.out.println("=== MÜŞTERİ GİRİŞ PANELİ ===");
        System.out.println("1 - Müşteri Kaydı");
        System.out.println("2 - Giriş Yap");
        System.out.println("0 - Geri Dön");
        System.out.print("Seçim yapınız: ");

        String choise = scanner.nextLine();

        switch (choise) {
            case "1":
                registerCustomer();
                break;
            case "2":
                loginCustomer();
                break;
            case "0":
                return;
            default:
                System.out.println("Geçersiz işlem!");
        }
    }


    private static void getUserMenu() throws StoreException {
        while (true) {

            System.out.println("=== KULLANICI GİRİŞ PANELİ ===");
            System.out.println("1 - Kullanıcı Kayıt Ol");
            System.out.println("2 - Kullanıcı Giriş Yap");
            System.out.println("0 - Geri Dön");
            System.out.print("Seçim yapınız: ");

            String choise = scanner.nextLine();

            switch (choise) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem!");
            }
        }
    }

    private static void loginUser() throws StoreException {

        System.out.print("Kullanıcı Adınızı giriniz: ");
        String userName = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        userService.login(userName, password);
    }

    private static void registerUser() throws StoreException {

        System.out.print("Kullanıcı Adınızı giriniz: ");
        String userName = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();
        System.out.print("Rolünüzü giriniz:(ADMIN-SUPPORT) ");
        String roleString = scanner.nextLine().toUpperCase();

        Role role = Role.valueOf(roleString);

        userService.save(userName, password, role);
        System.out.println("Kullanıcı kaydı başarılı.");
    }

    private static void getMainMenu() {
        System.out.println("=== GİRİŞ TÜRÜNÜ SEÇİNİZ ===");
        System.out.println("1-Kullanıcı Girişi(ADMIN - SUPPORT)");
        System.out.println("2-Müşteri Girişi");
        System.out.println("0-Çıkış");
        System.out.print("Yapmak istediğiniz işlemi giriniz: ");
    }

    private static void loginCustomer() throws StoreException {

        System.out.print("E-Mailinizi giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);
    }

    private static void registerCustomer() throws StoreException {

        System.out.print("İsminizi giriniz: ");
        String name = scanner.nextLine();
        System.out.print("E-Mailinizi giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi giriniz: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name, email, password);
    }
}
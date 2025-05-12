import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Category;
import model.Product;
import model.User;
import model.enums.Role;
import service.CategoryService;
import service.CustomerService;
import service.ProductService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PatikaStore {

    private static User LOGINED_USER;
    private static final Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();

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

        User loginedUser = userService.login(userName, password);

        if (loginedUser != null && loginedUser.isActive()){
            LOGINED_USER = loginedUser;
            System.out.println("Kullanıcı ID: " + LOGINED_USER.getId());
            getLoginUserMenu();
        }else{
            throw new StoreException(ExceptionMessagesConstants.USER_IS_NOT_ACTIVE);
        }
    }

    private static void getLoginUserMenu() throws StoreException {

        while (true) {
            System.out.println("=== Kullanıcı Menüsü ===");
            System.out.println("1 - Kategori Oluştur");
            System.out.println("2 - Kategori Listele");
            System.out.println("3 - Kategori Sil");
            System.out.println("4 - Ürün Oluştur");
            System.out.println("5 - Ürün Listele");
            System.out.println("6- Ürün Sil");
            System.out.println("7 - Sipariş Listele");
            System.out.println("0-Çıkış");
            System.out.print("Yapmak istediğiniz işlemi giriniz: ");
            String choise = scanner.nextLine();

            switch (choise) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    listCategory();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    createProduct();
                    break;
                case "5":
                    listProduct();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "7":
                    listOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem!");
            }
        }
    }


    private static void createProduct() throws StoreException {
        System.out.print("Ürün ismini giriniz: ");
        String name = scanner.nextLine();
        System.out.print("Ürün fiyatını giriniz: ");
        String price = scanner.nextLine();
        System.out.print("Ürünün stok sayısını giriniz: ");
        String stock = scanner.nextLine();
        System.out.print("Kategori ID giriniz: ");
        String category_id = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(category_id));

        Product product = new Product(name,new BigDecimal(price),Integer.parseInt(stock),category);
        productService.save(product,LOGINED_USER);

    }

    private static void listProduct() {
    }

    private static void deleteProduct() {
    }

    private static void listOrder() {
    }

    private static void deleteCategory() {

        System.out.print("Kategori ID giriniz: ");
        String id = scanner.nextLine();

        categoryService.deleteById(Long.parseLong(id));
    }

    private static void listCategory() {

        List<Category> categories = categoryService.listAll();

        categories.forEach(System.out::println);

    }

    private static void createCategory() throws StoreException {
        throw new StoreException("not iplemented");
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
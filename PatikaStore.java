import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.*;
import model.enums.Role;
import service.CategoryService;
import service.CustomerService;
import service.ProductService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PatikaStore {

    private static Customer LOGINED_CUSTOMER;
    private static User LOGINED_USER;
    private static final Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();
    private static CartService cartService = new CartService();

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
            System.out.println("6 - Ürün Sil");
            System.out.println("7 - Ürün Arama");
            System.out.println("8 - Ürün Filtreleme(Kategori Bazlı)");
            System.out.println("9 - Sipariş Listele");
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
                    searchProduct();
                    break;
                case "8":
                    filterProduct();
                    break;
                case "9":
                    listOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem!");
            }
        }
    }

    private static void filterProduct() {

        System.out.print("Aradığınız kategoriyi giriniz: ");
        String searchCategory =  scanner.nextLine();

        List<Product> productList = productService.filterProductByCategoryName(searchCategory);

        System.out.println("\n=== Ürün Listesi(Filtreleme Sonucu) ===");

        productList.forEach(product ->
                System.out.printf("%s - %s  - %s\n",product.getName(),product.getPrice(),product.getCategory().getName()));

        System.out.println("=======");
    }

    private static void searchProduct() {

        System.out.print("Aradığınız ürünün adını giriniz: ");
        String searchProductName =  scanner.nextLine();

        List<Product> productList = productService.searchProduct(searchProductName);

        System.out.println("\n=== Ürün Listesi(Arama Sonucu) ===");

        productList.forEach(product ->
                System.out.printf("%s - %s  - %s\n",product.getName(),product.getPrice(),product.getCategory().getName()));
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

    private static void listProduct() throws StoreException {
        int totalPage = productService.getTotalPage();

        int page = 1;

        do {
        List<Product> products = productService.listAll(page);

        System.out.println("=== Ürün Listesi (Sayfa " + page + "/" + totalPage + ") ===");
        products.forEach(product ->
                System.out.printf("Ürün ID: %s - Ürün: %s - Fiyat: %s - Stok: %s -  Kategori: %s\n\n",
                        product.getId(),product.getName(),product.getPrice(),product.getStock(),product.getCategory().getName()));
        System.out.print("Sonraki sayfa sayısı: ");
        String pageStr  = scanner.nextLine();
        page = Integer.parseInt(pageStr);
        if (page <= 0){
            throw new StoreException(ExceptionMessagesConstants.WRONG_PAGE_NUMBER);
        }

        }while(page <= totalPage);

    }

    private static void deleteProduct() {

        System.out.print("Silmek istediğiniz ürün ID giriniz: ");
        String id = scanner.nextLine();
        productService.deleteByID(Long.parseLong(id));
    }

    private static void listOrder() {
    }

    private static void deleteCategory() {

        System.out.print("Silmek istediğiniz kategori ID giriniz: ");
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
        Customer customer = customerService.login(email, password);
        LOGINED_CUSTOMER = customer;

        while(true){

            System.out.println("1 - Ürün Listele");
            System.out.println("2 - Ürün Arama");
            System.out.println("3 - Ürün Filtreleme(Kategori Bazlı)");
            System.out.println("4 - Sepete Ürün Ekle");
            System.out.println("5 - Sepeti Görüntüle");
            System.out.println("6 - Sepet Temizle");
            System.out.println("7 - Sipariş Listele");
            System.out.println("0 - Çıkış");
            System.out.print("Seçim Yapınız: ");
            String choise = scanner.nextLine();

            switch (choise){
                case "1":
                    listProduct();
                    break;
                case "2":
                    searchProduct();
                    break;
                case "3":
                    filterProduct();
                    break;
                case "4":
                    addProductToCard();
                    break;
                case "5":
                    listCart();
                    break;
                case "6":
                    clearCart();
                    break;
                case "7":
                    listOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem...");
            }

        }
    }

    private static void clearCart() {
    }

    private static void listCart() {

        List<Cart> carts = cartService.getAll(LOGINED_CUSTOMER);

        System.out.println("\n=== Sepetteki Ürün Listesi ===");

        carts.forEach(cart ->
                System.out.printf("%s - %s₺  - %s\n",cart.getItems().get(0).getProduct().getName(),cart.getTotalAmount(),cart.getQuantity()));
    }

    private static void addProductToCard() throws StoreException {

        boolean isContinue = true;

        while (isContinue){

        System.out.print("Ürün adı giriniz: ");
        String productName = scanner.nextLine();

        Product product = productService.getByName(productName);

        if (product == null){
            System.out.println("Ürün bulunamadı.");
        }else{
            System.out.print("Adet giriniz: ");
            int quantity = scanner.nextInt();

            if (product.getStock() < quantity){
                throw new StoreException(ExceptionMessagesConstants.PRODUCT_STOCK_IS_NOT_VALID);
            }

            scanner.nextLine();
            cartService.addToCard(LOGINED_CUSTOMER,product,quantity);

            System.out.print("Sepete ürün eklemeye devam etmek istiyor musunuz ?(E/H)");
            String yesNo = scanner.nextLine();

            if (!"E".equalsIgnoreCase(yesNo)){
                isContinue = false;
            }
        }
        }
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
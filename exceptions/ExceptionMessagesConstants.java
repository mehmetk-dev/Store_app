package exceptions;

public class ExceptionMessagesConstants {


    private ExceptionMessagesConstants() {
    }

    public static final String CUSTOMER_EMAIL_ALLREADY_EXIST = "Müşteri emaili zaten kayıtlı.";

    public static final String CUSTOMER_EMAIL_DOES_NOT_EXIST = "Girilen email ile ilgili müşteri bulunamamıştır.";

    public static final String CUSTOMER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH = "Girilen email adı veya şifre yanlış.";

    public static final String USER_EMAIL_ALLREADY_EXIST = "Kullanıcı zaten kayıtlı.";

    public static final String USER_EMAIL_DOES_NOT_EXIST = "Girilen kullanıcı adıyla ile ilgili kullanıcı bulunamamıştır.";

    public static final String USER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH = "Girilen kullanıcı adı veya şifre yanlış.";

    public static final String USER_IS_NOT_ADMIN = "Giriş yapan kullanıcı ADMIN rolüne sahip değildir.";

    public static final String USER_IS_NOT_ACTIVE = "Kullanıcı aktif değil ya da bulunamadı!";

    public static final String CATEGORY_NOT_FOUND = "Kategori bulunamadı.";
}

package dao.constants;

public class SqlScriptConstants {

    private SqlScriptConstants() {

    }

    public static final String CUSTOMER_SAVE = """
            INSERT INTO customer(name,email,password) VALUES(?,?,?);
            """;

    public static  final String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer WHERE id = ?;
            """;

    public static  final String CUSTOMER_FIND_ALL = """
            SELECT * FROM customer;
            """;

    public static  final String CUSTOMER_FIND_BY_EMAIL = """
            SELECT * FROM customer WHERE email = ?;
            """;

    public static final String ORDER_SAVE = """
            INSERT INTO \"order\"(customer_id,order_date,total_amount) VALUES(?,?,?);
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment(amount,order_id,payment_method) VALUES(?,?,?);
            """;

    public static final String PRODUCT_FIND_ALL = """
            SELECT  p.id as id,
                    p.name as name,
                    p.price as price,
                    p.stock as stock,
                    c.id as category_id,
                    c.name as category_name
            FROM    product p, 
                    category c 
            WHERE p.category_id = c.id
            ORDER BY p.id asc
            LIMIT ? OFFSET ?;
            """;

    public static final String PRODUCT_SEARCH_BY_NAME = """
                        SELECT  p.id as id,
                                p.name as name,
                                p.price as price,
                                p.stock as stock,
                                c.id as category_id,
                                c.name as category_name
                        FROM    product p
                            LEFT JOIN public.category c on c.id = p.category_id
                        WHERE p.name ILIKE ?

            """;

    public static final String PRODUCT_FIND_BY_CATEGORY_NAME = """
            SELECT              p.id as id,
                                p.name as name,
                                p.price as price,
                                p.stock as stock,
                                c.id as category_id,
                                c.name as category_name
             FROM product p
            join category c on c.id = p.category_id
            where c.name ilike ?
            """;

    public static final String PRODUCT_DELETE_BY_ID = """
            DELETE FROM product WHERE id = ?;
            """;

    public static final String PRODUCT_TOTAL_PAGE_COUNT = """
            SELECT COUNT(*) FROM product;
            """;

    public static final String PRODUCT_SAVE = """
            INSERT INTO product(name,price,stock,updated_by,created_by,category_id)
            VALUES(?,?,?,?,?,?)
            """;

    public static final String USER_SAVE = """
            INSERT INTO users(username,password,role,active) VALUES(?,?,?,?);
            """;

    public static final String USER_FIND_BY_USERNAME = """
            SELECT * FROM users WHERE username = ?;
            """;

    public static final String CATEGORY_SAVE = """
            INSERT INTO category(name,created_by,updated_by)
            VALUES(?,?,?);
            """;

    public static final String CATEGORY_DELETE_BY_ID = """
            DELETE FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_BY_ID = """
            SELECT * FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_ALL = """
            SELECT * FROM category;
            """;
}

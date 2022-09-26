package com.bercomic.youngculture.constants;

public class QueryConstant {
    public static final String ALL_PRODUCTS = "from Product";
    public static final String PRODUCT_BY_CATEGORY = "from Product p where p.category.code = (select c.code from Category c where c.name=:name)";
    public static final String PRODUCT_BY_ID =  "from Product p where p.id = :id";
    public static final String CART_BY_USER_ID =  "from Cart c where c.user.id=:userid";
    public static final String DELETE_CART_BY_ID = "DELETE from Cart c where c.id=:id";
    public static final String ALL_CATEGORY = "from Category";
    public static final String GET_CATEGORY_BY_NAME = "from Category u where u.name=:name";
}

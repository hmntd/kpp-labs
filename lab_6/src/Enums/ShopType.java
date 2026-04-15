package Enums;

public enum ShopType {
    SMALL_STORE(1),
    SUPERMARKET(5);

    private final int departmentCount;

    ShopType(int count) {
        this.departmentCount = count;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }
}

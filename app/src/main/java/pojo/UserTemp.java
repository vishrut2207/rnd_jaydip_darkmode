package pojo;

public class UserTemp {

    public static UseDatabaseHelper getUseDatabaseHelper() {
        return useDatabaseHelper;
    }

    public static void setUseDatabaseHelper(UseDatabaseHelper useDatabaseHelper) {
        UserTemp.useDatabaseHelper = useDatabaseHelper;
    }

    public static UseDatabaseHelper useDatabaseHelper;
}

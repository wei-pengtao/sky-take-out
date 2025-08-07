package com.sky.shop.context;

public class BaseContext {
    private static final ThreadLocal<Long> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(Long userId) {
        currentUser.set(userId);
    }

    public static Long getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}

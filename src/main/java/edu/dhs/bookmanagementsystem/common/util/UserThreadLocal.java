package edu.dhs.bookmanagementsystem.common.util;

import edu.dhs.bookmanagementsystem.entity.User;

/**
 * @program: book-management-system
 * @description:
 * @author: Zhenlong Yang
 * @create: 2023-04-04 00:32
 **/

public class UserThreadLocal {
    private static final ThreadLocal<User> local = new ThreadLocal<>();

    public static void setUser(User user) {
        if (local.get() != null) {
            local.remove();
        }
        local.set(user);
    }

    public static User getUser() {
        return local.get();
    }

    public static void cleanUser() {
        if (local.get() != null) {
            local.remove();
        }
    }

}

package app;

import core.Application;
import core.ApplicationContext;

public class MainTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = Application.run("app");


        Object o = context.getObject(UserService.class);
        Object o1 = context.getObject(UserService.class);

        System.out.println(o == o1);


    }
}

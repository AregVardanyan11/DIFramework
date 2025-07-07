package core;

import core.container.BeanFactory;
import core.container.BeanRegistry;
import core.container.DIContainer;

public class Application {

    public static ApplicationContext run(String packageToScan) throws Exception {
        DIContainer container = new DIContainer(packageToScan);
        ApplicationContext context = new ApplicationContext(container);
        return context;
    }
}

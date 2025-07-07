package core;

import core.annotations.Bean;
import core.annotations.Configuration;
import core.container.*;
import core.scanner.ClassPathScanner;

import java.util.Set;

public class ApplicationContext {

    private final DIContainer container;

    public ApplicationContext(DIContainer container) throws Exception {
        this.container = container;
    }

    @SuppressWarnings("unchecked")
    public <T> Object getObject(Class<T> cls) {
        return container.getBean(cls);
    }
}

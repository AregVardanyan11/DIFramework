package core.container;

import core.annotations.Bean;
import core.annotations.Configuration;
import core.scanner.ClassPathScanner;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * DIContainer is the central entry point of a simple dependency injection framework.
 * It is responsible for managing the lifecycle of beans and their dependencies within the application.
 * The container scans the provided base package for annotated classes, registers them, and
 * provides a way to retrieve instances of these classes with their dependencies resolved.
 *
 * The lifecycle of the DIContainer involves:
 * - Using the ClassPathScanner to discover classes annotated with dependency injection annotations
 *   (e.g., @Component or @Configuration) in the specified package.
 * - Creating BeanDefinitions for discovered classes using a BeanDefinitionFactory.
 * - Registering BeanDefinitions in the BeanRegistry.
 * - Utilizing the BeanFactory to manage creation and lifecycle of the beans.
 *
 * Constructor:
 * - Initializes the container by scanning the classpath for annotated classes in the given base package
 *   and setting up the bean factory with the registered beans.
 *
 * Methods:
 * - getBean(Class<T>): Retrieves an instance of the specified class type from the container.
 *   If the class is not found or cannot be resolved, an exception may be thrown.
 *
 * Usage of this container allows simplified dependency management, reducing the need for manual object instantiation
 * and wiring while providing a centralized location for configuring and resolving dependencies.
 */
public class DIContainer {

    private final BeanFactory factory;
    private final BeanRegistry registry;

    public DIContainer(String basePackage) throws  Exception {
        ClassPathScanner scanner = new ClassPathScanner();
        Set<Class<?>> discovered = scanner.scan(basePackage);

        registry = new BeanRegistry();
        for (Class<?> clazz : discovered) {
            BeanDefinition def = BeanDefinitionFactory.fromClass(clazz);
            registry.register(clazz, def);

            if (clazz.isAnnotationPresent(Configuration.class)) {
                Object configInstance = clazz.getDeclaredConstructor().newInstance();

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Bean.class)) {
                        BeanDefinition beanDef = BeanDefinitionFactory.fromBeanMethod(clazz, method);
                        registry.register(method.getReturnType(), beanDef);

                        if (!beanDef.getQualifier().isEmpty()) {
                            registry.registerAlias(beanDef.getQualifier(), method.getReturnType());
                        }

                        beanDef.setFactoryInstance(configInstance);
                    }
                }
            }


        }


        this.factory = new BeanFactory(registry);
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(factory.getBean(clazz));
    }
}

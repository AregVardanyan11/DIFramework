package core.scanner;

import core.annotations.Component;
import core.annotations.Configuration;
import org.reflections.Reflections;
import java.util.HashSet;
import java.util.Set;

public class ClassPathScanner {

    /**
     * Scans the given base package and returns classes annotated with
     * @Component or @Configuration annotations.
     * 
     * @param basePackage base package to scan
     * @return Set of annotated classes
     */
    public Set<Class<?>> scan(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        Set<Class<?>> configurations = reflections.getTypesAnnotatedWith(Configuration.class);

        Set<Class<?>> result = new HashSet<>();
        result.addAll(components);
        result.addAll(configurations);

        return result;
    }


}

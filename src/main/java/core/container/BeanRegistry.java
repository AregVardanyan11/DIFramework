package core.container;

import core.annotations.Bean;
import core.annotations.Configuration;

import java.util.*;

/**
 * The BeanRegistry class serves as a registry for managing bean definitions in a dependency injection framework.
 * It allows for registering, retrieving, and managing bean definitions, either by their type or by a unique qualifier.
 * This class plays a core role in maintaining the metadata and associations needed for resolving and creating beans.
 *
 * Responsibilities:
 * - Registering bean definitions by their class type.
 * - Registering bean definitions by an optional qualifier.
 * - Providing methods to retrieve bean definitions by class type or qualifier.
 * - Supporting aliasing, where a qualifier can be associated with a bean definition of a specific type.
 * - Checking if a bean definition exists by type or qualifier.
 *
 * Methods:
 * - register(Class<?> clazz, BeanDefinition def):
 *   Adds a new bean definition to the registry, associating it with both its class type and, if provided, its qualifier.
 * - registerAlias(String name, Class<?> type):
 *   Creates an alias for a bean definition, assigning it a unique name that can be used for retrieval.
 *   Throws IllegalArgumentException if the type is not already registered.
 * - getByType(Class<?> clazz):
 *   Retrieves the bean definition associated with the specified class type. Returns null if not found.
 * - getByQualifier(String name):
 *   Retrieves the bean definition associated with the specified qualifier. Returns null if not found.
 * - containsType(Class<?> clazz):
 *   Checks if a bean definition exists for the specified class type.
 * - containsQualifier(String name):
 *   Checks if a bean definition exists for the specified qualifier.
 */
public class BeanRegistry {

    private final Map<Class<?>, BeanDefinition> definitionsByType = new HashMap<>();
    private final Map<String, BeanDefinition> definitionsByName = new HashMap<>();

    public void register(Class<?> clazz, BeanDefinition def) {
        definitionsByType.put(clazz, def);
        if (!def.getQualifier().isEmpty()) {
            definitionsByName.put(def.getQualifier(), def);
        }
    }

    public void registerAlias(String name, Class<?> type) {
        BeanDefinition def = definitionsByType.get(type);
        if (def != null) {
            definitionsByName.put(name, def);
        } else {
            throw new IllegalArgumentException("No bean definition for class: " + type);
        }
    }

    public BeanDefinition getByType(Class<?> clazz) {
        return definitionsByType.get(clazz);
    }

    public BeanDefinition getByQualifier(String name) {
        return definitionsByName.get(name);
    }

    public boolean containsType(Class<?> clazz) {
        return definitionsByType.containsKey(clazz);
    }

    public boolean containsQualifier(String name) {
        return definitionsByName.containsKey(name);
    }

    public List<BeanDefinition> getAllDefinitions() {
        return new ArrayList<>(definitionsByType.values());
    }

}

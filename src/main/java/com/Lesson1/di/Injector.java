package com.Lesson1.di;

import com.google.common.reflect.ClassPath;

import com.Lesson1.di.Annotations.AutoInjectable;
import com.Lesson1.di.Annotations.Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Configuration
public class Injector {
    /**
     * Do injection of fields using java reflection
     * @param object which we need to inject fields
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public <T> T inject(T object) throws IllegalAccessException, InstantiationException, IOException {
        ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        for (Field field : object.getClass().getDeclaredFields()) {

            if (field.isAnnotationPresent(AutoInjectable.class)) {
                field.setAccessible(true);
                List allInstances = findPackages(field, classPath);
                if (Collection.class.isAssignableFrom(field.getType())) {
                    field.set(object, allInstances);
                } else if (allInstances.size() == 1)
                    field.set(object, allInstances);
                else
                    throw new InstantiationException("Anything went wrong on injection: " + field.getType().getName());
            }
        }
        return object;
    }

    /**
     * Find all packages for creating instances
     * @param field
     * @param classPath
     * @return A list of instances
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List findPackages(Field field, ClassPath classPath) throws IllegalAccessException, InstantiationException {
        List<Object> classInstances = new ArrayList<>();
        for (String pkg : Injector.class.getAnnotation(Configuration.class).packages()) {
            Set<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive(pkg);
            Class clazz = field.getAnnotation(AutoInjectable.class).clazz();

            for (ClassPath.ClassInfo ci : allClasses) {
                if (clazz.isAssignableFrom(ci.load()) && !ci.load().isInterface()) {
                    classInstances.add(ci.load().newInstance());
                }
            }
        }
        return classInstances;
    }
}




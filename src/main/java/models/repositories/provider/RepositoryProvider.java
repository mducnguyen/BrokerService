package models.repositories.provider;

import models.repositories.BrokersRepository;
import models.repositories.IBrokersRepository;
import models.repositories.IRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class RepositoryProvider {

    private static Map<Class, IRepository> repositoryMap = new HashMap<>();

    public static void register(Class repositoryClass, IRepository repository) {
        repositoryMap.put(repositoryClass,repository);
    }

    public static IRepository provide(Class repositoryClass) {
        return repositoryMap.get(repositoryClass);
    }
}

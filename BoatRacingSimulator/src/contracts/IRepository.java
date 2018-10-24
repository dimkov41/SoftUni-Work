package src.contracts;

import src.exeptions.DuplicateModelException;
import src.exeptions.NonExistantModelException;

public interface IRepository<T extends IModelable> {
    void Add(T item) throws DuplicateModelException;

    T GetItem(String model) throws NonExistantModelException;
}

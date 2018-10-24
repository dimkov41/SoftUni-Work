package src.database;

import src.Utility.Constants;
import src.contracts.IModelable;
import src.contracts.IRepository;
import src.exeptions.DuplicateModelException;
import src.exeptions.NonExistantModelException;

import java.util.HashMap;
import java.util.Map;

public class Repository<T extends IModelable> implements IRepository {
    private Map<String, T> itemsByModel;

    public Repository()
    {
        this.itemsByModel = new HashMap<>();
    }

    protected void setItemsByModel(HashMap<String, T> itemsByModel) {
        this.itemsByModel = itemsByModel;
    }

    @Override
    public void Add(IModelable item) throws DuplicateModelException {
        if (this.itemsByModel.containsKey(item.getModel()))
        {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }

        this.itemsByModel.put(item.getModel(), (T) item);

    }

    @Override
    public T GetItem(String model) throws NonExistantModelException {
        if (!this.itemsByModel.containsKey(model))
        {
            throw new NonExistantModelException(Constants.NonExistantModelMessage);
        }

        return this.itemsByModel.get(model);
    }

}

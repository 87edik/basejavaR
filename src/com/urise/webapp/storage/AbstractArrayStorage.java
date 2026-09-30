package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected final static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage,  0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    protected void doUpdate(Resume r, Integer index){
        storage[index] = r;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size)) ;
    }


    protected void doSave(Resume r, Integer index){
        if(size==STORAGE_LIMIT){
            throw new StorageException(r.getUuid(), "Starage overflow");
        } else {
            insertElement(r, index);
            size++;
        }
    }




    @Override
    public void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume doGet(Integer index) {

        return storage[index];
    }
    @Override
    protected  boolean isExist(Integer index){
        return index >=0;
    }

    protected abstract void fillDeletedElement(int index);
    protected abstract void insertElement(Resume r, int index);
    protected abstract Integer getSearchKey(String uuid);



}

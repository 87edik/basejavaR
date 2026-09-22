package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;



    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if(index>=0){

            throw new ExistStorageException(r.getUuid());

        } else if(size==STORAGE_LIMIT){
            throw new StorageException(r.getUuid(), "Starage overflow");
        } else {
            insertElement(r, index);
            size++;
        }



    }
    protected abstract void insertElement(Resume r, int index);


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index<0){

            throw new NotExistStorageException(uuid);
        }
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
        return;


    }
    protected abstract void fillDeletedElement(int index);


    public void clear() {
        Arrays.fill(storage,  0, size, null);

        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index<0){
            throw new NotExistStorageException(r.getUuid());
        }

        storage[index] = r;
        return;
    }


    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if(index<0){
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }


    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    public int size() {
        return size;
    }
}

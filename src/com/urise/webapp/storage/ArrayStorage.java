package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    public void clear() {
        Arrays.fill(storage,  0, size, null);

        size = 0;
    }
    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index==-1){
            System.out.println("Resume "+r.getUuid()+" not exist");
            return;
        }

        storage[index] = r;
        return;
    }

    @Override
    public void save(Resume r) {

        if(getIndex(r.getUuid())>=0){

            System.out.println("Resume "+r.getUuid()+"already exist");

        } else if(size==STORAGE_LIMIT){
            System.out.println("Starage overflow");
        } else {
            storage[size] = r;
            size++;
        }



    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index==-1){

            System.out.println("Resume "+uuid+" not exist");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
        return;


    }


    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
    @Override
    protected int getIndex(String uuid){
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

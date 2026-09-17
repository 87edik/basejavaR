package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public Resume get(String uuid) {
        return null;
    }

    protected abstract int getIndex(String uuid);

    public int size() {
        return size;
    }
}

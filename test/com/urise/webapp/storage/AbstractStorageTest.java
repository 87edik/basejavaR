package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.urise.webapp.TestData.*;
import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;



    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();

        storage.save(R1);
        storage.save(R2);
        storage.save(R3);


    }

    @Test
    public void save() {
        storage.save(R4);
        assertEquals(4, storage.size());
        assertGet(R4);

    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);

    }



    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("Dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        R1.setContact(ContactType.MAIL, "mail@goole.com");
        R1.setContact(ContactType.SKYPE, "New Skype");
        R1.setContact(ContactType.MOBILE, "+7 921 222-22-22");

        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("Dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());

        List<Resume> sortedResumes = Arrays.asList(R1, R2, R3);
        Collections.sort(sortedResumes);
        assertEquals(sortedResumes, list);

    }

    @Test
    public void size() {

        assertSize(3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}
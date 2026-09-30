package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail@mail.ru");
        R1.addContact(ContactType.PHONE, "1111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievenent11", "Achievenent12", "Achievenent13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization(
                        "Organization11", "http://Organization11.ru",
                        new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")
                        )
        ));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization(
                        "Institute", null,
                        new Organization.Position(1996, Month.JANUARY,2000, Month.DECEMBER, "aspirant", null),
                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")
                )
        ));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "2222");
        R2.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization(
                        "Organization2", "http://Organization2.ru",
                        new Organization.Position(2015, Month.JANUARY, "position1", "content1")

                )
        ));
    }


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
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
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
        assertEquals(list, Arrays.asList(R1, R2, R3));

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
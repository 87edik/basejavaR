package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.storage.serializer.XmlStreamSerializer;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}
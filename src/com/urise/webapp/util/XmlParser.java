package com.urise.webapp.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.Reader;
import java.io.Writer;

public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class<?>... classesToBeBound) { // Добавлен wild card <?>
        try {
            // Опечатка исправлена: newInstance вместо newInstanse
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);

            marshaller = ctx.createMarshaller();

            // Опечатка исправлена: JAXB_FORMATTED_OUTPUT вместо JAXB_FOMATED_OUTPUT
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            unmarshaller = ctx.createUnmarshaller();

        } catch (JAXBException e) { // Опечатка исправлена: JAXBException вместо JAXBExcepton
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("unchecked") // Подавляет предупреждение о приведении типов
    public <T> T unmarshall(Reader reader) {
        try {
            // Опечатка исправлена: JAXBException
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    // Опечатка исправлена: instance вместо instanse
    public void marshall(Object instance, Writer writer) {
        try {
            // Опечатка исправлена: JAXBException
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}

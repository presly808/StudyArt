package ua.artcode.utils;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.model.CodingBatTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Razer on 30.10.15.
 */
public class AppDataJsonSerializer {

    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public AppDataJsonSerializer(Class... classes) {

        try {
            jaxbContext = JAXBContext.newInstance(classes);

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public <T> void save(Collection<T> collection, String path) {
        try {
            marshaller.marshal(collection, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public <T> Collection<T> load(Class<T> tClass, String path) {
        Collection<T> codingBatTasks = null;
        try {
            if (FileUtils.isFile(path)) {
                codingBatTasks = (Collection<T>) unmarshaller.unmarshal(new File(path));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return codingBatTasks;
    }
}


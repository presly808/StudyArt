package ua.artcode.utils;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;


public class AppDataJsonSerializer {

    private static final Logger LOG = Logger.getLogger(AppDataJsonSerializer.class);

    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public AppDataJsonSerializer(Class... classes) {

        LOG.trace("init jaxb context, marshaller, unmarshaller");

        try {
            jaxbContext = JAXBContext.newInstance(classes);

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);

        } catch (JAXBException e) {
            LOG.error(e);
        }

    }

    public <T> void save(Collection<T> collection, String path) {
        LOG.trace("save entities into file " + path);
        try {
            marshaller.marshal(collection, new File(path));
        } catch (JAXBException e) {
            LOG.error(e);
        }
    }


    public <T> Collection<T> load(Class<T> tClass, String path) {
        LOG.trace("load entities from file " + path);
        Collection<T> codingBatTasks = null;
        try {
            if (FileUtils.exists(path)) {
                codingBatTasks = (Collection<T>) unmarshaller.unmarshal(new File(path));
            }
        } catch (JAXBException e) {
            LOG.error(e);
        }

        return codingBatTasks;
    }
}


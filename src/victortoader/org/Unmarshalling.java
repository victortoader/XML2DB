package victortoader.org;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by Vic on 2/8/2016.
 */
public class Unmarshalling {


    public static JAXBUser DoIt(String path) {

        JAXBUser jaxbUser = null;
        try {


            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(JAXBUser.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUser = (JAXBUser) jaxbUnmarshaller.unmarshal(file);

            System.out.println("Unmarshalling user name: " + jaxbUser.getName());
            System.out.println("Unmarshalling user message: " + jaxbUser.getMessage());

        } catch (JAXBException e) {
            e.printStackTrace();

        }
        return jaxbUser;
    }
}

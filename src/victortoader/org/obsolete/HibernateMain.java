package victortoader.org.obsolete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import victortoader.org.Unmarshalling;
import victortoader.org.User;


public class HibernateMain {

    Unmarshalling decodeuser;

    public void Do(User decodeuser) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session ss = sf.openSession();
        ss.beginTransaction();


        User user1 = new User();
        user1.setUserName(decodeuser.getUserName());
        user1.setUserMessage(decodeuser.getUserMessage());

        User user2 = new User();
        user1.setUserName(decodeuser.getUserName());
        user2.setUserMessage(user2.getUserMessage());


        //saving objects to session
        ss.save(user1);
        ss.save(user2);
        ss.getTransaction().commit();
        ss.flush();
        ss.clear();
        ss.close();
    }


    public static void main(String[] args) {

//        try {
//            DecodeUser();
//        }catch (JAXBException e){}
//        System.out.print("Jaxb Exception");
//


//        SessionFactory sf = new Configuration().configure().buildSessionFactory();
//        Session ss = sf.openSession();
//        ss.beginTransaction();
//
//
//        User user1 = new User();
//        user1.setUserName("aaa");
//        user1.setUserMessage("mesaj");
//
//        User user2 = new User();
//        user2.setUserName(user2.getUserName());
//        user2.setUserMessage(user2.getUserMessage());
//
//
//        //saving objects to session
//        ss.save(user1);
//        ss.save(user2);
//        ss.getTransaction().commit();
//        ss.flush();
//        ss.clear();
//        ss.close();


    }

}



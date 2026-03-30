packaage com.examly.service;

import com.examly.entity.Contact;

import java.util.List;

public interface ContactService {
     boolean addContact(Contact c);
     boolean updateContact(Contact c);
     boolean deleteContact(int id);
     Contact getContactById(int id);
     List<Contact> getAllContacts();
     List<Contact> searchByName(String name);
     List<Contact> filterByBlocked(boolean blocked);
}

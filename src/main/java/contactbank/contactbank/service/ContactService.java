package contactbank.contactbank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import contactbank.contactbank.model.Contact;

/**
 * ContactService
 */
public interface ContactService {

    List<Contact> getAllContacts();

    void saveContact(Contact contact);

    Contact getContactById(long id);

    void deleteContactById(long id);

    Page<Contact> findPaginated(int pageNo, int pageSize, String SortField, String sortDirection);
}
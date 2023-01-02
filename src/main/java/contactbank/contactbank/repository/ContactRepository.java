package contactbank.contactbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import contactbank.contactbank.model.Contact;

// @Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}

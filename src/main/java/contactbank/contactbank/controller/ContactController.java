package contactbank.contactbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import contactbank.contactbank.model.Contact;
import contactbank.contactbank.service.ContactService;

/**
 * ContactController
 */
@Controller
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewContactForm")
    public String showNewContactForm(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "new_contact";
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "update_contact";
    }

    @GetMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable(value = "id") long id) {
        this.contactService.deleteContactById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;
        Page<Contact> page = this.contactService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Contact> listContacts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listContacts", listContacts);
        return "index";
    }
}
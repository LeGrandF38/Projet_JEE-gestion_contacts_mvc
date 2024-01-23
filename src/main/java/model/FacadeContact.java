package model;

import java.util.List;

public interface FacadeContact {
    public void createContact(Contact c);
    public void deleteContact(int id);
    public void updateContact(Contact newc);
    public List<Contact> findAll();
    public int [] getAllIdContacts();
    public Contact getById(int id);
    public List<Contact> searchContacts(String keyword);
}

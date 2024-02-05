package model;

import java.util.List;

public interface FacadeContact {
    public void createContact(Contact c);
    public void deleteContact(int id);
    public void updateContact(Contact newc);
    public List<Contact> findAll();
    public int [] getAllId_Contacts();
    public Contact getById(int id);

}

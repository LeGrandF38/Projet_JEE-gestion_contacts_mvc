package model;

public class test {
public static void main(String []args){
    System.out.println("test rechercher");
    Contact dao=new Contact();
    for( Contact c:dao.findAll())
        System.out.println(c);
    System.out.println("Test ajout");
    Contact contact1 = new Contact(1, "John", "Doe", "john.doe@email.com", "123456789", "123 Main St");
    Contact contact2 = new Contact(2, "Jane", "Doe", "jane.doe@email.com", "987654321", "456 Oak St");
    dao.createContact(contact1);
    //System.out.println("Test suppression");
    //dao.deleteContact(1);




}


}

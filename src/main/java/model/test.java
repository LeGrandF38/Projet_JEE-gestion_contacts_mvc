package model;

public class test {
public static void main(String []args){
    Contact dao=new Contact();
    for( Contact c:dao.findAll())
        System.out.println(c);

}
}

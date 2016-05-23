package ua.artcode.experimental.future.core.v1;

// todo override Assert methods, for displaying test work
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

// todo extends from test base class (runner) method
public class Example1 {

    // open for programmers
    // todo replace by overrode method
    public static void runTests(){
        JUnitCore jUnitCore = new JUnitCore();
        //jUnitCore.addListener(new TextListener(System.out));// just test listeners
        jUnitCore.run(MyTests.class);
    }

    // open for teacher only
    // junit class for test some or own runner
    public static class MyTests{
        @Test
        public void _01testPositiveTest(){
            ContactInfo actual = Container.buildAll(19, "Ivan", "Kiev", "Ushynsk", "3");
            MyAssertDecorator.assertEquals(new ContactInfo(new User(19, "Ivan"), new Address("Kiev", "Ushynsk", "3")),
                    actual);
        }

        @Test
        public void _02testNotNUll(){
            ContactInfo actual = Container.buildAll(19, "Ivan", "Kiev", "Ushynsk", "3");
            MyAssertDecorator.assertNotNull(actual);
        }

        // todo how to log throwing exception see below method ???
        @Ignore
        @Test(expected = RuntimeException.class)
        public void _02testNegativeTest(){
            ContactInfo actual = Container.buildAll(0, "345345", "324234", "Ushynsk", "3");
        }
    }


}

// will be hidden for teachers and students
// wrapper for collecting test messages (expected, actual, result)
// todo decorate all public methods in org.junit.Assert
// todo extract
class MyAssertDecorator {
    public static void assertEquals(Object expected, Object actual) {
        try{
            Assert.assertEquals(null, expected, actual);
            System.out.printf("expected %s, actual %s, test passed\n", expected, actual);
        } catch (AssertionError e){
            System.out.printf("expected %s, actual %s, test filed\n", expected, actual);
            throw e;
        }
    }

    public static void assertNotNull(Object actual) {
        try{
            Assert.assertNotNull(actual);
            System.out.printf("expected %s, actual %s, test passed\n", "not null", actual);
        } catch (AssertionError e){
            System.out.printf("expected %s, actual %s, test filed\n", "not null", actual);
            throw e;
        }
    }
}

// OPEN for STUDENT
class User {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return !(name != null ? !name.equals(user.name) : user.name != null);

    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Address {

    String city;
    String street;
    String house;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return !(house != null ? !house.equals(address.house) : address.house != null);

    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}

class ContactInfo {

    User user;
    Address address;

    public ContactInfo(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public ContactInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "user=" + user +
                ", address=" + address +
                '}';
    }
}

class Container {
    // tell student do not change existed, you can add own classes, interfaces, any type

    // implemented task by student
    public static ContactInfo buildAll(int age, String name, String city, String street, String house){
        User user = new User();
        user.age = age;
        user.name = name;

        Address address = new Address();
        address.city = city;
        address.street = street;
        address.house = house;

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.user = user;
        contactInfo.address = address;

        return contactInfo;
    }

}











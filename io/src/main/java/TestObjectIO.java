import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 */
public class TestObjectIO {
    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("text");
        String fileName1 = resource.getFile() + "/person-detail.txt";
        String fileName2 = resource.getFile() + "person-detail.txt";
        String fileName3 = resource.getPath() + "/person-detail.txt";

        testObjectIO(fileName1,TransientPerson.class);
    }



    static void testObjectIO(String fileName, Class clazz) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            boolean isPerson = false;

            Object obj  = null;
            if("Person".equals(clazz.getName())) {
                isPerson = true;
                obj = new Person("musashi", 99, false);
            }else {
                obj = new TransientPerson("sasaki", 88, true);
            }
            oos.writeObject(obj);
            oos.flush();
            oos.close();

            //再从文件中把对象读出来然后反序列化称为对象
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            if(isPerson) {
                Person pRead = (Person) ois.readObject();
                System.out.println(pRead == obj);
                System.out.println(pRead.toString());
            }else {
                TransientPerson pRead = (TransientPerson) ois.readObject();
                System.out.println(pRead == obj);
                System.out.println(pRead.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person implements Serializable {
    String name;
    int age;
    boolean isHentai;

    public Person(String name, int age, boolean isHentai) {
        this.name = name;
        this.age = age;
        this.isHentai = isHentai;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isHentai=" + isHentai +
                '}';
    }
}

/**
 * transient 修饰的属性，在序列化时会忽略
 * 所以反序列化出来时，transient修饰的属性，使用默认值
 */
class TransientPerson implements Serializable {
    String name;
    int age;
    transient boolean isHentai;

    public TransientPerson(String name, int age, boolean isHentai) {
        this.name = name;
        this.age = age;
        this.isHentai = isHentai;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isHentai=" + isHentai +
                '}';
    }
}

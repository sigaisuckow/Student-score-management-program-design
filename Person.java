//定义一个 Person 类，表示人的基本信息
public class Person {
    //定义三个私有属性：姓名、年龄和性别
    private String name;
    private int age;
    private char gender; // M: 男性，F: 女性


    //定义一个有参构造方法，接收三个参数
    public Person(String name, int age, char gender) {
        //给属性赋参数值
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //定义三个公有的 get 方法，用于获取属性值
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    //定义三个公有的 set 方法，用于修改属性值
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    //定义一个公有的 toString 方法，用于返回对象的字符串表示
    public String toString() {
        return "Person[name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }
}

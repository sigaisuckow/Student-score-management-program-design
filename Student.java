//定义一个 Student 类，表示学生的信息，继承自 Person 类
public class Student extends Person {
    //定义一个私有属性：学号
    private String id;

    //定义一个有参构造方法，接收四个参数，调用父类的有参构造方法
    public Student(String name, int age, char gender, String id) {
        super(name, age, gender);
        //给属性赋参数值
        this.id = id;
    }

    //定义一个公有的 get 方法，用于获取属性值
    public String getId() {
        return id;
    }

    //定义一个公有的 set 方法，用于修改属性值
    public void setId(String id) {
        this.id = id;
    }

}

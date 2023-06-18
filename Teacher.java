//定义一个 Teacher 类，表示教师的信息，继承自 Person 类
public class Teacher extends Person {
    //定义私有属性：职称,id
    private String title;
    private String id;

    //定义一个有参构造方法，接收四个参数，调用父类的有参构造方法
    public Teacher(String name, int age, char gender, String title, String id) {
        super(name, age, gender);
        //给属性赋参数值
        this.title = title;
        this.id = id;
    }

    //定义一个公有的 get 方法，用于获取属性值
    public String getTitle() {
        return title;
    }

    //定义一个公有的 set 方法，用于修改属性值
    public void setTitle(String title) {
        this.title = title;
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

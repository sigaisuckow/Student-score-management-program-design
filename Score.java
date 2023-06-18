//定义一个 Score 类，表示成绩的信息
public class Score {
    //定义三个私有属性：课程、学生和分数
    private Course course;
    private Student student;
    private double score;


    //定义一个有参构造方法，接收三个参数
    public Score(Course course, Student student, double score) {
        //给属性赋参数值
        this.course = course;
        this.student = student;
        this.score = score;
    }

    //定义三个公有的 get 方法，用于获取属性值
    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public double getScore() {
        return score;
    }

    //定义三个公有的 set 方法，用于修改属性值
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setScore(double score) {
        this.score = score;
    }



    //定义一个公有的 toString 方法，用于返回对象的字符串表示
    public String toString() {
        return "Score[course=" + course + ", student=" + student + ", score=" + score + "]";
    }
}

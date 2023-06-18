//定义一个 Course 类，表示课程的信息
public class Course {
    //定义三个私有属性：课程编号、课程名称和教师
    private String code;
    private String name;
    private Teacher[] teachers;
    private int currentTeachers;

    //定义一个私有的数组属性：学生列表
    private Student[] students;

    //定义一个私有的整型属性：当前学生数
    private int currentStudents;

    //定义一个有参构造方法，接收2个参数
    public Course(String code, String name) {
        //给属性赋参数值
        this.code = code;
        this.name = name;
        this.teachers = new Teacher[1000];
        this.students = new Student[1000];
        currentStudents = 0;
    }

    //定义四个公有的 get 方法，用于获取属性值
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public int getCurrentTeachers() {
        return currentTeachers;
    }

    public Student[] getStudents() {
        return students;
    }

    // getcurrentStudents() method
    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentTeachers(int currentTeachers) {
        this.currentTeachers = currentTeachers;
    }

    // setCurrentStudents() method
    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    //定义四个公有的 set 方法，用于修改属性值
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher[] teachers) {
        this.teachers = teachers;
    }

    public void setStudents(Student[] students) {
        this.students = students;
        currentStudents = students.length;
    }

    //定义一个公有的方法，用于添加学生到课程中
    public boolean addStudent(Student student) {
        //如果当前学生数小于学生数组的长度
        if (currentStudents < students.length) {
            //将学生添加到数组中
            students[currentStudents] = student;
            //更新当前学生数
            currentStudents++;
            //返回添加成功
            return true;
        }
        //如果当前学生数等于学生数组的长度，返回添加失败
        return false;
    }

    //定义一个公有的方法，用于从课程中删除学生
    public boolean removeStudent(Student student) {
        //遍历学生数组，查找要删除的学生
        for (int i = 0; i < currentStudents; i++) {
            //如果找到了要删除的学生
            if (students[i].equals(student)) {
                //将后面的学生往前移动一位，覆盖掉要删除的学生
                for (int j = i; j < currentStudents -1; j++) {
                    students[j] = students[j+1];
                }
                //将最后一位学生置为空
                students[currentStudents -1] = null;
                //更新当前学生数
                currentStudents--;
                //返回删除成功
                return true;
            }
        }
        //如果没有找到要删除的学生，返回删除失败
        return false;
    }

    // addTeacher() method
    public boolean addTeacher(Teacher teacher) {
        //如果当前教师数小于教师数组的长度
        if (currentTeachers < teachers.length) {
            //将教师添加到数组中
            teachers[currentTeachers] = teacher;
            //更新当前教师数
            currentTeachers++;
            //返回添加成功
            return true;
        }
        //如果当前教师数等于教师数组的长度，返回添加失败
        return false;
    }

    // removeTeacher() method
    public boolean removeTeacher(Teacher teacher) {
        //遍历教师数组，查找要删除的教师
        for (int i = 0; i < currentTeachers; i++) {
            //如果找到了要删除的教师
            if (teachers[i].equals(teacher)) {
                //将后面的教师往前移动一位，覆盖掉要删除的教师
                for (int j = i; j < currentTeachers -1; j++) {
                    teachers[j] = teachers[j+1];
                }
                //将最后一位教师置为空
                teachers[currentTeachers -1] = null;
                //更新当前教师数
                currentTeachers--;
                //返回删除成功
                return true;
            }
        }
        //如果没有找到要删除的教师，返回删除失败
        return false;
    }

}


//导入 Scanner 类，用于接收用户输入
import java.util.Scanner;

//定义一个 Main 类，用于测试其他类的功能
public class Main {
    static Scanner input = new Scanner(System.in);
    //课程列表
    private static Course[] courses = new Course[1000];

    //当前课程数
    private static int currentCourses;

    //成绩列表
    private static Score[] scores = new Score[1000];

    //当前成绩数
    private static int currentScores;

    // 学生列表
    private static Student[] students = new Student[1000];
    // 当前学生数
    private static int currentStudents;

    // 教师列表
    private static Teacher[] teachers = new Teacher[1000];
    // 当前教师数
    private static int currentTeachers;


    // 定义一个公有的静态方法, 添加学生
    public static Boolean addStudent(Student student) {
        // 判断学生是否存在
        for (int i = 0; i < currentStudents; i++) {
            if (students[i].getId().equals(student.getId())) {
                System.out.println("学生已存在");
                return false;
            }
        }
        // 添加学生
        students[currentStudents] = student;
        currentStudents++;
        System.out.println("添加成功");
        return true;
    }
    // 定义一个公有的静态方法, 添加教师
    public static Boolean addTeacher(Teacher teacher) {
        // 判断教师是否存在
        for (int i = 0; i < currentTeachers; i++) {
            if (teachers[i].getId().equals(teacher.getId())) {
                System.out.println("教师已存在");
                return false;
            }
        }
        // 添加教师
        teachers[currentTeachers] = teacher;
        currentTeachers++;
        System.out.println("添加成功");
        return true;
    }
    // 定义一个公有的静态方法, 添加课程
    public static Boolean addCourse(Course course) {
        // 判断课程是否存在
        for (int i = 0; i < currentCourses; i++) {
            if (courses[i].getCode().equals(course.getCode())) {
                System.out.println("课程已存在");
                return false;
            }
        }
        // 添加课程
        courses[currentCourses] = course;
        currentCourses++;
        System.out.println("添加成功");
        return true;
    }
    // 定义一个公有的静态方法, 添加成绩
    public Boolean addScore(Score score) {
        // 判断成绩是否存在
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getStudent().getId().equals(score.getStudent().getId()) && scores[i].getCourse().getCode().equals(score.getCourse().getCode())) {
                System.out.println("成绩已存在");
                return false;
            }
        }
        // 添加成绩
        scores[currentScores] = score;
        currentScores++;
        System.out.println("添加成功");
        return true;
    }

    // 定义一个公有的静态方法, 删除学生
    public static Boolean deleteStudent(String id) {
        // 判断学生是否存在
        for (int i = 0; i < currentStudents; i++) {
            if (students[i].getId().equals(id)) {
                // 遍历所有Score对象，删除该学生的成绩
                for (int j = 0; j < currentScores; j++) {
                    if (scores[j].getStudent().getId().equals(id)) {
                        for (int k = j; k < currentScores - 1; k++) {
                            scores[k] = scores[k + 1];
                        }
                        currentScores--;
                    }
                }
                // 遍历所有课程，删除该学生的选课
                for (int j = 0; j < currentCourses; j++) {
                    for (int k = 0; k < courses[j].getCurrentStudents(); k++) {
                        if (courses[j].getStudents()[k].getId().equals(id)) {
                            for (int l = k; l < courses[j].getCurrentStudents() - 1; l++) {
                                courses[j].getStudents()[l] = courses[j].getStudents()[l + 1];
                            }
                            courses[j].setCurrentStudents(courses[j].getCurrentStudents() - 1);
                        }
                    }
                }

                // 删除学生
                for (int j = i; j < currentStudents - 1; j++) {
                    students[j] = students[j + 1];
                }
                currentStudents--;
                System.out.println("删除成功");
                return true;
            }
        }


        return false;
    }
    // 定义一个公有的静态方法, 删除教师
    public static Boolean deleteTeacher(String id) {
        // 判断教师是否存在
        for (int i = 0; i < currentTeachers; i++) {
            if (teachers[i].getId().equals(id)) {

                // 遍历所有课程，删除该教师的课程
                for (int j = 0; j < currentCourses; j++) {
                    courses[j].removeTeacher(teachers[i]);
                }
                // 删除教师
                for (int j = i; j < currentTeachers - 1; j++) {
                    teachers[j] = teachers[j + 1];
                }
                currentTeachers--;
                System.out.println("删除成功");
                return true;
            }
        }
        System.out.println("教师不存在");
        return false;
    }
    // 定义一个公有的静态方法, 删除课程
    public static Boolean deleteCourse(String code) {
        // 判断课程是否存在
        for (int i = 0; i < currentCourses; i++) {
            if (courses[i].getCode().equals(code)) {
                // 遍历所有Score对象，删除该课程的成绩
                for (int j = 0; j < currentScores; j++) {
                    if (scores[j].getCourse().getCode().equals(code)) {
                        for (int k = j; k < currentScores - 1; k++) {
                            scores[k] = scores[k + 1];
                        }
                        currentScores--;
                    }
                }

                // 删除课程
                for (int j = i; j < currentCourses - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                currentCourses--;
                System.out.println("删除成功");
                return true;
            }
        }
        System.out.println("课程不存在");
        return false;
    }


    ////查询与某门课程相关的信息
    public static void queryCourse() {
        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println("Course code: " + course.getCode());
        System.out.println("Course name: " + course.getName());
        System.out.print("Course teacher: ");
        for (int i = 0; i < course.getCurrentTeachers(); i++) {
            System.out.print(course.getTeachers()[i].getName() + " ");
        }
        System.out.println();
        System.out.println("Course student number: " + course.getCurrentStudents());
        System.out.println("Course student list: ");
        for (int i = 0; i < course.getCurrentStudents(); i++) {
            System.out.println(course.getStudents()[i].getId() + " " + course.getStudents()[i].getName());
        }
    }

    //查询某个学生所有课程的成绩
    public static void queryStudentScore() {
        System.out.println("Please enter the student ID:");
        String id = input.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("The student does not exist.");
            return;
        }
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student name: " + student.getName());
        System.out.println("Student score list: ");
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getStudent().getId().equals(id)) {
                System.out.println(scores[i].getCourse().getCode() + " " + scores[i].getCourse().getName() + " " + scores[i].getScore());
            }
        }
    }

    //查询某门课程所有学生的成绩，并计算平均分
    public static void queryCourseScore() {
        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println("Course code: " + course.getCode());
        System.out.println("Course name: " + course.getName());
        System.out.println("Course student number: " + course.getCurrentStudents());
        System.out.println("Course score list: ");
        double sum = 0;
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getCourse().getCode().equals(code)) {
                System.out.println(scores[i].getStudent().getId() + " " + scores[i].getStudent().getName() + " " + scores[i].getScore());
                sum += scores[i].getScore();
            }
        }
        System.out.println("Average score: " + sum / course.getCurrentStudents());
    }

    //查询某门课程的成绩排名
    public static void queryCourseRanking() {
        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println("Course code: " + course.getCode());
        System.out.println("Course name: " + course.getName());
        System.out.println("Course student number: " + course.getCurrentStudents());
        System.out.println("Course score list: ");
        Score[] scores1 = new Score[course.getCurrentStudents()];
        int index = 0;
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getCourse().getCode().equals(code)) {
                scores1[index++] = scores[i];
            }
        }
        for (int i = 0; i < index - 1; i++) {
            for (int j = 0; j < index - 1 - i; j++) {
                if (scores1[j].getScore() < scores1[j + 1].getScore()) {
                    Score temp = scores1[j];
                    scores1[j] = scores1[j + 1];
                    scores1[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < index; i++) {
            System.out.println(scores1[i].getStudent().getId() + " " + scores1[i].getStudent().getName() + " " + scores1[i].getScore());
        }
    }

    //查询某门课程所有学生在不同分数段的百分比：例如，优秀（90-100），良好（80-89），中等（70-79），及格（60-69），不及格（0-59）
    public static void queryCoursePercentage() {
        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println("Course code: " + course.getCode());
        System.out.println("Course name: " + course.getName());
        System.out.println("Course student number: " + course.getCurrentStudents());
        System.out.println("Course score list: ");
        int[] count = new int[5];
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getCourse().getCode().equals(code)) {
                if (scores[i].getScore() >= 90) {
                    count[0]++;
                } else if (scores[i].getScore() >= 80) {
                    count[1]++;
                } else if (scores[i].getScore() >= 70) {
                    count[2]++;
                } else if (scores[i].getScore() >= 60) {
                    count[3]++;
                } else {
                    count[4]++;
                }
            }
        }
        System.out.println("优秀：" + count[0] * 100 / course.getCurrentStudents() + "%");
        System.out.println("良好：" + count[1] * 100 / course.getCurrentStudents() + "%");
        System.out.println("中等：" + count[2] * 100 / course.getCurrentStudents() + "%");
        System.out.println("及格：" + count[3] * 100 / course.getCurrentStudents() + "%");
        System.out.println("不及格：" + count[4] * 100 / course.getCurrentStudents() + "%");
    }

    //通过键盘修改某个学生某门课程的成绩
    public static void updateScore() {
        System.out.println("Please enter the student id:");
        String id = input.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("The student does not exist.");
            return;
        }
        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println("Please enter the new score:");
        double score = input.nextDouble();
        input.nextLine();
        for (int i = 0; i < currentScores; i++) {
            if (scores[i].getStudent().getId().equals(id) && scores[i].getCourse().getCode().equals(code)) {
                scores[i].setScore(score);
                System.out.println("The score has been updated.");
                return;
            }
        }
        System.out.println("The student has not selected the course.");
    }

    //添加或删除某门课程的学生
    public static void updateCourseStudent() {
        System.out.println("Please enter the operation\n1:add\n2:delete\n");
        int operation = input.nextInt();
        input.nextLine();

        System.out.println("Please enter the course code:");
        String code = input.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }

        System.out.println("Please enter the student id:");
        String id = input.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("The student does not exist.");
            return;
        }

        if (operation == 1) {
            for (int i = 0; i < currentScores; i++) {
                if (scores[i].getStudent().getId().equals(id) && scores[i].getCourse().getCode().equals(code)) {
                    System.out.println("The student has selected the course.");
                    return;
                }
            }
            // 输入分数
            System.out.println("Please enter the score:");
            double score = input.nextDouble();
            input.nextLine();

            course.addStudent(student);
            scores[currentScores] = new Score(course, student, score);
            currentScores++;
            System.out.println("The student has been added.");
        } else if (operation == 2) {
            for (int i = 0; i < currentScores; i++) {
                if (scores[i].getStudent().getId().equals(id) && scores[i].getCourse().getCode().equals(code)) {
                    for (int j = i; j < currentScores - 1; j++) {
                        scores[j] = scores[j + 1];
                    }
                    currentScores--;
                    course.removeStudent(student);
                    System.out.println("The student has been deleted.");
                }
            }
            System.out.println("The student has not selected the course.");
        } else {
            System.out.println("Invalid operation.");
        }

    }


    //定义一个公有的静态方法，用于显示菜单选项
    public static void showMenu() {
        System.out.println("Welcome to the student score management system!");
        System.out.println("Please choose one of the following options:");
        //查询与某门课程相关的信息
        System.out.println("1. Query information related to a course.");
        //查询某个学生所有课程的成绩
        System.out.println("2. Query the marks of a student for all courses.");
        //查询某门课程所有学生的成绩，并计算平均分
        System.out.println("3. Query the marks of all students in a course and calculating the average mark for that course.");
        //查询某门课程的成绩排名
        System.out.println("4. Query the ranking of marks in a course.");
        //查询某门课程所有学生在不同分数段的百分比：例如，优秀（90-100），良好（80-89），中等（70-79），及格（60-69），不及格（0-59）
        System.out.println("5. Query the percentage of all students in a course in different mark bands.");
        //通过键盘修改某个学生某门课程的成绩
        System.out.println("6. Modify a student's mark in a course through the keyboard.");
        //添加或删除某门课程的学生
        System.out.println("7. Add or remove students from a course.");
        //添加或删除学生
        System.out.println("8. Add or remove a student.");
        //添加或删除课程
        System.out.println("9. Add or remove a course.");
        //添加或删除老师
        System.out.println("10. Add or remove a teacher.");
        //显示所有学生
        System.out.println("11. Display all students.");
        //显示所有课程
        System.out.println("12. Display all courses.");
        //显示所有老师
        System.out.println("13. Display all teachers.");
        System.out.println("14. Log out of the system.");
        System.out.println("Please enter your choice (1-14):");
    }

    //定义一个公有的静态方法，用于根据课程编号查找课程对象
    public static Course findCourseByCode(String code) {
        //遍历课程列表，查找匹配的课程对象
        for(int i=0; i<currentCourses; i++) {
            if(courses[i].getCode().equals(code)) {
                return courses[i];
            }
        }
        //如果没有找到，返回空
        return null;
    }

    //定义一个公有的静态方法，用于根据学生编号查找学生对象
    public static Student findStudentById(String id) {
        //遍历课程列表，查找匹配的学生对象
        for(int i=0; i<currentStudents; i++) {
            if(students[i].getId().equals(id)) {
                return students[i];
            }
        }
        //如果没有找到，返回空
        return null;
    }

    //定义一个公有的静态方法，用于根据课程和学生查找成绩对象
    public static Score findScoreByCourseAndStudent(Course course, Student student) {
        //遍历成绩列表，查找匹配的成绩对象
        for (Score score : scores) {
            if (score.getCourse().equals(course) && score.getStudent().equals(student)) {
                return score;
            }
        }
        //如果没有找到，返回空
        return null;
    }

    //定义主方法，用于启动程序
    public static void main(String[] args) {
        //创建一个 Scanner 对象，用于接收用户输入
        Scanner input = new Scanner(System.in);

        // 登录
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        System.out.println("Please enter your password:");
        String password = input.nextLine();
        if (!username.equals("admin") || !password.equals("123456")) {
            System.out.println("Invalid username or password.");
            return;
        }

        // 提前建立数据
        // 创建老师
        teachers[0] = new Teacher("Tom", 30, 'M', "teacher", "1001");
        teachers[1] = new Teacher("Jerry", 25, 'M', "teacher", "1002");
        teachers[2] = new Teacher("Mary", 28, 'F', "teacher", "1003");
        currentTeachers = 3;

        // 创建学生10
        students[0] = new Student("a1", 20, 'M', "10001");
        students[1] = new Student("a2", 21, 'M', "10002");
        students[2] = new Student("a3", 22, 'M', "10003");
        students[3] = new Student("a4", 23, 'M', "10004");
        students[4] = new Student("a5", 24, 'M', "10005");
        students[5] = new Student("a6", 25, 'M', "10006");
        students[6] = new Student("a7", 26, 'M', "10007");
        students[7] = new Student("a8", 27, 'M', "10008");
        students[8] = new Student("a9", 28, 'M', "10009");
        students[9] = new Student("a10", 29, 'M', "10010");
        currentStudents = 10;

        // 创建课程
        courses[0] = new Course("1001", "chinese");
        courses[1] = new Course("1002", "math");
        courses[2] = new Course("1003", "english");
        currentCourses = 3;


        // 添加教师
        for(int i=0; i<currentCourses; i++) {
            courses[i].addTeacher(teachers[i]);
        }

        // 选课
        for(int i=0; i<currentCourses; i++) {
            for(int j=5; j<currentStudents; j++) {
                courses[i].addStudent(students[j]);
                // 添加分数
                int score = (int)(Math.random() * 100);
                scores[currentScores++] = new Score(courses[i], students[j], score);
            }
        }

        courses[0].addStudent(students[0]); // 将学生a1添加到语文课程
        scores[currentScores++] = new Score(courses[0], students[0], 100); // 将学生a1的语文成绩添加到成绩列表



        //定义一个布尔变量，用于控制循环
        boolean flag = true;

        //循环显示菜单选项，并执行用户选择的功能
        while (flag) {
            showMenu();
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 14) {
                flag = false;
            } else {
                executeChoice(choice);
            }
        }

        //关闭 Scanner 对象
        input.close();
    }

    private static void executeChoice(int choice) {
        switch (choice) {
            case 1:
                queryCourse();
                break;
            case 2:
                queryStudentScore();
                break;
            case 3:
                queryCourseScore();
                break;
            case 4:
                queryCourseRanking();
                break;
            case 5:
                queryCoursePercentage();
                break;
            case 6:
                updateScore();
                break;
            case 7:
                updateCourseStudent();
                break;
            case 8:
                modifyStudent();
                break;
            case 9:
                modifyCourse();
                break;
            case 10:
                modifyTeacher();
                break;
            case 11:
                displayStudents();
                break;
            case 12:
                displayCourses();
                break;
            case 13:
                displayTeachers();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void displayTeachers() {
        for(int i = 0; i < currentTeachers; i++){
            System.out.print(i+1);
            System.out.println(' ' + teachers[i].getId() + ' ' + teachers[i].getName());
        }
    }

    private static void displayCourses() {
        for(int i = 0; i < currentCourses; i++){
            System.out.print(i+1);
            System.out.println(' ' + courses[i].getCode() + ' ' + courses[i].getName());
        }
    }

    private static void displayStudents() {
        for(int i = 0; i < currentStudents; i++){
            System.out.print(i+1);
            System.out.println(' ' + students[i].getId() + ' ' + students[i].getName());
        }
    }

    private static void modifyTeacher() {
        int choice = input.nextInt();
        input.nextLine();

        if(choice == 1){
            System.out.println("Please enter the teacher id:");
            String id = input.nextLine();
            System.out.println("Please enter the teacher name:");
            String name = input.nextLine();
            //char gender
            System.out.println("Please enter the teacher gender:");
            char gender = input.nextLine().charAt(0);
            //int age
            System.out.println("Please enter the teacher age:");
            int age = input.nextInt();
            input.nextLine();
            //String title
            System.out.println("Please enter the teacher title:");
            String title = input.nextLine();
            Teacher teacher = new Teacher(name, age, gender, title, id);
            addTeacher(teacher);
        }
        else if(choice == 2){
            System.out.println("Please enter the teacher id:");
            String id = input.nextLine();
            deleteTeacher(id);
        }
        else{
            System.out.println("Invalid choice.");
        }
    }

    private static Teacher findTeacherById(String id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return teacher;
            }
        }
        return null;
    }

    private static void modifyCourse() {

        System.out.println("Please enter the operation you want to perform:");
        System.out.println("1. Add a course.");
        System.out.println("2. Delete a course.");
        int choice = input.nextInt();
        input.nextLine();

        if(choice == 1){
            System.out.println("Please enter the course code:");
            String code = input.nextLine();
            System.out.println("Please enter the course name:");
            String name = input.nextLine();
            Course course = new Course(code, name);
            addCourse(course);
        }
        else if(choice == 2){
            System.out.println("Please enter the course code:");
            String code = input.nextLine();
            deleteCourse(code);
        }
        else{
            System.out.println("Invalid choice.");
        }
    }

    private static void modifyStudent() {


        System.out.println("Please enter the operation you want to perform:");
        System.out.println("1. Add a student.");
        System.out.println("2. Delete a student.");
        int choice = input.nextInt();
        input.nextLine();

        if(choice == 1){
            //String name, int age, char gender, String id
            System.out.println("Please enter the student name:");
            String name = input.nextLine();
            System.out.println("Please enter the student age:");
            int age = input.nextInt();
            input.nextLine();
            System.out.println("Please enter thr student gender(char):");
            char gender = input.nextLine().charAt(0);
            System.out.println("Please enter the student id:");
            String id = input.nextLine();
            Student student = new Student(name, age, gender, id);
            addStudent(student);
        }
        else if(choice == 2){
            System.out.println("Please enter the student id:");
            String id = input.nextLine();
            deleteStudent(id);
        }
        else{
            System.out.println("Invalid choice.");
        }
    }
}
